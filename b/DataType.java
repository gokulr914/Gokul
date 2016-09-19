package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public final class DataType {
    public final static DataType kFloat32 = new DataType("kFloat32");
    public final static DataType kFloat16 = new DataType("kFloat16");
    public final static DataType kInt = new DataType("kInt");
    public final static DataType kChar = new DataType("kChar");
    public final static DataType kDouble = new DataType("kDouble");

    public final int swigValue() {
        return swigValue;
    }

    public String toString() {
        return swigName;
    }

    public static DataType swigToEnum(int swigValue) {
        if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
            return swigValues[swigValue];
        for (int i = 0; i < swigValues.length; i++)
            if (swigValues[i].swigValue == swigValue)
                return swigValues[i];
        throw new IllegalArgumentException("No enum " + DataType.class + " with value " + swigValue);
    }

    private DataType(String swigName) {
        this.swigName = swigName;
        this.swigValue = swigNext++;
    }

    private DataType(String swigName, int swigValue) {
        this.swigName = swigName;
        this.swigValue = swigValue;
        swigNext = swigValue+1;
    }

    private DataType(String swigName, DataType swigEnum) {
        this.swigName = swigName;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue+1;
    }

    private static DataType[] swigValues = { kFloat32, kFloat16, kInt, kChar, kDouble };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
}

