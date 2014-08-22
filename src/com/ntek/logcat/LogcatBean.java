package com.ntek.logcat;

public class LogcatBean {
	private String mType = "";
	private String mTime = "";
	private String mPID = "";
	private String mProcess = "";
	private String mApplication = "";
	private String mMessage = "";

	public LogcatBean(String rawMessage) {
		mType = String.valueOf(rawMessage.charAt(0));
		rawMessage = rawMessage.substring(2, rawMessage.length());
		String[] lRawMessage = rawMessage.trim().replace(" ", "")
				.replace("(", ":").replace(")", "").split(":");
		System.out.println("#################################");
		for (String msg : lRawMessage) {
			System.out.println(">" + msg + "<");
		}
		System.out.println("#################################");

	}

	public String getType() {
		return mType;
	}

	public String getTime() {
		return mTime;
	}

	public String getPID() {
		return mPID;
	}

	public String getProcess() {
		return mProcess;
	}

	public String getApplication() {
		return mApplication;
	}

	public String getMessage() {
		return mMessage;
	}

}
