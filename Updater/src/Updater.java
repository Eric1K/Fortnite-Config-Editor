import java.io.InputStream;
import java.net.URL;

import javax.swing.JOptionPane;

public class Updater {
	
	private final static String versionURL = "https://secretlygod.github.io/updatehost/fnconfigeditor/version.html";
    private final static String historyURL = "https://secretlygod.github.io/updatehost/fnconfigeditor/history.html";
    
	public static String getLatestVersion() throws Exception
    {
		String data = getData(versionURL);
        return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));

    }
    public static String getWhatsNew() throws Exception
    {
    	String data = getData(historyURL);
        return data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }
    private static String getData(String address) throws Exception
    {
    	
    	URL url = new URL(address);
        
        InputStream html = null;

        html = url.openStream();
        
        int c = 0;
        StringBuffer buffer = new StringBuffer("");

        while(c != -1) {
            c = html.read();
            
        buffer.append((char)c);
        }
        return buffer.toString();
    }
    
    public static void main(String[] args) {
        try {
            if (Integer.parseInt(Updater.getLatestVersion()) > 0) {
                new UpdateInfo(Updater.getWhatsNew());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }


}
