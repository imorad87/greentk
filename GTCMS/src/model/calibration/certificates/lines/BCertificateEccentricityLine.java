package model.calibration.certificates.lines;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
// @DiscriminatorValue(value = "Eccentricity")
public class BCertificateEccentricityLine extends CertificateLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double readingAtPointOne;
	private double pointReading;
	private double readingValue;
	private double errorValue;

	/**
	 * @return the pointReading
	 */
	@Column(name = "Point_Reading")
	public double getPointReading() {
		return pointReading;
	}

	/**
	 * @param pointReading
	 *            the pointReading to set
	 */
	public void setPointReading(double pointReading) {
		this.pointReading = pointReading;
	}

	/**
	 * @return the readingValue
	 */
	@Column(name = "Reading_Value")
	public double getReadingValue() {
		return readingValue;
	}

	/**
	 * @param readingValue
	 *            the readingValue to set
	 */
	public void setReadingValue(double readingValue) {
		this.readingValue = readingValue;
	}

	/**
	 * @return the error
	 */
	@Column(name = "Error_Value")
	public double getErrorValue() {
		return errorValue;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setErrorValue(double error) {
		this.errorValue = error;
	}

	public double calculateError() {
		return readingValue - readingAtPointOne;
	}

	/**
	 * @return the readingAtPointOne
	 */
	@Column(name = "Reading_At_Point_One")
	public double getReadingAtPointOne() {
		return readingAtPointOne;
	}

	/**
	 * @param readingAtPointOne
	 *            the readingAtPointOne to set
	 */
	public void setReadingAtPointOne(double readingAtPointOne) {
		this.readingAtPointOne = readingAtPointOne;
	}
}
