package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Company;
import model.Department;
import model.Location;
import model.Machine;
import model.Sensor;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTree;

import tableModels.CertificatesTabelModel;
import view.newCertificatesWizard.NewCertificateWizard;
import certificates.Certificate;
import dao.CompanyTask;
import dao.DepartmentTask;
import dao.LocationTask;
import dao.MachineTask;
import dao.SensorTask;

public class CertificatesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane splitPane;
	private JPanel left;
	private JPanel toggleBtnPanel;
	private JToggleButton tglbtnExpand;
	private JPanel treePanel;
	private JXTree companiesTree;
	private JPanel right;
	private JPanel tableViewerPanel;
	private JXTable viewerTable;
	private JButton btnDeleteSelected;
	private JButton btnViewSelected;
	private JButton btnEditSelected;
	private JButton btnNewCertificate;
	private JPanel buttonsPanel;

	private CertificatesTabelModel certificatesTableModel;

	private List<Company> companiesList;
	private JButton btnPrintSelected;
	private JButton btnClear;
	private JCheckBox chckbxSelectAll;

	/**
	 * Create the panel.
	 */
	public CertificatesPanel() {

		certificatesTableModel = new CertificatesTabelModel();
		companiesList = new ArrayList<Company>();

		initCertificatesPanel();

	}

	private void initCertificatesPanel() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		splitPane.setMinimumSize(new Dimension(300, 25));
		splitPane.setPreferredSize(new Dimension(300, 25));
		add(splitPane, BorderLayout.CENTER);

		left = new JPanel();
		left.setMinimumSize(new Dimension(250, 10));
		left.setPreferredSize(new Dimension(250, 10));
		splitPane.setLeftComponent(left);
		left.setLayout(new BorderLayout(0, 0));

		toggleBtnPanel = new JPanel();
		left.add(toggleBtnPanel, BorderLayout.NORTH);

		tglbtnExpand = new JToggleButton("Expand");
		tglbtnExpand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tglbtnExpand.isSelected()) {
					companiesTree.expandAll();
					tglbtnExpand.setText("Collapse");
					companiesTree.updateUI();
				} else {
					companiesTree.collapseAll();
					tglbtnExpand.setText("Expand");
					companiesTree.updateUI();
				}
			}
		});

		toggleBtnPanel.add(tglbtnExpand);

		// initCompaniesTree();

		right = new JPanel();
		splitPane.setRightComponent(right);
		right.setLayout(new BorderLayout(0, 0));

		initViewerTable();

		initButtons();
	}

	private void initViewerTable() {
		tableViewerPanel = new JXPanel();
		right.add(tableViewerPanel, BorderLayout.CENTER);
		tableViewerPanel.setLayout(new BorderLayout(0, 0));
		viewerTable = new JXTable(certificatesTableModel);
		viewerTable.setFillsViewportHeight(true);
		viewerTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		viewerTable.setColumnControlVisible(true);
		viewerTable.setRowHeight(25);
		viewerTable.getColumn(8).setCellRenderer(new RedRenderer());
		tableViewerPanel.add(new JScrollPane(viewerTable), BorderLayout.CENTER);
	}

	public void initCompaniesTree() {
		treePanel = new JPanel();
		treePanel.setBorder(BorderFactory.createTitledBorder("Companies Tree"));
		left.add(treePanel, BorderLayout.CENTER);
		treePanel.setLayout(new BorderLayout(0, 0));

		companiesTree = new JXTree(createCompaniesTree(getCompaniesList()));
		companiesTree.setRootVisible(false);
		companiesTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				handleTreeSelection();

			}
		});
		treePanel.add(new JScrollPane(companiesTree));
	}

	private void initButtons() {
		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		right.add(buttonsPanel, BorderLayout.SOUTH);

		btnNewCertificate = new JButton("New");
		btnNewCertificate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						NewCertificateWizard certificateWizard = new NewCertificateWizard();
						certificateWizard.setVisible(true);						
					}
				});
				
			}
		});

		buttonsPanel.add(btnNewCertificate);

		btnEditSelected = new JButton("Edit");
		buttonsPanel.add(btnEditSelected);

		btnDeleteSelected = new JButton("Delete");
		buttonsPanel.add(btnDeleteSelected);

		btnViewSelected = new JButton("View");
		buttonsPanel.add(btnViewSelected);
		
		btnPrintSelected = new JButton("Print");
		buttonsPanel.add(btnPrintSelected);
		
		btnClear = new JButton("Clear");
		buttonsPanel.add(btnClear);
		
		chckbxSelectAll = new JCheckBox("Select All");
		chckbxSelectAll.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxSelectAll.isSelected()){
					viewerTable.selectAll();
				}else{
					viewerTable.clearSelection();
				}
				
			}
		});
		buttonsPanel.add(chckbxSelectAll);
	}

	private void handleTableSelection() {
		// TODO handle certificates table selection
	}

	private void handleTreeSelection() {
		// TODO handle certificates tree selection

		
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				List<Certificate> certificatesList = new ArrayList<>();
				DefaultMutableTreeNode selection = (DefaultMutableTreeNode) companiesTree
						.getLastSelectedPathComponent();

				Object selectedObject = selection.getUserObject();

				if (selectedObject instanceof Company) {

					Company selectedCompany = (Company) selectedObject;
					List<Department> departmentsList = new CompanyTask()
							.getAllDepartments(selectedCompany);
					Iterator<Department> depIterator = departmentsList
							.iterator();
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

								Iterator<Sensor> iterator = sensors.iterator();
								while (iterator.hasNext()) {
									Sensor sensor = iterator.next();
									Collection<Certificate> certificates = new SensorTask()
											.getAllCertificates(sensor);
									if (certificates != null) {
										certificatesList.addAll(certificates);
									}

								}
							}
						}
					}

					certificatesTableModel
							.setCertificatesList(certificatesList);
					viewerTable.setModel(certificatesTableModel);
					viewerTable.updateUI();
					viewerTable.repaint();

				} /*
				 * else if (selectedObject instanceof Department) { Department
				 * department = (Department) selectedObject; List<Location>
				 * locationsList = new DepartmentTask()
				 * .getAllLocation(department); Iterator<Location>
				 * locationIterator = locationsList.iterator(); while
				 * (locationIterator.hasNext()) { Location location =
				 * locationIterator.next(); List<Machine> machineList = new
				 * LocationTask() .getAllMAchines(location); Iterator<Machine>
				 * machineIterator = machineList.iterator(); while
				 * (machineIterator.hasNext()) { Machine machine =
				 * machineIterator.next(); List<Sensor> sensors = new
				 * SensorTask() .getAllSensors(machine);
				 * sensorsList.addAll(sensors); } }
				 * sensorsModel.setSensorsList(sensorsList);
				 * viewerTable.setModel(sensorsModel); viewerTable.updateUI();
				 * viewerTable.repaint();
				 * 
				 * } else if (selectedObject instanceof Location) {
				 * 
				 * Location selectedLocation = (Location) selectedObject;
				 * List<Machine> machines = new LocationTask()
				 * .getAllMAchines(selectedLocation); Iterator<Machine>
				 * machinesIterator = machines.iterator(); while
				 * (machinesIterator.hasNext()) { Machine machine =
				 * machinesIterator.next(); List<Sensor> sensors = new
				 * SensorTask().getAllSensors(machine);
				 * sensorsList.addAll(sensors); }
				 * sensorsModel.setSensorsList(sensorsList);
				 * viewerTable.setModel(sensorsModel); viewerTable.updateUI();
				 * viewerTable.repaint(); } else if (selectedObject instanceof
				 * Machine) { Machine machine = (Machine) selectedObject;
				 * List<Sensor> sensors = new
				 * SensorTask().getAllSensors(machine);
				 * sensorsModel.setSensorsList(sensors);
				 * viewerTable.setModel(sensorsModel); viewerTable.updateUI();
				 * viewerTable.repaint();
				 * 
				 * }
				 */
				return null;
			}
		};

		worker.execute();

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

						List<Sensor> sensors = new MachineTask()
								.getAllSensors(machine);
						Iterator<Sensor> iterator = sensors.iterator();
						while (iterator.hasNext()) {
							Sensor sensor = iterator.next();
							DefaultMutableTreeNode sensorNode = new DefaultMutableTreeNode(
									sensor);
							machineNode.add(sensorNode);

						}
					}
				}
			}

			index++;
		}
		return companiesRoot;
	}

	public List<Company> getCompaniesList() {
		return companiesList;
	}

	public void setCompaniesList(List<Company> companiesList) {
		this.companiesList = companiesList;
	}
	
	private class RedRenderer extends JLabel implements TableCellRenderer {
		// TODO modify the renderer based on the acceptance limit

		/**
		 * 
		 */
		private static final long serialVersionUID = -8144721503041262238L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			
			boolean v = (boolean) value;
			if (v == false) {
				JLabel l = new JLabel();
				l.setOpaque(true);
				l.setForeground(Color.WHITE);
				l.setBackground(Color.RED);
				l.setText("Expired");
				return l;
			} else {
				JLabel l2 = new JLabel();
				l2.setText("Valid");
				return l2;
			}
		}
	}

}
