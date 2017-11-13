
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainWindow extends JFrame {

	ArrayList<String> files;
	JList<String> list;
	JScrollPane listScroller, backupScroller;
	JPanel mainPanel, backupPanel;
	String path = "/Users/adrian/Documents/BOT";
	DefaultListModel<String> listModel;
	File file;

	public MainWindow() {
		super("Archiwizator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);

		mainPanel = new JPanel();
		backupPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		backupPanel.setLayout(new BoxLayout(backupPanel, BoxLayout.PAGE_AXIS));

		JLabel title1 = new JLabel("No takie pliki masz:");
		JLabel title2 = new JLabel("Zarchiwizowane:");

		mainPanel.add(title1);
		backupPanel.add(title2);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		backupPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		backupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		file = new File(path);
		listModel = new DefaultListModel();
		for (String val : file.list())
			listModel.addElement(val);
		list = new JList<String>(listModel);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScroller = new JScrollPane(list);
		listScroller.setAlignmentX(LEFT_ALIGNMENT);
		backupScroller = new JScrollPane(new JList<String>(new String[] { "Test" }));

		mainPanel.add(listScroller);
		backupPanel.add(backupScroller);

		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
		buttons.add(Box.createGlue());

		JButton goUp = new JButton("Go up");
		goUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newPath = file.getParent();
				if (newPath != null) {
					file = new File(newPath);
					loadList(newPath);
					System.out.println(file.getPath());
				}
			}
		});
		// Double click on list item
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				// JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					File newFile = new File(file.getPath() + "/" + list.getSelectedValue());
					System.out.println(newFile.getPath());

					if (newFile.isDirectory()) {
						file = newFile;
						loadList(file.getPath());
					} else {
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.open(newFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		});

		buttons.add(goUp);
		buttons.add(new JButton("Archiwizuj ogólnie xD"));
		buttons.add(new JButton("Archiwizuj teraz"));

		Container contentPane = getContentPane();
		JPanel panels = new JPanel();
		panels.setLayout(new GridLayout(0, 2));
		panels.add(mainPanel);
		panels.add(backupPanel);
		contentPane.add(panels, BorderLayout.CENTER);
		contentPane.add(buttons, BorderLayout.PAGE_END);

		setVisible(true);
	}

	private void loadList(String path) {
		listModel.clear();
		File newFile = new File(path);
		for (String val : newFile.list())
			listModel.addElement(val);
	}

	private void goUp() {
		// https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe

	}

}
