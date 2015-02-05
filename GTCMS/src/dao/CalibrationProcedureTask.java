/**
 * 
 */
package dao;

import java.util.List;

import model.CalibrationProcedure;
import org.hibernate.HibernateException;

/**
 * @author Islam
 * 
 */
public class CalibrationProcedureTask extends DAO {

	
	public List<CalibrationProcedure> getAll() throws HibernateException {
		List list = null;
		try {
			begin();
			list = getSession().createQuery(
					"from " + CalibrationProcedure.class.getName()).list();
			commit();
		} finally {
			close();

		}

		return list;
	}
	
	public CalibrationProcedure get(long cmsID) {
		CalibrationProcedure procedure;
		try {
			begin();
			procedure = (CalibrationProcedure) getSession().get(
					CalibrationProcedure.class, cmsID);
			if (procedure != null) {
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
		return procedure;
	}
	
	
	public void save(CalibrationProcedure procedure){
		try{
			begin();
			getSession().saveOrUpdate(procedure);
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		}finally{
			close();
		}
	}
	
	public void update(CalibrationProcedure procedure){
		try{
			begin();
			getSession().update(procedure);
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		}finally{
			close();
		}
	}

}
