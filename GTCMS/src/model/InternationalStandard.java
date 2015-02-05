package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "international_standard")
public class InternationalStandard extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String standardCode;

	/**
	 * @return the id
	 */
	@Id
	@Column(name = "standard_id")
	@GeneratedValue
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	@Column(name = "Standard_Code", nullable=false, unique=true)
	//@NotEmpty(message="Stndared code cannot be left blank")
	public String getStandardCode() {
		return standardCode;
	}

	/**
	 * @param standardCode
	 *            the description to set
	 */
	public void setStandardCode(String standardCode) {
		
		String oldValue = getStandardCode();
		String newValue = standardCode;
		this.standardCode = standardCode;
		firePropertyChange("standardCode", oldValue, newValue);
	}
	
	@Override
	public String toString() {
		return getStandardCode();
	}

}
