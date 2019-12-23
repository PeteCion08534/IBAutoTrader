package com.theta.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.theta.domain.ProfitLoss;

public class GenericsTest {

	@Test
	public void testGen(){
		System.out.println("Top of testGen");
		store("foobaar");
		
		GenericsTest gt = new GenericsTest();
		store(gt);
	}
		
	//public <T extends Object> T store(T toStore) {
	public <Type> Type store(Type toStore) {

		String className = toStore.getClass().getName();
		System.out.println("Here is className: " + className);
		
		return toStore;
		//return getEntityManager().merge(toStore);
	}
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ProfitLoss.class }));

//	public Set<Class<?>> getTypes() {
//		return dataTypes;
//	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}


}
