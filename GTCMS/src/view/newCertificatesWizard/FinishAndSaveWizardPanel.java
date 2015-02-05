package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import jwizardcomponent.FinishAction;
import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;
import model.CalibrationProcedure;
import model.InternationalStandard;
import model.Sensor;
import model.calibration.certificates.lines.TDCertificateLine;
import model.calibration.certificates.lines.TSCertificateLine;

import org.hibernate.HibernateException;

import tableModels.CalibrationInstrumentsTableModel;
import view.newCertificatesWizard.readingsViews.TDReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.TSReadingsWizardPanel;
import certificates.Certificate;
import certificates.TDCertificate;
import certificates.TSCertificate;
import dao.CertificateTask;

public class FinishAndSaveWizardPanel extends JWizardPanel {

	private static final long serialVersionUID = 6966444824554767051L;

	private static final int TS_Certificate = 0;
	private static final int T_Certificate = 1;
	private static final int PH_Certificate = 2;
	private static final int COND_Certificate = 3;
	private static final int TD_Certificate = 4;


	private JSplitPane splitPane;
	private JPanel container;
	private JPanel left;
	private JPanel right;
	private StepsPanel stepsPanel;

	/**
	 * Create the panel.
	 */
	public FinishAndSaveWizardPanel(final JWizardComponents e) {
		super(e);
		setPreferredSize(new Dimension(870, 376));
		setPanelTitle("");
		setLayout(new BorderLayout(0, 0));

		container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));

		left = new JPanel();
		left.setPreferredSize(new Dimension(200, 10));
		right = new JPanel();

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(left);
		left.setLayout(new BorderLayout(0, 0));

		stepsPanel = new StepsPanel(5);
		stepsPanel.setPreferredSize(new Dimension(205, 550));
		left.add(stepsPanel, BorderLayout.CENTER);
		splitPane.setRightComponent(right);
		right.setLayout(null);

		JLabel lblSaveExport = new JLabel("Save & Export");
		lblSaveExport.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveExport.setBounds(10, 11, 643, 32);
		lblSaveExport.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveExport.setOpaque(true);
		lblSaveExport.setForeground(new Color(255, 255, 255));
		lblSaveExport.setBackground(new Color(0, 128, 0));
		lblSaveExport.setFont(new Font(getFont().getFamily(), Font.BOLD, 15));
		right.add(lblSaveExport);

		JCheckBox chckbxViewCertificate = new JCheckBox("View Certificate");
		chckbxViewCertificate.setSelected(true);
		chckbxViewCertificate.setBounds(10, 93, 101, 23);
		right.add(chckbxViewCertificate);

		JCheckBox chckbxExport = new JCheckBox("Export");
		chckbxExport.setBounds(10, 119, 97, 23);
		right.add(chckbxExport);

		JLabel lblNowEverythingIs = new JLabel();
		lblNowEverythingIs.setBounds(10, 54, 643, 32);
		lblNowEverythingIs
				.setText(String
						.format("<html><div WIDTH=%d>%s</div><html>",
								643,
								"Now everything is ready. If you check the boxes below you will be able to view the certificate  and if not it will be saved but not displayed."));

		right.add(lblNowEverythingIs);

		container.add(splitPane, BorderLayout.CENTER);

		e.setFinishAction(new FinishAction(e) {

			@Override
			public void performAction() {

				try {
					new CertificateTask().save(createCertificate(4, e));
					
				} catch (HibernateException e1) {
					JOptionPane.showMessageDialog(FinishAndSaveWizardPanel.this, e1.getMessage());
				}

			}
		});
	}

	private Certificate createCertificate(int type, JWizardComponents e) {
		final SensorInformationWizardPanel sensorInformationPanel = (SensorInformationWizardPanel) e
				.getWizardPanel(1);
		final InitialInformationWizardPanel initialInformationPanel = (InitialInformationWizardPanel) e
				.getWizardPanel(2);
		final CalibrationElementWizardPanel calibrationElementPanel = (CalibrationElementWizardPanel) e
				.getWizardPanel(3);
		final ReadingsWizardPanel readingsWizardPanel = (ReadingsWizardPanel) e
				.getWizardPanel(4);

		switch (type) {
		case TS_Certificate:
			TSCertificate tsCertificate = new TSCertificate();
			TSReadingsWizardPanel tsReadingsWizardPanel = (TSReadingsWizardPanel) readingsWizardPanel
					.getReadingsWizardPanel();
			tsCertificate.setSensor((Sensor) sensorInformationPanel
					.getSensorComboBox().getModel().getSelectedItem());

			List<TSCertificateLine> list = tsReadingsWizardPanel
					.getTSLinesList();
			Iterator<TSCertificateLine> iterator = list.iterator();
			while (iterator.hasNext()) {
				iterator.next().setCertificate(tsCertificate);
			}

			tsCertificate.setLinesList(list);

			tsCertificate.setAcceptedLimit(Double
					.valueOf(calibrationElementPanel.getAcceptedLimitField()
							.getText()));
			tsCertificate
					.setApprovedBy(initialInformationPanel.getApprovedBy());
			tsCertificate.setCalEnvironment(readingsWizardPanel
					.getCalibrationEvnironment() == 0 ? "Lab" : "Site");
			tsCertificate.setCalibratedBy(initialInformationPanel
					.getCalibratedBy());
			tsCertificate.setCalibrationDate(initialInformationPanel
					.getCalibrationDate());
			tsCertificate
					.setCalibrationInstrumentsList(((CalibrationInstrumentsTableModel) calibrationElementPanel
							.getDevicesTable().getModel())
							.getCalibrationInstrumentsList());

			ArrayList<CalibrationProcedure> procedures = new ArrayList<CalibrationProcedure>();
			procedures.add((CalibrationProcedure) calibrationElementPanel
					.getCalibrationProcedureComboBox().getSelectedItem());

			tsCertificate.setCalProcedureList(procedures);
			tsCertificate.setCertificateCode(initialInformationPanel
					.getCertificateCode());
			tsCertificate.setCertificateStatus(tsReadingsWizardPanel
					.getStatus());
			tsCertificate.setDueDate(initialInformationPanel
					.getCalibrationDueDate());
			tsCertificate.setEnvironmentHumidity(readingsWizardPanel
					.getEnvironmentHumidity());
			tsCertificate.setEnvironmentTemp(readingsWizardPanel
					.getEnvironementTemp());

			ArrayList<InternationalStandard> standards = new ArrayList<InternationalStandard>();
			standards.add((InternationalStandard) calibrationElementPanel
					.getInternationalStandardComboBox().getSelectedItem());

			tsCertificate.setIntStandardList(standards);
			tsCertificate.setIssueDate(initialInformationPanel.getIssueDate());
			tsCertificate.setMaxCalRange(0);
			tsCertificate.setMinCalRange(100);
			tsCertificate.setReceivedDate(initialInformationPanel
					.getRecievedDate());
			tsCertificate.setStatusAfter(calibrationElementPanel
					.getStatusAfterField().getText());
			tsCertificate.setStatusBefore(calibrationElementPanel
					.getStatusBeforeField().getText());
			tsCertificate.setUncertianity(tsReadingsWizardPanel
					.getUncertianity());
			tsCertificate.setMeasurementUnit((String) initialInformationPanel.getUnitComboc().getSelectedItem());
			return tsCertificate;

		case T_Certificate:

			
		case COND_Certificate:

			

		case PH_Certificate:

		case TD_Certificate:
			TDCertificate tdCertificate = new TDCertificate();
			TDReadingsWizardPanel tdReadingsWizardPanel = (TDReadingsWizardPanel) readingsWizardPanel
					.getReadingsWizardPanel();
			tdCertificate.setSensor((Sensor) sensorInformationPanel
					.getSensorComboBox().getModel().getSelectedItem());

			List<TDCertificateLine> tdLinesList = tdReadingsWizardPanel
					.getTDLinesList();
			Iterator<TDCertificateLine> tdLineIterator = tdLinesList.iterator();
			
			while (tdLineIterator.hasNext()) {
				tdLineIterator.next().setCertificate(tdCertificate);
			}

			tdCertificate.setLinesList(tdLinesList);

			tdCertificate.setAcceptedLimit(Double
					.valueOf(calibrationElementPanel.getAcceptedLimitField()
							.getText()));
			tdCertificate
					.setApprovedBy(initialInformationPanel.getApprovedBy());
			tdCertificate.setCalEnvironment(readingsWizardPanel
					.getCalibrationEvnironment() == 0 ? "Lab" : "Site");
			tdCertificate.setCalibratedBy(initialInformationPanel
					.getCalibratedBy());
			tdCertificate.setCalibrationDate(initialInformationPanel
					.getCalibrationDate());
			tdCertificate
					.setCalibrationInstrumentsList(((CalibrationInstrumentsTableModel) calibrationElementPanel
							.getDevicesTable().getModel())
							.getCalibrationInstrumentsList());

			ArrayList<CalibrationProcedure> tdProceduresList = new ArrayList<CalibrationProcedure>();
			tdProceduresList.add((CalibrationProcedure) calibrationElementPanel
					.getCalibrationProcedureComboBox().getSelectedItem());

			tdCertificate.setCalProcedureList(tdProceduresList);
			tdCertificate.setCertificateCode(initialInformationPanel
					.getCertificateCode());
			tdCertificate.setCertificateStatus(tdReadingsWizardPanel
					.getStatus());
			tdCertificate.setDueDate(initialInformationPanel
					.getCalibrationDueDate());
			tdCertificate.setEnvironmentHumidity(readingsWizardPanel
					.getEnvironmentHumidity());
			tdCertificate.setEnvironmentTemp(readingsWizardPanel
					.getEnvironementTemp());

			ArrayList<InternationalStandard> tdStandardsList = new ArrayList<InternationalStandard>();
			tdStandardsList.add((InternationalStandard) calibrationElementPanel
					.getInternationalStandardComboBox().getSelectedItem());

			tdCertificate.setIntStandardList(tdStandardsList);
			tdCertificate.setIssueDate(initialInformationPanel.getIssueDate());
			tdCertificate.setMaxCalRange(0);
			tdCertificate.setMinCalRange(100);
			tdCertificate.setReceivedDate(initialInformationPanel
					.getRecievedDate());
			tdCertificate.setStatusAfter(calibrationElementPanel
					.getStatusAfterField().getText());
			tdCertificate.setStatusBefore(calibrationElementPanel
					.getStatusBeforeField().getText());
			tdCertificate.setUncertianity(tdReadingsWizardPanel.getUncertianity());
			tdCertificate.setMeasurementUnit((String) initialInformationPanel.getUnitComboc().getSelectedItem());
			return tdCertificate;

		default:
			return null;
		}

	}

	private void viewCertificate(Certificate detached) {

	}
}
