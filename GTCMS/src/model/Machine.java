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

/**
 * @author Islam Morad
 * 
 */
@Entity(name = "Machine")
@Table(name = "Machines")
public class Machine extends AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6131630650095435836L;

	private long machineID;
	private String serialNumber;
	private String machineCode;
	private String description;
	private String machineName;
	private String section;
	private int sensorQuantity;
	private String manufacturer;
	private String model;

	private Department department;
	private List<Sensor> sensorsList = new ArrayList<>();
	private Location location;

	/**
	 * @return the machineID
	 */
	@Id
	@GeneratedValue
	@Column(name = "machine_id")
	public long getMachineID() {
		return machineID;
	}

	/**
	 * @param machineID
	 *            the machineID to set
	 */
	public void setMachineID(long machineID) {
		this.machineID = machineID;
	}

	/**
	 * @return the machineName
	 */
	@Column(name = "machine_name", nullable = false)
	//@NotEmpty(message = "machine name can't be left blank")
	public String getMachineName() {
		return machineName;
	}

	/**
	 * @param machineName
	 *            the machineName to set
	 */
	public void setMachineName(String machineName) {
		String oldValue = getMachineName();
		String newValue = machineName;
		this.machineName = machineName;
		firePropertyChange("name", oldValue, newValue);
	}

	/**
	 * @return the sensorQuantity
	 */
	@Column(name = "sensores_inside", nullable = false)
	public int getSensorQuantity() {
		return sensorQuantity;
	}

	/**
	 * @param sensorQuantity
	 *            the sensorQuantity to set
	 */
	public void setSensorQuantity(int sensorQuantity) {
		this.sensorQuantity = sensorQuantity;
	}



	/**
	 * @return the machineCode
	 */
	@Column(name = "machine_code", nullable = false, unique = true)
	public String getMachineCode() {
		return machineCode;
	}

	/**
	 * @param machineCode
	 *            the machineCode to set
	 */
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	/**
	 * @return the description
	 */
	@Column(name = "machine_description", nullable = false)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the section
	 */
	@Column(name = "machine_section", nullable = false)
	public String getSection() {
		return section;
	}

	/**
	 * @param section
	 *            the section to set
	 */

	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the manufacturer
	 */
	@Column(name = "machine_manufacturer", nullable = false)
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the serialNumber
	 */
	@Column(name = "serial_number", nullable = false)
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the model
	 */
	@Column(name = "machine_model", nullable = false)
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the sensorsList
	 */
	@OneToMany(mappedBy = "machine", cascade = { CascadeType.ALL })
	public List<Sensor> getSensorsList() {
		return sensorsList;
	}

	/**
	 * @param sensorsList
	 *            the sensorsList to set
	 */
	public void setSensorsList(List<Sensor> sensorsList) {
		this.sensorsList = sensorsList;
	}

	@ManyToOne
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne
	@JoinColumn(name="location_id")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return machineName;
	}
	
	

}
