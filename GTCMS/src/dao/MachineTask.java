package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import model.Machine;
import model.Sensor;

public class MachineTask extends DAO {

	public List<Machine> getAllMachines() {
		begin();
		Session session = getSession();
		Query query = session.createQuery("from Machine");
		List<Machine> list = query.list();
		close();
		return list;

	}

	public void saveMachine(Machine machine) {
		try {
			begin();
			Session session = getSession();
			session.save(machine);
			commit();
			close();
		} catch (HibernateException e) {
			throw e;
		} finally {
			close();
		}

	}

	public Machine getMachine(Machine machine) {
		begin();
		Session session = getSession();
		Machine m = (Machine) session.get(Machine.class,
				new Long(machine.getMachineID()));
		commit();
		close();
		return m;
	}

	public Machine getByID(Long machineId) {
		begin();
		Machine machine = (Machine) getSession().get(Machine.class, machineId);
		commit();
		close();
		return machine;
	}

	public void updateMachine(Machine machine) {
		try {
			begin();
			getSession().update(machine);
			commit();
		} finally {
			close();

		}

	}
	
	public List<Sensor> getAllSensors(Machine machine){
		try {
			begin();
			Query query = getSession().createQuery("from Sensor where machine = :machine");
			query.setParameter("machine", machine);
			List<Sensor> list = query.list();
			commit();
			close();
			return list;
		} finally {
			close();

		}
		
	}

}
