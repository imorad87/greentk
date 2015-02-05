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
// @DiscriminatorValue(value = "M")
public class MCertificateLine extends CertificateLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572734936963952496L;
	private double weight;
	private double nominalValue;
	private double error;
	private double uncertianity;
	private String mclass;

	/**
	 * @return the weight
	 */
	@Column(name = "No_Of_Weight", nullable = false)
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the nominalValue
	 */
	@Column(name = "Nominal_Value", nullable = false)
	public double getNominalValue() {
		return nominalValue;
	}

	/**
	 * @param nominalValue
	 *            the nominalValue to set
	 */
	public void setNominalValue(double nominalValue) {
		this.nominalValue = nominalValue;
	}

	/**
	 * @return the error
	 */
	@Column(name = "mError", nullable = false)
	public double getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(double error) {
		this.error = error;
	}

	/**
	 * @return the uncertianity
	 */
	@Column(name = "mUncertianity", nullable = false)
	public double getUncertianity() {
		return uncertianity;
	}

	/**
	 * @param uncertianity
	 *            the uncertianity to set
	 */
	public void setUncertianity(double uncertianity) {
		this.uncertianity = uncertianity;
	}

	/**
	 * @return the mclass
	 */
	@Column(name = "mClass", nullable = false)
	public String getMclass() {
		return mclass;
	}

	/**
	 * @param mclass
	 *            the mclass to set
	 */
	public void setMclass(String mclass) {
		this.mclass = mclass;
	}

}
