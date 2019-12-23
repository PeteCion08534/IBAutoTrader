package com.theta.process;

import junit.framework.Assert;

import org.junit.Test;

public class InterfaceTest {

	
	@Test
	public void testInterface() {
		
		System.out.println("Hello world");
		ClassFoo classFoo = new ClassFoo();

		InterfaceFoo.resp.setErrorCode(1);
		
		ClassFoo.resp.setErrorCode(1);
		InterfaceFoo.resp.setErrorCode(2);
		
		int errorFoo1 = ClassFoo.resp.getErrorCode();
		int errorFoo2 = InterfaceFoo.resp.getErrorCode();
		
		Assert.assertEquals(1, errorFoo1);
		Assert.assertEquals(2, errorFoo2);
		System.out.println("here is errorFoo1, errorFoo2: " + errorFoo1 + ", " + errorFoo2);
		classFoo.setErrorCode(3);
		errorFoo1 = ClassFoo.resp.getErrorCode();
		Assert.assertEquals(3, errorFoo1);
		System.out.println("here is errorFoo1, errorFoo2: " + errorFoo1 + ", " + errorFoo2);
		
	}
	
}
