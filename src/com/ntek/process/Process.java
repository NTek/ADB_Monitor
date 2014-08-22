package com.ntek.process;

import java.util.ArrayList;

import com.ntek.daemon.DaemonCallback;
import com.ntek.daemon.DaemonCommands;
import com.ntek.daemon.DaemonExecute;

public class Process {

	private static Process mThis = null;
	private ArrayList<ProcessBean> mProcessBeans = null;

	private Process() {
	}

	public static Process getInstance() {
		if (mThis == null) {
			mThis = new Process();
		}
		return mThis;
	}

	public void getProcessesFromDaemon() {
		mProcessBeans = new ArrayList<ProcessBean>();
		DaemonExecute.getInstance()
				.setCommand(DaemonCommands.SHELL, new DaemonCallback() {
					@Override
					public void response(String response) {
						mProcessBeans.add(new ProcessBean(response));
					}
				}, DaemonCommands.SHOW_PROCESS).run();
	}

	public ArrayList<ProcessBean> getProcessBeans() {
		return mProcessBeans;
	}
}
