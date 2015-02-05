package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.PGCertificateLine;

public class PGReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	


	private List<PGCertificateLine> pgCertificateLinesList = new ArrayList<>();
	private String[] columnNames = { "Unit Under Test Reading ", "Reference Up Reading", "Reference Down Reading", "Correction Up", "Correction Down" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		if (pgCertificateLinesList == null || pgCertificateLinesList.size() == 0) {
			return 0;
		} else {
			return pgCertificateLinesList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		PGCertificateLine pgCertificateLine = pgCertificateLinesList.get(row);
		if (pgCertificateLine == null) {
			return null;
		}
		switch (col) {
		case 0:
			return ((pgCertificateLine.getUnitReading() >= 0) ?  pgCertificateLine.getUnitReading() : 0);
			
		case 1:
			return ((pgCertificateLine.getReferenceUpReading() >= 0) ?  pgCertificateLine.getReferenceUpReading() : 0);
		case 2:
			return ((pgCertificateLine.getReferenceDownReading() >= 0) ?  pgCertificateLine.getReferenceDownReading() : 0);
		case 3:
			return pgCertificateLine.getUpCorrectionvalue();
		case 4:
			return pgCertificateLine.getDownCorrectionValue();		
		}
		return null;
	}

	public List<PGCertificateLine> getPGCertificateLinesList() {
		return pgCertificateLinesList;
	}

	public void setPGCertificateLinesList(List<PGCertificateLine> pgCertificateLinesList) {
		this.pgCertificateLinesList = pgCertificateLinesList;
		fireTableDataChanged();
	}

}
