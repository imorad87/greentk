package dao;


import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Location;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DepartmentTask extends DAO{
	
	
	public synchronized List getAllDepartments() {
		try {
			begin();
			Query query = getSession().getNamedQuery("department.getAll");
			List list = query.list();
			commit();
			close();
			return list;
		} catch (HibernateException e) {
			throw e;
		}finally{
			close();
		}
	}

	public Department getDepartment(Department department) {
		try {
			Session session = getSession();
			begin();
			Department dep = (Department) session.get(Department.class, new Long(department.getId()));
			commit();
			close();
			return dep;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			throw new HibernateException(e.getMessage());
		}finally{
			close();
		}
	}
	
	public List<Location> getAllLocation(Department department){
		begin();
		List<Location> list = new ArrayList<>();
		Query query = getSession().createQuery("from Location where department = :department");
		query.setParameter("department", department);
		list = query.list();
		commit();
		close();
		return list;
	}

	public void save(Department dep) {
		try {
			begin();
			getSession().saveOrUpdate(dep);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		}finally{
			close();
		}
		
	}

	public Department get(long depID) {
		Department dep = null;
		
		try {
			begin();
			dep = (Department) getSession().get(Department.class, depID);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		}finally{
			close();
		}
		
		return dep;
		
	}

	public void update(Department department) {
		try {
			begin();
			getSession().update(department);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw e;
		}finally{
			close();
		}
		
		
	}

}
