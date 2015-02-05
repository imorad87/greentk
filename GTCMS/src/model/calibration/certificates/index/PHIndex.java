/**
 * 
 */
package model.calibration.certificates.index;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Islam
 * 
 */
@Entity
@DiscriminatorValue(value = "PH")
public class PHIndex extends TSCertificateIndex {

	/*
	 * private long id; private Company company; private JobType jobType;
	 * private Integer code;
	 *//**
	 * @return the id
	 */
	/*
	 * @Id
	 * 
	 * @GeneratedValue
	 * 
	 * @Column(name = "id") public long getId() { return id; }
	 *//**
	 * @param id
	 *            the id to set
	 */
	/*
	 * public void setId(long id) { this.id = id; }
	 *//**
	 * @return the company
	 */
	/*
	 * @OneToOne public Company getCompany() { return company; }
	 *//**
	 * @param company
	 *            the company to set
	 */
	/*
	 * public void setCompany(Company company) { this.company = company; }
	 *//**
	 * @return the jobType
	 */
	/*
	 * @OneToOne public JobType getJobType() { return jobType; }
	 *//**
	 * @param jobType
	 *            the jobType to set
	 */
	/*
	 * public void setJobType(JobType jobType) { this.jobType = jobType; }
	 *//**
	 * @return the code
	 */
	/*
	 * @Column(name = "Certificate_Code") public Integer getCode() { return
	 * code; }
	 *//**
	 * @param code
	 *            the code to set
	 */
	/*
	 * public void setCode(Integer code) { this.code = code; }
	 */
}
