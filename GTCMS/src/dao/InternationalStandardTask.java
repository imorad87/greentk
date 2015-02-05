/**
 * 
 */
package dao;

import java.util.List;

import org.hibernate.HibernateException;

import model.InternationalStandard;

/**
 * @author Islam
 * 
 */
public class InternationalStandardTask extends DAO {

	public List<InternationalStandard> getAll() {
		List list = null;
		try {
			begin();
			list = getSession().createQuery(
					"from " + InternationalStandard.class.getName()).list();
			commit();
		} finally {
			close();

		}

		return list;
	}
	
	public InternationalStandard get(long cmsID) {
		InternationalStandard standard;
		try {
			begin();
			standard = (InternationalStandard) getSession().get(
					InternationalStandard.class, cmsID);
			if (standard != null) {
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
		return standard;
	}
	
	public void save(InternationalStandard standard){
		try{
			begin();
			getSession().saveOrUpdate(standard);
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		}finally{
			close();
		}
	}
	
	public void update(InternationalStandard standard){
		try{
			begin();
			getSession().update(standard);
			commit();
		}catch (HibernateException e){
			rollback();
			throw e;
		}finally{
			close();
		}
	}


}
