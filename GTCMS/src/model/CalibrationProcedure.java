package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "calibration_procedure")
@Table(name = "calibration_procedure")
public class CalibrationProcedure {

	private long procedureID;
	private String procedureCode;

	/**
	 * @return the procedureID
	 */
	@Id
	@GeneratedValue
	@Column(name = "procedure_id")
	public long getProcedureID() {
		return procedureID;
	}

	/**
	 * @param procedureID
	 *            the procedureID to set
	 */
	public void setProcedureID(long procedureID) {
		this.procedureID = procedureID;
	}

	/**
	 * @return the procedureCode
	 */
	@Column(name = "procedure_code", nullable = false, unique = true)
	//@NotEmpty(message="Procedure code cannot be left blank")
	public String getProcedureCode() {
		return procedureCode;
	}

	/**
	 * @param procedureCode
	 *            the procedureCode to set
	 */
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	
	@Override
	public String toString() {
		return getProcedureCode();
	}

}
