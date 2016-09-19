package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/27/2016.
 */
public class FeedForwardNet {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected FeedForwardNet(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(FeedForwardNet obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_FeedForwardNet(swigCPtr);
            }
            swigCPtr = 0;
    }
}

    public FeedForwardNet() {
        this(singa_wrapJNI.new_FeedForwardNet(), true);
    }

    public SWIGTYPE_p_std__shared_ptrT_Layer_t Add(SWIGTYPE_p_std__shared_ptrT_Layer_t layer) {
        return new SWIGTYPE_p_std__shared_ptrT_Layer_t(singa_wrapJNI.FeedForwardNet_Add__SWIG_0(swigCPtr, this, SWIGTYPE_p_std__shared_ptrT_Layer_t.getCPtr(layer)), true);
    }

    public SWIGTYPE_p_std__shared_ptT_Layer_t Add(SWIGTYPE_p_LayerConf conf, SWIGTYPE_p_Shape sample_shape) {
        return new SWIGTYPE_p_std__shared_ptT_Layer_t(singa_wrapJNI.FeedForwardNet_Add__SWIG_1(swigCPtr, this, SWIGTYPE_p_LayerConf.getCPtr(conf), SWIGTYPE_p_Shape.getCPtr(sample_shape)), true);
    }

    public SWIGTYPE_p_vectorT_string_t GetParamNames() {
        return new SWIGTYPE_p_vectorT_string_t(singa_wrapJNI.FeedForwardNet_GetParamNames(swigCPtr, this), true);
    }

    public SWIGTYPE_p_vectorT_Tensor_t GetParamValues() {
        return new SWIGTYPE_p_vectorT_Tensor_t(singa_wrapJNI.FeedForwardNet_GetParamValues(swigCPtr, this), true);
    }

    public SWIGTYPE_p_vectorT_ParamSpec_t GetParamSpecs() {
        return new SWIGTYPE_p_vectorT_ParamSpec_t(singa_wrapJNI.FeedForwardNet_GetParamSpecs(swigCPtr, this), true);
    }

    public void Compile(boolean shuffle, SWIGTYPE_p_Optimizer opt, SWIGTYPE_p_Loss loss, SWIGTYPE_p_Metric metric) {
        singa_wrapJNI.FeedForwardNet_Compile(swigCPtr, this, shuffle, SWIGTYPE_p_Optimizer.getCPtr(opt), SWIGTYPE_p_Loss.getCPtr(loss), SWIGTYPE_p_Metric.getCPtr(metric));
    }

    public void ToDevice(SWIGTYPE_p_std__shared_ptrT_Device_t device) {
        singa_wrapJNI.FeedForwardNet_ToDevice(swigCPtr, this, SWIGTYPE_p_std__shared_ptrT_Device_t.getCPtr(device));
    }

    public FeedForwardNet Clone(SWIGTYPE_p_std__shared_ptrT_Device_t device) {
        return new FeedForwardNet(singa_wrapJNI.FeedForwardNet_Clone(swigCPtr, this, SWIGTYPE_p_std__shared_ptrT_Device_t.getCPtr(device)), true);
    }

    public void AsType(SWIGTYPE_p_DataType dtype) {
        singa_wrapJNI.FeedForwardNet_AsType(swigCPtr, this, SWIGTYPE_p_DataType.getCPtr(dtype));
    }

    public SWIGTYPE_p_Tensor Forward(int flag, SWIGTYPE_p_Tensor data) {
        return new SWIGTYPE_p_Tensor(singa_wrapJNI.FeedForwardNet_Forward(swigCPtr, this, flag, SWIGTYPE_p_Tensor.getCPtr(data)), true);
    }

    public SWIGTYPE_p_vectorT_Tensor_t Backward(int flag, SWIGTYPE_p_Tensor grad) {
        return new SWIGTYPE_p_vectorT_Tensor_t(singa_wrapJNI.FeedForwardNet_Backward(swigCPtr, this, flag, SWIGTYPE_p_Tensor.getCPtr(grad)), true);
    }

    public SWIGTYPE_p_std__pairT_Tensor_Tensor_t Evaluate(SWIGTYPE_p_Tensor x, SWIGTYPE_p_Tensor y, long batchsize) {
        return new SWIGTYPE_p_std__pairT_Tensor_Tensor_t(singa_wrapJNI.FeedForwardNet_Evaluate(swigCPtr, this, SWIGTYPE_p_Tensor.getCPtr(x), SWIGTYPE_p_Tensor.getCPtr(y), batchsize), true);
    }

    public SWIGTYPE_p_std__pairT_Tensor_Tensor_t EvaluateOnBatch(SWIGTYPE_p_Tensor x, SWIGTYPE_p_Tensor y) {
        return new SWIGTYPE_p_std__pairT_Tensor_Tensor_t(singa_wrapJNI.FeedForwardNet_EvaluateOnBatch(swigCPtr, this, SWIGTYPE_p_Tensor.getCPtr(x), SWIGTYPE_p_Tensor.getCPtr(y)), true);
    }

    public SWIGTYPE_p_Tensor Predict(SWIGTYPE_p_Tensor x, long batchsize) {
        return new SWIGTYPE_p_Tensor(singa_wrapJNI.FeedForwardNet_Predict(swigCPtr, this, SWIGTYPE_p_Tensor.getCPtr(x), batchsize), true);
    }

}
