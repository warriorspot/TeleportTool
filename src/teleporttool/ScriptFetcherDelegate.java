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
public interface ScriptFetcherDelegate {
 
    public void fetchWillStart();
    public void fetchCompleted(String script);
    public void fetchFailed(Exception e);
    
}
