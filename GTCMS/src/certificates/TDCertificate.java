/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.TDCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name="td_certificates")

public class TDCertificate extends Certificate {

	private static final long serialVersionUID = 1L;
	public static final transient String symbol = "TD";

	private Collection<TDCertificateLine> linesList = new HashSet<TDCertificateLine>();

	
	
	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<TDCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<TDCertificateLine> linesList) {
		this.linesList = linesList;
	}

	
}
