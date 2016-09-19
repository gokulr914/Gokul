package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public interface singa_wrapConstants {
    public final static int USE_CUDA = singa_wrapJNI.USE_CUDA_get();
    public final static int USE_CUDNN = singa_wrapJNI.USE_CUDNN_get();
    public final static int CUDNN_VERSION_SWIG = singa_wrapJNI.CUDNN_VERSION_SWIG_get();
}
