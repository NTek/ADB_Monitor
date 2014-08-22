package com.ntek.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.ntek.daemon.DaemonCommands;
import com.ntek.daemon.DaemonExecute;

public class FrameLogCat extends JFrame {

	private StyleContext sc = null;
	private DefaultStyledDocument doc = null;
	private JTextPane pane = null;
	private Style heading2Style = null;
	private Style mainStyle = null;
	private Style defaultStyle = null;
	private Style iStyle = null;
	private Style dStyle = null;
	private Style eStyle = null;
	private Style wStyle = null;

	public FrameLogCat() {
		super();
		this.setSize(300, 300);

		// Create the StyleContext, the document and the pane
		sc = new StyleContext();
		doc = new DefaultStyledDocument(sc);
		pane = new JTextPane(doc);
		pane.setEditable(false);

		// Create and add the main document style
		defaultStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
		mainStyle = sc.addStyle("MainStyle", defaultStyle);
		StyleConstants.setLeftIndent(mainStyle, 16);
		StyleConstants.setRightIndent(mainStyle, 16);
		StyleConstants.setFontFamily(mainStyle, "arial");
		StyleConstants.setFontSize(mainStyle, 12);

		// Create and add the heading style
		heading2Style = sc.addStyle("Heading2", null);
		StyleConstants.setForeground(heading2Style, Color.DARK_GRAY);
		StyleConstants.setFontSize(heading2Style, 16);
		StyleConstants.setFontFamily(heading2Style, "arial");
		StyleConstants.setBold(heading2Style, true);
		StyleConstants.setLeftIndent(heading2Style, 8);
		StyleConstants.setFirstLineIndent(heading2Style, 0);

		// Information
		iStyle = sc.addStyle("Information", null);
		StyleConstants.setFontFamily(iStyle, "arial");
		StyleConstants.setForeground(iStyle, Color.green);

		// Debug
		dStyle = sc.addStyle("Debug", null);
		StyleConstants.setFontFamily(dStyle, "arial");
		StyleConstants.setForeground(dStyle, Color.blue);

		// Error
		eStyle = sc.addStyle("Error", null);
		StyleConstants.setFontFamily(eStyle, "arial");
		StyleConstants.setForeground(eStyle, Color.red);

		// Warning
		wStyle = sc.addStyle("Warning", null);
		StyleConstants.setFontFamily(wStyle, "arial");
		StyleConstants.setForeground(wStyle, Color.orange);

		// Set the logical style
		doc.setLogicalStyle(0, mainStyle);

		// Finally, apply the style to the heading
		doc.setParagraphAttributes(0, 1, heading2Style, false);

		// Set the foreground color and change the font
		pane.setForeground(Color.black);
		pane.setFont(new Font("arial", Font.BOLD, 12));

		add(new JScrollPane(pane));

		// Close Operations
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				DaemonExecute.getInstance().interuptThread(
						DaemonCommands.LOGCAT);
				FrameLogCat.this.setVisible(false);
			}
		});
	}

	public DefaultStyledDocument getDoc() {
		return doc;
	}

	public void setDoc(DefaultStyledDocument doc) {
		this.doc = doc;
	}

	public Style getiStyle() {
		return iStyle;
	}

	public void setiStyle(Style iStyle) {
		this.iStyle = iStyle;
	}

	public Style getdStyle() {
		return dStyle;
	}

	public void setdStyle(Style dStyle) {
		this.dStyle = dStyle;
	}

	public Style geteStyle() {
		return eStyle;
	}

	public void seteStyle(Style eStyle) {
		this.eStyle = eStyle;
	}

	public Style getwStyle() {
		return wStyle;
	}

	public void setwStyle(Style wStyle) {
		this.wStyle = wStyle;
	}
}
