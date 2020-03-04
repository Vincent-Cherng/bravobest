package com.bravo.bravobest.service;

import com.bravo.bravobest.api.entity.Evaluation;
import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.binterface.EvaluationService;
import com.bravo.bravobest.common.util.ResultUtils;
import com.bravo.bravobest.dao.EvaluationDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public ResultData addEvaluation(Evaluation evaluation) throws Exception {
        try{
            int i = evaluationDao.addEvaluation(evaluation);
            if (i > 0){
                List<Map<String, Object>> mapList = new ArrayList<>();
                Class<? extends Evaluation> aClass = evaluation.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                int questionNum = 0;
                for (Field field : declaredFields) {
                    String fieldName = field.getName();
                    if (StringUtils.isNotBlank(fieldName) && fieldName.startsWith("question")){
                        questionNum++;
                        Map<String, Object> map = new HashMap<>();
                        map.put("evaluationId",evaluation.getEvalutionId());
                        map.put("evaluationStandardNo",fieldName);
                        Method method = aClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                        String value = (String)method.invoke(evaluation);
                        map.put("evaluationPoint",value);
                        mapList.add(map);
                    }
                }
                int saveDetailNum = evaluationDao.addEvaluationDetail(mapList);
                if(saveDetailNum != questionNum){
                    throw new RuntimeException("保存绩效考评信息失败！");
                }
            } else {
                throw new RuntimeException("保存绩效考评信息失败！");
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("保存绩效考评信息失败！");
        }
        return ResultUtils.success("成功保存绩效考评信息！");
    }
}
