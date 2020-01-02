package com.sxt.des.controller;

import com.sxt.annotation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.des.utils.DesResponse;
import com.sxt.service.DesService;

@Controller
public class DesController {
	
	@Autowired
	private DesService desService;
	
	/**
	 * 加密逻辑为： name+password 使用key作为密匙源，加密得到securityMessage
	 * 对提交的数据源进行加密，解密后对比加密后数据源与表单提交的数据进行对比，
	 * 判断表单数据是否被修改
	 * @param key 密匙源
	 * @param securityMessage 加密后的签名
	 * @param name 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping("/validateDes")
	@ResponseBody
	public DesResponse testDes(@RequestParam("key") String key, @RequestParam("message") String securityMessage
			, @RequestParam("name") String name, @RequestParam("password") String password){
		
		DesResponse resp = this.desService.validateDes(key, securityMessage, name, password);
		
		return resp;
	}
	/**
	 * 加密逻辑为： name+password 使用key作为密匙源，加密得到securityMessage
	 * 登录时对用户名及密码进行加密，使用约定的key进行解密；
	 * @param securityMessage 加密后的签名
	 * @return
	 */
	@RequestMapping("/loginDes")
	@ResponseBody
	public DesResponse loginDes(@RequestParam("message") String securityMessage){
		DesResponse resp = this.desService.loginDes( securityMessage);
		return resp;
	}

	/**
	 * 加密逻辑为： name+password 使用key作为密匙源，加密得到securityMessage
	 * 登录时对用户名及密码进行加密，使用约定的key进行解密；
	 * @param securityMessage 加密后的签名
	 * @return
	 */
	@RequestMapping("/aspectDes")
	@ResponseBody
	@Validate
	public DesResponse aspectDes(@RequestParam("message") String securityMessage){
		this.desService.aspectDes(securityMessage);
		DesResponse response = new DesResponse();
		response.setTest("测试");
		System.out.println("步骤一-------------------------");
		return response;
	}
}
