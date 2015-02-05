package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.CalibrationInstrument;

public class CalibrationDevicesComboBoxModel extends DefaultComboBoxModel<CalibrationInstrument> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<CalibrationInstrument> calibrationInstruments;

	public CalibrationDevicesComboBoxModel() {
		calibrationInstruments = new ArrayList<CalibrationInstrument>();
	}

	@Override
	public CalibrationInstrument getElementAt(int index) {
		return (calibrationInstruments.get(index));
	}

	@Override
	public int getSize() {
		
		if (calibrationInstruments == null || calibrationInstruments.isEmpty()) {
			return 0;
		}else{
			return calibrationInstruments.size();
		}
	}

	/**
	 * @return the companieList
	 */
	public List<CalibrationInstrument> getCalibrationInstruments() {
		return calibrationInstruments;
	}

	/**
	 * @param sesnorsList
	 *            the companieList to set
	 */
	public void setCalibrationInstrumentsList(List<CalibrationInstrument> calibrationInstruments) {
		this.calibrationInstruments = calibrationInstruments;
	}

}
