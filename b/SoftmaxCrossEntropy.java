package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class SoftmaxCrossEntropy extends Loss {
    private transient long swigCPtr;

    protected SoftmaxCrossEntropy(long cPtr, boolean cMemoryOwn) {
        super(singa_wrapJNI.SoftmaxCrossEntropy_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    protected static long getCPtr(SoftmaxCrossEntropy obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_SoftmaxCrossEntropy(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public Tensor Forward(int flag, Tensor prediction, Tensor target) {
        return new Tensor(singa_wrapJNI.SoftmaxCrossEntropy_Forward(swigCPtr, this, flag, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target), true);
    }

    public Tensor Backward() {
        return new Tensor(singa_wrapJNI.SoftmaxCrossEntropy_Backward(swigCPtr, this), true);
    }

    public SoftmaxCrossEntropy() {
        this(singa_wrapJNI.new_SoftmaxCrossEntropy(), true);
    }

}
