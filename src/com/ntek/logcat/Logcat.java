package com.ntek.logcat;

import java.util.ArrayList;

import javax.swing.text.BadLocationException;

import com.ntek.daemon.DaemonCallback;
import com.ntek.daemon.DaemonCommands;
import com.ntek.daemon.DaemonExecute;
import com.ntek.process.Process;
import com.ntek.process.ProcessBean;
import com.ntek.ui.FrameLogCat;

public class Logcat {

	private ArrayList<LogcatBean> mLogcatBeans = null;

	public Logcat() {
		mLogcatBeans = new ArrayList<LogcatBean>();
		Process.getInstance().getProcessesFromDaemon();
		final FrameLogCat logCatFrame = new FrameLogCat();
		logCatFrame.setVisible(true);
		DaemonExecute.getInstance()
				.setCommand(DaemonCommands.LOGCAT, new DaemonCallback() {

					@Override
					public void response(String line) {
						mLogcatBeans.add(new LogcatBean(line));
						try {
							logCatFrame.getDoc().insertString(0, line, null);
							logCatFrame.getDoc().insertString(0, "\n", null);
						} catch (BadLocationException e) {
							System.out.println(e.getMessage());
						}
						switch (line.charAt(0)) {
						case 'I':
							logCatFrame.getDoc().setCharacterAttributes(0,
									line.length() + 1, logCatFrame.getiStyle(),
									false);
							break;
						case 'D':
							logCatFrame.getDoc().setCharacterAttributes(0,
									line.length() + 1, logCatFrame.getdStyle(),
									false);
							break;
						case 'E':
							logCatFrame.getDoc().setCharacterAttributes(0,
									line.length() + 1, logCatFrame.geteStyle(),
									false);
							break;
						case 'W':
							logCatFrame.getDoc().setCharacterAttributes(0,
									line.length() + 1, logCatFrame.getwStyle(),
									false);
							break;
						default:
							break;
						}
					}
				}).run();
	}

}
