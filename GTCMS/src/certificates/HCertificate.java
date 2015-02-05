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

import model.calibration.certificates.lines.HCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity
// @DiscriminatorValue(value = "Humidity_Certificate")
public class HCertificate extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<HCertificateLine> linesList = new HashSet<HCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "HCertificateLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<HCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<HCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
