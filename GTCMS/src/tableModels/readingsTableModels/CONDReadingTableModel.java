package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.CondCertificateLine;

public class CONDReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<CondCertificateLine> condCertificateLinesList = new ArrayList<>();
	private String[] columnNames = { "Reference Reading",
			"Unit Under Test Reading", "Correction", "Uncertianity" };

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

		return condCertificateLinesList.size();

	}

	@Override
	public Object getValueAt(int row, int col) {
		CondCertificateLine condCertificateLine = condCertificateLinesList
				.get(row);

		switch (col) {

		case 0:

			return condCertificateLine.getReferenceReading();
			
		case 1:

			return condCertificateLine.getUnitUnderTestReading();

		case 2:

			return condCertificateLine.getCorrectionValue();

		case 3:
			
			return condCertificateLine.getUncertianity();
			
		}

		return 0;
	}

	public List<CondCertificateLine> getCondCertificateLinesList() {
		return condCertificateLinesList;
	}

	public void setCondCertificateLinesList(
			List<CondCertificateLine> condCertificateLinesList) {
		this.condCertificateLinesList = condCertificateLinesList;
		fireTableDataChanged();
	}

}
