import java.io.*;
import java.util.*;
public class DataFileParser<E>{
	public static ArrayList<Media> returnList(){
		Scanner scnr = null;
		ArrayList<String> data = new ArrayList<>();
		int count = 0;
		try {
			scnr = new Scanner(new FileInputStream("netflix_titles.csv"));
		} catch (FileNotFoundException e){
			System.out.println("Error: File not found");
			System.exit(1);
		}
		while (scnr.hasNext()){
			String  thisLine = (scnr.nextLine());
			for (String e:thisLine.toString().split(","))			// Splits lines at ','
				data.add(e);
		}
		for (int i = 0; i < data.size(); i++){
			if (data.get(i).length() != 0){
				if (data.get(i).charAt(0) == '\"' && data.get(i).charAt(data.get(i).length() - 1) != '\"'){			// Combines lines with ""
					if (data.get(i + 1).length() > 1 && data.get(i + 1).charAt(data.get(i + 1).length() - 1) == '\"' && data.get(i + 1).charAt(1) != '\"' && data.get(i + 1).charAt(data.get(i + 1).length() - 2) != '\"'){
						String temp = data.get(i) + "," + data.get(i + 1);		// Next index ends with "
						data.set(i, temp);
						data.remove(i + 1);
					}
					else{													
						String temp = data.get(i) + ",";
						while (true){
							temp += data.get(i + 1) + ",";
							if (data.get(i + 1).length() > 1 && data.get(i + 1).charAt(data.get(i + 1).length() - 1) == '\"' && data.get(i + 1).charAt(1) != '\"' && data.get(i + 1).charAt(data.get(i + 1).length() - 2) != '\"'){
								data.remove(i + 1);
								break;
							}
							data.remove(i + 1);
						}
						data.set(i, temp);
					}
				}
			}
		}
		for (int i = 0; i < data.size(); i++){
			data.set(i, data.get(i).replace("\"\"", "\""));			// Removes double quotes

		}
		return toMedia(data);
	}

	// Creates media objects of inputs 
	public static ArrayList<Media> toMedia(ArrayList<String> data){
		ArrayList<Media> mediaData = new ArrayList<>();
		for (int i = 0; i < 93085; i++){
			if(data.get(i).equals("Movie")){				// Creates Movies 
				if (data.get(i + 4).contains(",\",\"")){					// Edge Case: On some movies, the csv file puts an exta ',' at the end of the countries (I'm not sure why)
					String[] edgeCase = data.get(i + 4).split(",\",\"");
					data.set(i + 4, edgeCase[0]);
					data.add(i + 5, "");
				}
				Media x = new Movie(data.get(i + 1), data.get(i + 2), data.get(i + 3), data.get(i + 4), Integer.parseInt(data.get(i + 6)), data.get(i + 7), Integer.parseInt(data.get(i + 8).replace(" min", "")), data.get(i + 9), data.get(i + 10));
				mediaData.add(x);
			}
			else if(data.get(i).equals("TV Show")){			// Creates TV Show
				Media x = new Series(data.get(i + 1), data.get(i + 2), data.get(i + 3), data.get(i + 4), Integer.parseInt(data.get(i + 6)), data.get(i + 7), Integer.parseInt(data.get(i + 8).replace(" Seasons", "").replace(" Season", "")), data.get(i + 9), data.get(i + 10));
				mediaData.add(x);
			}
		}
		return mediaData;
	}

	public static void main(String[] args){
		System.out.println(returnList());
	}
}