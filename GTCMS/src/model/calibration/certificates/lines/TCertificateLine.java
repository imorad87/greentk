/**
 * 
 */
package model.calibration.certificates.lines;

import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import certificates.Certificate;

/**
 * @author Islam Morad
 * 
 */
@Entity(name = "t_certificates_lines")
public class TCertificateLine extends CertificateLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5771660871226185558L;
	private double referenceReading;
	private double unitUnderTestReading;
	private double correctionValue;
	private Certificate certificate;

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
		correctionValue = calculateCorrection();
		return correctionValue;
	}

	public void setCorrectionValue(double correctionValue){
		this.correctionValue = correctionValue;
	}

	public double calculateCorrection() {
		Double d = new Double(getUnitUnderTestReading() - getReferenceReading() );
		NumberFormat dd = NumberFormat.getInstance();
		dd.setMaximumFractionDigits(10);
		String format = dd.format(d.doubleValue());
		return Double.valueOf(format);
	}
	
	@ManyToOne
	@JoinColumn(name="certificate_id")
	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
}
