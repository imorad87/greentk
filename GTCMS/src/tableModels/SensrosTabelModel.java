package tableModels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Sensor;

public class SensrosTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Sensor> sensrosList = new ArrayList<>();
	private String[] columnNames = { "ID", "Code", "Description", "Model",
			"Serial #", "Manufacturer", "Resolution", "Type", "Calibrated",
			"Cal. Date", "Cal. Due Date", "Dep. Name", "Location", "Machine Code" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 14;
	}

	@Override
	public int getRowCount() {
		if (sensrosList == null || sensrosList.size() == 0) {
			return 0;
		} else {
			return sensrosList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		DateFormat formatter = DateFormat.getDateInstance();
		Sensor sensor = sensrosList.get(row);
		if (sensor == null) {
			return null;
		}
		switch (col) {
		case 0:
			Long id = sensor.getSensorsID();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String code = sensor.getCode();
			if (code == null || code.isEmpty()) {
				return "NA";
			} else {
				return code;
			}
		case 2:
			String description = sensor.getDescription();
			if (description == null || description.isEmpty()) {
				return "NA";
			} else {
				return description;
			}
		case 3:
			String model = sensor.getModel();
			if (model == null || model.isEmpty()) {
				return "NA";
			} else {
				return model;
			}
		case 4:
			String serialNumber = sensor.getSerialNumber();

			if (serialNumber == null || serialNumber.isEmpty()) {
				return "NA";
			} else {
				return serialNumber;
			}
		case 5:
			String manufacturer = sensor.getManufacturer();
			if (manufacturer == null || manufacturer.isEmpty()) {
				return "NA";
			} else {
				return manufacturer;
			}
		case 6:
			return sensor.getResolution();

		case 7:
			String type = sensor.getJobType().getSymbol();

			if (type == null || type.isEmpty()) {
				return "NA";
			} else {
				return type;
			}
		case 8:

			if (sensor.isCalibrated()) {
				return "Yes";
			} else {
				return "No";
			}
		case 9:
			Date calDate = sensor.getCalDate();

			if (calDate == null) {
				return "NA";
			} else {
				return formatter.format(calDate);
			}
		case 10:
			Date calDueDate = sensor.getCalDueDate();
			if (calDueDate == null) {
				return "NA";
			} else {
				return formatter.format(calDueDate);
			}
		case 11:
			String depName = sensor.getMachine().getDepartment().getName();

			if(depName == null){
				return "NA";
			}else{
				return depName;
			}
		case 12:
			String location = sensor.getMachine().getLocation().getLocation();
			if(location == null){
				return "NA";
			}else{
				return location;
			}
		case 13:
			String machineCode = sensor.getMachine().getMachineCode();
			if(machineCode == null){
				return "NA";
			}else{
				return machineCode;
			}
		}
		return null;
	}

	public List<Sensor> getSensorsList() {
		return sensrosList;
	}

	public void setSensorsList(List<Sensor> sensrosList) {
		this.sensrosList = sensrosList;
		fireTableDataChanged();
	}

}
