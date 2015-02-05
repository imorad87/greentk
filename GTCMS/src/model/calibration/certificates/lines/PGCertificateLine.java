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
 * @author Islam
 * 
 */
@Entity(name = "pg_certificates_lines")
public class PGCertificateLine extends CertificateLine {

	
	private static final long serialVersionUID = 7210292647083762090L;
	
	private double referenceUpReading;
	private double referenceDownReading;
	private double unitReading;
	private double upCorrectionvalue;
	private double downCorrectionValue;
	private Certificate certificate;

	/**
	 * @return the referenceReading
	 */
	@Column(name = "reference_up_reading", nullable = false)
	public double getReferenceUpReading() {
		return referenceUpReading;
	}

	/**
	 * @param referenceReading
	 *            the referenceReading to set
	 */
	public void setReferenceUpReading(double referenceUpReading) {
		this.referenceUpReading = referenceUpReading;
	}

	/**
	 * @return the referenceReading
	 */
	@Column(name = "reference_down_reading", nullable = false)
	public double getReferenceDownReading() {
		return referenceDownReading;
	}

	/**
	 * @param referenceReading
	 *            the referenceReading to set
	 */
	public void setReferenceDownReading(double referenceDownReading) {
		this.referenceDownReading = referenceDownReading;
	}

	/**
	 * @return the upReading
	 */
	@Column(name = "unit_under_test_reading", nullable = false)
	public double getUnitReading() {
		return unitReading;
	}

	/**
	 * @param upReading
	 *            the upReading to set
	 */
	public void setUnitReading(double unitReading) {
		this.unitReading = unitReading;
	}

	/**
	 * @return the upCorrectionvalue
	 */
	@Column(name = "correction_up_value", nullable = false)
	public double getUpCorrectionvalue() {
		upCorrectionvalue = calculateUpCorrection();
		return upCorrectionvalue;
	}

	

	/**
	 * @param upCorrectionvalue the upCorrectionvalue to set
	 */
	public void setUpCorrectionvalue(double upCorrectionvalue) {
		this.upCorrectionvalue = upCorrectionvalue;
	}

	/**
	 * @param downCorrectionValue the downCorrectionValue to set
	 */
	public void setDownCorrectionValue(double downCorrectionValue) {
		this.downCorrectionValue = downCorrectionValue;
	}

	/**
	 * @return the downCorrectionValue
	 */
	@Column(name = "correction_down_value", nullable = false)
	public double getDownCorrectionValue() {
		downCorrectionValue = calculateDownCorrection();
		return downCorrectionValue;
	}

	

	public double calculateDownCorrection() {
		Double d = new Double(referenceDownReading - unitReading);

		NumberFormat dd = NumberFormat.getInstance();

		dd.setMaximumFractionDigits(10);

		String format = dd.format(d.doubleValue());

		return Double.valueOf(format);
	}

	public double calculateUpCorrection() {
		Double d = new Double(referenceUpReading - unitReading);

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
