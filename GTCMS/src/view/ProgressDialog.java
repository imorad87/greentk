package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXBusyLabel;

public class ProgressDialog extends JDialog {

	
	private static final long serialVersionUID = 521958422334232772L;
	private JXBusyLabel label;
	private JProgressBar bar;

	public ProgressDialog(Window Parent) {
		
		super(Parent);
		bar = new JProgressBar();
		bar.setIndeterminate(true);
		setModal(true);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 50));
		setResizable(false);
		/*label = new JXBusyLabel();
		label.setText("Working with the database please wait...");
		label.setDirection(org.jdesktop.swingx.painter.BusyPainter.Direction.LEFT);
		add(label, BorderLayout.CENTER);
		label.setBusy(true);*/
		bar.setString("Loading");
		bar.setStringPainted(true);
		add(bar);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		pack();
	}

	public JXBusyLabel getLabel() {
		return label;
	}

	public void setLabel(JXBusyLabel label) {
		this.label = label;
	}
	
	

}
