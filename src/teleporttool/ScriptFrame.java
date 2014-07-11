/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import com.cedarsoftware.util.io.JsonObject;
import com.cedarsoftware.util.io.JsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.Arrays;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author brennan
 */
public class ScriptFrame extends javax.swing.JFrame implements ScriptFetcherDelegate {

    private String script;
    private Map scriptData;
    private String airingId;
    private String clientId;
    private String url;
    private String moduleId;
    
    /**
     * Creates new form ScriptFrame
     */
    public ScriptFrame() {
        initComponents();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public ScriptFrame(String script, String airingId, String clientId, String moduleId, String url) {
        this();
        
        this.script = script;
        this.airingId = airingId;
        this.clientId = clientId;
        this.url = url;
        this.moduleId = moduleId;
        
        updateWithScript(script);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        scriptTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scriptTextArea.setColumns(20);
        scriptTextArea.setLineWrap(true);
        scriptTextArea.setRows(5);
        jScrollPane1.setViewportView(scriptTextArea);

        jList1.setModel(new ModuleListModel());
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Refresh");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if(!jList1.getValueIsAdjusting()){
            Module module = (Module)this.jList1.getModel().getElementAt(this.jList1.getSelectedIndex());
            ModuleFrame moduleFrame = new ModuleFrame(module);
            moduleFrame.setVisible(true);
            
            this.jList1.clearSelection();
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        refreshScript();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private String prettyJSON(String uglyJSON) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSON);
        return gson.toJson(je);
    }
    
    private void refreshScript() {
        ScriptFetcher.sharedInstance().fetchScript(this.airingId, this.clientId, this.moduleId, this.url, this);
    }

    private void updateWithScript(String script) {
        this.scriptData = JsonReader.toMaps(script);
        
        ModuleListModel model = (ModuleListModel) this.jList1.getModel();
        model.removeAllModules();
        
        // Assume if the moduleId property is non-null, that this
        // script is a Big Red Button script, and the script itself
        // represents a single module.
        if(this.moduleId != null && this.moduleId.length() > 0) {
            model.addModule(Module.fromMap(scriptData));
        }
        else {
            JsonObject object = (JsonObject)this.scriptData.get("modules");
            if(object != null) {
                Object[] modules = object.getArray();
                model.addModules(Module.fromCollection(Arrays.asList(modules)));
            }
        }
        
        this.scriptTextArea.setText(prettyJSON(script));
        this.scriptTextArea.setCaretPosition(0);
        
        String title = this.airingId + " : " + this.scriptData.get("duration").toString() + " sec";
        this.setTitle(title);
    }
    
    public javax.swing.JTextArea getScriptTextArea() {
        return this.scriptTextArea;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea scriptTextArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fetchWillStart() {
        
    }

    @Override
    public void fetchCompleted(Map userData) {
        this.script = (String) userData.get("script");
        updateWithScript(this.script);
    }

    @Override
    public void fetchFailed(Exception e) {
        JOptionPane.showMessageDialog(this, e.toString());
    }

}
