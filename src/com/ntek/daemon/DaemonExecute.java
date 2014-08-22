package com.ntek.daemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DaemonExecute {
	private static final String TAG = "Execute";

	/** Execute Command */
	private Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				/** Execute Command. */
				Process lProcess = mRuntime.exec(getCommandArray());
				/** Read Output. */
				BufferedReader lBuffer = new BufferedReader(
						new InputStreamReader(lProcess.getInputStream()));
				String lLine = null;
				while ((lLine = lBuffer.readLine()) != null) {
					((DaemonThread) Thread.currentThread()).getDaemonCallback()
							.response(lLine);
					if (Thread.interrupted()) {
						break;
					}
				}
				/** Wait until Process is Finished. */
				lProcess.waitFor();
			} catch (IOException e) {
				System.out.println(TAG + " There was an error. " + e);
			} catch (InterruptedException e) {
				System.out.println(TAG + " There was an error. " + e);
			}
		}
	};

	private ArrayList<DaemonThread> mThreads = null;
	private String mCommand = "";
	private String[] mParameter = {};
	private DaemonCallback mDaemonCallback = null;
	private Runtime mRuntime = null;
	private static DaemonExecute mThis = null;

	private DaemonExecute() {
		mThreads = new ArrayList<DaemonThread>();
		mRuntime = Runtime.getRuntime();
	}

	private String[] getCommandArray() {
		String[] lCommandArray = new String[mParameter.length + 2];
		/** Add Daemon. */
		lCommandArray[0] = DaemonCommands.DAEMON;
		/** Add Command. */
		lCommandArray[1] = mCommand;
		for (int i = 0; i < mParameter.length; i++) {
			/** Add Parameters. */
			lCommandArray[i + 2] = mParameter[i];
		}
		return lCommandArray;
	}

	public static DaemonExecute getInstance() {
		if (mThis == null) {
			mThis = new DaemonExecute();
		}
		return mThis;
	}

	/**
	 * Set Command to Execute.
	 * 
	 * @param command
	 * @param daemonCallback
	 * @return Current Instance.
	 */
	public DaemonExecute setCommand(String command,
			DaemonCallback daemonCallback) {
		mCommand = command;
		mDaemonCallback = daemonCallback;
		return mThis;
	}

	/**
	 * Set Command to Execute.
	 * 
	 * @param command
	 * @param parameter
	 * @param daemonCallback
	 * @return Current Instance.
	 */
	public DaemonExecute setCommand(String command,
			DaemonCallback daemonCallback, String... parameter) {
		mCommand = command;
		mDaemonCallback = daemonCallback;
		mParameter = parameter;
		return mThis;
	}

	/** Run Thread with Specific Command. */
	public void run() {
		mThreads.add(new DaemonThread(mRunnable, mCommand, mDaemonCallback));
	}

	/** Stop Threada. */
	public void interuptThread(String command) {
		for (DaemonThread thread : mThreads) {
			if (thread.equals(command)) {
				thread.interrupt();
				mThreads.remove(thread);
				break;
			}
		}
	}

	/** Stop All Threads. */
	public void interuptAllThreads() {
		for (DaemonThread thread : mThreads) {
			thread.interrupt();
		}
	}

}
