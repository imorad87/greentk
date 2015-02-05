/**
 * 
 */
package certificates;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import model.calibration.certificates.lines.TSCertificateLine;

/**
 * @author Islam
 * 
 */
@Entity(name="ts_certificates")
public class TSCertificate extends Certificate {

	private static final long serialVersionUID = 1L;
	
	public static final transient String symbol = "TS";
	
	private Collection<TSCertificateLine> linesList = new HashSet<TSCertificateLine>();

	
	
	


	/**
	 * @return the linesList
	 */
	@OneToMany(mappedBy="certificate", cascade = CascadeType.ALL)
	public Collection<TSCertificateLine> getLinesList() {
		return linesList;
	}

	/**
	 * @param linesList
	 *            the linesList to set
	 */
	public void setLinesList(Collection<TSCertificateLine> linesList) {
		this.linesList = linesList;
	}

	

}
