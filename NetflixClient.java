import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class NetflixClient extends DataFileParser{

	public static void displayHome(ArrayList<Filter> f, ArrayList<Media> m, ArrayList<Media> mL){
		Scanner scnr = new Scanner(System.in);
		listFilters(f);
		System.out.println("(A)dd a filter");
		System.out.println("(R)emove a filter");
		System.out.println("(L)ist matching records (" + m.size() + " found)");
		System.out.println("(Q)uit");
		System.out.print("\nEnter the letter of your choice: ");
		String input = scnr.nextLine().toLowerCase();
		if (input.equals("a"))
			displayAddFilter(f, m);
		else if (input.equals("r"))
			removeFilter(f, m, mL);
		else if (input.equals("l"))
			System.out.println(m);
		else if (input.equals("q"))
			System.exit(0);
		else
			System.out.println("\n***Error: Invald Input.***\n");
	}
	
	public static void displayAddFilter(ArrayList<Filter> f, ArrayList<Media> m){
		Scanner scnr = new Scanner(System.in);
		Filter curFilter = new Filter();
		System.out.print("\nEnter new filter: ");
		String input = scnr.nextLine();
		input = input.toLowerCase();	
		if (input.equals("movie") || input.equals("series"))										// Type
			curFilter.setType(input);
		else if (input.length() > 6 && input.substring(0,5).equals("title"))						// Title
			curFilter.setTitle(input.substring(6));	
		else if (input.length() > 9 && input.substring(0,8).equals("director"))						// Director
			curFilter.setDir(input.substring(9));
		else if (input.length() > 6 && input.substring(0,4).equals("cast"))							// Cast
			curFilter.setCast(input.substring(5));	
		else if (input.length() > 8 && input.substring(0,7).equals("country"))						// Country
			curFilter.setCountry(input.substring(8));
		else if (input.length() > 7 && input.substring(0,6).equals("rating")){						// Rating
			curFilter.setRating(input.substring(7));
			System.out.println(input.substring(7) + "test");
		}
		else if (input.length() > 6 && input.substring(0,5).equals("genre"))						// Genre
			curFilter.setGenre(input.substring(6));
		else if (input.length() > 5 && input.substring(0,4).equals("year"))							// Year
			curFilter.setYear(Integer.parseInt(input.replaceAll("\\D+", "")));
		else if (input.length() > 8 && input.substring(0,7).equals("runtime"))						// Runtime
			curFilter.setDur(Integer.parseInt(input.replaceAll("\\D+", "")));
		else{
			curFilter.setMisc(input);
		}
		if (input.contains(" <") && (curFilter.getYear() != -1 || curFilter.getDur() != -1))		// Less Than
			curFilter.setLessThan(true);
		else if (input.contains(" >") && (curFilter.getYear() != -1 || curFilter.getDur() != -1))	// Greater Than
			curFilter.setGreaterThan(true);
		if (input.contains("= ") && (curFilter.getYear() != -1 || curFilter.getDur() != -1))		// Equal To
			curFilter.setEqualTo(true);
		f.add(curFilter);
		addFilter(curFilter, m);
		
	}

	public static void addFilter(Filter curFilter, ArrayList<Media> m){
		ArrayList<Media> temp = new ArrayList<>();
		for (int i = 0; i < m.size(); i++){
			if (m.get(i) instanceof Movie){
				if (curFilter.findMatch((Movie)m.get(i)))
					temp.add((Movie)m.get(i));
			}
			if (m.get(i) instanceof Series){
				if (curFilter.findMatch((Series)m.get(i)))
					temp.add((Series)m.get(i));
			}
		}
		m.clear();
		m.addAll(temp);
	}

	public static void listFilters(ArrayList<Filter> f){
		String toPrint = "\nCurrent Filters:\n----------------\n";
		for (int i = 0; i < f.size(); i++)
			toPrint += (i + 1) + ". " + f.get(i) + "\n";
		toPrint += "\n";
		System.out.println(toPrint);
	}

	public static void removeFilter(ArrayList<Filter> f, ArrayList<Media> m, ArrayList<Media> mL){
		Scanner scnr = new Scanner(System.in);
		ArrayList<Media> temp2 = new ArrayList<>();
		System.out.print("\nEnter the number of the filter to remove: ");
		int input = -1;
		boolean error = false;
		do {
			try{
				input = scnr.nextInt();
				while (input > f.size()){
					System.out.print("Error: Invalid input. Try again: ");
					input = scnr.nextInt();
					error = false;
				}
				error = false;
			} catch (InputMismatchException e){
				System.out.print("Error: Invalid Input. Try again: ");
				scnr.next();
				error = true;
			}
		}
		while (error);

		f.remove(input - 1);
		m.clear();
		m.addAll(mL);
		for (int i = 0; i < f.size(); i++)
			addFilter(f.get(i), m);	
	}




	public static void main(String[] args){
		ArrayList<Media> masterList = returnList();
		ArrayList<Filter> filterList = new ArrayList<>();
		ArrayList<Media> currentList = new ArrayList<>();
		currentList.addAll(masterList);
		while (true)
			displayHome(filterList, currentList, masterList);
	}


}