package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author Islam Morad
 * 
 */
@Entity
@Table(name = "phones")
public class Phone extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long phoneID;
	private String number;
	private String fax;
	private Company company;

	/**
	 * @return the phoneID
	 */
	@Id
	@GeneratedValue
	@Column(name = "phone_id", nullable = false)
	public long getPhoneID() {
		return phoneID;
	}

	/**
	 * @param phoneID
	 *            the phoneID to set
	 */
	public void setPhoneID(long phoneID) {
		this.phoneID = phoneID;
	}

	/**
	 * @return the number
	 */
	@Column(name = "phone_number", nullable = false, unique=true)
	//@NotEmpty(message="You have to Enter a phone number")
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the fax
	 */
	@Column(name = "fax_number", nullable = false, unique=true)
	//@NotEmpty(message="You have to enter the fax number")
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the company
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

}
