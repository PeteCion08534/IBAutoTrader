package com.theta.process;

import com.theta.client.Response;
import com.theta.client.ResponseInterface;

public interface InterfaceFoo {

	public static ResponseInterface resp = new Response();

	void setErrorCode(int i);
	
}
