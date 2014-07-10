/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teleporttool;

import com.brennancleveland.notificationcenter.NotificationCenter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author brennan
 */
public class ScriptFetcher {
   
    private ScriptFetcherDelegate delegate;
    private String airingId;
    private String clientId;
    private String url;
    
    private static ScriptFetcher instance = null;
    public static final String ScriptFetcherDidFetchScriptNotification = "ScriptFetcherDidFetchScript";
    
    public static ScriptFetcher sharedInstance() {
        if(instance == null) {
            instance = new ScriptFetcher();
        }
        return instance;
    }
    
    public String fetchScript() {
        return fetchScript(this.airingId, this.clientId, this.url, this.delegate);
    }
    
    public String fetchScript(String airingId, String clientId, String url) {
        return fetchScript(airingId, clientId, url, this.delegate);
    }
    
    public String fetchScript(String airingId, String clientId,  String cdnUrl, ScriptFetcherDelegate delegate) {
        String line;
        
        this.disableSSLChecking();
        
        if(delegate != null) {
            delegate.fetchWillStart();
        }
        
        String urlString = cdnUrl + clientId + "/" + airingId + "/script.json";
        System.out.println(urlString);
        String data = new String();
        HashMap userData = new HashMap();
       
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while((line = reader.readLine()) != null) {
                data += line + "\n";
            }
            
            userData.put("script", data);
            userData.put("client_id", clientId);
            userData.put("airing_id", airingId);
            userData.put("url", cdnUrl);
            
            if(delegate != null) {
                delegate.fetchCompleted(userData);
            }
           
            NotificationCenter.sharedInstance().postNotification(ScriptFetcher.ScriptFetcherDidFetchScriptNotification, userData);
            
            System.out.println(data);
        } catch(IOException e) {
            if(delegate != null) {
                delegate.fetchFailed(e);
            }
        }

        return null;
    }
    
    public void setAiringId(String airingId) {
        this.airingId = airingId;
    }
    
    public String getAiringId() {
        return this.airingId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getClientId() {
        return this.clientId;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setDelegate(ScriptFetcherDelegate delegate) {
        this.delegate = delegate;
    }
    
    public ScriptFetcherDelegate getDelegate() {
        return this.delegate;
    }
    
    private void disableSSLChecking()
    {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
            }
            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(ScriptFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid;
        allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
}
