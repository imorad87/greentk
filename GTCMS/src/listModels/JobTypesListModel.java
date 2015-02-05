package listModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import model.JobType;

public class JobTypesListModel extends AbstractListModel<JobType> {
	
	private static final long serialVersionUID = 6379657166148069491L;
	
	private List<JobType> jopTypesList;
	
	public JobTypesListModel(){
		jopTypesList = new ArrayList<>();
	}
	

	@Override
	public JobType getElementAt(int index) {
		
		return (jopTypesList.get(index));
	}

	@Override
	public int getSize() {
		
		return jopTypesList.size();
	}

	public List<JobType> getJobTypesList() {
		return jopTypesList;
	}

	public void setJobTypesList(List<JobType> JobTypesList) {
		Collections.sort(JobTypesList);
		this.jopTypesList = JobTypesList;
	}

	

}
