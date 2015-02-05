package view.newCertificatesWizard.readingsViews;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellRenderer;

import model.calibration.certificates.lines.PGCertificateLine;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.jdesktop.swingx.JXTable;

import tableModels.readingsTableModels.PGReadingTableModel;
import wizard.WizardPanel;
import formatters.ImprovedFormattedTextField;
import formatters.TemperatureFormatter;

public class PGReadingsWizardPanel extends WizardPanel implements ReadingsPanel {

	private static final long serialVersionUID = -8779175501397234565L;
	private JFormattedTextField unitField;
	private JXTable table;
	private JTextField statusfield;
	private JFormattedTextField uncertiaintyField;

	private PGReadingTableModel pgReadingTableModel;
	private JFormattedTextField refDownField;
	private JFormattedTextField refUpField;
	private JButton removeButton;
	private JButton removeAllButton;

	private double maxRange;
	private double acceptanceLimit;
	private int limitCalculationType;

	private static final int PLUS_MINUS_VALUE = 1;
	private static final int PERCENTAGE_OF_READING = 2;
	private static final int PERCENTAGE_OF_FULL_SCALE = 3;

	/**
	 * Create the panel.
	 */
	public PGReadingsWizardPanel() {

		NumberFormat decimalFormat = NumberFormat.getInstance();
		decimalFormat.setMaximumFractionDigits(10);

	

		pgReadingTableModel = new PGReadingTableModel();
		setPreferredSize(new Dimension(646, 335));
		setLayout(null);

		JLabel lblTemperature = new JLabel("Pressure Calibration Result");
		lblTemperature.setBounds(10, 11, 626, 32);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setOpaque(true);
		lblTemperature.setForeground(new Color(255, 255, 255));
		lblTemperature.setBackground(new Color(0, 128, 0));
		lblTemperature.setFont(new Font(getFont().getFamily(), Font.BOLD, 15));
		add(lblTemperature);

		JPanel panel = new JPanel();
		panel.setBounds(10, 54, 626, 237);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel lblUnitUnderTest = new JLabel("Unit Under Test Reading:");
		panel_1.add(lblUnitUnderTest);

		unitField = new ImprovedFormattedTextField(decimalFormat);
		panel_1.add(unitField);
		unitField.setColumns(10);

		JButton addButton = new JButton("");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PGCertificateLine line = new PGCertificateLine();
				line.setUnitReading(Double.parseDouble(unitField.getText()));
				line.setReferenceUpReading(Double.parseDouble(refUpField
						.getText()));
				line.setReferenceDownReading(Double.parseDouble(refDownField
						.getText()));

				PGReadingTableModel model = (PGReadingTableModel) table
						.getModel();
				model.getPGCertificateLinesList().add(line);
				model.fireTableDataChanged();
			}
		});

		JLabel lblUp = new JLabel("Ref Up:");
		panel_1.add(lblUp);

		refUpField = new JFormattedTextField();
		panel_1.add(refUpField);
		refUpField.setColumns(10);

		JLabel lblDown = new JLabel("Ref Down:");
		panel_1.add(lblDown);
		panel_1.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { unitField, refUpField, refDownField,
						addButton, removeButton, removeAllButton }));

		refDownField = new JFormattedTextField();
		panel_1.add(refDownField);
		refDownField.setColumns(10);

		addButton.setPreferredSize(new Dimension(30, 30));
		addButton.setIcon(new ImageIcon(PGReadingsWizardPanel.class
				.getResource("/resources/Add/Add_16x16.png")));
		panel_1.add(addButton);

		removeButton = new JButton("");
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					PGReadingTableModel tableModel = (PGReadingTableModel) table
							.getModel();
					tableModel.getPGCertificateLinesList().remove(row);
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(PGReadingsWizardPanel.this,
									"Select a device from the table before you remove.");
				}

			}

		});

		removeButton.setPreferredSize(new Dimension(30, 30));
		removeButton.setIcon(new ImageIcon(PGReadingsWizardPanel.class
				.getResource("/resources/Remove/Remove_16x16.png")));
		panel_1.add(removeButton);

		removeAllButton = new JButton("");
		removeAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PGReadingTableModel tableModel = (PGReadingTableModel) table
						.getModel();
				if (tableModel.getPGCertificateLinesList().size() > 0) {
					tableModel.getPGCertificateLinesList().removeAll(
							tableModel.getPGCertificateLinesList());
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(PGReadingsWizardPanel.this,
									"What the hell you want to remove? The table is already EMPTY.");
				}

			}
		});

		removeAllButton.setPreferredSize(new Dimension(30, 30));
		removeAllButton.setIcon(new ImageIcon(PGReadingsWizardPanel.class
				.getResource("/resources/Remove/remove_all_16.png")));
		panel_1.add(removeAllButton);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		table = new JXTable(pgReadingTableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnControlVisible(true);
		table.setRowHeight(25);
		table.getColumn(3).setCellRenderer(new RedRenderer());
		table.getColumn(4).setCellRenderer(new RedRenderer());

		panel_2.add(new JScrollPane(table), BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel(
				"Expanded Uncertainty at C.L. 95% (k=2):");
		lblNewLabel_1.setBounds(10, 302, 203, 14);
		add(lblNewLabel_1);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(408, 302, 46, 14);
		add(lblStatus);

		statusfield = new JTextField();
		statusfield.setBounds(464, 299, 172, 20);
		add(statusfield);
		statusfield.setColumns(10);

		uncertiaintyField = new JFormattedTextField(decimalFormat);
		uncertiaintyField.setColumns(10);
		uncertiaintyField.setBounds(223, 299, 172, 20);
		add(uncertiaintyField);
	}

	public List<PGCertificateLine> getTSLinesList() {
		PGReadingTableModel model = (PGReadingTableModel) table.getModel();
		return model.getPGCertificateLinesList();
	}

	public double gerUncertianity() {
		return Double.valueOf(uncertiaintyField.getText());
	}

	public String getStatus() {
		return statusfield.getText();
	}

	public double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public double getAcceptanceLimit() {
		return acceptanceLimit;
	}

	public void setAcceptanceLimit(double acceptanceLimit) {
		this.acceptanceLimit = acceptanceLimit;
	}

	public int getLimitCalculationType() {
		return limitCalculationType;
	}

	public void setLimitCalculationType(int limitCalculationType) {
		this.limitCalculationType = limitCalculationType;
	}

	boolean withinLimit(double value, int calType, double limit, double maxRange) {
		
		System.out.println("Cal Type: " + calType);

		switch (calType) {
		case PLUS_MINUS_VALUE:
			if (value >= (limit * -1) && value <= limit) {
				return true;
			} else {
				return false;
			}

		case PERCENTAGE_OF_FULL_SCALE:

			if (value <= (limit * maxRange / 100)
					&& value >= ((limit * maxRange / 100) * -1)) {
				return true;
			} else {
				return false;
			}

		case PERCENTAGE_OF_READING:
			double reading = Double.valueOf(unitField.getText());

			if (value <= (reading * maxRange / 100)
					&& value >= ((reading * maxRange / 100) * -1)) {
				return true;
			} else {
				return false;
			}

		default:
			return false;
		}

	}

	private class RedRenderer extends JLabel implements TableCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8144721503041262238L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			if (withinLimit((double) value, getLimitCalculationType(),
					getAcceptanceLimit(), getMaxRange()) == false) {
				JLabel l = new JLabel();
				l.setOpaque(true);
				l.setForeground(Color.WHITE);
				l.setBackground(Color.RED);
				l.setText(String.valueOf(value));

				return l;
			} else {
				JLabel l2 = new JLabel();
				l2.setText(String.valueOf(value));
				return l2;
			}
		}
	}
}
