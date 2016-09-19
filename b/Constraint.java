package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Constraint {
    private transient long swigCPtr;
    private transient boolean swigCMemOwn;

    protected Constraint(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Constraint obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Constraint(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public Constraint() {
        this(singa_wrapJNI.new_Constraint(), true);
    }

    public void Setup(String conf_str) {
        singa_wrapJNI.Constraint_Setup(swigCPtr, this, conf_str);
    }

    public void Apply(int step, Tensor grad, Tensor value) {
        singa_wrapJNI.Constraint_Apply(swigCPtr, this, step, Tensor.getCPtr(grad), grad, Tensor.getCPtr(value), value);
    }

}
