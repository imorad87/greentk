package wizard;

import java.awt.*;

import javax.swing.*;

public class WizardTry extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WizardTry() throws HeadlessException {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.setPreferredSize(new Dimension(800, 600));

        final JScrollPane scroll = new JScrollPane(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(final String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WizardTry().setVisible(true);
            }
        });
    }
}