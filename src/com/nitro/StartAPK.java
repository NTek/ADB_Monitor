package com.nitro;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartAPK {

	
	public static void main(String[] args) {
		try {
		    Runtime r = Runtime.getRuntime();
		    String[] tasks={"/home/kroki-nitro/Android/adt-bundle-linux/sdk/platform-tools/adb","connect","192.168.232.111"};
		    
		    Process pr =r.exec(tasks);
	
		    BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		    
            String line=null;

            while((line=input.readLine()) != null) {
                System.out.println(line);
            }
            
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);
		
		} catch (IOException ex) {
		    ex.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Look();

	}

}
