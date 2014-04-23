/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import com.cedarsoftware.util.io.JsonObject;
import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author brennan
 */
public class ScriptFrame extends javax.swing.JFrame {

    private String script;
    private Map scriptData;
    
    /**
     * Creates new form ScriptFrame
     */
    public ScriptFrame() {
        initComponents();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public ScriptFrame(String script) {
        this();
        
        this.script = script;
        this.scriptData = JsonReader.toMaps(script);
        ModuleListModel model = (ModuleListModel) this.jList1.getModel();
        
        JsonObject object = (JsonObject)this.scriptData.get("modules");
        Object[] modules = object.getArray();
        model.addModules(Module.fromCollection(Arrays.asList(modules)));
        this.scriptTextArea.setText(prettyJSON(script));
        this.scriptTextArea.setCaretPosition(0);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
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
        }
    }//GEN-LAST:event_jList1ValueChanged

    private String prettyJSON(String uglyJSON) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSON);
        return gson.toJson(je);
    }

    public javax.swing.JTextArea getScriptTextArea() {
        return this.scriptTextArea;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea scriptTextArea;
    // End of variables declaration//GEN-END:variables

}
