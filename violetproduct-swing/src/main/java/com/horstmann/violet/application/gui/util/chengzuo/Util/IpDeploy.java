package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;


public class IpDeploy {

	//list of ip
	private List<String> ips;
	//depoly: ip - type 
	private List<Pair<String,String>> deploy;
	
	public List<String> getIps() {
		return ips;
	}

	public void setIps(List<String> ips) {
		this.ips = ips;
	}

	public List<Pair<String, String>> getDeploy() {
		return deploy;
	}

	public Pair<String, String> getDeployAtIndex(int index) {
		return deploy.get(index);
	}

	public String getIPbyType(String type) {
		for (Pair<String,String> d:deploy){
			if(d.getSecond().equals(type)){
				return d.getFirst();
			}
		}
		return null;
	}

	public void setDeploy(List<Pair<String, String>> deploy) {
		this.deploy = deploy;
	}
	
	IpDeploy(){
		//1.get the properties
 		Pair<String,String> rP =  openProperties();
		//2.build ips and deploy
		build(rP);
	}
	
	//tranfer 
	private void build(Pair<String,String> rP) {
		
		String ip = rP.getFirst();
		String deployS = rP.getSecond();

		ips = new ArrayList<String>();
		String[] tmpL = ip.split(",");
		for(String r:tmpL) {
			ips.add(r);
		}
		
		deploy = new ArrayList<Pair<String,String>>();
		
		tmpL = deployS.split(";");
		for(String r:tmpL) {
			String[] t = r.split(",");
			String v="";
			if("0".equals(t[1])) {
				v = "function";
			}
			if("1".equals(t[1])) {
				v = "performance";
			}
			if("2".equals(t[1])) {
				v = "time";
			}
			String ipS = ips.get(Integer.valueOf(t[0]));
			deploy.add(new Pair<String,String>(ipS,v));
		}
	}
	
	//read properties
	private Pair<String,String> openProperties() {
		Properties prop = new Properties();
		Pair<String,String> rPair = new Pair<String,String>();
		try {
            // read .properties
			InputStream in = new BufferedInputStream(new FileInputStream("src/ip.properties"));
            // load list
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = prop.getProperty(key);
				//read ips
				if(key.equals("ip")) {
					rPair.setFirst(value);
				}
				//read deploy
				if(key.equals("deploy")) {
					rPair.setSecond(value);;
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rPair;
	}

	//test 
	public static void main(String[] args) {
		IpDeploy p = new IpDeploy();
	}
}
