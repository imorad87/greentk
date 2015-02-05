package dao;

import java.util.ArrayList;
import java.util.List;

import model.Machine;
import model.Sensor;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import certificates.Certificate;

public class SensorTask extends DAO {

	public synchronized Sensor getByID(long id) throws HibernateException {
		try {
			Sensor sensor = (Sensor) getSession().get(Sensor.class, id);
			if (sensor != null) {
				close();
				return sensor;
			} else {
				rollback();
				close();
				return null;
			}

		} catch (Exception e) {

		} finally {
			close();
		}
		return null;

	}

	public void update(Sensor sensor) {
		begin();
		getSession().merge(sensor);
		commit();
		close();
	}

	public List<Sensor> getAllSensors(Machine machine) {
		begin();
		List<Sensor> list = new ArrayList<>();
		Query query = getSession().createQuery(
				"from Sensor where machine = :machine");
		query.setParameter("machine", machine);
		list = query.list();
		commit();
		close();
		return list;
	}

	public void create(Sensor sensor) {
		try {
			begin();
			getSession().save(sensor);
			commit();
		}catch (HibernateException e){
			throw e;
		}finally {
			close();
		}

	}
	
	public List<Certificate> getAllCertificates(Sensor sensor){
		/*JobType jobType = sensor.getJobType();
		if(jobType.getSymbol().equalsIgnoreCase("ts")){
			begin();
			List<Certificate> list = new ArrayList<>();
			Query query = getSession().createQuery(
					"from " +TSCertificate.class.getName()+  " where sensor = :sensor");
			query.setParameter("sensor", sensor);
			list = query.list();
			System.out.println(list.size());
			commit();
			close();
			return list;
		}else{
			return null;
		}*/
		begin();
		List<Certificate> list = new ArrayList<>();
		Query query = getSession().createQuery(
				"from " +Certificate.class.getName()+  " where sensor = :sensor");
		query.setParameter("sensor", sensor);
		list = query.list();
		commit();
		close();
		return list;
		
	}

}
