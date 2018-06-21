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

public class TestRegression5 {

	//Random number generator seed, for reproducability
    public static final int seed = 12345;
    //Number of epochs (full passes of the data)
    public static final int nEpochs = 40000;
    //How frequently should we plot the network output?
    public static final int plotFrequency = 2000;
    //Number of data points
    public static int nSamples = 220;
    //Batch size: i.e., each epoch has nSamples/batchSize parameter updates
    public static int batchSize = 220;
    //Network learning rate
	public static final double learningRate = 0.01;
	//Network momentum
	public static final double momentum = 0.9;
    public static final Random rng = new Random(seed);
    public static final int numInputs = 5;
    public static final int numOutputs = 1;
    
    public static boolean sflag=false;
    public static int tSamples=50;
    public static List<List<Double>> txl=new ArrayList<>();
	public static List<Double> tyl = new ArrayList<>();
	public static double[] normalized = new double[] { 0, 1.0, 2.0 / 3, 1.0 / 3, 0 };
    
    public static void main(final String[] args){
    	
    	File file=new File("D://BP.txt");
    	file.delete();
    	try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        //Generate the training data
        DataSetIterator iterator = null;
		try {
			iterator = getTrainingData(batchSize,rng);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
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
            if((i+1) % plotFrequency == 0){
            	 try {
         			TestNet(net);
         		} catch (IOException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
            }
        }
        
    }
    
	/** Returns the network configuration, 2 hidden DenseLayers of size 50.
     */
    private static MultiLayerConfiguration getDeepDenseLayerNetworkConfiguration() {
        final int numHiddenNodes = 10;
        return new NeuralNetConfiguration.Builder()
                .seed(seed)
                .weightInit(WeightInit.XAVIER)
                .updater(new Nesterovs(learningRate, momentum))
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .activation(Activation.SIGMOID).build())
                .layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                        .activation(Activation.SIGMOID).build())
//                .layer(2, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
//                        .activation(Activation.SIGMOID).build())
//                .layer(3, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
//                        .activation(Activation.SIGMOID).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.SIGMOID)
                        .nIn(numHiddenNodes).nOut(numOutputs).build())
                .pretrain(false).backprop(true).build();
    }
    
    /** Create a DataSetIterator for training
     * @param batchSize Batch size (number of examples for every call of DataSetIterator.next())
     * @param rng Random number generator (for repeatability)
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
		int[] yy = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

//		for(int i=0;i<xx.length;i++){
//			List<Double> list=new ArrayList<>();
//			for(int j=0;j<xx[i].length;j++){
//				list.add(xx[i][j]);
//			}
////			Collections.shuffle(list, rng);
//			Collections.reverse(list);
//			for(int j=0;j<xx[i].length;j++){
//				xx[i][j]=list.get(j);
//			}
//		}
		
		List<List<Double>> xl=new ArrayList<>();
		List<Double> yl=new ArrayList<>();
		for(int i=0;i<xx.length;i++){
//			for(int k=0;k<xx[i].length;k++){
//				List<Double> list=new ArrayList<>();
//				for(int j=0;j<xx[i].length;j++){
//					list.add(xx[i][(j+k)%xx[i].length]);
//				}
//				xl.add(list);
//				yl.add(yy[i]);
//			}
			List<List<Integer>> list=DuplicatePerm.DFSInteger(xx[i]);
			for(int j=0;j<list.size();j++){
				
				List<Double> l=new ArrayList<>();
				for(int k=0;k<list.get(j).size();k++){
					l.add(normalized[list.get(j).get(k).intValue()]);
				}
				
				xl.add(l);
				yl.add(normalized[yy[i]]);
			}
		}
		
		nSamples=xl.size();
		batchSize=nSamples;
		
    	double[] result=new double[nSamples];
    	double[] input1=new double[nSamples];
    	double[] input2=new double[nSamples];
    	double[] input3=new double[nSamples];
    	double[] input4=new double[nSamples];
    	double[] input5=new double[nSamples];
    	
    	for(int i=0;i<nSamples;i++){
    		input1[i]=xl.get(i).get(0).doubleValue();
    		input2[i]=xl.get(i).get(1).doubleValue();
    		input3[i]=xl.get(i).get(2).doubleValue();
    		input4[i]=xl.get(i).get(3).doubleValue();
    		input5[i]=xl.get(i).get(4).doubleValue();
    		result[i]=yl.get(i);
    	}
    	
    	File file=new File("D://BP.txt");
    	Writer writer=new FileWriter(file, true);
    	
    	writer.write(nSamples+"\n");
    	for(int i=0;i<nSamples;i++){
    		writer.write(xl.get(i).toString()+" "+yl.get(i)+"\n");
    	}
    	writer.write("\n\n\n");
    	
    	writer.flush();
		writer.close();
    	
    	
    	
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
    
    private static void TestNet(MultiLayerNetwork net) throws IOException {
    	
    	File file=new File("D://BP.txt");
    	Writer writer=new FileWriter(file, true);
    	
		int[][] xx = new int[][] { { 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 }, { 4, 4, 4, 4, 4 },
				{ 3, 4, 2, 1, 3 }, { 1, 4, 4, 3, 2 }, { 2, 1, 3, 4, 3 }, { 3, 3, 3, 2, 4 }, { 4, 1, 1, 4, 3 },
				{ 2, 2, 1, 1, 4 }, { 2, 3, 1, 4, 2 }, { 1, 2, 1, 1, 3 }, { 4, 2, 3, 1, 3 }, { 1, 1, 2, 4, 1 },
				{ 2, 4, 4, 3, 3 }, { 3, 2, 1, 1, 4 }, { 3, 2, 1, 2, 4 }, { 2, 1, 1, 2, 3 }, { 4, 1, 2, 3, 2 },
				{ 4, 4, 2, 2, 4 }, };
		int[] yy = new int[] { 1, 2, 3, 4, 3, 4, 3, 3, 4, 1, 2, 1, 3, 1, 4, 1, 2, 2, 2, 4 };
		
		if(!sflag){
//			for(int i=0;i<xx.length;i++){
//				List<Double> list=new ArrayList<>();
//				for(int j=0;j<xx[i].length;j++){
//					list.add(normalized[xx[i][j]]);
//				}
//				txl.add(list);
//				tyl.add(normalized[yy[i]]);
////				Collections.shuffle(list, rng);
////				Collections.reverse(list);
//			}
			
			for(int i=0;i<tSamples;i++){
				int[] c=new int[6];
				List<Double> list=new ArrayList<>();
				for(int j=0;j<5;j++){
					int x=(int) (Math.random() * 4 + 1);
					c[x]++;
					list.add(normalized[x]);
				}
				int y,yc;
				y=0;
				yc=0;
				for(int j=1;j<c.length;j++){
					if(c[j]>=yc){
						yc=c[j];
						y=j;
					}
				}
				txl.add(list);
				tyl.add(normalized[y]);
			}
			
			sflag=true;
		}

    	List<INDArray> inputlist=new ArrayList<>();
    	List<INDArray> outlist=new ArrayList<>();
    	List<INDArray> resultlist=new ArrayList<>();
    	
    	for(int i=0;i<tyl.size();i++){
    		double[] tx=new double[5];
    		double[] ty=new double[1];
    		for(int j=0;j<txl.get(i).size();j++){
    			tx[j]=txl.get(i).get(j);
    		}
    		ty[0]=tyl.get(i);
    		INDArray input=Nd4j.create(tx, new int[]{1,5});
			inputlist.add(input);
			outlist.add(net.output(input));
			resultlist.add(Nd4j.create(ty, new int[]{1,1}));
    	}
    	
    	int t1,t2,count;
    	count=0;
    	for(int i=0;i<outlist.size();i++){
    		t1=ReNormalized(resultlist.get(i).getDouble(0));
    		t2=ReNormalized(outlist.get(i).getDouble(0));
    		if(t1==t2){
    			count++;
    		}
    		System.out.println(inputlist.get(i)+" "+resultlist.get(i)+" "+outlist.get(i)+" "+t1+" "+t2+" "+(t1==t2?"":"-"));
    		writer.write(inputlist.get(i)+" "+resultlist.get(i)+" "+outlist.get(i)+" "+t1+" "+t2+" "+(t1==t2?"":"-")+"\n");
    	}
    	System.out.println(count*1.0/outlist.size());
    	writer.write(count*1.0/outlist.size()+"\n");
    	writer.write("\n\n\n");
    	
    	writer.flush();
		writer.close();
    	
	}
    
    public static int ReNormalized(double value){
    	
    	int r;
    	double t,e;
    	r=0;
    	t=1.0;
    	
    	for(int i=1;i<normalized.length;i++){
    		e=Math.abs(value-normalized[i]);
    		if(e<t){
    			r=i;
    			t=e;
    		}
    	}
    	
    	return r;
    	
    }

}
