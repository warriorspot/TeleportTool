/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import com.brennancleveland.notificationcenter.NotificationCenter;
import com.brennancleveland.notificationcenter.NotificationListener;
import com.brennancleveland.notificationcenter.Notification;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author brennan
 */
public class FetcherFrame extends javax.swing.JFrame implements ScriptFetcherDelegate, NotificationListener {

    /**
     * Creates new form FetcherFrame
     */
    public FetcherFrame() {
        initComponents();
        NotificationCenter.sharedInstance().addListener(this, ScriptFrame.ScriptFrameShowMainNotification);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        airingIdTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        clientIdComboBox = new javax.swing.JComboBox();
        moduleIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Airing ID:");

        jLabel2.setText("Client ID:");

        jButton1.setText("Fetch");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        clientIdComboBox.setModel(new ClientIdModel());

        jLabel3.setText("Module ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jButton1)
                .addGap(98, 98, 98))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(airingIdTextField)
                            .addComponent(moduleIdTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addComponent(clientIdComboBox, 0, 349, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(airingIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduleIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientIdComboBox)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String airingId = this.airingIdTextField.getText();
        String moduleId = this.moduleIdTextField.getText();
        
        if(airingId.length() == 0) {
            JOptionPane.showMessageDialog(this, "Airing ID is required!");
            return;
        }
        
        ClientId id = (ClientId)this.clientIdComboBox.getModel().getSelectedItem();
        if(id == null) {
            JOptionPane.showMessageDialog(this, "Client ID is required!");
            return;
        }
        
        ScriptFetcher.sharedInstance().fetchScript(airingId, id.getId(), moduleId, id.getUrl(), this);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField airingIdTextField;
    private javax.swing.JComboBox clientIdComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField moduleIdTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fetchWillStart() {
        System.out.println("Starting fetch");
    }

    @Override
    public void fetchCompleted(Map userData) {
        String script = (String) userData.get("script");
        String airingId = (String) userData.get("airing_id");
        String clientId = (String) userData.get("client_id");
        String moduleId = (String) userData.get("module_id");
        String url = (String) userData.get("url");
        ScriptFrame scriptFrame = new ScriptFrame(script, airingId, clientId, moduleId, url);
        scriptFrame.setVisible(true);
    }

    @Override
    public void fetchFailed(Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(this, e.toString());
    }
    
    @Override
    public void notificationReceived(Notification n) {
        if(n.getName().equals(ScriptFrame.ScriptFrameShowMainNotification)) {
            this.setVisible(true);
        }
    }
}
