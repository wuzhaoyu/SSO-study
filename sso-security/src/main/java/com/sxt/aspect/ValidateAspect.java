package com.sxt.aspect;

import com.sxt.des.utils.DesCrypt;
import com.sxt.des.utils.DesResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/1/2
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Aspect
@Component
public class ValidateAspect {

    /**
     *  //设置切点
     */
    @Pointcut("@annotation(com.sxt.annotation.Validate)")
    public void ValidatePointCut(){
    }

    @After(value = "ValidatePointCut()" )
    public DesResponse around(ProceedingJoinPoint point) throws Throwable{
        System.out.println("步骤三-------------------------");
        DesResponse result = (DesResponse) point.proceed();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String securityMessage = request.getParameter("message");
        System.out.println("接收到的密文： " + securityMessage);
        DesResponse resp = new DesResponse();
        String respKey = DesCrypt.getKEY();
        String message = "";
        //密匙源
       // String key = "c61faa5f-66a4-4374-89f1-1090a86de13b";
        // 解密解析请求数据
        try{
            // 解密得到请求源参数
            String decodeMessage = DesCrypt.decode(null, securityMessage);
            System.out.println("解密后的数据：" + decodeMessage);
            // 校验请求参数
            // 请求参数验证成功
            message = "登录成功！";
        }catch(Exception e){
            e.printStackTrace();
            // 有解密异常发生。
            message = "请求数据解析错误！";
        }

        System.out.println("响应中的key：" + respKey);
        System.out.println("响应消息明文：" + message);
        // 加密处理响应数据
        try{
            message = DesCrypt.encode(respKey, message);
        }catch(Exception e){
            e.printStackTrace();
            // 加密异常发生。
        }
        System.out.println("响应消息密文：" + message);
        resp.setKey(respKey);
        resp.setSecurityMessage(message);
        resp.setTest(result.getTest());
        return resp;
    }
}
