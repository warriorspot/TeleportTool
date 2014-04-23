/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import com.brennancleveland.notificationcenter.Notification;
import com.brennancleveland.notificationcenter.NotificationCenter;
import com.brennancleveland.notificationcenter.NotificationListener;

/**
 *
 * @author brennan
 */
public class TeleportTool implements NotificationListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* Create and display the form */
        TeleportTool tool = new TeleportTool();
        tool.start();
    }
    
    public void start() {
        NotificationCenter.sharedInstance().addListener(this, ScriptFetcher.ScriptFetcherDidFetchScriptNotification);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FetcherFrame().setVisible(true);
            }
        });
    }
    
    @Override
    public void notificationReceived(Notification notification) {
        if(notification.getName().equals(ScriptFetcher.ScriptFetcherDidFetchScriptNotification)) {
            String script = (String) notification.getData();
            ScriptFrame scriptFrame = new ScriptFrame(script);
            scriptFrame.setVisible(true);
        }
    }
    
}
