package wizard;

/*
 * WizardDialog.java
 * Do you see this dumb text here?
 * Created on June 3, 2002, 6:05 PM
 */

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * <PRE>
 * A WizardDialog is used to create a wizard. It 
 * is similiar to a JDialog except for the fact that
 * it contains:
 * <OL>
 * <LI>A vector of WizardPanels
 * <LI>A Navigator Panel
 * </OL>
 * The WizardDialog is setup with a BorderLayout.
 * The WizardPanels are show in the center pane and the
 * Navigator is displayed in the southern pane.
 * The WizardDialog class constructs a basic wizard. 
 * It is designed with the idea that the programmer will 
 * extend it in the following fashion:
 * <CODE>
 *  public final class YourClassName extends Files.JWizard.WizardDialog
 *  {   
 *  }
 * </CODE>
 * The programmer is responsible for implementing two 
 * classes in their subclass implementation
 * <OL>
 * <LI> public static void main(java.lang.String[] args)  - initialize the WizardDialog
 * <LI> protected void makeEachScreen() - add each WizardPanel (dialog) that you want the end user to see
 * </OL>
 * 
 * An Example main function:
 * <CODE>
 *  public static void main(java.lang.String[] args)
 *   {
 *      YourWizardClassName j = new YourWizardClassName("Text to appear in title bar");
 *      j.setBounds(300, 150, 600, 475);   //set the bounds of your wizard (x, y, width, height)  600 and 475 are recommended
 *      j.show();  //show your wizard
 *   }</CODE>
 * 
 * An Example makeEachScreen function:
 * <CODE>
 *   protected void makeEachScreen()
 *   {      
 *       //declare all WizardPanels 1st
 *       final WizardPanel jp3 = new WizardPanel();   
 *       final WizardPanel jp4 = new WizardPanel();
 * 
 *      //Setup the first panel*************
 *       jp3.setBorder(BorderFactory.createEtchedBorder());
 *       jp3.setName("jp3");  //set the name of the panel
 *          
 *       //the child of panel 3 (what you see when you click the next button) is WizardPanel jp4
 *       //the parent of jp4 is jp3
 *       jp3.setNext(jp4, jp3); //set the child of the panel, param 2 is the calling panel(parent)
 *       
 *       JLabel jp5l1= new JLabel();
 *       jp5l1.setText("This is the first dialog");  //set the text
 *       jp3.add(jp5l1);  //add the label        
 * 
 *      //Setup Panel 6 the final panel***********
 *       jp4.setBorder(BorderFactory.createEtchedBorder());
 *       jp4.setName("jp4");  //set the name of the panel
 *       //setup the parent child relationship
 *       jp4.setNext(null, jp4); //set the child of the panel (
 *       //this is the last WizardPanel, it has no children, param 2 is the calling panel(parent)
 *       
 *       JLabel jp5l2= new JLabel();
 *       jp5l1.setText("This is the last dialog");  //set the text
 *       jp4.add(jp5l2);  //add the label        
 * 
 *       //add all of your panels to vector of WizardPanels
 *       addPanel(jp3);  //add the first panel to the WizardDialog Vector
 *       addPanel(jp4);  //last panel
 *   }</CODE>
 * </PRE>
 * 
 * @author Ruel Loehr
 * @version
 */
public class WizardDialog extends JDialog {

	static Vector panels = new Vector(); // a vector that holds each of the
											// wizard display screens, each
											// screen is one panel

	/**
	 * Creates a new WizardDialog
	 * 
	 * @param title
	 *            The text that you wish to placed on the title bar
	 */
	public WizardDialog(String title) {
		super();
		initComponents(); // setup the WizardPanel dialouge

		BorderLayout b4 = new BorderLayout();

		this.getContentPane().setLayout(b4); // the whole dialog window
		Navigator n = new Navigator(getPanels(), this); // make a new navigator
		n.setSize(400, 100);
		this.getContentPane().add(n.getView(), BorderLayout.SOUTH);

		setWizardTitle(title);

	}

	/**
	 * Sets the vector of panels
	 * 
	 * @param v
	 *            The vector you wish to store
	 */
	protected static void setPanels(Vector v) {
		panels = v;
	}

	/**
	 * Adds a panel to the Vector of WizardPanels
	 * 
	 * @param j
	 *            The WizardPanel you wish to add
	 * @return void
	 */
	protected static void addPanel(WizardPanel j) {
		panels.addElement(j);
	}

	/**
	 * Returns the current vector of WizardPanels
	 * 
	 * @return Vector Contains WizardPanels
	 */
	protected static Vector getPanels() {
		return (panels);
	}

	/**
	 * Returns the wizard panel from the vector
	 * 
	 * @param j
	 *            The WizardPanel you wish to get
	 * @return WizardPanel
	 */
	protected static WizardPanel getWizPanel(WizardPanel wp) {

		for (int i = 0; i < panels.size(); i++) {
			if (panels.elementAt(i) == wp) {
				return ((WizardPanel) panels.elementAt(i));
			}

		}

		return ((WizardPanel) panels.elementAt(0));

	}

	/**
	 * Sets the tile of the WizardDialog
	 * 
	 * @param title
	 *            The title you wish to see in the title bar
	 * @return void
	 */
	protected void setWizardTitle(String title) {
		this.setTitle(title);
	}

	/**
	 * You must define this function. Add each WizardPanel that you wish for
	 * your user to see. Then add it to the Vector panels: see method
	 * addPanel(WizardPanel j).
	 */
	protected void makeEachScreen() {

		final WizardPanel panel1; // this is the main panel

		// setup the the main window
		panel1 = new WizardPanel();
		panel1.setBorder(BorderFactory.createEtchedBorder());
		panel1.setName("panel1");
		panel1.setNext(null, null);

		// make and components to the 1st WizardPanel
		JLabel label1 = new JLabel();

		// make the label
		label1.setHorizontalAlignment(SwingConstants.LEADING);
		label1.setVerticalAlignment(SwingConstants.TOP);
		label1.setText("If you can see this you must implement the method makeEachScreen() for you WizardDialog Class");

		panel1.add(label1);

		addPanel(panel1);
	}

	/**
	 * Sets the default settings for the WizardDialog
	 * 
	 * @return void
	 */
	protected void initComponents() {
		try {
			// UIManager.setLookAndFeel(new
			// com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
			this.getContentPane().setSize(900, 400);
			makeEachScreen();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * User must define this function. Define what you want to happen when
	 * finish button is clicked
	 */
	protected void doIt() {
		JOptionPane
				.showMessageDialog(
						this,
						"If you see this message you must overide the method doIt() in class WizardDialog");
	}

}