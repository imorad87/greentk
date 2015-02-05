package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.Company;

@Entity
@Table(name = "Protocols")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Protocol_Type")
public abstract class AbstractProtocol extends AbstractModel implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 541910842962815265L;
	private long id;
	private String protocolCode;
	private Date issueDate;
	private Company company;
	private EnvironementalConditionsDevice conditionDevice;
	private Collection<CalibrationInstrument> instrumentsList = new HashSet<CalibrationInstrument>();

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "Protocol_ID")
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
	 * @return the protocolCode
	 */
	@Column(name = "Protocol_Code", nullable = false, unique=true)
	public String getProtocolCode() {
		return protocolCode;
	}

	/**
	 * @param protocolCode
	 *            the protocolCode to set
	 */
	public void setProtocolCode(String protocolCode) {
		this.protocolCode = protocolCode;
	}

	/**
	 * @return the issueDate
	 */
	@Column(name = "Issue_Date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate
	 *            the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the company
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Company_ID")
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

	/**
	 * @return the conditionDevice
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ConditionDevice_ID")
	public EnvironementalConditionsDevice getConditionDevice() {
		return conditionDevice;
	}

	/**
	 * @param conditionDevice
	 *            the conditionDevice to set
	 */
	public void setConditionDevice(
			EnvironementalConditionsDevice conditionDevice) {
		this.conditionDevice = conditionDevice;
	}

	/**
	 * @return the instrumentsList
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ProtocolsInstruments", joinColumns = @JoinColumn(name = "Protocol_ID"), inverseJoinColumns = @JoinColumn(name = "Instrument_ID"))
	public Collection<CalibrationInstrument> getInstrumentsList() {
		return instrumentsList;
	}

	/**
	 * @param instrumentsList
	 *            the instrumentsList to set
	 */
	public void setInstrumentsList(
			Collection<CalibrationInstrument> instrumentsList) {
		this.instrumentsList = instrumentsList;
	}

}
