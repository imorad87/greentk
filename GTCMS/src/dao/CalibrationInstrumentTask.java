/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import model.CalibrationInstrument;
import model.JobType;

import org.hibernate.HibernateException;

/**
 * @author Islam
 * 
 */
public class CalibrationInstrumentTask extends DAO {

	public List<JobType> getAllJobTypes(Long id) {
		List<JobType> jobTypesList = new ArrayList<>();
		try {
			begin();
			CalibrationInstrument device = (CalibrationInstrument) getSession()
					.get(CalibrationInstrument.class, id);
			jobTypesList.addAll(device.getJobTypesList());
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		} finally {
			close();
		}

		return jobTypesList;
	}

	public List<CalibrationInstrument> getAll() {
		List<CalibrationInstrument> list = null;
		try {
			begin();
			list = getSession().createQuery(
					"from " + CalibrationInstrument.class.getName()).list();
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		} finally {
			close();

		}

		return list;
	}

	public void save(CalibrationInstrument instrument)
			throws HibernateException {

		try {
			begin();
			getSession().saveOrUpdate(instrument);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		} finally {
			close();
		}
	}

	public CalibrationInstrument get(long cmsID) {
		CalibrationInstrument device;
		try {
			begin();
			device = (CalibrationInstrument) getSession().get(
					CalibrationInstrument.class, cmsID);
			if (device != null) {
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
		return device;
	}

	public void update(CalibrationInstrument instrument) {
		try {
			begin();
			getSession().update(instrument);
			commit();
		} catch (HibernateException e) {
			throw e;
		} finally {
			close();
		}
	}

}
