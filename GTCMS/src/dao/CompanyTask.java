/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Department;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Islam Morad
 * 
 */
public class CompanyTask extends DAO {

	public synchronized void addCompany(Company company) throws HibernateException{
		//boolean exist = companyExists(company);
		//if (!exist) {
			try {
				begin();
				getSession().save(company);
				commit();
				close();
			} catch (HibernateException e) {
				throw e;
			}finally{
				close();
			}
			//return true;
		//} 
		//return false;
	}

	public synchronized void updateCompany(Company company) throws HibernateException{
		try {
			begin();
			getSession().update(company);
			commit();
		} catch (HibernateException e) {
			throw e;
		}finally{
			close();
		}
	}

	/*public boolean companyExists(Company company) throws HibernateException{
		try {
			begin();
			Company loadedCompany = (Company) getSession().get(Company.class,
					company.getCompanyID());
			if (loadedCompany != null) {
				return true;
			} 
		} catch (HibernateException e) {
			throw new HibernateException(e.getMessage());
		}finally{
			close();
		}
		return false;
	}*/

	public synchronized List<Company> getAllCompanies() {
		List<Company> list = null;
		try{
		begin();
		 list = getSession().createCriteria(Company.class).list();
		commit();
		}catch (HibernateException e){
			throw e;
			
		}finally{
			close();
		}	
		
		return list;

	}
	
	
	public synchronized List<Company> getAllWithName(String name) {
		Session session = getSession();
		Query query = session.getNamedQuery("company.getAllWithName");
		query.setString("name", name);
		List<Company> list = query.list();
		commit();
		close();
		return list;
	}
	

	public Company getByName(Company company) throws HibernateException{
		Company c;
		
		try {
			begin();
			
			c = (Company) getSession().get(Company.class, new Long(company.getCompanyID()));
			commit();
			
			
		} catch (HibernateException e) {
			
			throw e;
		}finally{
			close();
		}
		return c;
	}
	
	public Company getByCode(String code) throws HibernateException{
		Company c;
		try {
			begin();
			Query query = getSession().getNamedQuery("company.byCode");
			query.setString(0, code);
			List<Company> list = query.list();
			
			c = list.get(0);
			commit();
			
		} catch (HibernateException e) {
			throw e;
		}finally{
			close();
		}
		return c;
	}

	public synchronized Company getByID(long id) throws HibernateException{
		try {
			begin();
			Company c = (Company)getSession().get(Company.class, id);
			if(c != null){
				commit();
				close();
				return c;
			}else{
				rollback();
				return null;
			}
		} catch (HibernateException e) {
			throw new HibernateException(e.getMessage());
		}finally{
			close();
		}
	}
	
	
	public List<Department> getAllDepartments(Company company){
		List<Department> list = new ArrayList<>();
		
		try{
		begin();
		Query query = getSession().createQuery("from Department where company = :company");
		query.setParameter("company", company);
		list = query.list();
		commit();
		}finally {
			close();
		}
		return list;
	}
}
