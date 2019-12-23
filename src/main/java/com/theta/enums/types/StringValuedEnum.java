package com.theta.enums.types;
/**
 * Utility class designed to allow dynamic finding and manipulation of Enum 
 * instances which hold a string value.
 */
public interface StringValuedEnum {    
    
    /**
     * Current string value stored in the enum.
     * @return string value.
     */
    public String getValue();
    
}