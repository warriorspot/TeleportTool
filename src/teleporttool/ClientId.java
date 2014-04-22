/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

/**
 *
 * @author brennan
 */
public class ClientId {
    
    private final String name;
    private final String id;
    
    public ClientId(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
