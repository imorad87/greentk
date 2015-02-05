package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.Sensor;

public class SensorsComboBoxModel extends DefaultComboBoxModel<Sensor> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<Sensor> sensorsList;

	public SensorsComboBoxModel() {
		sensorsList = new ArrayList<Sensor>();
	}

	@Override
	public Sensor getElementAt(int index) {
		return (sensorsList.get(index));
	}

	@Override
	public int getSize() {
		
		if (sensorsList == null || sensorsList.isEmpty()) {
			return 0;
		}else{
			return sensorsList.size();
		}
	}

	/**
	 * @return the companieList
	 */
	public List<Sensor> getsesnorsList() {
		return sensorsList;
	}

	/**
	 * @param sesnorsList
	 *            the companieList to set
	 */
	public void setsesnorsList(List<Sensor> sesnorsList) {
		this.sensorsList = sesnorsList;
		fireContentsChanged(this, 0, sesnorsList.size());
	}

}
