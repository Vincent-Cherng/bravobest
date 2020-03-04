package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.Evaluation;
import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.binterface.EvaluationService;
import com.bravo.bravobest.common.base.BaseController;
import com.bravo.bravobest.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequestMapping("/evaluation")
@RestController
public class EvaluationController extends BaseController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     *
     * @return
     */
    @RequestMapping("addEvalution")
    public ResultData addEvaluation(HttpServletRequest req, Evaluation evaluation) throws Exception {
        evaluation.setEvalutionPerson(super.getCurUserNo(req));
        ResultData data = null;
        try{
            data = evaluationService.addEvaluation(evaluation);
        } catch (Exception e){
            e.printStackTrace();
            data = ResultUtils.fail(e.getMessage());
        }
        return data;
    }

    @RequestMapping("/getPeriod")
    public ResultData getPeriod(){
        return ResultUtils.success((Object) LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM")));
    }
}
