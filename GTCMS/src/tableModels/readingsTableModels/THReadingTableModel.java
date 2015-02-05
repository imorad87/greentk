package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.TDCertificateLine;

public class THReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	


	private List<TDCertificateLine> tdCertificateLinesList = new ArrayList<>();
	private String[] columnNames = {"Unit Under Test Reading \u2103", "Reference Reading \u2103", "Correction Value \u2103" };

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
		
			return tdCertificateLinesList.size();
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		TDCertificateLine tdCertificateLine = tdCertificateLinesList.get(row);
		
		switch (col) {
		
		case 0:
			
			return tdCertificateLine.getUnitUnderTestReading();
			
		case 1:
			
			return tdCertificateLine.getReferenceReading();
			
		case 2:
			
			return tdCertificateLine.getCorrectionValue();

		}
		
		return 0;
	}

	public List<TDCertificateLine> getTDCertificateLinesList() {
		return tdCertificateLinesList;
	}

	public void setTDCertificateLinesList(List<TDCertificateLine> tdCertificateLinesList) {
		this.tdCertificateLinesList = tdCertificateLinesList;
		fireTableDataChanged();
	}

}
