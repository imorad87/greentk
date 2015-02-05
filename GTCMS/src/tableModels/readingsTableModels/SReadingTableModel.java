package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.SCertificateLine;

public class SReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	


	private List<SCertificateLine> sCertificateLinesList = new ArrayList<>();
	private String[] columnNames = {"Unit Under Test Reading", "Reference Reading", "Correction Value" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (sCertificateLinesList == null || sCertificateLinesList.size() == 0) {
			return 0;
		} else {
			return sCertificateLinesList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		SCertificateLine sCertificateLine = sCertificateLinesList.get(row);
		if (sCertificateLine == null) {
			return null;
		}
		switch (col) {
		
		case 0:
			
			return ((sCertificateLine.getReferenceReading() >= 0) ?  sCertificateLine.getReferenceReading() : 0);
			
		case 1:
			return ((sCertificateLine.getUnitUnderTestReading() >= 0) ?  sCertificateLine.getUnitUnderTestReading() : 0);
			
			
		case 2:
			
			return sCertificateLine.getCorrectionValue();

		}
		
		return null;
	}

	public List<SCertificateLine> getSCertificateLinesList() {
		return sCertificateLinesList;
	}

	public void setSCertificateLinesList(List<SCertificateLine> sCertificateLinesList) {
		this.sCertificateLinesList = sCertificateLinesList;
		fireTableDataChanged();
	}

}
