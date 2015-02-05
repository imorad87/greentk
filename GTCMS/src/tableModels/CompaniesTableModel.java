package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Company;

public class CompaniesTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private List<Company> companiesList = new ArrayList<Company>();
	
	private String[] columnNames = { "ID", "Code", "Name", "Address", "Phone",
			"Fax" };
	
	


	@Override
	public String getColumnName(int col) {

		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return companiesList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Company company = companiesList.get(row);
		switch (col) {
		case 0:
			return company.getCompanyID();
		case 1:
			return company.getCompanyCode();
		case 2:
			return company.getCompanyName();
		case 3:
			return company.getAddress();
		case 4:
			return company.getPhoneNumber();
		case 5:
			return company.getFaxNumber();
		}
		return null;
	}

	public List<Company> getCompaniesList() {
		return companiesList;
	}

	public void setCompaniesList(List<Company> companiesList) {
		this.companiesList = companiesList;
	}

	

}
