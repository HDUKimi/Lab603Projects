package com.horstmann.violet.application.lmr.deeplearn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

public class BpRegression {

	// Random number generator seed, for reproducability
	public static final int seed = 12345;
	// Number of epochs (full passes of the data)
	public static final int nEpochs = 20000;
	// How frequently should we plot the network output?
	public static final int plotFrequency = 2000;
	// Number of data points
	public static int nSamples = 220;
	// Batch size: i.e., each epoch has nSamples/batchSize parameter updates
	public static int batchSize = 220;
	// Network learning rate
	public static final double learningRate = 0.01;
	// Network momentum
	public static final double momentum = 0.9;
	public static final Random rng = new Random(seed);
	public static final int numInputs = 5;
	public static final int numOutputs = 1;

	public static int tSamples = 50;
	// public static List<List<Double>> txl = new ArrayList<>();
	// public static List<Double> tyl = new ArrayList<>();

	public static int[] input;

	public int Start(int[] input) {

		this.input = input;

		// Generate the training data
		DataSetIterator iterator = null;
		try {
			iterator = getTrainingData(batchSize, rng);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Switch these two options to do different functions with different
		// networks
		final MultiLayerConfiguration conf = getDeepDenseLayerNetworkConfiguration();

		// Create the network
		final MultiLayerNetwork net = new MultiLayerNetwork(conf);
		net.init();
		net.setListeners(new ScoreIterationListener(1));

		// Train the network on the full data set, and evaluate in periodically
		for (int i = 0; i < nEpochs; i++) {
			iterator.reset();
			net.fit(iterator);
		}

		return RunNet(net);

	}

	public static void main(final String[] args) {

		// Generate the training data
		DataSetIterator iterator = null;
		try {
			iterator = getTrainingData(batchSize, rng);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Switch these two options to do different functions with different
		// networks
		final MultiLayerConfiguration conf = getDeepDenseLayerNetworkConfiguration();

		// Create the network
		final MultiLayerNetwork net = new MultiLayerNetwork(conf);
		net.init();
		net.setListeners(new ScoreIterationListener(1));

		// Train the network on the full data set, and evaluate in periodically
		for (int i = 0; i < nEpochs; i++) {
			iterator.reset();
			net.fit(iterator);

			// if((i+1) % plotFrequency == 0){
			// try {
			// TestNet(net);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
		}

	}

	/**
	 * Returns the network configuration, 2 hidden DenseLayers of size 50.
	 */
	private static MultiLayerConfiguration getDeepDenseLayerNetworkConfiguration() {
		final int numHiddenNodes = 10;
		return new NeuralNetConfiguration.Builder().seed(seed).weightInit(WeightInit.XAVIER)
				.updater(new Nesterovs(learningRate, momentum)).list()
				.layer(0,
						new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes).activation(Activation.SIGMOID)
								.build())
				.layer(1,
						new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).activation(Activation.SIGMOID)
								.build())
				// .layer(2, new
				// DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
				// .activation(Activation.SIGMOID).build())
				// .layer(3, new
				// DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
				// .activation(Activation.SIGMOID).build())
				.layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE).activation(Activation.IDENTITY)
						.nIn(numHiddenNodes).nOut(numOutputs).build())
				.pretrain(false).backprop(true).build();
	}

	/**
	 * Create a DataSetIterator for training
	 * 
	 * @param batchSize
	 *            Batch size (number of examples for every call of
	 *            DataSetIterator.next())
	 * @param rng
	 *            Random number generator (for repeatability)
	 * @throws IOException
	 */
	private static DataSetIterator getTrainingData(int batchSize, final Random rng) throws IOException {

		int[][] xx = new int[][] { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 2 }, { 1, 1, 1, 1, 3 }, { 1, 1, 1, 1, 4 },
				{ 1, 1, 1, 2, 2 }, { 1, 1, 1, 2, 3 }, { 1, 1, 1, 2, 4 }, { 1, 1, 1, 3, 3 }, { 1, 1, 1, 3, 4 },
				{ 1, 1, 1, 4, 4 }, { 1, 1, 2, 3, 4 }, { 2, 2, 2, 2, 2 }, { 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 3 },
				{ 2, 2, 2, 2, 4 }, { 2, 2, 2, 1, 1 }, { 2, 2, 2, 1, 3 }, { 2, 2, 2, 1, 4 }, { 2, 2, 2, 3, 3 },
				{ 2, 2, 2, 3, 4 }, { 2, 2, 2, 4, 4 }, { 2, 2, 1, 3, 4 }, { 3, 3, 3, 3, 3 }, { 3, 3, 3, 3, 1 },
				{ 3, 3, 3, 3, 2 }, { 3, 3, 3, 3, 4 }, { 3, 3, 3, 1, 1 }, { 3, 3, 3, 1, 2 }, { 3, 3, 3, 1, 4 },
				{ 3, 3, 3, 2, 2 }, { 3, 3, 3, 2, 4 }, { 3, 3, 3, 4, 4 }, { 3, 3, 1, 2, 4 }, { 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 1 }, { 4, 4, 4, 4, 2 }, { 4, 4, 4, 4, 3 }, { 4, 4, 4, 1, 1 }, { 4, 4, 4, 1, 2 },
				{ 4, 4, 4, 1, 3 }, { 4, 4, 4, 2, 2 }, { 4, 4, 4, 2, 3 }, { 4, 4, 4, 3, 3 }, { 4, 4, 1, 2, 3 } };
		int[] yy = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

		// for(int i=0;i<xx.length;i++){
		// List<Double> list=new ArrayList<>();
		// for(int j=0;j<xx[i].length;j++){
		// list.add(xx[i][j]);
		// }
		//// Collections.shuffle(list, rng);
		// Collections.reverse(list);
		// for(int j=0;j<xx[i].length;j++){
		// xx[i][j]=list.get(j);
		// }
		// }

		List<List<Double>> xl = new ArrayList<>();
		List<Double> yl = new ArrayList<>();
		for (int i = 0; i < xx.length; i++) {
			List<List<Double>> list = DuplicatePerm.DFSDouble(xx[i]);
			for (int j = 0; j < list.size(); j++) {
				xl.add(list.get(j));
				yl.add((double) yy[i]);
			}
		}

		nSamples = xl.size();
		batchSize = nSamples;

		double[] result = new double[nSamples];
		double[] input1 = new double[nSamples];
		double[] input2 = new double[nSamples];
		double[] input3 = new double[nSamples];
		double[] input4 = new double[nSamples];
		double[] input5 = new double[nSamples];

		for (int i = 0; i < nSamples; i++) {
			input1[i] = xl.get(i).get(0).doubleValue();
			input2[i] = xl.get(i).get(1).doubleValue();
			input3[i] = xl.get(i).get(2).doubleValue();
			input4[i] = xl.get(i).get(3).doubleValue();
			input5[i] = xl.get(i).get(4).doubleValue();
			result[i] = yl.get(i);
		}

		INDArray inputNDArray1 = Nd4j.create(input1, new int[] { nSamples, 1 });
		INDArray inputNDArray2 = Nd4j.create(input2, new int[] { nSamples, 1 });
		INDArray inputNDArray3 = Nd4j.create(input3, new int[] { nSamples, 1 });
		INDArray inputNDArray4 = Nd4j.create(input4, new int[] { nSamples, 1 });
		INDArray inputNDArray5 = Nd4j.create(input5, new int[] { nSamples, 1 });
		INDArray input = Nd4j.hstack(inputNDArray1, inputNDArray2, inputNDArray3, inputNDArray4, inputNDArray5);
		INDArray out = Nd4j.create(result, new int[] { nSamples, 1 });

		DataSet allData = new DataSet(input, out);

		List<DataSet> list = allData.asList();
		Collections.shuffle(list, rng);
		return new ListDataSetIterator(list, batchSize);
	}

	private int RunNet(MultiLayerNetwork net) {

		double[] tx = new double[5];

		for (int i = 0; i < input.length; i++) {
			tx[i] = input[i];
		}
		INDArray inputArray = Nd4j.create(tx, new int[] { 1, 5 });

		double out=net.output(inputArray).getDouble(0);
		int result;
		
		if(out<1.5){
			result=1;
		}
		else if(out<2.5){
			result=2;
		}
		else if(out<3.5){
			result=3;
		}
		else{
			result=4;
		}
		
		System.out.println(out+" "+result);
		
		return result;

	}

}
