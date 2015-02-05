package treeModels;

import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import model.Company;
import dao.CompanyTask;

public class CompaniesTreeModel implements TreeModel {
	
	private List<Company> companiesList = new CompanyTask().getAllCompanies();

	
	private static final long serialVersionUID = 1L;

	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getChild(Object parent, int index) {
		Company company = (Company) parent;
		return company.getDepartmentsList().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		Company company = (Company) parent;

		return company.getDepartmentsList().size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		Company company = (Company) parent;

		return company.getDepartmentsList().indexOf(child);
	}

	@Override
	public Object getRoot() {
		
		return null;
	}

	@Override
	public boolean isLeaf(Object child) {
		
		return false;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
