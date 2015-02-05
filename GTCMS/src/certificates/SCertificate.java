/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.SCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name="s_certificates")
public class SCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final transient String symbol = "S";

	private Collection<SCertificateLine> linesList = new HashSet<SCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy= "certificate", cascade = CascadeType.ALL)
	public Collection<SCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<SCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
