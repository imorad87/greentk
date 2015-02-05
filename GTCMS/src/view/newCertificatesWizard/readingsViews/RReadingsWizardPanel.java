package view.newCertificatesWizard.readingsViews;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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

import model.calibration.certificates.lines.RCertificateLine;
import model.calibration.certificates.lines.TDCertificateLine;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import tableModels.readingsTableModels.RReadingTableModel;
import tableModels.readingsTableModels.TDReadingTableModel;
import wizard.WizardPanel;
import formatters.ImprovedFormattedTextField;
import formatters.TemperatureFormatter;

public class RReadingsWizardPanel extends WizardPanel implements ReadingsPanel {

	private static final long serialVersionUID = -8779175501397234565L;
	private JFormattedTextField referenceField;
	private JFormattedTextField unitField;
	private JXTable table;
	private JTextField statusfield;
	private JFormattedTextField uncertiaintyField;

	private RReadingTableModel rReadingTableModel;

	/**
	 * Create the panel.
	 */
	public RReadingsWizardPanel() {

		NumberFormat decimalFormat = DecimalFormat.getInstance();
		decimalFormat.setMaximumFractionDigits(10);
		
		TemperatureFormatter tempFormatter = new TemperatureFormatter(0);
		TemperatureFormatter normalFormatter = new TemperatureFormatter(1);

		rReadingTableModel = new RReadingTableModel();
		setPreferredSize(new Dimension(646, 335));
		setLayout(null);

		JLabel lblTemperature = new JLabel("Temperature Calibration Result");
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

		JLabel lblUnitUnderTest = new JLabel("Unit Under Test Reading \u2103:");
		panel_1.add(lblUnitUnderTest);

		unitField = new ImprovedFormattedTextField(decimalFormat);
		unitField.setFormatterFactory(normalFormatter);
		panel_1.add(unitField);
		unitField.setColumns(7);

		JLabel lblReferenceReading = new JLabel("Reference Reading \u2103:");
		panel_1.add(lblReferenceReading);

		referenceField = new ImprovedFormattedTextField(decimalFormat);
		referenceField.setFormatterFactory(normalFormatter);

		panel_1.add(referenceField);
		referenceField.setColumns(7);

		

		JButton addButton = new JButton("");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RCertificateLine line = new RCertificateLine();
				line.setReferenceReading( (long) referenceField.getValue());
				line.setUnitUnderTestReading( (long) unitField.getValue());
				
				RReadingTableModel model = (RReadingTableModel) table
						.getModel();
				model.getRCertificateLinesList().add(line);
				model.fireTableDataChanged();
			}
		});

		addButton.setPreferredSize(new Dimension(30, 30));
		addButton.setIcon(new ImageIcon(RReadingsWizardPanel.class
				.getResource("/resources/Add/Add_16x16.png")));
		panel_1.add(addButton);

		JButton removeButton = new JButton("");
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					RReadingTableModel tableModel = (RReadingTableModel) table
							.getModel();
					tableModel.getRCertificateLinesList().remove(row);
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(
									RReadingsWizardPanel.this,
									"Select a device from the table before you remove.");
				}

			}
				
			
		});
		
		removeButton.setPreferredSize(new Dimension(30, 30));
		removeButton.setIcon(new ImageIcon(RReadingsWizardPanel.class
				.getResource("/resources/Remove/Remove_16x16.png")));
		panel_1.add(removeButton);

		JButton removeAllButton = new JButton("");
		removeAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				RReadingTableModel tableModel = (RReadingTableModel) table
						.getModel();
				if (tableModel.getRCertificateLinesList().size() > 0) {
					tableModel.getRCertificateLinesList().removeAll(
							tableModel.getRCertificateLinesList());
					tableModel.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(
									RReadingsWizardPanel.this,
									"What the hell you want to remove? The table is already EMPTY.");
				}

			}
		});
		
		removeAllButton.setPreferredSize(new Dimension(30, 30));
		removeAllButton.setIcon(new ImageIcon(RReadingsWizardPanel.class
				.getResource("/resources/Remove/remove_all_16.png")));
		panel_1.add(removeAllButton);

		JLabel lblNewLabel = new JLabel((String) null);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		table = new JXTable(rReadingTableModel);
		table.setColumnControlVisible(true);
		table.addHighlighter(HighlighterFactory.createAlternateStriping(Color.WHITE, Color.GREEN));
		table.getSelectedRow();
		table.setRowHeight(25);
		table.getColumn(3).setCellRenderer(new RedRenderer());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
		uncertiaintyField.setFormatterFactory(tempFormatter);
		uncertiaintyField.setColumns(10);
		uncertiaintyField.setBounds(223, 299, 172, 20);
		add(uncertiaintyField);
	}

	public List<TDCertificateLine> getTDLinesList() {
		TDReadingTableModel model = (TDReadingTableModel) table.getModel();
		return model.getTDCertificateLinesList();
	}

	public double gerUncertianity() {
		return Double.valueOf(uncertiaintyField.getText());
	}

	public String getStatus() {
		return statusfield.getText();
	}
	
	private class RedRenderer extends JLabel implements TableCellRenderer {
		
		// TODO modify the renderer based on the acceptance limit

		/**
		 * 
		 */
		private static final long serialVersionUID = -8144721503041262238L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			
			double v = (double) value;
			if (v == 0) {
				JLabel l = new JLabel();
				l.setOpaque(true);
				l.setForeground(Color.WHITE);
				l.setBackground(Color.RED);
				l.setText(String.valueOf(v));
				return l;
			} else {
				JLabel l2 = new JLabel();
				l2.setText(String.valueOf(value));
				return l2;
			}
		}
	}
	
	
}
