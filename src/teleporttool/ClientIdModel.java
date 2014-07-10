/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author brennan
 */
public class ClientIdModel implements ComboBoxModel {

    private List<ClientId> clientIds;
    private ClientId selectedItem;
    private List<ListDataListener> dataListeners;
    
    public ClientIdModel() {
        //@{@"stage" : @"WVhS7fp", @"demo" : @"YIbxOn8", @"production" : @"d75gDl8"};

        this.clientIds = new ArrayList();
        this.clientIds.add(new ClientId("stage", "WVhS7fp", "https://stage.cdn.teleport.gravitymobile.com/"));
        this.clientIds.add(new ClientId("demo", "YIbxOn8", "https://stage.cdn.teleport.gravitymobile.com/" ));
        this.clientIds.add(new ClientId("gravity-dev", "testing", "https://s3.amazonaws.com/gravity-development/"));   
    }
    
    public ClientIdModel(List clientIds) {
        this.clientIds = clientIds;
    }
    
    public void addClientId(ClientId newId) {
        if(null == this.clientIds) {
            this.clientIds = new ArrayList();
        }
        this.clientIds.add(newId);
        notifyListeners();
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        int index = this.clientIds.indexOf(anItem);
        if(index != -1) {
            this.selectedItem = this.clientIds.get(index);
        }
        else {
            this.selectedItem = null;
        }
        
    }

    @Override
    public Object getSelectedItem() {
        return this.selectedItem;
    }

    @Override
    public int getSize() {
        return this.clientIds.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.clientIds.get(index);
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
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED,0, this.clientIds.size()));
        }
    }
}
