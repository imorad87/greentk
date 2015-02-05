package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.Department;

public class DepartmentsComboBoxModel extends DefaultComboBoxModel<Department>{

	
	private static final long serialVersionUID = -6491981373444024730L;
	private List<Department> departmentsList;
	
	

	public DepartmentsComboBoxModel(){
		departmentsList = new ArrayList<Department>();
	}
		
	
	@Override
	public Department getElementAt(int index) {
		
		return (departmentsList.get(index));
	}

	@Override
	public int getSize() {
		
		return departmentsList.size();
	}
	
	
	/**
	 * @return the companieList
	 */
	public List<Department> getDepartmentsList() {
		return departmentsList;
	}


	/**
	 * @param companieList the companieList to set
	 */
	public void setDepartmentsList(List<Department> departmentsList) {
		this.departmentsList = departmentsList;
		fireContentsChanged(this, 0, departmentsList.size());
	}

}
