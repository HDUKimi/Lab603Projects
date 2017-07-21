package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

import org.apache.log4j.Logger;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;

/**
 * MutliThread with Socket
 * 
 * @author geek
 *
 */
public class Controller {

	private static Logger logger = Logger.getLogger(Controller.class);

	// deploy
	private static IpDeploy IP_TYPE_DEPLOY = new IpDeploy();

	// result data
	public static Map<String, List<TestCase>> testCaseMap = Collections
			.synchronizedMap(new HashMap<String, List<TestCase>>());

	// thread pool
	private static ExecutorService executorService = Executors.newCachedThreadPool();

	// result
	private static List<Future<Pair<String,List<TestCase>>>> resultList =
			new ArrayList<Future<Pair<String,List<TestCase>>>>();

	// deploy and handle
	private static void handleMapping(Pair<String, File> data) {

		if (data == null) {
			logger.debug("please choose file to send!");
		}
		String IP = IP_TYPE_DEPLOY.getIPbyType(data.getFirst());
		String Type = data.getFirst();
		File f = data.getSecond();
		logger.debug("connect ip is:" + IP + " ,type is:" + Type);
		resultList.add(executorService.submit(new HandelThread(IP, f, Type)));
	}

	// handle result
	private static void handleResult() {
		for (Future<Pair<String,List<TestCase>>> fs : resultList) {
			try {
				testCaseMap.put(fs.get().getFirst(), fs.get().getSecond());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public static void Run(Pair<String, File> data) {

		// deploy, distribute and accept
		handleMapping(data);

		// handle result
		handleResult();
	}

	public static void Close(Pair<String, File> data) {
		// close
		executorService.shutdown();
	}

}
