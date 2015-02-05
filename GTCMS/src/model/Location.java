package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "locations")
public class Location extends AbstractModel implements Serializable, Comparable<Location> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long locationID;
	private String location;
	private String description;
	private Department department;
	private List<Machine> machinesList = new ArrayList<Machine>();


	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name="location_id")
	public long getId() {
		return locationID;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		long oldValue = this.locationID;
		this.locationID = id;
		firePropertyChange("id", oldValue, this.locationID);
	}

	/**
	 * @return the location
	 */
	@Column(name = "location")
	//@NotBlank(message = "location cannot left blank")
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		String oldValue = this.location;
		this.location = location;
		firePropertyChange("location", oldValue, this.location);
	}

	/**
	 * @return the description
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		String oldValue = this.description;
		this.description = description;
		firePropertyChange("description", oldValue, this.description);
	}

	@ManyToOne
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		Department oldValue = this.department;
		this.department = department;
		firePropertyChange("department", oldValue, this.department);
	}

	@OneToMany(mappedBy="location", cascade=CascadeType.ALL)
	public List<Machine> getMachinesList() {
		return machinesList;
	}

	public void setMachinesList(List<Machine> machinesList) {
		this.machinesList = machinesList;

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
		result = prime * result + (int) (locationID ^ (locationID >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (locationID != other.locationID)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getLocation();
	}

	@Override
	public int compareTo(Location o) {
		
		return this.getLocation().compareTo(o.getLocation());
	}

}
