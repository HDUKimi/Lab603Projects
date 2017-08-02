package com.horstmann.violet.application.menu.xiaole.SequenceTransfrom;

public class MainTransEAToViolet {
        public static void TransEAToViolet(String url,String path) throws Exception
        {
        	TransEAToViolet read=new TransEAToViolet();
        	read.ReadEATimingGraph(url);
      	    read.WriteVioletSequence(path);
        }
		
		public static void TransEAToViolet(String url,String path,String filename,EADiagram eADiagram) throws Exception
        {
        	TransEAToViolet read = new TransEAToViolet(url,filename,eADiagram);
        	read.ReadEATimingGraph(url);
      	    read.WriteVioletSequence(path);
        }
		
}
