import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class HtmlReport

extends JFrame {
	public HtmlReport() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
	
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(10);
		JFormattedTextField formattedTextField = new JFormattedTextField(df);
		formattedTextField.setColumns(25);
		panel.add(formattedTextField);
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5693082520170463041L;
	private JTextField textField;

	public static void main(String[] args) {
		HtmlReport h = new HtmlReport();
		h.setVisible(true);
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.pack();
	}

}