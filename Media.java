public abstract class Media{
	protected String title;
	protected String director;
	protected String cast;
	protected String country;
	protected int year;
	protected String rating;
	protected int duration;
	protected String genre;
	protected String desc;
	// Constructor:
	public Media(String title, String director, String cast, String country, int year, String rating, int duration, String genre, String desc){
		setTitle(title);
		setDirector(director);
		setCast(cast);
		setCountry(country);
		setYear(year);
		setRating(rating);
		setDuration(duration);
		setGenre(genre);
		setDesc(desc);
	}
	// Setters:
	public void setTitle(String value){ title = value;}
	public void setDirector(String value){ director = value;}
	public void setCast(String value){ cast = value;}
	public void setCountry(String value){ country = value;}
	public void setYear(int value){ year = value;}
	public void setRating(String value){ rating = value;}
	public void setDuration(int value){ duration = value;}
	public void setGenre(String value){ genre = value;}
	public void setDesc(String value){ desc = value;}

	// Getters:
	public String getTitle(){ return title;}
	public String getDirector(){ return director;}
	public String getCast(){ return cast;}
	public String getCountry(){ return country;}
	public int getYear(){ return year;}
	public String getRating(){ return rating;}
	public int getDuration(){ return duration;}
	public String getGenre(){ return genre;}
	public String getDesc(){ return desc;}

}