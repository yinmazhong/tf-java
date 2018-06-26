package kbs;

import org.tensorflow.*;

import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xxh on 18-6-25.
 */
public class MnistRload {
    public static void main(String args[]) throws IOException {

        System.out.println(TensorFlow.version());
        final int NUM_PREDICTIONS = 1;

        /* load the model Bundle */
        SavedModelBundle bundle = SavedModelBundle.load("/tmp/mnist/model/1815", "serve");


        // create the session from the Bundle
        //Session sess = b.session();

        try (Graph g = bundle.graph();
             Session sess = bundle.session();) {
            Iterator<Operation> operations = g.operations();
            while (operations.hasNext()){
                System.out.println(operations.next().name());
            }
           // Ops ops = Ops.create(g);

            // Create the model
           // Input x = ops.placeholder(DataType.FLOAT, Shape.make(-1, 784));
//            Input x = ops.placeholder(DataType.INT64, Shape.make(-1, 784));
           /* Input weight = ops.variable(zeros(784, 10));
            Input b = ops.variable(new float[10]);
            Input y = ops.math().add(ops.math().matMul(x, weight), b);*/

            // Loss
            //Input y_ = ops.placeholder(DataType.INT64, Shape.make(-1, 10));
//            Input y_ = ops.placeholder(DataType.FLOAT, Shape.make(-1, 10));
            /*Input cross_entropy =
                    ops.math().mean(ops.nn().softmaxCrossEntropyWithLogits(y, y_).loss(), ops.constant(0));*/

            /*for (int i = 0; i < 100; i++) {
                Mnist mnist = Mnist.loadTrain(new File("mnist_data"), i * 100, 100);
                try (Tensor xs = Tensor.create(mnist.images());
                     Tensor ys_ = Tensor.create(mnist.labels());) {
                    dump(
                            sess.runner()
                                    .feed(x.asOutput(), xs)
                                    .feed(y_.asOutput(), ys_)
                                    //.fetch(cross_entropy.asOutput())
                                    .fetch(y.asOutput())
                                    .run());
                }
            }*/
            Mnist mnist = Mnist.loadTrain(new File("mnist_data"), 100, 10);
            try (Tensor xs = Tensor.create(mnist.images());
                 Tensor ys_ = Tensor.create(mnist.intlabels());) {
                long[] y = sess.runner()
                        .feed("x", xs)
                        .feed("y_", ys_)
                        .fetch("pred")
                        .run()
                        .get(0)
                        .copyTo(new long[10]);
                for (int i=0;i<10;i++){

                    System.out.println(y[i]);
                }
            }

            /*float[] y = sess.runner()
                    .feed("x", x)
                    .fetch("y")
                    .run()
                    .get(0)
                    .copyTo(new float[NUM_PREDICTIONS]);*/

            // print out the result.

        }
    }


    /*private static void dump(List<Tensor<?>> tensors) {
        for (Tensor tensor : tensors) {
            System.out.println(tensor.floatValue());
        }
    }*/


    private static final float[][] zeros(int dim1, int dim2) {
        float[][] result = new float[dim1][];
        for (int i = 0; i < dim1; i++) {
            result[i] = new float[dim2];
        }
        return result;
    }
}
