package certificates;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.AbstractModel;
import model.CalibrationInstrument;
import model.CalibrationProcedure;
import model.InternationalStandard;
import model.Sensor;

import org.joda.time.DateTime;
import org.joda.time.Days;

@Entity(name = "certificates")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Certificate extends AbstractModel implements Serializable {

	protected static final long serialVersionUID = 5853171067393399280L;
	
	protected long certificateID;
	protected String certificateCode;
	private int index;
	protected Date issueDate;
	protected String calEnvironment;
	protected double minCalRange;
	protected double maxCalRange;
	protected String statusBefore;
	protected String statusAfter;
	protected double acceptedLimit;
	protected int remainingDays;
	protected boolean isValid;
	private String measurementUnit;
	

	protected Collection<CalibrationProcedure> calProcedureList = new HashSet<>();
	protected Collection<InternationalStandard> intStandardList = new HashSet<>();

	protected Collection<CalibrationInstrument> calibrationInstrumentsList = new HashSet<CalibrationInstrument>();

	protected double environmentTemp;
	protected double environmentHumidity;

	protected double uncertianity;

	protected String certificateStatus;

	protected Date receivedDate;
	protected Date calibrationDate;
	protected Date calDueDate;

	protected String calibratedBy;
	protected String approvedBy;

	protected Sensor sensor;

	/**
	 * @return the calEnvironment
	 */
	@Column(name = "cal_environment")
	public String getCalEnvironment() {
		return calEnvironment;
	}

	/**
	 * @param calEnvironment
	 *            the calEnvironment to set
	 */
	public void setCalEnvironment(String calEnvironment) {
		this.calEnvironment = calEnvironment;
	}

	/**
	 * @return the calProcedureList
	 */
	@ManyToMany
	public Collection<CalibrationProcedure> getCalProcedureList() {
		return calProcedureList;
	}

	/**
	 * @param calProcedureList
	 *            the calProcedureList to set
	 */
	public void setCalProcedureList(
			Collection<CalibrationProcedure> calProcedureList) {
		this.calProcedureList = calProcedureList;
	}

	/**
	 * @return the intStandardList
	 */
	@ManyToMany
	public Collection<InternationalStandard> getIntStandardList() {
		return intStandardList;
	}

	/**
	 * @param intStandardList
	 *            the intStandardList to set
	 */
	public void setIntStandardList(
			Collection<InternationalStandard> intStandardList) {
		this.intStandardList = intStandardList;
	}

	/**
	 * @return the acceptedLimit
	 */
	@Column(name = "accepted_limit", nullable = false)
	public double getAcceptedLimit() {
		return acceptedLimit;
	}

	/**
	 * @return the approvedBy
	 */
	@Column(name = "approved_by", nullable = false)
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @return the calibratedBy
	 */
	@Column(name = "calibrated_by", nullable = false)
	public String getCalibratedBy() {
		return calibratedBy;
	}

	/**
	 * @return the calibrationDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "cal_date", nullable = false)
	public Date getCalibrationDate() {
		return calibrationDate;
	}

	/**
	 * @return the calibrationInstrumentsList
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	public Collection<CalibrationInstrument> getCalibrationInstrumentsList() {
		return calibrationInstrumentsList;
	}

	/**
	 * @return the certificateCode
	 */
	@Column(name = "certificate_code", nullable = false, unique = true)
	public String getCertificateCode() {
		return certificateCode;
	}

	/**
	 * @return the certificateID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "certificate_id", nullable = false)
	public long getCertificateID() {
		return certificateID;
	}

	/**
	 * @return the certificateStatus
	 */
	@Column(name = "certificate_status", nullable = false)
	public String getCertificateStatus() {
		return certificateStatus;
	}

	/**
	 * @return the dueDate
	 */
	@Column(name = "due_date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDueDate() {
		return calDueDate;
	}

	/**
	 * @return the environmentHumidity
	 */
	@Column(name = "environment_humidity", nullable = false)
	public double getEnvironmentHumidity() {
		return environmentHumidity;
	}

	/**
	 * @return the environmentTemp
	 */
	@Column(name = "environment_temp", nullable = false)
	public double getEnvironmentTemp() {
		return environmentTemp;
	}

	/**
	 * @return the issueDate
	 */
	@Column(name = "issue_date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @return the maxCalRange
	 */
	@Column(name = "max_cal_range", nullable = false)
	public double getMaxCalRange() {
		return maxCalRange;
	}

	/**
	 * @return the minCalRange
	 */
	@Column(name = "mini_cal_range", nullable = false)
	public double getMinCalRange() {
		return minCalRange;
	}

	/**
	 * @return the receivedDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "received_date", nullable = false)
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @return the sensor
	 */
	@ManyToOne
	@JoinColumn(name = "sensor_id")
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * @return the statusAfter
	 */
	@Column(name = "status_after", nullable = false)
	public String getStatusAfter() {
		return statusAfter;
	}

	/**
	 * @return the statusBefore
	 */
	@Column(name = "status_before", nullable = false)
	public String getStatusBefore() {
		return statusBefore;
	}

	/**
	 * @return the uncertianity
	 */
	@Column(name = "uncertianity", nullable = false)
	public double getUncertianity() {
		return uncertianity;
	}

	/**
	 * @param acceptedLimit
	 *            the acceptedLimit to set
	 */
	public void setAcceptedLimit(double acceptedLimit) {
		this.acceptedLimit = acceptedLimit;
	}

	/**
	 * @param approvedBy
	 *            the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @param calibratedBy
	 *            the calibratedBy to set
	 */
	public void setCalibratedBy(String calibratedBy) {
		this.calibratedBy = calibratedBy;
	}

	/**
	 * @param calibrationDate
	 *            the calibrationDate to set
	 */
	public void setCalibrationDate(Date calibrationDate) {
		this.calibrationDate = calibrationDate;
	}

	/**
	 * @param calibrationInstrumentsList
	 *            the calibrationInstrumentsList to set
	 */
	public void setCalibrationInstrumentsList(
			Collection<CalibrationInstrument> calibrationInstrumentsList) {
		this.calibrationInstrumentsList = calibrationInstrumentsList;
	}

	/**
	 * @param certificateCode
	 *            the certificateCode to set
	 */
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	/**
	 * @param certificateID
	 *            the certificateID to set
	 */
	public void setCertificateID(long certificateID) {
		this.certificateID = certificateID;
	}

	/**
	 * @param certificateStatus
	 *            the certificateStatus to set
	 */
	public void setCertificateStatus(String certificateStatus) {
		this.certificateStatus = certificateStatus;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.calDueDate = dueDate;
	}

	/**
	 * @param environmentHumidity
	 *            the environmentHumidity to set
	 */
	public void setEnvironmentHumidity(double environmentHumidity) {
		this.environmentHumidity = environmentHumidity;
	}

	/**
	 * @param environmentTemp
	 *            the environmentTemp to set
	 */
	public void setEnvironmentTemp(double environmentTemp) {
		this.environmentTemp = environmentTemp;
	}

	/**
	 * @param issueDate
	 *            the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @param maxCalRange
	 *            the maxCalRange to set
	 */
	public void setMaxCalRange(double maxCalRange) {
		this.maxCalRange = maxCalRange;
	}

	/**
	 * @param minCalRange
	 *            the minCalRange to set
	 */
	public void setMinCalRange(double minCalRange) {
		this.minCalRange = minCalRange;
	}

	/**
	 * @param receivedDate
	 *            the receivedDate to set
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * @param sensor
	 *            the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * @param statusAfter
	 *            the statusAfter to set
	 */
	public void setStatusAfter(String statusAfter) {
		this.statusAfter = statusAfter;
	}

	/**
	 * @param statusBefore
	 *            the statusBefore to set
	 */
	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}

	/**
	 * @param uncertianity
	 *            the uncertianity to set
	 */
	public void setUncertianity(double uncertianity) {
		this.uncertianity = uncertianity;
	}
	
	@Column(name = "measurement_unit", nullable = false)
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
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
		DateTime dueDate = new DateTime(getDueDate().getTime());
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
	
	@Column(name = "cert_index" , nullable=false, unique=true)
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
