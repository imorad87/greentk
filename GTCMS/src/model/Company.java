package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * @author Islam Morad
 */
@Entity
@Table(name = "companies")
@NamedNativeQueries({
		@NamedNativeQuery(name = "company.byCode", query = "select * from companies where company_code = ?", resultClass = Company.class),
		@NamedNativeQuery(name = "company.getAllWithName", query = "select * from companies where company_name regexp :name", resultClass = Company.class),
		@NamedNativeQuery(name = "company.getByName", query = "select * from companies where company_name == :name", resultClass = Company.class),
		@NamedNativeQuery(name = "company.getByID", query = "select * from companies where company_id == :id", resultClass = Company.class) })
public class Company extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private long companyID;
	private String companyCode;
	private String companyName;
	// private Collection<Phone> phonesList = new HashSet<>();
	private List<Department> departmentsList = new ArrayList<>();
	// private Collection<Address> addressesList = new HashSet<>();
	private String address;
	private String phoneNumber;
	private String faxNumber;
	

	@Id
	@GeneratedValue
	@Column(name = "company_id", nullable = false)
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * A method to set a new id for the company
	 * 
	 * @param companyID
	 *            the new id of the company
	 */
	public void setCompanyID(long companyID) {
		long oldValue = this.companyID;
		this.companyID = companyID;
		firePropertyChange("id", oldValue, this.companyID);
	}

	/**
	 * A method to return the name of the company
	 * 
	 * @return the company name;
	 */
	@Column(name = "company_name", nullable = false, unique = true)
	//@NotEmpty(message = "Company name cannot be left empty")
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		String oldValue = this.companyName;
		this.companyName = companyName;
		firePropertyChange("name", oldValue, this.companyName);
	}

	/**
	 * @return the companyCode
	 */
	@Column(name = "company_code", nullable = false)
	//@NotEmpty(message = "You have to enter a code for the company")
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 *            the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		String oldValue = this.companyCode;
		this.companyCode = companyCode;
		firePropertyChange("code", oldValue, this.companyCode);
	}

	/**
	 * @return the departmentsList fetch = FetchType.EAGER,
	 */
	@OneToMany( mappedBy = "company", cascade = CascadeType.ALL)
	public List<Department> getDepartmentsList() {
		return departmentsList;
	}

	/**
	 * @param departmentsList
	 *            the departmentsList to set
	 */
	public void setDepartmentsList(List<Department> departmentsList) {
		List<Department> oldValue = this.departmentsList;
		this.departmentsList = departmentsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyID ^ (companyID >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
		if (companyID != other.companyID) {
			return false;
		}
		return true;
	}

	/*
	 * @OneToMany(mappedBy = "company", cascade=CascadeType.ALL) public
	 * Collection<Phone> getPhonesList() { return phonesList; }
	 * 
	 * public void setPhonesList(Collection<Phone> phonesList) { this.phonesList
	 * = phonesList; }
	 */

	/**
	 * @return the address
	 */
	@Column(name = "address", nullable = false)
	//@NotEmpty(message = "You should enter the address")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		String oldValue = this.address;
		this.address = address;
		firePropertyChange("address", oldValue, this.address);
	}

	/**
	 * @return the phoneNumber
	 */
	@Column(name = "phone_number", nullable = false, unique = true)
	//@NotEmpty(message = "You should enter the phone number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		String oldValue = this.phoneNumber;
		this.phoneNumber = phoneNumber;
		firePropertyChange("phone", oldValue, this.phoneNumber);
	}

	/**
	 * @return the faxNumber
	 */
	@Column(name = "fax_number", nullable = false, unique = true)
	//@NotEmpty(message = "You should enter the fax number")
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber
	 *            the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		String oldValue = this.faxNumber;
		this.faxNumber = faxNumber;
		firePropertyChange("fax", oldValue, this.getFaxNumber());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return companyName;
				
	}


	/**
	 * @return the addressesList
	 */
	/*
	 * @ManyToMany(cascade=CascadeType.ALL)
	 * 
	 * @JoinTable(name="companies_addresses",
	 * joinColumns=@JoinColumn(name="compnay_id"),
	 * inverseJoinColumns=@JoinColumn(name="address_id")) public
	 * Collection<Address> getAddressesList() { return addressesList; }
	 *//**
	 * @param addressesList
	 *            the addressesList to set
	 */
	/*
	 * public void setAddressesList(Collection<Address> addressesList) {
	 * this.addressesList = addressesList; }
	 */

}
