package com.theta.test;

import java.util.Comparator;

public class FooComparator implements Comparator{


	public int compare(Object foo1, Object foo2){
		/*
		 * parameter are of type Object, so we have to downcast it
		 * to Employee objects
		 */
		String foo1Second = ((Foo)foo1).getSecond();         
		String foo2Second = ((Foo)foo2).getSecond();

		return foo1Second.compareTo(foo2Second);

		/*
		if(foo1Second > foo2Second)
			return 1;
		else if(emp1Age < emp2Age)
			return -1;
		else
			return 0;    
		*/
	}

}
