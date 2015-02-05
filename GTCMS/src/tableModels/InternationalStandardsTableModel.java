package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.InternationalStandard;

public class InternationalStandardsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<InternationalStandard> internationalStandardsList = new ArrayList<>();
	private String[] columnNames = { "ID", "Description" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		if (internationalStandardsList == null || internationalStandardsList.size() == 0) {
			return 0;
		} else {
			return internationalStandardsList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		InternationalStandard internationalStandard = internationalStandardsList.get(row);
		
		switch (col) {
		case 0:
			Long id = internationalStandard.getId();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String description = internationalStandard.getStandardCode();
			if (description == null || description.isEmpty()) {
				return "NA";
			} else {
				return description;
			}
		}
		return null;
	}

	public List<InternationalStandard> getInternationalStandardsList() {
		return internationalStandardsList;
	}

	public void setInternationalStandardsList(List<InternationalStandard> internationalStandardsList) {
		this.internationalStandardsList = internationalStandardsList;
		fireTableDataChanged();
	}

}
