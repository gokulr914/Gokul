/* interface file for swig */


%module net
%include "std_vector.i"
%include "std_string.i"
%include "std_pair.i"
%include "std_shared_ptr.i"
%{
#include "singa/model/feed_forward_net.h"
#include "singa/model/intializer.h"
#include "singa/utils/logging.h"
#include "singa/utils/channel.h"
using singa::Tensor;
using singa::ParamSpec;
using singa::Device;
using singa::DataType;
%}

%shared_ptr(singa::Layer)


namespace singa {

class FeedForwardNet{
        
        std::shared_ptr<Layer> FeedForwardNet::Add(std::shared_ptr<Layer> layer);

        std::shared_pt<Layer> FeedForwardNet::Add(const LayerConf& conf, const Shape* sample_shape);

        const vector<string> FeedForwardNet::GetParamNames();

        const vector<Tensor> FeedForwardNet::GetParamValues();

        const vector <ParamSpec> FeedForwardNet::GetParamSpecs();

        void FeedForwardNet::Compile(bool shuffle, Optimizer* opt,Loss* loss, Metric* metric);

        void FeedForwardNet::ToDevice(std::shared_ptr<Device> device);

        FeedForwardNet FeedForwardNet::Clone(std::shared_ptr<Device> device);

        void FeedForwardNet::AsType(DataType dtype);

        void FeedForwardNet::Train(size_t batchsize,int nb_epoch, const Tensor& x,const Tensor& y,float val_split);

        void FeedForwardNet::Train(size_t batchsize,int nb_epoch, const Tensor& x,const Tensor & y,const Tensor& val_x,
               const Tensor& val_y) 

        const std::pair<float,float> FeedForwardNet::TrainOnBatch(int epoch,const Tensor& x, const Tensor& y);

        const Tensor FeedForwardNet::Forward(int flag,const Tensor& data);

        const vector<Tensor> FeedForwardNet::Backward(int flag,const Tensor& grad);

        
        std::pair<Tensor,Tensor> FeedForwardNet::Evaluate(const Tensor& x,const Tensor& y,size_t batchsize);

        std::pair<Tensor, Tensor> FeedForwardNet::EvaluateOnBatch(const Tensor& x, const Tensor& y);

        const Tensor FeedForwardNet::Predict(const Tensor& x, size_t batchsize);

        const Tensor FeedForwardNet:PredictOnBatch(const Tensor& x);
        
};
}

