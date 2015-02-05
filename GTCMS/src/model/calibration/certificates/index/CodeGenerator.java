package model.calibration.certificates.index;

import java.util.List;

import model.Company;

import org.hibernate.Query;

import dao.DAO;

public class CodeGenerator {

	public static final int TS_INDEX = 0;

	public static final int TD_INDEX = 1;

	public static final int S_INDEX = 2;

	public static final int RPM_INDEX = 3;

	public static final int PG_INDEX = 4;

	public static final int PDG_INDEX = 5;

	public static final int COND_INDEX = 6;

	public static final int PH_INDEX = 7;

	public static final int RH_INDEX = 8;

	public static final int TH_INDEX = 9;

	private int autoIndex;

	private int manualIndex;

	private int lastAutoIndex;

	private int lastManualIndex;

	private long CompanyID;

	private int type;

	public CodeGenerator(long companyID, int type) {

		this.CompanyID = companyID;

		this.type = type;

		initAutoIndex();

		initManualIndex();

		int difference = checkDifference();

		if (difference == 1) {
			consolidateAutoManual();
		}
	}

	public int getCode() {

		int code = getAutoIndex() + 1;
		autoIndex = code;
		return code;
	}

	@SuppressWarnings("unchecked")
	private void initAutoIndex() {

		Index aIndex = null;
		Company company = null;
		Query query = null;
		List<Index> list = null;

		switch (getType()) {

		case TS_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TSCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (TSCertificateIndex) list.get(0);

			System.out.println(((TSCertificateIndex) aIndex).getId());

			autoIndex = ((TSCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((TSCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;

		case TD_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TDCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (TDCertificateIndex) list.get(0);

			System.out.println(((TDCertificateIndex) aIndex).getId());

			autoIndex = ((TDCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((TDCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;

		case S_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from SCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (SCertificateIndex) list.get(0);

			System.out.println(((SCertificateIndex) aIndex).getId());

			autoIndex = ((SCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((SCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;
		case RPM_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from RPMCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (RPMCertificateIndex) list.get(0);

			System.out.println(((RPMCertificateIndex) aIndex).getId());

			autoIndex = ((RPMCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((RPMCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;
		case PG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PGCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (PGCertificateIndex) list.get(0);

			System.out.println(((PGCertificateIndex) aIndex).getId());

			autoIndex = ((PGCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((PGCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;
		case PDG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PDGCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (PDGCertificateIndex) list.get(0);

			System.out.println(((PDGCertificateIndex) aIndex).getId());

			autoIndex = ((PDGCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((PDGCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;
		case COND_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from CONDCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (CONDCertificateIndex) list.get(0);

			System.out.println(((CONDCertificateIndex) aIndex).getId());

			autoIndex = ((CONDCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((CONDCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;
		case PH_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PHCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			aIndex = (PHCertificateIndex) list.get(0);

			System.out.println(((PHCertificateIndex) aIndex).getId());

			autoIndex = ((PHCertificateIndex) aIndex).getLastIndex();
			lastAutoIndex = ((PHCertificateIndex) aIndex).getLastIndex();
			DAO.commit();
			DAO.close();
			break;
		case RH_INDEX:
			// TODO implement rh index initalizer
		case TH_INDEX:
			// TODO implement th index initalizer
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void initManualIndex() {

		Index mIndex = null;
		Company company = null;
		Query query = null;
		List<Index> list = null;

		switch (getType()) {

		case TS_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TSCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (TSCertificateManualIndex) list.get(0);

			System.out.println(((TSCertificateManualIndex) mIndex).getId());

			
				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;

		case TD_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TDCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (TDCertificateManualIndex) list.get(0);

			System.out.println(((TDCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;

		case S_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from SCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (SCertificateManualIndex) list.get(0);

			System.out.println(((SCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;
		case RPM_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from RPMCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (RPMCertificateManualIndex) list.get(0);

			System.out.println(((RPMCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;
		case PG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PGCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (PGCertificateManualIndex) list.get(0);

			System.out.println(((PGCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;
		case PDG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PDGCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (PDGCertificateManualIndex) list.get(0);

			System.out.println(((PDGCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;
		case COND_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from CONDCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (CONDCertificateManualIndex) list.get(0);

			System.out.println(((CONDCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;
		case PH_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PHCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			if (list.size() > 0 ) {
			mIndex = (PHCertificateManualIndex) list.get(0);

			System.out.println(((PHCertificateManualIndex) mIndex).getId());

				manualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
				lastManualIndex = ((TSCertificateManualIndex) mIndex)
						.getLastIndex();
			}
			DAO.commit();
			DAO.close();
			break;
		case RH_INDEX:
			// TODO implement rh index initalizer
		case TH_INDEX:
			// TODO implement th index initalizer
		default:
			break;
		}

	}

	private int checkDifference() {
		int dif = getAutoIndex() - getManualIndex();
		return Math.abs(dif);
	}

	@SuppressWarnings("unchecked")
	private void consolidateAutoManual() {

		Index mIndex = null;
		int manualIndex = 0;
		Company company = null;
		Query query = null;
		List<Index> list = null;

		switch (getType()) {

		case TS_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TSCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (TSCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case TD_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TDCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (TDCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case S_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from SCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (SCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case RPM_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from RPMCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (RPMCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case PG_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PGCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (PGCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case PDG_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PDGCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (PDGCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
			
		case COND_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from CONDCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (CONDCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case PH_INDEX:
			manualIndex = getManualIndex();
			setAutoIndex(manualIndex);
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PHCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();

			mIndex = (PHCertificateManualIndex) list.get(0);

			mIndex.setLastIndex(0);
			DAO.commit();
			DAO.close();
			break;
		case RH_INDEX:
			break;
		case TH_INDEX:
			break;
		default:
			break;
		}

	}

	@SuppressWarnings("unchecked")
	public void commit() {

		Index aIndex = null;
		Company company = null;
		Query query = null;
		List<Index> list = null;

		switch (getType()) {

		case TS_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TSCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (TSCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.getSession().update(aIndex);
			DAO.commit();
			DAO.close();
			break;
		case TD_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TDCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (TDCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case S_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from SCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (SCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case RPM_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from RPMCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (RPMCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case PG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PGCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (PGCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case PDG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PDGCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (PDGCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case COND_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from CONDCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (CONDCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case PH_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PHCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (PHCertificateIndex) list.get(0);
			aIndex.setLastIndex(autoIndex);
			DAO.commit();
			DAO.close();
			break;

		case RH_INDEX:

			break;

		case TH_INDEX:

			break;

		default:
			break;
		}

	}

	@SuppressWarnings("unchecked")
	public void rollBack() {
		Index aIndex = null;
		Index mIndex = null;
		Company company = null;
		Query query = null;
		List<Index> list = null;

		switch (getType()) {

		case TS_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TSCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (TSCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from TSCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (TSCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;
		case TD_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from TDCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (TDCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from TDCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (TDCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case S_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from SCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (SCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from SCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (SCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case RPM_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from RPMCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (RPMCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from RPMCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (RPMCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case PG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PGCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (PGCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from PGCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (PGCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case PDG_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PDGCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (PDGCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from PDGCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (PDGCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case COND_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from CONDCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (CONDCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from CONDCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (CONDCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case PH_INDEX:
			DAO.begin();
			company = (Company) DAO.getSession().get(Company.class,
					getCompanyID());
			query = DAO.getSession().createQuery(
					"from PHCertificateIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			aIndex = (PHCertificateIndex) list.get(0);

			aIndex.setLastIndex(lastAutoIndex);
			query = DAO.getSession().createQuery(
					"from PHCertificateManualIndex where company = :company");
			query.setParameter("company", company);
			System.out.println(query.list().size());

			list = query.list();
			mIndex = (PHCertificateManualIndex) list.get(0);
			mIndex.setLastIndex(lastManualIndex);
			DAO.commit();
			DAO.close();
			break;

		case RH_INDEX:
			break;

		case TH_INDEX:
			break;

		default:
			break;
		}

		DAO.begin();
		company = (Company) DAO.getSession().get(Company.class, getCompanyID());
		query = DAO.getSession().createQuery(
				"from TSCertificateIndex where company = :company");
		query.setParameter("company", company);
		System.out.println(query.list().size());

		list = query.list();
		aIndex = (TSCertificateIndex) list.get(0);

		aIndex.setLastIndex(lastAutoIndex);
		query = DAO.getSession().createQuery(
				"from TSCertificateManualIndex where company = :company");
		query.setParameter("company", company);
		System.out.println(query.list().size());

		list = query.list();
		mIndex = (TSCertificateManualIndex) list.get(0);
		mIndex.setLastIndex(lastManualIndex);
		DAO.commit();
		DAO.close();
	}

	/**
	 * @return the autoIndex
	 */
	public int getAutoIndex() {
		return autoIndex;
	}

	/**
	 * @param autoIndex
	 *            the autoIndex to set
	 */
	public void setAutoIndex(int autoIndex) {
		this.autoIndex = autoIndex;
	}

	/**
	 * @return the manualIndex
	 */
	public int getManualIndex() {
		return manualIndex;
	}

	/**
	 * @param manualIndex
	 *            the manualIndex to set
	 */
	public void setManualIndex(int manualIndex) {
		this.manualIndex = manualIndex;
	}

	public int getLastAutoIndex() {
		return lastAutoIndex;
	}

	public void setLastAutoIndex(int lastAutoIndex) {
		this.lastAutoIndex = lastAutoIndex;
	}

	public int getLastManualIndex() {
		return lastManualIndex;
	}

	public void setLastManualIndex(int lastManualIndex) {
		this.lastManualIndex = lastManualIndex;
	}

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return CompanyID;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(long companyID) {
		CompanyID = companyID;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
