package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class strVector {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected strVector(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(strVector obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_strVector(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public strVector() {
        this(singa_wrapJNI.new_strVector__SWIG_0(), true);
    }

    public strVector(long n) {
        this(singa_wrapJNI.new_strVector__SWIG_1(n), true);
    }

    public long size() {
        return singa_wrapJNI.strVector_size(swigCPtr, this);
    }

    public long capacity() {
        return singa_wrapJNI.strVector_capacity(swigCPtr, this);
    }

    public void reserve(long n) {
        singa_wrapJNI.strVector_reserve(swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return singa_wrapJNI.strVector_isEmpty(swigCPtr, this);
    }

    public void clear() {
        singa_wrapJNI.strVector_clear(swigCPtr, this);
    }

    public void add(String x) {
        singa_wrapJNI.strVector_add(swigCPtr, this, x);
    }

    public String get(int i) {
        return singa_wrapJNI.strVector_get(swigCPtr, this, i);
    }

    public void set(int i, String val) {
        singa_wrapJNI.strVector_set(swigCPtr, this, i, val);
    }

}
