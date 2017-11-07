package com.northbrain.list.course.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.OrchCourseVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.list.course.domain.ICourseDomain;
import com.northbrain.list.course.domain.IOperationRecordDomain;
import com.northbrain.list.course.domain.IStorageDomain;
import com.northbrain.list.course.service.ICourseService;

import feign.FeignException;

/**
 * 类名：课程服务接口的实现类
 * 用途：读取课程相关信息
 * @author Jiakun
 * @version 1.0
 */
@Service
public class CourseService implements ICourseService
{
    private static Logger logger = Logger.getLogger(CourseService.class);
    private final ICourseDomain courseDomain;
    private final IStorageDomain storageDomain;
    private final IOperationRecordDomain operationRecordDomain;

    @Autowired
    public CourseService(ICourseDomain courseDomain, IStorageDomain storageDomain, IOperationRecordDomain operationRecordDomain)
    {
        this.courseDomain = courseDomain;
        this.storageDomain = storageDomain;
        this.operationRecordDomain = operationRecordDomain;
    }

    /**
     * 方法：读取在用的课程列表
     * @return 在用的课程列表，用ServiceVO封装
     */
    public ServiceVO readInUsedCourses()
    {
        ServiceVO orchServiceVO = new ServiceVO();
        ServiceVO atomServiceVO;

        try
        {
            if (courseDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDomain");
                return orchServiceVO;
            }

            if (storageDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDomain");
                return orchServiceVO;
            }

            atomServiceVO = JSON.parseObject(this.courseDomain.readInUsedCourses(), ServiceVO.class);

            if (atomServiceVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
                return orchServiceVO;
            }

            if(!atomServiceVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOMIC_SERVICE + atomServiceVO.getResponseCode());
                return orchServiceVO;
            }

            Object atomServiceResponse = atomServiceVO.getResponse();

            if (atomServiceResponse == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceResponse");
                return orchServiceVO;
            }

            logger.info("-------------" + atomServiceResponse.getClass());
            //JSONArray atomCourseVOS = JSONArray.parseArray(atomServiceResponse, )
            JSONArray atomCourseVOS = JSONArray.class.cast(atomServiceResponse);

            //List<CourseVO> atomCourseVOS = (ArrayList<CourseVO>) atomServiceVO.getResponse();

            logger.info(String.format("-------------%s", atomCourseVOS.get(0)));

            if (atomCourseVOS.size() == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomCourseVOS");
                return orchServiceVO;
            }

            List<OrchCourseVO> orchCourseVOS = new ArrayList<>();
            OrchCourseVO orchCourseVO;

            for (Object object : atomCourseVOS)
            {
                orchCourseVO = new OrchCourseVO();
                logger.info(String.format("++++++++++++%s", object.getClass()));
                //ObjectMapper mapper = new ObjectMapper();
                //CourseVO courseVO = mapper.readValue((JsonParser) object, new TypeReference<CourseVO>(){});
                //CourseVO courseVO = JSON.parseObject(object, CourseVO.class);
                CourseVO courseVO = (CourseVO) JSONObject.toJavaObject((JSON) object, CourseVO.class);
                orchCourseVO.setCourseId(courseVO.getCourseId());
                orchCourseVO.setName(courseVO.getName());
                orchCourseVO.setGrade(courseVO.getGrade());
                orchCourseVO.setLevel(courseVO.getLevel());
                orchCourseVO.setSubject(courseVO.getSubject());

                ServiceVO thumbnailVO = JSON.parseObject(this.storageDomain.readStorage(courseVO.getCourseId()), ServiceVO.class);

                if (thumbnailVO == null)
                {
                    logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "thumbnailVO");
                    continue;
                }

                Object thumbnailResponse = thumbnailVO.getResponse();

                if (thumbnailResponse == null)
                {
                    logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "thumbnailResponse");
                    continue;
                }

                StorageVO atomStorageVO = StorageVO.class.cast(thumbnailResponse);

                if (atomStorageVO == null)
                {
                    logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomStorageVO");
                    continue;
                }

                orchCourseVO.setThumbnail(atomStorageVO.getUri());
                orchCourseVO.setStatus(courseVO.getStatus());
                orchCourseVO.setCreateTime(courseVO.getCreateTime());
                orchCourseVO.setStatusTime(courseVO.getStatusTime());
                orchCourseVO.setDesciption(courseVO.getDesciption());

                orchCourseVOS.add(orchCourseVO);
            }

            orchServiceVO.setResponse(orchCourseVOS);
            orchServiceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (ClassCastException classCastException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(classCastException));
            orchServiceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_CLASS_CAST_EXCEPTION);
        }
        catch(IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            orchServiceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            orchServiceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (FeignException feignException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(feignException));
            orchServiceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FEIGN_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            orchServiceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        orchServiceVO.setResponseTime(new Date());
        return orchServiceVO;
    }
}
