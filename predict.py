# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# =============================================================================

import numpy as np
import sys
import os
import csv

sys.path.append(os.path.join(os.path.dirname(__file__), '../../build/python'))

from singa import device
from numpy import *
from singa import tensor
from math import sqrt
import alexnet

size = 140001
split=size-10000

def load_dataset(filepath,num):
    print 'Loading data file %s' % filepath
    with open(filepath, 'rb') as fd:
        reader = csv.reader(fd,delimiter=',')
        array  = []
        label = []
        for row in reader:
                label.append(row[0])
                array.append(row[1])
                array.append(row[2])
                array.append(row[3])

        image = np.array(array,dtype=np.float32)
        image = image.reshape((size,3))
        label = np.array(label,dtype=np.int32)
        label = label.reshape(label.size, 1)

        np.random.shuffle(label)
        np.random.shuffle(image)

        if num ==1:
                return image[:split,:],label[:split,:]
        elif num ==2:
                return image[split:,:],label[split:,:]

def predict(net, images, cuda, topk=8):
    x = tensor.from_numpy(images.astype(np.float32))
    x.to_device(cuda)
    y = net.predict(x)
    y.to_host()
    y = tensor.to_numpy(y)
    prob = np.average(y,0)
    labels = np.flipud(np.argsort(prob))  # sort prob in descending order
    return labels[0:topk], prob[labels[0:topk]]

def compute_image_mean(train_dir,num):
    images = load_dataset(train_dir,num)
    print images
    return np.average(images,axis=1)
    
def compute_std(train_dir,num):
    images = load_dataset(train_dir,num)
    return np.std(images)

if __name__ == '__main__':
    model = alexnet.create_net()
    model.load('model.bin')
    cuda = device.create_cuda_gpu()
    model.to_device(cuda)

    #mean = compute_image_mean('/home/goku/Downloads/transformed103variance.csv',1)
    #std = compute_std('/home/goku/Downloads/transformed103variance.csv',1)
    test_images, _ = load_dataset('/home/goku/Downloads/6.csv',2)
    print predict(model, test_images,cuda)
