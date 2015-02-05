package tableModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Location;
import dao.LocationTask;

public class LocationsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Location> locationsList = new ArrayList<>();
	private String[] columnNames = { "ID", "Name", "Description", "Department",
			"# Machines" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		if (locationsList == null || locationsList.size() == 0) {
			return 0;
		} else {
			return locationsList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		Location location = locationsList.get(row);
		
		switch (col) {
		case 0:
			Long id = location.getId();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String description = location.getLocation();
			if (description == null || description.isEmpty()) {
				return "NA";
			} else {
				return description;
			}
		case 2:
			String symbol = location.getDescription();
			if (symbol == null || symbol.isEmpty()) {
				return "NA";
			} else {
				return symbol;
			}
		case 3:
			String department = location.getDepartment().getName();

			if (department == null || department.isEmpty()) {
				return "NA";
			} else {
				return department;
			}
		case 4:
			return new LocationTask().getAllMAchines(location).size();
		}
		return null;
	}

	public List<Location> getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(List<Location> locationsList) {
		Collections.sort(locationsList);
		this.locationsList = locationsList;
		fireTableDataChanged();
	}

}
