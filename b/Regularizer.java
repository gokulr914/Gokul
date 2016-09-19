package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Regularizer {
    private transient long swigCPtr;
    private transient boolean swigCMemOwn;

    protected Regularizer(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Regularizer obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Regularizer(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public Regularizer() {
        this(singa_wrapJNI.new_Regularizer(), true);
    }

    public void Setup(String conf_str) {
        singa_wrapJNI.Regularizer_Setup(swigCPtr, this, conf_str);
    }

    public void Apply(int step, Tensor grad, Tensor value) {
        singa_wrapJNI.Regularizer_Apply(swigCPtr, this, step, Tensor.getCPtr(grad), grad, Tensor.getCPtr(value), value);
    }

}

