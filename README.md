### Python脚本保存model

#### simple-test

[src/main/resources/pythonSaveModel.py](http://52.80.92.184/xianhong.xu/tensorflow-model-loader-jvm/blob/master/src/main/resources/pythonSaveModel.py)

执行该脚本可在指定目录保存model:仅含有简单variable

#### mnist-sotfmax

[src/main/resources/mnistSoftMax.py](http://52.80.92.184/xianhong.xu/tensorflow-model-loader-jvm/blob/master/src/main/resources/mnistSoftMax.py)

执行脚本可在指定目录保存mnist model

### Java加载model

#### simple-test

[src/main/java/LoadModelSimple.java](http://52.80.92.184/xianhong.xu/tensorflow-model-loader-jvm/blob/master/src/main/java/LoadModelSimple.java)

加载model并打印variable实例

#### mnist-sotfmax

[src/main/java/kbs/MnistRload.java](http://52.80.92.184/xianhong.xu/tensorflow-model-loader-jvm/blob/master/src/main/java/kbs/MnistRload.java)

现在测试可以加载model,加载输入数据，并成功预测结果