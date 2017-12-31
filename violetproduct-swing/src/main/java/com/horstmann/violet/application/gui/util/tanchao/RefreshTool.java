package com.horstmann.violet.application.gui.util.tanchao;

import com.horstmann.violet.application.gui.StepFiveCenterTabbedPane;
import com.horstmann.violet.application.gui.StepFourCenterTabbedPane;
import com.horstmann.violet.application.gui.StepSixCenterTabbedPane;
import com.horstmann.violet.application.gui.StepThreeCenterTabbedPane;
import com.horstmann.violet.application.gui.StepTwoCenterTabbedPane;

public class RefreshTool {

	public static void RefreshTwoAndEnd() {
		StepTwoCenterTabbedPane.setNeedRefresh(true);
		StepSixCenterTabbedPane.setNeedRefresh(true);
		StepThreeCenterTabbedPane.setNeedRefresh(true);
		StepFourCenterTabbedPane.setNeedRefresh(true);
		StepFiveCenterTabbedPane.setNeedRefresh(true);
	}

	public static void RefreshThreeAndEnd() {
		StepSixCenterTabbedPane.setNeedRefresh(true);
		StepThreeCenterTabbedPane.setNeedRefresh(true);
		StepFourCenterTabbedPane.setNeedRefresh(true);
		StepFiveCenterTabbedPane.setNeedRefresh(true);
	}

	public static void RefreshFourAndEnd() {
		StepFourCenterTabbedPane.setNeedRefresh(true);
		StepFiveCenterTabbedPane.setNeedRefresh(true);
	}

	public static void RefreshFiveAndEnd() {
		StepFiveCenterTabbedPane.setNeedRefresh(true);
	}

}
