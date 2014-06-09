package umlgraph_gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class UMLGraphGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    private JPanel mainpanel;
    // JTabbedPane tabButtons;
    EmptyBorder border5 = new EmptyBorder(1, 1, 5, 1);
    JTextArea textArea;
    String docName = "Document 1";
    JLabel umlimage = new JLabel(new ImageIcon("W:\\GUI\\FlowLayoutDemo\\src\\mary\\Person.png"));
    BufferedReader br;
    String currentFileBeingEdited = null;
    ClassOptDialog classDialog;
    RelationshipDialog reldialog;
    JFrame panelAC;
    private String text1 = "";
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UMLGraphGUI frame = new UMLGraphGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UMLGraphGUI() {
        super("UMLGraph GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(120, 110, 1000, 720);
        setResizable(false);
        mainpanel = new JPanel();
        mainpanel.setBorder(new EmptyBorder(40, 20, 20, 20));
        mainpanel.setLayout(new BorderLayout(5, 5));
        setContentPane(mainpanel);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Add "File" menu item
        JMenu filemenu = new JMenu("File");
        filemenu.add(makeMenuItem("Open file..."));
        filemenu.addSeparator();
        filemenu.add(makeMenuItem("Save"));
        filemenu.addSeparator();
        filemenu.add(makeMenuItem("Exit"));
        
        // Add "Edit" menu item
        JMenu editmenu = new JMenu("Edit");
        editmenu.add(makeMenuItem1("Cut"));
        editmenu.add(makeMenuItem1("Copy"));
        editmenu.add(makeMenuItem1("Paste"));
        editmenu.add(makeMenuItem1("Select all"));
        
        // Add "options" menu item
        JMenu optionsmenu = new JMenu("Options");
        optionsmenu.setToolTipText("For Options, move the cursor over the element you want to model");
        optionsmenu.add(makeMenuItem4("Class Diagram Options"));
        optionsmenu.add(makeMenuItem4("Relationships"));
        
        
        // Add "help" menu item
        JMenu helpmenu = new JMenu("Help");
        helpmenu.add(makeMenuItem3("About UMLGraph"));
 
        // Add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(filemenu);
        menuBar.add(editmenu);
        menuBar.add(optionsmenu);
        menuBar.add(helpmenu);
        setJMenuBar(menuBar);
        
        /**Panel panelbuttons = new Panel();
        FlowLayout fl_panelbuttons = (FlowLayout) panelbuttons.getLayout();
        fl_panelbuttons.setAlignment(FlowLayout.LEFT);
        fl_panelbuttons.setVgap(10);
        fl_panelbuttons.setHgap(10);
        mainpanel.add(panelbuttons, BorderLayout.NORTH);
        */
        
        JPanel paneltext = new JPanel();
        textArea = new JTextArea();
        textArea.setToolTipText("Your code goes here...");
        JScrollPane paneJ = new JScrollPane(textArea);
        paneltext.add(paneJ);
        textArea.setTabSize(11);
        textArea.setRows(34);
        textArea.setColumns(40);
        
        JLabel lblDocument = new JLabel(docName);
        paneJ.setColumnHeaderView(lblDocument);
        mainpanel.add(paneltext, BorderLayout.WEST);
 
        JPanel panelimage = new JPanel();
        panelimage.setBorder(new EmptyBorder(5, 40, 8, 10));
        mainpanel.add(panelimage, BorderLayout.CENTER);
        panelimage.setLayout(new BorderLayout(20, 20));
        
        JPanel panelimage1 = new JPanel();
        panelimage1.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
        JScrollPane paneImage = new JScrollPane(panelimage1);
        panelimage.add(paneImage);
        
        panelimage1.add(umlimage);
        
        JPanel convertpanel = new JPanel();
        mainpanel.add(convertpanel, BorderLayout.SOUTH);
        convertpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnConvertToUml = new JButton("Convert to UML Diagram");
        convertpanel.add(btnConvertToUml);
        
        setVisible(true);
        
    }
    
    private JMenuItem makeMenuItem(String name) {
        JMenuItem m = new JMenuItem(name);
        m.addActionListener(this);
        return m;
    }
    
    private JMenuItem makeMenuItem1(String name) {
        JMenuItem m1 = new JMenuItem(name);
        m1.addActionListener(this);
        return m1;
    }
    
    private JMenuItem makeMenuItem3(String name) {
        JMenuItem m3 = new JMenuItem(name);
        m3.addActionListener(this);
        return m3;
    }
    
    private JMenuItem makeMenuItem4(String name) {
        JMenuItem m4 = new JMenuItem(name);
        m4.addActionListener(this);
        return m4;
    }
    
    public void actionPerformed(ActionEvent e) {
    	// Menu item actions
    	String command = e.getActionCommand();
    	String line = null;
    	String textmodel = null;
    	// "File" menu item actions
    	if (command.equals("Exit")) {
    		System.exit(0);
    	} else if (command.equals("Open file...")) {
    		// Open menu item action
    		final JFileChooser fc = new JFileChooser();
    		fc.addActionListener(this);
    		int returnVal = fc.showOpenDialog(UMLGraphGUI.this);
    		if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                docName = file.getName();
                //This is where the application would open the file.
                currentFileBeingEdited = file.getAbsolutePath();
                try {
                    br = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    line = br.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                while (line != null) {
                	textArea.append(line + "\n");
                    try {
                        line = br.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
            	textArea.append("\n");
            }
    	} else if (command.equals("Save")) {
    		 // Save menu item action
    		 String textsave = textArea.getText();
    		 JFileChooser chooser = new JFileChooser();
    	     chooser.setCurrentDirectory( new File( "./") );
    	     int actionDialog = chooser.showSaveDialog(this);
    	     if (actionDialog == JFileChooser.APPROVE_OPTION) {
    	    	 File fileName = new File(chooser.getSelectedFile( ) + "" );
    	    	 if(fileName.exists()) {
    	    		 actionDialog = JOptionPane.showConfirmDialog(this, "Replace existing file?");
    	    		 if (actionDialog == JOptionPane.NO_OPTION) {
    	    			 textArea.append("\n");
    	    		 }
    	    	 }
    	    	 try {
    	    		 BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
    	    		 out.write(textsave);
    	    		 out.close();
    	    		 docName = fileName.getName();
    	    	 } catch(Exception ee) {
    	    		 System.err.println("Error: " + ee.getMessage());
    	    	 }
    	     }
    	}
    	 	
    	// "Edit" menu item actions
    	if (command.equals("Cut")) {
    		text1 = textArea.getSelectedText(); // Copy the text to the text1
            textArea.replaceRange("",       // and delete 
            textArea.getSelectionStart(),  // from the start of the selection
            textArea.getSelectionEnd());  // to the end
            //addRecentCut(text1);
    	} else if (command.equals("Copy")) {
    		text1 = textArea.getSelectedText();
    	} else if (command.equals("Paste")) {
    		textArea.insert(text1, textArea.getCaretPosition());
    	} else if (command.equals("Select all")) {
    		textArea.selectAll();
    	} else if (command.equals("About UMLGraph")) {
    		JOptionPane.showMessageDialog(UMLGraphGUI.this,
    			    "<HTML>Visit  <FONT color=\"#000099\"><U>http://www.umlgraph.org/</U></FONT></HTML>",
    			    "About UMLGraph",
    			    JOptionPane.PLAIN_MESSAGE);
    	}
    	
    	// "Options" menu item actions
    	else {
    		if (command.equals("Class Diagram Options")) {
    			JFrame framec = new JFrame("Class Diagram Options");
    	        framec.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	        //Create and set up the content pane.
    	        classDialog = new ClassOptDialog(framec);
    	        //dialogDemo.setOpaque(true); //content panes must be opaque
    	        framec.setContentPane(classDialog);
    	        framec.setBounds(320, 250, 1000, 720);

    	        //Display the window.
    	        framec.pack();
    	        framec.setVisible(true);
    	        
    	        //textmodel = classDialog.getText();
    	        textArea.insert(textmodel, textArea.getCaretPosition());
    	        //	textmodel = "/**\n  * @opt\n  */\n";
    		} else if (command.equals("Relationships")) {
    			JFrame frameR = new JFrame("Relationships");
    			frameR.setBounds(320, 250, 200, 300);
    			frameR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    			reldialog = new RelationshipDialog(frameR);
    			frameR.setContentPane(reldialog);
    			
    			frameR.pack();
    	        frameR.setVisible(true);
    			
    		}
    		
    		textArea.insert(textmodel, textArea.getCaretPosition());
    		
    	} 
    	/**else {
    		textArea.append("\n");
    	}
    	*/
    }
}
