package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StepsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1651429811471100940L;
	private JPanel leftContainer;
	private JLabel lblWelcome;
	private JLabel lblChooseSensor;
	private JLabel lblCalElementcal;
	private JLabel lblCalReadings;
	private JLabel lblSave;
	private JLabel lblIntialInformation;

	/**
	 * Create the panel.
	 */
	public StepsPanel(int highlighted) {

		setLayout(new BorderLayout());

		leftContainer = new JPanel();
		leftContainer.setPreferredSize(new Dimension(205, 10));
		leftContainer.setLayout(null);

		JLabel lblStepsToCreate = new JLabel("Steps to create a certificate:");
		lblStepsToCreate.setBounds(10, 11, 180, 14);
		lblStepsToCreate
				.setFont(new Font(getFont().getFamily(), Font.BOLD, 12));
		leftContainer.add(lblStepsToCreate);

		lblWelcome = new JLabel("- Welcome");
		lblWelcome.setBounds(20, 36, 170, 14);
		lblWelcome.setFont(new Font(getFont().getFamily(), Font.PLAIN, 11));

		leftContainer.add(lblWelcome);

		lblChooseSensor = new JLabel("- Choose Sensor");
		lblChooseSensor.setBounds(20, 61, 170, 14);
		lblChooseSensor
				.setFont(new Font(getFont().getFamily(), Font.PLAIN, 11));

		leftContainer.add(lblChooseSensor);

		lblIntialInformation = new JLabel();
		lblIntialInformation.setText("- Intial Information");
		lblIntialInformation.setBounds(20, 86, 160, 13);
		lblIntialInformation.setFont(new Font(getFont().getFamily(),
				Font.PLAIN, 11));

		leftContainer.add(lblIntialInformation);

		lblCalElementcal = new JLabel("- Cal. Element / Cal. Device info.");
		lblCalElementcal.setBounds(20, 111, 264, 14);
		lblCalElementcal
				.setFont(new Font(getFont().getFamily(), Font.PLAIN, 11));

		leftContainer.add(lblCalElementcal);

		lblCalReadings = new JLabel("- Cal. Readings / Cal. Environment");
		lblCalReadings.setBounds(20, 136, 264, 14);
		lblCalReadings.setFont(new Font(getFont().getFamily(), Font.PLAIN, 11));

		leftContainer.add(lblCalReadings);

		lblSave = new JLabel("- Save & Export");
		lblSave.setBounds(20, 158, 161, 14);
		lblSave.setFont(new Font(getFont().getFamily(), Font.PLAIN, 11));

		leftContainer.add(lblSave);

		makeBold(highlighted);

		add(leftContainer, BorderLayout.CENTER);
	}

	private void makeBold(int highlighted) {
		switch (highlighted) {
		case 0:
			lblWelcome.setFont(new Font(getFont().getFamily(), Font.BOLD, 11));
			break;
		case 1:
			lblChooseSensor.setFont(new Font(getFont().getFamily(), Font.BOLD,
					11));
			break;
		case 2:
			lblIntialInformation.setFont(new Font(getFont().getFamily(),Font.BOLD, 11));
			break;
		case 3:
			lblCalElementcal
			.setFont(new Font(getFont().getFamily(), Font.BOLD, 11));
			break;
		case 4:
			lblCalReadings.setFont(new Font(getFont().getFamily(), Font.BOLD, 11));
			break;
		case 5:
			lblSave.setFont(new Font(getFont().getFamily(), Font.BOLD, 11));
			break;
		default:
			break;
		}

	}

}
