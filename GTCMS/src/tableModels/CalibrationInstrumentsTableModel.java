package tableModels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.CalibrationInstrument;
import model.JobType;
import dao.CalibrationInstrumentTask;

public class CalibrationInstrumentsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<CalibrationInstrument> calibrationInstrumentsList = new ArrayList<CalibrationInstrument>();

	private String[] columnNames = { "CMS ID", "Code", "Model", "Name", "Type",
			"Cal. Date", "Cal. Due Date", "Mini Range", "Max Range",
			"Uncertianity", "Remainig Days", "Valid" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 12;
	}

	@Override
	public int getRowCount() {
		if (calibrationInstrumentsList == null
				|| calibrationInstrumentsList.size() == 0) {
			return 0;
		} else {
			return calibrationInstrumentsList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {

		DateFormat formatter = DateFormat.getDateInstance();
		CalibrationInstrument calibrationInstrument = calibrationInstrumentsList
				.get(row);
		if (calibrationInstrument == null) {
			return null;
		}
		switch (col) {
		case 0:
			Long id = calibrationInstrument.getInstrumentID();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String code = calibrationInstrument.getInstrumentCode();
			if (code == null || code.isEmpty()) {
				return "NA";
			} else {
				return code;
			}
		
		case 2:
			String model = calibrationInstrument.getModel();
			if (model == null || model.isEmpty()) {
				return "NA";
			} else {
				return model;
			}
		
		case 3:
			return calibrationInstrument.getName();

		case 4:
			List<JobType> allJobTypes = new CalibrationInstrumentTask().getAllJobTypes(calibrationInstrument.getInstrumentID());
					
			return allJobTypes;

		case 5:
			Date calDate = calibrationInstrument.getCalibrationDate();

			if (calDate == null) {
				return "NA";
			} else {
				return formatter.format(calDate);
			}
		case 6:
			Date calDueDate = calibrationInstrument.getCalibrationDueDate();
			if (calDueDate == null) {
				return "NA";
			} else {
				return formatter.format(calDueDate);
			}
		case 7:
			return calibrationInstrument.getMinRange();
		case 8:
			return calibrationInstrument.getMaxRange();

		case 9:
			return calibrationInstrument.getUncertianity();
		case 10:
			return calibrationInstrument.getRemainingDays();
		case 11:
			return calibrationInstrument.isValid();
		}
		return null;
	}

	public List<CalibrationInstrument> getCalibrationInstrumentsList() {
		return calibrationInstrumentsList;
	}

	public void setCalibrationInstrumentsList(
			List<CalibrationInstrument> calibrationInstrumentsList) {
		this.calibrationInstrumentsList = calibrationInstrumentsList;
		fireTableDataChanged();
	}

}
