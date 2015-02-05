package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.TSCertificateLine;

public class TSReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	


	private List<TSCertificateLine> tsCertificateLinesList = new ArrayList<>();
	private String[] columnNames = { "Setting Reading \u2103",  "Unit Under Test Reading \u2103", "Reference Reading \u2103", "Correction Value \u2103" };

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if (tsCertificateLinesList == null || tsCertificateLinesList.size() == 0) {
			return 0;
		} else {
			return tsCertificateLinesList.size();
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		TSCertificateLine tsCertificateLine = tsCertificateLinesList.get(row);
		if (tsCertificateLine == null) {
			return null;
		}
		switch (col) {
		case 0:
			return ((tsCertificateLine.getSettingReading() >= 0) ?  tsCertificateLine.getSettingReading() : 0);
			
		case 1:
			return ((tsCertificateLine.getUnitUnderTestReading() >= 0) ?  tsCertificateLine.getUnitUnderTestReading() : 0);
		case 2:
			return ((tsCertificateLine.getReferenceReading() >= 0) ?  tsCertificateLine.getReferenceReading() : 0);
		case 3:
			return tsCertificateLine.getCorrectionValue();

		
		}
		return null;
	}

	public List<TSCertificateLine> getTSCertificateLinesList() {
		return tsCertificateLinesList;
	}

	public void setTSCertificateLinesList(List<TSCertificateLine> tsCertificateLinesList) {
		this.tsCertificateLinesList = tsCertificateLinesList;
		fireTableDataChanged();
	}

}
