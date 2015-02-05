package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import listModels.DepartmentListModel;
import model.Company;
import model.Department;
import model.Location;

import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import tableModels.LocationsTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import comboModels.CompaniesComboBoxModel;
import comboModels.DepartmentsComboBoxModel;

import dao.CompanyTask;
import dao.DepartmentTask;
import dao.LocationTask;

public class DepartmentsPanel extends JPanel {

	private static final long serialVersionUID = 7835680668398161093L;

	private DepartmentListModel departmentListModel;
	private DepartmentsComboBoxModel departmentsComboBoxModel;
	private CompaniesComboBoxModel companiesComboBoxModel;
	private CompaniesComboBoxModel locationCompaniesComboBoxModel;
	private LocationsTableModel locationsTableModel;
	private JTextField locationNameField;
	private JTextField locationDescriptionField;

	private JXList departmentsList;
	private JComboBox<Company> companiesComboBox;
	private JComboBox<Company> locationCompanyComboBox;
	private JComboBox<Department> locationDepComboBox;
	private JXTable locationsTable;

	private JTextField depNameField;

	private JTextField depDescriptionField;

	private JButton locationUpdateButton;

	private JButton locationCreateButton;

	private JButton locationResetButton;

	private JButton depCreateButton;

	private JButton depResetButton;

	private JButton depUpdateButton;

	/**
	 * Create the panel.
	 */
	public DepartmentsPanel() {

		IsValid depIsValid = new IsValid(0);
		IsValid locIsValid = new IsValid(1);

		setLayout(new BorderLayout(0, 0));

		departmentListModel = new DepartmentListModel();
		departmentsComboBoxModel = new DepartmentsComboBoxModel();
		companiesComboBoxModel = new CompaniesComboBoxModel();
		locationCompaniesComboBoxModel = new CompaniesComboBoxModel();
		locationsTableModel = new LocationsTableModel();

		JPanel departmentPanel = new JPanel();
		departmentPanel.setPreferredSize(new Dimension(300, 10));
		add(departmentPanel, BorderLayout.WEST);
		departmentPanel.setLayout(new BorderLayout(0, 0));

		JPanel companiesPanel = new JPanel();
		companiesPanel.setPreferredSize(new Dimension(10, 50));
		companiesPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Companies",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		departmentPanel.add(companiesPanel, BorderLayout.NORTH);

		companiesComboBox = new JComboBox<Company>(companiesComboBoxModel);
		companiesComboBox.setPreferredSize(new Dimension(230, 20));
		companiesComboBox.setName("Select Company");
		companiesComboBox.addActionListener(depIsValid);
		companiesComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					Company selectedCompany = (Company) e.getItem();
					List<Department> departments = new CompanyTask()
							.getAllDepartments(selectedCompany);
					DepartmentListModel model = new DepartmentListModel();
					model.setDepartmentsList(departments);
					departmentsList.setModel(model);
					depNameField.setText("");
					depDescriptionField.setText("");
				}
			}
		});

		companiesPanel.setLayout(new BorderLayout(0, 0));
		companiesPanel.add(companiesComboBox);

		JPanel departmentsPanel = new JPanel();
		departmentsPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Departments View",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		departmentPanel.add(departmentsPanel, BorderLayout.CENTER);
		departmentsPanel.setLayout(new BorderLayout(0, 0));

		departmentsList = new JXList(departmentListModel);
		departmentsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				List<Location> locations = new ArrayList<>();

				Object[] selectedDepartments = departmentsList
						.getSelectedValues();

				int length = selectedDepartments.length;

				for (Object department : selectedDepartments) {
					Department d = (Department) department;
					List<Location> depLocations = new DepartmentTask()
							.getAllLocation(d);
					locations.addAll(depLocations);

					if (length == 1) {
						depNameField.setText(d.getName());
						depDescriptionField.setText(d.getDescription());
					} else {
						depNameField.setText("");
						depDescriptionField.setText("");
					}
				}

				locationsTableModel.setLocationsList(locations);

			}
		});
		departmentsList.setToolTipText("CTRL + A to select all department");
		departmentsList.setBackground(Color.WHITE);
		departmentsPanel.add(new JScrollPane(departmentsList));

		JPanel newDepartmentPanel = new JPanel();
		newDepartmentPanel.setBorder(new TitledBorder(null,
				"Create new department", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		newDepartmentPanel.setPreferredSize(new Dimension(10, 115));
		departmentPanel.add(newDepartmentPanel, BorderLayout.SOUTH);
		newDepartmentPanel.setLayout(new BorderLayout(0, 0));

		JPanel newDepartmentButtonsPanel = new JPanel();
		newDepartmentPanel.add(newDepartmentButtonsPanel, BorderLayout.SOUTH);

		depResetButton = new JButton("Reset");
		depResetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset(0);

			}
		});
		depResetButton.setEnabled(false);
		newDepartmentButtonsPanel.add(depResetButton);

		depCreateButton = new JButton("Create");
		depCreateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create(0);

			}
		});
		depCreateButton.setEnabled(false);
		newDepartmentButtonsPanel.add(depCreateButton);

		depUpdateButton = new JButton("Update");
		depUpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update(0);

			}
		});
		depUpdateButton.setEnabled(false);
		newDepartmentButtonsPanel.add(depUpdateButton);

		JPanel newDepartmentFieldsPanel = new JPanel();
		newDepartmentPanel.add(newDepartmentFieldsPanel, BorderLayout.CENTER);
		newDepartmentFieldsPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel departmentNameLabel = new JLabel("Dep. Name:");
		newDepartmentFieldsPanel
				.add(departmentNameLabel, "2, 2, left, default");

		depNameField = new JTextField();
		depNameField.getDocument().addDocumentListener(depIsValid);
		newDepartmentFieldsPanel.add(depNameField, "4, 2, fill, default");
		depNameField.setColumns(10);

		JLabel departmentDescriptionLabel = new JLabel("Dep. Description:");
		newDepartmentFieldsPanel.add(departmentDescriptionLabel,
				"2, 4, left, default");

		depDescriptionField = new JTextField();
		depDescriptionField.getDocument().addDocumentListener(depIsValid);
		newDepartmentFieldsPanel
				.add(depDescriptionField, "4, 4, fill, default");
		depDescriptionField.setColumns(10);
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		topPanel.add(toolBar);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Copy");
		btnNewButton_2.setIcon(new ImageIcon(DepartmentsPanel.class
				.getResource("/resources/Copy/Copy_24x24.png")));
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Paste");
		btnNewButton_3.setIcon(new ImageIcon(DepartmentsPanel.class
				.getResource("/resources/Paste/Paste_24x24.png")));
		toolBar.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Cut");
		btnNewButton_4.setIcon(new ImageIcon(DepartmentsPanel.class
				.getResource("/resources/Cut/Cut_24x24.png")));
		toolBar.add(btnNewButton_4);

		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Export to excel sheet");
		btnNewButton.setIcon(new ImageIcon(DepartmentsPanel.class
				.getResource("/resources/microsoft_office_excel.png")));
		toolBar.add(btnNewButton);

		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		JPanel createLocationPanel = new JPanel();
		createLocationPanel.setPreferredSize(new Dimension(10, 115));
		createLocationPanel.setBorder(new TitledBorder(null,
				"Create new location", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		centerPanel.add(createLocationPanel, BorderLayout.SOUTH);
		createLocationPanel.setLayout(new BorderLayout(0, 0));

		JPanel createLocationButtonsPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) createLocationButtonsPanel
				.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		createLocationPanel.add(createLocationButtonsPanel, BorderLayout.SOUTH);

		locationResetButton = new JButton("Reset");
		locationResetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset(1);

			}
		});
		locationResetButton.setEnabled(false);
		createLocationButtonsPanel.add(locationResetButton);

		locationCreateButton = new JButton("Create");
		locationCreateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create(1);

			}
		});
		locationCreateButton.setEnabled(false);
		createLocationButtonsPanel.add(locationCreateButton);

		locationUpdateButton = new JButton("Update");
		locationUpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update(1);

			}
		});
		locationUpdateButton.setEnabled(false);
		createLocationButtonsPanel.add(locationUpdateButton);

		JPanel createLocationFieldsPanel = new JPanel();
		createLocationPanel.add(createLocationFieldsPanel, BorderLayout.CENTER);
		createLocationFieldsPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(50dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(100dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(50dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(100dlu;default)"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC, }));

		JLabel CompaniesList = new JLabel("Company:");
		createLocationFieldsPanel.add(CompaniesList, "2, 2, left, default");

		locationCompanyComboBox = new JComboBox<Company>(
				locationCompaniesComboBoxModel);
		locationCompanyComboBox.addActionListener(locIsValid);
		locationCompanyComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					Company selectedCompany = (Company) e.getItem();

					List<Department> departments = new CompanyTask()
							.getAllDepartments(selectedCompany);

					DepartmentsComboBoxModel depModel = new DepartmentsComboBoxModel();
					depModel.setDepartmentsList(departments);

					locationDepComboBox.setModel(depModel);
				}

			}
		});

		createLocationFieldsPanel.add(locationCompanyComboBox,
				"4, 2, fill, default");

		JLabel locationNameLabel = new JLabel("Location Name:");
		createLocationFieldsPanel.add(locationNameLabel, "6, 2, left, default");

		locationNameField = new JTextField();
		locationNameField.getDocument().addDocumentListener(locIsValid);
		createLocationFieldsPanel.add(locationNameField, "8, 2, fill, default");
		locationNameField.setColumns(25);

		JLabel DepartmentsList = new JLabel("Department:");
		createLocationFieldsPanel.add(DepartmentsList, "2, 4, left, default");

		locationDepComboBox = new JComboBox<Department>(
				departmentsComboBoxModel);
		locationDepComboBox.addActionListener(locIsValid);
		createLocationFieldsPanel.add(locationDepComboBox,
				"4, 4, fill, default");

		JLabel locationDescriptionLabel = new JLabel("Location Description:");
		createLocationFieldsPanel.add(locationDescriptionLabel,
				"6, 4, left, default");

		locationDescriptionField = new JTextField();
		locationDescriptionField.getDocument().addDocumentListener(locIsValid);
		createLocationFieldsPanel.add(locationDescriptionField,
				"8, 4, fill, default");
		locationDescriptionField.setColumns(25);

		JPanel locationsViewPanel = new JPanel();
		locationsViewPanel.setBorder(new TitledBorder(null, "Locations View",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(locationsViewPanel, BorderLayout.CENTER);
		locationsViewPanel.setLayout(new BorderLayout(0, 0));

		locationsTable = new JXTable(locationsTableModel);
		locationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		locationsTable.addMouseListener(new LocationSelectionHandler());
		locationsTable.addKeyListener(new LocationSelectionHandler());
		locationsTable.setShowGrid(true);
		locationsTable.setRowHeight(25);
		locationsTable.setHighlighters(HighlighterFactory
				.createAlternateStriping());
		locationsTable.setColumnControlVisible(true);

		locationsViewPanel.add(new JScrollPane(locationsTable));
	}

	/**
	 * @return the locationNameField
	 */
	public JTextField getLocationNameField() {
		return locationNameField;
	}

	/**
	 * @param locationNameField
	 *            the locationNameField to set
	 */
	public void setLocationNameField(JTextField locationNameField) {
		this.locationNameField = locationNameField;
	}

	/**
	 * @return the locationDescriptionField
	 */
	public JTextField getLocationDescriptionField() {
		return locationDescriptionField;
	}

	/**
	 * @param locationDescriptionField
	 *            the locationDescriptionField to set
	 */
	public void setLocationDescriptionField(JTextField locationDescriptionField) {
		this.locationDescriptionField = locationDescriptionField;
	}

	/**
	 * @return the departmentsList
	 */
	public JXList getDepartmentsList() {
		return departmentsList;
	}

	/**
	 * @param departmentsList
	 *            the departmentsList to set
	 */
	public void setDepartmentsList(JXList departmentsList) {
		this.departmentsList = departmentsList;
	}

	/**
	 * @return the companiesComboBox
	 */
	public JComboBox<Company> getCompaniesComboBox() {
		return companiesComboBox;
	}

	/**
	 * @param companiesComboBox
	 *            the companiesComboBox to set
	 */
	public void setCompaniesComboBox(JComboBox<Company> companiesComboBox) {
		this.companiesComboBox = companiesComboBox;
	}

	/**
	 * @return the locationCompanyComboBox
	 */
	public JComboBox<Company> getLocationCompanyComboBox() {
		return locationCompanyComboBox;
	}

	/**
	 * @param locationCompanyComboBox
	 *            the locationCompanyComboBox to set
	 */
	public void setLocationCompanyComboBox(
			JComboBox<Company> locationCompanyComboBox) {
		this.locationCompanyComboBox = locationCompanyComboBox;
	}

	/**
	 * @return the locationDepComboBox
	 */
	public JComboBox<Department> getLocationDepComboBox() {
		return locationDepComboBox;
	}

	/**
	 * @param locationDepComboBox
	 *            the locationDepComboBox to set
	 */
	public void setLocationDepComboBox(JComboBox<Department> locationDepComboBox) {
		this.locationDepComboBox = locationDepComboBox;
	}

	private void refereshData() {

	}

	private void create(int type) {

		if (type == 0) {
			Company selectedCompany = (Company) companiesComboBox.getModel()
					.getSelectedItem();

			String depName = depNameField.getText();
			String depDescription = depDescriptionField.getText();

			Department dep = new Department();
			dep.setCompany(selectedCompany);
			dep.setDescription(depDescription);
			dep.setName(depName);

			try {
				new DepartmentTask().save(dep);
			} catch (HibernateException e) {
				JOptionPane.showMessageDialog(this, e.getCause().getMessage());
			}

		} else if (type == 1) {

			Department selectedDepartment = (Department) locationDepComboBox
					.getModel().getSelectedItem();
			String locDescription = locationDescriptionField.getText();
			String locName = locationNameField.getText();

			Location location = new Location();

			location.setDepartment(selectedDepartment);
			location.setDescription(locDescription);
			location.setLocation(locName);

			try {
				new LocationTask().save(location);
			} catch (HibernateException e) {
				JOptionPane.showMessageDialog(this, e.getCause().getMessage());
			}
		}

	}

	private void update(int type) {
		if (type == 0) {

			Department selectedDepartment = (Department) departmentsList
					.getSelectedValue();

			long depID = selectedDepartment.getId();

			Department department = new DepartmentTask().get(depID);

	

			String depName = depNameField.getText();
			String depDescription = depDescriptionField.getText();

			department.setDescription(depDescription);
			department.setName(depName);

			try {
				new DepartmentTask().update(department);
			} catch (HibernateException e) {
				JOptionPane.showMessageDialog(this, e.getCause().getMessage());
			}

		} else if (type == 1) {

			int selectedRow = locationsTable.getSelectedRow();

			long locID = (long) locationsTable.getValueAt(selectedRow, 0);

			Location location = new LocationTask().getLocation(locID);

			Department selectedDepartment = (Department) locationDepComboBox
					.getModel().getSelectedItem();
			String locDescription = locationDescriptionField.getText();
			String locName = locationNameField.getText();

			location.setDepartment(selectedDepartment);
			location.setDescription(locDescription);
			location.setLocation(locName);

			try {
				new LocationTask().update(location);
			} catch (HibernateException e) {
				JOptionPane.showMessageDialog(this, e.getCause().getMessage());
			}
		}
	}

	private void reset(int type) {

		if (type == 0) {

			companiesComboBox.getModel().setSelectedItem(null);
			departmentsList.setModel(new DepartmentsComboBoxModel());;
			depNameField.setText("");
			depDescriptionField.setText("");

		} else if (type == 1) {
			locationCompanyComboBox.getModel().setSelectedItem(null);
			locationDepComboBox.getModel().setSelectedItem(null);
			locationDescriptionField.setText("");
			locationNameField.setText("");
			departmentsList.clearSelection();
			locationsTable.clearSelection();
		}
	}

	private class LocationSelectionHandler implements MouseListener,
			KeyListener {

		private void handleSlection() {
			int selectedRow = locationsTable.getSelectedRow();

			long id = (long) locationsTable.getValueAt(selectedRow, 0);

			Location location = new LocationTask().getLocation(id);

			locationNameField.setText(location.getLocation());
			locationDescriptionField.setText(location.getDescription());
			locationCompanyComboBox.getModel().setSelectedItem(
					location.getDepartment().getCompany());
			locationDepComboBox.getModel().setSelectedItem(
					location.getDepartment());

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				handleSlection();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				handleSlection();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				handleSlection();
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_ENTER) {

				handleSlection();

			}

		}

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_ENTER) {

				handleSlection();

			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_ENTER) {

				handleSlection();

			}

		}

	}

	private class IsValid implements DocumentListener, ActionListener {

		private int type;

		public IsValid(int type) {
			this.type = type;
		}

		private void handleButtons(int type, boolean valid) {
			switch (type) {
			case 0:
				depCreateButton.setEnabled(valid);
				depResetButton.setEnabled(valid);
				depUpdateButton.setEnabled(valid);
				break;
			case 1:
				locationResetButton.setEnabled(valid);
				locationUpdateButton.setEnabled(valid);
				locationCreateButton.setEnabled(valid);
				break;
			default:
				break;
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean valid = isValid(type);
			handleButtons(type, valid);

		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			boolean valid = isValid(type);
			handleButtons(type, valid);

		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			boolean valid = isValid(type);
			handleButtons(type, valid);

		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			boolean valid = isValid(type);
			handleButtons(type, valid);

		}

		private boolean isValid(int type) {
			switch (type) {
			case 0:
				if (companiesComboBox.getModel().getSelectedItem() == null
						|| depDescriptionField.getText().isEmpty()
						|| depNameField.getText().isEmpty()) {
					return false;
				} else {
					return true;
				}
			case 1:
				if (locationNameField.getText().isEmpty()
						|| locationDescriptionField.getText().isEmpty()
						|| locationCompanyComboBox.getModel().getSelectedItem() == null
						|| locationDepComboBox.getModel().getSelectedItem() == null) {
					return false;
				} else {
					return true;
				}
			default:
				return false;

			}
		}
	}
}
