package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SortOrder;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import listModels.JobTypesListModel;
import model.CalibrationInstrument;
import model.JobType;

import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTable;

import tableModels.CalibrationInstrumentsTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import dao.CalibrationInstrumentTask;
import dao.JobTypeTask;
import formatters.ImprovedFormattedTextField;

public class CalibrationInstrumentsPanel extends JPanel {
	

	private static final long serialVersionUID = 945929215804853305L;

	private JFormattedTextField nameField;

	private JobTypesListModel jobTypesListModel;

	private CalibrationInstrumentsTableModel calibrationInstrumentsTableModel;

	private JXList jobTypesList;

	private JXTable table;

	private JPanel leftPanel;

	private JPanel instrumentInfoPanel;

	private JFormattedTextField cmsIdField;

	private JFormattedTextField codeField;

	private JFormattedTextField descriptionField;

	private JFormattedTextField manufacturerField;
	private JFormattedTextField serialField;
	private JFormattedTextField modelField;
	private ImprovedFormattedTextField miniRangeField;
	private ImprovedFormattedTextField maxRangeField;
	private ImprovedFormattedTextField uncertianityField;
	private JXDatePicker calDateField;
	private JXDatePicker calDueDateField;
	private NumberFormat format;

	private JButton btnUpdate;

	private JButton btnReset;

	private JButton btnCreate;

	/**
	 * Create the panel.
	 */
	public CalibrationInstrumentsPanel() {
		
		IsValid isValid = new IsValid();
		jobTypesListModel = new JobTypesListModel();

		calibrationInstrumentsTableModel = new CalibrationInstrumentsTableModel();

		format = NumberFormat.getNumberInstance();

		setPreferredSize(new Dimension(700, 430));
		setLayout(new BorderLayout(0, 0));

		JPanel container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		container.add(topPanel, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		container.add(splitPane, BorderLayout.CENTER);

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(250, 10));
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));

		instrumentInfoPanel = new JPanel();
		instrumentInfoPanel.setBorder(new TitledBorder(null,
				"Instrument Information", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		leftPanel.add(instrumentInfoPanel);
		instrumentInfoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		JLabel cmsIdLbl = new JLabel("CMS ID:");
		instrumentInfoPanel.add(cmsIdLbl, "2, 2, left, default");

		cmsIdField = new JFormattedTextField();
		cmsIdField.setEnabled(false);
		cmsIdField.setEditable(false);
		cmsIdField.setColumns(10);
		instrumentInfoPanel.add(cmsIdField, "4, 2, left, default");

		JLabel codeLbl = new JLabel("Code:");
		instrumentInfoPanel.add(codeLbl, "2, 4, left, default");

		codeField = new JFormattedTextField();
		codeField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(codeField, "4, 4, fill, default");

		JLabel lblName = new JLabel("Name:");
		instrumentInfoPanel.add(lblName, "2, 6, left, default");

		nameField = new JFormattedTextField();
		nameField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(nameField, "4, 6, fill, default");
		nameField.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		instrumentInfoPanel.add(lblDescription, "2, 8, left, default");

		descriptionField = new JFormattedTextField();
		descriptionField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(descriptionField, "4, 8, fill, default");

		JLabel lblManufacturer = new JLabel("Manufacturer:");
		instrumentInfoPanel.add(lblManufacturer, "2, 10, left, default");

		manufacturerField = new JFormattedTextField();
		manufacturerField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(manufacturerField, "4, 10, fill, default");

		JLabel lblSerialNo = new JLabel("Serial No.:");
		instrumentInfoPanel.add(lblSerialNo, "2, 12, left, default");

		serialField = new JFormattedTextField();
		serialField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(serialField, "4, 12, fill, default");

		JLabel lblModel = new JLabel("Model:");
		instrumentInfoPanel.add(lblModel, "2, 14, left, default");

		modelField = new JFormattedTextField();
		modelField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(modelField, "4, 14, fill, default");

		JLabel lblMiniRange = new JLabel("Mini Range:");
		instrumentInfoPanel.add(lblMiniRange, "2, 16, left, default");

		miniRangeField = new ImprovedFormattedTextField(format);
		miniRangeField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(miniRangeField, "4, 16, fill, default");

		JLabel label = new JLabel("Max Range:");
		instrumentInfoPanel.add(label, "2, 18, left, default");

		maxRangeField = new ImprovedFormattedTextField(format);
		maxRangeField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(maxRangeField, "4, 18, fill, default");

		JLabel lblUncertianity = new JLabel("Uncertianity:");
		instrumentInfoPanel.add(lblUncertianity, "2, 20, left, default");

		uncertianityField = new ImprovedFormattedTextField(format);
		uncertianityField.getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(uncertianityField, "4, 20, fill, default");

		JLabel lblCalDate = new JLabel("Cal. Date:");
		instrumentInfoPanel.add(lblCalDate, "2, 22, left, default");

		calDateField = new JXDatePicker();
		calDateField.getEditor().getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(calDateField, "4, 22");

		JLabel lblCalDueDate = new JLabel("Cal. Due Date:");
		instrumentInfoPanel.add(lblCalDueDate, "2, 24, left, default");

		calDueDateField = new JXDatePicker();
		calDueDateField.getEditor().getDocument().addDocumentListener(isValid);
		instrumentInfoPanel.add(calDueDateField, "4, 24");

		JLabel lblJobType = new JLabel("Job Type:");
		instrumentInfoPanel.add(lblJobType, "2, 26");

		jobTypesList = new JXList();
		jobTypesList.addListSelectionListener(isValid);
		jobTypesList.setSortOrder(SortOrder.ASCENDING);
		instrumentInfoPanel.add(new JScrollPane(jobTypesList),
				"4, 26, fill, fill");

		JPanel panel = new JPanel();
		leftPanel.add(panel, BorderLayout.SOUTH);

		btnReset = new JButton("Reset");
		btnReset.setEnabled(false);
		btnReset.setToolTipText("clear selection and fields' data");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
		panel.add(btnReset);

		btnCreate = new JButton("Create");
		btnCreate.setEnabled(false);
		btnCreate.setToolTipText("Create new instrument");
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create();
				refereshData();
			}
		});
		panel.add(btnCreate);

		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.setToolTipText("Update selected instrument");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				refereshData();

			}
		});
		panel.add(btnUpdate);

		JPanel rightpanel = new JPanel();
		splitPane.setRightComponent(rightpanel);
		rightpanel.setLayout(new BorderLayout(0, 0));

		table = new JXTable(calibrationInstrumentsTableModel);
		table.addMouseListener(new DeviceSelectionHandler());
		table.setEditable(false);
		table.setHorizontalScrollEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		table.setColumnControlVisible(true);
		table.getColumn(11).setCellRenderer(new RedRenderer());

		rightpanel.add(new JScrollPane(table), BorderLayout.CENTER);

		refereshData();

	}

	private void refereshData() {

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			private List<JobType> jobTypes;

			private List<CalibrationInstrument> instrumentList;

			@Override
			protected Void doInBackground() throws Exception {

				jobTypes = new JobTypeTask().getAll();

				instrumentList = new CalibrationInstrumentTask().getAll();

				return null;
			}

			@Override
			protected void done() {

				jobTypesListModel.setJobTypesList(jobTypes);
				jobTypesList.setModel(jobTypesListModel);

				calibrationInstrumentsTableModel
						.setCalibrationInstrumentsList(instrumentList);

				table.setModel(calibrationInstrumentsTableModel);

				calibrationInstrumentsTableModel.fireTableDataChanged();

				// table.setDefaultRenderer(Object.class, new RedRowRenderer());
				table.updateUI();

			}
		};

		worker.execute();

	}

	private void reset() {
		Component[] components = instrumentInfoPanel.getComponents();
		for (Component c : components) {
			if (c instanceof JFormattedTextField) {
				JFormattedTextField field = (JFormattedTextField) c;
				field.setText("");
			} else if (c instanceof JXDatePicker) {
				JXDatePicker datefield = (JXDatePicker) c;
				datefield.setDate(null);
			}
		}
		jobTypesList.clearSelection();
		table.clearSelection();
	}

	private void create() {

		CalibrationInstrument instrument = new CalibrationInstrument();

		String code = codeField.getText();
		String name = nameField.getText();
		String description = descriptionField.getText();
		String model = modelField.getText();
		String serial = serialField.getText();
		Object uncertianity = uncertianityField.getValue();
		Object miniRange = miniRangeField.getValue();
		Object maxRange = maxRangeField.getValue();

		@SuppressWarnings("unchecked")
		List<JobType> selectedValuesList = jobTypesList.getSelectedValuesList();

		Date calDate = calDateField.getDate();
		Date calDueDate = calDueDateField.getDate();
		String manufacturer = manufacturerField.getText();

		instrument.setCalibrationDate(calDate);
		instrument.setCalibrationDueDate(calDueDate);
		instrument.setDescription(description);
		instrument.setInstrumentCode(code);
		instrument.getJobTypesList().addAll(selectedValuesList);
		instrument.setManufacturer(manufacturer);
		instrument.setMaxRange((Double) maxRange);
		instrument.setMinRange((Double) miniRange);
		instrument.setModel(model);
		instrument.setName(name);
		instrument.setSerialNumber(serial);
		instrument.setUncertianity((Double) uncertianity);
		instrument.setRemainingDays(instrument.getRemainingDays());
		instrument.setValid(instrument.isValid());

		try {
			new CalibrationInstrumentTask().save(instrument);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(this, e.getCause().getMessage());
		}

	}

	private void update() {

		long cmsID = (long) cmsIdField.getValue();

		CalibrationInstrument instrument = new CalibrationInstrumentTask()
				.get(cmsID);

		String code = codeField.getText();
		String name = nameField.getText();
		String description = descriptionField.getText();
		String model = modelField.getText();
		String serial = serialField.getText();
		Object uncertianity = uncertianityField.getValue();
		Object miniRange = miniRangeField.getValue();
		Object maxRange = maxRangeField.getValue();

		@SuppressWarnings("unchecked")
		List<JobType> selectedValuesList = jobTypesList.getSelectedValuesList();

		Date calDate = calDateField.getDate();
		Date calDueDate = calDueDateField.getDate();
		String manufacturer = manufacturerField.getText();

		instrument.setCalibrationDate(calDate);
		instrument.setCalibrationDueDate(calDueDate);
		instrument.setDescription(description);
		instrument.setInstrumentCode(code);
		instrument.setJobTypesList(selectedValuesList);
		instrument.setManufacturer(manufacturer);
		instrument.setMaxRange((Double) maxRange);
		instrument.setMinRange((Double) miniRange);
		instrument.setModel(model);
		instrument.setName(name);
		instrument.setSerialNumber(serial);
		instrument.setUncertianity((Double) uncertianity);
		instrument.setRemainingDays(instrument.getRemainingDays());
		instrument.setValid(instrument.isValid());

		try {
			new CalibrationInstrumentTask().update(instrument);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(this, e.getCause().getMessage());
		}

	}

	private class DeviceSelectionHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					long cmsID = (long) table.getValueAt(selectedRow, 0);

					CalibrationInstrument instrument = new CalibrationInstrumentTask()
							.get(cmsID);

					cmsIdField.setValue(instrument.getInstrumentID());
					codeField.setText(instrument.getInstrumentCode());
					nameField.setText(instrument.getName());
					modelField.setText(instrument.getModel());
					serialField.setText(instrument.getSerialNumber());
					miniRangeField.setValue(instrument.getMinRange());
					maxRangeField.setValue(instrument.getMaxRange());
					calDateField.setDate(instrument.getCalibrationDate());
					calDueDateField.setDate(instrument.getCalibrationDueDate());

					List<JobType> types = new CalibrationInstrumentTask()
							.getAllJobTypes(cmsID);

					int[] indices = new int[types.size()];

					for (int i = 0; i < types.size(); i++) {
						JobType type = types.get(i);
						JobTypesListModel listModel = (JobTypesListModel) jobTypesList
								.getModel();
						indices[i] = listModel.getJobTypesList().indexOf(type);

					}

					jobTypesList.setSelectedIndices(indices);
					descriptionField.setText(instrument.getDescription());
					manufacturerField.setText(instrument.getManufacturer());
					uncertianityField.setValue(instrument.getUncertianity());
				}
			}
		}

	}

	
	private class IsValid implements DocumentListener, ListSelectionListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			boolean valid = isValid();
			btnReset.setEnabled(valid);
			btnUpdate.setEnabled(valid);
			btnCreate.setEnabled(valid);

		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			boolean valid = isValid();
			btnReset.setEnabled(valid);
			btnUpdate.setEnabled(valid);
			btnCreate.setEnabled(valid);

		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			boolean valid = isValid();
			btnReset.setEnabled(valid);
			btnUpdate.setEnabled(valid);
			btnCreate.setEnabled(valid);

		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			boolean valid = isValid();
			btnReset.setEnabled(valid);
			btnUpdate.setEnabled(valid);
			btnCreate.setEnabled(valid);

			
		}

		private boolean isValid() {

			if (codeField.getText().isEmpty() || nameField.getText().isEmpty()
					|| serialField.getText().isEmpty()
					|| miniRangeField.getText().isEmpty()
					|| maxRangeField.getText().isEmpty()
					|| uncertianityField.getText().isEmpty()
					|| calDateField.getDate() == null
					|| calDueDateField.getDate() == null
					|| jobTypesList.getSelectedIndex() < 0
					|| manufacturerField.getText().isEmpty()
					|| modelField.getText().isEmpty()) {
				return false;
			} else {

				return true;
			}
		}
	}
	
	
	private class RedRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = -8144721503041262238L;

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			
			boolean v = (boolean) value;
			if (v == false) {
				JLabel l = new JLabel();
				l.setOpaque(true);
				l.setForeground(Color.WHITE);
				l.setBackground(Color.RED);
				l.setText("EXPIRED");
				return l;
			} else {
				JLabel l2 = new JLabel();
				l2.setText("Valid");
				return l2;
			}
		}
	}

}
