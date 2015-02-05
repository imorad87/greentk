package comboModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import model.CalibrationProcedure;

public class CalibrationProceduresComboBoxModel extends DefaultComboBoxModel<CalibrationProcedure> {

	private static final long serialVersionUID = -6491981373444024730L;
	private List<CalibrationProcedure> calibrationProcedures;

	public CalibrationProceduresComboBoxModel() {
		calibrationProcedures = new ArrayList<CalibrationProcedure>();
	}

	@Override
	public CalibrationProcedure getElementAt(int index) {
		return (calibrationProcedures.get(index));
	}

	@Override
	public int getSize() {
		
		if (calibrationProcedures == null || calibrationProcedures.isEmpty()) {
			return 0;
		}else{
			return calibrationProcedures.size();
		}
	}

	/**
	 * @return the companieList
	 */
	public List<CalibrationProcedure> getCalibrationProceduresList() {
		return calibrationProcedures;
	}

	/**
	 * @param calibrationProcedures
	 *            the companieList to set
	 */
	public void setCalibrationProceduresList(List<CalibrationProcedure> calibrationProcedures) {
		this.calibrationProcedures = calibrationProcedures;
	}

}
