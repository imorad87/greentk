/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.PHCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name = "ph_certificates")

public class PHCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final transient String symbol = "PH";

	private Collection<PHCertificateLine> linesList = new HashSet<PHCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<PHCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<PHCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
