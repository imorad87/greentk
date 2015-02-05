package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import model.Company;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;

import tableModels.CompaniesTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import dao.CompanyTask;

@SuppressWarnings("serial")
public class CompaniesPanel extends JPanel implements MouseListener {
	private JTextField nameField;
	private JTextField codeField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField faxField;
	private JXTable table;
	private JPanel centerPanel;
	private JPanel iconPanel;
	private JPanel formPanel;
	private JPanel panel;
	private JButton createButton;
	private JButton updateButton;
	private JButton searchButton;
	private Company company;
	private JLabel idLabel;
	private JTextField idField;
	private JLabel searchForLabel;
	private JTextField searchField;
	private JButton goButton;
	private JPanel searchPanel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel searchByLabel;
	private JPanel bottomPanel;
	private JPanel buttonPanel;
	private JPanel textProgPanel;
	private JProgressBar progressBar;
	private JPanel progressPanel;
	private JPanel textPanel;
	private JPopupMenu popupMenu;
	private CompaniesTableModel companiesModel;
	private JMenuItem copyItem;
	private JMenuItem pasteItem;
	private JMenuItem cutItem;
	private JTextPane textArea;

	private final EnableUpdateCreateButtons enableUpdateCreateButtons = new EnableUpdateCreateButtons();
	private JPanel panel_1;
	private JToolBar toolBar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JSeparator separator;
	private JXButton button_1;
	private JPanel panel_2;
	private JXLabel label;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CompaniesPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout(5, 5));

		JPanel leftPabel = new JPanel();
		leftPabel.setBorder(new CompoundBorder(new LineBorder(
				new Color(0, 0, 0)), new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Company Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null)));
		leftPabel.setPreferredSize(new Dimension(230, 150));
		add(leftPabel, BorderLayout.WEST);
		leftPabel.setLayout(new BorderLayout(0, 0));

		formPanel = new JPanel();
		leftPabel.add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(103dlu;default)"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(99dlu;default):grow"), }));

		idLabel = new JLabel("CMS ID:");
		formPanel.add(idLabel, "2, 2, left, default");

		idField = new JTextField();
		idField.setEditable(false);
		formPanel.add(idField, "4, 2, left, default");
		idField.setColumns(5);

		JLabel lblName = new JLabel("Name:");
		formPanel.add(lblName, "2, 4, left, default");

		nameField = new JTextField();
		nameField.getDocument().addDocumentListener(enableUpdateCreateButtons);
		nameField.addMouseListener(this);
		formPanel.add(nameField, "4, 4, fill, default");
		nameField.setColumns(10);

		JLabel lblCode = new JLabel("Code:");
		formPanel.add(lblCode, "2, 6, left, default");

		codeField = new JTextField();
		codeField.getDocument().addDocumentListener(enableUpdateCreateButtons);
		codeField.addMouseListener(this);
		formPanel.add(codeField, "4, 6, fill, default");
		codeField.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		formPanel.add(lblAddress, "2, 8, left, default");

		addressField = new JTextField();
		addressField.getDocument().addDocumentListener(
				enableUpdateCreateButtons);
		addressField.addMouseListener(this);
		formPanel.add(addressField, "4, 8, fill, default");
		addressField.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		formPanel.add(lblPhone, "2, 10, left, default");

		phoneField = new JTextField();
		phoneField.getDocument().addDocumentListener(enableUpdateCreateButtons);
		phoneField.addMouseListener(this);
		formPanel.add(phoneField, "4, 10, fill, default");
		phoneField.setColumns(10);

		JLabel lblFax = new JLabel("Fax:");
		formPanel.add(lblFax, "2, 12, left, default");

		faxField = new JTextField();
		faxField.getDocument().addDocumentListener(enableUpdateCreateButtons);
		faxField.addMouseListener(this);
		formPanel.add(faxField, "4, 12, fill, default");
		faxField.setColumns(10);

		panel = new JPanel();
		formPanel.add(panel, "2, 14, 3, 1, center, top");

		searchButton = new JButton("Refresh");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshData();
			}
		});
		panel.add(searchButton);

		createButton = new JButton("Create");
		createButton.setEnabled(false);
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createCompany();
			}
		});

		panel.add(createButton);

		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateCompany();

			}
		});
		panel.add(updateButton);

		iconPanel = new JPanel();
		iconPanel.setPreferredSize(new Dimension(250, 130));
		leftPabel.add(iconPanel, BorderLayout.SOUTH);
		iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label = new JXLabel();
		label.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/company-building-icon.png")));
		iconPanel.add(label);

		centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(centerPanel, BorderLayout.CENTER);

		table = new JXTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		table.setSortable(true);
		table.setColumnControlVisible(true);
		table.setRowHeight(25);
		
	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {

					int row = table.getSelectedRow();
					Long id = (Long) table.getModel().getValueAt(row, 0);
					String name = (String) table.getModel().getValueAt(row, 1);
					String code = (String) table.getModel().getValueAt(row, 2);
					String address = (String) table.getModel().getValueAt(row,
							3);
					String phone = (String) table.getModel().getValueAt(row, 4);
					String fax = (String) table.getModel().getValueAt(row, 5);
					idField.setText(String.valueOf(id));
					nameField.setText(name);
					codeField.setText(code);
					addressField.setText(address);
					phoneField.setText(phone);
					faxField.setText(fax);
				}
			}
		});
		companiesModel = new CompaniesTableModel();

		centerPanel.setLayout(new BorderLayout(0, 0));
		table.setModel(companiesModel);
		table.getAutoCreateRowSorter();
		centerPanel.add(new JScrollPane(table));

		bottomPanel = new JPanel();
		centerPanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout());

		textProgPanel = new JPanel();
		bottomPanel.add(textProgPanel, BorderLayout.CENTER);
		textProgPanel.setLayout(new BorderLayout(0, 0));

		progressPanel = new JPanel();
		textProgPanel.add(progressPanel, BorderLayout.EAST);
		progressPanel.setLayout(new BorderLayout(0, 0));

		textPanel = new JPanel();
		textProgPanel.add(textPanel, BorderLayout.CENTER);
		textPanel.setLayout(new BorderLayout(0, 0));
		textArea = new JTextPane();
		textArea.setOpaque(false);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPanel.add(textArea, BorderLayout.CENTER);
		textArea.setEditable(false);

		buttonPanel = new JPanel();
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		searchPanel = new JPanel();
		searchPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		searchPanel.add(panel_1);

		searchByLabel = new JLabel("Search By:");
		panel_1.add(searchByLabel);

		comboBox = new JComboBox();
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CMS ID", "Company Code", "Company Name", "Like Name"}));
		comboBox.setMaximumRowCount(3);

		searchForLabel = new JLabel("For:");
		panel_1.add(searchForLabel);

		searchField = new JTextField();
		panel_1.add(searchField);
		searchField.getDocument().addDocumentListener(new EnableGoButton());
		searchField.addMouseListener(this);
		searchField.setColumns(70);

		goButton = new JButton("");
		goButton.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/search.png")));
		panel_1.add(goButton);
		goButton.setEnabled(false);
		
		toolBar = new JToolBar();
		searchPanel.add(toolBar, BorderLayout.NORTH);
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/Copy/Copy_24x24.png")));
		toolBar.add(btnNewButton);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/Paste/Paste_24x24.png")));
		toolBar.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/Cut/Cut_24x24.png")));
		toolBar.add(btnNewButton_2);
		
		separator = new JSeparator();
		separator.setMaximumSize(new Dimension(5, 32767));
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					table.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/Print/Print_24x24.png")));
		toolBar.add(btnNewButton_4);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				export();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/microsoft_office_excel.png")));
		toolBar.add(btnNewButton_3);
		
		button_1 = new JXButton();
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshData();
			}
		});
		button_1.setIcon(new ImageIcon(CompaniesPanel.class.getResource("/resources/Refresh/Refresh_24x24.png")));
		toolBar.add(button_1);
				
				panel_2 = new JPanel();
				panel_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				panel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
				toolBar.add(panel_2);
						panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				
						progressBar = new JProgressBar();
						progressBar.setPreferredSize(new Dimension(146, 20));
						panel_2.add(progressBar);
						progressBar.setMaximumSize(new Dimension(100, 14));
						progressBar.setEnabled(false);
						progressBar.setBorderPainted(false);
		goButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchCompany();
			}
		});

		popupMenu = new JPopupMenu();

		cutItem = new JMenuItem();
		cutItem.setAction(nameField.getActionMap().get("cut-to-clipboard"));
		cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		cutItem.setText("Cut");

		copyItem = new JMenuItem();
		copyItem.setAction(nameField.getActionMap().get("copy-to-clipboard"));
		copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		copyItem.setText("Copy");

		pasteItem = new JMenuItem();
		pasteItem.setAction(nameField.getActionMap()
				.get("paste-from-clipboard"));
		pasteItem.setText("Paste");
		pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));

		popupMenu.add(copyItem);
		popupMenu.add(pasteItem);
		popupMenu.add(cutItem);


	}

	public JTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JXTable table) {
		this.table = table;
	}

	/**
	 * @return the model
	 */
	public CompaniesTableModel getModel() {
		return companiesModel;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(CompaniesTableModel model) {
		this.companiesModel = model;
	}

	private void createCompany() {
		String companyResult = validateCompany();

		if (companyResult == null) {

			try {
				company = new Company();
				company.setCompanyCode(codeField.getText().trim());
				company.setCompanyName(nameField.getText().trim());
				company.setAddress(addressField.getText().trim());
				company.setPhoneNumber(phoneField.getText().trim());
				company.setFaxNumber(faxField.getText().trim());
				new CompanyTask().addCompany(company);
				table.getAutoCreateRowSorter();
				appendToPane(textArea,
						"- Company created succesffully." + "\n", Color.BLUE);
			} catch (HibernateException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}

			refreshData();
			clearFields();

		} else {
			appendToPane(textArea, companyResult, Color.RED);
		}

	}

	private void updateCompany() {

		Company detachedCompany = new CompanyTask().getByID(Long.valueOf(idField
				.getText()));

		detachedCompany.setCompanyName(nameField.getText().trim());
		detachedCompany.setCompanyCode(codeField.getText().trim());

		detachedCompany.setAddress(addressField.getText().trim());
		detachedCompany.setFaxNumber(faxField.getText().trim());

		detachedCompany.setPhoneNumber(phoneField.getText().trim());
		new CompanyTask().updateCompany(detachedCompany);
		appendToPane(textArea, "- Company " + detachedCompany.getCompanyCode()
				+ " has been updated successfully." + "\n", Color.BLUE);

	}

	private void searchCompany() {
		String value = searchField.getText().trim();
		Pattern charsPattern = Pattern.compile("\\d");

		ArrayList<Company> arrayList = new ArrayList<>();
		int selectedIndex = comboBox.getSelectedIndex();
		switch (selectedIndex) {
		case 0:
			Matcher matcher = charsPattern.matcher(value);
			if (!matcher.matches()) {
				appendToPane(textArea, "- " + value
						+ " is not valid for as a CMS ID" + "\n", Color.RED);
				return;
			} else {
				long id = Long.valueOf(value);
				Company companyByID = new CompanyTask().getByID(id);
				arrayList.add(companyByID);
				companiesModel.setCompaniesList(arrayList);
				companiesModel.fireTableDataChanged();
			}
			break;
		case 1:
			String code = codeField.getText().trim();
			Company companyByCode = new CompanyTask().getByCode(code);
			arrayList.add(companyByCode);
			companiesModel.setCompaniesList(arrayList);
			companiesModel.fireTableDataChanged();
			break;
		default:
			break;
		}

	}

	private String validateCompany() {

		/*Pattern numbersOnly = Pattern.compile("\\d");
		Matcher phoneMatcher = numbersOnly.matcher(phoneField.getText().trim());
		Matcher faxMatcher = numbersOnly.matcher(faxField.getText().trim());
		
		if(phoneMatcher.matches() && faxMatcher.matches()){
			return null;
		}else{
			return "- Check the phone or the fax please";
		}*/
		
		return null;
	}

	private void refreshData() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@SuppressWarnings("rawtypes")
			List list = null;

			@Override
			protected Void doInBackground() throws Exception {
				progressBar.setIndeterminate(true);
				list = new CompanyTask().getAllCompanies();
				return null;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void done() {
				getModel().setCompaniesList(list);
				getModel().fireTableDataChanged();
				progressBar.setIndeterminate(false);
				progressBar.setValue(0);
				appendToPane(textArea,
						"- Data refereshed successfully." + "\n", Color.BLUE);
			}
		};

		worker.execute();
	}

	private void clearFields() {
		idField.setText("");
		nameField.setText("");
		codeField.setText("");
		addressField.setText("");
		phoneField.setText("");
		faxField.setText("");
	}
	
	private void export(){
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("Companies Data");
		createHeaderRow(workbook, sheet);
		populateData(workbook, sheet);
		saveData(workbook);
	}

	private void createHeaderRow(SXSSFWorkbook workbook, Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		Cell idHeader = headerRow.createCell(0);
		idHeader.setCellValue("CMS ID");
		idHeader.setCellStyle(style);
		Cell codeHeader = headerRow.createCell(1);
		codeHeader.setCellValue("Code");
		codeHeader.setCellStyle(style);
		Cell nameHeader = headerRow.createCell(2);
		nameHeader.setCellValue("Name");
		nameHeader.setCellStyle(style);
		Cell addressHeader = headerRow.createCell(3);
		addressHeader.setCellValue("Company Address");
		addressHeader.setCellStyle(style);
		Cell phoneHeader = headerRow.createCell(4);
		phoneHeader.setCellValue("Phone Number");
		phoneHeader.setCellStyle(style);
		Cell faxHeader = headerRow.createCell(5);
		faxHeader.setCellValue("Fax Number");
		faxHeader.setCellStyle(style);
	}

	private void populateData(SXSSFWorkbook workbook, Sheet sheet) {

		int index = 1;
		Iterator<Company> iterator = companiesModel.getCompaniesList()
				.iterator();
		progressBar.setMinimum(0);
		progressBar.setMaximum(companiesModel.getCompaniesList().size());
		while (iterator.hasNext()) {
			Company c = iterator.next();
			Row createRow = sheet.createRow(index);
			Cell id = createRow.createCell(0);
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			id.setCellValue(c.getCompanyID());
			Cell code = createRow.createCell(1);
			code.setCellStyle(style);
			code.setCellValue(c.getCompanyCode());
			Cell name = createRow.createCell(2);
			name.setCellValue(c.getCompanyName());
			name.setCellStyle(style);
			Cell address = createRow.createCell(3);
			address.setCellValue(c.getAddress());
			address.setCellStyle(style);
			Cell phone = createRow.createCell(4);
			phone.setCellValue(c.getPhoneNumber());
			phone.setCellStyle(style);
			Cell fax = createRow.createCell(5);
			fax.setCellValue(c.getFaxNumber());
			fax.setCellStyle(style);
			progressBar.setValue(index);
			updateUI();
			index++;
		}

	}

	private boolean saveData(SXSSFWorkbook workbook) {
		JFileChooser saveChooser = new JFileChooser();
		saveChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		saveChooser.setSelectedFile(new File("Companies_data.xlsx"));
		saveChooser.setName("Saving Companies Information");
		int i = saveChooser.showSaveDialog(getParent());
		if (i == JFileChooser.APPROVE_OPTION) {

			FileOutputStream out = null;
			try {
				File selectedFile = saveChooser.getSelectedFile();
				out = new FileOutputStream(selectedFile);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
			try {
				workbook.write(out);
				out.flush();
				out.close();
				return true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getCause().getMessage());
			}
		}
		return false;
	}

	void showPopupMenu(MouseEvent e) {
		int button = e.getButton();
		if (button == MouseEvent.BUTTON3) {
			JTextField textField = (JTextField) e.getComponent();
			textField.requestFocus();
			textField.selectAll();
			popupMenu.show(textField, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		showPopupMenu(e);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	private void appendToPane(JTextPane tp, String msg, Color c) {
		DefaultStyledDocument document = new DefaultStyledDocument();
		tp.setDocument(document);
		StyleContext context = new StyleContext();
		Style style = context.addStyle("color", document.getStyle("default"));
		StyleConstants.setForeground(style, c);
		StyleConstants.setFontSize(style, 13);
		try {
			document.insertString(0, msg, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	private class EnableUpdateCreateButtons implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {

			if (nameField.getDocument().getLength() > 0
					&& codeField.getDocument().getLength() > 0
					&& addressField.getDocument().getLength() > 0
					&& phoneField.getDocument().getLength() > 0
					&& faxField.getDocument().getLength() > 0) {
				createButton.setEnabled(true);
				updateButton.setEnabled(true);
			} else {
				createButton.setEnabled(false);
				updateButton.setEnabled(false);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			if (nameField.getDocument().getLength() > 0
					&& codeField.getDocument().getLength() > 0
					&& addressField.getDocument().getLength() > 0
					&& phoneField.getDocument().getLength() > 0
					&& faxField.getDocument().getLength() > 0) {
				createButton.setEnabled(true);
				updateButton.setEnabled(true);
			} else {
				createButton.setEnabled(false);
				updateButton.setEnabled(false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (nameField.getDocument().getLength() > 0
					&& codeField.getDocument().getLength() > 0
					&& addressField.getDocument().getLength() > 0
					&& phoneField.getDocument().getLength() > 0
					&& faxField.getDocument().getLength() > 0) {
				createButton.setEnabled(true);
				updateButton.setEnabled(true);
			} else {
				createButton.setEnabled(false);
				updateButton.setEnabled(false);
			}
		}
	}
	
	
	private class EnableGoButton implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			
			goButton.setEnabled(e.getDocument().getLength() > 0);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			goButton.setEnabled(e.getDocument().getLength() > 0);
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			goButton.setEnabled(e.getDocument().getLength() > 0);

			
		}
		
	}
}
