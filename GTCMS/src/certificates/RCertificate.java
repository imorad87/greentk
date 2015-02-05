/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.RCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity
// @DiscriminatorValue(value = "RPM_Certificate")
public class RCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<RCertificateLine> linesList = new HashSet<RCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RPMCertificateLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<RCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<RCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
