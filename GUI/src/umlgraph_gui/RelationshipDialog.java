package umlgraph_gui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.FlowLayout;

public class RelationshipDialog extends JPanel implements ActionListener {
	
	JFrame frame;
	JPanel mainpanel;
	String reltext;

	public RelationshipDialog(JFrame frame) {
		super();
		setLayout(new BorderLayout(10, 10));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(20, 10, 10, 10));
		panel.setLayout(new BorderLayout(10, 10));
		
		JLabel lblChooseTheSource = new JLabel("Choose the source class's relationship:");
		lblChooseTheSource.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblChooseTheSource, BorderLayout.NORTH);
		
		String[] relName = { "Implementation", "Generalization", "Association", "Navigable association", 
				"Aggregation", "Navigable aggregation", "Composition", "Navigable composition", "Dependency" };

        //Create the combo box, select the item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox relList = new JComboBox(relName);
        relList.setSelectedIndex(8);
        relList.addActionListener(this);
        
        panel.add(relList, BorderLayout.WEST);
        
        JPanel panelAd = new JPanel();
        panelAd.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.add(panelAd, BorderLayout.CENTER);
        panelAd.setLayout(new BorderLayout(5, 5));
        
        String[] sourceAdornments = { "1", "2", "3", "4", "5", "*" };
        
        JComboBox sourceAdList = new JComboBox(sourceAdornments);
        sourceAdList.setEditable(true);
        sourceAdList.setSelectedIndex(5);
        sourceAdList.addActionListener(this);
        
        panelAd.add(sourceAdList, BorderLayout.WEST);
        
        String[] targetAdornments = { "1", "2", "3", "4", "5", "*" };
        
        JComboBox comboBox = new JComboBox(targetAdornments);
        comboBox.setEditable(true);
        panelAd.add(comboBox, BorderLayout.CENTER);
        
        textField = new JTextField();
        textField.setToolTipText("Target class's name");
        panelAd.add(textField, BorderLayout.EAST);
        textField.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnOk = new JButton("OK");
        panel_1.add(btnOk);
	}
	
	public void actionPerformed(ActionEvent e) {
    	// Menu item actions
        	String command = e.getActionCommand();
        	
	}

	// May delete the next line
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Relationships");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        RelationshipDialog newContentPane = new RelationshipDialog(frame);
        newContentPane.setVisible(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
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