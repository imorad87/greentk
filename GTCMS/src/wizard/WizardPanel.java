package wizard;
/*
 * WizardPanel.java
 * Do you see this dumb text here?
 * Created on June 3, 2002, 6:05 PM
 */
import javax.swing.JDialog;
import javax.swing.JOptionPane;


/**
 * A WizardPanel is simply an addition to the JPanel class.  It adds a tree-like
 * functionalty to a JPanel.  Each WizardPanel is one dialog
 * screen that the end user will see.
 * It contains 4 additional data members that JPanel does not:
 * <OL>
 * <LI>WizardPanel next - the next WizardPanel to display
 * <LI>WizardPanel previous - the previous WizardPanel to display
 * <LI>String name - the name of the WizardPanel
 * <LI>InputValidator v - the InputValidator to use for this WizardPanel
 * </OL>
 * <BR>
 * These 4 fields are used by the Navigator class to move
 * through the tree and to validate the entered data.<BR><BR>
 * The programmer basically consructs a WizardPanel just as he would a JPanel, only you must call method setNext(next, this) for each WizardPanel<BR><BR>
 * The WizardPanel class is called by the user in their subclass of WizardDialog in function makeEachScreen().  An example follows:<BR><BR>
 * <PRE><CODE>
        //make 2 new WizardPanels, (be sure to declare them up at the top, since they reference each other)
        final WizardPanel jp2 = new WizardPanel();
        final WizardPanel jp3 = new WizardPanel();

        jp2.setName("panel1");
        jp2.setNext(jp3, jp2);  //set up the parent child relationship
        jp2.addInputValidator(new Files.JWizard.InputValidator()
            {
                public String validate()
                {
                    //you can add your input validation here
                    return("1");  //or return "Your error message"  see interface: InputValidator
                }
            );

        //make and add components to the 1st WizardPanel
        JLabel label1= new JLabel();

        //make the label
        label1.setHorizontalAlignment(JLabel.LEADING);
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setText("Welcome to the migration wizard. How would you like to");

        JLabel label2=new JLabel();
        label2.setHorizontalAlignment(JLabel.LEADING);
        label2.setVerticalAlignment(JLabel.TOP);
        label2.setText("proceed?");

        jp3.setName("jp3");
        jp3.setNext(null, jp3);

        //make and components to the 2nd WizardPanel
        JLabel label3 = new JLabel();

        //make the label
        label3.setHorizontalAlignment(JLabel.LEADING);
        label3.setVerticalAlignment(JLabel.TOP);
        label3.setText("This is the last dialouge the user sees");

        jp3.add(label3);  //add label to WizardPanel
        //don't forget, you can add an InputValidator as well
        //now use method addpanel from class WizardDialog to add the panel to a vector: see class WizardDialog for details
 </CODE></PRE>
 </P>
 * @author  Ruel Loehr
 * @version
 */
public class WizardPanel extends javax.swing.JPanel
{

    private WizardPanel next;
    private WizardPanel previous;
    private String name;
    private InputValidator v;

    /** Creates a new WizardPanel */
    public WizardPanel()
    {
        super();
    }
    /** Add an InputValidator to the panel*/
    public void addInputValidator(InputValidator p_validator)
    {
        v=p_validator;
    }

    /** Validate the information that the user entered.  If no problems occured
     * you get to proceed to the next WizardPanel, if a problem has occured
     * display a dialog box with the error message.  The Navigator calls this function
     * the programmer should not explicity call it.
     * @return boolean true if no problems occured false otherwise*/
    public boolean validateInfo()
    {
        //if no  InputValidator was added to this class
        //there is no info to validate, so return true (true=everything is okay)
        if (v==null)
            return(true);

        //otherwise call the user defined validate function
        String message=v.validate();

        //if "1" was returned, no problem has occurred return true
        if (message=="1")
            return(true);
        else
        {
           //otherwise get the user defined error message and display it in a dialog box
           JOptionPane pane = new JOptionPane(message);
           JDialog dialog = pane.createDialog(this, "Error");
           dialog.show();

         return(false);
        }


    }


    /** Set the name of the WizardPanel
     * @param String n The name of the panel */
    @Override
	public void setName(String n)
    {
        name=n;
    }

    /** Returns the name of the WizardPanel
     * @return String Name of the WizardPanel */
    @Override
	public String getName()
    {
        return(name);
    }

    /** Sets the next WizardPanel to be displayed.
        Also updates the "previous" property of each WizardPanel
        Similiar to fixdown() in a tree
        @param n The WizardPanel that you wish to assign as the child
        @param p The Wizard Panel that you wish to assign as the parent (calling panel)*/
    public void setNext(WizardPanel n,  WizardPanel p)
    {
        next=n;  //set the next property (the next panel to be displayed
        if (n!=null)      //if it is not designated "last" panel to be displayed
        {
            n.setPrevious(p);  //set the parent

            WizardPanel prev;  //used for iteration
            prev=n;  //pointer to parent

            //for each subsequent panel
            for (WizardPanel i = n.getNext(); i !=null; i=i.getNext())
            {
                //set the parent to the panel before
                i.setPrevious(prev);
                //back up 1 panel
                prev=prev.getNext();

            }
        }

    }

    /** Returns the next WizardPanel (the child)
     * @return WizardPanel */
    public WizardPanel getNext()
    {
        return(next);
    }

    /** Set the parent of this WizardPanel
     * @param WizardPanel The parent panel
     * @return void */
    public void setPrevious(WizardPanel p)
    {
        previous=p;
    }

    /** Returns the parent of this WizardPanel
     * @return WizardPanel The parent panel */
    public WizardPanel getPrevious()
    {
        return(previous);
    }
}
