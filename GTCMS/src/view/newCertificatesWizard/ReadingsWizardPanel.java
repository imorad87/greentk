package view.newCertificatesWizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;
import model.JobType;
import model.Sensor;
import view.newCertificatesWizard.readingsViews.CONDReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.PDGReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.PGReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.PHReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.RHReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.RPMReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.ReadingsPanel;
import view.newCertificatesWizard.readingsViews.SReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.TDReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.THReadingsWizardPanel;
import view.newCertificatesWizard.readingsViews.TSReadingsWizardPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ReadingsWizardPanel extends JWizardPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7242408688711224118L;
	private JSplitPane splitPane;
	private JPanel container;
	private JPanel left;
	private StepsPanel leftContainer;
	private JPanel panel;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JTextField tempField;
	private JTextField humidityField;
	private JPanel panel_2;
	private JRadioButton labRadioButton;
	private JRadioButton siteRadioButton;
	private ButtonGroup calibratioEnvironmentGroup;

	private ReadingsPanel tsReadingsWizardPanel;
	private ReadingsPanel pgReadingsWizardPanel;
	private ReadingsPanel tdReadingsWizardPanel;
	private ReadingsPanel sReadingsWizardPanel;
	private ReadingsPanel rReadingsWizardPanel;
	private ReadingsPanel pdgReadingsWizardPanel;
	private ReadingsPanel phReadingsWizardPanel;
	private ReadingsPanel condReadingsWizardPanel;
	private ReadingsPanel rhReadingsWizardPanel;
	private ReadingsPanel thReadingsWizardPanel;

	private Sensor selectedSensor;

	/**
	 * Create the panel.
	 */
	public ReadingsWizardPanel(JWizardComponents e) {
		super(e);

		setPreferredSize(new Dimension(870, 433));
		setLayout(new BorderLayout(0, 0));

		container = new JPanel();
		add(container);
		container.setLayout(new BorderLayout(0, 0));

		left = new JPanel();
		left.setPreferredSize(new Dimension(220, 10));

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(left);
		left.setLayout(new BorderLayout(0, 0));

		leftContainer = new StepsPanel(4);
		left.add(leftContainer, BorderLayout.CENTER);

		container.add(splitPane, BorderLayout.CENTER);

		panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 100));
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Calibration Environment");
		lblNewLabel.setBounds(10, 11, 628, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font(getFont().getFamily(), Font.BOLD, 15));
		panel_1.add(lblNewLabel);

		panel_3 = new JPanel();
		panel_3.setBounds(10, 54, 628, 32);
		panel_1.add(panel_3);
		panel_3.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblNewLabel_1 = new JLabel("Calibration Environment:");
		panel_3.add(lblNewLabel_1, "2, 2");

		calibratioEnvironmentGroup = new ButtonGroup();
		labRadioButton = new JRadioButton("Lab");
		labRadioButton.setActionCommand("lab");

		panel_3.add(labRadioButton, "4, 2");

		siteRadioButton = new JRadioButton("Site");
		siteRadioButton.setActionCommand("site");
		panel_3.add(siteRadioButton, "6, 2");

		calibratioEnvironmentGroup.add(labRadioButton);
		calibratioEnvironmentGroup.add(siteRadioButton);

		JLabel lblTemperature = new JLabel("Temperature:");
		panel_3.add(lblTemperature, "8, 2, right, default");

		tempField = new JFormattedTextField();

		
		panel_3.add(tempField, "10, 2, left, default");
		tempField.setColumns(15);

		
		JLabel lblHumidity = new JLabel("Relative Humidity:");
		panel_3.add(lblHumidity, "12, 2, right, default");

		
		humidityField = new JTextField();
		humidityField.setColumns(15);
		panel_3.add(humidityField, "14, 2, left, default");

		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		
		
		final SensorInformationWizardPanel sensor = (SensorInformationWizardPanel) e
				.getWizardPanel(1);
		sensor.setReadingListener(new SensorChosenListener() {

			@Override
			public void sensorChosen(Sensor s) {
				selectedSensor = s;
				JobType type = s.getJobType();
				if (type.getSymbol().equalsIgnoreCase("TS")) {
					panel_2.removeAll();
					tsReadingsWizardPanel = new TSReadingsWizardPanel();
					TSReadingsWizardPanel tsr = (TSReadingsWizardPanel) tsReadingsWizardPanel;
					tsr.setPreferredSize(new Dimension(645,
							10));
					panel_2.add(tsr, BorderLayout.CENTER);
				} else if (type.getSymbol().equalsIgnoreCase("PG")) {
					panel_2.removeAll();
					pgReadingsWizardPanel =  new PGReadingsWizardPanel();
					PGReadingsWizardPanel pgr = (PGReadingsWizardPanel) pgReadingsWizardPanel;
					pgr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(pgr, BorderLayout.CENTER);
				} else if (type.getSymbol().equalsIgnoreCase("TD")) {
					panel_2.removeAll();
					tdReadingsWizardPanel = new TDReadingsWizardPanel();
					TDReadingsWizardPanel tdr = (TDReadingsWizardPanel) tdReadingsWizardPanel;
					tdr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(tdr, BorderLayout.CENTER);
				} else if (type.getSymbol().equalsIgnoreCase("S")) {
					panel_2.removeAll();
					sReadingsWizardPanel = new SReadingsWizardPanel();
					SReadingsWizardPanel sr = (SReadingsWizardPanel) sReadingsWizardPanel;
					sr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(sr, BorderLayout.CENTER);
				} else if (type.getSymbol().equalsIgnoreCase("RPM")) {
					panel_2.removeAll();
					rReadingsWizardPanel = new RPMReadingsWizardPanel();
					RPMReadingsWizardPanel rr = (RPMReadingsWizardPanel) rReadingsWizardPanel;
					rr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(rr, BorderLayout.CENTER);
				}else if (type.getSymbol().equalsIgnoreCase("PH")) {
					panel_2.removeAll();
					phReadingsWizardPanel = new PHReadingsWizardPanel();
					PHReadingsWizardPanel phr = (PHReadingsWizardPanel) phReadingsWizardPanel;
					phr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(phr, BorderLayout.CENTER);
				}else if (type.getSymbol().equalsIgnoreCase("PDG")) {
					panel_2.removeAll();
					pdgReadingsWizardPanel = new PDGReadingsWizardPanel();
					PDGReadingsWizardPanel pdgr = (PDGReadingsWizardPanel) pdgReadingsWizardPanel;
					pdgr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(pdgr, BorderLayout.CENTER);
				}else if (type.getSymbol().equalsIgnoreCase("C")) {
					panel_2.removeAll();
					condReadingsWizardPanel = new CONDReadingsWizardPanel();
					CONDReadingsWizardPanel condr = (CONDReadingsWizardPanel) condReadingsWizardPanel;
					condr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(condr, BorderLayout.CENTER);
				}else if (type.getSymbol().equalsIgnoreCase("H")) {
					panel_2.removeAll();
					rhReadingsWizardPanel = new RHReadingsWizardPanel();
					RHReadingsWizardPanel rhr = (RHReadingsWizardPanel) rhReadingsWizardPanel;
					rhr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(rhr, BorderLayout.CENTER);
				}else if (type.getSymbol().equalsIgnoreCase("TH")) {
					panel_2.removeAll();
					thReadingsWizardPanel = new THReadingsWizardPanel();
					THReadingsWizardPanel thr = (THReadingsWizardPanel) thReadingsWizardPanel;
					thr.setPreferredSize(new Dimension(645, 10));
					panel_2.add(thr, BorderLayout.CENTER);
				}
			}
		});
	
		
	
		final CalibrationElementWizardPanel calElementPanel = (CalibrationElementWizardPanel) e
				.getWizardPanel(3);

		calElementPanel.setElementListener(new CalibrationElementListener() {

			@Override
			public void acceptanceLimitUpdated(double acceptanceLimit) {
				if (selectedSensor != null) {
					String type = selectedSensor.getJobType().getSymbol();

					if (type.equalsIgnoreCase("td")) {
						
						TDReadingsWizardPanel tdr = (TDReadingsWizardPanel) tdReadingsWizardPanel;
						
						tdr.setAcceptanceLimit(acceptanceLimit);
						
					} else if (type.equalsIgnoreCase("ts")) {
						
						TSReadingsWizardPanel tsr = (TSReadingsWizardPanel) tsReadingsWizardPanel;
						
						tsr.setAcceptanceLimit(acceptanceLimit);
					
					}else if (type.equalsIgnoreCase("pg")) {
						
						PGReadingsWizardPanel pgr = (PGReadingsWizardPanel) pgReadingsWizardPanel;
						
						pgr.setAcceptanceLimit(acceptanceLimit);
					
					}else if (type.equalsIgnoreCase("pdg")) {
						
						PDGReadingsWizardPanel pdgr = (PDGReadingsWizardPanel) pdgReadingsWizardPanel;
						pdgr.setAcceptanceLimit(acceptanceLimit);
					}else if (type.equalsIgnoreCase("ph")) {
						
						PHReadingsWizardPanel phr = (PHReadingsWizardPanel) phReadingsWizardPanel;
						phr.setAcceptanceLimit(acceptanceLimit);
					}else if (type.equalsIgnoreCase("s")) {
						
						SReadingsWizardPanel sr = (SReadingsWizardPanel) sReadingsWizardPanel;
						sr.setAcceptanceLimit(acceptanceLimit);
					}else if (type.equalsIgnoreCase("r")) {
						
						RPMReadingsWizardPanel rr = (RPMReadingsWizardPanel) rReadingsWizardPanel;
						rr.setAcceptanceLimit(acceptanceLimit);
					}else if (type.equalsIgnoreCase("c")) {
						
						CONDReadingsWizardPanel cr = (CONDReadingsWizardPanel) condReadingsWizardPanel;
						cr.setAcceptanceLimit(acceptanceLimit);
					}else if (type.equalsIgnoreCase("th")) {
						
						// TODO to be made.
					}else if (type.equalsIgnoreCase("rh")) {
						
						// TODO to be made
					}
				}

			}

			@Override
			public void maxRangeUpdated(double maxRange) {
				if (selectedSensor != null) {
					String type = selectedSensor.getJobType().getSymbol();
					if (type.equalsIgnoreCase("td")) {
						
						TDReadingsWizardPanel tdr = (TDReadingsWizardPanel) tdReadingsWizardPanel;
						
						tdr.setMaxRange(maxRange);
						
					} else if (type.equalsIgnoreCase("ts")) {
						
						TSReadingsWizardPanel tsr = (TSReadingsWizardPanel) tsReadingsWizardPanel;

						tsr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("pg")) {
						
						PGReadingsWizardPanel pgr = (PGReadingsWizardPanel) pgReadingsWizardPanel;

						pgr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("pdg")) {
						
						PDGReadingsWizardPanel pdgr = (PDGReadingsWizardPanel) pdgReadingsWizardPanel;
						pdgr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("ph")) {
						
						PHReadingsWizardPanel phr = (PHReadingsWizardPanel) phReadingsWizardPanel;
						phr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("s")) {
						
						SReadingsWizardPanel sr = (SReadingsWizardPanel) sReadingsWizardPanel;
						sr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("r")) {
						
						RPMReadingsWizardPanel rr = (RPMReadingsWizardPanel) rReadingsWizardPanel;
						rr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("c")) {
						
						CONDReadingsWizardPanel cr = (CONDReadingsWizardPanel) condReadingsWizardPanel;
						cr.setMaxRange(maxRange);
					}else if (type.equalsIgnoreCase("th")) {
						
						// TODO to be made.
					}else if (type.equalsIgnoreCase("rh")) {
						
						// TODO to be made
					}
				}
			}

			@Override
			public void calculationMethodUpdated(int method) {
				if (selectedSensor != null) {
					String type = selectedSensor.getJobType().getSymbol();
					if (type.equalsIgnoreCase("td")) {
						TDReadingsWizardPanel tdr = (TDReadingsWizardPanel) tdReadingsWizardPanel;

						tdr.setLimitCalculationType(method);
					} else if (type.equalsIgnoreCase("ts")) {
						
						TSReadingsWizardPanel tsr = (TSReadingsWizardPanel) tsReadingsWizardPanel;
						tsr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("pg")) {
						
						PGReadingsWizardPanel pgr = (PGReadingsWizardPanel) pgReadingsWizardPanel;
						pgr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("pdg")) {
						
						PDGReadingsWizardPanel pdgr = (PDGReadingsWizardPanel) pdgReadingsWizardPanel;
						pdgr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("ph")) {
						
						PHReadingsWizardPanel phr = (PHReadingsWizardPanel) phReadingsWizardPanel;
						phr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("s")) {
						
						SReadingsWizardPanel sr = (SReadingsWizardPanel) sReadingsWizardPanel;
						sr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("r")) {
						
						RPMReadingsWizardPanel rr = (RPMReadingsWizardPanel) rReadingsWizardPanel;
						rr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("c")) {
						
						CONDReadingsWizardPanel cr = (CONDReadingsWizardPanel) condReadingsWizardPanel;
						cr.setLimitCalculationType(method);
					}else if (type.equalsIgnoreCase("th")) {
						
						// TODO to be made.
					}else if (type.equalsIgnoreCase("rh")) {
						
						// TODO to be made
					}
				}
			}
		});
	}

	
	
	
	public int getCalibrationEvnironment() {
		String command = calibratioEnvironmentGroup.getSelection()
				.getActionCommand();

		if (command.equals("lab")) {
			return 0;
		} else {
			return 1;
		}
	}

	
	
	
	public double getEnvironementTemp() {
		return Double.valueOf(tempField.getText().trim());
	}

	
	
	public double getEnvironmentHumidity() {
		return Double.valueOf(humidityField.getText().trim());
	}



	public ReadingsPanel getReadingsWizardPanel() {
		
		JobType type = selectedSensor.getJobType();
		
		if (type.getSymbol().equalsIgnoreCase("TS")) {
			return tsReadingsWizardPanel;
		} else if (type.getSymbol().equalsIgnoreCase("PG")) {
			return pgReadingsWizardPanel;

		} else if (type.getSymbol().equalsIgnoreCase("TD")) {
			return tdReadingsWizardPanel;

		} else if (type.getSymbol().equalsIgnoreCase("S")) {
			return sReadingsWizardPanel;

		} else if (type.getSymbol().equalsIgnoreCase("RPM")) {
			return rReadingsWizardPanel;

		}else if (type.getSymbol().equalsIgnoreCase("PH")) {
			return phReadingsWizardPanel;

		}else if (type.getSymbol().equalsIgnoreCase("PDG")) {
			return pdgReadingsWizardPanel;

		}else if (type.getSymbol().equalsIgnoreCase("C")) {
			return condReadingsWizardPanel;

		}else if (type.getSymbol().equalsIgnoreCase("H")) {
			return rhReadingsWizardPanel;

		}else if (type.getSymbol().equalsIgnoreCase("TH")) {
			return thReadingsWizardPanel;

		}else{
			return null;
		}
	}

	

}
