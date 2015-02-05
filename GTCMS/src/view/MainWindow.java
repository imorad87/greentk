package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Company;
import model.JobType;

import org.jdesktop.swingx.JXStatusBar;

import comboModels.CompaniesComboBoxModel;
import dao.CompanyTask;
import dao.JobTypeTask;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private CompaniesPanel companiesPanel;
	private ProgressDialog progress;
	private DepartmentsPanel departmentsPanel;
	private MachinesPanel machinesPanel;
	private JTabbedPane tabbedPane;
	private JXStatusBar statusBar;
	private JProgressBar progressBar;
	private JLabel statusLabel;
	
	private CertificatesPanel certificatesPanel;
	private CalibrationInstrumentsPanel calibrationInstrumentsPanel;
	private JobTypesPanel jobTypesPanel;
	private InternationalStandardsPanel internationalStandardsPanel;
	private CalibrationProceduresPanel calibrationProceduresPanel;
	


	/**
	 * Create the application.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/dialogLogo.jpg")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("GreenTK Certification Management System");
		setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		companiesPanel = new CompaniesPanel();
		departmentsPanel = new DepartmentsPanel();
		progress = new ProgressDialog((Window) getParent());
		machinesPanel = new MachinesPanel();
		certificatesPanel = new CertificatesPanel();
		calibrationInstrumentsPanel = new CalibrationInstrumentsPanel();
		jobTypesPanel = new JobTypesPanel();
		internationalStandardsPanel = new InternationalStandardsPanel();
		calibrationProceduresPanel = new CalibrationProceduresPanel();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Database...");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem menuItem = new JMenuItem("New menu item");
		mnNewMenu_1.add(menuItem);
		
		JMenu mnWindow = new JMenu("Options");
		menuBar.add(mnWindow);
		
		JMenu mnThemes = new JMenu("Themes");
		mnWindow.add(mnThemes);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnThemes.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnThemes.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("New menu item");
		mnThemes.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mnThemes.add(mntmNewMenuItem_4);
		
		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);
		getContentPane().setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(SwingConstants.LEFT);
		
		Icon icon = new ImageIcon(getClass().getResource("/resources/home.png"));
		JLabel home = new JLabel(icon);
				
		tabbedPane.addTab(null, new JPanel());
		tabbedPane.setTabComponentAt(0, home);
		
		tabbedPane.addTab("Companies", companiesPanel);
		tabbedPane.addTab("Department & Location", departmentsPanel);
		tabbedPane.addTab(null, machinesPanel);
		JLabel machines = new JLabel("Machines & Sensors");
		machines.setIcon(new ImageIcon(getClass().getResource("/resources/machine.png")));
		tabbedPane.setTabComponentAt(3, machines);

		tabbedPane.addTab("Protocols", new JPanel());
		tabbedPane.addTab("Certificates",  certificatesPanel);
		tabbedPane.addTab("Job Types",  jobTypesPanel);
		tabbedPane.addTab("Calibration Instruments",  calibrationInstrumentsPanel);
		tabbedPane.addTab("International Standards",  internationalStandardsPanel);
		tabbedPane.addTab("Calibration Procedures",  calibrationProceduresPanel);


		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				
				if (tabbedPane.getSelectedIndex() == 1) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.setLocation(getMousePosition());
							progress.setVisible(true);

						}
					});

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
						@SuppressWarnings("rawtypes")
						List list = null;

						@Override
						protected Void doInBackground() throws Exception {
							CompanyTask companyTask = new CompanyTask();
							list = companyTask.getAllCompanies();
							return null;
						}

						@SuppressWarnings("unchecked")
						@Override
						protected void done() {
							companiesPanel.getModel().setCompaniesList(list);
							companiesPanel.getModel().fireTableDataChanged();
							progress.setVisible(false);
							
						}
					};

					worker.execute();
					
				}else if(tabbedPane.getSelectedIndex() == 2){
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.setLocation(getMousePosition());
							progress.setVisible(true);
							progressBar.setIndeterminate(true);
							

						}
					});

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
						private List<Company> list = null;
						@Override
						protected Void doInBackground() throws Exception {
							list = new CompanyTask().getAllCompanies();
							return null;
						}

						@Override
						protected void done() {							
							CompaniesComboBoxModel companieModel = (CompaniesComboBoxModel) departmentsPanel.getCompaniesComboBox().getModel();
							companieModel.setCompanieList(list);
							
							CompaniesComboBoxModel locationCompaniesModel = (CompaniesComboBoxModel) departmentsPanel.getLocationCompanyComboBox().getModel();
							locationCompaniesModel.setCompanieList(list);
							progress.dispose();
							
						}
					};

					worker.execute();
					
				}else if(tabbedPane.getSelectedIndex() == 3){
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.setLocation(getMousePosition());
							progress.setVisible(true);
							progressBar.setIndeterminate(true);
							

						}
					});

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
						List<Company> list = null;
						List<JobType> jobTypesList = null;
						@Override
						protected Void doInBackground() throws Exception {
							list = new CompanyTask().getAllCompanies();
							JobTypeTask jobTypeTask = new JobTypeTask();
							jobTypesList = jobTypeTask.getAll();
							return null;
						}

						@Override
						protected void done() {							
							machinesPanel.initComoaniesTree(list);
							machinesPanel.getSensorCompaniesComboBoxModel().setCompanieList(list);
							machinesPanel.getMachineCompaniesComboBoxModel().setCompanieList(list);
							machinesPanel.getJobTypesComboBoxModel().setJobTypesList(jobTypesList);
							machinesPanel.updateUI();
							machinesPanel.repaint();
							progress.dispose();
							
						}
					};

					worker.execute();
				}else if(tabbedPane.getSelectedIndex() == 5){
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							progress.setLocation(getMousePosition());
							progress.setVisible(true);
							progressBar.setIndeterminate(true);
							

						}
					});

					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
						List<Company> list = null;
						@Override
						protected Void doInBackground() throws Exception {
							list = new CompanyTask().getAllCompanies();
							return null;
						}

						@Override
						protected void done() {				
							certificatesPanel.setCompaniesList(list);
							certificatesPanel.initCompaniesTree();
							certificatesPanel.updateUI();
							certificatesPanel.repaint();
							progress.dispose();
							
						}
					};

					worker.execute();
					
				}

			}
		});
		
		JPanel statusBarPanel = new JPanel();
		statusBarPanel.setPreferredSize(new Dimension(10, 20));
		getContentPane().add(statusBarPanel, BorderLayout.SOUTH);
		statusBarPanel.setLayout(new BorderLayout(0, 0));
		
		statusBar = new JXStatusBar();
		statusBarPanel.add(statusBar);
		
		JPanel panel_1 = new JPanel();
		statusBarPanel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		panel_1.add(progressBar);
		
		statusLabel = new JLabel("");
		panel_1.add(statusLabel, BorderLayout.WEST);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		centerLocation();
		setVisible(true);
	}

	private void centerLocation() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (screenSize.width / 2) - (getSize().width / 2);
		int y = (screenSize.height / 2) - (getSize().height / 2);
		setLocation(x, y);
	}
	
	private boolean startReportingPlatform(){
		org.eclipse.birt.report.engine.api.IReportEngine engine = null;
		org.eclipse.birt.report.engine.api.EngineConfig config = null; 
		
		return true;
		
	}
	
	
}
