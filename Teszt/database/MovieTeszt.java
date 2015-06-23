package database;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTeszt {

	@Test
	public void TesztMovieFrame() {
		MovieFrame m=new MovieFrame();
		m.initComponents();
		m.cim_mezo.getText();
	}
	
	@Test
	public void TesztMovieData() {
		MovieData m=new MovieData();
		m.getColumnCount();
		m.getRowCount();
		m.getColumnName(1);
	}
	
	
	@Test
	public void TesztMovie2() {
		Movie A=new Movie("Cím","Rendezõ Józsi","Szereplõ Béla","Sci-Fi",2010,98);
		A.getTitle();
		A.getDirector();
		A.getStars();
		A.getStyle();
		A.getYear();
		A.getRating();
	}

}
