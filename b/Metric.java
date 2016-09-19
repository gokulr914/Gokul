package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Metric {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected Metric(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Metric obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Metric(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public Tensor Forward(Tensor prediction, Tensor target) {
        return new Tensor(singa_wrapJNI.Metric_Forward(swigCPtr, this, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target), true);
    }

    public float Evaluate(Tensor prediction, Tensor target) {
        return singa_wrapJNI.Metric_Evaluate(swigCPtr, this, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target);
    }

}

