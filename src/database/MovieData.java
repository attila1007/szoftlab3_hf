package database;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A filmek adatait t�rol� oszt�ly.
 */
public class MovieData extends AbstractTableModel  {

    /*
     * Ez a tagv�ltoz� t�rolja a film adatokat.
     */
    public List<Movie> Movies = new ArrayList<Movie>();
    
    //oszlopok sz�ma
    public int getColumnCount()   {
    	return 6;
    }
    
    //soroksz�ma
    public int getRowCount() {
    	return Movies.size();
    }
    
    //film adatok kikeres�se sor �s oszlopsz�m alapj�n
    public Object getValueAt(int rowIndex, int columnIndex) { 
    	 Movie Movie = Movies.get(rowIndex); 
    	 switch(columnIndex) { 
    	 	case 0: return Movie.getTitle(); 
    	 	case 1: return Movie.getDirector(); 
    	 	case 2: return Movie.getStars();
    	 	case 3: return Movie.getStyle();
    	 	case 4: return Movie.getYear();
    	 	default: return Movie.getRating(); 
    	 } 
    }
    
    //oszlopn�v be�ll�t�s
    public String getColumnName(int column) {
        switch (column) {
        case 0:return "C�m";
        case 1:return "Rendez�";
        case 2:return "Szerepl�k";
        case 3:return "M�faj";
        case 4:return "Megjelen�s �ve";
        default:return "�rt�kel�s";
        }    
    }
    
    //oszlopba szerepl� adatok t�pus�nak lek�rdez�se
    public Class getColumnClass(int column) {
    	switch (column) {
        case 0:return String.class;
        case 1:return String.class;
        case 2:return String.class;
        case 3:return String.class;
        case 4:return Integer.class;
        default:return Integer.class;
        }
    }
    
    //szerkeszthet� e a cella
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return true;
    }
    
    //cella szerkeszt�se sor �s oszlopsz�m alapj�n
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) 
    {
    	Movie Movie = Movies.get(rowIndex);
    	switch(columnIndex) 
    	{ 
    		case 0: Movie.setTitle((String)aValue); break;
    		case 1: Movie.setDirector((String)aValue); break;
    		case 2: Movie.setStars((String)aValue); break;
    		case 3: Movie.setStyle((String)aValue); break;
    		case 4: Movie.setYear((Integer)aValue);break;
    		default: Movie.setRating((Integer)aValue); 
   	 	} 
    }
    
    //a filmeket tartalmazo ArrayList lek�rdez�se
    public List<Movie> getMovies() {
    	return Movies;
    }
    
    //sor sz�m alapj�n egy film lek�rdez�se
    public Movie getMovie(int rowIndex)  {
    	Movie Movie = Movies.get(rowIndex);
    	return Movie;
    }
    
    //film t�rl�se
    public void deleteMovie(Object o)   {
    	Movies.remove(o);
    }
       
    //az �sszes film t�rl�se
    public void Clear()
    {
    	Movies.clear();
    }
    
    //elt�rolt filmek sz�m�nak lek�rdez�se
    public int size()
    {
    	return Movies.size();
    }
    
    //keres�s
    //egyszerre minden attributum alapj�n
    //ha nem adnak meg egyet az se gond �gyis m�k�dik
    //az �vnek �s az �rt�kel�snek egy als� �s egy fels� hat�rt lehet megadni
    //�s a k�t �rt�k k�z� es�ket list�zza ki
    public void Search(MovieData data, String title, String director, String stars, String style, int year1,int year2, int rating1, int rating2)
    {
    	List<Movie> tmp = new ArrayList<Movie>();

    	for (int i=0 ; i < data.size() ; i++)
    	{   		
    		if (data.Movies.get(i).getTitle().contains(title)&& //c�m
    				data.Movies.get(i).getDirector().contains(director)&& //rendez�
    				data.Movies.get(i).getStars().contains(stars)&& //szerepl�k
    				data.Movies.get(i).getStyle().contains(style)&&  //m�faj
    			year1<=data.Movies.get(i).getYear()&& //�v
    			year2>=data.Movies.get(i).getYear()&&
    			rating1<=data.Movies.get(i).getRating()&& //�rt�kel�s
    			rating2>=data.Movies.get(i).getRating())
    		{
    			tmp.add(data.Movies.get(i));
    		}
    	}
    	
    	this.Clear();
    	this.addMovies(tmp);
    	this.fireTableDataChanged();
    }
    
    //film hozz�ad�sa attributumokk�nt
    public void addMovie(String title, String director, String stars, String style, int year, int rating) {
    	//c�met k�telez� megadni
    	if (!title.equals(""))
    	{
    		Movie temp=new Movie(title,director,stars,style,year,rating);
        	this.Movies.add(temp);
        	this.fireTableDataChanged();
    	}
    } 
    
    //film hozz�ad�sa 
    public void addMovie(Movie tmp)
    {
    	this.Movies.add(tmp);
    	this.fireTableDataChanged();
    }
    
    //filmek hozz�ad�sa
    public void addMovies(List<Movie> tmp)
    {
    	this.Movies=tmp;
    	this.fireTableDataChanged();
    }
    

}
