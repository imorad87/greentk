/**
 * 
 */
package model.calibration.certificates.lines;

import javax.persistence.Entity;

/**
 * @author Islam
 * 
 */
@Entity
// @DiscriminatorValue(value = "B")
public class BCertificateLine extends CertificateLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double loadValue;
	private double loadIncreasingValue;
	private double loadDecreasingValue;
	private double errorIcreasingValue;
	private double errorDecreasingValue;

	/**
	 * @return the load
	 */
	public double getLoadValue() {
		return loadValue;
	}

	/**
	 * @param load
	 *            the load to set
	 */
	public void setLoadValue(double load) {
		this.loadValue = load;
	}

	/**
	 * @return the loadIncreasingValue
	 */
	public double getLoadIncreasingValue() {
		return loadIncreasingValue;
	}

	/**
	 * @param loadIncreasingValue
	 *            the loadIncreasingValue to set
	 */
	public void setLoadIncreasingValue(double loadIncreasingValue) {
		this.loadIncreasingValue = loadIncreasingValue;
	}

	/**
	 * @return the loadDecreasingValue
	 */
	public double getLoadDecreasingValue() {
		return loadDecreasingValue;
	}

	/**
	 * @param loadDecreasingValue
	 *            the loadDecreasingValue to set
	 */
	public void setLoadDecreasingValue(double loadDecreasingValue) {
		this.loadDecreasingValue = loadDecreasingValue;
	}

	/**
	 * @return the errorIcreasingValue
	 */
	public double getErrorIcreasingValue() {
		return errorIcreasingValue;
	}

	/**
	 * @param errorIcreasingValue
	 *            the errorIcreasingValue to set
	 */
	public void setErrorIcreasingValue(double errorIcreasingValue) {
		this.errorIcreasingValue = errorIcreasingValue;
	}

	/**
	 * @return the errorDecreasingValue
	 */
	public double getErrorDecreasingValue() {
		return errorDecreasingValue;
	}

	/**
	 * @param errorDecreasingValue
	 *            the errorDecreasingValue to set
	 */
	public void setErrorDecreasingValue(double errorDecreasingValue) {
		this.errorDecreasingValue = errorDecreasingValue;
	}

	public double calculateErrorIncreasing() {
		return loadIncreasingValue - loadValue;
	}

	public double calculateErrorDecreasing() {
		return loadDecreasingValue - loadValue;
	}
}
