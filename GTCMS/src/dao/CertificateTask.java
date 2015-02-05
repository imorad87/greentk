package dao;

import org.hibernate.HibernateException;

import certificates.Certificate;

public class CertificateTask extends DAO {

	public void save(Certificate certificate) {
		try {
			begin();
			getSession().save(certificate);
			commit();
		} catch (HibernateException e) {
			throw e;
		} finally {
			close();
		}

	}

	public Certificate getCertificate(Long id) {
		Certificate certificate = null;
		try {
			begin();
			certificate = (Certificate) getSession().get(Certificate.class, id);
			commit();

		} catch (HibernateException e) {
			throw e;
		} finally {
			close();
		}
		return certificate;
	}
	
	public void update(Certificate certificate){
		try {
			begin();
			getSession().update(certificate);
			commit();
		} catch (HibernateException e) {
			throw e;
		} finally {
			close();
		}
	}

}
