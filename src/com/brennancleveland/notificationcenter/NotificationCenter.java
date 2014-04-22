package com.brennancleveland.notificationcenter;

import java.util.*;

public class NotificationCenter {

    private final HashMap<String, LinkedList> observers;

    private static NotificationCenter instance = null;
    
    public static NotificationCenter sharedInstance() {
        if(instance == null) {
            instance = new NotificationCenter();
        }
        return instance;
    }
    
    public NotificationCenter() {
        this.observers = new HashMap<>();
    }

    public void addListener(NotificationListener listener, String notificationName) {
        LinkedList list = this.observers.get(notificationName);
        if(list == null) {
            list = new LinkedList();
            list.add(listener);
            this.observers.put(notificationName, list);
        }			
        else {
            list.add(listener);
        }
    }

    public void postNotification(String name, Object data) {
        LinkedList list = this.observers.get(name);
        if(list != null) {
            Iterator iter = list.iterator();
            while(iter.hasNext()) {
                NotificationListener listener;
                listener = (NotificationListener)iter.next();
                listener.notificationReceived(new Notification(name, data));
            }
        }
    }

    public void removeListener(NotificationListener listener) {
        Collection values = this.observers.values();
        Iterator iter = values.iterator();
        while(iter.hasNext()) {
            LinkedList list = (LinkedList) iter.next();
            list.remove(listener);
        }
    }
}
