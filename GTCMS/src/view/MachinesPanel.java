package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Company;
import model.Department;
import model.JobType;
import model.Location;
import model.Machine;
import model.Sensor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import tableModels.MachinesTableModel;
import tableModels.SensrosTabelModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import comboModels.CompaniesComboBoxModel;
import comboModels.DepartmentsComboBoxModel;
import comboModels.JobTypesComboBoxModel;
import comboModels.LocationsComboBoxModel;
import comboModels.MachinesComboBoxModel;

import dao.CompanyTask;
import dao.DepartmentTask;
import dao.LocationTask;
import dao.MachineTask;
import dao.SensorTask;

@SuppressWarnings("serial")
public class MachinesPanel extends JPanel implements MouseListener {

	// ----------Machines/Sensors Table----------//
	private JXTable viewerTable;
	// ------------------------------------------//

	// --------JPanels---------//
	private JPanel centerPanel;
	private JPanel machineFormPanel;
	private JPanel machineButtonPanel;
	private JPanel topPanel;
	private JPanel sensorButtonPanel;
	private JPanel progreesBarPanel;
	private JPanel searchPanel;
	private JPanel tablePanel;
	private JPanel sensorsPanel;
	private JPanel companiesViewPanel;
	private JPanel sensorsFormPanel;
	// --------------------------------//

	// ---------------Machine Creation Fields----------------------//
	private JTextField machineNameField;
	private JTextField machineCodeField;
	private JTextField machineModelField;
	private JTextField machineManufacturerField;
	private JTextField machineSerialField;
	private JTextField machineIdField;
	private JTextField machineSectionField;
	private JTextField machineDescriptionField;

	private JComboBox<Company> machineCompaniesComboBox;
	private JComboBox<Department> machineDepartmentsComboBox;
	private JComboBox<Location> machineLocationsComboBox;

	private JLabel machineSectionLbl;
	private JLabel machineCompanyLbl;
	private JLabel machineDepartmentLbl;
	private JLabel machineLocationLbl;
	private JLabel machineDescriptionLbl;
	private JLabel machineIdLbl;

	private JButton machineCreateButton;
	private JButton machineUpdateButton;
	private JButton MachineDeleteButton;
	// -----------------------------------------------------------//

	private JLabel searchForLabel;
	private JTextField searchField;
	private JButton goButton;

	private JComboBox<String> searchByComboBox;
	private JLabel searchByLabel;
	private JProgressBar progressBar;
	private JPopupMenu popupMenu;
	private JMenuItem copyItem;
	private JMenuItem pasteItem;
	private JMenuItem cutItem;

	// ------------------Beans-----------------//
	private Machine machine = new Machine();
	private Sensor sensor = new Sensor();
	private Company company = new Company();
	private Department department = new Department();
	// ---------------------------------------//

	// -------------Tables Model-------------//
	private MachinesTableModel machinesModel;
	private SensrosTabelModel sensorsModel;
	// ------------------------------------//

	// -------------DAO for company, department and location-------------//

	// -----------------------------------------------------------------//

	// -------------Comboxes models-------------------------//
	private CompaniesComboBoxModel sensorCompaniesComboBoxModel;
	private DepartmentsComboBoxModel sensorDepartmentComboBoxModel;
	private LocationsComboBoxModel sensorLocationsComboBoxModel;
	private JobTypesComboBoxModel sensorJobTypesComboBoxModel;
	private MachinesComboBoxModel sensorMachinesComboBoxModel;

	private CompaniesComboBoxModel machineCompaniesComboBoxModel;
	private DepartmentsComboBoxModel machineDepartmentComboBoxModel;
	private LocationsComboBoxModel machineLocationsComboBoxModel;

	// ----------------------------------------------------//

	// private final EnableUpdateCreateButtons enableUpdateCreateButtons = new
	// EnableUpdateCreateButtons();
	private JToolBar toolBar;
	private JButton copyButton;
	private JButton pasteButton;
	private JButton cutButton;
	private JButton exportButton;
	private JButton printButton;
	private JSeparator separator;
	private JXButton refreashButton;

	private JSplitPane leftSplitPane;
	private JXTree companiesTree;

	private JLabel sensorCodeLbl;
	private JLabel sensorDescriptionLbl;
	private JTextField sensorCodeField;
	private JTextField sensorDescriptionField;
	private JLabel sensorSerialLbl;
	private JTextField sensorSerialField;
	private JLabel sensorModelLbl;
	private JLabel sensorManufactruerLbl;
	private JLabel sensorTypeLbl;
	private JLabel sensorCalibratedLbl;
	private JLabel sensorCalDateLbl;
	private JLabel sensorCalDueDateLbl;
	private JXDatePicker sensorCalDateField;
	private JXDatePicker sensorCalDueDateField;
	private JCheckBox sensorCalibratedCheckBox;
	private JTextField sensorManufactruerField;
	private JTextField sensorModelField;
	private JComboBox<JobType> sensorTypeComboBox;
	private JSplitPane centerSplitPane;
	private JButton sensorDeleteButton;
	private JButton sensorCreateButton;
	private JButton sensorUpdateButton;
	private JLabel sensorCmsLbl;
	private JTextField sensorCmsField;
	private JLabel sensorCompanyLbl;
	private JComboBox<Company> sensorCompanyComboBox;
	private JLabel sensorDepartmentLbl;
	private JComboBox<Department> sensorDepartmentComboBox;
	private JLabel sensorLocationLbl;
	private JComboBox<Location> sensorLocationComboBox;
	private JLabel sensorMachineLbl;
	private JComboBox<Machine> sensorMachineComboBox;
	private JPanel machinesSensorsViewPanel;
	private JRadioButton viewMachinesRadioButton;
	private JRadioButton viewSensorsRadioButton;
	private ButtonGroup radioButtonGroup;

	private JLabel sensorResolutionLbl;
	private JSpinner sensorResolutionField;

	private List<Company> companiesList;

	private SensorDataReady sensorDataReady;
	private MachineDataReady machineDataReady;
	
	private JButton btnNewButton;
	private JButton btnClear;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MachinesPanel() {
		companiesList = new ArrayList<>();
		sensorDataReady = new SensorDataReady();
		machineDataReady = new MachineDataReady();

		sensorDepartmentComboBoxModel = new DepartmentsComboBoxModel();
		sensorCompaniesComboBoxModel = new CompaniesComboBoxModel();
		sensorLocationsComboBoxModel = new LocationsComboBoxModel();
		sensorJobTypesComboBoxModel = new JobTypesComboBoxModel();
		sensorMachinesComboBoxModel = new MachinesComboBoxModel();

		machineCompaniesComboBoxModel = new CompaniesComboBoxModel();
		machineDepartmentComboBoxModel = new DepartmentsComboBoxModel();
		machineLocationsComboBoxModel = new LocationsComboBoxModel();

		setPreferredSize(new Dimension(1160, 600));
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout(5, 5));

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(300, 150));
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BorderLayout(0, 0));

		machineFormPanel = new JPanel();
		machineFormPanel.setBorder(new TitledBorder(null,
				"Machine Information", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		// leftPabel.add(machineFormPanel, BorderLayout.CENTER);
		machineFormPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(133dlu;default):grow"), },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
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
						FormFactory.DEFAULT_ROWSPEC, }));

		machineIdLbl = new JLabel("CMS ID:");
		machineFormPanel.add(machineIdLbl, "2, 2, left, default");

		machineIdField = new JTextField();
		machineIdField.addMouseListener(this);
		machineIdField.setEditable(false);
		machineFormPanel.add(machineIdField, "4, 2, left, default");
		machineIdField.setColumns(10);

		JLabel machineNameLbl = new JLabel("Name:");
		machineFormPanel.add(machineNameLbl, "2, 4, left, default");

		machineNameField = new JTextField();
		machineNameField.addMouseListener(this);
		machineNameField.getDocument().addDocumentListener(machineDataReady);
		machineFormPanel.add(machineNameField, "4, 4, fill, default");
		machineNameField.setColumns(10);

		JLabel machineCodeLbl = new JLabel("Code:");
		machineFormPanel.add(machineCodeLbl, "2, 6, left, default");

		machineCodeField = new JTextField();
		machineCodeField.addMouseListener(this);
		machineCodeField.getDocument().addDocumentListener(machineDataReady);
		machineFormPanel.add(machineCodeField, "4, 6, fill, default");
		machineCodeField.setColumns(10);

		JLabel machineModelLbl = new JLabel("Model:");
		machineFormPanel.add(machineModelLbl, "2, 8, left, default");

		machineModelField = new JTextField();
		machineModelField.addMouseListener(this);

		machineModelField.getDocument().addDocumentListener(machineDataReady);
		machineFormPanel.add(machineModelField, "4, 8, fill, default");
		machineModelField.setColumns(10);

		JLabel machineManufacturerLbl = new JLabel("Manufacturer:");
		machineFormPanel.add(machineManufacturerLbl, "2, 10, left, default");

		machineManufacturerField = new JTextField();
		machineManufacturerField.addMouseListener(this);
		machineManufacturerField.getDocument().addDocumentListener(machineDataReady);
		machineFormPanel.add(machineManufacturerField, "4, 10, fill, default");
		machineManufacturerField.setColumns(10);

		JLabel machineSerialLbl = new JLabel("Serial Number:");
		machineFormPanel.add(machineSerialLbl, "2, 12, left, default");

		machineSerialField = new JTextField();
		machineSerialField.getDocument().addDocumentListener(machineDataReady);
		machineSerialField.addMouseListener(this);
		machineFormPanel.add(machineSerialField, "4, 12, fill, default");
		machineSerialField.setColumns(10);

		machineSectionLbl = new JLabel("Section:");
		machineFormPanel.add(machineSectionLbl, "2, 14, left, default");

		machineSectionField = new JTextField();
		machineSectionField.addMouseListener(this);
		machineSectionField.getDocument().addDocumentListener(machineDataReady);
		machineFormPanel.add(machineSectionField, "4, 14, fill, default");
		machineSectionField.setColumns(10);

		machineDescriptionLbl = new JLabel("Description:");
		machineFormPanel.add(machineDescriptionLbl, "2, 16, left, default");

		machineDescriptionField = new JTextField();
		machineDescriptionField.getDocument().addDocumentListener(machineDataReady);
		machineDescriptionField.addMouseListener(this);
		machineFormPanel.add(machineDescriptionField, "4, 16, fill, default");
		machineDescriptionField.setColumns(10);

		machineCompanyLbl = new JLabel("Company:");
		machineFormPanel.add(machineCompanyLbl, "2, 18, left, default");

		machineCompaniesComboBox = new JComboBox();
		machineCompaniesComboBox.addActionListener(machineDataReady);
		machineCompaniesComboBox.setModel(machineCompaniesComboBoxModel);
		machineCompaniesComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Company selectedCompany = (Company) e.getItem();
					List<Department> departmentsList = new CompanyTask()
							.getAllDepartments(selectedCompany);
					if (departmentsList.size() > 0) {
						machineDepartmentComboBoxModel
								.setDepartmentsList(departmentsList);
						machineDepartmentsComboBox.setModel(machineDepartmentComboBoxModel);
						machineDepartmentsComboBox.setSelectedIndex(-1);
						machineLocationsComboBox
								.setModel(new LocationsComboBoxModel());

					} else {
						machineDepartmentsComboBox
								.setModel(new DepartmentsComboBoxModel());
						machineLocationsComboBox
								.setModel(new LocationsComboBoxModel());

						updateUI();
					}
				}
			}
		});

		machineFormPanel.add(machineCompaniesComboBox, "4, 18, fill, default");

		machineDepartmentLbl = new JLabel("Department:");
		machineFormPanel.add(machineDepartmentLbl, "2, 20, left, default");

		machineDepartmentsComboBox = new JComboBox<Department>();
		machineDepartmentsComboBox.addActionListener(machineDataReady);
		//machineDepartmentsComboBox.setModel(machineDepartmentComboBoxModel);
		machineDepartmentsComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Department selectedDepartment = (Department) e.getItem();
					List<Location> locationsList = new DepartmentTask()
							.getAllLocation(selectedDepartment);
					if (locationsList.size() > 0) {

						machineLocationsComboBoxModel
								.setLocationsList(locationsList);
						machineLocationsComboBox
								.setModel(machineLocationsComboBoxModel);
						machineLocationsComboBox.setSelectedIndex(-1);
					} else {
						machineLocationsComboBox
								.setModel(new LocationsComboBoxModel());

						updateUI();
					}
				}

			}
		});

		machineFormPanel
				.add(machineDepartmentsComboBox, "4, 20, fill, default");

		machineLocationLbl = new JLabel("Location:");
		machineFormPanel.add(machineLocationLbl, "2, 22, left, default");

		machineLocationsComboBox = new JComboBox();
		machineLocationsComboBox.addActionListener(machineDataReady);
		//machineLocationsComboBox.setModel(machineLocationsComboBoxModel);

		machineFormPanel.add(machineLocationsComboBox, "4, 22, fill, default");

		machineButtonPanel = new JPanel();
		machineFormPanel.add(machineButtonPanel, "2, 24, 3, 1, center, top");

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearMachineFields();
			}
		});
		machineButtonPanel.add(btnClear);

		MachineDeleteButton = new JButton("Delete");

		machineButtonPanel.add(MachineDeleteButton);

		machineCreateButton = new JButton("Create");
		machineCreateButton.setEnabled(false);

		machineCreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createMachine();
				viewerTable.clearSelection();
			}
		});

		machineButtonPanel.add(machineCreateButton);

		machineUpdateButton = new JButton("Update");
		machineUpdateButton.setEnabled(false);
		machineUpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateMachine();
			}

			private void updateMachine() {
				String name = machineNameField.getText().trim();
				String code = machineCodeField.getText().trim();
				String model = machineModelField.getText().trim();
				String serial = machineSerialField.getText().trim();
				String section = machineSectionField.getText().trim();
				String manfacturer = machineManufacturerField.getText().trim();
				String description = machineDescriptionField.getText().trim();

				Department dep = (Department) machineDepartmentsComboBox
						.getSelectedItem();
				Location loc = (Location) machineLocationsComboBox
						.getSelectedItem();

				machine.setDepartment(dep);
				machine.setDescription(description);
				machine.setLocation(loc);
				machine.setMachineCode(code);
				machine.setMachineName(name);
				machine.setManufacturer(manfacturer);
				machine.setModel(model);
				machine.setSection(section);
				machine.setSerialNumber(serial);

				try {
					new MachineTask().updateMachine(machine);
				} catch (HibernateException e) {
					JOptionPane.showMessageDialog(null, e.getCause()
							.getMessage());
				}
				refreshData();
				clearMachineFields();

			}
		});

		machineUpdateButton.setEnabled(true);

		machineButtonPanel.add(machineUpdateButton);

		companiesViewPanel = new JPanel();
		companiesViewPanel.setBorder(new TitledBorder(null, "Choose Company",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		leftSplitPane = new JSplitPane();
		leftSplitPane.setResizeWeight(1.0);
		leftSplitPane
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		leftSplitPane.setDividerSize(8);
		leftSplitPane.setOneTouchExpandable(true);
		leftSplitPane.setPreferredSize(new Dimension(179, 50));
		leftSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		leftSplitPane.add(companiesViewPanel, JSplitPane.TOP);
		companiesViewPanel.setLayout(new BorderLayout(0, 0));

		radioButtonGroup = new ButtonGroup();

		machinesSensorsViewPanel = new JPanel();
		machinesSensorsViewPanel.setBorder(new TitledBorder(null,
				"Data to view", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		machinesSensorsViewPanel
				.setToolTipText("What you want to display on the table");
		leftPanel.add(machinesSensorsViewPanel, BorderLayout.NORTH);
		machinesSensorsViewPanel.setLayout(new BoxLayout(
				machinesSensorsViewPanel, BoxLayout.X_AXIS));

		viewMachinesRadioButton = new JRadioButton("View Machines");
		viewMachinesRadioButton.setActionCommand("View Machines");
		viewMachinesRadioButton.setSelected(true);
		viewMachinesRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewerTable.setModel(new MachinesTableModel());
				companiesTree.setSelectionRow(-1);
				companiesTree.updateUI();
				clearSensorFields();
			}
		});
		;
		machinesSensorsViewPanel.add(viewMachinesRadioButton);

		viewSensorsRadioButton = new JRadioButton("View Sensors");
		viewSensorsRadioButton.setActionCommand("View Sensors");
		viewSensorsRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewerTable.setModel(new SensrosTabelModel());
				companiesTree.setSelectionRow(-1);
				companiesTree.updateUI();
				clearMachineFields();

			}
		});
		machinesSensorsViewPanel.add(viewSensorsRadioButton);

		radioButtonGroup.add(viewMachinesRadioButton);
		radioButtonGroup.add(viewSensorsRadioButton);

		leftSplitPane.add(machineFormPanel, JSplitPane.BOTTOM);
		leftPanel.add(leftSplitPane, BorderLayout.CENTER);

		centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(centerPanel, BorderLayout.CENTER);

		machinesModel = new MachinesTableModel();
		sensorsModel = new SensrosTabelModel();

		centerPanel.setLayout(new BorderLayout(0, 0));

		tablePanel = new JPanel();
		// centerPanel.add(tablePanel, BorderLayout.CENTER);

		viewerTable = new JXTable();
		viewerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleTableSelection(e);
			}
		});

		viewerTable.setFillsViewportHeight(true);
		viewerTable.setHighlighters(HighlighterFactory.createSimpleStriping());
		viewerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		viewerTable.setAutoCreateRowSorter(true);
		viewerTable.setSortable(true);
		viewerTable.setColumnControlVisible(true);
		viewerTable.setHorizontalScrollEnabled(true);
		viewerTable.setRowHeight(25);

		tablePanel.setLayout(new BorderLayout(0, 0));
		tablePanel.add(new JScrollPane(viewerTable));

		sensorsPanel = new JPanel();
		sensorsPanel.setPreferredSize(new Dimension(10, 150));
		sensorsPanel.setLayout(new BorderLayout(0, 0));

		sensorsFormPanel = new JPanel();
		sensorsFormPanel.setBorder(new TitledBorder(null, "Sensor Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sensorsPanel.add(sensorsFormPanel, BorderLayout.CENTER);
		sensorsFormPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("center:pref"),
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(1dlu;default)"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
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
								FormFactory.DEFAULT_ROWSPEC, }));

		sensorCmsLbl = new JLabel("CMS ID:");
		sensorsFormPanel.add(sensorCmsLbl, "2, 2, left, default");

		sensorCmsField = new JTextField();
		sensorCmsField.setEditable(false);
		sensorCmsField.setEnabled(false);
		sensorsFormPanel.add(sensorCmsField, "4, 2, left, default");
		sensorCmsField.setColumns(10);

		sensorCodeLbl = new JLabel("Code:");
		sensorsFormPanel.add(sensorCodeLbl, "6, 2, left, default");

		sensorCodeField = new JTextField();
		sensorCodeField.addMouseListener(this);

		sensorCodeField.getDocument().addDocumentListener(sensorDataReady);
		sensorsFormPanel.add(sensorCodeField, "8, 2, fill, default");
		sensorCodeField.setColumns(20);

		sensorDescriptionLbl = new JLabel("Description:");
		sensorsFormPanel.add(sensorDescriptionLbl, "10, 2, left, default");

		sensorDescriptionField = new JTextField();
		sensorDescriptionField.getDocument().addDocumentListener(
				sensorDataReady);

		sensorDescriptionField.addMouseListener(this);
		sensorsFormPanel.add(sensorDescriptionField, "11, 2, fill, default");
		sensorDescriptionField.setColumns(20);

		sensorSerialLbl = new JLabel("Serial #:");
		sensorsFormPanel.add(sensorSerialLbl, "2, 4, left, default");

		sensorSerialField = new JTextField();
		sensorSerialField.getDocument().addDocumentListener(sensorDataReady);

		sensorSerialField.addMouseListener(this);

		sensorsFormPanel.add(sensorSerialField, "4, 4, fill, default");
		sensorSerialField.setColumns(20);

		sensorManufactruerLbl = new JLabel("Manufactruer:");
		sensorsFormPanel.add(sensorManufactruerLbl, "6, 4, right, default");

		sensorManufactruerField = new JTextField();
		sensorManufactruerField.getDocument().addDocumentListener(
				sensorDataReady);

		sensorManufactruerField.addMouseListener(this);

		sensorsFormPanel.add(sensorManufactruerField, "8, 4, fill, default");
		sensorManufactruerField.setColumns(20);

		sensorTypeLbl = new JLabel("Type:");
		sensorsFormPanel.add(sensorTypeLbl, "10, 4, left, default");

		sensorTypeComboBox = new JComboBox<JobType>();
		sensorTypeComboBox.setModel(sensorJobTypesComboBoxModel);
		sensorTypeComboBox.addActionListener(sensorDataReady);
		sensorsFormPanel.add(sensorTypeComboBox, "11, 4, fill, default");

		sensorModelLbl = new JLabel("Model:");
		sensorsFormPanel.add(sensorModelLbl, "2, 6, left, default");

		sensorModelField = new JTextField();
		sensorModelField.addMouseListener(this);
		sensorModelField.getDocument().addDocumentListener(sensorDataReady);

		sensorsFormPanel.add(sensorModelField, "4, 6, fill, default");
		sensorModelField.setColumns(20);

		sensorCompanyLbl = new JLabel("Company:");
		sensorsFormPanel.add(sensorCompanyLbl, "6, 6, left, default");

		sensorCompanyComboBox = new JComboBox();
		sensorCompanyComboBox.setModel(sensorCompaniesComboBoxModel);
		sensorCompanyComboBox.addActionListener(sensorDataReady);
		sensorCompanyComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Company selectedCompany = (Company) e.getItem();
					List<Department> departmentsList = new CompanyTask()
							.getAllDepartments(selectedCompany);
					if (departmentsList.size() > 0) {
						sensorDepartmentComboBoxModel
								.setDepartmentsList(departmentsList);
						sensorDepartmentComboBox.setSelectedIndex(-1);
						sensorLocationComboBox
								.setModel(new LocationsComboBoxModel());
						sensorMachineComboBox
								.setModel(new MachinesComboBoxModel());
					} else {
						sensorDepartmentComboBox
								.setModel(new DepartmentsComboBoxModel());
						sensorLocationComboBox
								.setModel(new LocationsComboBoxModel());
						sensorMachineComboBox
								.setModel(new MachinesComboBoxModel());
						updateUI();
					}
				}
			}
		});

		sensorsFormPanel.add(sensorCompanyComboBox, "8, 6, fill, default");

		sensorDepartmentLbl = new JLabel("Department:");
		sensorsFormPanel.add(sensorDepartmentLbl, "10, 6, left, default");

		sensorDepartmentComboBox = new JComboBox();
		sensorDepartmentComboBox.setModel(sensorDepartmentComboBoxModel);
		sensorDepartmentComboBox.addActionListener(sensorDataReady);
		sensorDepartmentComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Department selectedDepartment = (Department) e.getItem();
					List<Location> locationsList = new DepartmentTask()
							.getAllLocation(selectedDepartment);
					if (locationsList.size() > 0) {

						sensorLocationsComboBoxModel
								.setLocationsList(locationsList);
						sensorLocationComboBox
								.setModel(sensorLocationsComboBoxModel);
						sensorLocationComboBox.setSelectedIndex(-1);
					} else {
						sensorLocationComboBox
								.setModel(new LocationsComboBoxModel());
						sensorMachineComboBox
								.setModel(new MachinesComboBoxModel());
						updateUI();
					}
				}

			}
		});

		sensorsFormPanel.add(sensorDepartmentComboBox, "11, 6, fill, default");

		sensorLocationLbl = new JLabel("Location:");
		sensorsFormPanel.add(sensorLocationLbl, "2, 8, left, default");

		sensorLocationComboBox = new JComboBox();
		sensorLocationComboBox.setModel(sensorLocationsComboBoxModel);
		sensorLocationComboBox.addActionListener(sensorDataReady);
		sensorLocationComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int stateChange = e.getStateChange();
				if (stateChange == ItemEvent.SELECTED) {
					Location selectedLocation = (Location) e.getItem();
					List<Machine> machinesList = new LocationTask()
							.getAllMAchines(selectedLocation);
					if (machinesList.size() > 0) {
						sensorMachinesComboBoxModel
								.setMachinesList(machinesList);
						sensorMachineComboBox
								.setModel(sensorMachinesComboBoxModel);
						sensorMachineComboBox.setSelectedIndex(-1);
					} else {
						sensorMachineComboBox
								.setModel(new MachinesComboBoxModel());
						updateUI();
					}
				}
			}
		});

		sensorsFormPanel.add(sensorLocationComboBox, "4, 8, fill, default");

		sensorMachineLbl = new JLabel("Machine:");
		sensorsFormPanel.add(sensorMachineLbl, "6, 8, left, default");

		sensorMachineComboBox = new JComboBox<Machine>();
		sensorMachineComboBox.setModel(sensorMachinesComboBoxModel);
		sensorMachineComboBox.addActionListener(sensorDataReady);
		sensorsFormPanel.add(sensorMachineComboBox, "8, 8, fill, default");

		sensorResolutionLbl = new JLabel("Resolution:");
		sensorsFormPanel.add(sensorResolutionLbl, "10, 8, left, default");

		sensorResolutionField = new JSpinner();
		sensorResolutionField.setModel(new SpinnerNumberModel(
				new Double(0.001), null, null, new Double(0.001)));
		sensorResolutionField.addChangeListener(sensorDataReady);
		sensorsFormPanel.add(sensorResolutionField, "11, 8");

		sensorCalibratedCheckBox = new JCheckBox("");
		sensorCalibratedCheckBox.addChangeListener(sensorDataReady);
		sensorCalibratedCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sensorCalibratedCheckBox.isSelected() == true) {
					sensorCalDateField.setEnabled(true);
					sensorCalDueDateField.setEnabled(true);
				} else if (sensorCalibratedCheckBox.isSelected() == false) {
					sensorCalDateField.setEnabled(false);
					sensorCalDateField.setDate(null);
					sensorCalDueDateField.setEnabled(false);
					sensorCalDueDateField.setDate(null);
				}

			}
		});

		sensorCalibratedCheckBox.addPropertyChangeListener("checkBoxSelected",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						String propertyName = e.getPropertyName();
						System.out.println(propertyName);
						if (propertyName.equals("checkBoxSelected")) {
							sensorCalibratedCheckBox.setSelected(true);
							sensorCalDateField.setEnabled(true);
							sensorCalDueDateField.setEnabled(true);
						}
					}
				});

		sensorCalibratedLbl = new JLabel("Calibrated:");
		sensorsFormPanel.add(sensorCalibratedLbl, "2, 10, left, default");

		sensorsFormPanel.add(sensorCalibratedCheckBox, "4, 10");

		sensorCalDateLbl = new JLabel("Cal. Date:");
		sensorsFormPanel.add(sensorCalDateLbl, "6, 10, left, default");

		sensorCalDateField = new JXDatePicker();
		sensorCalDateField.setEnabled(false);
		sensorCalDateField.getEditor().setColumns(20);
		sensorCalDateField.addActionListener(sensorDataReady);
		sensorsFormPanel.add(sensorCalDateField, "8, 10");

		sensorCalDueDateLbl = new JLabel("Cal. Due date:");
		sensorsFormPanel.add(sensorCalDueDateLbl, "10, 10, left, default");

		sensorCalDueDateField = new JXDatePicker();
		sensorCalDueDateField.setEnabled(false);
		sensorCalDueDateField.addActionListener(sensorDataReady);
		sensorCalDueDateField.getEditor().setColumns(20);
		sensorsFormPanel.add(sensorCalDueDateField, "11, 10");

		sensorButtonPanel = new JPanel();
		sensorButtonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));
		sensorButtonPanel.getLayout();
		sensorsFormPanel.add(sensorButtonPanel, "11, 12, right, default");

		sensorDeleteButton = new JButton("Delete");
		sensorDeleteButton.setEnabled(false);
		sensorDeleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = viewerTable.getSelectedRow();
				System.out.println("Selected Row: " + selectedRow);
				if (selectedRow > 0) {
					Object valueAt = viewerTable.getModel().getValueAt(
							selectedRow, 0);
					System.out.println("Selected Row ID: " + valueAt);

					SensrosTabelModel model = (SensrosTabelModel) viewerTable
							.getModel();
					Sensor sensor2 = model.getSensorsList().get(selectedRow);
					System.out.println("Sensor id at selected Row: "
							+ sensor2.getSensorsID());
				}
			}
		});

		btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (radioButtonGroup.getSelection().getActionCommand()
						.equals("View Sensors")) {
					viewerTable.clearSelection();
					clearSensorFields();
				}
			}
		});
		sensorButtonPanel.add(btnNewButton);
		sensorButtonPanel.add(sensorDeleteButton);

		sensorCreateButton = new JButton("Create");
		sensorCreateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createSensor();
			}
		});
		sensorCreateButton.setEnabled(false);
		sensorButtonPanel.add(sensorCreateButton);

		sensorUpdateButton = new JButton("Update");
		sensorUpdateButton.setEnabled(false);
		sensorUpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateSensor();

			}
		});
		sensorButtonPanel.add(sensorUpdateButton);

		centerSplitPane = new JSplitPane();
		centerSplitPane.setDividerSize(8);
		centerSplitPane.setOneTouchExpandable(true);
		centerSplitPane.setEnabled(false);
		centerSplitPane.setResizeWeight(1.0);
		centerSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		centerSplitPane.add(tablePanel, JSplitPane.TOP);
		centerSplitPane.add(sensorsPanel, JSplitPane.BOTTOM);
		centerPanel.add(centerSplitPane, BorderLayout.CENTER);

		topPanel = new JPanel();
		topPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		searchPanel = new JPanel();
		searchPanel
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.add(searchPanel);

		searchByLabel = new JLabel("Search By:");
		searchPanel.add(searchByLabel);

		searchByComboBox = new JComboBox<String>();
		searchByComboBox.setEditable(true);
		AutoCompleteDecorator.decorate(searchByComboBox);
		searchPanel.add(searchByComboBox);
		searchByComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "CMS ID", "Code", "Name", "Model",
						"Manufacturer", "Serial Number", "Section", "Company",
						"Department", "Location" }));
		searchByComboBox.setMaximumRowCount(10);

		searchForLabel = new JLabel("For:");
		searchPanel.add(searchForLabel);

		searchField = new JTextField();
		searchPanel.add(searchField);
		searchField.getDocument().addDocumentListener(new EnableGoButton());
		searchField.addMouseListener(this);
		searchField.setColumns(70);

		goButton = new JButton("");
		goButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/search.png")));
		searchPanel.add(goButton);
		goButton.setEnabled(false);

		toolBar = new JToolBar();
		topPanel.add(toolBar, BorderLayout.NORTH);

		copyButton = new JButton("");
		copyButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/Copy/Copy_24x24.png")));
		toolBar.add(copyButton);

		pasteButton = new JButton("");
		pasteButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/Paste/Paste_24x24.png")));
		toolBar.add(pasteButton);

		cutButton = new JButton("");
		cutButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/Cut/Cut_24x24.png")));
		toolBar.add(cutButton);

		separator = new JSeparator();
		separator.setMaximumSize(new Dimension(5, 32767));
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		printButton = new JButton("");
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					viewerTable.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		printButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/Print/Print_24x24.png")));
		toolBar.add(printButton);

		exportButton = new JButton("");

		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				export();
			}
		});

		exportButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/microsoft_office_excel.png")));
		toolBar.add(exportButton);

		refreashButton = new JXButton();

		refreashButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshData();
			}
		});

		refreashButton.setIcon(new ImageIcon(MachinesPanel.class
				.getResource("/resources/Refresh/Refresh_24x24.png")));
		toolBar.add(refreashButton);

		progreesBarPanel = new JPanel();
		progreesBarPanel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		progreesBarPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		toolBar.add(progreesBarPanel);
		progreesBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(146, 20));
		progreesBarPanel.add(progressBar);
		progressBar.setMaximumSize(new Dimension(100, 14));
		progressBar.setEnabled(false);
		progressBar.setBorderPainted(false);
		/*
		 * goButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * searchCompany(); } });
		 */

		popupMenu = new JPopupMenu();

		cutItem = new JMenuItem();
		cutItem.setAction(machineNameField.getActionMap().get(
				"cut-to-clipboard"));
		cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		cutItem.setText("Cut");

		copyItem = new JMenuItem();
		copyItem.setAction(machineNameField.getActionMap().get(
				"copy-to-clipboard"));
		copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		copyItem.setText("Copy");

		pasteItem = new JMenuItem();
		pasteItem.setAction(machineNameField.getActionMap().get(
				"paste-from-clipboard"));
		pasteItem.setText("Paste");
		pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));

		popupMenu.add(copyItem);
		popupMenu.add(pasteItem);
		popupMenu.add(cutItem);

	}

	public JTable getTable() {
		return viewerTable;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JXTable table) {
		this.viewerTable = table;
	}

	/**
	 * @return the model
	 */
	public MachinesTableModel getMachinesModel() {
		return machinesModel;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setMachinesModel(MachinesTableModel model) {
		this.machinesModel = model;
	}

	private void createMachine() {

		String name = machineNameField.getText().trim();
		String code = machineCodeField.getText().trim();
		String model = machineModelField.getText().trim();
		String serial = machineSerialField.getText().trim();
		String section = machineSectionField.getText().trim();
		String manfacturer = machineManufacturerField.getText().trim();
		String description = machineDescriptionField.getText().trim();

		Department dep = (Department) machineDepartmentsComboBox
				.getSelectedItem();
		Location loc = (Location) machineLocationsComboBox.getSelectedItem();

		machine.setDepartment(dep);
		machine.setDescription(description);
		machine.setMachineCode(code);
		machine.setMachineName(name);
		machine.setManufacturer(manfacturer);
		machine.setModel(model);
		machine.setSection(section);
		machine.setSerialNumber(serial);
		machine.getDepartment().setCompany(company);
		machine.setLocation(loc);

		try {
			new MachineTask().saveMachine(machine);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(null, e.getCause().getMessage());
		}
		refreshData();
		initComoaniesTree(loadCompanies());
		clearMachineFields();

	}

	private void updateSensor() {

		Long id = Long.valueOf(sensorCmsField.getText());

		sensor = new SensorTask().getByID(id);

		boolean selected = sensorCalibratedCheckBox.isSelected();
		Date calDate = null;
		Date calDueDate = null;
		if (selected) {
			calDate = sensorCalDateField.getDate();
			calDueDate = sensorCalDueDateField.getDate();
		}

		String code = sensorCodeField.getText().trim();
		String manufacturer = sensorManufactruerField.getText().trim();
		String model = sensorModelField.getText().trim();
		String serial = sensorSerialField.getText().trim();
		String description = sensorDescriptionField.getText().trim();
		Double resolution = (Double) sensorResolutionField.getValue();
		JobType selectedType = (JobType) sensorTypeComboBox.getSelectedItem();
		Company selectedCompany = (Company) sensorCompanyComboBox
				.getSelectedItem();
		Department selectedDepartment = (Department) sensorDepartmentComboBox
				.getSelectedItem();
		Location selectedLocation = (Location) sensorLocationComboBox
				.getSelectedItem();
		Machine selectedMachine = (Machine) sensorMachineComboBox
				.getSelectedItem();

		sensor.setCalDate(calDate);
		sensor.setCalDueDate(calDueDate);
		sensor.setCalibrated(selected);
		sensor.setCode(code);
		sensor.setDescription(description);
		sensor.setJobType(selectedType);
		sensor.setMachine(selectedMachine);
		sensor.setManufacturer(manufacturer);
		sensor.setModel(model);
		sensor.setResolution(resolution);
		sensor.setSerialNumber(serial);
		sensor.getMachine().setDepartment(selectedDepartment);
		sensor.getMachine().getDepartment().setCompany(selectedCompany);
		sensor.getMachine().setLocation(selectedLocation);

		new SensorTask().update(sensor);
		handleTreeSelection();
		sensorDeleteButton.setEnabled(false);
		clearSensorFields();

	}

	/*
	 * private void searchCompany() { String value =
	 * searchField.getText().trim(); Pattern charsPattern =
	 * Pattern.compile("\\d");
	 * 
	 * ArrayList<Object> arrayList = new ArrayList<>(); int selectedIndex =
	 * comboBox.getSelectedIndex(); switch (selectedIndex) { case 0: Matcher
	 * matcher = charsPattern.matcher(value); if (!matcher.matches()) {
	 * appendToPane(textArea, "- " + value + " is not valid for as a CMS ID" +
	 * "\n", Color.RED); return; } else { long id = Long.valueOf(value); Company
	 * companyByID = companyTask.getByID(id); arrayList.add(companyByID);
	 * companiesModel.setCompaniesList(arrayList);
	 * companiesModel.fireTableDataChanged(); } break; case 1: String code =
	 * codeField.getText().trim(); Company companyByCode =
	 * companyTask.getByCode(code); arrayList.add(companyByCode);
	 * companiesModel.setCompaniesList(arrayList);
	 * companiesModel.fireTableDataChanged(); break; default: break; }
	 * 
	 * }
	 */

	/*
	 * private String validateCompany() {
	 * 
	 * Pattern numbersOnly = Pattern.compile("\\d"); Matcher phoneMatcher =
	 * numbersOnly.matcher(phoneField.getText().trim()); Matcher faxMatcher =
	 * numbersOnly.matcher(faxField.getText().trim());
	 * 
	 * if(phoneMatcher.matches() && faxMatcher.matches()){ return null; }else{
	 * return "- Check the phone or the fax please"; }
	 * 
	 * }
	 */

	private void clearSensorFields() {
		sensorCmsField.setText("");
		sensorCodeField.setText("");
		sensorSerialField.setText("");
		sensorModelField.setText("");
		sensorCalDateField.setDate(null);
		sensorCalDueDateField.setDate(null);
		sensorTypeComboBox.setSelectedItem(null);
		sensorCompanyComboBox.setSelectedItem(null);
		sensorDepartmentComboBox.getModel().setSelectedItem(null);
		sensorLocationComboBox.getModel().setSelectedItem(null);
		sensorMachineComboBox.getModel().setSelectedItem(null);
		sensorDescriptionField.setText("");
		sensorManufactruerField.setText("");
		sensorCalibratedCheckBox.setSelected(false);
		sensorResolutionField.setValue(0.000);
		sensorCalDateField.setEnabled(false);
		sensorCalDueDateField.setEnabled(false);
	}

	private void refreshData() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			List<Machine> list = null;

			@Override
			protected Void doInBackground() throws Exception {
				progressBar.setIndeterminate(true);
				list = new MachineTask().getAllMachines();
				return null;
			}

			@Override
			protected void done() {
				getMachinesModel().setMachinesList(list);
				getMachinesModel().fireTableDataChanged();
				progressBar.setIndeterminate(false);
			}
		};

		worker.execute();
	}

	private void clearMachineFields() {
		machineIdField.setText("");
		machineNameField.setText("");
		machineCodeField.setText("");
		machineModelField.setText("");
		machineManufacturerField.setText("");
		machineSerialField.setText("");
		machineSectionField.setText("");
		machineDescriptionField.setText("");
		machineCompaniesComboBox.getModel().setSelectedItem(null);
		machineDepartmentsComboBox.getModel().setSelectedItem(null);
		machineLocationsComboBox.getModel().setSelectedItem(null);

	}

	private void export() {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("Machines Data");
		createHeaderRow(workbook, sheet);
		populateData(workbook, sheet);
		saveData(workbook);
	}

	private void createHeaderRow(SXSSFWorkbook workbook, Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);

		Cell idHeader = headerRow.createCell(0);
		idHeader.setCellValue("CMS ID");
		idHeader.setCellStyle(style);

		Cell nameHeader = headerRow.createCell(1);
		nameHeader.setCellValue("Name");
		nameHeader.setCellStyle(style);

		Cell codeHeader = headerRow.createCell(2);
		codeHeader.setCellValue("Code");
		codeHeader.setCellStyle(style);

		Cell modelHeader = headerRow.createCell(3);
		modelHeader.setCellValue("Model");
		modelHeader.setCellStyle(style);

		Cell manufacturerHeader = headerRow.createCell(4);
		manufacturerHeader.setCellValue("Manufacturer");
		manufacturerHeader.setCellStyle(style);

		Cell serialHeader = headerRow.createCell(5);
		serialHeader.setCellValue("Serial Number");
		serialHeader.setCellStyle(style);

		Cell sectionHeader = headerRow.createCell(5);
		sectionHeader.setCellValue("Section");
		sectionHeader.setCellStyle(style);

		Cell companyHeader = headerRow.createCell(5);
		companyHeader.setCellValue("Company");
		companyHeader.setCellStyle(style);

		Cell departmentHeader = headerRow.createCell(5);
		departmentHeader.setCellValue("Department");
		departmentHeader.setCellStyle(style);

		Cell locationHeader = headerRow.createCell(5);
		locationHeader.setCellValue("Location");
		locationHeader.setCellStyle(style);

		Cell sensorsCountHeader = headerRow.createCell(5);
		sensorsCountHeader.setCellValue("Sensors Inside");
		sensorsCountHeader.setCellStyle(style);
	}

	private void populateData(SXSSFWorkbook workbook, Sheet sheet) {

		progressBar.setMinimum(0);

		int index = 1;

		Iterator<Object> iterator = machinesModel.getMachinesList().iterator();
		progressBar.setMaximum(machinesModel.getMachinesList().size());

		while (iterator.hasNext()) {
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);

			Machine m = (Machine) iterator.next();
			Row createRow = sheet.createRow(index);

			Cell idHeader = createRow.createCell(0);
			idHeader.setCellValue(m.getMachineID());
			idHeader.setCellStyle(style);

			Cell nameHeader = createRow.createCell(1);
			nameHeader.setCellValue(m.getMachineName());
			nameHeader.setCellStyle(style);

			Cell codeHeader = createRow.createCell(2);
			codeHeader.setCellValue(m.getMachineCode());
			codeHeader.setCellStyle(style);

			Cell modelHeader = createRow.createCell(3);
			modelHeader.setCellValue(m.getModel());
			modelHeader.setCellStyle(style);

			Cell manufacturerHeader = createRow.createCell(4);
			manufacturerHeader.setCellValue(m.getManufacturer());
			manufacturerHeader.setCellStyle(style);

			Cell serialHeader = createRow.createCell(5);
			serialHeader.setCellValue(m.getSerialNumber());
			serialHeader.setCellStyle(style);

			Cell sectionHeader = createRow.createCell(5);
			sectionHeader.setCellValue(m.getSection());
			sectionHeader.setCellStyle(style);

			Cell companyHeader = createRow.createCell(5);
			companyHeader.setCellValue(m.getDepartment().getCompany()
					.getCompanyName());
			companyHeader.setCellStyle(style);

			Cell departmentHeader = createRow.createCell(5);
			departmentHeader.setCellValue(m.getDepartment().getName());
			departmentHeader.setCellStyle(style);

			Cell locationHeader = createRow.createCell(5);
			locationHeader.setCellValue(m.getLocation().getLocation());
			locationHeader.setCellStyle(style);

			Cell sensorsCount = createRow.createCell(5);
			sensorsCount.setCellValue(m.getSensorQuantity());
			sensorsCount.setCellStyle(style);

			progressBar.setValue(index);
			updateUI();
			index++;
		}

	}

	private boolean saveData(SXSSFWorkbook workbook) {
		JFileChooser saveChooser = new JFileChooser();
		saveChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		saveChooser.setSelectedFile(new File("Machines_data.xlsx"));
		int i = saveChooser.showSaveDialog(getParent());
		if (i == JFileChooser.APPROVE_OPTION) {
			FileOutputStream out = null;
			try {
				File selectedFile = saveChooser.getSelectedFile();
				out = new FileOutputStream(selectedFile);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
			try {
				workbook.write(out);
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
		}
		return false;
	}

	private void showPopupMenu(MouseEvent e) {
		int button = e.getButton();
		if (button == MouseEvent.BUTTON3) {
			JTextField textField = (JTextField) e.getComponent();
			textField.requestFocus();
			textField.selectAll();
			popupMenu.show(textField, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		showPopupMenu(e);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@SuppressWarnings("unused")
	private class EnableUpdateCreateButtons implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {

			if (machineNameField.getDocument().getLength() > 0
					&& machineCodeField.getDocument().getLength() > 0
					&& machineModelField.getDocument().getLength() > 0
					&& machineManufacturerField.getDocument().getLength() > 0
					&& machineSerialField.getDocument().getLength() > 0
					&& machineSectionField.getDocument().getLength() > 0
					&& machineCompaniesComboBox.getSelectedIndex() > 0
					&& machineDepartmentsComboBox.getSelectedIndex() > 0
					&& machineLocationsComboBox.getSelectedIndex() > 0) {
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			} else {
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			if (machineNameField.getDocument().getLength() > 0
					&& machineCodeField.getDocument().getLength() > 0
					&& machineModelField.getDocument().getLength() > 0
					&& machineManufacturerField.getDocument().getLength() > 0
					&& machineSerialField.getDocument().getLength() > 0
					&& machineSectionField.getDocument().getLength() > 0
					&& machineCompaniesComboBox.getSelectedIndex() > 0
					&& machineDepartmentsComboBox.getSelectedIndex() > 0
					&& machineLocationsComboBox.getSelectedIndex() > 0) {
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			} else {
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (machineNameField.getDocument().getLength() > 0
					&& machineCodeField.getDocument().getLength() > 0
					&& machineModelField.getDocument().getLength() > 0
					&& machineManufacturerField.getDocument().getLength() > 0
					&& machineSerialField.getDocument().getLength() > 0
					&& machineSectionField.getDocument().getLength() > 0
					&& machineCompaniesComboBox.getSelectedIndex() > 0
					&& machineDepartmentsComboBox.getSelectedIndex() > 0
					&& machineLocationsComboBox.getSelectedIndex() > 0) {
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			} else {
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}
	}

	private class EnableGoButton implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {

			goButton.setEnabled(e.getDocument().getLength() > 0);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			goButton.setEnabled(e.getDocument().getLength() > 0);

		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			goButton.setEnabled(e.getDocument().getLength() > 0);

		}

	}

	private DefaultMutableTreeNode createCompaniesTree(
			List<Company> companiesList) {
		List<Company> companies = companiesList;
		DefaultMutableTreeNode companiesRoot = new DefaultMutableTreeNode(
				"Companies");

		int companiesSize = companies.size();
		int index = 0;
		DefaultMutableTreeNode companyNode;
		DefaultMutableTreeNode depNode;
		DefaultMutableTreeNode locNode;
		while (index < companiesSize) {
			Company company = companies.get(index);
			companyNode = new DefaultMutableTreeNode(company);
			companiesRoot.add(companyNode);

			List<Department> departments = new CompanyTask()
					.getAllDepartments(company);
			int depSize = departments.size();
			int depIndex = 0;

			while (depIndex < depSize) {
				Department dep = departments.get(depIndex);
				depNode = new DefaultMutableTreeNode(dep);
				companyNode.add(depNode);
				depIndex++;

				List<Location> locations = new DepartmentTask()
						.getAllLocation(dep);
				int locSize = locations.size();
				int locIndex = 0;

				while (locIndex < locSize) {
					Location loction = locations.get(locIndex);
					locNode = new DefaultMutableTreeNode(loction);
					depNode.add(locNode);
					locIndex++;

					List<Machine> machines = new LocationTask()
							.getAllMAchines(loction);

					int machinesSize = machines.size();
					int machineIndex = 0;

					while (machineIndex < machinesSize) {
						Machine machine = machines.get(machineIndex);
						DefaultMutableTreeNode machineNode = new DefaultMutableTreeNode(
								machine);
						locNode.add(machineNode);
						machineIndex++;
					}
				}
			}

			index++;
		}
		return companiesRoot;
	}

	private void handleTableSelection(MouseEvent e) {
		int clickedButton = e.getButton();
		JXTable source = (JXTable) e.getSource();
		if (clickedButton == MouseEvent.BUTTON1
				&& radioButtonGroup.getSelection().getActionCommand() == "View Sensors") {
			int selectedRow = source.getSelectedRow();
			if (selectedRow >= 0) {
				sensorDeleteButton.setEnabled(true);
			}
			Long sensorId = (Long) source.getModel().getValueAt(selectedRow, 0);

			sensor = new SensorTask().getByID(sensorId);

			sensorCmsField.setText(String.valueOf(sensor.getSensorsID()));
			sensorCodeField.setText(sensor.getCode());
			sensorDescriptionField.setText(sensor.getDescription());
			sensorSerialField.setText(sensor.getSerialNumber());
			sensorManufactruerField.setText(sensor.getManufacturer());
			sensorModelField.setText(sensor.getModel());
			sensorCalDateField.setDate(sensor.getCalDate());
			sensorCalDueDateField.setDate(sensor.getCalDueDate());
			sensorTypeComboBox.setSelectedItem(sensor.getJobType());
			sensorCompanyComboBox.setSelectedItem(sensor.getMachine()
					.getDepartment().getCompany());
			sensorDepartmentComboBox.getModel().setSelectedItem(
					sensor.getMachine().getDepartment());
			sensorLocationComboBox.getModel().setSelectedItem(
					sensor.getMachine().getLocation());
			sensorMachineComboBox.getModel().setSelectedItem(
					sensor.getMachine());
			boolean calibrated = sensor.isCalibrated();
			if (calibrated) {
				sensorCalibratedCheckBox.firePropertyChange("checkBoxSelected",
						sensorCalibratedCheckBox.isSelected(), calibrated);
			} else {
				sensorCalDateField.setEnabled(false);
				sensorCalDueDateField.setEnabled(false);
			}

			sensorCalibratedCheckBox.setSelected(calibrated);
			sensorResolutionField.setValue(sensor.getResolution());
		} else if (clickedButton == MouseEvent.BUTTON1
				&& radioButtonGroup.getSelection().getActionCommand() == "View Machines") {
			int selectedRow = source.getSelectedRow();
			Long machineId = (Long) source.getModel()
					.getValueAt(selectedRow, 0);

			machine = new MachineTask().getByID(machineId);
			machineCodeField.setText(machine.getMachineCode());
			machineCompaniesComboBox.getModel().setSelectedItem(
					machine.getDepartment().getCompany());
			machineDepartmentsComboBox.getModel().setSelectedItem(
					machine.getDepartment());
			machineDescriptionField.setText(machine.getDescription());
			machineIdField.setText(String.valueOf(machine.getMachineID()));
			machineLocationsComboBox.getModel().setSelectedItem(
					machine.getLocation());
			machineSerialField.setText(machine.getSerialNumber());
			machineSectionField.setText(machine.getSection());
			machineNameField.setText(machine.getMachineName());
			machineModelField.setText(machine.getModel());
			machineManufacturerField.setText(machine.getManufacturer());

		}

	}

	private void handleTreeSelection() {

		if (radioButtonGroup.getSelection().getActionCommand() == "View Machines") {
			List<Machine> machinesList = new ArrayList<>();
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) companiesTree
					.getLastSelectedPathComponent();

			Object selectedObject = selection.getUserObject();

			if (selectedObject instanceof Company) {

				Company selectedCompany = (Company) selectedObject;
				List<Department> departmentsList = new CompanyTask()
						.getAllDepartments(selectedCompany);
				Iterator<Department> depIterator = departmentsList.iterator();
				while (depIterator.hasNext()) {
					Department next = depIterator.next();
					List<Location> locationsList = new DepartmentTask()
							.getAllLocation(next);
					Iterator<Location> locationIterator = locationsList
							.iterator();
					while (locationIterator.hasNext()) {
						Location location = locationIterator.next();
						List<Machine> machines = new LocationTask()
								.getAllMAchines(location);
						machinesList.addAll(machines);
					}
				}

				machinesModel.setMachinesList(machinesList);
				viewerTable.setModel(machinesModel);
				viewerTable.updateUI();
				viewerTable.repaint();

			} else if (selectedObject instanceof Department) {
				Department department = (Department) selectedObject;
				List<Location> locationsList = new DepartmentTask()
						.getAllLocation(department);
				Iterator<Location> locationIterator = locationsList.iterator();
				while (locationIterator.hasNext()) {
					Location location = locationIterator.next();
					List<Machine> machines = new LocationTask()
							.getAllMAchines(location);
					machinesList.addAll(machines);
				}

				machinesModel.setMachinesList(machinesList);
				viewerTable.setModel(machinesModel);
				viewerTable.updateUI();
				viewerTable.repaint();
			} else if (selectedObject instanceof Location) {
				Location selectedLocation = (Location) selectedObject;
				List<Machine> machines = new LocationTask()
						.getAllMAchines(selectedLocation);
				machinesModel.setMachinesList(machines);
				viewerTable.setModel(machinesModel);
				viewerTable.updateUI();
				viewerTable.repaint();
			} else if (selectedObject instanceof Machine) {

				machine = (Machine) selectedObject;
				machineIdField.setText(String.valueOf(machine.getMachineID()));
				machineNameField.setText(machine.getMachineName());
				machineDescriptionField.setText(machine.getDescription());
				machineSectionField.setText(machine.getSection());
				machineSerialField.setText(machine.getSerialNumber());
				machineManufacturerField.setText(machine.getManufacturer());
				machineModelField.setText(machine.getModel());
				machineCodeField.setText(machine.getMachineCode());
				machineCompaniesComboBox.setSelectedItem(machine
						.getDepartment().getCompany());
				machineDepartmentsComboBox.getModel().setSelectedItem(machine
						.getDepartment());
				machineLocationsComboBox.getModel().setSelectedItem(machine.getLocation());

				viewerTable.setModel(new DefaultTableModel());
				viewerTable.updateUI();
				viewerTable.repaint();
			}
		} else if (radioButtonGroup.getSelection().getActionCommand() == "View Sensors") {
			List<Sensor> sensorsList = new ArrayList<>();
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) companiesTree
					.getLastSelectedPathComponent();

			Object selectedObject = selection.getUserObject();

			if (selectedObject instanceof Company) {

				Company selectedCompany = (Company) selectedObject;
				List<Department> departmentsList = new CompanyTask()
						.getAllDepartments(selectedCompany);
				Iterator<Department> depIterator = departmentsList.iterator();
				while (depIterator.hasNext()) {
					Department next = depIterator.next();
					List<Location> locationsList = new DepartmentTask()
							.getAllLocation(next);
					Iterator<Location> locationIterator = locationsList
							.iterator();
					while (locationIterator.hasNext()) {
						Location location = locationIterator.next();
						List<Machine> machines = new LocationTask()
								.getAllMAchines(location);
						Iterator<Machine> machinesIterator = machines
								.iterator();
						while (machinesIterator.hasNext()) {
							Machine machine = machinesIterator.next();
							List<Sensor> sensors = new SensorTask()
									.getAllSensors(machine);
							sensorsList.addAll(sensors);
						}
					}
				}

				sensorsModel.setSensorsList(sensorsList);
				viewerTable.setModel(sensorsModel);
				viewerTable.updateUI();
				viewerTable.repaint();

			} else if (selectedObject instanceof Department) {
				Department department = (Department) selectedObject;
				List<Location> locationsList = new DepartmentTask()
						.getAllLocation(department);
				Iterator<Location> locationIterator = locationsList.iterator();
				while (locationIterator.hasNext()) {
					Location location = locationIterator.next();
					List<Machine> machineList = new LocationTask()
							.getAllMAchines(location);
					Iterator<Machine> machineIterator = machineList.iterator();
					while (machineIterator.hasNext()) {
						Machine machine = machineIterator.next();
						List<Sensor> sensors = new SensorTask()
								.getAllSensors(machine);
						sensorsList.addAll(sensors);
					}
				}
				sensorsModel.setSensorsList(sensorsList);
				viewerTable.setModel(sensorsModel);
				viewerTable.updateUI();
				viewerTable.repaint();

			} else if (selectedObject instanceof Location) {

				Location selectedLocation = (Location) selectedObject;
				List<Machine> machines = new LocationTask()
						.getAllMAchines(selectedLocation);
				Iterator<Machine> machinesIterator = machines.iterator();
				while (machinesIterator.hasNext()) {
					Machine machine = machinesIterator.next();
					List<Sensor> sensors = new SensorTask()
							.getAllSensors(machine);
					sensorsList.addAll(sensors);
				}
				sensorsModel.setSensorsList(sensorsList);
				viewerTable.setModel(sensorsModel);
				viewerTable.updateUI();
				viewerTable.repaint();
			} else if (selectedObject instanceof Machine) {
				Machine machine = (Machine) selectedObject;
				List<Sensor> sensors = new SensorTask().getAllSensors(machine);
				sensorsModel.setSensorsList(sensors);
				viewerTable.setModel(sensorsModel);
				viewerTable.updateUI();
				viewerTable.repaint();

			}
		}

	}

	public Sensor getSensor() {
		return sensor;
	}

	public SensrosTabelModel getSensorsModel() {
		return sensorsModel;
	}

	public void setSensorsModel(SensrosTabelModel sensorsModel) {
		this.sensorsModel = sensorsModel;
	}

	public Company getCompany() {
		return company;
	}

	/**
	 * @return the companiesComboBoxModel
	 */
	public CompaniesComboBoxModel getSensorCompaniesComboBoxModel() {
		return sensorCompaniesComboBoxModel;
	}

	/**
	 * @param companiesComboBoxModel
	 *            the companiesComboBoxModel to set
	 */
	public void setSensorCompaniesComboBoxModel(
			CompaniesComboBoxModel companiesComboBoxModel) {
		this.sensorCompaniesComboBoxModel = companiesComboBoxModel;
	}

	public CompaniesComboBoxModel getMachineCompaniesComboBoxModel() {
		return machineCompaniesComboBoxModel;
	}

	/**
	 * @param companiesComboBoxModel
	 *            the companiesComboBoxModel to set
	 */
	public void setMachineCompaniesComboBoxModel(
			CompaniesComboBoxModel companiesComboBoxModel) {
		this.machineCompaniesComboBoxModel = companiesComboBoxModel;
	}

	public DepartmentsComboBoxModel getDepartmentComboBoxModel() {
		return sensorDepartmentComboBoxModel;
	}

	public void setDepartmentComboBoxModel(
			DepartmentsComboBoxModel departmentComboBoxModel) {
		this.sensorDepartmentComboBoxModel = departmentComboBoxModel;
	}

	public LocationsComboBoxModel getLocationsComboBoxModel() {
		return sensorLocationsComboBoxModel;
	}

	public void setLocationsComboBoxModel(
			LocationsComboBoxModel locationsComboBoxModel) {
		this.sensorLocationsComboBoxModel = locationsComboBoxModel;
	}

	/**
	 * @return the jobTypesComboBoxModel
	 */
	public JobTypesComboBoxModel getJobTypesComboBoxModel() {
		return sensorJobTypesComboBoxModel;
	}

	/**
	 * @param jobTypesComboBoxModel
	 *            the jobTypesComboBoxModel to set
	 */
	public void setJobTypesComboBoxModel(
			JobTypesComboBoxModel jobTypesComboBoxModel) {
		this.sensorJobTypesComboBoxModel = jobTypesComboBoxModel;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Company> getCompaniesList() {
		return companiesList;
	}

	public void setCompaniesList(List<Company> companiesList) {
		this.companiesList = companiesList;
	}

	public void initComoaniesTree(final List<Company> list) {
		SwingWorker<Void, Void> treeWorker = new SwingWorker<Void, Void>() {
			
			/* (non-Javadoc)
			 * @see javax.swing.SwingWorker#done()
			 */
			@Override
			protected void done() {
				MachinesPanel.this.updateUI();
				MachinesPanel.this.repaint();
			}

			@Override
			protected Void doInBackground() throws Exception {
				companiesViewPanel.removeAll();
				companiesTree = new JXTree(createCompaniesTree(list));
				companiesTree.setOverwriteRendererIcons(true);
				companiesTree.setScrollsOnExpand(true);
				companiesTree.setRolloverEnabled(true);
				companiesTree.addTreeSelectionListener(new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {

						handleTreeSelection();

					}
				});

				JScrollPane treeScrollPane = new JScrollPane(companiesTree);
				companiesViewPanel.add(treeScrollPane, BorderLayout.CENTER);
				return null;
			}
		};
		
		treeWorker.execute();
	}

	private class SensorDataReady implements DocumentListener, ActionListener,
			ChangeListener {

		private boolean sensorFieldsValid() {
			boolean sensorCodeFieldEmpty = sensorCodeField.getText().isEmpty();

			boolean sensorCalDateEmpty = (sensorCalDateField.getDate() == null);

			boolean sensorCalDueDateEmpty = (sensorCalDueDateField.getDate() == null);

			boolean sensorCompanyComboBoxEmpty = (sensorCompanyComboBox
					.getModel().getSelectedItem() == null);

			boolean sensorDepartmentComboBoxEmpty = (sensorDepartmentComboBox
					.getModel().getSelectedItem() == null);

			boolean sensorDescriptionFieldEmpty = sensorDescriptionField
					.getText().isEmpty();

			boolean sensorLocationComboBoxEmpty = (sensorLocationComboBox
					.getModel().getSelectedItem() == null);

			boolean sensorMachineComboBoxEmpty = (sensorMachineComboBox
					.getModel().getSelectedItem() == null);

			boolean sensorManufactruerFieldEmpty = sensorManufactruerField
					.getText().isEmpty();

			boolean sensorModelFieldEmpty = sensorModelField.getText()
					.isEmpty();

			boolean sensorResolutionFieldEmpty = ((Double) sensorResolutionField
					.getValue() == 0);

			boolean sensorSerialFieldEmpty = sensorSerialField.getText()
					.isEmpty();

			boolean sensorTypeComboBoxEmpty = (sensorTypeComboBox.getModel()
					.getSelectedItem() == null);

			if (sensorCalibratedCheckBox.isSelected() == true) {
				if (sensorCodeFieldEmpty == false
						&& sensorDescriptionFieldEmpty == false
						&& sensorManufactruerFieldEmpty == false
						&& sensorModelFieldEmpty == false
						&& sensorResolutionFieldEmpty == false
						&& sensorSerialFieldEmpty == false
						&& sensorTypeComboBoxEmpty == false
						&& sensorMachineComboBoxEmpty == false
						&& sensorLocationComboBoxEmpty == false
						&& sensorDepartmentComboBoxEmpty == false
						&& sensorCalDateEmpty == false
						&& sensorCalDueDateEmpty == false
						&& sensorCompanyComboBoxEmpty == false) {
					return true;
				} else {
					return false;
				}
			} else if (sensorCalibratedCheckBox.isSelected() == false) {
				if (sensorCodeFieldEmpty == false
						&& sensorDescriptionFieldEmpty == false
						&& sensorManufactruerFieldEmpty == false
						&& sensorModelFieldEmpty == false
						&& sensorResolutionFieldEmpty == false
						&& sensorSerialFieldEmpty == false
						&& sensorTypeComboBoxEmpty == false
						&& sensorMachineComboBoxEmpty == false
						&& sensorLocationComboBoxEmpty == false
						&& sensorDepartmentComboBoxEmpty == false
						&& sensorCompanyComboBoxEmpty == false) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			boolean sensorFieldsValid = sensorFieldsValid();
			if (sensorFieldsValid && viewSensorsRadioButton.isSelected()) {
				sensorUpdateButton.setEnabled(true);
				sensorCreateButton.setEnabled(true);
			} else {
				sensorUpdateButton.setEnabled(false);
				sensorCreateButton.setEnabled(false);

			}

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			boolean sensorFieldsValid = sensorFieldsValid();

			if (sensorFieldsValid && viewSensorsRadioButton.isSelected()) {
				sensorUpdateButton.setEnabled(true);
				sensorCreateButton.setEnabled(true);
			} else {
				sensorUpdateButton.setEnabled(false);
				sensorCreateButton.setEnabled(false);

			}

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {

			boolean sensorFieldsValid = sensorFieldsValid();

			if (sensorFieldsValid && viewSensorsRadioButton.isSelected()) {
				sensorUpdateButton.setEnabled(true);
				sensorCreateButton.setEnabled(true);
			} else {
				sensorUpdateButton.setEnabled(false);
				sensorCreateButton.setEnabled(false);

			}

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean sensorFieldsValid = sensorFieldsValid();
			if (sensorFieldsValid && viewSensorsRadioButton.isSelected()) {
				sensorUpdateButton.setEnabled(true);
				sensorCreateButton.setEnabled(true);
			} else {
				sensorUpdateButton.setEnabled(false);
				sensorCreateButton.setEnabled(false);
			}

		}

		@Override
		public void stateChanged(ChangeEvent arg0) {
			boolean sensorFieldsValid = sensorFieldsValid();
			if (sensorFieldsValid && viewSensorsRadioButton.isSelected()) {
				sensorUpdateButton.setEnabled(true);
				sensorCreateButton.setEnabled(true);
			} else {
				sensorUpdateButton.setEnabled(false);
				sensorCreateButton.setEnabled(false);

			}

		}

	}

	private void createSensor() {

		boolean selected = sensorCalibratedCheckBox.isSelected();
		Date calDate = null;
		Date calDueDate = null;
		if (selected) {
			calDate = sensorCalDateField.getDate();
			calDueDate = sensorCalDueDateField.getDate();
		}

		String code = sensorCodeField.getText().trim();
		String manufacturer = sensorManufactruerField.getText().trim();
		String model = sensorModelField.getText().trim();
		String serial = sensorSerialField.getText().trim();
		String description = sensorDescriptionField.getText().trim();
		Double resolution = (Double) sensorResolutionField.getValue();
		JobType selectedType = (JobType) sensorTypeComboBox.getSelectedItem();
		Company selectedCompany = (Company) sensorCompanyComboBox
				.getSelectedItem();
		Department selectedDepartment = (Department) sensorDepartmentComboBox
				.getSelectedItem();
		Location selectedLocation = (Location) sensorLocationComboBox
				.getSelectedItem();
		Machine selectedMachine = (Machine) sensorMachineComboBox
				.getSelectedItem();

		sensor.setCalDate(calDate);
		sensor.setCalDueDate(calDueDate);
		sensor.setCalibrated(selected);
		sensor.setCode(code);
		sensor.setDescription(description);
		sensor.setJobType(selectedType);
		sensor.setMachine(selectedMachine);
		sensor.setManufacturer(manufacturer);
		sensor.setModel(model);
		sensor.setResolution(resolution);
		sensor.setSerialNumber(serial);
		sensor.getMachine().setDepartment(selectedDepartment);
		sensor.getMachine().getDepartment().setCompany(selectedCompany);
		sensor.getMachine().setLocation(selectedLocation);

		try {
			new SensorTask().create(sensor);
			handleTreeSelection();
			clearSensorFields();
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(this, e.getCause().getMessage());
		}

	}

	private class MachineDataReady implements ChangeListener, ActionListener,
			DocumentListener {

		private boolean machineFieldsValid() {
			boolean nameEmpty = machineNameField.getText().isEmpty();
			boolean codeEmpty = machineCodeField.getText().isEmpty();
			boolean modelEmpty = machineModelField.getText().isEmpty();
			boolean serialEmpty = machineSerialField.getText().isEmpty();
			boolean sectionEmpty = machineSectionField.getText().isEmpty();
			boolean manfacturerEmpty = machineManufacturerField.getText()
					.isEmpty();
			boolean descriptionEmpty = machineDescriptionField.getText()
					.isEmpty();

			boolean depEmpty = ((Department) machineDepartmentsComboBox
					.getSelectedItem() == null);
			boolean locEmpty = ((Location) machineLocationsComboBox
					.getSelectedItem() == null);
			if (nameEmpty == false && codeEmpty == false && modelEmpty == false
					&& serialEmpty == false && sectionEmpty == false
					&& manfacturerEmpty == false && descriptionEmpty == false
					&& depEmpty == false && locEmpty == false) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			boolean machineFieldsValid = machineFieldsValid();
			if(machineFieldsValid == true){
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			}else{
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			boolean machineFieldsValid = machineFieldsValid();
			if(machineFieldsValid == true){
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			}else{
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			boolean machineFieldsValid = machineFieldsValid();
			if(machineFieldsValid == true){
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			}else{
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean machineFieldsValid = machineFieldsValid();
			if(machineFieldsValid == true){
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			}else{
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

		@Override
		public void stateChanged(ChangeEvent arg0) {
			boolean machineFieldsValid = machineFieldsValid();
			if(machineFieldsValid == true){
				machineCreateButton.setEnabled(true);
				machineUpdateButton.setEnabled(true);
			}else{
				machineCreateButton.setEnabled(false);
				machineUpdateButton.setEnabled(false);
			}
		}

	}
	
	private List<Company> loadCompanies(){
		return new CompanyTask().getAllCompanies();
	}

}
