/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author brennan
 */
public class Module {
    
    private Long id;
    private Long timestamp;
    private String type;
    private Object data;
    private String modified;
    private Long ttl;

    public static Collection fromCollection(Collection list) {
        ArrayList moduleList = new ArrayList(list.size());
        Iterator iter = list.iterator();
       
        while(iter.hasNext()) {
            Map next = (Map)iter.next();
            Module module = Module.fromMap(next);
            moduleList.add(module);
        }
        
        return moduleList;
    }
    
    public static Module fromMap(Map map) {
        Module module = new Module();
        
        module.id = (Long)map.get("id");
        module.timestamp = (Long)map.get("timestamp");
        module.type = (String)map.get("type");
        module.data = map.get("data");
        module.modified = (String)map.get("modified");
        module.ttl = (Long)map.get("ttl");
        
        return module;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * @return the ttl
     */
    public Long getTtl() {
        return ttl;
    }

    /**
     * @param ttl the ttl to set
     */
    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
    
    @Override
    public String toString() {
        return this.timestamp.toString() + " - " + this.type;
    }
}
