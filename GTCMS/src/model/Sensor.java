/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import certificates.Certificate;

/**
 * @author Islam Morad
 * 
 */
@Entity
@Table(name = "sensors")
public class Sensor extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -1899029762693813562L;
	private long sensorID;
	private String code;
	private String manufacturer;
	private String serialNumber;
	private String model;
	private double resolution;
	private String description;
	private JobType jobType;
	private Machine machine;
	private boolean isCalibrated;
	private Date calDate;
	private Date calDueDate;
	private Collection<Certificate> certificatesList = new HashSet<>();

	/**
	 * @return the sensorsID
	 */
	@Id
	@GeneratedValue
	@Column(name = "sensor_id")
	public long getSensorsID() {
		return sensorID;
	}

	/**
	 * @param sensorsID
	 *            the sensorsID to set
	 */
	public void setSensorsID(long sensorsID) {
		this.sensorID = sensorsID;
	}

	/**
	 * @return the sensorCode
	 */
	@Column(name = "sensor_code", nullable = false, unique = true)
	public String getCode() {
		return code;
	}

	/**
	 * @param sensorCode
	 *            the sensorCode to set
	 */
	public void setCode(String sensorCode) {
		String oldValue = getCode();
		this.code = sensorCode;
		firePropertyChange("code", oldValue, getCode());
	}

	/**
	 * @return the manufacturer
	 */
	@Column(name = "manufacturer", nullable = false)
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		String oldValue = getManufacturer();
		this.manufacturer = manufacturer;
		firePropertyChange("manufacturer", oldValue, getManufacturer());
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
		String oldValue = getSerialNumber();
		this.serialNumber = serialNumber;
		firePropertyChange("serialNumber", oldValue, getSerialNumber());
	}

	/**
	 * @return the model
	 */
	@Column(name = "model", nullable = false)
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		String oldValue = getModel();
		this.model = model;
		firePropertyChange("model", oldValue, getModel());
	}

	/**
	 * @return the resolution
	 */
	@Column(name = "resolution", nullable = false)
	public double getResolution() {
		return resolution;
	}

	/**
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(double resolution) {
		double oldValue = getResolution();
		double newValue = resolution;
		this.resolution = resolution;
		firePropertyChange("resolution", oldValue, newValue);
	}

	/**
	 * @return the machine
	 */
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "machine_id")
	public Machine getMachine() {
		return machine;
	}

	/**
	 * @param machine
	 *            the machine to set
	 */
	public void setMachine(Machine machine) {
		Machine oldValue = getMachine();
		this.machine = machine;
		firePropertyChange("machine", oldValue, getMachine());
	}

	/**
	 * @return the description
	 */
	@Column(name = "sensor_description", nullable = false)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		String oldValue = getDescription();
		this.description = description;
		firePropertyChange("description", oldValue, getDescription());
	}

	/**
	 * @return the jobType
	 */
	@OneToOne(cascade = { CascadeType.ALL })
	public JobType getJobType() {
		return jobType;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobType(JobType jobType) {
		JobType oldValue = getJobType();
		this.jobType = jobType;
		firePropertyChange("jobType", oldValue, getJobType());
	}

	/**
	 * @return the isCalibrated
	 */
	@Column(name = "is_calibrated", nullable = false)
	public boolean isCalibrated() {
		return isCalibrated;
	}

	/**
	 * @param isCalibrated
	 *            the isCalibrated to set
	 */
	public void setCalibrated(boolean isCalibrated) {
		boolean oldValue = isCalibrated();
		boolean newValue = isCalibrated;
		this.isCalibrated = isCalibrated;
		firePropertyChange("isCalibrated", oldValue, newValue);

	}

	@OneToMany(mappedBy="sensor", cascade=CascadeType.ALL)
	public Collection<Certificate> getCertificatesList() {
		return certificatesList;
	}

	public void setCertificatesList(Collection<Certificate> certificatesList) {
		this.certificatesList = certificatesList;
	}

	/**
	 * @return the calDate
	 */
	@Column(name="cal_date")
	@Temporal(TemporalType.DATE)
	public Date getCalDate() {
		return calDate;
	}

	/**
	 * @param calDate
	 *            the calDate to set
	 */
	public void setCalDate(Date calDate) {
		Date oldValue = getCalDate();
		this.calDate = calDate;
		firePropertyChange("calDate", oldValue, getCalDate());
	}

	/**
	 * @return the calDueDate
	 */
	@Column(name="cal_due_date")
	@Temporal(TemporalType.DATE)
	public Date getCalDueDate() {
		return calDueDate;
	}

	/**
	 * @param calDueDate
	 *            the calDueDate to set
	 */
	public void setCalDueDate(Date calDueDate) {
		Date oldValue = getCalDueDate();
		this.calDueDate = calDueDate;
		firePropertyChange("caleDueDate", oldValue, getCalDueDate());
	}
	
	@Override
	public String toString() {
		return getCode();
	}

}
