package tableModels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import certificates.Certificate;

public class CertificatesTabelModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Certificate> certificatesList = new ArrayList<Certificate>();
	private String[] columnNames = { "ID", "Cert. Code","Sensor Code", "Issue Date",
			"Due Date", "Status", "Cert. Type", "Days Remaining", "Valid" };

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

		return certificatesList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		DateFormat formatter = DateFormat.getDateInstance();
		Certificate certificate = certificatesList.get(row);
		if (certificate == null) {
			return null;
		}
		switch (col) {
		case 0:
			Long id = certificate.getCertificateID();
			if (id == null) {
				return "NA";
			} else {
				return id;
			}
		case 1:
			String code = certificate.getCertificateCode();
			if (code == null || code.isEmpty()) {
				return "NA";
			} else {
				return code;
			}
			
		case 2:
			return certificate.getSensor().getCode();
		case 3:
			Date issueDate = certificate.getIssueDate();
			if (issueDate == null) {
				return "NA";
			} else {
				return formatter.format(issueDate);
			}
		case 4:
			Date dueDate = certificate.getDueDate();
			if (dueDate == null) {
				return "NA";
			} else {
				return formatter.format(dueDate);
			}
		case 5:
			String status = certificate.getCertificateStatus();
			if (status == null || status.isEmpty()) {
				return "NA";
			} else {
				return status;
			}
		case 6:
			return certificate.getSensor().getJobType().getSymbol();
		case 7:
			return certificate.getRemainingDays();
		case 8:
			return certificate.isValid();
		}
		return null;
	}

	public List<Certificate> getCertificatesList() {
		return certificatesList;
	}

	public void setCertificatesList(List<Certificate> certificatesList) {
		this.certificatesList = certificatesList;
		fireTableDataChanged();
	}

}
