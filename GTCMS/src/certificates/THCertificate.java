/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.RHCertificateLine;
import model.calibration.certificates.lines.TCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name = "th_certificates")
public class THCertificate extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final transient String symbol = "TH";
	
	private double hMinCalRange;
	private double hMaxCalRange;
	private double hAcceptedLimit;

	private Collection<TCertificateLine> tLinesList = new HashSet<TCertificateLine>();

	private Collection<RHCertificateLine> rhlinesList = new HashSet<RHCertificateLine>();
	
	/**
	 * @return the hMinCalRange
	 */
	@Column(name = "humidity_mini_cal_range", nullable = false)

	public double gethMinCalRange() {
		return hMinCalRange;
	}

	/**
	 * @param hMinCalRange the hMinCalRange to set
	 */
	public void sethMinCalRange(double hMinCalRange) {
		this.hMinCalRange = hMinCalRange;
	}

	/**
	 * @return the hMaxCalRange
	 */
	@Column(name = "humidity_max_cal_range", nullable = false)

	public double gethMaxCalRange() {
		return hMaxCalRange;
	}

	/**
	 * @param hMaxCalRange the hMaxCalRange to set
	 */
	public void sethMaxCalRange(double hMaxCalRange) {
		this.hMaxCalRange = hMaxCalRange;
	}

	/**
	 * @return the hAcceptedLimit
	 */
	@Column(name = "humidity_accepted_limit", nullable = false)

	public double gethAcceptedLimit() {
		return hAcceptedLimit;
	}

	/**
	 * @param hAcceptedLimit the hAcceptedLimit to set
	 */
	public void sethAcceptedLimit(double hAcceptedLimit) {
		this.hAcceptedLimit = hAcceptedLimit;
	}

	

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<TCertificateLine> getTLinesList() {
		return tLinesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setTLinesList(Collection<TCertificateLine> tLinesList) {
		this.tLinesList = tLinesList;
	}
	
	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<RHCertificateLine> getRHLinesList() {
		return rhlinesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setRHLinesList(Collection<RHCertificateLine> rhlinesList) {
		this.rhlinesList = rhlinesList;
	}


}
