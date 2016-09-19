package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Device {
    private transient long swigCPtr;
    private transient boolean swigCMemOwn;

    protected Device(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Device obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Device(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public void SetRandSeed(long seed) {
        singa_wrapJNI.Device_SetRandSeed(swigCPtr, this, seed);
    }

    public Device host() {
        long cPtr = singa_wrapJNI.Device_host(swigCPtr, this);
        return (cPtr == 0) ? null : new Device(cPtr, true);
    }

    public int id() {
        return singa_wrapJNI.Device_id(swigCPtr, this);
    }

}
