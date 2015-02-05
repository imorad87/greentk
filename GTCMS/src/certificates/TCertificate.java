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

import model.calibration.certificates.lines.TCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name="t_certificates")

public class TCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<TCertificateLine> linesList = new HashSet<TCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TCertificateLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<TCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<TCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
