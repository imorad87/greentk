/**
 * @author Islam Morad
 * Email: imorad87@gmail.com
 * Phone:+201142520468
 * Version: 1.1
 * Copyright reserved to GREENTK
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "job_type")
public class JobType extends AbstractModel implements Serializable, Comparable<JobType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long jobTypeID;
	private String description;
	private String symbol;
	private double price;
	
	private Collection<CalibrationInstrument> instumentsList = new HashSet<>();

	/**
	 * @return the jobTypeID
	 */
	@Id
	@GeneratedValue
	@Column(name = "jobType_id", nullable = false)
	public long getJobTypeID() {
		return jobTypeID;
	}

	/**
	 * @param jobTypeID
	 *            the jobTypeID to set
	 */
	public void setJobTypeID(long jobTypeID) {
		this.jobTypeID = jobTypeID;
	}

	/**
	 * @return the description
	 */
	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
		firePropertyChange("description", getDescription(), description);

	}

	/**
	 * @return the symbol
	 */
	@Column(name = "function_symbol", nullable = false, unique = true)
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the price
	 */
	@Column(name = "type_price", nullable = false)
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
		result = prime * result + (int) (jobTypeID ^ (jobTypeID >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		JobType other = (JobType) obj;
		if (jobTypeID != other.jobTypeID)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return getSymbol();
	}

	

	@Override
	public int compareTo(JobType o) {
		
		return this.getSymbol().compareTo(o.getSymbol());
	}

	@ManyToMany(mappedBy="jobTypesList")
	public Collection<CalibrationInstrument> getInstumentsList() {
		return instumentsList;
	}

	public void setInstumentsList(List<CalibrationInstrument> instumentsList) {
		this.instumentsList = instumentsList;
	}
}
