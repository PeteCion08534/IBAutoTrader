package com.theta.process;

import com.theta.client.Response;
import com.theta.client.ResponseInterface;

public class ClassFoo implements InterfaceFoo {

	public static ResponseInterface resp = new Response();

	public void setErrorCode(int i) {
		// TODO Auto-generated method stub
		ClassFoo.resp.setErrorCode(i);
	}
	
}
