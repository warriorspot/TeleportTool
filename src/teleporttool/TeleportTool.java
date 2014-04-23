/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author brennan
 */
public class TeleportTool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /* Create and display the form */
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TeleportTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TeleportTool tool = new TeleportTool();
        tool.start();
    }
    
    public void start() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FetcherFrame().setVisible(true);
            }
        });
    }
}
