package edu.najah.cap;

import java.awt.event.ActionEvent;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class Action extends JFrame {


	public JEditorPane TP;//Text Panel
	public boolean changed = false;
    ActionEvent e;
    
    Action(ActionEvent e){
        this.e = e;
    }





	private void write(PrintWriter writer,String text,Files file) throws Exception{
		if (!file.getFile().canWrite())					
			throw new Exception("Cannot write file!");
		writer.write(text);
		changed = false;
	}
	
    private void checkSave(int ans ,SaveFiles saveFiles,Files file){
        if (ans != 1&&file.getFile() == null) {
            saveFiles.saveAs("Save");
        }else if(ans != 1&&file.getFile()!=null){
            String text = TP.getText();
            System.out.println(text);
            try (PrintWriter writer = new PrintWriter(file.getFile());){
                write(writer,text,file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    } 
    private void checkNew(Files file){
			file.setFile(null);
			TP.setText("");
			changed = false;
			setTitle("Editor");
    } 

    public void actions() {
        int ans = 0;
		SaveFiles saveFiles = new SaveFiles();
		String action = e.getActionCommand();
        Files file = new Files();
		if (action.equals("Quit")) {
			System.exit(0);
		} else if (action.equals("Open")) {
			file.loadFile();
		}else if(action.equals("Save")&&changed){
            //Save file
            // 0 means yes and no option, 2 Used for warning messages.  
            ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
            checkSave(ans,saveFiles,file);

		} else if(action.equals("New")&&changed&&ans==1){
            ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
            return;

        } else if(action.equals("New")&&changed&&file.getFile()==null){
            ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
            saveFiles.saveAs("Save");
            return;

        } else if(action.equals("New")&&changed){
            //New file
            // 0 means yes and no option, 2 Used for warning messages.  
            ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
            //1 value from class method if NO is chosen.
            String text = TP.getText();
            System.out.println(text);
            try (PrintWriter writer = new PrintWriter(file.getFile());){
                write(writer,text,file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            checkNew(file);
        } else if (action.equals("Save as...")) {
			saveFiles.saveAs("Save as...");
		} else if (action.equals("Select All")) {
			TP.selectAll();
		} else if (action.equals("Copy")) {
			TP.copy();
		} else if (action.equals("Cut")) {
			TP.cut();
		} else if (action.equals("Paste")) {
			TP.paste();
		} else if (action.equals("Find")) {
            FindDialog find = new FindDialog(file, true);
			find.showDialog();
		}
	}




}