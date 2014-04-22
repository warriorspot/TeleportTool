/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author brennan
 */
public class ModuleListModel implements ListModel {

    private final ArrayList<Module> modules;
    
    public ModuleListModel() {
        modules = new ArrayList();
    }
    
    public void addModules(Collection modules) {
        boolean addAll = this.modules.addAll(modules);
    }
    
    @Override
    public int getSize() {
        return this.modules.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.modules.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
