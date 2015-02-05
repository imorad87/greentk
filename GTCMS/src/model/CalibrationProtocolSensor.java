package model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "sensorprotocol")
public class CalibrationProtocolSensor extends AbstractProtocol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<Sensor> sensorsList = new HashSet<Sensor>();

	/**
	 * @return the sensorsList
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SensorProtocol_Sensors", joinColumns = @JoinColumn(name = "SensorProtocol_ID"), inverseJoinColumns = @JoinColumn(name = "Sensor_ID"))
	public Collection<Sensor> getSensorsList() {
		return sensorsList;
	}

	/**
	 * @param sensorsList
	 *            the sensorsList to set
	 */
	public void setSensorsList(Collection<Sensor> sensorsList) {
		this.sensorsList = sensorsList;
	}

}
