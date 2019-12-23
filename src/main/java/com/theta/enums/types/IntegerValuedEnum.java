package com.theta.enums.types;
/**
 * Utility class designed to allow dynmaic finding and manipulation of Enum 
 * instances which hold an Integer value.
 */
public interface IntegerValuedEnum {    
    
    /**
     * Current Integer value stored in the enum.
     * @return Integer value.
     */
    public Integer getValue();
    
}