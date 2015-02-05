package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;
import model.CalibrationInstrument;
import model.CalibrationProcedure;
import model.InternationalStandard;
import model.Sensor;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import tableModels.CalibrationInstrumentsTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import comboModels.CalibrationDevicesComboBoxModel;
import comboModels.CalibrationProceduresComboBoxModel;
import comboModels.InternationalStandardsComboBoxModel;

import dao.CalibrationInstrumentTask;
import dao.CalibrationProcedureTask;
import dao.InternationalStandardTask;
import formatters.ImprovedFormattedTextField;

public class CalibrationElementWizardPanel extends JWizardPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2961746209912261983L;
	private JSplitPane splitPane;
	private JPanel container;
	private JPanel left;
	private JPanel right;
	private JLabel lblCalibrationElement;
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;

	private JTextField codeField;
	private JTextField serialField;
	private JTextField resolutionField;
	private JTextField statusBeforeField;
	private JTextField statusAfterField;
	private JTextField jobTypeField;
	private JTextField descriptionField;
	private JTextField manufacturerField;
	private JTextField locationField;
	private JTextField sectionField;

	private JLabel lblCalibrationProcedure;
	private JComboBox<CalibrationProcedure> calibrationProcedureComboBox;
	private JLabel lblInternationalStandard;
	private JComboBox<InternationalStandard> internationalStandardComboBox;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblChooseDevice;
	private JComboBox<CalibrationInstrument> calibrationDeviceComboBox;
	private JTable devicesTable;
	private JPanel panel_3;
	private JPanel panel_4;
	private StepsPanel stepsPanel;

	private CalibrationDevicesComboBoxModel calibrationDevicesComboBoxModel;
	private CalibrationProceduresComboBoxModel calibrationProceduresComboBoxModel;
	private InternationalStandardsComboBoxModel internationalStandardsComboBoxModel;
	private CalibrationInstrumentsTableModel calibrationInstrumentsTableModel;

	/**
	 * Create the panel.
	 */

	private Sensor sensor;
	private JPanel panel_5;
	private JLabel label_12;
	private ImprovedFormattedTextField maxRangeField;
	private JPanel panel_6;
	private JTextField acceptedLimitField;
	private JFormattedTextField miniRangeField;
	private JButton btnNewButton;

	private CalibrationElementListener elementListener;
	private JComboBox<String> limitTypeComboBox;

	public CalibrationElementListener getElementListener() {
		return elementListener;
	}

	public void setElementListener(CalibrationElementListener elementListener) {
		this.elementListener = elementListener;
	}

	public CalibrationElementWizardPanel(JWizardComponents e) {
		super(e);

		calibrationDevicesComboBoxModel = new CalibrationDevicesComboBoxModel();
		calibrationProceduresComboBoxModel = new CalibrationProceduresComboBoxModel();
		internationalStandardsComboBoxModel = new InternationalStandardsComboBoxModel();
		calibrationInstrumentsTableModel = new CalibrationInstrumentsTableModel();
		init();

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			List<CalibrationInstrument> instrumentsList = new ArrayList<>();
			List<CalibrationProcedure> proceduresList = new ArrayList<>();
			List<InternationalStandard> standardsList = new ArrayList<>();

			@Override
			protected Void doInBackground() throws Exception {

				instrumentsList = new CalibrationInstrumentTask().getAll();
				standardsList = new InternationalStandardTask().getAll();
				proceduresList = new CalibrationProcedureTask().getAll();
				return null;
			}

			@Override
			protected void done() {
				calibrationProceduresComboBoxModel
						.setCalibrationProceduresList(proceduresList);

				internationalStandardsComboBoxModel
						.setInternationalStandardsList(standardsList);
				calibrationDevicesComboBoxModel
						.setCalibrationInstrumentsList(instrumentsList);
			}
		};

		worker.execute();

		final SensorInformationWizardPanel sensor = (SensorInformationWizardPanel) e
				.getWizardPanel(1);
		sensor.setCalElementListener(new SensorChosenListener() {

			@Override
			public void sensorChosen(Sensor s) {
				setSensor(s);
				codeField.setText(s.getCode());
				serialField.setText(s.getSerialNumber());
				resolutionField.setText(String.valueOf(s.getResolution()));
				jobTypeField.setText(s.getJobType().getSymbol());
				descriptionField.setText(s.getDescription());
				manufacturerField.setText(s.getManufacturer());
				locationField.setText(s.getMachine().getLocation()
						.getLocation());
				sectionField.setText(s.getMachine().getSection());
			}
		});
	}

	private void init() {

		NumberFormat decimalFormat = NumberFormat.getInstance();
		decimalFormat.setMaximumFractionDigits(10);

		setPreferredSize(new Dimension(856, 500));
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

		stepsPanel = new StepsPanel(3);
		left.add(stepsPanel, BorderLayout.CENTER);
		splitPane.setRightComponent(right);
		right.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 11, 623, 235);
		right.add(panel_3);
		panel_3.setLayout(null);

		lblCalibrationElement = new JLabel("Calibration Element");
		lblCalibrationElement.setBounds(0, 0, 623, 32);
		panel_3.add(lblCalibrationElement);
		lblCalibrationElement.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalibrationElement.setOpaque(true);
		lblCalibrationElement.setForeground(new Color(255, 255, 255));
		lblCalibrationElement.setBackground(new Color(0, 128, 0));
		lblCalibrationElement.setFont(new Font(getFont().getFamily(),
				Font.BOLD, 15));

		panel = new JPanel();
		panel.setBounds(0, 32, 623, 203);
		panel_3.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(110dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(110dlu;pref):grow"),
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), RowSpec.decode("10dlu"),
				FormFactory.DEFAULT_ROWSPEC, }));

		label = new JLabel("Code:");
		panel.add(label, "2, 2, left, default");

		codeField = new JTextField();
		codeField.setEditable(false);
		codeField.setEnabled(false);
		codeField.setToolTipText(codeField.getText());
		codeField.setColumns(10);
		panel.add(codeField, "4, 2, fill, default");

		label_6 = new JLabel("Serial #:");
		panel.add(label_6, "6, 2, left, default");

		serialField = new JTextField();
		serialField.setEditable(false);
		serialField.setEnabled(false);
		serialField.setColumns(10);
		panel.add(serialField, "8, 2, fill, default");

		label_1 = new JLabel("Element Function Symbol:");
		panel.add(label_1, "2, 4, right, default");

		jobTypeField = new JTextField();
		jobTypeField.setEditable(false);
		jobTypeField.setEnabled(false);
		jobTypeField.setColumns(10);
		panel.add(jobTypeField, "4, 4, fill, default");

		label_7 = new JLabel("Calibration Range:");
		panel.add(label_7, "6, 4, left, default");

		panel_5 = new JPanel();
		panel.add(panel_5, "8, 4, fill, center");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"), ColumnSpec.decode("1px"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("1px"), FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormFactory.DEFAULT_ROWSPEC, }));

		miniRangeField = new ImprovedFormattedTextField(decimalFormat);
		panel_5.add(miniRangeField, "1, 1, fill, default");

		label_12 = new JLabel(":");
		panel_5.add(label_12, "4, 1, right, default");

		maxRangeField = new ImprovedFormattedTextField(decimalFormat);
		maxRangeField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (elementListener != null) {
					try{
					Double mr = Double.valueOf(maxRangeField.getText());
					elementListener.maxRangeUpdated(mr);
					}catch (NumberFormatException e1){
						
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (elementListener != null) {
					try{
					Double mr = Double.valueOf(maxRangeField.getText());
					elementListener.maxRangeUpdated(mr);
					}catch (NumberFormatException e2){
						
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (elementListener != null) {
					try{
					Double mr = Double.valueOf(maxRangeField.getText());
					elementListener.maxRangeUpdated(mr);
					}catch (NumberFormatException e3){
						
					}
				}

			}
		});

		panel_5.add(maxRangeField, "7, 1, fill, default");

		label_2 = new JLabel("Describtion:");
		panel.add(label_2, "2, 6, left, default");

		descriptionField = new JTextField();
		descriptionField.setEditable(false);
		descriptionField.setEnabled(false);
		descriptionField.setColumns(10);
		panel.add(descriptionField, "4, 6, fill, default");

		label_8 = new JLabel("Resolution:");
		panel.add(label_8, "6, 6, left, default");

		resolutionField = new JTextField();
		resolutionField.setEditable(false);
		resolutionField.setEnabled(false);
		resolutionField.setColumns(10);
		panel.add(resolutionField, "8, 6, fill, default");

		label_3 = new JLabel("Manufacturer:");
		panel.add(label_3, "2, 8, left, default");

		manufacturerField = new JTextField();
		manufacturerField.setEditable(false);
		manufacturerField.setEnabled(false);
		manufacturerField.setColumns(10);
		panel.add(manufacturerField, "4, 8, fill, default");

		label_9 = new JLabel("Status Before Cal.:");
		panel.add(label_9, "6, 8, left, default");

		statusBeforeField = new JTextField();
		statusBeforeField.setEnabled(false);
		statusBeforeField.setText("In Tolerance");
		statusBeforeField.setColumns(10);
		panel.add(statusBeforeField, "8, 8, fill, default");

		label_4 = new JLabel("Location:");

		panel.add(label_4, "2, 10, left, default");

		locationField = new JTextField();

		locationField.setEditable(false);

		locationField.setEnabled(false);

		locationField.setColumns(10);

		panel.add(locationField, "4, 10, fill, default");

		label_10 = new JLabel("Status After Cal.:");

		panel.add(label_10, "6, 10, left, default");

		statusAfterField = new JTextField();

		statusAfterField.setEnabled(false);

		statusAfterField.setText("In Tolerance");

		statusAfterField.setColumns(10);

		panel.add(statusAfterField, "8, 10, fill, default");

		label_5 = new JLabel("Section:");

		panel.add(label_5, "2, 12, left, default");

		sectionField = new JTextField();

		sectionField.setEditable(false);

		sectionField.setEnabled(false);

		sectionField.setColumns(10);

		panel.add(sectionField, "4, 12, fill, default");

		label_11 = new JLabel("Accepted Limit:");

		panel.add(label_11, "6, 12, left, default");

		panel_6 = new JPanel();

		panel.add(panel_6, "8, 12, fill, center");

		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		acceptedLimitField = new ImprovedFormattedTextField(decimalFormat);
		acceptedLimitField.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						if (elementListener != null) {
							try {
								Double al = Double.valueOf(acceptedLimitField
										.getText());
								elementListener.acceptanceLimitUpdated(al);
							} catch (NumberFormatException e1) {

							}

						}
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						if (elementListener != null) {
							try {
								Double al = Double.valueOf(acceptedLimitField
										.getText());
								elementListener.acceptanceLimitUpdated(al);
							} catch (NumberFormatException e2) {

							}

						}
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						if (elementListener != null) {
							try {
								Double al = Double.valueOf(acceptedLimitField
										.getText());
								elementListener.acceptanceLimitUpdated(al);
							} catch (NumberFormatException e3) {

							}

						}
					}
				});

		// acceptedLimitField.setFormatterFactory(plusMinuesFormatter);

		// acceptedLimitField.setValue(2);

		panel_6.add(acceptedLimitField);

		limitTypeComboBox = new JComboBox<String>();
		limitTypeComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					if (elementListener != null) {
						
						int selectedIndex = limitTypeComboBox.getSelectedIndex();
						
						if(selectedIndex > 0){
						
						elementListener
								.calculationMethodUpdated(limitTypeComboBox
										.getSelectedIndex());
						}else{
						JOptionPane.showMessageDialog(CalibrationElementWizardPanel.this, "You have to select a calculation method for the acceptance limit.");
						return;
						}
					}
				}

			}
		});
		
		DefaultComboBoxModel<String> limitTypesModel = new DefaultComboBoxModel<String>();
		limitTypesModel.addElement("Select");
		limitTypesModel.addElement("\u00B1 Value");
		limitTypesModel.addElement("% Rdg.");
		limitTypesModel.addElement("% F.S" );



		limitTypeComboBox.setModel(limitTypesModel);
				
		panel_6.add(limitTypeComboBox);

		lblCalibrationProcedure = new JLabel("Calibration Procedure:");
		panel.add(lblCalibrationProcedure, "2, 14, left, default");

		calibrationProcedureComboBox = new JComboBox<CalibrationProcedure>(
				calibrationProceduresComboBoxModel);
		AutoCompleteDecorator.decorate(calibrationProcedureComboBox);
		panel.add(calibrationProcedureComboBox, "4, 14, fill, default");

		lblInternationalStandard = new JLabel("International Standard:");
		panel.add(lblInternationalStandard, "6, 14, right, default");

		internationalStandardComboBox = new JComboBox<InternationalStandard>(
				internationalStandardsComboBoxModel);
		AutoCompleteDecorator.decorate(internationalStandardComboBox);
		panel.add(internationalStandardComboBox, "8, 14, fill, default");

		panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 257, 623, 230);
		right.add(panel_4);
		panel_4.setLayout(null);

		lblNewLabel = new JLabel("Calibration Device");
		lblNewLabel.setBounds(0, 0, 623, 32);
		panel_4.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font(getFont().getFamily(), Font.BOLD, 15));

		panel_1 = new JPanel();
		panel_1.setBounds(0, 31, 623, 199);
		panel_4.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2, BorderLayout.NORTH);

		lblChooseDevice = new JLabel("Choose Device:");
		panel_2.add(lblChooseDevice);

		calibrationDeviceComboBox = new JComboBox<CalibrationInstrument>(
				calibrationDevicesComboBoxModel);
		AutoCompleteDecorator.decorate(calibrationDeviceComboBox);

		panel_2.add(calibrationDeviceComboBox);

		JButton addButton = new JButton("");
		addButton.setToolTipText("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CalibrationInstrumentsTableModel tableModel = (CalibrationInstrumentsTableModel) devicesTable
						.getModel();
				CalibrationInstrument selectedDevice = (CalibrationInstrument) calibrationDeviceComboBox
						.getModel().getSelectedItem();
				if (selectedDevice != null) {
					if (tableModel.getCalibrationInstrumentsList().contains(
							selectedDevice)) {
						JOptionPane
								.showMessageDialog(
										CalibrationElementWizardPanel.this,
										"A device can only be added once. SMART ASS. Concentrate for God's Sake. I don't want to be blamed because of YOU.");
						return;
					}
					tableModel.getCalibrationInstrumentsList().add(
							selectedDevice);
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane.showMessageDialog(
							CalibrationElementWizardPanel.this,
							"You have to select a device first.");
				}
			}
		});
		addButton.setIcon(new ImageIcon(CalibrationElementWizardPanel.class
				.getResource("/resources/Add/Add_16x16.png")));
		panel_2.add(addButton);

		JButton removeButton = new JButton("");
		removeButton.setToolTipText("Remove Selected");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int row = devicesTable.getSelectedRow();
				if (row >= 0) {
					CalibrationInstrumentsTableModel tableModel = (CalibrationInstrumentsTableModel) devicesTable
							.getModel();
					tableModel.getCalibrationInstrumentsList().remove(row);
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(
									CalibrationElementWizardPanel.this,
									"Select a device from the table before you remove.");
				}

			}
		});
		removeButton.setIcon(new ImageIcon(CalibrationElementWizardPanel.class
				.getResource("/resources/Remove/Remove_16x16.png")));
		panel_2.add(removeButton);

		JButton removeAllButton = new JButton("");
		removeAllButton.setToolTipText("Remove all");
		removeAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CalibrationInstrumentsTableModel tableModel = (CalibrationInstrumentsTableModel) devicesTable
						.getModel();
				if (tableModel.getCalibrationInstrumentsList().size() > 0) {
					tableModel.getCalibrationInstrumentsList().removeAll(
							tableModel.getCalibrationInstrumentsList());
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(
									CalibrationElementWizardPanel.this,
									"What the hell you want to remove? The table is already EMPTY.");
				}
			}
		});
		removeAllButton.setIcon(new ImageIcon(
				CalibrationElementWizardPanel.class
						.getResource("/resources/Remove/remove_all_16.png")));
		panel_2.add(removeAllButton);

		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(acceptedLimitField.getText());
			}
		});
		panel_2.add(btnNewButton);

		devicesTable = new JTable(calibrationInstrumentsTableModel);
		JScrollPane scrollPane = new JScrollPane(devicesTable);
		scrollPane.setToolTipText("Devices table");
		panel_1.add(scrollPane, BorderLayout.CENTER);

		container.add(splitPane, BorderLayout.CENTER);

	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor s) {
		this.sensor = s;
	}

	/**
	 * @return the statusBeforeField
	 */
	public JTextField getStatusBeforeField() {
		return statusBeforeField;
	}

	/**
	 * @param statusBeforeField
	 *            the statusBeforeField to set
	 */
	public void setStatusBeforeField(JTextField statusBeforeField) {
		this.statusBeforeField = statusBeforeField;
	}

	/**
	 * @return the statusAfterField
	 */
	public JTextField getStatusAfterField() {
		return statusAfterField;
	}

	/**
	 * @param statusAfterField
	 *            the statusAfterField to set
	 */
	public void setStatusAfterField(JTextField statusAfterField) {
		this.statusAfterField = statusAfterField;
	}

	/**
	 * @return the acceptedLimitField
	 */
	public JTextField getAcceptedLimitField() {
		return acceptedLimitField;
	}

	/**
	 * @param acceptedLimitField
	 *            the acceptedLimitField to set
	 */
	public void setAcceptedLimitField(JFormattedTextField acceptedLimitField) {
		this.acceptedLimitField = acceptedLimitField;
	}

	/**
	 * @return the calibrationProcedureComboBox
	 */
	public JComboBox<CalibrationProcedure> getCalibrationProcedureComboBox() {
		return calibrationProcedureComboBox;
	}

	/**
	 * @param calibrationProcedureComboBox
	 *            the calibrationProcedureComboBox to set
	 */
	public void setCalibrationProcedureComboBox(
			JComboBox<CalibrationProcedure> calibrationProcedureComboBox) {
		this.calibrationProcedureComboBox = calibrationProcedureComboBox;
	}

	/**
	 * @return the internationalStandardComboBox
	 */
	public JComboBox<InternationalStandard> getInternationalStandardComboBox() {
		return internationalStandardComboBox;
	}

	/**
	 * @param internationalStandardComboBox
	 *            the internationalStandardComboBox to set
	 */
	public void setInternationalStandardComboBox(
			JComboBox<InternationalStandard> internationalStandardComboBox) {
		this.internationalStandardComboBox = internationalStandardComboBox;
	}

	/**
	 * @return the calibrationDeviceComboBox
	 */
	public JComboBox<CalibrationInstrument> getCalibrationDeviceComboBox() {
		return calibrationDeviceComboBox;
	}

	/**
	 * @param calibrationDeviceComboBox
	 *            the calibrationDeviceComboBox to set
	 */
	public void setCalibrationDeviceComboBox(
			JComboBox<CalibrationInstrument> calibrationDeviceComboBox) {
		this.calibrationDeviceComboBox = calibrationDeviceComboBox;
	}

	/**
	 * @return the devicesTable
	 */
	public JTable getDevicesTable() {
		return devicesTable;
	}

	/**
	 * @param devicesTable
	 *            the devicesTable to set
	 */
	public void setDevicesTable(JTable devicesTable) {
		this.devicesTable = devicesTable;
	}

	public JTextField getMaxRangeField() {
		return maxRangeField;
	}
}
