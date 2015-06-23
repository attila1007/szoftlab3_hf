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
 * A megjelen�tend� ablakunk oszt�lya.
 */
public class BrowseFrame extends JFrame {
    
    /*
     * Ebben az objektumban hozzuk l�tre a keres�shez sz�ks�ges ablakot 
     */
    public MovieData data;//MovieData p�ld�nyos�t�sa
    public MovieData temp;//ideiglene t�rol� a keres�shez, ezt m�dos�tjuk majd
    
    //keres�shez
    public String settitle="";
    public String setdirector="";
    public String setstars="";
    public String setstyle="";
    public int setyear1=1900;
    public int setyear2=2020;
    public int setrating1=0;
    public int setrating2=100;    
    
    /*
     * Itt hozzuk l�tre �s adjuk hozz� az ablakunkhoz a k�l�nb�z� komponenseket
     */
	
    private void initComponents() {
        this.setLayout(new BorderLayout());
        
        //l�trehozunk egy JPanel-t �s hozz�adjuk
        JPanel jp2=new JPanel();
        this.add(BorderLayout.SOUTH,jp2);
        
        //l�trehozunk egy JTable-t ami a temp adatait jelen�ti majd meg
        final JTable table = new JTable(temp);
        
        //rendez�shez
        table.setAutoCreateRowSorter(true);

        table.setFillsViewportHeight(true);
        
        //l�trehozunk egy scroll panelt �s hozz�adjuk
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(BorderLayout.CENTER,scrollPane);
        
        jp2.setBackground(new Color(140,245,251));
        table.setBackground(new Color(209,255,198));
        table.setForeground(new Color(15,0,147));
        //table.setGridColor(new Color(140,245,251));
        
        //keres�shez sz�ks�ges mez�k l�trehoz�sa �s felv�tele
        JLabel cim=new JLabel("C�m:");
        jp2.add(cim);
        final JTextField cim_mezo=new JTextField(15);
        jp2.add(cim_mezo);
        
        JLabel rendezo=new JLabel("Rendez�:");
        jp2.add(rendezo);
        final JTextField rendezo_mezo=new JTextField(15);
        jp2.add(rendezo_mezo);
        
        JLabel szereplok=new JLabel("Szerepl�k:");
        jp2.add(szereplok);
        final JTextField szereplok_mezo=new JTextField(15);
        jp2.add(szereplok_mezo);
        
        JLabel mufaj=new JLabel("M�faj:");
        jp2.add(mufaj);
        final JTextField mufaj_mezo=new JTextField(15);
        jp2.add(mufaj_mezo);
        
        JLabel ev=new JLabel("�v:");
        jp2.add(ev);
        final JTextField ev_mezo1=new JTextField(4);
        jp2.add(ev_mezo1);
        final JTextField ev_mezo2=new JTextField(4);
        jp2.add(ev_mezo2);
        
        JLabel ertekeles=new JLabel("�rt�kel�s:");
        jp2.add(ertekeles);
        final JTextField ertek_mezo1=new JTextField(3);
        jp2.add(ertek_mezo1);
        final JTextField ertek_mezo2=new JTextField(3);
        jp2.add(ertek_mezo2);

        //keres� nyomogomb
        JButton search=new JButton("Keres");
        jp2.add(search);
        
        //keres�s             
        search.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		//lek�rdezz�k a JTextField-ek �rt�keit
        		settitle=cim_mezo.getText();
        		setdirector=rendezo_mezo.getText();
        		setstars=szereplok_mezo.getText();
        		setstyle=mufaj_mezo.getText();
        		
        		//ha ford�tott sorrendbe adt�k meg vagy nem adtak meg semmit az �vhez �s �rt�kel�shez
        		//�gy is m�k�dj�n
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
        		
        		//keres�s
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
        super("Filmek b�ng�sz�se");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Indul�skor bet�ltj�k az adatokat
        try {
            data = new MovieData();
            temp = new MovieData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Movies.dat"));
            data.Movies = (List<Movie>)ois.readObject();
            ois.close();
            //temp-be bem�soljuk a beolvasott adatokat
            for (int i=0 ; i < data.size() ; i++)
        	{   		
        		temp.addMovie(data.getMovie(i));
      		}
      
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Fel�p�tj�k az ablakot
        setMinimumSize(new Dimension(1300, 500));
        initComponents();
    }
}
