import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainWindow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				new MainWindow();
			}
		});

		/*
		 * final CompanyTask cTask = new CompanyTask(); DepartmentTask dTask =
		 * new DepartmentTask();
		 * 
		 * Company com = new Company();
		 * 
		 * Department dep1 = new Department(); dep1.setCompany(com);
		 * dep1.setDescription("Dep Des 1008"); dep1.setName("Dep 1008");
		 * 
		 * Department dep2 = new Department(); dep2.setCompany(com);
		 * dep2.setDescription("Dep Des 1007"); dep2.setName("Dep 1007");
		 * 
		 * 
		 * com.setAddress("Add 1006"); com.setCompanyCode("A 1006");
		 * com.setFaxNumber("FAX 1006"); com.setCompanyName("Com 1006");
		 * com.setPhoneNumber("Phone 1006"); com.getDepartmentsList().add(dep1);
		 * com.getDepartmentsList().add(dep2);
		 * 
		 * cTask.addCompany(com);
		 * 
		 * 
		 * 
		 * 
		 * JFrame f = new JFrame(); JPanel panel = new JPanel(); final
		 * JComboBox<Company> companies = new JComboBox<>();
		 * ComaniesComboBoxModel cmodel = new ComaniesComboBoxModel();
		 * companies.setModel(cmodel);
		 * 
		 * 
		 * 
		 * final JComboBox<Department> departments = new JComboBox<>();
		 * DepartmentsComboBoxModel dmodel = new DepartmentsComboBoxModel();
		 * departments.setModel(dmodel);
		 * 
		 * panel.add(companies); panel.add(departments);
		 * 
		 * companies.addItemListener(new ItemListener() {
		 * 
		 * @Override public void itemStateChanged(ItemEvent e) {
		 * 
		 * int state = e.getStateChange(); if(state == ItemEvent.SELECTED){
		 * Company company = (Company) companies.getSelectedItem();
		 * System.out.println
		 * (companies.getSelectedItem().getClass().getSimpleName() +
		 * " \n Selected Item Class: " + company.getClass().getSimpleName());
		 * Company detachedCompany = cTask.getByName(company); List<Department>
		 * departmentsList = detachedCompany.getDepartmentsList();
		 * System.out.println("Department Size = " + departmentsList.size());
		 * DepartmentsComboBoxModel model = (DepartmentsComboBoxModel)
		 * departments.getModel(); model.setDepartmentsList(departmentsList); //
		 * departments.repaint(); }
		 * 
		 * } });
		 * 
		 * f.add(panel); f.pack(); f.setVisible(true);
		 * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 */

		/*
		 * SchemaExport s = new SchemaExport(new Configuration().configure());
		 * s.create(true, true);
		 */
	}

}
