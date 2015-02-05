package listModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import model.Department;

public class DepartmentListModel extends AbstractListModel<Department> {
	
	private static final long serialVersionUID = 6379657166148069491L;
	
	private List departmentsList;
	
	public DepartmentListModel(){
		departmentsList = new ArrayList<>();
	}
	

	@Override
	public Department getElementAt(int index) {
		
		return ((Department) departmentsList.get(index));
	}

	@Override
	public int getSize() {
		
		return departmentsList.size();
	}

	public List getDepartmentsList() {
		return departmentsList;
	}

	public void setDepartmentsList(List departmentsList) {
		this.departmentsList = departmentsList;
	}

	

}
