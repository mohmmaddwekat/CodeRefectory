package edu.najah.cap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Files extends JFrame implements ActionListener, DocumentListener {


	public JEditorPane TP;//Text Panel
	private JMenuBar menu;//Menu
	public boolean changed = false;
	private File file;


	File getFile(){
		return file;
	}
	void setFile(File file){
		 this.file=file;
	}

	private void createFile(JMenu  file){
		file.setMnemonic('F');
		menu.add(file);
	}

	private void addOperation(JMenuItem operation,JMenu  file,char keyboard,int key){
		operation.setMnemonic(keyboard);
		operation.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK));
		operation.addActionListener(this);
		file.add(operation);
	}

	private void newFile(JMenu  file){
		JMenuItem n = new JMenuItem("New");
		addOperation(n,file,'N',KeyEvent.VK_N);
	}
	private void openFile(JMenu  file){
		JMenuItem open = new JMenuItem("Open");
		addOperation(open,file,'O',KeyEvent.VK_O);
	}
	private void saveFile(JMenu  file){
		JMenuItem save = new JMenuItem("Save");
		addOperation(save,file,'S',KeyEvent.VK_S);
	}
	private void saveasFile(JMenu  file){
		JMenuItem saveas = new JMenuItem("Save as...");
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		saveas.addActionListener(this);
		saveas.setMnemonic('S');
		file.add(saveas);
	}
	private void quitFile(JMenu  file){
		JMenuItem quit = new JMenuItem("Quit");
		addOperation(quit,file,'Q',KeyEvent.VK_Q);
	}

	public void buildFileMenu() {
		JMenu file = new JMenu("File");
		createFile(file);
		newFile(file);
		openFile(file);
		saveFile(file);
		saveasFile(file);
		quitFile(file);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Action action = new Action(e);
		action.actions();
	}

	public void loadFile() {
		JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
		dialog.setMultiSelectionEnabled(false);
		try {
			int result = dialog.showOpenDialog(this);
			if (result == 1)//1 value if cancel is chosen.
				return;
			if (result == 0&&changed) {
					//Save file
					if (changed) {
						int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file",
								0, 2);//0 means yes and no question and 2 mean warning dialog
						if (ans == 1)// no option 
							return;
					}
					if (file == null) {
						SaveFiles saveFiles = new SaveFiles();
						saveFiles.saveAs("Save");
						return;
					}
					String text = TP.getText();
					System.out.println(text);
					try (PrintWriter writer = new PrintWriter(file);){
						if (!file.canWrite())
							throw new Exception("Cannot write file!");
						writer.write(text);
						changed = false;
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			if (result == 0) {// value if approve (yes, ok) is chosen.
				file = dialog.getSelectedFile();
				//Read file 
				StringBuilder rs = new StringBuilder();
				try (	FileReader fr = new FileReader(file);		
						BufferedReader reader = new BufferedReader(fr);) {
					String line;
					while ((line = reader.readLine()) != null) {
						rs.append(line + "\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", 0);//0 means show Error Dialog
				}
				
				TP.setText(rs.toString());
				changed = false;
				setTitle("Editor - " + file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			//0 means show Error Dialog
			JOptionPane.showMessageDialog(null, e, "Error", 0);
		}
	}

	
	
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		changed = true;
	}

}