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
    private final String url;
    
    public ClientId(String name, String id, String url) {
        this.name = name;
        this.id = id;
        this.url = url;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
