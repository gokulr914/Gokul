package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Optimizer {
    private transient long swigCPtr;
    private transient boolean swigCMemOwn;

    protected Optimizer(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Optimizer obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Optimizer(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public void Setup(String str) {
        singa_wrapJNI.Optimizer_Setup(swigCPtr, this, str);
    }

    public void Apply(int step, float lr, String name, Tensor grad, Tensor value) {
        singa_wrapJNI.Optimizer_Apply(swigCPtr, this, step, lr, name, Tensor.getCPtr(grad), grad, Tensor.getCPtr(value), value);
    }

}

