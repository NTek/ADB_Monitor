package com.nitro;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.nitro.logcat.LogCatFrame;

public class Look extends JFrame {
	public Look() {
		super();
		this.setSize(300, 300);
		this.add(new MainPanel());
		this.setVisible(true);
		
		//Close Operation
		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		 addWindowListener( new WindowAdapter() {
			 @Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit( 0 );  
			}
		});
	}

}
