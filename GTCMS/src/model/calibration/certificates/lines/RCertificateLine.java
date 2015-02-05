/**
 * 
 */
package model.calibration.certificates.lines;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Islam Morad
 * 
 */
@Entity
public class RCertificateLine extends CertificateLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3863830728040432023L;
	private double referenceReading;
	private double unitUnderTestReading;
	private double correctionValue;

	/**
	 * @return the reference
	 */
	@Column(name = "reference_reading", nullable = false)
	public double getReferenceReading() {
		return referenceReading;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReferenceReading(double reference) {
		this.referenceReading = reference;
	}

	/**
	 * @return the unitUnderTest
	 */
	@Column(name = "unit_under_test_reading", nullable = false)
	public double getUnitUnderTestReading() {
		return unitUnderTestReading;
	}

	/**
	 * @param unitUnderTest
	 *            the unitUnderTest to set
	 */
	public void setUnitUnderTestReading(double unitUnderTest) {
		this.unitUnderTestReading = unitUnderTest;
	}

	/**
	 * @return the correction
	 */
	@Column(name = "correction", nullable = false)
	public double getCorrectionValue() {
		return calculateCorrection();
	}

	/**
	 * @param correction
	 *            the correction to set
	 */
	public void setCorrectionValue(double correction) {
		this.correctionValue = correction;
	}

	public double calculateCorrection() {
		return getUnitUnderTestReading() - getReferenceReading();
	}
}
