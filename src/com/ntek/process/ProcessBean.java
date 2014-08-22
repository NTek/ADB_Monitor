package com.ntek.process;

public class ProcessBean {

	private static final int USER_ID = 0;
	private static final int PID_ID = 1;
	private static final int NAME_ID = 8;

	private String mUser = "";
	private String mPID = "";
	private String mName = "";

	public ProcessBean(String rawProcess) {
		int lCounter = 0;
		String[] lProcessParsed = new String[9];
		for (String proc : rawProcess.split(" ")) {
			if (!proc.equals("")) {
				lProcessParsed[lCounter++] = proc;
			}
		}
		mUser = lProcessParsed[USER_ID];
		mPID = lProcessParsed[PID_ID];
		mName = lProcessParsed[NAME_ID];
	}

	public String getUser() {
		return mUser;
	}

	public String getPID() {
		return mPID;
	}

	public String getName() {
		return mName;
	}
}
