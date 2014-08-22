package com.ntek.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.ntek.daemon.DaemonExecute;
import com.ntek.logcat.Logcat;
import com.ntek.process.Process;

public class FrameMain {

	private JFrame frmAdbMonitor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain window = new FrameMain();
					window.frmAdbMonitor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdbMonitor = new JFrame();
		frmAdbMonitor.setTitle("ADB Monitor");
		frmAdbMonitor.setBounds(100, 100, 450, 300);
		frmAdbMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdbMonitor.getContentPane().setLayout(new BorderLayout(0, 0));
		frmAdbMonitor.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				DaemonExecute.getInstance().interuptAllThreads();
			}
		});

		JMenuBar menuBar = new JMenuBar();
		frmAdbMonitor.setJMenuBar(menuBar);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Process.getInstance().getProcessesFromDaemon();
			}
		});
		menuBar.add(btnConnect);

		JButton btnLogcat = new JButton("Logcat");
		btnLogcat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Logcat();
			}
		});
		menuBar.add(btnLogcat);
	}
}
