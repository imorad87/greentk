package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;
import model.Company;
import model.Department;
import model.Location;
import model.Machine;
import model.Sensor;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import comboModels.CompaniesComboBoxModel;
import comboModels.DepartmentsComboBoxModel;
import comboModels.LocationsComboBoxModel;
import comboModels.MachinesComboBoxModel;
import comboModels.SensorsComboBoxModel;

import dao.CompanyTask;
import dao.DepartmentTask;
import dao.LocationTask;
import dao.MachineTask;

public class SensorInformationWizardPanel extends JWizardPanel {

	private static final long serialVersionUID = 8515271059650825094L;
	private JSplitPane splitPane;
	private JPanel container;
	private JPanel left;
	private JPanel right;
	private JPanel sensorInforPanel;
	private JLabel lblCompany;
	private ButtonGroup firstQuestionGroup;
	private ButtonGroup secondQuestionGroup;
	private JComboBox<Company> companyComboBox;
	private JComboBox<Department> departmentComboBox;
	private JComboBox<Location> locationComboBox;
	private JComboBox<Machine> machineComboBox;
	private JComboBox<Sensor> sensorComboBox;
	private JRadioButton manualCodeYesRadioButton;
	private JRadioButton manualCodeNoRadioButton;
	private JRadioButton manualIssueDateYesRadioButton;
	private JRadioButton manualIssueDateNoRadioButton;
	private JPanel questionsPanel;
	private JLabel CarefulLabel;

	private CompaniesComboBoxModel companiesComboBoxModel;
	private DepartmentsComboBoxModel departmentsComboBoxModel;
	private LocationsComboBoxModel locationsComboBoxModel;
	private MachinesComboBoxModel machinesComboBoxModel;
	private SensorsComboBoxModel sensorsComboBoxModel;

	private boolean isDateManual;
	private boolean isCodeManual;
	
	private QuestionsListener qListener;

	
	public void setQuestionsListener(QuestionsListener q) {
		this.qListener = q;
	}
	
	
	private SensorChosenListener initialInfoListener;
	public void setInitialInfoListenerListener(SensorChosenListener initialInfoListener) {
		this.initialInfoListener = initialInfoListener;
	}
	
	
	private SensorChosenListener calElementListener;

	public void setCalElementListener(SensorChosenListener calElementListener) {
		this.calElementListener = calElementListener;
	}



	

	private SensorChosenListener readingListener;
	
	public void setReadingListener(SensorChosenListener readingListener) {
		this.readingListener = readingListener;
	}
	


	public SensorInformationWizardPanel(JWizardComponents e) {
		super(e);
		companiesComboBoxModel = new CompaniesComboBoxModel();
		departmentsComboBoxModel = new DepartmentsComboBoxModel();
		locationsComboBoxModel = new LocationsComboBoxModel();
		machinesComboBoxModel = new MachinesComboBoxModel();
		sensorsComboBoxModel = new SensorsComboBoxModel();
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			List<Company> list = null;
			private List<Company> companiesList = new ArrayList<Company>();

			@Override
			protected Void doInBackground() throws Exception {
				list = new CompanyTask().getAllCompanies();
				return null;
			}

			@Override
			protected void done() {
				companiesList = list;
				companiesComboBoxModel.setCompanieList(companiesList);
				updateUI();
			}

		};
		worker.execute();
		initComponents();

	}

	private void initComponents() {
		setPreferredSize(new Dimension(852, 376));
		setLayout(new BorderLayout(0, 0));

		container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));

		left = new JPanel();
		left.setPreferredSize(new Dimension(220, 10));
		right = new JPanel();
		right.setBackground(SystemColor.controlHighlight);

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(left);
		left.setLayout(new BorderLayout(0, 0));

		StepsPanel stepsPanel = new StepsPanel(1);
		stepsPanel.setPreferredSize(new Dimension(220, 10));
		left.add(stepsPanel, BorderLayout.CENTER);
		splitPane.setRightComponent(right);
		right.setLayout(new BorderLayout(0, 0));

		JPanel rightCenter = new JPanel();
		right.add(rightCenter, BorderLayout.CENTER);
		rightCenter.setLayout(null);

		sensorInforPanel = new JPanel();
		sensorInforPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Sensor Information",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		sensorInforPanel.setBounds(10, 11, 605, 153);
		rightCenter.add(sensorInforPanel);
		sensorInforPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblCompany = new JLabel("Company:");
		sensorInforPanel.add(lblCompany, "2, 2, left, default");

		companyComboBox = new JComboBox<Company>(companiesComboBoxModel);
		AutoCompleteDecorator.decorate(companyComboBox);
		companyComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Company selectedCompany = (Company) e.getItem();
					List<Department> departmentsList = new CompanyTask()
							.getAllDepartments(selectedCompany);
					if (departmentsList.size() > 0) {
						departmentsComboBoxModel
								.setDepartmentsList(departmentsList);
						departmentComboBox.setModel(departmentsComboBoxModel);
						departmentComboBox.setSelectedIndex(-1);
						locationComboBox.setModel(new LocationsComboBoxModel());

					} else {
						departmentComboBox
								.setModel(new DepartmentsComboBoxModel());
						locationComboBox.setModel(new LocationsComboBoxModel());

						updateUI();
					}
				}
			}
		});
		sensorInforPanel.add(companyComboBox, "4, 2, fill, default");

		JLabel lblDepartment = new JLabel("Department:");
		sensorInforPanel.add(lblDepartment, "2, 4, left, default");

		departmentComboBox = new JComboBox<Department>(departmentsComboBoxModel);
		AutoCompleteDecorator.decorate(departmentComboBox);
		departmentComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Department selectedDepartment = (Department) e.getItem();
					List<Location> locationsList = new DepartmentTask()
							.getAllLocation(selectedDepartment);
					if (locationsList.size() > 0) {

						locationsComboBoxModel.setLocationsList(locationsList);
						locationComboBox.setModel(locationsComboBoxModel);
						locationComboBox.setSelectedIndex(-1);
					} else {
						locationComboBox.setModel(new LocationsComboBoxModel());

						updateUI();
					}
				}

			}
		});

		sensorInforPanel.add(departmentComboBox, "4, 4, fill, default");

		JLabel lblLocation = new JLabel("Location:");
		sensorInforPanel.add(lblLocation, "2, 6, left, default");

		locationComboBox = new JComboBox<Location>(locationsComboBoxModel);
		AutoCompleteDecorator.decorate(locationComboBox);

		locationComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Location selectedLocation = (Location) e.getItem();
					List<Machine> machinesList = new LocationTask()
							.getAllMAchines(selectedLocation);
					if (machinesList.size() > 0) {
						machinesComboBoxModel.setMachinesList(machinesList);
						machineComboBox.setModel(machinesComboBoxModel);
						machineComboBox.setSelectedIndex(-1);
					} else {
						machineComboBox.setModel(new MachinesComboBoxModel());
						updateUI();
					}
				}
			}
		});
		sensorInforPanel.add(locationComboBox, "4, 6, fill, default");

		JLabel lblMachine = new JLabel("Machine:");
		sensorInforPanel.add(lblMachine, "2, 8, left, default");

		machineComboBox = new JComboBox<Machine>(machinesComboBoxModel);
		AutoCompleteDecorator.decorate(machineComboBox);
		machineComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Machine selectedmachine = (Machine) e.getItem();
					List<Sensor> sensorsList = new MachineTask()
							.getAllSensors(selectedmachine);

					if (sensorsList.size() > 0) {
						sensorsComboBoxModel.setsesnorsList(sensorsList);
						sensorComboBox.setModel(sensorsComboBoxModel);
						sensorComboBox.setSelectedIndex(-1);
					} else {
						sensorComboBox.setModel(new SensorsComboBoxModel());
						updateUI();
					}
				}
			}
		});
		sensorInforPanel.add(machineComboBox, "4, 8, fill, default");

		JLabel lblSensor = new JLabel("Sensor:");
		sensorInforPanel.add(lblSensor, "2, 10, left, default");

		sensorComboBox = new JComboBox<Sensor>();
		sensorComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Sensor selectedSensor = (Sensor) e.getItem();
					calElementListener.sensorChosen(selectedSensor);
					readingListener.sensorChosen(selectedSensor);
					initialInfoListener.sensorChosen(selectedSensor);
				}
				
			}
		});
		
		AutoCompleteDecorator.decorate(sensorComboBox);
		sensorInforPanel.add(sensorComboBox, "4, 10, fill, default");

		CarefulLabel = new JLabel(
				"Please be carefull with the next questions as they will affect the creation process directly.");
		CarefulLabel.setBounds(10, 175, 436, 14);
		rightCenter.add(CarefulLabel);

		questionsPanel = new JPanel();
		questionsPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Questions",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		questionsPanel.setBounds(10, 196, 605, 66);
		rightCenter.add(questionsPanel);
		questionsPanel.setLayout(null);

		JLabel question1Label = new JLabel(
				"Do you want to add the code for the certificate manually?");
		question1Label.setBounds(10, 16, 277, 14);
		questionsPanel.add(question1Label);

		firstQuestionGroup = new ButtonGroup();

		manualCodeYesRadioButton = new JRadioButton("Yes");
		manualCodeYesRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setCodeManual(true);
				qListener.questionsAnswered();
			}
		});

		manualCodeYesRadioButton.setBounds(503, 12, 43, 23);
		questionsPanel.add(manualCodeYesRadioButton);

		manualCodeNoRadioButton = new JRadioButton("No");
		manualCodeNoRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setCodeManual(false);
				qListener.questionsAnswered();
			}
		});
		
		manualCodeNoRadioButton.setBounds(548, 12, 43, 23);
		questionsPanel.add(manualCodeNoRadioButton);

		firstQuestionGroup.add(manualCodeYesRadioButton);
		firstQuestionGroup.add(manualCodeNoRadioButton);

		JLabel question2Label = new JLabel(
				"Do you want to add the issue date of the certificate manually?");
		question2Label.setBounds(10, 41, 299, 14);
		questionsPanel.add(question2Label);

		secondQuestionGroup = new ButtonGroup();

		manualIssueDateYesRadioButton = new JRadioButton("Yes");
		manualIssueDateYesRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDateManual(true);
				qListener.questionsAnswered();
			}
		});

		manualIssueDateYesRadioButton.setBounds(503, 37, 43, 23);
		questionsPanel.add(manualIssueDateYesRadioButton);

		manualIssueDateNoRadioButton = new JRadioButton("No");
		manualIssueDateNoRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDateManual(false);
				qListener.questionsAnswered();
			}
		});
		manualIssueDateNoRadioButton.setBounds(548, 38, 43, 23);
		questionsPanel.add(manualIssueDateNoRadioButton);

		secondQuestionGroup.add(manualIssueDateNoRadioButton);
		secondQuestionGroup.add(manualIssueDateYesRadioButton);

		JLabel noteLabel = new JLabel();
		noteLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		noteLabel.setHorizontalAlignment(SwingConstants.LEFT);
		noteLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		noteLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		noteLabel
				.setText(String
						.format("<html><div WIDTH=%d>%s</div><html>",
								600,
								"Note that by choosing no for both questions, the code will be generated automaticlly based on the last issued certificated and the date will be the date of today."));
		noteLabel.setBounds(10, 273, 605, 32);
		rightCenter.add(noteLabel);

		JPanel rightNorth = new JPanel();
		rightNorth.setPreferredSize(new Dimension(10, 45));
		right.add(rightNorth, BorderLayout.NORTH);
		rightNorth.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sensor Information & Questions");
		lblNewLabel.setBounds(10, 11, 605, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font(getFont().getFamily(), Font.BOLD, 15));
		rightNorth.add(lblNewLabel);

		container.add(splitPane, BorderLayout.CENTER);

	}

	/**
	 * @return the companiesComboBoxModel
	 */
	public CompaniesComboBoxModel getCompaniesComboBoxModel() {
		return companiesComboBoxModel;
	}

	/**
	 * @param companiesComboBoxModel
	 *            the companiesComboBoxModel to set
	 */
	public void setCompaniesComboBoxModel(
			CompaniesComboBoxModel companiesComboBoxModel) {
		this.companiesComboBoxModel = companiesComboBoxModel;
	}

	/**
	 * @return the departmentsComboBoxModel
	 */
	public DepartmentsComboBoxModel getDepartmentsComboBoxModel() {
		return departmentsComboBoxModel;
	}

	/**
	 * @param departmentsComboBoxModel
	 *            the departmentsComboBoxModel to set
	 */
	public void setDepartmentsComboBoxModel(
			DepartmentsComboBoxModel departmentsComboBoxModel) {
		this.departmentsComboBoxModel = departmentsComboBoxModel;
	}

	/**
	 * @return the locationsComboBoxModel
	 */
	public LocationsComboBoxModel getLocationsComboBoxModel() {
		return locationsComboBoxModel;
	}

	/**
	 * @param locationsComboBoxModel
	 *            the locationsComboBoxModel to set
	 */
	public void setLocationsComboBoxModel(
			LocationsComboBoxModel locationsComboBoxModel) {
		this.locationsComboBoxModel = locationsComboBoxModel;
	}

	/**
	 * @return the machinesComboBoxModel
	 */
	public MachinesComboBoxModel getMachinesComboBoxModel() {
		return machinesComboBoxModel;
	}

	/**
	 * @param machinesComboBoxModel
	 *            the machinesComboBoxModel to set
	 */
	public void setMachinesComboBoxModel(
			MachinesComboBoxModel machinesComboBoxModel) {
		this.machinesComboBoxModel = machinesComboBoxModel;
	}

	/**
	 * @return the sensorsComboBoxModel
	 */
	public SensorsComboBoxModel getSensorsComboBoxModel() {
		return sensorsComboBoxModel;
	}

	/**
	 * @param sensorsComboBoxModel
	 *            the sensorsComboBoxModel to set
	 */
	public void setSensorsComboBoxModel(
			SensorsComboBoxModel sensorsComboBoxModel) {
		this.sensorsComboBoxModel = sensorsComboBoxModel;
	}

	/**
	 * @return the companyComboBox
	 */
	public JComboBox<Company> getCompanyComboBox() {
		return companyComboBox;
	}

	/**
	 * @param companyComboBox
	 *            the companyComboBox to set
	 */
	public void setCompanyComboBox(JComboBox<Company> companyComboBox) {
		this.companyComboBox = companyComboBox;
	}

	/**
	 * @return the departmentComboBox
	 */
	public JComboBox<Department> getDepartmentComboBox() {
		return departmentComboBox;
	}

	/**
	 * @param departmentComboBox
	 *            the departmentComboBox to set
	 */
	public void setDepartmentComboBox(JComboBox<Department> departmentComboBox) {
		this.departmentComboBox = departmentComboBox;
	}

	/**
	 * @return the locationComboBox
	 */
	public JComboBox<Location> getLocationComboBox() {
		return locationComboBox;
	}

	/**
	 * @param locationComboBox
	 *            the locationComboBox to set
	 */
	public void setLocationComboBox(JComboBox<Location> locationComboBox) {
		this.locationComboBox = locationComboBox;
	}

	/**
	 * @return the machineComboBox
	 */
	public JComboBox<Machine> getMachineComboBox() {
		return machineComboBox;
	}

	/**
	 * @param machineComboBox
	 *            the machineComboBox to set
	 */
	public void setMachineComboBox(JComboBox<Machine> machineComboBox) {
		this.machineComboBox = machineComboBox;
	}

	/**
	 * @return the sensorComboBox
	 */
	public JComboBox<Sensor> getSensorComboBox() {
		return sensorComboBox;
	}

	/**
	 * @param sensorComboBox
	 *            the sensorComboBox to set
	 */
	public void setSensorComboBox(JComboBox<Sensor> sensorComboBox) {
		this.sensorComboBox = sensorComboBox;
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

}
