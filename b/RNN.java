package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class RNN extends Layer {
    private transient long swigCPtr;
    private boolean swigCMemOwnDerived;

    protected RNN(long cPtr, boolean cMemoryOwn) {
        super(singa_wrapJNI.RNN_SWIGSmartPtrUpcast(cPtr), true);
        swigCMemOwnDerived = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(RNN obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwnDerived) {
                swigCMemOwnDerived = false;
                singa_wrapJNI.delete_RNN(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public RNN() {
        this(singa_wrapJNI.new_RNN(), true);
    }

}
