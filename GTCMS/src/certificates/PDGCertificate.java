/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.PDGCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name = "pdg_certificates")

public class PDGCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final transient String symbol = "PDG";

	
	private Collection<PDGCertificateLine> linesList = new HashSet<PDGCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy="certificate", cascade = CascadeType.ALL)
	public Collection<PDGCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<PDGCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
