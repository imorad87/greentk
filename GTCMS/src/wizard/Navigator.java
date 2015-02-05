package wizard;

/*
 * WizardPanel.java
 * Created on June 3, 2002, 6:05 PM
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.*;

/**
 * A Navigator takes care of creating and displaying a panel with four buttons
 * as well as showing the appropriate WizardPanel based on button clicks by the
 * user. It is called by the WizardPanel class. The programmer should not have
 * to modify this class or explicitly call it. The only reason to modify this
 * class is to change the placement of or look of the buttons. <BR>
 * <BR>
 * The job of the Navigator is to:<BR>
 * <OL>
 * <LI>Manage Button Visibility - is next button greyed out or active?
 * <LI>Display the appropriate WizardPanel
 * </OL>
 * 
 * @author Ruel Loehr
 * @version
 */
public class Navigator extends Object implements ActionListener {

	private Vector panels = new Vector(); // a vector of wizard panels
	private JPanel navPanel = null;
	private JPanel oldPanel = null;
	private JButton prevButton = null;
	private JButton nextButton = null;
	private JButton cancelledButton = null;
	private JButton finishedButton = null;
	private WizardDialog parentFrame = null;
	private WizardPanel currentWizPanel = null;
	private WizardPanel prevWizPanel = null;

	/**
	 * Creates a Navigator that will display the WizardPanels contained in
	 * Vector p and will itself be diplayed in WizardDialog j.
	 * 
	 * @param p
	 *            The vector of WizardPanels to display
	 * @param j
	 *            The WizardDialog to display this Navigator in
	 */
	public Navigator(Vector p, WizardDialog j) {
		super();
		this.panels = p; // store the vector
		this.parentFrame = j; // store the WizardDialog
		// this.lastPanel = panels.size(); //set the size

		// if there is not a current WizardPanel, it is the first time through
		// set it up with the first wizard panel
		if (currentWizPanel == null) {
			currentWizPanel = (WizardPanel) WizardDialog.getPanels().elementAt(0);
		}

		// show the current WizardPanel
		show(currentWizPanel);
	}

	/**
	 * Handles the events of the buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Cancel")) {

			int confirmDialog = JOptionPane
					.showConfirmDialog(this.getParent(),
							"No data will be saved. Do you want to proceed with canceling?");
			if (confirmDialog == JOptionPane.OK_OPTION) {
				
				this.getParent().dispose();// or normally dispose of the parent
			}
		} else if (e.getActionCommand().equals("Finished")) {
			currentWizPanel.validateInfo(); // make sure all necessary info is
											// correct
			this.getParent().doIt(); // begin the processing on all info entered
										// into the wizard
		} else if (e.getActionCommand().equals("Previous")) {
			currentWizPanel = WizardDialog.getWizPanel(currentWizPanel)
					.getPrevious(); // get the parent panel

			// if you click previous, next should then be enabled
			nextButton.setEnabled(true);

			// if you click previous, finished should never be enabled
			finishedButton.setEnabled(false);

			// disable prevous if the current panel is the first panel
			if (currentWizPanel.getPrevious() == null) {
				prevButton.setEnabled(false);
			} else {
				prevButton.setEnabled(true);
			}

			// show the current panel
			this.show(currentWizPanel);
		} else if (e.getActionCommand().equals("Next")) {
			// validate the information the the user entered
			// if it is all good, get the next panel and display it
			if (currentWizPanel.validateInfo()) {
				// get the child panel
				currentWizPanel = WizardDialog.getWizPanel(currentWizPanel)
						.getNext();
			}

			// show the panel
			this.show(currentWizPanel);

			// enable Finished (if necessary) and disable Next
			prevButton.setEnabled(true);

			if (currentWizPanel.getNext() == null) {
				nextButton.setEnabled(false);
				finishedButton.setEnabled(true);
			} else {
				finishedButton.setEnabled(false);
			}
		}
	}

	/**
	 * this is the parent container; throws an exception if you have placed the
	 * Navigator on anything other than a WizardDialog content pane..
	 * 
	 * @return wizard.WizardDialog
	 */
	private WizardDialog getParent() {
		return this.parentFrame;
	}

	/**
	 * The view for navigator
	 * 
	 * @return JPanel
	 */
	protected JPanel getView() {
		// setup the buttons
		if (navPanel == null) {
			navPanel = new JPanel();
			FlowLayout g4 = new FlowLayout();
			navPanel.setLayout(g4);

			Insets defaultInsets = new Insets(0, 0, 0, 0);
			GridBagConstraints constraints = new GridBagConstraints(0, 0, 0, 0,
					0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
					defaultInsets, 0, 0);

			prevButton = new JButton();
			prevButton.setText("< Previous");
			prevButton.setActionCommand("Previous");
			prevButton.addActionListener(this);
			navPanel.add(prevButton);
			prevButton.setEnabled(false); // initially disabled

			Insets defaultInsets1 = new Insets(10, 35, 10, 20);
			GridBagConstraints constraints1 = new GridBagConstraints(0, 1, 0,
					0, 0.0, 0.0, GridBagConstraints.WEST,
					GridBagConstraints.NONE, defaultInsets1, 0, 0);
			nextButton = new JButton();
			nextButton.setText("Next >");
			nextButton.setActionCommand("Next");
			nextButton.addActionListener(this);
			navPanel.add(nextButton);

			Insets defaultInsets2 = new Insets(10, 35, 10, 20);
			GridBagConstraints constraints2 = new GridBagConstraints(2, 0, 0,
					0, 0.0, 0.0, GridBagConstraints.WEST,
					GridBagConstraints.NONE, defaultInsets2, 0, 0);
			finishedButton = new JButton();
			finishedButton.setText("Finished");
			finishedButton.setActionCommand("Finished");
			finishedButton.addActionListener(this);
			navPanel.add(finishedButton);
			finishedButton.setEnabled(false);

			Insets defaultInsets3 = new Insets(10, 35, 10, 20);
			GridBagConstraints constraints3 = new GridBagConstraints(3, 0, 0,
					0, 0.0, 0.0, GridBagConstraints.WEST,
					GridBagConstraints.NONE, defaultInsets3, 0, 0);
			cancelledButton = new JButton();
			cancelledButton.setText("Cancel");
			cancelledButton.setActionCommand("Cancel");
			cancelledButton.addActionListener(this);
			navPanel.add(cancelledButton);

		}

		return navPanel;
	}

	/**
	 * Set the size of the Navigator Panel
	 * 
	 * @param w
	 *            int
	 * @param h
	 *            int
	 */
	protected void setSize(int w, int h) {

		this.getView().setSize(w, h);
	}

	/**
	 * Display a particular panel in the Vector
	 * 
	 * @param wp
	 *            WizardPanel The WizardPanel you wish to display
	 */
	private void show(WizardPanel wp) {
		WizardPanel newPanel;

		// remove the old one
		if (oldPanel != null) {
			this.getParent().getContentPane().remove(oldPanel);
		}

		newPanel = currentWizPanel; // get thew new panel you want to display
		newPanel.setVisible(true);

		this.getParent().getContentPane().add(newPanel, BorderLayout.CENTER);
		this.getParent().validate();
		this.getParent().repaint();

		oldPanel = newPanel; // swap for next call

	}
}
