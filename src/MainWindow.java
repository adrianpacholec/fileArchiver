
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainWindow extends JFrame {

	ArrayList<String> files;
	JList<?> list;
	JScrollPane listScroller, backupScroller;
	String path = "C:\\Users\\Adrian\\Desktop";

	public MainWindow() {
		super("Archiwizator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		
		JPanel mainPanel = new JPanel();
		JPanel backupPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		backupPanel.setLayout(new BoxLayout(backupPanel, BoxLayout.PAGE_AXIS));
		
		JLabel title1 = new JLabel("No takie pliki masz:");
		JLabel title2 = new JLabel("Zarchiwizowane:");
		
		mainPanel.add(title1);
		backupPanel.add(title2);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		backupPanel.add(Box.createRigidArea(new Dimension(0,5)));
		backupPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		list = new JList(getFile(new File(path)).toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScroller = new JScrollPane(list);
		listScroller.setAlignmentX(LEFT_ALIGNMENT);
		backupScroller = new JScrollPane(new JList(new String[]{"Test"}));

		mainPanel.add(listScroller);
		backupPanel.add(backupScroller);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		buttons.add(Box.createGlue());		
		
		buttons.add(new JButton("Go up"));
		buttons.add(new JButton("Archiwizuj ogólnie xD"));
		buttons.add(new JButton("Archiwizuj teraz"));
		
		Container contentPane = getContentPane();
		JPanel panels = new JPanel();
		panels.setLayout(new GridLayout(0,2));
		panels.add(mainPanel);
		panels.add(backupPanel);
		contentPane.add(panels,BorderLayout.CENTER);
		contentPane.add(buttons,BorderLayout.PAGE_END);

		setVisible(true);
	}
	
	
	public ArrayList<String> getFile(final File folder) {
		ArrayList<String> lista = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        /*if (fileEntry.isDirectory()) {
	            getFile(fileEntry);
	        } else {
	            lista.add(fileEntry.getName());
	        }*/
	        lista.add(fileEntry.getName());
	    }
		return lista;
	}
	
	private void goUp(){
		//https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe
		
	}
	
	
}
