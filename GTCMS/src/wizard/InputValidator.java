/*
 * InputValidator.java
 *
 * Created on June 5, 2002, 8:24 PM
 */

package wizard;

/**
 * An InputValidator is an Interface that provides one method:  String validate().<BR>
 * In this method, a user may define a custom way of handling data. <BR> A example of using this interface
 * would be to add an InputValidator into a WizardPanel.<BR><BR>
 * Example:<BR>
 *<P><CODE><BR>
 *       <FONT COLOR=RED>//add an input validator to the current wizard panel</FONT><BR>
 *       panel1.addInputValidator(new Files.JWizard.InputValidator()    <FONT COLOR=RED>//define right there in function</FONT><BR>
           &nbsp;&nbsp; {<BR>
           &nbsp;&nbsp;&nbsp;public String validate()   <FONT COLOR=RED>//user implements the interface function</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  {<BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (jrb1.isSelected())   <FONT COLOR=RED>//if jradiobutton1 is selected</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;setExport(1);   <FONT COLOR=RED>//set the value of some private data</FONT><BR>                                   
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jp3.setNext(jp5, jp3);  <FONT COLOR=RED>//set the value of the next WizardPanel</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return("1");  <FONT COLOR=RED>//returning the string "1" implies that everything is okay to proceed</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (jrb2.isSelected())  <FONT COLOR=RED>//if jradiobutton 2 is selected </FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{  <BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;setImport(1);  <FONT COLOR=RED>//set the value of some private data</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jp3.setNext(jp2, jp3); <FONT COLOR=RED>//set the value of the next WizardPanel</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return("1");  <FONT COLOR=RED>//returning the string "1" implies that everything is okay to proceed</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return("You must choose to import or export");   <FONT COLOR=RED>//returning anything other than "1" implies problem, print returned string</FONT><BR>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<FONT COLOR=RED>//in a pop up dialog box</FONT><BR><BR>
                    
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<BR><BR>
                
   &nbsp;&nbsp;         }<BR>
 </CODE></P>
 *               
 * @author  Ruel Loehr
 * @version
 */
public interface InputValidator {

     /** validation method for the programmer to override, for WizardPanel return "1", or "Error message"
     @return String "1" or "Error Message"*/         
    public String validate();
    
 
    
}

