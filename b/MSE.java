package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class MSE extends Loss {
    private transient long swigCPtr;

    protected MSE(long cPtr, boolean cMemoryOwn) {
        super(singa_wrapJNI.MSE_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    protected static long getCPtr(MSE obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_MSE(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public Tensor Forward(int flag, Tensor prediction, Tensor target) {
        return new Tensor(singa_wrapJNI.MSE_Forward(swigCPtr, this, flag, Tensor.getCPtr(prediction), prediction, Tensor.getCPtr(target), target), true);
    }

    public Tensor Backward() {
        return new Tensor(singa_wrapJNI.MSE_Backward(swigCPtr, this), true);
    }

    public MSE() {
        this(singa_wrapJNI.new_MSE(), true);
    }

}
