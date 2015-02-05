/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.RHCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name = "rh_certificates")

public class RHCertificate extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final transient String symbol = "RH";

	private Collection<RHCertificateLine> linesList = new HashSet<RHCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<RHCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<RHCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
