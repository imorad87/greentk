package model.calibration.certificates.lines;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="certificates_lines")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CertificateLine implements Serializable {

	private static final long serialVersionUID = 3659629245394309936L;
	protected long lineID;


	/**
	 * @return the lineID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Line_ID")
	public long getLineID() {
		return lineID;
	}

	/**
	 * @param lineID
	 *            the lineID to set
	 */
	public void setLineID(long lineID) {
		this.lineID = lineID;
	}
	
	
}

