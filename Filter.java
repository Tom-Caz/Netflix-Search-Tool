import java.util.*;
public class Filter{
	protected String type,
					 title,
					 dir,
					 cast,
					 country,
					 rating,
					 genre,
					 misc;
	protected int year,
				  dur;
	protected boolean lessThan,
					  greaterThan,
					  equalTo;
	ArrayList<Filter> filters = new ArrayList<>();

	// Constructors:
	public Filter(String type, String title, String dir, String cast, String country, String rating, String genre, int year, int dur, boolean lessThan, boolean greaterThan, boolean equalTo, String misc){
		setType(type);
		setTitle(title);
		setDir(dir);
		setCast(cast);
		setCountry(country);
		setYear(year);
		setRating(rating);
		setGenre(genre);
		setDur(dur);
		setLessThan(lessThan);
		setGreaterThan(greaterThan);
		setEqualTo(equalTo);
		setMisc(misc);
		filters.add(this);
	}

	public Filter(){
		this("","","","","","","",-1,-1,false,false,false,"");
		filters.add(this);
	}

	// Setters:
	public void setType(String value){ type = value;}
	public void setTitle(String value){ title = value;}
	public void setDir(String value){ dir = value;}
	public void setCast(String value){ cast = value;}
	public void setCountry(String value){ country = value;}
	public void setYear(int value){ year = value;}
	public void setRating(String value){ rating = value;}
	public void setDur(int value){ dur = value;}
	public void setGenre(String value){ genre = value;}
	public void setLessThan(boolean value){ lessThan = value;}
	public void setGreaterThan(boolean value){ greaterThan = value;}
	public void setEqualTo(boolean value){ equalTo = value;}
	public void setMisc(String value){ misc = value;}

	// Getters:
	public String getType(){ return type;}
	public String getTitle(){ return title;}
	public String getDir(){ return dir;}
	public String getCast(){ return cast;}
	public String getCountry(){ return this.country;}
	public int getYear(){ return year;}
	public String getRating(){ return rating;}
	public int getDur(){ return dur;}
	public String getGenre(){ return genre;}
	public boolean getLessThan(){ return lessThan;}
	public boolean getGreaterThan(){ return greaterThan;}
	public boolean getEqualTo(){ return equalTo;}
	public ArrayList getFilters(){ return filters;}
	public String getMisc(){ return misc;}

	public boolean findMatch(Series m){
		String filter = this.getType() + this.getTitle() + this.getDir() + this.getCast() + this.getCountry() + this.getRating() + this.getGenre();
		if (filter.equals("series"))
			return true;
		else if (!this.getMisc().equals("") && ((m.getTitle().toLowerCase().contains(getMisc())) || (m.getDirector().toLowerCase().contains(getMisc())) || (m.getCast().toLowerCase().contains(getMisc())) || (m.getCountry().toLowerCase().contains(getMisc())) || (m.getGenre().toLowerCase().contains(getMisc())) || (m.getDesc().toLowerCase().contains(getMisc())))){
			return true;
		}
		else if (!(this.getTitle().equals("")) && (m.getTitle().toLowerCase().contains(this.getTitle())))
			return true;
		else if (!(this.getDir().equals("")) && (m.getDirector().toLowerCase().contains(this.getDir())))
			return true;
		else if (!(this.getCast().equals("")) && (m.getCast().toLowerCase().contains(this.getCast())))
			return true;
		else if (!(this.getCountry().equals("")) && (m.getCountry().toLowerCase().contains(this.getCountry())))
			return true;
		else if (!(this.getGenre().equals("")) && (m.getGenre().toLowerCase().contains(this.getGenre())))
			return true;
		else if (!(this.getRating().equals("")) && this.getRating().toLowerCase().equals(m.getRating().toLowerCase()))
			return true;
		else if (this.getYear() != -1){
			if ((this.getLessThan() && this.getYear() > m.getYear()) || (this.getGreaterThan() && this.getYear() < m.getYear()) || (this.getEqualTo() && this.getYear() == m.getYear())){
				return true;
			}
		}
		else if (this.getDur() != -1){
			if ((this.getLessThan() && this.getDur() > m.getDuration()) || (this.getGreaterThan() && this.getDur() < m.getDuration()) || (this.getEqualTo() && this.getDur() == m.getDuration())){
				return true;

			}
		}
		return false;
	}

	public boolean findMatch(Movie m){
		String filter = this.getType() + this.getTitle() + this.getDir() + this.getCast() + this.getCountry() + this.getRating() + this.getGenre();
		if (filter.equals("movie"))
			return true;
		else if (!this.getMisc().equals("") && ((m.getTitle().toLowerCase().contains(getMisc())) || (m.getDirector().toLowerCase().contains(getMisc())) || (m.getCast().toLowerCase().contains(getMisc())) || (m.getCountry().toLowerCase().contains(getMisc())) || (m.getGenre().toLowerCase().contains(getMisc())) || (m.getDesc().toLowerCase().contains(getMisc())))){
			return true;
		}
		else if (!(this.getTitle().equals("")) && (m.getTitle().toLowerCase().contains(this.getTitle())))
			return true;
		else if (!(this.getDir().equals("")) && (m.getDirector().toLowerCase().contains(this.getDir())))
			return true;
		else if (!(this.getCast().equals("")) && (m.getCast().toLowerCase().contains(this.getCast())))
			return true;
		else if (!(this.getCountry().equals("")) && (m.getCountry().toLowerCase().contains(this.getCountry())))
			return true;
		else if (!(this.getGenre().equals("")) && (m.getGenre().toLowerCase().contains(this.getGenre())))
			return true;
		else if (!(this.getRating().equals("")) && this.getRating().toLowerCase().equals(m.getRating().toLowerCase()))
			return true;
		else if (this.getYear() != -1){
			if ((this.getLessThan() && this.getYear() > m.getYear()) || (this.getGreaterThan() && this.getYear() < m.getYear()) || (this.getEqualTo() && this.getYear() == m.getYear()))
				return true;
		}
		else if (this.getDur() != -1){
			if ((this.getLessThan() && this.getDur() > m.getDuration()) || (this.getGreaterThan() && this.getDur() < m.getDuration()) || (this.getEqualTo() && this.getDur() == m.getDuration()))
				return true;
		}
		return false;
	}

	public String toString(){
		String toReturn =  "";
		if (type.equals("movie"))
			toReturn += "Movie";
		else if (type.equals("series"))
			toReturn += "Series";
		else if (!title.equals(""))
			toReturn += "Title: " + title;
		else if (!dir.equals(""))
			toReturn += "Director: " + dir;
		else if (!cast.equals(""))
			toReturn += "Cast: " + cast;
		else if (!country.equals(""))
			toReturn += "Country: " + country;
		else if (!rating.equals(""))
			toReturn += "Rating: " + rating;
		else if (!genre.equals(""))
			toReturn += "Genre: " + genre;

		if (year != -1){
			toReturn += "Year ";
			if (lessThan)
				toReturn += "< ";
			else if (greaterThan)
				toReturn += "> ";
			if (equalTo)
				toReturn += "= ";
			toReturn += " " + year;
		}
		else if (dur != -1){
			toReturn += "Runtime ";
			if (lessThan)
				toReturn += "< ";
			else if (greaterThan)
				toReturn += "> ";
			if (equalTo)
				toReturn += "= ";
			toReturn += dur;
		}
		toReturn += misc;
		return toReturn;
	}
}