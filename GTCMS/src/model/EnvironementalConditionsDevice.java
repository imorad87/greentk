package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "environemental_conditions_device")
public class EnvironementalConditionsDevice implements Serializable {

	private static final long serialVersionUID = -7960609289846315494L;
	private long deviceID;
	private String manufacturer;
	private String description;
	private String model;
	private String serialNumber;
	private double tempMinRange;
	private double tempMaxRange;
	private double humidityMinRange;
	private double humidityMaxRange;
	private double uncertianity;
	private Date calDueDate;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "condition_device_id")
	public long getId() {
		return deviceID;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.deviceID = id;
	}

	/**
	 * @return the manufacturer
	 */
	@Column(name = "manufacturer", nullable = false)
	//@NotEmpty(message="Manufacturer cannot left blank")
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
	 * @return the description
	 */
	@Column(name = "description", nullable = false)
	//@NotEmpty(message="description cannot left blank")
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
	 * @return the model
	 */
	@Column(name = "model", nullable = false)
	//@NotEmpty(message="Model cannot left blank")
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
	 * @return the serialNumber
	 */
	@Column(name = "serial_number", nullable = false, unique=true)
	//@NotEmpty(message="Serial number cannot be left blank")
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
	 * @return the tempMinRange
	 */
	@Column(name = "temp_mini_range", nullable = false)
	//@NotEmpty(message="temp_mini_range cannot left blank")
	public double getTempMinRange() {
		return tempMinRange;
	}

	/**
	 * @param tempMinRange
	 *            the tempMinRange to set
	 */
	public void setTempMinRange(double tempMinRange) {
		this.tempMinRange = tempMinRange;
	}

	/**
	 * @return the tempMaxRange
	 */
	@Column(name = "temp_max_range", nullable = false)
	//@NotEmpty(message="temp_max_range cannot left blank")
	public double getTempMaxRange() {
		return tempMaxRange;
	}

	/**
	 * @param tempMaxRange
	 *            the tempMaxRange to set
	 */
	public void setTempMaxRange(double tempMaxRange) {
		this.tempMaxRange = tempMaxRange;
	}

	/**
	 * @return the humidityMinRange
	 */
	@Column(name = "humidity_mini_range", nullable = false)
	//@NotEmpty(message="humidity_mini_range cannot left blank")
	public double getHumidityMinRange() {
		return humidityMinRange;
	}

	/**
	 * @param humidityMinRange
	 *            the humidityMinRange to set
	 */
	public void setHumidityMinRange(double humidityMinRange) {
		this.humidityMinRange = humidityMinRange;
	}

	/**
	 * @return the humidityMaxRange
	 */
	@Column(name = "humidity_max_range", nullable = false)
	//@NotEmpty(message="humidity_max_range cannot left blank")
	public double getHumidityMaxRange() {
		return humidityMaxRange;
	}

	/**
	 * @param humidityMaxRange
	 *            the humidityMaxRange to set
	 */
	public void setHumidityMaxRange(double humidityMaxRange) {
		this.humidityMaxRange = humidityMaxRange;
	}

	/**
	 * @return the uncertianity
	 */
	@Column(name = "uncertianity", nullable = false)
	//@NotEmpty(message="uncertianity cannot left blank")
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
	 * @return the calDueDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "cal_due_date", nullable = false)
	//@NotEmpty(message="cal_due_date cannot left blank")
	//@Future(message="Due date must be in the future")
	public Date getCalDueDate() {
		return calDueDate;
	}

	/**
	 * @param calDueDate
	 *            the calDueDate to set
	 */
	public void setCalDueDate(Date calDueDate) {
		this.calDueDate = calDueDate;
	}

}
