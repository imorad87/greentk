/**
 * 
 */
package dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import model.JobType;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

/**
 * @author Islam
 * 
 */
public class JobTypeTask extends DAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.JobTypeFuncationality#getAll()
	 */
	@Transactional
	public ArrayList<JobType> getAll() throws HibernateException {

		ArrayList<JobType> list = null;

		try {
			begin();
			Criteria c = getSession().createCriteria(JobType.class);
			list = (ArrayList<JobType>) c.list();
			commit();
		} finally {
			close();
		}

		return list;
	}

	public JobType get(long cmsID) {
		JobType type;
		try {
			begin();
			type = (JobType) getSession().get(
					JobType.class, cmsID);
			if (type != null) {
				commit();
			} else {
				rollback();
			}
		} catch (HibernateException e) {
			rollback();
			throw e;
		} finally {
			close();
		}
		return type;
	}
	
	public void save(JobType type){
		try{
			begin();
			getSession().saveOrUpdate(type);
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		}finally{
			close();
		}
	}
	
	public void update(JobType type){
		try{
			begin();
			getSession().update(type);
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		}finally{
			close();
		}
	}

}
