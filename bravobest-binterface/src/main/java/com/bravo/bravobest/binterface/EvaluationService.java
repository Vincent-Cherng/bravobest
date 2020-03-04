package com.bravo.bravobest.binterface;

import com.bravo.bravobest.api.entity.Evaluation;
import com.bravo.bravobest.api.entity.ResultData;

public interface EvaluationService {

    /**
     * 新增绩效考评
     * @param evalution
     * @return
     * @throws Exception
     */
    ResultData addEvaluation(Evaluation evalution) throws Exception;
}
