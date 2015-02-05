package model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "machineprotocol")
public class CalibrationProtocolMachine extends AbstractProtocol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 766392217097055273L;
	private Machine machine;

	/**
	 * @return the machine
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Machine_ID")
	public Machine getMachine() {
		return machine;
	}

	/**
	 * @param machine
	 *            the machine to set
	 */
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}
