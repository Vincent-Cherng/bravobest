package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.util.ResultUtils;
import com.bravo.bravobest.common.validatecode.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;


@RequestMapping("/")
@RestController
public class LoginController {

    public static final String VALIDATE_CODE_KEY = "bravo_XX_checkCode";


    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        String a = "iiiiiii";
        System.out.println(a);
        return a;
    }

//    @CrossOrigin
    @RequestMapping("login")
    public ResultData login(HttpServletRequest req,String loginName, String password, String checkCode) throws Exception {
        if (StringUtils.isBlank(loginName)) {
            return ResultUtils.fail(-2,"用户名必填！");
        }
        if (StringUtils.isBlank(password)) {
            return ResultUtils.fail(-2,"密码必填！");
        }
        if (StringUtils.isBlank(checkCode)) {
            return ResultUtils.fail(-2,"验证码必填！");
        }
        String code = (String)req.getSession().getAttribute(VALIDATE_CODE_KEY);
        if(!checkCode.equalsIgnoreCase(code)){
            return ResultUtils.fail(-3,"验证码错误！");
        }
        ResultData resultData = userService.queryOneByLoginName(loginName);
        if (resultData.getCode() != ResultData.DEFAULT_SUCCESS_CODE){
            return ResultUtils.fail(-1,"登录失败！");
        }
        User user = (User)resultData.getData();
        if (user == null) {
            return ResultUtils.fail(-3,"用户登录名不存在！");
        }
        if (!password.equals(user.getPassword())) {
            return ResultUtils.fail(-4,"用户密码输入错误！");
        }
        req.getSession().setAttribute("user",user);
        return ResultUtils.success(user);
    }


    /**
     * 生成验证码
     * @param req
     * @param res
     */
    @RequestMapping("code")
    public void code(HttpServletRequest req, HttpServletResponse res){
        ValidateCode vCode = new ValidateCode(160,40,4,50);
        //验证码放入session中
        req.getSession().setAttribute(VALIDATE_CODE_KEY,vCode.getCode());
        BufferedImage codeImg = vCode.getBuffImg();
//        BufferedImage buffImg = ImageIO.read(new FileInputStream("home/images/test.png"));
        OutputStream outputStream = null;
        try {
            outputStream = res.getOutputStream();
            // 禁止图像缓存。
            res.setHeader("Pragma","no-cache");
            res.setHeader("Cache-Control","no-cache");
            res.setDateHeader("Expires",0);
            res.setContentType("image/jpeg");
            // 将图像输出到Servlet输出流中。
            ImageIO.write(codeImg, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @RequestMapping("logOut")
    public ResultData logout(HttpServletRequest req){
        req.getSession().removeAttribute("user");
        return ResultUtils.success();
    }
}
