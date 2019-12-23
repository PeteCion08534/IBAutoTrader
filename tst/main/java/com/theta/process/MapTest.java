package com.theta.process;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map<Integer, Boolean> testMapB = null;
	Map<Integer, Integer> testMap = null;

	@Before
	public void setUp() {
		testMapB = new HashMap<Integer, Boolean>();
		testMap = new HashMap<Integer, Integer>();
	}
	
	
	@Test
	public void testMapB(){
		
		Boolean ret = testMapB.put(1, true);
		System.out.println("Here is ret: " + ret);
		
		ret = testMapB.put(1, false);
		System.out.println("Here is ret: " + ret);

		ret = testMapB.remove(1);
		System.out.println("Here is ret - remove something that is there: " + ret);
		
		ret = testMapB.remove(17);
		System.out.println("Here is ret - remove something that is NOT there: " + ret);
		
		ret = testMapB.put(2, false);
		
	}

	
	@Test
	public void testMap(){
		Integer ret = testMap.put(1, 2);
		testMap.put(2, 4);
		
		System.out.println("here is ret on putting a key (1,2) that IS there:  " + ret);
		System.out.println("Here is testMap: " + testMap);
		
		ret = testMap.put(2,5);
		System.out.println("here is ret on putting a key (2,5) that IS there:  " + ret);
		System.out.println("Here is testMap: " + testMap);
		
		ret = testMap.remove(2);
		System.out.println("here is ret on a key that IS there:  " + ret);
		System.out.println("Here is testMap: " + testMap);
		System.out.println("here is testMap.get(1): " + testMap.get(1));

		System.out.println("here is testMap.get(100): " + testMap.get(100));

		ret = testMap.remove(17);
		System.out.println("here is ret on a key that isn't there:  " + ret);
		
		System.out.println("HI");
	}

	
	
	
}
