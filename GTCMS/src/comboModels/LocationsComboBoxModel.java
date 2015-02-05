package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.Location;

public class LocationsComboBoxModel extends DefaultComboBoxModel<Location> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<Location> locationList;

	public LocationsComboBoxModel() {
		locationList = new ArrayList<Location>();
	}

	@Override
	public Location getElementAt(int index) {
		return (locationList.get(index));
	}

	@Override
	public int getSize() {
		

		
			return locationList.size();
		
	}

	/**
	 * @return the companieList
	 */
	public List<Location> getLocationsList() {
		return locationList;
	}

	/**
	 * @param locationsList
	 *            the companieList to set
	 */
	public void setLocationsList(List<Location> locationsList) {
		this.locationList = locationsList;
		fireContentsChanged(this, 0, locationList.size());
	}

}
