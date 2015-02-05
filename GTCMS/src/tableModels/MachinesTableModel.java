package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Machine;

public class MachinesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private List machinesList = new ArrayList<>();
	private String[] columnNames = { "ID", "Name", "Code", "Model",
			"Manufacturer", "Serial Number", "Section", "Company",
			"Department", "Location", "Sensors Inside" };

	@Override
	public String getColumnName(int col) {

		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 11;
	}

	@Override
	public int getRowCount() {
		if (machinesList == null || machinesList.size() == 0) {
			return 0;
		} else {
			return machinesList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		Machine machine = (Machine) machinesList.get(row);
		switch (col) {
		case 0:
			Long id = machine.getMachineID();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String name = machine.getMachineName();
			if (name == null || name.isEmpty()) {
				return "NA";
			} else {
				return name;
			}
		case 2:
			String code = machine.getMachineCode();
			if (code == null || code.isEmpty()) {
				return "NA";
			} else {
				return code;
			}
		case 3:
			String model = machine.getModel();
			if (model == null || model.isEmpty()) {
				return "NA";
			} else {
				return model;
			}
		case 4:
			String manufacturer = machine.getManufacturer();
			if (manufacturer == null || manufacturer.isEmpty()) {
				return "NA";
			} else {
				return manufacturer;
			}
		case 5:
			String serialNumber = machine.getSerialNumber();

			if (serialNumber == null || serialNumber.isEmpty()) {
				return "NA";
			} else {
				return serialNumber;
			}
		case 6:
			String section = machine.getSection();
			if (section == null || section.isEmpty()) {
				return "NA";
			} else {
				return section;
			}
		case 7:
			String companyName = machine.getDepartment().getCompany()
					.getCompanyName();
			if (companyName == null || companyName.isEmpty()) {
				return "NA";
			} else {
				return companyName;
			}
		case 8:
			String department = machine.getDepartment().getName();
			if (department == null || department.isEmpty()) {
				return "NA";
			} else {
				return department;
			}
		case 9:
			String location = machine.getLocation().getLocation();
			if (location == null || location.isEmpty()) {
				return "NA";
			} else {
				return location;
			}
		case 10:
			Integer sensorsCount = machine.getSensorQuantity();
			if (sensorsCount == null) {
				return 0;
			} else {
				return sensorsCount;
			}

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getMachinesList() {
		return machinesList;
	}

	public void setMachinesList(List<Machine> machines) {
		this.machinesList = machines;
	}

}
