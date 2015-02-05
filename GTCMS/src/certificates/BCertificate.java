/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.BCertificateEccentricityLine;
import model.calibration.certificates.lines.BCertificateLine;
import model.calibration.certificates.lines.BCertificateRepeatabilityLine;

/**
 * @author Islam Morad
 * 
 */
@Entity
// @DiscriminatorValue(value = "Balance_Certificate")
public class BCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double loadUsed;

	/**
	 * @param eccentricityLines
	 *            the eccentricityLines to set
	 */
	public void setEccentricityLines(
			Collection<BCertificateEccentricityLine> eccentricityLines) {
		this.eccentricityLines = eccentricityLines;
	}

	private double readingAtPointOne;

	private Collection<BCertificateEccentricityLine> eccentricityLines = new HashSet<BCertificateEccentricityLine>();
	private Collection<BCertificateLine> linesList = new HashSet<BCertificateLine>();
	private Collection<BCertificateRepeatabilityLine> repeatabilityLines = new HashSet<BCertificateRepeatabilityLine>();

	/**
	 * @return the loadUsed
	 */
	@Column(name = "Load_Used", nullable = false)
	public double getLoadUsed() {
		return loadUsed;
	}

	/**
	 * @param loadUsed
	 *            the loadUsed to set
	 */
	public void setLoadUsed(double loadUsed) {
		this.loadUsed = loadUsed;
	}

	/**
	 * @return the readingAtPointOne
	 */
	@Column(name = "Reading_AT_point_one", nullable = false)
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

	/**
	 * @return the eccentricityLine
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BCertificateEccentricityLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<BCertificateEccentricityLine> getEccentricityLines() {
		return eccentricityLines;
	}

	/**
	 * @return the certificateLines
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BCertificateLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<BCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param certificateLines
	 *            the certificateLines to set
	 */
	public void setLinesList(Collection<BCertificateLine> certificateLines) {
		this.linesList = certificateLines;
	}

	/**
	 * @return the repeatabilityLines
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BCertificateRepeatablityLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<BCertificateRepeatabilityLine> getRepeatabilityLines() {
		return repeatabilityLines;
	}

	/**
	 * @param repeatabilityLines
	 *            the repeatabilityLines to set
	 */
	public void setRepeatabilityLines(
			Collection<BCertificateRepeatabilityLine> repeatabilityLines) {
		this.repeatabilityLines = repeatabilityLines;
	}

}
