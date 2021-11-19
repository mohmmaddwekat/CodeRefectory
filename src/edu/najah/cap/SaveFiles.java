package edu.najah.cap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class SaveFiles extends JFrame  {


	public JEditorPane TP;//Text Panel
	public boolean changed = false;
	private File file;

	public File fileChooser(String dialogTitle){
		JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
		dialog.setDialogTitle(dialogTitle);
		int result = dialog.showSaveDialog(this);
		if (result != 0)//0 value if approve (yes, ok) is chosen.
			return null;
		return file = dialog.getSelectedFile();

	}
	public void writer(String dialogTitle,String type){
		file = fileChooser(dialogTitle);
		try (PrintWriter writer = new PrintWriter(file);){
			writer.write(TP.getText());
			changed = false;
			setTitle(type+" - " + file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void saveAs(String dialogTitle) {
		writer(dialogTitle,"Editor");
	}
	public void saveAsText(String dialogTitle) {
		writer(dialogTitle,"Save as Text Editor");
	}

}
