

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRenderOption;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * This is a simple demonstration of using Adobe Acrobat to display a generated report.  Much of this
 * code was gleaned from other sources.
 * 
 * This code was written by a BIRT novice in an attempt to show that a report can be run from
 * within a Java Swing application without having to run the report from a Tomcat server or deploying
 * the application as an OSGi application.  
 * 
 * The BIRT does use the OSGi but only for starting the BIRT Engine.  The OSGi is included in the BIRT jars.
 * The SwingingBIRT application itself does not use OSGi.
 * 
 * Being test code, it does not contain features that would make the engine setup or report calls
 * generic.  Those tasks were not considered essential for demonstrating mere proof of concept. 
 * 
 * The engine APIs used were the BIRT 2.5 engine APIs found at http://www.birt-exchange.com/be/downloads/
 * (Eclipse BIRT Engine and Sample Viewer)
 * 
 * Sample Batch File (A bit lengthy, made it specific to show each jar; JVM was already in the system environment)
 * SWINGINGBIRT.BAT:
 * --- START BATCH ---
 * set CLASSPATH=C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\chartengineapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\chartitemapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\com.ibm.icu_4.0.1.v20090822.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\commons-cli-1.0.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\coreapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\crosstabcoreapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\dataadapterapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\dataaggregationapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\dataextraction.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\dteapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\emitterconfig.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\engineapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\flute.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\js.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\modelapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\modelodaapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\odadesignapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\org.apache.commons.codec_1.3.0.v20080530-1600.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\org.eclipse.emf.common_2.5.0.v200906151043.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\org.eclipse.emf.ecore_2.5.0.v200906151043.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\org.eclipse.emf.ecore.xmi_2.5.0.v200906151043.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\org.w3c.css.sac_1.3.0.v200805290154.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\BIRTFiles\birt-runtime-2_5_1\ReportEngine\lib\scriptapi.jar
 * set CLASSPATH=%CLASSPATH%;C:\MyFiles\WSAD\mysql-connector-java-3.1.6-bin.jar
 * set CLASSPATH=%CLASSPATH%;C:\GalileoWorkspace\HelloWorld\build\classes
 * 
 * java -classpath %CLASSPATH% com.helloworld.SwingingBIRT
 * --- END BATCH ---
 * 
 * Gotchas:
 *   - Make sure images in the report are referred to using double-quoted URLs when referring to
 *     images on the file system.  Use care with relative paths.
 *   - Make sure the BIRT jars are all included in the deployment class path and that they are spelled correctly.
 *   - If any program maintains a lock on the output file the PDF reader will not be able to access it 
 *     when it is called.  Eclipse was a bit funny that way occasionally.  Ran fine as stand-alone.
 *   
 */
public class SwingingBIRT extends JFrame
{
	org.eclipse.birt.report.engine.api.IReportEngine engine = null;  //  @jve:decl-index=0:
	org.eclipse.birt.report.engine.api.EngineConfig config = null;  //  @jve:decl-index=0:
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton runReportButton = null;

	/**
	 * This is the default constructor
	 */
	public SwingingBIRT()
	{
		super();
		initialize();
	}

	/**
	 * This method initializes the application
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(300, 174);
		this.setContentPane(getJContentPane());
		this.setTitle("Swinging BIRT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent e)
			{
			   // Gracefully stopping BIRT Engine
				System.out.println("Stopping BIRT Engine");
				stopPlatform();
				System.out.println("BIRT Engine Stopped");
			}
		});
		
		// Fire up the BIRT engine
		System.out.println("Starting BIRT Engine");
		startPlatform();
		System.out.println("BIRT Engine Started");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getRunReportButton(), null);
		}
		return jContentPane;
	}
	
	/**
    * This method initializes runReportButton	
    * 	
    * @return javax.swing.JButton	
    */
   private JButton getRunReportButton()
   {
   	if (runReportButton == null)
   	{
   		runReportButton = new JButton();
   		runReportButton.setBounds(new Rectangle(60, 50, 171, 31));
   		runReportButton.setText("Run The Report");
   		runReportButton.addActionListener(new java.awt.event.ActionListener()
   		{
   			@Override
			public void actionPerformed(java.awt.event.ActionEvent e)
   			{
   				System.out.println("Running Birt Report");
   				runReport();
   				System.out.println("BIRT Report Ran");
   			}
   		});
   	}
   	return runReportButton;
   }

// // ----------------------------------------------------------------------------------------------
// // --- MAIN
// // ----------------------------------------------------------------------------------------------
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// Run the application
				SwingingBIRT theApplication = new SwingingBIRT();
				theApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				theApplication.setVisible(true);
			}
		});
	}
	
	/**
	 * Starts the BIRT platform and engine
	 */
	public void startPlatform()
	{
		try
		{
			// Instantiate the configuration and set the BIRT home (this is where all of the jar files are for the BIRT APIs)
			config = new org.eclipse.birt.report.engine.api.EngineConfig();
			config.setBIRTHome("C:\\Users\\islam.morad\\Google Drive\\GTCMS\\Tools\\birt-runtime-4_4_1-20140916\\birt-runtime-4_4_1\\ReportEngine");  // In a production environment the BIRT home should be read in from a properties file (or equivalent method)

			// Startup the BIRT platform using the configuration setup above
			org.eclipse.birt.core.framework.Platform.startup(config);
			
			// Startup the BIRT engine using the configuration setup above
			org.eclipse.birt.report.engine.api.IReportEngineFactory factory = (org.eclipse.birt.report.engine.api.IReportEngineFactory) org.eclipse.birt.core.framework.Platform.createFactoryObject(org.eclipse.birt.report.engine.api.IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			engine = factory.createReportEngine(config);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Run and render the report.
	 */
	public void runReport()
	{
		
	
			org.eclipse.birt.report.engine.api.IReportRunnable design = null;

			// Open the report design
			//    - Location of the report design does not seem to matter except for the fact that it must exist on a drive somewhere.
			try {
				design = engine.openReportDesign("C:\\Users\\islam.morad\\Google Drive\\GTCMS\\GTCMS\\src\\reports\\new_report_1.rptdesign");
			} catch (EngineException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // In a production environment the report address should be read in from a properties file (or equivalent method)

			// Create task to run and render the report
			org.eclipse.birt.report.engine.api.IRunAndRenderTask task = engine.createRunAndRenderTask(design);

			// Create task options for this report
			org.eclipse.birt.report.engine.api.PDFRenderOption options = new org.eclipse.birt.report.engine.api.PDFRenderOption();
			
			// Create output file for the report
			java.io.FileOutputStream fileOutput = null;
			try {
				fileOutput = new java.io.FileOutputStream("C:\\Users\\islam.morad\\Desktop\\test2.pdf");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  // In a production environment the output file for the report should be dynamically defined
			options.setOutputStream(fileOutput);
			options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
			
			// Set the task options
			task.setRenderOption(options);

			// Pass the report parameters
			//    - The rparam_siteid is the name of a report parameter defined in the report
			  // In production the parameters should be handled dynamically based on the report definition.
			HashMap parameterValues = new HashMap<>();
			parameterValues.put("certificate_id", new Integer(32768));
			parameterValues.put("sensor_id", new Integer(3));
			//parameterValues.put("company_id", new Integer(1));
	
			task.setParameterValues(parameterValues);
			// Set the driver class path for the task
			//    - The class path was pointed to a directory where the MySQL Connector J jar was located on my PC
			task.getAppContext().put("OdaJDBCDriverClassPath", "C:\\Users\\islam.morad\\Google Drive\\GTCMS\\Tools\\mysql-connector-java-5.1.22\\mysql-connector-java-5.1.22-bin.jar"); // In a production environment the value of the class path location should come from a properties file or equivalent method
			
			
			// RUN THE TASK
			try {
				task.run();
			} catch (EngineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// Close out the task
			task.close();
			
			// Close the output file to release the file lock
			try {
				fileOutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Call adobe acrobat reader to render the written PDF report
			try {
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Adobe\\Acrobat 9.0\\Acrobat\\Acrobat.exe C:\\Users\\islam.morad\\Desktop\\test2.pdf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // In a production environment the location of the Acrobat executable must be discoverable or set based on the the unique setup of the PC.
		
		
		
	}

	/**
	 * Stops the BIRT engine and platform
	 */
	public void stopPlatform()
	{
		engine.destroy();
		org.eclipse.birt.core.framework.Platform.shutdown();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
