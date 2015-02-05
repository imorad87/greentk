package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.RCertificateLine;

public class RReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	


	private List<RCertificateLine> rCertificateLinesList = new ArrayList<>();
	private String[] columnNames = {"Reference Reading", "Unit Under Test Reading", "Correction Value" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		if (rCertificateLinesList == null || rCertificateLinesList.size() == 0) {
			return 0;
		} else {
			return rCertificateLinesList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		RCertificateLine rCertificateLine = (RCertificateLine) rCertificateLinesList.get(row);
		if (rCertificateLine == null) {
			return null;
		}
		switch (col) {
		
		case 0:
			
			return ((rCertificateLine.getReferenceReading() >= 0) ?  rCertificateLine.getReferenceReading() : 0);
			
		case 1:
			return ((rCertificateLine.getUnitUnderTestReading() >= 0) ?  rCertificateLine.getUnitUnderTestReading() : 0);
			
			
		case 2:
			
			return rCertificateLine.getCorrectionValue();

		}
		
		return null;
	}

	public List<RCertificateLine> getRCertificateLinesList() {
		return rCertificateLinesList;
	}

	public void setRCertificateLinesList(List<RCertificateLine> rCertificateLinesList) {
		this.rCertificateLinesList = rCertificateLinesList;
		fireTableDataChanged();
	}

}
