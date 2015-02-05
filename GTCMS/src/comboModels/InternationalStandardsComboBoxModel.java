package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.InternationalStandard;

public class InternationalStandardsComboBoxModel extends DefaultComboBoxModel<InternationalStandard> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<InternationalStandard> internationalStandardsList;

	public InternationalStandardsComboBoxModel() {
		internationalStandardsList = new ArrayList<InternationalStandard>();
	}

	@Override
	public InternationalStandard getElementAt(int index) {
		return (internationalStandardsList.get(index));
	}

	@Override
	public int getSize() {
		
		if (internationalStandardsList == null || internationalStandardsList.isEmpty()) {
			return 0;
		}else{
			return internationalStandardsList.size();
		}
	}

	/**
	 * @return the companieList
	 */
	public List<InternationalStandard> getInternationalStandardsList() {
		return internationalStandardsList;
	}

	/**
	 * @param internationalStandards
	 *            the companieList to set
	 */
	public void setInternationalStandardsList(List<InternationalStandard> internationalStandards) {
		this.internationalStandardsList = internationalStandards;
	}

}
