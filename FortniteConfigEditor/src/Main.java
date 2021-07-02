import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
	//TODO ADD DESIRED SCREEN RESOLUTION AND FIX NO FILE CHOSEN
	public boolean readytogo = false;
	public boolean mouseac = true;
	public static boolean crash = false;
    JButton go = new JButton("Choose GameUserSettings");
    JButton confirm = new JButton("Confirm");
    static String sourceFolder="";
    String theFile="";
    JFileChooser chooser;
    String choosertitle;
    JTextField xRes;
    JTextField yRes;
    JTextField FPS;
    JTextField oldxRes;
    JTextField oldyRes;
    JTextField oldFPS;
    JLabel xlabel = new JLabel(); 
    JLabel ylabel = new JLabel();
    JLabel fpslabel = new JLabel();
    JLabel oldxlabel = new JLabel(); 
    JLabel oldylabel = new JLabel();
    JLabel oldfpslabel = new JLabel();
    JLabel madeby = new JLabel();
    JLabel website = new JLabel();
    JLabel version = new JLabel();
    JCheckBox mouseAC = new JCheckBox("Mouse Accerlation", true);
   
  public Main() {
	  
	 go.addActionListener(this);
     xRes = new JTextField("New X Res", 10);
     yRes = new JTextField("New Y Res", 10);
     FPS = new JTextField("New FPS", 10);
     oldxRes = new JTextField("Old X Res", 10);
     oldyRes = new JTextField("Old Y Res", 10);
     oldFPS = new JTextField("Old FPS", 10);
     
     add(madeby);
     madeby.setText("©2018 Made by Eric Feng");
     add(website);
     website.setText("Site: http://bit.ly/2PUUWjl");
     add(version);
     version.setText("Build: v1.01");
     add(go);
     add(xRes);
     add(yRes);
     add(FPS);
     add(oldxRes);
     add(oldyRes);
     add(oldFPS);
     add(xlabel);
     add(ylabel);
     add(fpslabel);
     add(oldxlabel);
     add(oldylabel);
     add(oldfpslabel);
     add(mouseAC);
     add(confirm);
     
     mouseAC.addActionListener(new ActionListener()
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 mouseac = !mouseac;
    	 }
    });
     confirm.addActionListener(new ActionListener()
     {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 readytogo = true;
    		 if(readytogo == true)
    		    {
    			 //Get Path as a File
    			 //String fileName = "GameUserSettings.ini";
    			 //String fps = fpsinput;
    			 File dir = new File (sourceFolder);
    			 //File settings = new File(dir,fileName);
    			 System.out.println(dir);
    			 System.out.println("Writing to File...");
    		     
    			 //write to disk

    		     replaceSelected("FrameRateLimit=", FPS.getText(), oldFPS.getText());
    		     replaceSelected("ResolutionSizeX=", xRes.getText(), oldxRes.getText());
    		     replaceSelected("LastUserConfirmedResolutionSizeX=", xRes.getText(), oldxRes.getText());
    		     replaceSelected("ResolutionSizeY=", yRes.getText(), oldyRes.getText());
    		     replaceSelected("LastUserConfirmedResolutionSizeY=", yRes.getText(), oldyRes.getText());
    		     replaceSelected("DesiredScreenWidth=", xRes.getText(), oldxRes.getText());
    		     replaceSelected("DesiredScreenHeight=", yRes.getText(), oldyRes.getText());
    		     if(mouseac == false)
    		     {
    		    	 replaceSelected("bDisableMouseAcceleration=", "True", "False");
    		     }
    		     if(mouseac == true)
    		     {
    		    	 replaceSelected("bDisableMouseAcceleration=", "False", "True");
    		     }

    		     System.out.println("DONE");
    		     if(crash == false)
    		     {
    		    	 JOptionPane.showMessageDialog(null, "Finished");
    		    	 System.exit(0);
    		     }else
    		     {
    		    	 JOptionPane.showMessageDialog(null, "An Error Occured"); 
    		     }
    		    }
    	 }
     });
     xRes.addActionListener(new ActionListener()
     {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 String xinput = xRes.getText();
    		 xlabel.setText("New X Resolution: " + xinput);
    		 //System.out.println(xinput);
    	 }
    });
    yRes.addActionListener(new ActionListener()
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 String yinput = yRes.getText();
    		 ylabel.setText("New Y Resolution: " + yinput);
    	 }
    });
    FPS.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		String fpsinput = FPS.getText();
    		fpslabel.setText("New FPS Cap: " + fpsinput);
    	}
    });
    
    
    
    
    oldxRes.addActionListener(new ActionListener()
    {
   	 public void actionPerformed(ActionEvent e)
   	 {
   		 String oldxinput = oldxRes.getText();
   		 oldxlabel.setText("Old X Resolution: " + oldxinput);
   		 //System.out.println(xinput);
   	 }
   });
   oldyRes.addActionListener(new ActionListener()
   {
   	 public void actionPerformed(ActionEvent e)
   	 {
   		 String oldyinput = oldyRes.getText();
   		 oldylabel.setText("Old Y Resolution: " + oldyinput);
   	 }
   });
   oldFPS.addActionListener(new ActionListener()
   {
   	public void actionPerformed(ActionEvent e)
   	{
   		String oldfpsinput = oldFPS.getText();
   		oldfpslabel.setText("Old FPS Cap: " + oldfpsinput);
   	}
   });
    
    
   }

  public void actionPerformed(ActionEvent e) {
 
     //System.out.println("Choosing...");
     chooser = new JFileChooser(); 
     chooser.setCurrentDirectory(new java.io.File("."));
     chooser.setDialogTitle(choosertitle);
     FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Config Files","ini");
     chooser.setFileFilter(filter);
     chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      
         String dirr = "" + chooser.getCurrentDirectory();
         File file = chooser.getSelectedFile();
       
      if(dirr.substring(dirr.length()-1,dirr.length()).equals(".")){
           dirr = dirr.substring(0,dirr.length()-1);
           sourceFolder=""+dirr + "" + file.getName();
        }else{
            
            sourceFolder=""+dirr + "/" + file.getName();
        }

          System.out.println("Folder path: " + dirr + " | File Name: " + file.getName());
          System.out.println(sourceFolder);
 			//ExamineImage.lum(sourceFolder);
    
      }else {
    	  JOptionPane.showMessageDialog(null, "No file chosen!"); 
    	  System.out.println("No Selection ");
      }
     }
   
  public Dimension getPreferredSize(){
          return new Dimension(300, 630);
    }
    
  public static void replaceSelected(String replaceWith, String type, String oldtype) {
	    try {
	        if(sourceFolder == "")
	    	{
	    		crash = true;
	    		JOptionPane.showMessageDialog(null, "Error: No File Chosen!"); 
		    	System.exit(-1);
	    	}
	    	boolean containsdigit = false;
	    	// input the file content to the StringBuffer "input"
	    	//mine:https://stackoverflow.com/questions/53464213/how-to-overwrite-certain-words-in-txt-file
	    	//https://stackoverflow.com/questions/20039980/java-replace-line-in-text-file
	    	if (type != null && !type.isEmpty()) {
	            for (char c : type.toCharArray()) {
	                if (containsdigit = Character.isDigit(c)) {
	                    System.out.println(containsdigit);
	                	break;
	                }
	            }
	        }

	    	BufferedReader file = new BufferedReader(new FileReader(sourceFolder));
	        String line;
	        StringBuffer inputBuffer = new StringBuffer();

	        while ((line = file.readLine()) != null) {
	            inputBuffer.append(line);
	            inputBuffer.append("\r\n");
	        }
	        String inputStr = inputBuffer.toString();

	        file.close();

	        System.out.println(inputStr); // check that it's inputted right

	        // this if structure determines whether or not to replace "0" or "1"
	        //if (Integer.parseInt(type) == 0) {
	        //    inputStr = inputStr.replace(replaceWith + "1", replaceWith + "0"); 
	        //}
	        //else if (Integer.parseInt(type) == 1) {
	        //    inputStr = inputStr.replace(replaceWith + "0", replaceWith + "1");
	        //} 
	       
	        inputStr = inputStr.replace(replaceWith + oldtype, replaceWith + type); 
	        //if (Integer.parseInt(oldtype) != Integer.parseInt(type)) {
	        //    System.out.println("SWAPPING..");
	        //	inputStr = inputStr.replace(replaceWith + oldtype, replaceWith + type + " "); 
	        //}
	        //else if (Integer.parseInt(type) == Integer.parseInt(oldtype)) {
	         //   System.out.println("The 2 inputs Matched");
	        //} 

	        // check if the new input is right
	        System.out.println("----------------------------------\n"  + inputStr);

	        // write the new String with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream(sourceFolder);
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();
	        System.out.println("Success with " + replaceWith);

	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "An error occured while reading file");
	    	crash = true;
	    	//System.exit(-1);
	    	System.out.println("Error Reading File");
	        //Popup error,ok exits the program
	        
	    }
	}

  public static void main(String s[]) {
	  try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
			System.out.println("Missing Libraries... Try redownloading?");
		} catch (InstantiationException e1) {
			
			e1.printStackTrace();
			System.out.println("Failed to instantiate");
		} catch (IllegalAccessException e1) {
			
			e1.printStackTrace();
			System.out.println("Cannot access UI");
		} catch (UnsupportedLookAndFeelException e1) {
			
			e1.printStackTrace();
			System.out.println("Unsupported UI");
		}
	  JFrame frame = new JFrame("Fortnite Config Editor v1.0");
    Main panel = new Main();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
    frame.getContentPane().add(panel,"Center");
    frame.setSize(panel.getPreferredSize());
    frame.setVisible(true);
     
    
    //frame.setResizable(false);
    }
}
