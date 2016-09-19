package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */

public class Shape {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected Shape(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Shape obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Shape(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public Shape() {
        this(singa_wrapJNI.new_Shape__SWIG_0(), true);
    }

    public Shape(long n) {
        this(singa_wrapJNI.new_Shape__SWIG_1(n), true);
    }

    public long size() {
        return singa_wrapJNI.Shape_size(swigCPtr, this);
    }

    public long capacity() {
        return singa_wrapJNI.Shape_capacity(swigCPtr, this);
    }

    public void reserve(long n) {
        singa_wrapJNI.Shape_reserve(swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return singa_wrapJNI.Shape_isEmpty(swigCPtr, this);
    }

    public void clear() {
        singa_wrapJNI.Shape_clear(swigCPtr, this);
    }

    public void add(long x) {
        singa_wrapJNI.Shape_add(swigCPtr, this, x);
    }

    public long get(int i) {
        return singa_wrapJNI.Shape_get(swigCPtr, this, i);
    }

    public void set(int i, long val) {
        singa_wrapJNI.Shape_set(swigCPtr, this, i, val);
    }

}

