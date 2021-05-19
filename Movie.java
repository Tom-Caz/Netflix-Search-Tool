public class Movie extends Media{
	protected String type = "movie";

	// Constructor: 
	public Movie(String title, String director, String cast, String country, int year, String rating, int duration, String genre, String desc){
		super(title, director, cast, country, year, rating, duration, genre, desc);
	}

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
	public String getType(){ return type;}

	// Methods: 
	public String toString(){
		String toReturn = "\n\n-------------\n";
		toReturn += "\nTitle: \t\t" + title + "\n";
		toReturn += "Type: \t\t" + "Movie" + "\n";
		toReturn += "Director: \t" + director + "\n";
		toReturn += "Cast: \t\t" + cast + "\n";
		toReturn += "Country: \t" + country + "\n";
		toReturn += "Year: \t\t" + year + "\n";
		toReturn += "Rating: \t" + rating + "\n";
		toReturn += "Duration: \t" + duration + " Minutes\n";
		toReturn += "Genre: \t\t" + genre + "\n";
		toReturn += "Description: \t" + desc + "\n";
		return toReturn;
	}
}