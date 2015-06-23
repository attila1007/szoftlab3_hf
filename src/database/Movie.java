package database;

import java.io.Serializable;

/*
 * Egy film adatait tároló osztály.
 * 
 */
public class Movie implements Serializable {

    // Film címe
    private String title;
    // Cím lekérdezése. 
    public String getTitle() {
        return title;    }
    // Cím beállítása.
    public void setTitle(String title) {
        this.title = title;    }

    
    // Rendezõ.
    private String director;
    // Rendezõ lekérdezése.
    public String getDirector() {
        return director;    }
    // Rendezõ beállítása.
    public void setDirector(String director) {
        this.director = director;    }

    
    // Szereplõk
    private String stars;
    //szereplõk lekérdezése
    public String getStars() {
        return stars;    }
    // Szereplõk beállítása.
    public void setStars(String stars) {
        this.stars = stars;    }
    
    
    //mûfaj
    private String style;
    //mûfaj lekérdezése
    public String getStyle() {
        return style;    }
    // mûfaj beállítása.
    public void setStyle(String style) {
        this.style = style;    }
    

    // Megjelenés éve 1900 és 2020 közötti érték
    private int year;
    // Év lekérdezése.
    public Integer getYear() {
        return year;    }
    // Év beállítása.
    public void setYear(Integer year) {
    	if (year<1900) this.year = 1900;
    	else if (year>2020) this.year = 2020;
    	else this.year = year;
    }
    
    // Értékelés 0 és 100 közötti érték
    private int rating;
    // Értékelés lekérdezése
    public Integer getRating() {
        return rating;    }
    // Értékelés beállítása
    public void setRating(Integer rating) {
    	if (rating<0) this.rating = 0;
    	else if (rating>100) this.rating = 100;
    	else this.rating = rating;
    }

    // Film létrehozása
    public Movie(String title, String director, String stars , String style, Integer year, Integer rating) 
    {
        this.title = title;
        this.director = director;
        this.stars = stars;
        this.style = style;
        
    	if (year<1900) this.year = 1900;
    	else if (year>2020) this.year = 2020;
    	else this.year = year;
    	
    	if (rating<0) this.rating = 0;
    	else if (rating>100) this.rating = 100;
    	else this.rating = rating;
    }
    
}
