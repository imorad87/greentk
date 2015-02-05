package model.calibration.certificates.lines;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import certificates.RepetabilityReading;

@Entity
// @DiscriminatorValue(value = "BRepeatability")
public class BCertificateRepeatabilityLine extends CertificateLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double loadValue;

	// private double[] rowReadings = new double[6];

	// private Collection<Double> repetabilityReadings = new HashSet<Double>();

	@Embedded
	private RepetabilityReading repeatabilityReadings;

	/**
	 * @return the repeatabilityReadings
	 */
	public RepetabilityReading getRepeatabilityReadings() {
		return repeatabilityReadings;
	}

	/**
	 * @param repeatabilityReadings
	 *            the repeatabilityReadings to set
	 */
	public void setRepeatabilityReadings(
			RepetabilityReading repeatabilityReadings) {
		this.repeatabilityReadings = repeatabilityReadings;
	}

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
	 * @return the rowReadings
	 */
	/*
	 * @ElementCollection public double[] getRowReadings() { return rowReadings;
	 * }
	 *//**
	 * @param rowReadings
	 *            the rowReadings to set
	 */
	/*
	 * public void setRowReadings(double[] rowReadings) { this.rowReadings =
	 * rowReadings; }
	 */

	/**
	 * @return the repetabilityReadings
	 */
	/*
	 * @ElementCollection public Collection<Double> getRepetabilityReadings() {
	 * return repetabilityReadings; }
	 *//**
	 * @param repetabilityReadings
	 *            the repetabilityReadings to set
	 */
	/*
	 * 
	 * public void setRepetabilityReadings(Collection<Double>
	 * repetabilityReadings) { this.repetabilityReadings = repetabilityReadings;
	 * }
	 */

}
