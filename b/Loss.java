package com.examples.singamnist;
/**
 * Created by AdminCOOP on 8/23/2016.
 */

public class Loss {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected Loss(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Loss obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Loss(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public Tensor Forward(int flag, Tensor prediction, Tensor target) {
        return new Tensor(singa_wrapJNI.Loss_Forward(swigCPtr, this, flag, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target), true);
    }

    public float Evaluate(int flag, Tensor prediction, Tensor target) {
        return singa_wrapJNI.Loss_Evaluate(swigCPtr, this, flag, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target);
    }

    public Tensor Backward() {
        return new Tensor(singa_wrapJNI.Loss_Backward(swigCPtr, this), true);
    }
}

