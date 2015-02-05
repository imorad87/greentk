package view;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import dao.JobTypeTask;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import model.JobType;

import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import tableModels.JobTypesTableModel;

public class JobTypesPanel extends JPanel {

	private static final long serialVersionUID = -5252719629927538029L;

	private JobTypesTableModel jobTypesTableModel;

	private JXTable table;

	private JFormattedTextField priceField;

	private JFormattedTextField descriptionField;

	private JFormattedTextField symbolField;

	private JFormattedTextField cmsField;

	private JPanel panel;

	private JButton btnReset;

	private JButton btnCreate;

	private JButton btnUpdate;

	public JobTypesPanel() {

		Ready ready = new Ready();

		jobTypesTableModel = new JobTypesTableModel();

		setPreferredSize(new Dimension(700, 430));
		setLayout(new BorderLayout(0, 0));

		JPanel container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));

		JPanel topPanel = new JPanel();
		container.add(topPanel, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		container.add(splitPane, BorderLayout.CENTER);

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(230, 10));
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Job Type Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblCmsId = new JLabel("CMS ID:");
		panel.add(lblCmsId, "2, 2, left, default");

		cmsField = new JFormattedTextField();
		cmsField.setColumns(7);
		cmsField.setEditable(false);
		cmsField.setEnabled(false);
		panel.add(cmsField, "4, 2, left, default");

		JLabel lblFunctionSymbol = new JLabel("Function Symbol:");
		panel.add(lblFunctionSymbol, "2, 4, left, default");

		symbolField = new JFormattedTextField();
		symbolField.getDocument().addDocumentListener(ready);
		panel.add(symbolField, "4, 4, fill, default");

		JLabel lblDescription = new JLabel("Description:");
		panel.add(lblDescription, "2, 6, left, default");

		descriptionField = new JFormattedTextField();
		descriptionField.getDocument().addDocumentListener(ready);
		panel.add(descriptionField, "4, 6, fill, default");

		JLabel lblPrice = new JLabel("Price:");
		panel.add(lblPrice, "2, 8, left, default");

		priceField = new JFormattedTextField();
		priceField.getDocument().addDocumentListener(ready);
		panel.add(priceField, "4, 8, fill, default");

		JPanel panel_1 = new JPanel();
		leftPanel.add(panel_1, BorderLayout.SOUTH);

		btnReset = new JButton("Reset");
		btnReset.setEnabled(false);
		btnReset.setToolTipText("Reset fields and selection");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
		panel_1.add(btnReset);

		btnCreate = new JButton("Create");
		btnCreate.setEnabled(false);
		btnCreate.setToolTipText("Create new Job Type");
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create();
				refereshData();

			}
		});
		panel_1.add(btnCreate);

		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.setToolTipText("Update selected job type");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				refereshData();

			}
		});
		panel_1.add(btnUpdate);

		JPanel rightpanel = new JPanel();
		splitPane.setRightComponent(rightpanel);
		rightpanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		rightpanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		table = new JXTable(jobTypesTableModel);
		table.addMouseListener(new JobTypeSelectionHandler());
		table.getColumn(0).setMaxWidth(100);
		;
		table.setEditable(false);
		table.setHorizontalScrollEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		table.setHighlighters(HighlighterFactory.createAlternateStriping());
		table.setColumnControlVisible(true);
		panel_2.add(new JScrollPane(table));

		refereshData();

	}

	private void refereshData() {

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			private List<JobType> jobTypes;

			@Override
			protected Void doInBackground() throws Exception {

				jobTypes = new JobTypeTask().getAll();

				return null;
			}

			@Override
			protected void done() {
				jobTypesTableModel.setJobTypesList(jobTypes);
				table.setModel(jobTypesTableModel);
				table.updateUI();
			}
		};

		worker.execute();

	}

	private void create() {

		JobType type = new JobType();

		String description = descriptionField.getText();
		String symbol = symbolField.getText();
		double price = (double) priceField.getValue();

		type.setDescription(description);
		type.setSymbol(symbol);
		type.setPrice(price);

		try {
			new JobTypeTask().save(type);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(this, e.getCause().getMessage());
		}
	}

	private void update() {

		long id = (long) cmsField.getValue();

		JobType type = new JobTypeTask().get(id);

		String description = descriptionField.getText();
		String symbol = symbolField.getText();
		double price = (double) priceField.getValue();

		type.setDescription(description);
		type.setSymbol(symbol);
		type.setPrice(price);

		try {
			new JobTypeTask().update(type);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(this, e.getCause().getMessage());
		}
	}

	private void reset() {

		Component[] components = panel.getComponents();

		for (Component component : components) {
			if (component instanceof JFormattedTextField) {
				JFormattedTextField field = (JFormattedTextField) component;
				field.setText("");
			}
		}
		table.clearSelection();

	}

	private class JobTypeSelectionHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					long cmsID = (long) table.getValueAt(selectedRow, 0);

					JobType type = new JobTypeTask().get(cmsID);

					cmsField.setValue(type.getJobTypeID());
					descriptionField.setText(type.getDescription());
					priceField.setValue(type.getPrice());
					symbolField.setText(type.getSymbol());
				}
			}
		}

	}

	private class Ready implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {

			boolean checkFields = isValid();
			btnCreate.setEnabled(checkFields);
			btnReset.setEnabled(checkFields);
			btnUpdate.setEnabled(checkFields);

		}

		@Override
		public void removeUpdate(DocumentEvent e) {

			boolean checkFields = isValid();
			btnCreate.setEnabled(checkFields);
			btnReset.setEnabled(checkFields);
			btnUpdate.setEnabled(checkFields);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			boolean checkFields = isValid();
			btnCreate.setEnabled(checkFields);
			btnReset.setEnabled(checkFields);
			btnUpdate.setEnabled(checkFields);

		}

		private boolean isValid() {

			if (symbolField.getText().isEmpty()
					|| descriptionField.getText().isEmpty()
					|| priceField.getText().isEmpty()) {
				return false;
			} else {
				return true;
			}
		}

	}

}
