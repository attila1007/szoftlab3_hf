package database;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A filmek adatait tároló osztály.
 */
public class MovieData extends AbstractTableModel  {

    /*
     * Ez a tagváltozó tárolja a film adatokat.
     */
    public List<Movie> Movies = new ArrayList<Movie>();
    
    //oszlopok száma
    public int getColumnCount()   {
    	return 6;
    }
    
    //sorokszáma
    public int getRowCount() {
    	return Movies.size();
    }
    
    //film adatok kikeresése sor és oszlopszám alapján
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
    
    //oszlopnév beállítás
    public String getColumnName(int column) {
        switch (column) {
        case 0:return "Cím";
        case 1:return "Rendezõ";
        case 2:return "Szereplõk";
        case 3:return "Mûfaj";
        case 4:return "Megjelenés éve";
        default:return "Értékelés";
        }    
    }
    
    //oszlopba szereplõ adatok típusának lekérdezése
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
    
    //szerkeszthetõ e a cella
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return true;
    }
    
    //cella szerkesztése sor és oszlopszám alapján
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
    
    //a filmeket tartalmazo ArrayList lekérdezése
    public List<Movie> getMovies() {
    	return Movies;
    }
    
    //sor szám alapján egy film lekérdezése
    public Movie getMovie(int rowIndex)  {
    	Movie Movie = Movies.get(rowIndex);
    	return Movie;
    }
    
    //film törlése
    public void deleteMovie(Object o)   {
    	Movies.remove(o);
    }
       
    //az összes film törlése
    public void Clear()
    {
    	Movies.clear();
    }
    
    //eltárolt filmek számának lekérdezése
    public int size()
    {
    	return Movies.size();
    }
    
    //keresés
    //egyszerre minden attributum alapján
    //ha nem adnak meg egyet az se gond úgyis mûködik
    //az évnek és az értékelésnek egy alsó és egy felsõ határt lehet megadni
    //és a két érték közé esõket listázza ki
    public void Search(MovieData data, String title, String director, String stars, String style, int year1,int year2, int rating1, int rating2)
    {
    	List<Movie> tmp = new ArrayList<Movie>();

    	for (int i=0 ; i < data.size() ; i++)
    	{   		
    		if (data.Movies.get(i).getTitle().contains(title)&& //cím
    				data.Movies.get(i).getDirector().contains(director)&& //rendezõ
    				data.Movies.get(i).getStars().contains(stars)&& //szereplõk
    				data.Movies.get(i).getStyle().contains(style)&&  //mûfaj
    			year1<=data.Movies.get(i).getYear()&& //év
    			year2>=data.Movies.get(i).getYear()&&
    			rating1<=data.Movies.get(i).getRating()&& //értékelés
    			rating2>=data.Movies.get(i).getRating())
    		{
    			tmp.add(data.Movies.get(i));
    		}
    	}
    	
    	this.Clear();
    	this.addMovies(tmp);
    	this.fireTableDataChanged();
    }
    
    //film hozzáadása attributumokként
    public void addMovie(String title, String director, String stars, String style, int year, int rating) {
    	//címet kötelezõ megadni
    	if (!title.equals(""))
    	{
    		Movie temp=new Movie(title,director,stars,style,year,rating);
        	this.Movies.add(temp);
        	this.fireTableDataChanged();
    	}
    } 
    
    //film hozzáadása 
    public void addMovie(Movie tmp)
    {
    	this.Movies.add(tmp);
    	this.fireTableDataChanged();
    }
    
    //filmek hozzáadása
    public void addMovies(List<Movie> tmp)
    {
    	this.Movies=tmp;
    	this.fireTableDataChanged();
    }
    

}
