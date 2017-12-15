package com.northbrain.list.course.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.OperationRecordException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.CourseVO;
import com.northbrain.base.common.model.vo.atom.OperationRecordVO;
import com.northbrain.base.common.model.vo.orch.OrchCourseVO;
import com.northbrain.base.common.model.vo.atom.StorageVO;
import com.northbrain.list.course.dao.ICourseDAO;
import com.northbrain.list.course.dao.IOperationRecordDAO;
import com.northbrain.list.course.dao.ISequenceDAO;
import com.northbrain.list.course.dao.IStorageDAO;
import com.northbrain.list.course.domain.ICourseDomain;
import com.northbrain.list.course.dto.ICourseDTO;

/**
 * 类名：课程DOMAIN接口的实现类
 * 用途：实现课程相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
@Component
public class CourseDomain implements ICourseDomain
{
    private static Logger logger = Logger.getLogger(CourseDomain.class);
    private final ICourseDAO courseDAO;
    private final IStorageDAO storageDAO;
    private final IOperationRecordDAO operationRecordDAO;
    private final ICourseDTO courseDTO;
    private final ISequenceDAO sequenceDAO;

    @Autowired
    public CourseDomain(ICourseDAO courseDAO, IStorageDAO storageDAO, IOperationRecordDAO operationRecordDAO, ICourseDTO courseDTO, ISequenceDAO sequenceDAO)
    {
        this.courseDAO = courseDAO;
        this.storageDAO = storageDAO;
        this.operationRecordDAO = operationRecordDAO;
        this.courseDTO = courseDTO;
        this.sequenceDAO = sequenceDAO;
    }

    /**
     * 方法：读取在用的课程列表
     * 为避免多次调用微服务，对于存储信息，可采用一次性调用，然后循环查找。
     * 在调用微服务的过程中，将记录信息输出至log中，在exception和最终才将操作记录持久化。
     * @return 在用的课程列表
     */
    @Override
    public List<OrchCourseVO> readInUsedCourses() throws Exception
    {
        int rank = 0;

        //1、获取全局唯一的序列号，作为操作记录、事务的流水号
        int operationRecordId = readAtomNextGlobalValue();

        if(operationRecordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operationRecordId:" + String.valueOf(operationRecordId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = createOperationRecord(operationRecordId, BaseType.OPERATETYPE.READ,
                8888, BaseType.DOMAIN.LIST, Constants.BUSINESS_LIST_COURSE_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, "TEST");

        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.info(operationRecordVO);

        //3、调用课程列表原子服务，获取课程列表
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PRODUCT,
                Constants.BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        List<CourseVO> courseVOS = readAtomInUsedCourses();

        if (courseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "courseVOS");
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        //4、根据课程列表，获取课程缩略图的存储列表
        rank++;
        operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.RESOURCE,
                Constants.BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        List<StorageVO> storageVOS = readAtomStorages(courseVOS);

        if (storageVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storageVOS");
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        //5、根据课程列表和缩略图URL列表，循环生成编排层的课程列表。采用等值连接的方式，避免没有存储信息的数据暴露出去。
        List<OrchCourseVO> orchCourseVOS = new ArrayList<>();

        for (CourseVO courseVO: courseVOS)
        {
            OrchCourseVO orchCourseVO = new OrchCourseVO();

            orchCourseVO.setCourseId(courseVO.getCourseId());
            orchCourseVO.setName(courseVO.getName());
            orchCourseVO.setGrade(courseVO.getGrade());
            orchCourseVO.setLevel(courseVO.getLevel());
            orchCourseVO.setSubject(courseVO.getSubject());

            boolean isExists = false;

            for (StorageVO storageVO: storageVOS)
            {
                if(Objects.equals(storageVO.getStorageId(), courseVO.getThumbnail()))
                {
                    isExists = true;
                    orchCourseVO.setThumbnail(storageVO.getUri());
                    break;
                }
            }

            //如果不存在，那么就不增加该条记录，确保为等值连接方式。
            if(!isExists)
                continue;

            orchCourseVO.setStatus(courseVO.getStatus());
            orchCourseVO.setCreateTime(courseVO.getCreateTime());
            orchCourseVO.setStatusTime(courseVO.getStatusTime());
            orchCourseVO.setDesciption(courseVO.getDesciption());

            orchCourseVOS.add(orchCourseVO);
        }

        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);

        return orchCourseVOS;
    }

    /**
     * 方法：通过微服务获取全局唯一序列号
     * @return 全局唯一序列号
     * @throws Exception 异常
     */
    private int readAtomNextGlobalValue() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if (courseDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Integer nextGlobalValue = courseDTO.convertToInteger(sequenceDAO.readAtomNextGlobalValue());

        logger.info(Hints.HINT_BUSINESS_COMMON_SEQUENCE_NEXT_VALUE + String.valueOf(nextGlobalValue));

        return nextGlobalValue;
    }

    /**
     * 方法：创建一条操作记录
     * @param operationRecordId 操作记录流水号
     * @param operationType 操作类型
     * @param operatorId 操作工号
     * @param domain 服务归属域
     * @param serviceName 编排服务名称
     * @param status 状态
     * @param description 描述（如A/B环境等）
     * @return 操作记录对象
     * @throws Exception 异常
     */
    private OperationRecordVO createOperationRecord(int operationRecordId, BaseType.OPERATETYPE operationType, int operatorId,
                                     BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status,
                                     String description) throws Exception
    {
        if(operationRecordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operationRecordId:" + String.valueOf(operationRecordId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operatorId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operatorId:" + String.valueOf(operatorId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(serviceName == null || serviceName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceName");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        OperationRecordVO operationRecordVO = new OperationRecordVO();
        operationRecordVO.setRecordId(operationRecordId);
        operationRecordVO.setOperateType(operationType.name());
        operationRecordVO.setOperatorId(operatorId);
        operationRecordVO.setDomain(domain.name());
        operationRecordVO.setServiceName(serviceName);
        operationRecordVO.setStatus(status.ordinal());
        operationRecordVO.setStartTime(new Date());
        operationRecordVO.setFinishTime(new Date());
        operationRecordVO.setDescription(description);

        return operationRecordVO;
    }

    /**
     * 方法：通过微服务新增一条操作记录，将操作记录持久化
     * @param operationRecordVO 操作记录对象
     * @throws Exception 异常
     */
    private void createAtomOperationRecord(OperationRecordVO operationRecordVO) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if (courseDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        boolean isCreated = courseDTO.convertToBoolean(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

        if(!isCreated)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }
    }

    /**
     * 方法：新增一条操作明细记录
     * @param operationRecordVO 操作记录值对象
     * @param rank 序号
     * @param operationType 操作类型
     * @param domain 域
     * @param serviceName 服务名称
     * @param status 状态
     * @return 操作明细对象
     * @throws Exception 异常
     */
    private OperationRecordVO.OperationRecordDetailVO createOperationRecordDetail(OperationRecordVO operationRecordVO, int rank, BaseType.OPERATETYPE operationType,
                                                                                      BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rank <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "rank:" + String.valueOf(rank));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(serviceName == null || serviceName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceName");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if (courseDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = new OperationRecordVO.OperationRecordDetailVO();
        operationRecordDetailVO.setRecordDetailId((long) (operationRecordVO.getRecordId() * Constants.BUSINESS_COMMON_OPERATION_RECORD_DETAIL_ID_MULTIPLE + rank));
        operationRecordDetailVO.setRank(rank);
        operationRecordDetailVO.setOperateType(operationType.name());
        operationRecordDetailVO.setDomain(domain.name());
        operationRecordDetailVO.setServiceName(serviceName);
        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setStartTime(new Date());
        operationRecordDetailVO.setFinishTime(new Date());

        if(operationRecordVO.addOperationRecordDetail(operationRecordDetailVO))
        {
            return operationRecordDetailVO;
        }
        else
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }
    }

    /**
     * 方法：调用课程列表原子服务，获取课程列表
     * @return 课程列表
     * @throws Exception 异常
     */
    private List<CourseVO> readAtomInUsedCourses() throws Exception
    {
        if (courseDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if (courseDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        JSONArray atomCourseVOS = courseDTO.convertToCourseVOArray(this.courseDAO.readAtomInUsedCourses());

        if (atomCourseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomCourseVOS");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<CourseVO> courseVOS = new ArrayList<>();

        for (Object atomCourseVOObject: atomCourseVOS)
        {
            CourseVO courseVO = courseDTO.convertToCourseVO(atomCourseVOObject);

            if(courseVO == null)
                continue;

            courseVOS.add(courseVO);
        }

        return courseVOS;
    }

    /**
     * 方法：读取课程列表中的存储信息
     * @param courseVOS 课程列表
     * @return 存储信息列表
     */
    private List<StorageVO> readAtomStorages(List<CourseVO> courseVOS) throws Exception
    {
        if (courseVOS == null || courseVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "courseVOS");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (courseDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if (storageDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<Integer> storageIds = new ArrayList<>();

        for (CourseVO courseVO: courseVOS)
        {
            storageIds.add(courseVO.getThumbnail());
        }

        if (storageIds.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storageIds");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        JSONArray atomStorageVOS = courseDTO.convertToStorageVOArray(this.storageDAO.readAtomStorages(storageIds));

        if (atomStorageVOS == null || atomStorageVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomStorageVOS");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<StorageVO> storageVOS = new ArrayList<>();

        for (Object atomStorageVOObject: atomStorageVOS)
        {
            StorageVO storageVO = courseDTO.convertToStorageVO(atomStorageVOObject);

            if(storageVO == null)
                continue;

            storageVOS.add(storageVO);
        }

        return storageVOS;
    }
}
