package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "departments")
@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "department.getAll", query = "select * from departments", resultClass = Department.class),
		@NamedNativeQuery(name = "department.getAllWithName", query = "select * from departments where name regexp :name", resultClass = Department.class),
		@NamedNativeQuery(name = "department.getByName", query = "select * from departments where name == :name", resultClass = Department.class),
		@NamedNativeQuery(name = "department.getByID", query = "select * from departments where id == :id", resultClass = Department.class),
		@NamedNativeQuery(name = "department.getByCompanyID", query = "select * from departments where company_id == :id", resultClass = Department.class) })
public class Department extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long departmentID;
	private String departmentName;
	private String departmentDescription;
	private Company company;
	private List<Location> locationsList = new ArrayList<>();

	

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return departmentID;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		long oldValue = this.departmentID;
		this.departmentID = id;
		firePropertyChange("id", oldValue, this.departmentID);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return departmentName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		String oldValue = this.departmentName;
		this.departmentName = name;
		firePropertyChange("name", oldValue, this.departmentName);

	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return departmentDescription;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		String oldValue = this.departmentDescription;
		this.departmentDescription = description;
		firePropertyChange("description", oldValue, this.departmentDescription);

	}

	/**
	 * @return the company
	 */
	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		Company oldValue = this.company;
		this.company = company;
		firePropertyChange("company", oldValue, this.company);

	}

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	//@LazyCollection(LazyCollectionOption.FALSE)
	public List<Location> getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(List<Location> locationsList) {
		this.locationsList = locationsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}

}
