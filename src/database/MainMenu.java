package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
 * A megjelen�tend� ablakunk oszt�lya.
 */
public class MainMenu extends JFrame {
    
    
    /*
     * F�men�
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JPanel jp=new JPanel();
        this.add(BorderLayout.CENTER,jp);
        
        jp.setBackground(new Color(140,245,251));
        //3 nyomogomb l�trehoz�sa
        JButton browse=new JButton("B�ng�sz�s");
        jp.add(browse);
        
        JButton modify=new JButton("Filmadatb�zis m�dos�t�sa");
        jp.add(modify);
        
        JButton exit=new JButton("Kil�p�s");
        jp.add(exit);
        
        //keres� ablak l�trehoz�sa
        browse.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		BrowseFrame bf = new BrowseFrame();
                bf.setVisible(true);
        	}
        });
        
        //m�dos�t� ablak l�trehoz�sa
        modify.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		MovieFrame sf = new MovieFrame();
                sf.setVisible(true);
        	}
        });
        
        //kil�p�s
        exit.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		System.exit(0);
        	}
        });
    }

    /*
     * Az ablak konstruktora.
     * 
     */
    @SuppressWarnings("unchecked")
    public MainMenu() {
        super("Film adatb�zis");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        // Fel�p�tj�k az ablakot
        setMinimumSize(new Dimension(250, 150));
        initComponents();
    }

    public static void main(String[] args) {
        // Megjelen�tj�k az ablakot
    	MainMenu mm = new MainMenu();
    	mm.setVisible(true);
    }
}
