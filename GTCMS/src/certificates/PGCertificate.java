/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.PGCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name = "pg_certificates")

public class PGCertificate extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final transient String symbol = "PG";

	
	private Collection<PGCertificateLine> linesList = new HashSet<PGCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy="certificate", cascade = CascadeType.ALL)
	public Collection<PGCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<PGCertificateLine> linesList) {
		this.linesList = linesList;
	}
}
