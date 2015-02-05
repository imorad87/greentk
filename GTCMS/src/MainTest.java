
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import java.util.*;
import java.net.*;

public class MainTest

extends JFrame

{

	JEditorPane myEditorPane = null;

	IReportEngine engine = null;

	EngineConfig config = null;

	MainTest()

	{

		myEditorPane = new JEditorPane();

		myEditorPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(myEditorPane);

		JFrame myframe = new JFrame("BIRT Report");

		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myframe.getContentPane().add(scrollPane);

		myframe.setSize(600, 600);

		myframe.setVisible(true);

	}

	public void runReport()

	{

		try {

			IReportRunnable design = null;

			// Open the report design

			design = engine
					.openReportDesign("C:\\Users\\islam.morad\\workspaceluna\\test\\src\\test\\new_report.rptdesign");

			// Create task to run and render the report,

			IRunAndRenderTask task = engine.createRunAndRenderTask(design);

		
			HashMap parameterValues = new HashMap<>();
			parameterValues.put("certificate_id", new Integer(32768));
			parameterValues.put("company_id", new Integer(1));
	
			task.setParameterValues(parameterValues);

		
			PDFRenderOption options = new PDFRenderOption();

			// HTMLRenderOption options = new HTMLRenderOption();

			java.io.FileOutputStream fileOutput = new java.io.FileOutputStream("C:\\Users\\islam.morad\\Desktop\\test1.pdf");  // In a production environment the output file for the report should be dynamically defined
			options.setOutputStream(fileOutput);
			options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);

			// options.setEmbeddable(true);

			task.setRenderOption(options);

			task.run();

			task.close();

			/*myEditorPane.setContentType("text/html");

			myEditorPane.setText(bos.toString());*/

			System.out.println("Finished Gen");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	public void startPlatform() {

		try {

			config = new EngineConfig();

			config.setBIRTHome("C:\\Users\\islam.morad\\Google Drive\\GTCMS\\Tools\\birt-runtime-4_4_1-20140916\\birt-runtime-4_4_1\\ReportEngine");
			HashMap context = new HashMap();

			URLClassLoader cl = (URLClassLoader) MainTest.class
					.getClassLoader();
			URL[] myurls = cl.getURLs();

			Class cl1 = cl.loadClass("com.mysql.jdbc.Driver");
			context.put("PARENT_CLASSLOADER", cl);
			context.put(
					"OdaJDBCDriverClassPath",
					"C:\\Users\\islam.morad\\Google Drive\\GTCMS\\Tools\\mysql-connector-java-5.1.22\\mysql-connector-java-5.1.22-bin.jar");
			config.setAppContext(context);

			Platform.startup(config);

			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

			engine = factory.createReportEngine(config);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void stopPlatform() {

		engine.destroy();

		Platform.shutdown();

	}

	public static void main(String[] args)

	{
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			
			MainTest html = new MainTest();

			html.startPlatform();

			System.out.println("Started");

			html.runReport();

			html.stopPlatform();

			System.out.println("Finished");

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}