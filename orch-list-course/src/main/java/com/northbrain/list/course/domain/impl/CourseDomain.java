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
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.base.common.model.vo.OrchCourseVO;
import com.northbrain.base.common.model.vo.StorageVO;
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
        OperationRecordVO operationRecordVO = createAtomOperationRecord(operationRecordId, BaseType.OPERATETYPE.READ,
                8888, BaseType.DOMAIN.LIST, Constants.BUSINESS_LIST_COURSE_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, "TEST");

        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //3、调用课程列表原子服务，获取课程列表
        List<CourseVO> courseVOS = readAtomInUsedCourses(operationRecordVO, rank++);

        if (courseVOS == null || courseVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "courseVOS");
            updateAtomOperationRecord(operationRecordVO, BaseType.STATUS.FAILURE);

            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //4、根据课程列表，获取课程缩略图的存储列表
        List<StorageVO> storageVOS = readAtomStorages(operationRecordVO, rank++, courseVOS);

        if (storageVOS == null || storageVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storageVOS");
            updateAtomOperationRecord(operationRecordVO, BaseType.STATUS.FAILURE);

            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        //5、根据课程列表和缩略图URL列表，循环生成编排层的课程列表
        List<OrchCourseVO> orchCourseVOS = new ArrayList<>();

        for (CourseVO courseVO: courseVOS)
        {
            OrchCourseVO orchCourseVO = new OrchCourseVO();

            orchCourseVO.setCourseId(courseVO.getCourseId());
            orchCourseVO.setName(courseVO.getName());
            orchCourseVO.setGrade(courseVO.getGrade());
            orchCourseVO.setLevel(courseVO.getLevel());
            orchCourseVO.setSubject(courseVO.getSubject());

            for (StorageVO storageVO: storageVOS)
            {
                if(Objects.equals(storageVO.getStorageId(), courseVO.getThumbnail()))
                {
                    orchCourseVO.setThumbnail(storageVO.getUri());
                    break;
                }
            }

            orchCourseVO.setStatus(courseVO.getStatus());
            orchCourseVO.setCreateTime(courseVO.getCreateTime());
            orchCourseVO.setStatusTime(courseVO.getStatusTime());
            orchCourseVO.setDesciption(courseVO.getDesciption());

            orchCourseVOS.add(orchCourseVO);
        }

        updateAtomOperationRecord(operationRecordVO, BaseType.STATUS.SUCCESS);

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
     * 方法：通过微服务创建一条操作记录
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
    private OperationRecordVO createAtomOperationRecord(int operationRecordId, BaseType.OPERATETYPE operationType, int operatorId,
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

        boolean isCreated = courseDTO.convertToBoolean(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

        if(!isCreated)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }

        return operationRecordVO;
    }

    /**
     * 方法：更新操作记录，包括状态与完成时间
     * @param operationRecordVO 操作记录对象
     * @param status 状态
     * @throws Exception 异常
     */
    private void updateAtomOperationRecord(OperationRecordVO operationRecordVO, BaseType.STATUS status) throws Exception
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

        operationRecordVO.setStatus(status.ordinal());
        operationRecordVO.setFinishTime(new Date());

        boolean isUpdated = courseDTO.convertToBoolean(operationRecordDAO.updateAtomOperationRecord(operationRecordVO));

        if(!isUpdated)
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
    private OperationRecordVO.OperationRecordDetailVO createAtomOperationRecordDetail(OperationRecordVO operationRecordVO, int rank, BaseType.OPERATETYPE operationType,
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

        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        boolean isCreated = courseDTO.convertToBoolean(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

        if(!isCreated)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }

        return operationRecordDetailVO;
    }

    /**
     * 方法：更新操作记录明细
     * @param operationRecordVO 操作记录对象
     * @param operationRecordDetailVO 操作记录明细对象
     * @param status 要更新的状态
     * @throws Exception 异常
     */
    private void updateAtomOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                 OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                 BaseType.STATUS status) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
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

        operationRecordVO.setOperationRecordDetailVOS(null);
        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        boolean isUpdated = courseDTO.convertToBoolean(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

        if(!isUpdated)
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
    private List<CourseVO> readAtomInUsedCourses(OperationRecordVO operationRecordVO, int rank) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (rank <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "rank:" + String.valueOf(rank));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

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

        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = createAtomOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PRODUCT,
                Constants.BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        JSONArray atomCourseVOS = courseDTO.convertToCourseVOArray(this.courseDAO.readAtomInUsedCourses());

        if (atomCourseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomCourseVOS");
            updateAtomOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE);

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

        updateAtomOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS);

        return courseVOS;
    }

    /**
     * 方法：读取课程列表中的存储信息
     * @param courseVOS 课程列表
     * @return 存储信息列表
     */
    private List<StorageVO> readAtomStorages(OperationRecordVO operationRecordVO, int rank, List<CourseVO> courseVOS) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (rank <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "rank:" + String.valueOf(rank));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

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

        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = createAtomOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.RESOURCE,
                Constants.BUSINESS_RESOURCE_STORAGE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        List<Integer> storageIds = new ArrayList<>();

        for (CourseVO courseVO: courseVOS)
        {
            storageIds.add(courseVO.getThumbnail());
        }

        if (storageIds.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "storageIds");
            updateAtomOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE);

            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        JSONArray atomStorageVOS = courseDTO.convertToStorageVOArray(this.storageDAO.readAtomStorages(storageIds));

        if (atomStorageVOS == null || atomStorageVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomStorageVOS");
            updateAtomOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE);

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

        updateAtomOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS);

        return storageVOS;
    }
}
