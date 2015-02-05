/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.CondCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name = "cond_certificates")

public class CondCertificate extends Certificate {

	private static final long serialVersionUID = 1L;
	
	public static final transient String symbol = "C";

	private Collection<CondCertificateLine> linesList = new HashSet<CondCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<CondCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<CondCertificateLine> linesList) {
		this.linesList = linesList;
	}
}
