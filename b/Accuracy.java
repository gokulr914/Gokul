package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Accuracy extends Metric {
    private transient long swigCPtr;

    protected Accuracy(long cPtr, boolean cMemoryOwn) {
        super(singa_wrapJNI.Accuracy_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Accuracy obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Accuracy(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public Tensor Forward(Tensor prediction, Tensor target) {
        return new Tensor(singa_wrapJNI.Accuracy_Forward(swigCPtr, this, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target), true);
    }

    public Accuracy() {
        this(singa_wrapJNI.new_Accuracy(), true);
    }

}
