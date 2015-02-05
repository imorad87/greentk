package tableModels.readingsTableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.calibration.certificates.lines.PDGCertificateLine;

public class PDGReadingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	


	private List<PDGCertificateLine> pdgCertificateLinesList = new ArrayList<>();
	private String[] columnNames = {"Reference Reading", "Unit Under Test Reading", "Correction" };

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
		
			return pdgCertificateLinesList.size();
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		PDGCertificateLine pdgCertificateLine = pdgCertificateLinesList.get(row);
		
		switch (col) {
		
		case 0:
			
			return pdgCertificateLine.getReferenceReading();
			
		case 1:
			
			return pdgCertificateLine.getUnitUnderTestReading();
			
		case 2:
			
			return pdgCertificateLine.getCorrectionValue();

		}
		
		return 0;
	}

	public List<PDGCertificateLine> getPDGCertificateLinesList() {
		return pdgCertificateLinesList;
	}

	public void setPDGCertificateLinesList(List<PDGCertificateLine> pdgCertificateLinesList) {
		this.pdgCertificateLinesList = pdgCertificateLinesList;
		fireTableDataChanged();
	}

}
