package umlgraph_gui;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;

public class ClassOptDialog extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel label;
    ImageIcon icon = createImageIcon("Person.png");
    JFrame frame;
    String dialogClassMod = "Class Modelling";
    String text = null;
	
    public ClassOptDialog(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        
        JPanel classPanel = createDialogClassMod();
        label = new JLabel("Click the \"Add\" button"
                + " to specify the options.",
                JLabel.CENTER);
        
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        classPanel.setBorder(padding);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Class Modelling", null,
        		classPanel, dialogClassMod);
        add(tabbedPane, BorderLayout.CENTER);
        
        add(label, BorderLayout.PAGE_END);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}
    
    /** Sets the text displayed at the bottom of the frame. */
    void setLabel(String newText) {
        label.setText(newText);
    }
    /** Creates the panel shown by the first tab. */
    private JPanel createDialogClassMod() {
        final int numButtons = 12;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String classesCommand = "classes";
        final String activeclassesCommand = " activeclasses";
        final String componentsCommand = "components";
        final String yncCommand = "ync";

        radioButtons[0] = new JRadioButton("Classes");
        radioButtons[0].setActionCommand(classesCommand);

        radioButtons[1] = new JRadioButton("Active classes");
        radioButtons[1].setActionCommand(activeclassesCommand);

        radioButtons[2] = new JRadioButton("Components");
        radioButtons[2].setActionCommand(componentsCommand);

        radioButtons[3] = new JRadioButton("Nodes");
        radioButtons[3].setActionCommand(yncCommand);
        
        radioButtons[4] = new JRadioButton("Use cases");
        radioButtons[4].setActionCommand(yncCommand);
        
        radioButtons[5] = new JRadioButton("Collaborations");
        radioButtons[5].setActionCommand(yncCommand);
        
        radioButtons[6] = new JRadioButton("Packages");
        radioButtons[6].setActionCommand(yncCommand);
        
        radioButtons[7] = new JRadioButton("Operations");
        radioButtons[7].setActionCommand(yncCommand);
        
        radioButtons[8] = new JRadioButton("Attributes");
        radioButtons[8].setActionCommand(yncCommand);
        
        radioButtons[9] = new JRadioButton("Stereotypes");
        radioButtons[9].setActionCommand(yncCommand);
        
        radioButtons[10] = new JRadioButton("Tagged values");
        radioButtons[10].setActionCommand(yncCommand);
        
        radioButtons[11] = new JRadioButton("Notes");
        radioButtons[11].setActionCommand(yncCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Add");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //classes dialog
                if (command == classesCommand) {
                    text = "/**\n  * @opt ";

                //active class dialog
                }
                return;
            }
        });
        return createPane(dialogClassMod + ":",
                      radioButtons,
                      showItButton);
    }
    
    /**
     * Used by createDialogClassMod to create a pane 
     * containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton showButton) {

        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ClassOptDialog.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ClassOptDialog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        ClassOptDialog newContentPane = new ClassOptDialog(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public String getText() {
    	return text;
    }
    public void setText(String text) {
        this.text = text;
      }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
