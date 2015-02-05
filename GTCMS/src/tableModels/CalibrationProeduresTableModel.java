package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.CalibrationProcedure;

public class CalibrationProeduresTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<CalibrationProcedure> calibrationProceduressList = new ArrayList<>();
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
		if (calibrationProceduressList == null || calibrationProceduressList.size() == 0) {
			return 0;
		} else {
			return calibrationProceduressList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		CalibrationProcedure calibrationProcedure = calibrationProceduressList.get(row);
		
		switch (col) {
		case 0:
			Long id = calibrationProcedure.getProcedureID();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String description = calibrationProcedure.getProcedureCode();
			if (description == null || description.isEmpty()) {
				return "NA";
			} else {
				return description;
			}
		}
		return null;
	}

	public List<CalibrationProcedure> getCalibrationProceduresList() {
		return calibrationProceduressList;
	}

	public void setCalibrationProceduresList(List<CalibrationProcedure> calibrationProceduresList) {
		this.calibrationProceduressList = calibrationProceduresList;
		fireTableDataChanged();
	}

}
