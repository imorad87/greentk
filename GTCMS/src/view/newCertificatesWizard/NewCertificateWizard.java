package view.newCertificatesWizard;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import jwizardcomponent.frame.JWizardFrame;

public class NewCertificateWizard extends jwizardcomponent.dialog.SimpleJWizardDialog {

	private static final long serialVersionUID = 6096384253674196752L;

	

	private WelcomeWizardPanel wellcomePanel;
	private SensorInformationWizardPanel sensorInformationWizardPanel;
	private InitialInformationWizardPanel initialInformationWizardPanel;
	private CalibrationElementWizardPanel calibrationElementWizardPanel;
	private ReadingsWizardPanel readingsWizardPanel;
	private FinishAndSaveWizardPanel finishAndSaveWizardPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					NewCertificateWizard dialog = new NewCertificateWizard();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public NewCertificateWizard() {
		wellcomePanel = new WelcomeWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(wellcomePanel);
		
		sensorInformationWizardPanel = new SensorInformationWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(sensorInformationWizardPanel);
		
		initialInformationWizardPanel = new InitialInformationWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(initialInformationWizardPanel);

		calibrationElementWizardPanel = new CalibrationElementWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(calibrationElementWizardPanel);

		readingsWizardPanel = new ReadingsWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(readingsWizardPanel);

		finishAndSaveWizardPanel = new FinishAndSaveWizardPanel(getWizardComponents());
		getWizardComponents().addWizardPanel(finishAndSaveWizardPanel);
		
		setSize(new Dimension(900, 600));
		centerLocation();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/dialogLogo.jpg")));	
		setTitle("You are creating a new certificate");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	
		setModal(true);
	getWizardComponents().getFinishButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
	});	
	}
	
	

	
	
	


	private void centerLocation() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screenSize.width / 2) - (getSize().width / 2);
		int y = (screenSize.height / 2) - (getSize().height / 2)-30;
		setLocation(x, y);
	}
}
