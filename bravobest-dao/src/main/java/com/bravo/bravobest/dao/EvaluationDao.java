package com.bravo.bravobest.dao;

import com.bravo.bravobest.api.entity.Evaluation;
import com.bravo.bravobest.api.entity.Menu;
import org.springframework.web.servlet.tags.EvalTag;

import java.util.List;
import java.util.Map;

public interface EvaluationDao {

    /**
     * 新增绩效考评主信息
     * @param evaluation
     * @return
     * @throws Exception
     */
    int addEvaluation(Evaluation evaluation) throws Exception;

    /**
     * 新增绩效考评详情信息
     * @param evaluationDetialList
     * @return
     * @throws Exception
     */
    int addEvaluationDetail(List<Map<String,Object>> evaluationDetialList) throws Exception;
}
