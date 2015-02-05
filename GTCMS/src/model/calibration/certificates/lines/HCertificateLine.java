/**
 * 
 */
package model.calibration.certificates.lines;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Islam
 * 
 */
@Entity
// @DiscriminatorValue(value = "H")
public class HCertificateLine extends CertificateLine {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3325293786336052377L;
	private double referenceReading;
	private double unitUnderTestReading;
	private double correctionValue;

	/**
	 * @return the reference
	 */
	@Column(name = "Reference_Reading", nullable = false)
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
	@Column(name = "Unit_Under_Test_Reading", nullable = false)
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
	@Column(name = "Correction", nullable = false)
	public double getCorrectionValue() {
		return correctionValue;
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
