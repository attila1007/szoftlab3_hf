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
 * A megjelenítendõ ablakunk osztálya.
 */
public class MovieFrame extends JFrame {
    
    /*
     * Ebben az objektumban hozzuk létre a film felvételéhez, 
     * szerkeztéséhez és törléséhez szükséges ablakot 
     */
    private MovieData data;//MovieData példányosítása
    
    //Film felvételéhez
    public String settitle;
    public String setdirector;
    public String setstars;
    public String setstyle;
    public int setyear;
    public int setrating;
    
    //tesztelés miatt
    public JTextField cim_mezo;
    
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     */
    public void initComponents() {
        this.setLayout(new BorderLayout());
        
        //létrehozunk egy JPanelt
        JPanel jp2=new JPanel();
        //hozzáadjuk
        this.add(BorderLayout.SOUTH,jp2);
        
        jp2.setBackground(new Color(140,245,251));
        //létrehozunk egy JTable-t és a MovieData példányát jeleníti majd meg
        final JTable table = new JTable(data);
        //rendezhetõ legyen
        table.setAutoCreateRowSorter(true);
        
        table.setBackground(new Color(255,254,198));
        
        table.setFillsViewportHeight(true);
        
        //létrehozunk egy scrollPane-t és hozzáadjuk
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(BorderLayout.CENTER,scrollPane);
        
        //film felvételéhez szükséges mezõk létrehozása és hozzáadása a JPanel-hez
        JLabel cim=new JLabel("Cím:");
        jp2.add(cim);
        cim_mezo=new JTextField(15);
        jp2.add(cim_mezo);
        
        JLabel rend=new JLabel("Rendezõ:");
        jp2.add(rend);
        final JTextField rend_mezo=new JTextField(10);
        jp2.add(rend_mezo);
        
        JLabel star=new JLabel("Szereplõk:");
        jp2.add(star);
        final JTextField star_mezo=new JTextField(15);
        jp2.add(star_mezo);
        
        JLabel sty=new JLabel("Mûfaj:");
        jp2.add(sty);
        final JTextField style_mezo=new JTextField(10);
        jp2.add(style_mezo);
        
        JLabel ev=new JLabel("Év:");
        jp2.add(ev);
        final JTextField ev_mezo=new JTextField(4);
        jp2.add(ev_mezo);
        
        JLabel rate=new JLabel("Értékelés:");
        jp2.add(rate);
        final JTextField rating_mezo=new JTextField(3);
        jp2.add(rating_mezo);
        
        //film felvételére és törlésére szolgáló gomb létrehozása
        JButton felv;
        felv=new JButton("Felvesz");
        jp2.add(felv);
        
        JButton del;
        del=new JButton("Töröl");
        jp2.add(del);
        
        //film felvétele     
        felv.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		//JTextField-ekbõl lekérdezzük a szöveget
        		settitle=cim_mezo.getText();
        		setdirector=rend_mezo.getText();
        		setstars=star_mezo.getText();
        		setstyle=style_mezo.getText();
        		
        		//Ha nem adunk meg évet és értékelést akkor is felvegye a filmet
        		
        		String sev=ev_mezo.getText();
        		if (sev.equals("")) sev="1900";
        		
        	    String sertek=rating_mezo.getText();
        	    if (sertek.equals("")) sertek="0";
        	    
        		setyear=Integer.parseInt(sev);
        		setrating=Integer.parseInt(sertek);
        		
        		//film felvétele
        		data.addMovie(settitle, setdirector,setstars, setstyle, setyear, setrating);
        	}
        });
        
        //törlés
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
        super("Film adatbázis szerkesztése");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new MovieData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Movies.dat"));
            data.Movies = (List<Movie>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
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

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(1300, 500));
        initComponents();
    }
}
