package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;
import model.Sensor;

import org.jdesktop.swingx.JXDatePicker;
import org.joda.time.DateTime;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class InitialInformationWizardPanel extends JWizardPanel implements
		PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4441397362905945887L;
	private JSplitPane splitPane;
	private JPanel container;
	private JPanel left;
	private JPanel right;
	private JLabel lblCalibrationCertificate;
	private StepsPanel stepsPanel;

	private JTextField certCodeField;
	private JTextField customerCodeField;
	private JTextField customerNameField;
	private JTextField customeraAddressField;
	private JTextField customerPhoneField;
	private JTextField calibratedByField;
	private JTextField approvedByField;

	private JXDatePicker issueDateField;
	private JXDatePicker recievedDateField;
	private JXDatePicker calibrationDateField;
	private JXDatePicker calibrationDueDateField;

	private boolean isDateManual;
	private boolean isCodeManual;
	private JLabel lblUnitOfMesuerment;
	private JComboBox<String> unitComboc;
	
	/**
	 * Create the panel.
	 */
	public InitialInformationWizardPanel(JWizardComponents e) {

		super(e);
		
		this.addPropertyChangeListener(this);
		
		final SensorInformationWizardPanel sensorInfoPanel = (SensorInformationWizardPanel) e
				.getWizardPanel(1);
		
		sensorInfoPanel.setQuestionsListener(new QuestionsListener() {

			@Override
			public void questionsAnswered() {

				boolean codeManual = sensorInfoPanel.isCodeManual();
				boolean dateManual = sensorInfoPanel.isDateManual();

				if (codeManual == true) {
					certCodeField.setEnabled(true);
					certCodeField.setText("");
				} else {
					certCodeField.setEnabled(false);
					certCodeField.setText("should be generated");
				}

				if (dateManual == true) {
					issueDateField.setEnabled(true);
					setIssueDate(null);
					recievedDateField.setDate(null);
					calibrationDateField.setDate(null);
					calibrationDueDateField.setDate(null);
				} else {
					issueDateField.setEnabled(false);
					setIssueDate(new Date());
				}

			}

		});
		
		sensorInfoPanel.setInitialInfoListenerListener(new SensorChosenListener() {
			
			@Override
			public void sensorChosen(Sensor sensor) {
				customerCodeField.setText(sensor.getMachine().getDepartment().getCompany().getCompanyCode());
				customerNameField.setText(sensor.getMachine().getDepartment().getCompany().getCompanyName());
				customeraAddressField.setText(sensor.getMachine().getDepartment().getCompany().getAddress());
				customerPhoneField.setText(sensor.getMachine().getDepartment().getCompany().getPhoneNumber());
			}
		});

		setPreferredSize(new Dimension(860, 393));
		setLayout(new BorderLayout(0, 0));

		container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));

		left = new JPanel();
		left.setPreferredSize(new Dimension(205, 10));
		right = new JPanel();

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(left);
		left.setLayout(new BorderLayout(0, 0));

		stepsPanel = new StepsPanel(2);
		left.add(stepsPanel, BorderLayout.CENTER);
		splitPane.setRightComponent(right);
		right.setLayout(null);

		lblCalibrationCertificate = new JLabel("Initial Information");
		lblCalibrationCertificate.setBounds(10, 11, 628, 32);
		lblCalibrationCertificate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalibrationCertificate.setOpaque(true);
		lblCalibrationCertificate.setForeground(new Color(255, 255, 255));
		lblCalibrationCertificate.setBackground(new Color(0, 128, 0));
		lblCalibrationCertificate.setFont(new Font(getFont().getFamily(),
				Font.BOLD, 15));
		right.add(lblCalibrationCertificate);

		JPanel panel = new JPanel();
		panel.setBounds(10, 54, 628, 326);
		right.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblCertificateNo = new JLabel("Certificate No.:");
		panel.add(lblCertificateNo, "2, 2, left, default");

		certCodeField = new JTextField();
		panel.add(certCodeField, "4, 2, fill, default");
		certCodeField.setColumns(10);

		JLabel lblIssueDate = new JLabel("Issue Date:");
		panel.add(lblIssueDate, "2, 4, left, default");

		issueDateField = new JXDatePicker();
		issueDateField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setCertificateDates();

			}
		});
		issueDateField.getEditor().setColumns(20);
		panel.add(issueDateField, "4, 4, fill, default");

		JLabel lblCustomerCode = new JLabel("Customer Code:");
		panel.add(lblCustomerCode, "2, 6, left, default");

		customerCodeField = new JTextField();
		customerCodeField.setEnabled(false);
		customerCodeField.setColumns(20);
		panel.add(customerCodeField, "4, 6, fill, default");

		JLabel lblCustomerName = new JLabel("Customer Name:");
		panel.add(lblCustomerName, "2, 8, left, default");

		customerNameField = new JTextField();
		customerNameField.setEnabled(false);
		customerNameField.setColumns(10);
		panel.add(customerNameField, "4, 8, fill, default");

		JLabel lblCustomerAddress = new JLabel("Customer Address:");
		panel.add(lblCustomerAddress, "2, 10, left, default");

		customeraAddressField = new JTextField();
		customeraAddressField.setEnabled(false);
		customeraAddressField.setColumns(10);
		panel.add(customeraAddressField, "4, 10, fill, default");

		JLabel lblCustomerPhone = new JLabel("Customer Phone:");
		panel.add(lblCustomerPhone, "2, 12, left, default");

		customerPhoneField = new JTextField();
		customerPhoneField.setEnabled(false);
		customerPhoneField.setColumns(10);
		panel.add(customerPhoneField, "4, 12, fill, default");

		JLabel lblRecievedDate = new JLabel("Recieved Date:");
		panel.add(lblRecievedDate, "2, 14, left, default");

		recievedDateField = new JXDatePicker();
		recievedDateField.getEditor().setEditable(false);
		recievedDateField.getEditor().setEnabled(false);
		panel.add(recievedDateField, "4, 14");

		JLabel lblCalibrationDate = new JLabel("Calibration Date:");
		panel.add(lblCalibrationDate, "2, 16, left, default");

		calibrationDateField = new JXDatePicker();
		calibrationDateField.getEditor().setEditable(false);
		calibrationDateField.getEditor().setEnabled(false);
		panel.add(calibrationDateField, "4, 16");

		JLabel lblCalibrationDueDate = new JLabel("Calibration Due Date:");
		panel.add(lblCalibrationDueDate, "2, 18, left, default");

		calibrationDueDateField = new JXDatePicker();
		calibrationDueDateField.getEditor().setEditable(false);
		calibrationDueDateField.getEditor().setEnabled(false);
		panel.add(calibrationDueDateField, "4, 18");

		JLabel lblCalibratedBy = new JLabel("Calibrated By:");
		panel.add(lblCalibratedBy, "2, 20, left, default");

		calibratedByField = new JTextField();
		calibratedByField.setColumns(10);
		panel.add(calibratedByField, "4, 20, fill, default");

		JLabel lblApprovedBy = new JLabel("Approved By:");
		panel.add(lblApprovedBy, "2, 22, left, default");

		approvedByField = new JTextField();
		approvedByField.setColumns(10);
		panel.add(approvedByField, "4, 22, fill, default");
		
		lblUnitOfMesuerment = new JLabel("Unit of Measurement:");
		panel.add(lblUnitOfMesuerment, "2, 24, right, default");
		
		unitComboc = new JComboBox();
		unitComboc.setModel(new DefaultComboBoxModel(new String[] {"Select The Unit", "Bar", "Pascal", "Degree Celisus (\u00B0C)", "Degree Celisus (\u00B0C) & Relative Humidity (% RH)", "Grams (g)", "Kilogram (kg)", "Miliggram (mg)", "Miliseconds (m/s)", "Milimeter (mm H2O \u00D7 10)", "Minutes (m)", "Relative Humidity (% RH)", "RPM", "Seconds (s)"}));
		unitComboc.setPreferredSize(new Dimension(250, 20));
		panel.add(unitComboc, "4, 24, left, default");

		container.add(splitPane, BorderLayout.CENTER);

	}

	/**
	 * @return the isDateManual
	 */
	public boolean isDateManual() {
		return isDateManual;
	}

	/**
	 * @param isDateManual
	 *            the isDateManual to set
	 */
	public void setDateManual(boolean isDateManual) {
		this.isDateManual = isDateManual;
	}

	/**
	 * @return the isCodeManual
	 */
	public boolean isCodeManual() {
		return isCodeManual;
	}

	/**
	 * @param isCodeManual
	 *            the isCodeManual to set
	 */
	public void setCodeManual(boolean isCodeManual) {
		this.isCodeManual = isCodeManual;
	}

	public String getCertificateCode() {
		return certCodeField.getText().trim();
	}

	public String getCalibratedBy() {
		return calibratedByField.getText().trim();
	}

	public String getApprovedBy() {
		return approvedByField.getText().trim();
	}

	public Date getIssueDate() {
		return issueDateField.getDate();
	}

	public Date getRecievedDate() {
		return recievedDateField.getDate();
	}

	public Date getCalibrationDate() {
		return calibrationDateField.getDate();
	}

	public Date getCalibrationDueDate() {
		return calibrationDueDateField.getDate();
	}

	private void setCertificateDates() {
		
		Date issueDate = issueDateField.getDate();
		
		if (issueDate != null) {

			DateTime receievedDate = new DateTime(issueDate).minusDays(2);
			
			DateTime calDate = receievedDate;
			
			DateTime calDueDate = new DateTime(calDate).plusYears(1);

			recievedDateField.setDate(receievedDate.toDate());
			
			calibrationDateField.setDate(calDate.toDate());
			
			calibrationDueDateField.setDate(calDueDate.toDate());
		}

	}

	private void setIssueDate(Date date) {
		Date oldValue = issueDateField.getDate();
		issueDateField.setDate(date);
		this.firePropertyChange("issueDate", oldValue, issueDateField.getDate());
	}

	/**
	 * @return the unitComboc
	 */
	public JComboBox<String> getUnitComboc() {
		return unitComboc;
	}

	/**
	 * @param unitComboc the unitComboc to set
	 */
	public void setUnitComboc(JComboBox<String> unitComboc) {
		this.unitComboc = unitComboc;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String propertyName = e.getPropertyName();
		if (propertyName.equalsIgnoreCase("issueDate")) {
			setCertificateDates();
		}

	}
}
