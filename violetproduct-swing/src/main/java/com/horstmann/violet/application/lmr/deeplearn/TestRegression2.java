package com.horstmann.violet.application.lmr.deeplearn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class TestRegression2 {

	//Random number generator seed, for reproducability
    public static final int seed = 12345;
    //Number of epochs (full passes of the data)
    public static final int nEpochs = 10000;
    //How frequently should we plot the network output?
    public static final int plotFrequency = 5000;
    //Number of data points
    public static final int nSamples = 3;
    //Batch size: i.e., each epoch has nSamples/batchSize parameter updates
    public static final int batchSize = 3;
    //Network learning rate
	public static final double learningRate = 0.01;
	//Network momentum
	public static final double momentum = 0.9;
    public static final Random rng = new Random(seed);
    public static final int numInputs = 5;
    public static final int numOutputs = 1;
    
    public static void main(final String[] args){

        //Generate the training data
        final DataSetIterator iterator = getTrainingData(batchSize,rng);
        
        //Switch these two options to do different functions with different networks
        final MultiLayerConfiguration conf = getDeepDenseLayerNetworkConfiguration();

        //Create the network
        final MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(1));

        //Train the network on the full data set, and evaluate in periodically
        for( int i=0; i<nEpochs; i++ ){
            iterator.reset();
            net.fit(iterator);
        }
        
        TestNet(net);

    }
    
	/** Returns the network configuration, 2 hidden DenseLayers of size 50.
     */
    private static MultiLayerConfiguration getDeepDenseLayerNetworkConfiguration() {
        final int numHiddenNodes = 5;
        return new NeuralNetConfiguration.Builder()
                .seed(seed)
                .weightInit(WeightInit.XAVIER)
                .updater(new Nesterovs(learningRate, momentum))
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .activation(Activation.SIGMOID).build())
                .layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                        .activation(Activation.SIGMOID).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.IDENTITY)
                        .nIn(numHiddenNodes).nOut(numOutputs).build())
                .pretrain(false).backprop(true).build();
    }
    
    /** Create a DataSetIterator for training
     * @param batchSize Batch size (number of examples for every call of DataSetIterator.next())
     * @param rng Random number generator (for repeatability)
     */
    private static DataSetIterator getTrainingData(final int batchSize, final Random rng) {
        
    	double[] result=new double[nSamples];
    	double[] input1=new double[nSamples];
    	double[] input2=new double[nSamples];
    	double[] input3=new double[nSamples];
    	double[] input4=new double[nSamples];
    	double[] input5=new double[nSamples];
    	
    	for(int i=0;i<nSamples;i++){
    		input1[i]=i+1;
    		input2[i]=i+1;
    		input3[i]=i+1;
    		input4[i]=i+1;
    		input5[i]=i+1;
    		result[i]=i+1;
    	}
    	
    	INDArray inputNDArray1=Nd4j.create(input1, new int[]{nSamples,1});
    	INDArray inputNDArray2=Nd4j.create(input2, new int[]{nSamples,1});
    	INDArray inputNDArray3=Nd4j.create(input3, new int[]{nSamples,1});
    	INDArray inputNDArray4=Nd4j.create(input4, new int[]{nSamples,1});
    	INDArray inputNDArray5=Nd4j.create(input5, new int[]{nSamples,1});
    	INDArray input=Nd4j.hstack(inputNDArray1,inputNDArray2,inputNDArray3,inputNDArray4,inputNDArray5);
    	INDArray out=Nd4j.create(result, new int[]{nSamples,1});
    	
        DataSet allData = new DataSet(input,out);

        List<DataSet> list = allData.asList();
        Collections.shuffle(list,rng);
        return new ListDataSetIterator(list,batchSize);
    }
    
    private static void TestNet(MultiLayerNetwork net) {
    	
    	double[][] xx = new double[][] { { 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 }, { 3, 3, 3, 1, 2 },
			{ 2, 1, 1, 3, 1 }, { 2, 1, 1, 3, 1 }, { 2, 1, 1, 2, 1 }, { 2, 3, 1, 1, 1 }, { 2, 2, 2, 2, 1 },
			{ 3, 3, 1, 3, 1 }, { 1, 3, 2, 3, 3 }, { 2, 1, 2, 1, 1 }, { 3, 3, 3, 2, 2 }, { 2, 2, 3, 1, 1 },
			{ 1, 2, 3, 1, 1 }, { 1, 2, 1, 3, 1 }, { 2, 1, 2, 1, 3 }, { 2, 3, 3, 3, 3 }, { 1, 1, 1, 2, 3 },
			{ 1, 3, 3, 3, 2 } };
		double[] yy = new double[] { 1, 2, 3, 3, 1, 1, 1, 1, 2, 3, 3, 1, 3, 2, 1, 1, 2, 3, 1, 3 };
		
    	List<INDArray> inputlist=new ArrayList<>();
    	List<INDArray> outlist=new ArrayList<>();
    	List<INDArray> resultlist=new ArrayList<>();
    	
    	for(int i=0;i<yy.length;i++){
    		INDArray input=Nd4j.create(xx[i], new int[]{1,5});
			inputlist.add(input);
			outlist.add(net.output(input));
			resultlist.add(Nd4j.create(new double[]{yy[i]}, new int[]{1,1}));
    	}
    	
    	for(int i=0;i<outlist.size();i++){
    		System.out.println(inputlist.get(i)+" "+resultlist.get(i)+" "+outlist.get(i));
    	}
    	
	}

}
