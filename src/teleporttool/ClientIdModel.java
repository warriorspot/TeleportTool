/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author brennan
 */
public class ClientIdModel implements ComboBoxModel {

    private List<ClientId> clientIds;
    private ClientId selectedItem;
    
    public ClientIdModel() {
        //@{@"stage" : @"WVhS7fp", @"demo" : @"YIbxOn8", @"production" : @"d75gDl8"};

        this.clientIds = new ArrayList();
        this.clientIds.add(new ClientId("stage", "WVhS7fp"));
        this.clientIds.add(new ClientId("demo", "YIbxOn8"));
    }
    
    public ClientIdModel(List clientIds) {
        this.clientIds = clientIds;
    }
    
    public void addClientId(ClientId newId) {
        if(null == this.clientIds) {
            this.clientIds = new ArrayList();
        }
        this.clientIds.add(newId);
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
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
   
}
