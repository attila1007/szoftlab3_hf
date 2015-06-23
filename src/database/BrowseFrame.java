package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
public class BrowseFrame extends JFrame {
    
    /*
     * Ebben az objektumban hozzuk létre a kereséshez szükséges ablakot 
     */
    public MovieData data;//MovieData példányosítása
    public MovieData temp;//ideiglene tároló a kereséshez, ezt módosítjuk majd
    
    //kereséshez
    public String settitle="";
    public String setdirector="";
    public String setstars="";
    public String setstyle="";
    public int setyear1=1900;
    public int setyear2=2020;
    public int setrating1=0;
    public int setrating2=100;    
    
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     */
	
    private void initComponents() {
        this.setLayout(new BorderLayout());
        
        //létrehozunk egy JPanel-t és hozzáadjuk
        JPanel jp2=new JPanel();
        this.add(BorderLayout.SOUTH,jp2);
        
        //létrehozunk egy JTable-t ami a temp adatait jeleníti majd meg
        final JTable table = new JTable(temp);
        
        //rendezéshez
        table.setAutoCreateRowSorter(true);

        table.setFillsViewportHeight(true);
        
        //létrehozunk egy scroll panelt és hozzáadjuk
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(BorderLayout.CENTER,scrollPane);
        
        jp2.setBackground(new Color(140,245,251));
        table.setBackground(new Color(209,255,198));
        table.setForeground(new Color(15,0,147));
        //table.setGridColor(new Color(140,245,251));
        
        //kereséshez szükséges mezõk létrehozása és felvétele
        JLabel cim=new JLabel("Cím:");
        jp2.add(cim);
        final JTextField cim_mezo=new JTextField(15);
        jp2.add(cim_mezo);
        
        JLabel rendezo=new JLabel("Rendezõ:");
        jp2.add(rendezo);
        final JTextField rendezo_mezo=new JTextField(15);
        jp2.add(rendezo_mezo);
        
        JLabel szereplok=new JLabel("Szereplõk:");
        jp2.add(szereplok);
        final JTextField szereplok_mezo=new JTextField(15);
        jp2.add(szereplok_mezo);
        
        JLabel mufaj=new JLabel("Mûfaj:");
        jp2.add(mufaj);
        final JTextField mufaj_mezo=new JTextField(15);
        jp2.add(mufaj_mezo);
        
        JLabel ev=new JLabel("Év:");
        jp2.add(ev);
        final JTextField ev_mezo1=new JTextField(4);
        jp2.add(ev_mezo1);
        final JTextField ev_mezo2=new JTextField(4);
        jp2.add(ev_mezo2);
        
        JLabel ertekeles=new JLabel("Értékelés:");
        jp2.add(ertekeles);
        final JTextField ertek_mezo1=new JTextField(3);
        jp2.add(ertek_mezo1);
        final JTextField ertek_mezo2=new JTextField(3);
        jp2.add(ertek_mezo2);

        //keresõ nyomogomb
        JButton search=new JButton("Keres");
        jp2.add(search);
        
        //keresés             
        search.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		//lekérdezzük a JTextField-ek értékeit
        		settitle=cim_mezo.getText();
        		setdirector=rendezo_mezo.getText();
        		setstars=szereplok_mezo.getText();
        		setstyle=mufaj_mezo.getText();
        		
        		//ha fordított sorrendbe adták meg vagy nem adtak meg semmit az évhez és értékeléshez
        		//úgy is mûködjön
        		int ev1,ev2,ertek1,ertek2;
        		
        		String sev1=ev_mezo1.getText();
        		if (sev1.equals("")) sev1="1900";
        		String sev2=ev_mezo2.getText();
        		if (sev2.equals("")) sev2="2020";
        		
        	    String sertek1=ertek_mezo1.getText();
        	    if (sertek1.equals("")) sertek1="0";
        	    String sertek2=ertek_mezo2.getText();
        	    if (sertek2.equals("")) sertek2="100";
        	    
        		
        		ev1=Integer.parseInt(sev1);
        		ev2=Integer.parseInt(sev2);
        		ertek1=Integer.parseInt(sertek1);
        		ertek2=Integer.parseInt(sertek2);
        		
        		if (ev1>=ev2)
            		{
            			if (ev1>2020)
                			setyear2=2020;
                		else 
                			setyear2=ev1;
            			
                		if (ev2<1900)
                			setyear1=1900;
                		else
                			setyear1=ev2;
            		}
            	else
            		{
            		if (ev2>2020)
            			setyear2=2020;
            		else 
            			setyear2=ev2;
        			
            		if (ev1<1900)
            			setyear1=1900;
            		else
            			setyear1=ev1;
            		}
        				
        		if (ertek1<=ertek2)
        		{
            		if (ertek1<0)
            			setrating1=0;
            		else
            			setrating1=ertek1;
            	    if(ertek2>100)
            			setrating2=100;
            		else
            			setrating2=ertek2;
        		}
        		else
        		{
            		if (ertek2<0)
            			setrating1=0;
            		else
            			setrating1=ertek2;
            	    if(ertek1>100)
            			setrating2=100;
            		else
            			setrating2=ertek1;
        		}
        		
        		//keresés
        		temp.Search(data,settitle,setdirector,setstars,setstyle,setyear1,setyear2,setrating1,setrating2);
        	}
        });         
    }
    
    /*
     * Az ablak konstruktora.
     * 
     */
    @SuppressWarnings("unchecked")
    public BrowseFrame() {
        super("Filmek böngészése");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new MovieData();
            temp = new MovieData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Movies.dat"));
            data.Movies = (List<Movie>)ois.readObject();
            ois.close();
            //temp-be bemásoljuk a beolvasott adatokat
            for (int i=0 ; i < data.size() ; i++)
        	{   		
        		temp.addMovie(data.getMovie(i));
      		}
      
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Felépítjük az ablakot
        setMinimumSize(new Dimension(1300, 500));
        initComponents();
    }
}
