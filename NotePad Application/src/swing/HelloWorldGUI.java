package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class HelloWorldGUI extends JFrame implements ActionListener{

    JTextArea ta = new JTextArea();

    public HelloWorldGUI() {
        setTitle("My Hello World Frame");
        //setSize(300, 100);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMI = new JMenuItem("New");
        newMI.addActionListener(this);
        JMenuItem openMI = new JMenuItem("Open");
        openMI.addActionListener(this);
        JMenuItem saveMI = new JMenuItem("Save");
        saveMI.addActionListener(this);
        fileMenu.add(newMI);
        fileMenu.add(openMI);
        fileMenu.add(saveMI);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMI = new JMenuItem("Cut");
        cutMI.addActionListener(this);
        JMenuItem copyMI = new JMenuItem("Copy");
        copyMI.addActionListener(this);
        JMenuItem pasteMI = new JMenuItem("Paste");
        pasteMI.addActionListener(this);
        JMenuItem selectAllMI = new JMenuItem("Select All");
        selectAllMI.addActionListener(this);
        editMenu.add(cutMI);
        editMenu.add(copyMI);
        editMenu.add(pasteMI);
        editMenu.add(selectAllMI);

        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutNotepadMI = new JMenuItem("About Notepad");
        aboutNotepadMI.addActionListener(this);
        aboutMenu.add(aboutNotepadMI);


        JMenu viewMenu = new JMenu("View");
        JMenuItem zoomInMI = new JMenuItem("Zoom In");
        zoomInMI.addActionListener(this);
        JMenuItem zoomOutMI = new JMenuItem("Zoom Out");
        zoomOutMI.addActionListener(this);
        viewMenu.add(zoomInMI);
        viewMenu.add(zoomOutMI);

        JMenuBar mb = new JMenuBar();
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(viewMenu);
        mb.add(aboutMenu);

        add(mb, BorderLayout.NORTH);

        ta.setBackground(Color.WHITE);
        add(ta);

    }

    public static void main(String[] args) {
        HelloWorldGUI gui = new HelloWorldGUI();
        gui.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("New")) {
            ta.setText("");
        }else if(cmd.equals("Open")) {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(this);
            File f = fc.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                while(br.ready()) {
                    ta.append(br.readLine()+"\n");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(cmd.equals("Save")) {
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(this);
            File f = fc.getSelectedFile();
            try {
                FileWriter fwrite = new FileWriter(new File(f.getAbsolutePath()));
                fwrite.write(ta.getText());
                fwrite.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if(cmd.equals("Cut")) {
            ta.cut();
        }else if(cmd.equals("Copy")) {
            ta.copy();
        }else if(cmd.equals("Paste")){
            ta.paste();
        }else if(cmd.equals("Select All")){
            ta.selectAll();
        }else if(cmd.equals("Zoom In")) {
            ta.setFont(ta.getFont().deriveFont(ta.getFont().getSize()+5f));
        }else if(cmd.equals("Zoom Out")) {
            ta.setFont(ta.getFont().deriveFont(ta.getFont().getSize()-5f));
        }else if(cmd.equals("About Notepad")){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,"This is a simple notepad application","Notepad",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

}