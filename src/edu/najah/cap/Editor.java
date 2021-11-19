package edu.najah.cap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Editor extends JFrame implements ActionListener, DocumentListener {

	public static  void main(String[] args) {
		new Editor();
	}
	public JEditorPane TP;//Text Panel
	private JMenuBar menu;//Menu
	private JMenuItem copy, paste, cut, move;
	public boolean changed = false;

	public Editor() {
		//Editor the name of our application
		super("Editor");
		TP = new JEditorPane();
		// center means middle of container.
		add(new JScrollPane(TP), "Center");
		TP.getDocument().addDocumentListener(this);

		menu = new JMenuBar();
		setJMenuBar(menu);
		BuildMenu();
		//The size of window
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void BuildMenu() {
		Files file = new Files();
		file.buildFileMenu();
		buildEditMenu();
	}

	private void createEdit(JMenu  edit){
		edit.setMnemonic('E');
		menu.add(edit);
	}

	private void addOperation(JMenuItem operation,JMenu  file,char keyboard,int key){
		operation.setMnemonic(keyboard);
		operation.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK));
		operation.addActionListener(this);
		file.add(operation);
	}

	private void cut(JMenu  edit){
		cut = new JMenuItem("Cut");
		addOperation(cut,edit,'T',KeyEvent.VK_X);
	}
	private void copy(JMenu  edit){
		copy = new JMenuItem("Copy");
		addOperation(copy,edit,'C',KeyEvent.VK_C);
	}
	private void paste(JMenu  edit){
		paste = new JMenuItem("Paste");
		addOperation(paste,edit,'P',KeyEvent.VK_V);
	}
	private void move(JMenu  edit){
		move = new JMenuItem("Move");
		addOperation(move,edit,'M',KeyEvent.VK_M);
	}
	private void find(JMenu  edit){
		JMenuItem find = new JMenuItem("Find");
		addOperation(find,edit,'F',KeyEvent.VK_F);
	}
	private void sall(JMenu  edit){
		JMenuItem sall = new JMenuItem("Select All");
		addOperation(sall,edit,'A',KeyEvent.VK_A);
	}

	private void buildEditMenu() {
		JMenu edit = new JMenu("Edit");
		createEdit(edit);
		// cut
		cut(edit);
		// copy
		copy(edit);
		// paste
		paste(edit);
		//move 
		move(edit);
		// find
		find(edit);
		// select all
		sall(edit);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Action action = new Action(e);
		action.actions();
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