package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.JobType;

public class JobTypesComboBoxModel extends DefaultComboBoxModel<JobType> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<JobType> jobTypesList;

	public JobTypesComboBoxModel() {
		jobTypesList = new ArrayList<JobType>();
	}

	@Override
	public JobType getElementAt(int index) {
		return (jobTypesList.get(index));
	}

	@Override
	public int getSize() {
		
		if (jobTypesList == null || jobTypesList.isEmpty()) {
			return 0;
		}else{
			return jobTypesList.size();
		}
	}

	/**
	 * @return the companieList
	 */
	public List<JobType> getJobTypesList() {
		return jobTypesList;
	}

	/**
	 * @param jobeTypesList
	 *            the companieList to set
	 */
	public void setJobTypesList(List<JobType> jobeTypesList) {
		this.jobTypesList = jobeTypesList;
		fireContentsChanged(this, 0, jobeTypesList.size());
	}

}
