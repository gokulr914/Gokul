package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class paramVector {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected paramVector(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(paramVector obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_paramVector(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public paramVector() {
        this(singa_wrapJNI.new_paramVector__SWIG_0(), true);
    }

    public paramVector(long n) {
        this(singa_wrapJNI.new_paramVector__SWIG_1(n), true);
    }

    public long size() {
        return singa_wrapJNI.paramVector_size(swigCPtr, this);
    }

    public long capacity() {
        return singa_wrapJNI.paramVector_capacity(swigCPtr, this);
    }

    public void reserve(long n) {
        singa_wrapJNI.paramVector_reserve(swigCPtr, this, n);
    }

    public boolean isEmpty() {
        return singa_wrapJNI.paramVector_isEmpty(swigCPtr, this);
    }

    public void clear() {
        singa_wrapJNI.paramVector_clear(swigCPtr, this);
    }

    public void add(SWIGTYPE_p_singa__ParamSpec x) {
        singa_wrapJNI.paramVector_add(swigCPtr, this, SWIGTYPE_p_singa__ParamSpec.getCPtr(x));
    }

    public SWIGTYPE_p_singa__ParamSpec get(int i) {
        return new SWIGTYPE_p_singa__ParamSpec(singa_wrapJNI.paramVector_get(swigCPtr, this, i), false);
    }

    public void set(int i, SWIGTYPE_p_singa__ParamSpec val) {
        singa_wrapJNI.paramVector_set(swigCPtr, this, i, SWIGTYPE_p_singa__ParamSpec.getCPtr(val));
    }

}
