package comboModels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.Company;

public class CompaniesComboBoxModel extends DefaultComboBoxModel<Company> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<Company> companieList;
	private PropertyChangeSupport pcs;

	public CompaniesComboBoxModel() {
		companieList = new ArrayList<>();
		this.pcs = new PropertyChangeSupport(this);
	}

	@Override
	public Company getElementAt(int index) {
		// String code = ((Company) companieList.get(index)).getCompanyCode();
		// String name = ((Company) companieList.get(index)).getCompanyName();
		// return code + "-" + name;
		// return ((Company) companieList.get(index)).getCompanyName();
		return companieList.get(index);
	}

	@Override
	public int getSize() {

		return companieList.size();
	}

	/**
	 * @return the companieList
	 */
	public List<Company> getCompanieList() {
		return companieList;
	}

	/**
	 * @param companieList
	 *            the companieList to set
	 */
	public void setCompanieList(List<Company> companieList) {
		List<Company> oldValue = this.companieList;
		this.companieList = companieList;
		firePropertyChange("companiesList", oldValue, this.companieList);
		fireContentsChanged(this, 0, companieList.size());
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	private void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

}
