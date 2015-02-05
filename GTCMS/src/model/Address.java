package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



/**
 * 
 * @author Islam Morad
 */
@Entity
@Table(name = "addresses")
public class Address extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7162754693551897604L;
	private long addressID;
	private String street;
	
	private Collection<Company> companiesList = new HashSet<>();

	/**
	 * @return the addressID
	 */
	@Id
	@GeneratedValue
	@Column(name = "address_id", nullable = false)
	public long getAddressID() {
		return addressID;
	}

	/**
	 * @param addressID
	 *            the addressID to set
	 */
	public void setAddressID(long addressID) {
		
		this.addressID = addressID;
		
		
	}

	/**
	 * @return the street
	 */
	@Column(name = "address", nullable = false, unique=true)
	//@NotEmpty(message="You have to enter the address")
	public String getStreet() {
		
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		String oldValue = getStreet();
		this.street = street;
		firePropertyChange("address", oldValue, this.street);
	}

	@ManyToMany(mappedBy="addressesList", cascade=CascadeType.ALL)
	public Collection<Company> getCompaniesList() {
		return companiesList;
	}

	public void setCompaniesList(Collection<Company> companiesList) {
		this.companiesList = companiesList;
	}



	

}
