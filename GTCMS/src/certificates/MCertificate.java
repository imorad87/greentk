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

import model.calibration.certificates.lines.MCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity
// @DiscriminatorValue(value = "Mass_Certificate")
public class MCertificate extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<MCertificateLine> linesList = new HashSet<MCertificateLine>();

	/**
	 * @return the linesList
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MCertificateLines", joinColumns = @JoinColumn(name = "Certificate_ID"), inverseJoinColumns = @JoinColumn(name = "Line_ID"))
	public Collection<MCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<MCertificateLine> linesList) {
		this.linesList = linesList;
	}

}
