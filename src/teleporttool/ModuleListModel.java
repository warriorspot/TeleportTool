/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author brennan
 */
public class ModuleListModel implements ListModel {

    private final ArrayList<Module> modules;
    private List<ListDataListener> dataListeners;
    
    
    public ModuleListModel() {
        modules = new ArrayList();
    }
    
    public void addModules(Collection modules) {
        boolean addAll = this.modules.addAll(modules);
        notifyListeners();
    }
    
    public void removeAllModules() {
        this.modules.clear();
        notifyListeners();
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
        if(this.dataListeners == null) {
            this.dataListeners = new LinkedList();
        }
        
        this.dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListeners.remove(l);
    }
   
    private void notifyListeners() {
        for (ListDataListener listener : this.dataListeners) {
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED,0, this.modules.size()));
        }
    }
    
}
