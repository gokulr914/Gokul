package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */

public class singa_wrapJNI {
    public final static native int USE_CUDA_get();
    public final static native int USE_CUDNN_get();
    public final static native int CUDNN_VERSION_SWIG_get();
    public final static native long new_Shape__SWIG_0();
    public final static native long new_Shape__SWIG_1(long jarg1);
    public final static native long Shape_size(long jarg1, Shape jarg1_);
    public final static native long Shape_capacity(long jarg1, Shape jarg1_);
    public final static native void Shape_reserve(long jarg1, Shape jarg1_, long jarg2);
    public final static native boolean Shape_isEmpty(long jarg1, Shape jarg1_);
    public final static native void Shape_clear(long jarg1, Shape jarg1_);
    public final static native void Shape_add(long jarg1, Shape jarg1_, long jarg2);
    public final static native long Shape_get(long jarg1, Shape jarg1_, int jarg2);
    public final static native void Shape_set(long jarg1, Shape jarg1_, int jarg2, long jarg3);
    public final static native void delete_Shape(long jarg1);
    public final static native long Product__SWIG_0(long jarg1, Shape jarg1_, int jarg2, long jarg3);
    public final static native long Product__SWIG_1(long jarg1, Shape jarg1_, int jarg2);
    public final static native long Product__SWIG_2(long jarg1, Shape jarg1_);
    public final static native long SizeOf(int jarg1);
    public final static native long new_Tensor__SWIG_0();
    public final static native long new_Tensor__SWIG_1(long jarg1, Shape jarg1_, int jarg2);
    public final static native long new_Tensor__SWIG_2(long jarg1, Shape jarg1_);
    public final static native long new_Tensor__SWIG_3(long jarg1, Shape jarg1_, long jarg2, Device jarg2_, int jarg3);
    public final static native long new_Tensor__SWIG_4(long jarg1, Shape jarg1_, long jarg2, Device jarg2_);
    public final static native long new_Tensor__SWIG_5(long jarg1, Tensor jarg1_);
    public final static native long Tensor_device(long jarg1, Tensor jarg1_);
    public final static native void Tensor_floatGetValue(long jarg1, Tensor jarg1_, long jarg2, long jarg3);
    public final static native void Tensor_intGetValue(long jarg1, Tensor jarg1_, long jarg2, long jarg3);
    public final static native int Tensor_data_type(long jarg1, Tensor jarg1_);
    public final static native long Tensor_shape__SWIG_0(long jarg1, Tensor jarg1_);
    public final static native long Tensor_shape__SWIG_1(long jarg1, Tensor jarg1_, long jarg2);
    public final static native long Tensor_nDim(long jarg1, Tensor jarg1_);
    public final static native boolean Tensor_transpose(long jarg1, Tensor jarg1_);
    public final static native long Tensor_Size(long jarg1, Tensor jarg1_);
    public final static native long Tensor_MemSize(long jarg1, Tensor jarg1_);
    public final static native void Tensor_Reshape(long jarg1, Tensor jarg1_, long jarg2, Shape jarg2_);
    public final static native void Tensor_ResetLike(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void Tensor_AsType(long jarg1, Tensor jarg1_, int jarg2);
    public final static native void Tensor_ToDevice(long jarg1, Tensor jarg1_, long jarg2, Device jarg2_);
    public final static native void Tensor_ToHost(long jarg1, Tensor jarg1_);
    public final static native float Tensor_L2(long jarg1, Tensor jarg1_);
    public final static native float Tensor_L1(long jarg1, Tensor jarg1_);
    public final static native void Tensor_floatSetValue(long jarg1, Tensor jarg1_, float jarg2);
    public final static native void Tensor_floatCopyDataFromHostPtr__SWIG_0(long jarg1, Tensor jarg1_, long jarg2, long jarg3, long jarg4);
    public final static native void Tensor_floatCopyDataFromHostPtr__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, long jarg3);
    public final static native void Tensor_intCopyDataFromHostPtr__SWIG_0(long jarg1, Tensor jarg1_, long jarg2, long jarg3, long jarg4);
    public final static native void Tensor_intCopyDataFromHostPtr__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, long jarg3);
    public final static native void Tensor_CopyData(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long Tensor_Clone(long jarg1, Tensor jarg1_);
    public final static native long Tensor_T(long jarg1, Tensor jarg1_);
    public final static native long Tensor_iAdd_f(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long Tensor_iSub_f(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long Tensor_iMul_f(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long Tensor_iDiv_f(long jarg1, Tensor jarg1_, float jarg2);
    public final static native void delete_Tensor(long jarg1);
    public final static native void CopyDataToFrom__SWIG_0(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, long jarg4, long jarg5);
    public final static native void CopyDataToFrom__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, long jarg4);
    public final static native void CopyDataToFrom__SWIG_2(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3);
    public final static native long Reshape(long jarg1, Tensor jarg1_, long jarg2, Shape jarg2_);
    public final static native long Abs(long jarg1, Tensor jarg1_);
    public final static native long Exp(long jarg1, Tensor jarg1_);
    public final static native long Log(long jarg1, Tensor jarg1_);
    public final static native long ReLU(long jarg1, Tensor jarg1_);
    public final static native long Sigmoid(long jarg1, Tensor jarg1_);
    public final static native long Sign(long jarg1, Tensor jarg1_);
    public final static native long Sqrt(long jarg1, Tensor jarg1_);
    public final static native long Square(long jarg1, Tensor jarg1_);
    public final static native long Tanh(long jarg1, Tensor jarg1_);
    public final static native long Sum(long jarg1, Tensor jarg1_, int jarg2);
    public final static native float floatSum(long jarg1, Tensor jarg1_);
    public final static native long Average(long jarg1, Tensor jarg1_, int jarg2);
    public final static native long SoftMax__SWIG_0(long jarg1, Tensor jarg1_);
    public final static native long Pow__SWIG_0(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void Pow__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native long Pow_f(long jarg1, Tensor jarg1_, float jarg2);
    public final static native void Pow_f_out(long jarg1, Tensor jarg1_, float jarg2, long jarg3, Tensor jarg3_);
    public final static native long LT_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long LE_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long GT_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long GE_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long LT_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long LE_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long GT_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long GE_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long Add_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long Sub_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long EltwiseMul_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long Div_TT(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long Add_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long Sub_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long EltwiseMul_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native long Div_Tf(long jarg1, Tensor jarg1_, float jarg2);
    public final static native void Add(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native void Sub(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native void EltwiseMult(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native void Div(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native void Add_Tf_out(long jarg1, Tensor jarg1_, float jarg2, long jarg3, Tensor jarg3_);
    public final static native void Sub_Tf_out(long jarg1, Tensor jarg1_, float jarg2, long jarg3, Tensor jarg3_);
    public final static native void EltwiseMult_Tf_out(long jarg1, Tensor jarg1_, float jarg2, long jarg3, Tensor jarg3_);
    public final static native void Div_Tf_out(long jarg1, Tensor jarg1_, float jarg2, long jarg3, Tensor jarg3_);
    public final static native void floatBernoulli(float jarg1, long jarg2, Tensor jarg2_);
    public final static native void floatGaussian(float jarg1, float jarg2, long jarg3, Tensor jarg3_);
    public final static native void floatUniform(float jarg1, float jarg2, long jarg3, Tensor jarg3_);
    public final static native void floatAxpy(float jarg1, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native long Mult__SWIG_0(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void Mult__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native void floatMult(float jarg1, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_, float jarg4, long jarg5, Tensor jarg5_);
    public final static native void AddColumn(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void floatAddColumn(float jarg1, float jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native void AddRow(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void floatAddRow(float jarg1, float jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native void DivColumn(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void DivRow(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void MultColumn(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void MultRow(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void SubColumn(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void SubRow(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void SumColumns(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void SumRows(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native void SoftMax__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, Tensor jarg2_);
    public final static native long new_sizePair__SWIG_0();
    public final static native long new_sizePair__SWIG_1(long jarg1, long jarg2);
    public final static native long new_sizePair__SWIG_2(long jarg1, sizePair jarg1_);
    public final static native void sizePair_first_set(long jarg1, sizePair jarg1_, long jarg2);
    public final static native long sizePair_first_get(long jarg1, sizePair jarg1_);
    public final static native void sizePair_second_set(long jarg1, sizePair jarg1_, long jarg2);
    public final static native long sizePair_second_get(long jarg1, sizePair jarg1_);
    public final static native void delete_sizePair(long jarg1);
    public final static native long new_vectorPair__SWIG_0();
    public final static native long new_vectorPair__SWIG_1(long jarg1);
    public final static native long vectorPair_size(long jarg1, vectorPair jarg1_);
    public final static native long vectorPair_capacity(long jarg1, vectorPair jarg1_);
    public final static native void vectorPair_reserve(long jarg1, vectorPair jarg1_, long jarg2);
    public final static native boolean vectorPair_isEmpty(long jarg1, vectorPair jarg1_);
    public final static native void vectorPair_clear(long jarg1, vectorPair jarg1_);
    public final static native void vectorPair_add(long jarg1, vectorPair jarg1_, long jarg2, sizePair jarg2_);
    public final static native long vectorPair_get(long jarg1, vectorPair jarg1_, int jarg2);
    public final static native void vectorPair_set(long jarg1, vectorPair jarg1_, int jarg2, long jarg3, sizePair jarg3_);
    public final static native void delete_vectorPair(long jarg1);
    public final static native long new_vectorSharedPtr__SWIG_0();
    public final static native long new_vectorSharedPtr__SWIG_1(long jarg1);
    //public final static native long vectorSharedPtr_size(long jarg1, vectorSharedPtr jarg1_);
    //public final static native long vectorSharedPtr_capacity(long jarg1, vectorSharedPtr jarg1_);
    //public final static native void vectorSharedPtr_reserve(long jarg1, vectorSharedPtr jarg1_, long jarg2);
    //public final static native boolean vectorSharedPtr_isEmpty(long jarg1, vectorSharedPtr jarg1_);
    //public final static native void vectorSharedPtr_clear(long jarg1, vectorSharedPtr jarg1_);
    //public final static native void vectorSharedPtr_add(long jarg1, vectorSharedPtr jarg1_, long jarg2, Device jarg2_);
    //public final static native long vectorSharedPtr_get(long jarg1, vectorSharedPtr jarg1_, int jarg2);
    //public final static native void vectorSharedPtr_set(long jarg1, vectorSharedPtr jarg1_, int jarg2, long jarg3, Device jarg3_);
    public final static native void delete_vectorSharedPtr(long jarg1);
    public final static native void Device_SetRandSeed(long jarg1, Device jarg1_, long jarg2);
    public final static native long Device_host(long jarg1, Device jarg1_);
    public final static native int Device_id(long jarg1, Device jarg1_);
    public final static native void delete_Device(long jarg1);
    public final static native int Platform_GetNumGPUs();
    public final static native long Platform_GetGPUIDs();
    public final static native long Platform_GetGPUMemSize__SWIG_0(int jarg1);
    public final static native long Platform_GetGPUMemSize__SWIG_1();
    public final static native String Platform_DeviceQuery__SWIG_0(int jarg1, boolean jarg2);
    public final static native String Platform_DeviceQuery__SWIG_1(int jarg1);
    public final static native long Platform_CreateCudaGPUs__SWIG_0(long jarg1, long jarg2);
    public final static native long Platform_CreateCudaGPUs__SWIG_1(long jarg1);
    public final static native long Platform_CreateCudaGPUsOn__SWIG_0(long jarg1, long jarg2);
    public final static native long Platform_CreateCudaGPUsOn__SWIG_1(long jarg1);
    public final static native long Platform_GetDefaultDevice();
    public final static native long new_Platform();
    public final static native void delete_Platform(long jarg1);
    public final static native long new_strVector__SWIG_0();
    public final static native long new_strVector__SWIG_1(long jarg1);
    public final static native long strVector_size(long jarg1, strVector jarg1_);
    public final static native long strVector_capacity(long jarg1, strVector jarg1_);
    public final static native void strVector_reserve(long jarg1, strVector jarg1_, long jarg2);
    public final static native boolean strVector_isEmpty(long jarg1, strVector jarg1_);
    public final static native void strVector_clear(long jarg1, strVector jarg1_);
    public final static native void strVector_add(long jarg1, strVector jarg1_, String jarg2);
    public final static native String strVector_get(long jarg1, strVector jarg1_, int jarg2);
    public final static native void strVector_set(long jarg1, strVector jarg1_, int jarg2, String jarg3);
    public final static native void delete_strVector(long jarg1);
    public final static native long new_paramVector__SWIG_0();
    public final static native long new_paramVector__SWIG_1(long jarg1);
    public final static native long paramVector_size(long jarg1, paramVector jarg1_);
    public final static native long paramVector_capacity(long jarg1, paramVector jarg1_);
    public final static native void paramVector_reserve(long jarg1, paramVector jarg1_, long jarg2);
    public final static native boolean paramVector_isEmpty(long jarg1, paramVector jarg1_);
    public final static native void paramVector_clear(long jarg1, paramVector jarg1_);
    public final static native void paramVector_add(long jarg1, paramVector jarg1_, long jarg2);
    public final static native long paramVector_get(long jarg1, paramVector jarg1_, int jarg2);
    public final static native void paramVector_set(long jarg1, paramVector jarg1_, int jarg2, long jarg3);
    public final static native void delete_paramVector(long jarg1);
    public final static native long new_tensorVector__SWIG_0();
    public final static native long new_tensorVector__SWIG_1(long jarg1);
    public final static native long tensorVector_size(long jarg1, tensorVector jarg1_);
    public final static native long tensorVector_capacity(long jarg1, tensorVector jarg1_);
    public final static native void tensorVector_reserve(long jarg1, tensorVector jarg1_, long jarg2);
    public final static native boolean tensorVector_isEmpty(long jarg1, tensorVector jarg1_);
    public final static native void tensorVector_clear(long jarg1, tensorVector jarg1_);
    public final static native void tensorVector_add(long jarg1, tensorVector jarg1_, long jarg2, Tensor jarg2_);
    public final static native long tensorVector_get(long jarg1, tensorVector jarg1_, int jarg2);
    public final static native void tensorVector_set(long jarg1, tensorVector jarg1_, int jarg2, long jarg3, Tensor jarg3_);
    public final static native void delete_tensorVector(long jarg1);
    public final static native long new_ttvecPair__SWIG_0();
    public final static native long new_ttvecPair__SWIG_1(long jarg1, Tensor jarg1_, long jarg2, tensorVector jarg2_);
    public final static native long new_ttvecPair__SWIG_2(long jarg1, ttvecPair jarg1_);
    public final static native void ttvecPair_first_set(long jarg1, ttvecPair jarg1_, long jarg2, Tensor jarg2_);
    public final static native long ttvecPair_first_get(long jarg1, ttvecPair jarg1_);
    public final static native void ttvecPair_second_set(long jarg1, ttvecPair jarg1_, long jarg2, tensorVector jarg2_);
    public final static native long ttvecPair_second_get(long jarg1, ttvecPair jarg1_);
    public final static native void delete_ttvecPair(long jarg1);
    public final static native long new_tvecPair__SWIG_0();
    public final static native long new_tvecPair__SWIG_1(long jarg1, tensorVector jarg1_, long jarg2, tensorVector jarg2_);
    public final static native long new_tvecPair__SWIG_2(long jarg1, tvecPair jarg1_);
    public final static native void tvecPair_first_set(long jarg1, tvecPair jarg1_, long jarg2, tensorVector jarg2_);
    public final static native long tvecPair_first_get(long jarg1, tvecPair jarg1_);
    public final static native void tvecPair_second_set(long jarg1, tvecPair jarg1_, long jarg2, tensorVector jarg2_);
    public final static native long tvecPair_second_get(long jarg1, tvecPair jarg1_);
    public final static native void delete_tvecPair(long jarg1);
    public final static native long new_Layer();
    public final static native void Layer_Setup(long jarg1, Layer jarg1_, long jarg2, Shape jarg2_, String jarg3);
    public final static native long Layer_param_values(long jarg1, Layer jarg1_);
    public final static native long Layer_GetOutputSampleShape(long jarg1, Layer jarg1_);
    public final static native void Layer_ToDevice(long jarg1, Layer jarg1_, long jarg2, Device jarg2_);
    public final static native void Layer_AsType(long jarg1, Layer jarg1_, int jarg2);
    public final static native long Layer_Forward__SWIG_0(long jarg1, Layer jarg1_, int jarg2, long jarg3, Tensor jarg3_);
    public final static native long Layer_Forward__SWIG_1(long jarg1, Layer jarg1_, int jarg2, long jarg3, tensorVector jarg3_);
    public final static native long Layer_Backward__SWIG_0(long jarg1, Layer jarg1_, int jarg2, long jarg3, Tensor jarg3_);
    public final static native long Layer_Backward__SWIG_1(long jarg1, Layer jarg1_, int jarg2, long jarg3);
    public final static native void delete_Layer(long jarg1);
    public final static native long CreateLayer(String jarg1);
    public final static native long GetRegisteredLayers();
    public final static native long new_RNN();
    public final static native void delete_RNN(long jarg1);
    public final static native void delete_Optimizer(long jarg1);
    public final static native void Optimizer_Setup(long jarg1, Optimizer jarg1_, String jarg2);
    public final static native void Optimizer_Apply(long jarg1, Optimizer jarg1_, int jarg2, float jarg3, String jarg4, long jarg5, Tensor jarg5_, long jarg6, Tensor jarg6_);
    public final static native long CreateOptimizer(String jarg1);
    public final static native long new_Constraint();
    public final static native void Constraint_Setup(long jarg1, Constraint jarg1_, String jarg2);
    public final static native void Constraint_Apply(long jarg1, Constraint jarg1_, int jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native void delete_Constraint(long jarg1);
    public final static native long CreateConstraint(String jarg1);
    public final static native long new_Regularizer();
    public final static native void Regularizer_Setup(long jarg1, Regularizer jarg1_, String jarg2);
    public final static native void Regularizer_Apply(long jarg1, Regularizer jarg1_, int jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native void delete_Regularizer(long jarg1);
    public final static native long CreateRegularizer(String jarg1);
    public final static native void delete_Loss(long jarg1);
    public final static native long Loss_Forward(long jarg1, Loss jarg1_, int jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native float Loss_Evaluate(long jarg1, Loss jarg1_, int jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native long Loss_Backward(long jarg1, Loss jarg1_);
    public final static native long MSE_Forward(long jarg1, MSE jarg1_, int jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native long MSE_Backward(long jarg1, MSE jarg1_);
    public final static native long new_MSE();
    public final static native void delete_MSE(long jarg1);
    public final static native long SoftmaxCrossEntropy_Forward(long jarg1, SoftmaxCrossEntropy jarg1_, int jarg2, long jarg3, Tensor jarg3_, long jarg4, Tensor jarg4_);
    public final static native long SoftmaxCrossEntropy_Backward(long jarg1, SoftmaxCrossEntropy jarg1_);
    public final static native long new_SoftmaxCrossEntropy();
    public final static native void delete_SoftmaxCrossEntropy(long jarg1);
    public final static native void delete_Metric(long jarg1);
    public final static native long Metric_Forward(long jarg1, Metric jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native float Metric_Evaluate(long jarg1, Metric jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native long Accuracy_Forward(long jarg1, Accuracy jarg1_, long jarg2, Tensor jarg2_, long jarg3, Tensor jarg3_);
    public final static native long new_Accuracy();
    public final static native void delete_Accuracy(long jarg1);
    public final static native long RNN_SWIGSmartPtrUpcast(long jarg1);
    public final static native long MSE_SWIGUpcast(long jarg1);
    public final static native long SoftmaxCrossEntropy_SWIGUpcast(long jarg1);
    public final static native long Accuracy_SWIGUpcast(long jarg1);
    public final static native long new_FeedForwardNet();
    public final static native long FeedForwardNet_Add__SWIG_0(long jarg1, FeedForwardNet jarg1_, long jarg2);
    public final static native long FeedForwardNet_Add__SWIG_1(long jarg1, FeedForwardNet jarg1_, long jarg2, long jarg3);
    public final static native long FeedForwardNet_GetParamNames(long jarg1, FeedForwardNet jarg1_);
    public final static native long FeedForwardNet_GetParamValues(long jarg1, FeedForwardNet jarg1_);
    public final static native long FeedForwardNet_GetParamSpecs(long jarg1, FeedForwardNet jarg1_);
    public final static native void FeedForwardNet_Compile(long jarg1, FeedForwardNet jarg1_, boolean jarg2, long jarg3, long jarg4, long jarg5);
    public final static native void FeedForwardNet_ToDevice(long jarg1, FeedForwardNet jarg1_, long jarg2);
    public final static native long FeedForwardNet_Clone(long jarg1, FeedForwardNet jarg1_, long jarg2);
    public final static native void FeedForwardNet_AsType(long jarg1, FeedForwardNet jarg1_, long jarg2);
    public final static native long FeedForwardNet_Forward(long jarg1, FeedForwardNet jarg1_, int jarg2, long jarg3);
    public final static native long FeedForwardNet_Backward(long jarg1, FeedForwardNet jarg1_, int jarg2, long jarg3);
    public final static native long FeedForwardNet_Evaluate(long jarg1, FeedForwardNet jarg1_, long jarg2, long jarg3, long jarg4);
    public final static native long FeedForwardNet_EvaluateOnBatch(long jarg1, FeedForwardNet jarg1_, long jarg2, long jarg3);
    public final static native long FeedForwardNet_Predict(long jarg1, FeedForwardNet jarg1_, long jarg2, long jarg3);
    public final static native void delete_FeedForwardNet(long jarg1);
}

