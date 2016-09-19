package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Layer {
    private transient long swigCPtr;
    private transient boolean swigCMemOwn;

    protected Layer(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Layer obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Layer(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public Layer() {
        this(singa_wrapJNI.new_Layer(), true);
    }

    public void Setup(Shape in_sample_shape, String proto_str) {
        singa_wrapJNI.Layer_Setup(swigCPtr, this, Shape.getCPtr(in_sample_shape), in_sample_shape, proto_str);
    }

    public tensorVector param_values() {
        return new tensorVector(singa_wrapJNI.Layer_param_values(swigCPtr, this), true);
    }

    public Shape GetOutputSampleShape() {
        return new Shape(singa_wrapJNI.Layer_GetOutputSampleShape(swigCPtr, this), true);
    }

    public void ToDevice(Device device) {
        singa_wrapJNI.Layer_ToDevice(swigCPtr, this, Device.getCPtr(device), device);
    }

    public void AsType(DataType dtype) {
        singa_wrapJNI.Layer_AsType(swigCPtr, this, dtype.swigValue());
    }

    public Tensor Forward(int flag, Tensor input) {
        return new Tensor(singa_wrapJNI.Layer_Forward__SWIG_0(swigCPtr, this, flag, Tensor.getCPtr(input), input), true);
    }

    public tensorVector Forward(int flag, tensorVector inputs) {
        return new tensorVector(singa_wrapJNI.Layer_Forward__SWIG_1(swigCPtr, this, flag, tensorVector.getCPtr(inputs), inputs), true);
    }

    public ttvecPair Backward(int flag, Tensor grad) {
        return new ttvecPair(singa_wrapJNI.Layer_Backward__SWIG_0(swigCPtr, this, flag, Tensor.getCPtr(grad), grad), true);
    }

    public tvecPair Backward(int flag, SWIGTYPE_p_vectorT_singa__Tensor_t grads) {
        return new tvecPair(singa_wrapJNI.Layer_Backward__SWIG_1(swigCPtr, this, flag, SWIGTYPE_p_vectorT_singa__Tensor_t.getCPtr(grads)), true);
    }

}
