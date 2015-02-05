package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import model.Location;
import model.Machine;

public class LocationTask extends DAO {

	public List<Location> getAllLocations() {
		begin();
		Criteria criteria = getSession().createCriteria(Location.class);
		commit();
		close();
		return criteria.list();
	}
	
	public List<Machine> getAllMAchines(Location location){
		List<Machine> list = new ArrayList<>();
		begin();
		Query query = getSession().createQuery("from Machine where location = :location");
		query.setParameter("location", location);
		list = query.list();
		commit();
		close();
		return list;
	}
	
	
	public Location getLocation(long id){
		Location location = null;
		try {
			begin();
			location = (Location) getSession().get(Location.class, id);
		} catch (HibernateException e) {
			rollback();
			throw e;
		}finally{
			close();
		}
		return location;
	}
	
	public void save(Location location){
		try {
			begin();
			getSession().saveOrUpdate(location);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		}finally{
			close();
		}
	}

	public void update(Location location) {
		try {
			begin();
			getSession().update(location);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		}finally{
			close();
		}
		
	}

}
