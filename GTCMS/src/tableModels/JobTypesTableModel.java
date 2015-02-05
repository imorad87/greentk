package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.JobType;

public class JobTypesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<JobType> jobTypesList = new ArrayList<>();
	private String[] columnNames = { "ID", "Description", "Function Symbol",
			"Price" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if (jobTypesList == null || jobTypesList.size() == 0) {
			return 0;
		} else {
			return jobTypesList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		JobType jobType = jobTypesList.get(row);
		if (jobType == null) {
			return null;
		}
		switch (col) {
		case 0:
			Long id = jobType.getJobTypeID();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String description = jobType.getDescription();
			if (description == null || description.isEmpty()) {
				return "NA";
			} else {
				return description;
			}
		case 2:
			String symbol = jobType.getSymbol();
			if (symbol == null || symbol.isEmpty()) {
				return "NA";
			} else {
				return symbol;
			}
		case 3:
			double price = jobType.getPrice();

			return price;
		}
		return null;
	}

	public List<JobType> getJobTypesList() {
		return jobTypesList;
	}

	public void setJobTypesList(List<JobType> jobTypesList) {
		this.jobTypesList = jobTypesList;
		fireTableDataChanged();
	}

}
