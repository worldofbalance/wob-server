/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.simulation.simjob;

import shared.simulation.simjob.FormCustomSim;
import shared.simulation.simjob.FormBatchSim;
import shared.core.GameServer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import shared.metadata.Constants;
import shared.model.SpeciesType;

/**
 * SimJobMenu is a JFrame menu for running simulations and contains the main()
 * function. I was learning about Java GUI as I implemented this and the functionality
 has some problems.  For instance, the menu cannot be exited by clicking on the
 red x in the upper left hand corner.  Two forms are called from this menu,
 FormRandomSim and FormCustomSim.  FormCustomSim was the only one that was 
 implemented using NetBeans GUI design builder.
 * 
 * @author Justina
 */
class SimJobMenu extends JFrame {

    private final int rows = 4,
            cols = 1,
            width = cols * 400,
            height = rows * 50;
    // Special font for control buttons
    private final Font bold = new Font("Sans Serif", Font.BOLD, 20);
    // Menu options
    private final String[] functions = {
        "Random Simulation",
        "Custom Simulation(s)",
        "Run Batch Simulations",  //9/25/14, jtc, added
        "Create Param Analysis Sims"  //10/27/14, jtc, added
};
//    private static SimulationEngine se;

    public SimJobMenu() {
        super("Simulation Job Menu");

        //9/25/14 - shifted simulation engine creation down to processes using it
        //(SimJobManager)
//        se = new SimulationEngine();
        
        GameServer.getInstance();  //load species information
        /* read in experimental variables only used for running simulation jobs*/
        //9/25/14, JTC, integration with Gary's code (ECOSYSTEM_TYPE)
        SpeciesType.loadSimTestNodeParams(Constants.ECOSYSTEM_TYPE);
        SpeciesType.loadSimTestLinkParams(Constants.ECOSYSTEM_TYPE); 

        setSize(width, height);         // Define dialog dimensions
        setLocationRelativeTo(null);    // Center
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void addContents(final JFrame frame) {

        final JPanel content = new JPanel();
        add(content);
        content.setLayout(new GridLayout(rows, cols));
        // A temporary "Button" variable used to create each button
        JButton button;

        // Control function buttons        
        button = new JButton(functions[0]); // Random Simulation
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[0]);

                /* Create and display the form */
                getContentPane().setVisible(false);
                //9/25/14, jtc, pass Frame to new form
                //9/25/14, jtc, moved multi-job loop back to form; send
                //"modal=true" and se as parameters.
                FormRandomSim rForm = new FormRandomSim((Frame) frame, true);
                rForm.setVisible(true);
                getContentPane().setVisible(true);

            }
        });
        content.add(button);

        button = new JButton(functions[1]); // Custom Simulation
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[1]);
                /* Create and display the form */
                getContentPane().setVisible(false);
                FormCustomSim cForm = new FormCustomSim();
                cForm.setVisible(true);
                getContentPane().setVisible(true);

            }
        });
        
        //9/24/14, jtc, add batch functionality
        content.add(button);
        button = new JButton(functions[2]); // Batch Simulations
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[2]);
                /* Create and display the form */
                getContentPane().setVisible(false);
                FormBatchSim cForm = new FormBatchSim((Frame) frame, true);
                cForm.setVisible(true);
                getContentPane().setVisible(true);

            }
        });
        content.add(button);

        //10/26/14, jtc, add param analysis functionality
        content.add(button);
        button = new JButton(functions[3]); // Param Analysis Sims
        button.setFont(bold);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + functions[3]);
                /* Create and display the form */
                getContentPane().setVisible(false);
                FormParamAnalSim cForm = new FormParamAnalSim();
                cForm.setVisible(true);
                getContentPane().setVisible(true);

            }
        });
        content.add(button);
}

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SimJobMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimJobMenu menu = new SimJobMenu();
                //9/25/14, jtc, add close functionality
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //9/25/14, jtc, pass frame for use by actionlisteners
                menu.addContents(menu);
                menu.setVisible(true);
            }
        });
    }

}
