/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.RPMCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name="rpm_certificates")

public class RPMCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final transient String symbol = "R";

	private Collection<RPMCertificateLine> linesList = new HashSet<RPMCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<RPMCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<RPMCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
