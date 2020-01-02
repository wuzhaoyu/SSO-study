package com.sxt.service;

import com.sxt.des.utils.DesResponse;

public interface DesService {

	 DesResponse validateDes(String key, String securityMessage, String name, String password);

	 DesResponse loginDes( String securityMessage);

	DesResponse aspectDes( String securityMessage);

}
