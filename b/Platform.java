package com.examples.singamnist;

/**
 * Created by AdminCOOP on 8/23/2016.
 */
public class Platform {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;

    protected Platform(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(Platform obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                singa_wrapJNI.delete_Platform(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public static int GetNumGPUs() {
        return singa_wrapJNI.Platform_GetNumGPUs();
    }

    public static SWIGTYPE_p_std__vectorT_int_t GetGPUIDs() {
        return new SWIGTYPE_p_std__vectorT_int_t(singa_wrapJNI.Platform_GetGPUIDs(), true);
    }

    public static sizePair GetGPUMemSize(int device) {
        return new sizePair(singa_wrapJNI.Platform_GetGPUMemSize__SWIG_0(device), true);
    }

    public static vectorPair GetGPUMemSize() {
        return new vectorPair(singa_wrapJNI.Platform_GetGPUMemSize__SWIG_1(), true);
    }

    public static String DeviceQuery(int id, boolean verbose) {
        return singa_wrapJNI.Platform_DeviceQuery__SWIG_0(id, verbose);
    }

    public static String DeviceQuery(int id) {
        return singa_wrapJNI.Platform_DeviceQuery__SWIG_1(id);
    }

  //  public static vectorSharedPtr CreateCudaGPUs(long num_devices, long init_size) {
    //    return new vectorSharedPtr(singa_wrapJNI.Platform_CreateCudaGPUs__SWIG_0(num_devices, init_size), true);
    //}

    //public static vectorSharedPtr CreateCudaGPUs(long num_devices) {
      //  return new vectorSharedPtr(singa_wrapJNI.Platform_CreateCudaGPUs__SWIG_1(num_devices), true);
    //}

 //   public static vectorSharedPtr CreateCudaGPUsOn(SWIGTYPE_p_std__vectorT_int_t devices, long init_size) {
   //     return new vectorSharedPtr(singa_wrapJNI.Platform_CreateCudaGPUsOn__SWIG_0(SWIGTYPE_p_std__vectorT_int_t.getCPtr(devices), init_size), true);
    //}

//    public static vectorSharedPtr CreateCudaGPUsOn(SWIGTYPE_p_std__vectorT_int_t devices) {
  //      return new vectorSharedPtr(singa_wrapJNI.Platform_CreateCudaGPUsOn__SWIG_1(SWIGTYPE_p_std__vectorT_int_t.getCPtr(devices)), true);
   // }

 //   public static Device GetDefaultDevice() {
   //     long cPtr = singa_wrapJNI.Platform_GetDefaultDevice();
     //   return (cPtr == 0) ? null : new Device(cPtr, true);
    //}

    public Platform() {
        this(singa_wrapJNI.new_Platform(), true);
    }

}