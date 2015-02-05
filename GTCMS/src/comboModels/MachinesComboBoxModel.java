package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.Machine;

public class MachinesComboBoxModel extends DefaultComboBoxModel<Machine> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<Machine> machinesList;

	public MachinesComboBoxModel() {
		machinesList = new ArrayList<Machine>();
	}

	@Override
	public Machine getElementAt(int index) {
		return (machinesList.get(index));
	}

	@Override
	public int getSize() {
		
		if (machinesList == null || machinesList.isEmpty()) {
			return 0;
		}else{
			return machinesList.size();
		}
	}

	/**
	 * @return the companieList
	 */
	public List<?> getMachinesList() {
		return machinesList;
	}

	/**
	 * @param machinesList
	 *            the companieList to set
	 */
	public void setMachinesList(List<Machine> machinesList) {
		this.machinesList = machinesList;
		fireContentsChanged(this, 0, machinesList.size());
	}

}
