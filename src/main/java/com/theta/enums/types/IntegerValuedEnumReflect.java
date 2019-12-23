package com.theta.enums.types;
/**
 * Utility class designed to inspect StringValuedEnums.
 */
public class IntegerValuedEnumReflect {
    
    /**
     * Don't let anyone instantiate this class.
     * @throws UnsupportedOperationException Always.
     */
    private IntegerValuedEnumReflect() {
        throw new UnsupportedOperationException("This class must not be instanciated.");
    }
    
    /**
     * All Enum constants (instances) declared in the specified class. 
     * @param enumClass Class to reflect
     * @return Array of all declared EnumConstants (instances).
     */
    private static <T extends Enum<T>> T[] 
            getValues(Class<T> enumClass){
        return enumClass.getEnumConstants();
    }
    
    /**
     * All possible integer values of the integer valued enum.
     * @param enumClass Class to reflect.
     * @return Available integer values.
     */
    public static <T extends Enum<T> & IntegerValuedEnum> Integer[] 
            getIntegerValues(Class<T> enumClass){ 
        T[] values = getValues(enumClass);
        Integer[] result = new Integer[values.length];
        for (int i=0; i<values.length; i++){
            result[i] = values[i].getValue();
        }
        return result;
    }
    
    /**
     * Name of the enum instance which holds the specified integer value.
     * If value has duplicate enum instances than returns the first occurency.
     * @param enumClass Class to inspect.
     * @param value Integer.
     * @return name of the enum instance.
     */
    public static <T extends Enum<T> & IntegerValuedEnum> String 
            getNameFromValue(Class<T> enumClass, Integer value){
        T[] values = getValues(enumClass);
        for (T v : values){
            if (v.getValue().equals(value)){
                return v.name();
            }
        }
        return "";
    }
    
}