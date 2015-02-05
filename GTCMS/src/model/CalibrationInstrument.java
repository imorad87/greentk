/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.joda.time.Days;

import certificates.Certificate;


/**
 * @author Islam
 * 
 */
@Entity
@Table(name = "instruments")
public class CalibrationInstrument extends AbstractModel implements Comparable<CalibrationInstrument> {

	private long instrumentID;
	private String instrumentCode;
	private String name;
	private String description;
	private String manufacturer;
	private Date calibrationDate;
	private Date calibrationDueDate;
	private double maxRange;
	private double minRange;
	private String serialNumber;
	private String model;
	private double uncertianity;
	
	private int remainingDays;
	private boolean isValid;
	
	
	private List<JobType> jobTypesList = new ArrayList<JobType>();

	private List<Certificate> certificatesList = new ArrayList<>();
	/**
	 * @return the instrumentID
	 */
	@Id
	@GeneratedValue
	@Column(name = "instrument_id", nullable = false)
	public long getInstrumentID() {
		return instrumentID;
	}

	/**
	 * @param instrumentID
	 *            the instrumentID to set
	 */
	public void setInstrumentID(long instrumentID) {
		this.instrumentID = instrumentID;
	}

	/**
	 * @return the name
	 */
	@Column(name = "instrument_name", nullable = false)
	//@NotEmpty(message="instrument name cannot be left blank")	
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	@Column(name = "description", nullable = false)
	//@NotEmpty(message="Description name cannot be left blank")	
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
	 * @return the manufacturer
	 */
	@Column(name = "manufacturer_name", nullable = false)
	//@NotEmpty(message="manufacturer_name name cannot be left blank")
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
	 * @return the calibrationDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "calibration_date", nullable = false)
	public Date getCalibrationDate() {
		return calibrationDate;
	}

	/**
	 * @param calibrationDate
	 *            the calibrationDate to set
	 */
	public void setCalibrationDate(Date calibrationDate) {
		this.calibrationDate = calibrationDate;
	}

	/**
	 * @return the calibrationDueDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "cal_due_date", nullable = false)
	public Date getCalibrationDueDate() {
		return calibrationDueDate;
	}

	/**
	 * @param calibrationDueDate
	 *            the calibrationDueDate to set
	 */
	public void setCalibrationDueDate(Date calibrationDueDate) {
		this.calibrationDueDate = calibrationDueDate;
	}

	/**
	 * @return the instrumentCode
	 */
	@Column(name = "instrument_code", nullable = false, unique=true)
	//@NotEmpty(message="instrument_code cannot be left blank")
	public String getInstrumentCode() {
		return instrumentCode;
	}

	/**
	 * @param instrumentCode
	 *            the instrumentCode to set
	 */
	public void setInstrumentCode(String instrumentCode) {

		this.instrumentCode = instrumentCode;

	}

	/**
	 * @return the maxRange
	 */
	@Column(name = "maximum_range", nullable = false)
	//@NotEmpty(message="maximum_range cannot be left blank")
	public double getMaxRange() {
		return maxRange;
	}

	/**
	 * @param maxRange
	 *            the maxRange to set
	 */
	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	/**
	 * @return the minRange
	 */
	@Column(name = "minimum_range", nullable = false)
	//@NotEmpty(message="Minimum_Range cannot be left blank")
	public double getMinRange() {
		return minRange;
	}

	/**
	 * @param minRange
	 *            the minRange to set
	 */
	public void setMinRange(double minRange) {
		this.minRange = minRange;
	}

	/**
	 * @return the serialNumber
	 */
	@Column(name = "serial_number", nullable = false)
	//@NotEmpty(message="Serial_Number cannot be left blank")
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
	@Column(name = "model", nullable = false)
	//@NotEmpty(message="model cannot be left blank")
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
	 * @return the uncertianity
	 */
	@Column(name = "uncertianity", nullable = false)
	//@NotEmpty(message="uncertianity cannot be left blank")
	public double getUncertianity() {
		return uncertianity;
	}

	/**
	 * @param uncertianity
	 *            the uncertianity to set
	 */
	public void setUncertianity(double uncertianity) {
		this.uncertianity = uncertianity;
	}

	/**
	 * @return the jobType
	 */
	
	@ManyToMany(cascade= {CascadeType.ALL})
	@JoinTable(name="instruments_jobtypes", joinColumns=@JoinColumn(name="instrument_id"), inverseJoinColumns=@JoinColumn(name="jobtype_id"))
	public List<JobType> getJobTypesList() {
		return jobTypesList;
	}

	/**
	 * @param jobType
	 *            the jobType to set
	 */
	public void setJobTypesList(List<JobType> jobTypesList) {
		this.jobTypesList = jobTypesList;
	}

	@ManyToMany(mappedBy="calibrationInstrumentsList")
	public List<Certificate> getCertificatesList() {
		return certificatesList;
	}

	public void setCertificatesList(List<Certificate> certificatesList) {
		this.certificatesList = certificatesList;
	}

	@Override
	public String toString() {
	
		return getName() + " - " + getInstrumentCode();
	}
	
	
	@Column(name = "valid")
	public boolean isValid() {
		if (getRemainingDays() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the remainingDays
	 */
	@Column(name = "remaining_days")
	public int getRemainingDays() {
		DateTime today = new DateTime(new Date().getTime());
		DateTime dueDate = new DateTime(getCalibrationDueDate().getTime());
		return Days.daysBetween(today, dueDate).getDays();
	}

	/**
	 * @param remainingDays the remainingDays to set
	 */
	public void setRemainingDays(int remainingDays) {
		this.remainingDays = remainingDays;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public int compareTo(CalibrationInstrument o) {
		
		return this.getName().compareTo(o.getName());
	}
}
