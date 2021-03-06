package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.InternationalStandard;

import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import tableModels.InternationalStandardsTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import dao.InternationalStandardTask;

public class InternationalStandardsPanel extends JPanel {

	private static final long serialVersionUID = -5252719629927538029L;

	private InternationalStandardsTableModel internationalStandardsTableModel;

	private JXTable table;

	private JFormattedTextField cmsField;

	private JFormattedTextField descriptionField;

	private JPanel panel;

	private JButton btnUpdate;

	private JButton btnCreate;

	private JButton btnReset;

	public InternationalStandardsPanel() {

		internationalStandardsTableModel = new InternationalStandardsTableModel();

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
		panel.setBorder(new TitledBorder(null,
				"International Standard Information", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
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

		JLabel lblFunctionSymbol = new JLabel("Description:");
		panel.add(lblFunctionSymbol, "2, 4, left, default");

		descriptionField = new JFormattedTextField();
		descriptionField.getDocument().addDocumentListener(new Ready());
		panel.add(descriptionField, "4, 4, fill, default");

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
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create();
				refereshData();

			}
		});
		btnCreate.setToolTipText("Create new Job Type");
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

		table = new JXTable(internationalStandardsTableModel);
		table.addMouseListener(new StandardSelectionHandler());
		table.getColumn(0).setMaxWidth(100);
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
			private List<InternationalStandard> standards;

			@Override
			protected Void doInBackground() throws Exception {

				standards = new InternationalStandardTask().getAll();

				return null;
			}

			@Override
			protected void done() {

				internationalStandardsTableModel
						.setInternationalStandardsList(standards);
				table.setModel(internationalStandardsTableModel);

			}
		};

		worker.execute();

	}

	private void create() {

		InternationalStandard standard = new InternationalStandard();

		standard.setStandardCode(descriptionField.getText());

		try {
			new InternationalStandardTask().save(standard);
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(this, e.getCause().getMessage());
		}
		

	}

	private void update() {
		
		long id = (long) cmsField.getValue();
		InternationalStandard standard = new InternationalStandardTask().get(id);

		standard.setStandardCode(descriptionField.getText());

		try {
			new InternationalStandardTask().update(standard);
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

	private class StandardSelectionHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					long cmsID = (long) table.getValueAt(selectedRow, 0);

					InternationalStandard standard = new InternationalStandardTask()
							.get(cmsID);

					cmsField.setValue(standard.getId());
					descriptionField.setText(standard.getStandardCode());

				}
			}
		}

	}

	private class Ready implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			
			boolean checkFields = checkFields();
			btnCreate.setEnabled(!checkFields);
			btnReset.setEnabled(!checkFields);
			btnUpdate.setEnabled(!checkFields);
			
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			
			boolean checkFields = checkFields();
			btnCreate.setEnabled(!checkFields);
			btnReset.setEnabled(!checkFields);
			btnUpdate.setEnabled(!checkFields);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			boolean checkFields = checkFields();
			btnCreate.setEnabled(!checkFields);
			btnReset.setEnabled(!checkFields);
			btnUpdate.setEnabled(!checkFields);
			
		}
		
		private boolean checkFields(){
			return descriptionField.getText().isEmpty();
		}
		
	}
}
