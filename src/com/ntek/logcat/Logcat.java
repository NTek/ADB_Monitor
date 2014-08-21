package com.ntek.logcat;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Handler;

import javax.swing.text.BadLocationException;

public class Logcat implements Runnable {

	private Handler handle = null;
	private LogCatFrame logCatFrame =null;

	public Logcat(Handler handle, LogCatFrame logCatFrame) {
		this.handle = handle;
		this.logCatFrame=logCatFrame;
	}

	@Override
	public void run() {
		try {
			Runtime r = Runtime.getRuntime();
			String[] tasks = {
					"/home/kroki-nitro/Android/adt-bundle-linux/sdk/platform-tools/adb",
					"logcat" };

			Process pr = r.exec(tasks);

			BufferedReader input = new BufferedReader(new InputStreamReader(
					pr.getInputStream()));

			String line = null;
			String add=new String();
			
			while ((line = input.readLine()) != null) {
				try {
					logCatFrame.getDoc().insertString(0, line, null);
					logCatFrame.getDoc().insertString(0, "\n", null);
				}catch(BadLocationException e){
					System.out.println(e.getMessage());
				}
				switch(line.charAt(0)){
				case 'I':
					logCatFrame.getDoc().setCharacterAttributes(0, line.length()+1, logCatFrame.getiStyle(), false);
					break;
				case 'D':
					logCatFrame.getDoc().setCharacterAttributes(0, line.length()+1, logCatFrame.getdStyle(), false);
					break;
				case 'E':
					logCatFrame.getDoc().setCharacterAttributes(0, line.length()+1, logCatFrame.geteStyle(), false);
					break;
				case 'W':
					logCatFrame.getDoc().setCharacterAttributes(0, line.length()+1, logCatFrame.getwStyle(), false);
					break;
				default:
					break;
				}
			}

			int exitVal = pr.waitFor();
			System.out.println("Exited with error code " + exitVal);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
