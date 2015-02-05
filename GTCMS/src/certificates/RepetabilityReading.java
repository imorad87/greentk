/**
 * 
 */
package certificates;

import javax.persistence.Embeddable;

/**
 * @author Islam
 * 
 */
@Embeddable
public class RepetabilityReading {

	private double reading1;
	private double reading2;
	private double reading3;
	private double reading4;
	private double reading5;
	private double reading6;

	/**
	 * @return the reading1
	 */
	public double getReading1() {
		return reading1;
	}

	/**
	 * @param reading1
	 *            the reading1 to set
	 */
	public void setReading1(double reading1) {
		this.reading1 = reading1;
	}

	/**
	 * @return the reading2
	 */
	public double getReading2() {
		return reading2;
	}

	/**
	 * @param reading2
	 *            the reading2 to set
	 */
	public void setReading2(double reading2) {
		this.reading2 = reading2;
	}

	/**
	 * @return the reading3
	 */
	public double getReading3() {
		return reading3;
	}

	/**
	 * @param reading3
	 *            the reading3 to set
	 */
	public void setReading3(double reading3) {
		this.reading3 = reading3;
	}

	/**
	 * @return the reading4
	 */
	public double getReading4() {
		return reading4;
	}

	/**
	 * @param reading4
	 *            the reading4 to set
	 */
	public void setReading4(double reading4) {
		this.reading4 = reading4;
	}

	/**
	 * @return the reading5
	 */
	public double getReading5() {
		return reading5;
	}

	/**
	 * @param reading5
	 *            the reading5 to set
	 */
	public void setReading5(double reading5) {
		this.reading5 = reading5;
	}

	/**
	 * @return the reading6
	 */
	public double getReading6() {
		return reading6;
	}

	/**
	 * @param reading6
	 *            the reading6 to set
	 */
	public void setReading6(double reading6) {
		this.reading6 = reading6;
	}

}
