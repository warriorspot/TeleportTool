/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import java.util.Map;

/**
 *
 * @author brennan
 */
public interface ScriptFetcherDelegate {
 
    public void fetchWillStart();
    public void fetchCompleted(Map userData);
    public void fetchFailed(Exception e);
    
}
