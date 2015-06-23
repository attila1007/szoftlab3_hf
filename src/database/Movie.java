package database;

import java.io.Serializable;

/*
 * Egy film adatait t�rol� oszt�ly.
 * 
 */
public class Movie implements Serializable {

    // Film c�me
    private String title;
    // C�m lek�rdez�se. 
    public String getTitle() {
        return title;    }
    // C�m be�ll�t�sa.
    public void setTitle(String title) {
        this.title = title;    }

    
    // Rendez�.
    private String director;
    // Rendez� lek�rdez�se.
    public String getDirector() {
        return director;    }
    // Rendez� be�ll�t�sa.
    public void setDirector(String director) {
        this.director = director;    }

    
    // Szerepl�k
    private String stars;
    //szerepl�k lek�rdez�se
    public String getStars() {
        return stars;    }
    // Szerepl�k be�ll�t�sa.
    public void setStars(String stars) {
        this.stars = stars;    }
    
    
    //m�faj
    private String style;
    //m�faj lek�rdez�se
    public String getStyle() {
        return style;    }
    // m�faj be�ll�t�sa.
    public void setStyle(String style) {
        this.style = style;    }
    

    // Megjelen�s �ve 1900 �s 2020 k�z�tti �rt�k
    private int year;
    // �v lek�rdez�se.
    public Integer getYear() {
        return year;    }
    // �v be�ll�t�sa.
    public void setYear(Integer year) {
    	if (year<1900) this.year = 1900;
    	else if (year>2020) this.year = 2020;
    	else this.year = year;
    }
    
    // �rt�kel�s 0 �s 100 k�z�tti �rt�k
    private int rating;
    // �rt�kel�s lek�rdez�se
    public Integer getRating() {
        return rating;    }
    // �rt�kel�s be�ll�t�sa
    public void setRating(Integer rating) {
    	if (rating<0) this.rating = 0;
    	else if (rating>100) this.rating = 100;
    	else this.rating = rating;
    }

    // Film l�trehoz�sa
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
