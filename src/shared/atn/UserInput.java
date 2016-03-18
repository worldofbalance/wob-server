/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.atn;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Justina
 */
// class to display input form for entering directory
class UserInput extends JDialog {

    // Change if you want, but you'll have to fix some other parts too
    private int rows = 3, // Change this to match your number of items
            cols = 4;   // Change this at your own risk

    private final int width = cols * 100,
            height = (rows + 1) * 40;

    private Container content;
    // Special font for control buttons
    private final Font bold = new Font("Sans Serif", Font.BOLD, 20);

    public String destDir;

    //Step1 constructor
    public UserInput(JFrame parent) {
        super(parent, "Get Destination Directory", true);

        final JLabel destDirLabel;
        final JTextField destDirField;
        destDir = "";

        content = getContentPane();

        // Use a grid to layout buttons and add one row for control buttons
        setLayout(new GridLayout(rows + 1, cols));
        setSize(width, height);         // Define dialog dimensions
        setLocationRelativeTo(null);    // Center

        //define labels and data entry fields
        destDirLabel = new JLabel("Destination directory for output CSV files:");
        destDirField = new JTextField(30);
        destDirField.setText(System.getProperty("user.dir"));

        // add information to ContentPane
        content.add(destDirLabel);
        content.add(destDirField);

        // A temporary "Button" variable used to create each button
        JButton button;

        //add action buttons
        button = new JButton("Continue"); // Save info
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed Continue");
                //save data
                if (destDirField.getText().isEmpty()) {
                    destDir = "";
                } else {
                    destDir = destDirField.getText();
                    if (!destDir.endsWith("/")) {
                        destDir = destDir.concat("/");
                    }
                }
                dispose();
            }

        });
        content.add(button);

        button = new JButton("Cancel"); // Cancel
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed Cancel");
                destDir = "";
                dispose();
            }
        });
        content.add(button);
    }
}
