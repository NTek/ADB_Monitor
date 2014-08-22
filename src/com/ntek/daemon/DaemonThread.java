package com.ntek.daemon;

public class DaemonThread extends Thread {
	private String mDaemonCommand = null;
	private DaemonCallback mDaemonCallback = null;

	public DaemonThread(Runnable runnable, String command,
			DaemonCallback daemonCallback) {
		super(runnable);
		mDaemonCommand = command;
		mDaemonCallback = daemonCallback;
		start();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			String command = (String) obj;
			return command.equals(mDaemonCommand);
		}
		return super.equals(obj);
	}

	public DaemonCallback getDaemonCallback() {
		return mDaemonCallback;
	}
}
