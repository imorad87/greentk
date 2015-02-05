package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;

public class WelcomeWizardPanel extends JWizardPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1442135657439028225L;
	private JSplitPane splitPane;
	private JPanel container;
	private JPanel left;
	private JPanel right;
	private JLabel lblNewLabel;
	private JLabel lblUsingThisWizard;
	private StepsPanel stepsPanel;

	/**
	 * Create the panel.
	 */
	public WelcomeWizardPanel(JWizardComponents e) {
		super(e);
		setLayout(new BorderLayout(0, 0));
		
		container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));
		
		left = new JPanel();
		left.setPreferredSize(new Dimension(200, 10));
		right = new JPanel();
		right.setBackground(SystemColor.controlHighlight);
		
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(left);
		left.setLayout(new BorderLayout(0, 0));
		
		stepsPanel = new StepsPanel(0);
		left.add(stepsPanel, BorderLayout.CENTER);
		splitPane.setRightComponent(right);
		right.setLayout(null);
		
		lblNewLabel = new JLabel("Welcome Engineer");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 473, 20);
		right.add(lblNewLabel);
		
		lblUsingThisWizard = new JLabel();//("Using this wizard, you are about to create a certificate for one of the sensors that you have calibrated.");
		lblUsingThisWizard.setBounds(10, 42, 473, 40);
		lblUsingThisWizard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsingThisWizard.setText(String.format("<html><div WIDTH=%d>%s</div><html>", 473, "Using this wizard, you are about to create a certificate for one of the sensors that you have calibrated."));
		right.add(lblUsingThisWizard);
		
		JLabel lblThisWizardWill = new JLabel();
		lblThisWizardWill.setBounds(10, 93, 473, 85);
		lblThisWizardWill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThisWizardWill.setText(String.format("<html><div WIDTH=%d>%s</div><html>", 473, "This wizard will guide you though creating the certificates by dividing the creation process into simple steps which you have to go though one after the other. The steps which you are about to go thought are displayed on the left.  Each steps will be highlighted to you; just to give you an indicator at which step you are."));

		right.add(lblThisWizardWill);
		
		JLabel finalMessage = new JLabel();
		finalMessage.setBounds(10, 230, 473, 61);
		finalMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		finalMessage.setText(String.format("<html><div WIDTH=%d>%s</div><html>", 473, "Please, Try to be carefule while working with this wizard and don't enter incorrect information becasue, what you will enter here will be displayed in the final certificate."));

		right.add(finalMessage);
		
		container.add(splitPane, BorderLayout.CENTER);
	
	}

	
}
