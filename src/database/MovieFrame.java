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
public class MovieFrame extends JFrame {
    
    /*
     * Ebben az objektumban hozzuk l�tre a film felv�tel�hez, 
     * szerkezt�s�hez �s t�rl�s�hez sz�ks�ges ablakot 
     */
    private MovieData data;//MovieData p�ld�nyos�t�sa
    
    //Film felv�tel�hez
    public String settitle;
    public String setdirector;
    public String setstars;
    public String setstyle;
    public int setyear;
    public int setrating;
    
    //tesztel�s miatt
    public JTextField cim_mezo;
    
    /*
     * Itt hozzuk l�tre �s adjuk hozz� az ablakunkhoz a k�l�nb�z� komponenseket
     */
    public void initComponents() {
        this.setLayout(new BorderLayout());
        
        //l�trehozunk egy JPanelt
        JPanel jp2=new JPanel();
        //hozz�adjuk
        this.add(BorderLayout.SOUTH,jp2);
        
        jp2.setBackground(new Color(140,245,251));
        //l�trehozunk egy JTable-t �s a MovieData p�ld�ny�t jelen�ti majd meg
        final JTable table = new JTable(data);
        //rendezhet� legyen
        table.setAutoCreateRowSorter(true);
        
        table.setBackground(new Color(255,254,198));
        
        table.setFillsViewportHeight(true);
        
        //l�trehozunk egy scrollPane-t �s hozz�adjuk
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(BorderLayout.CENTER,scrollPane);
        
        //film felv�tel�hez sz�ks�ges mez�k l�trehoz�sa �s hozz�ad�sa a JPanel-hez
        JLabel cim=new JLabel("C�m:");
        jp2.add(cim);
        cim_mezo=new JTextField(15);
        jp2.add(cim_mezo);
        
        JLabel rend=new JLabel("Rendez�:");
        jp2.add(rend);
        final JTextField rend_mezo=new JTextField(10);
        jp2.add(rend_mezo);
        
        JLabel star=new JLabel("Szerepl�k:");
        jp2.add(star);
        final JTextField star_mezo=new JTextField(15);
        jp2.add(star_mezo);
        
        JLabel sty=new JLabel("M�faj:");
        jp2.add(sty);
        final JTextField style_mezo=new JTextField(10);
        jp2.add(style_mezo);
        
        JLabel ev=new JLabel("�v:");
        jp2.add(ev);
        final JTextField ev_mezo=new JTextField(4);
        jp2.add(ev_mezo);
        
        JLabel rate=new JLabel("�rt�kel�s:");
        jp2.add(rate);
        final JTextField rating_mezo=new JTextField(3);
        jp2.add(rating_mezo);
        
        //film felv�tel�re �s t�rl�s�re szolg�l� gomb l�trehoz�sa
        JButton felv;
        felv=new JButton("Felvesz");
        jp2.add(felv);
        
        JButton del;
        del=new JButton("T�r�l");
        jp2.add(del);
        
        //film felv�tele     
        felv.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		//JTextField-ekb�l lek�rdezz�k a sz�veget
        		settitle=cim_mezo.getText();
        		setdirector=rend_mezo.getText();
        		setstars=star_mezo.getText();
        		setstyle=style_mezo.getText();
        		
        		//Ha nem adunk meg �vet �s �rt�kel�st akkor is felvegye a filmet
        		
        		String sev=ev_mezo.getText();
        		if (sev.equals("")) sev="1900";
        		
        	    String sertek=rating_mezo.getText();
        	    if (sertek.equals("")) sertek="0";
        	    
        		setyear=Integer.parseInt(sev);
        		setrating=Integer.parseInt(sertek);
        		
        		//film felv�tele
        		data.addMovie(settitle, setdirector,setstars, setstyle, setyear, setrating);
        	}
        });
        
        //t�rl�s
        del.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		revalidate();
        		repaint();
        		data.deleteMovie(data.getMovie(table.getSelectedRow()));
        	}
        });
        
    }

    /*
     * Az ablak konstruktora.
     * 
     */
    @SuppressWarnings("unchecked")
    public MovieFrame() {
        super("Film adatb�zis szerkeszt�se");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Indul�skor bet�ltj�k az adatokat
        try {
            data = new MovieData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Movies.dat"));
            data.Movies = (List<Movie>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bez�r�skor mentj�k az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Movies.dat"));
                    oos.writeObject(data.Movies);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Fel�p�tj�k az ablakot
        setMinimumSize(new Dimension(1300, 500));
        initComponents();
    }
}
