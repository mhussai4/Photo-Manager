package photomanager;

import java.io.*;
import java.util.*;

/**
 * The PhotoManager class keeps track of Photos by using an ArrayList of Photo
 * references. The class relies heavily on ArrayList methods. At least you will
 * be using the following ArrayList methods: add, get, remove, clear. Check the
 * Java API ArrayList entry for information about each of these methods.
 * 
 * @author UMCP CS Department
 *
 */
public class PhotoManager {
	private ArrayList<Photo> allPhotos;

	/**
	 * Assigns to the allPhotos instance variable an ArrayList of Photos.
	 */

	public PhotoManager() {
		this.allPhotos = new ArrayList<Photo>();
	}

	/**
	 * Creates a Photo based on the provided parameters and adds the photo to the
	 * allPhotos ArrayList. The photo will be added if it does not already exist in
	 * the ArrayList (hint: use the findPhoto method). The method must handle
	 * (try/catch block) any exception thrown by creating a Photo (remember that a
	 * Photo constructor can throw an IllegalArgumentException). If an exception is
	 * thrown by the Photo constructor, the photo will not be added; in addition the
	 * message "addPhoto: Invalid arguments" will be printed to standard error (that
	 * means using System.err.println (notice the err)) and false will be returned.
	 * If a photo is added the method will return true.
	 * 
	 * @param photoSource Photo's url or filename.
	 * @param width       Photo's width in pixels.
	 * @param height      Photo's height in pixels.
	 * @param date        Date the photo was taken.
	 * @return true if photo added; false otherwise.
	 */
	public boolean addPhoto(String photoSource, int width, int height, String date) {
		// Attempts to create a new Photo object with provided parameters
		try {

			Photo newPhoto = new Photo(photoSource, width, height, date);
			// Adds the newly created photo to the 'allPhotos' collection
			allPhotos.add(newPhoto);

			// Checks if the newly added photo can be retrieved and its source
			// matches the provided photoSource
			if (allPhotos.get(findPhoto(photoSource)).getPhotoSource().equals(photoSource)) {
				// If the source of the retrieved photo matches the given photoSource,
				// return true, showing successful addition
				return true;
			}
			// If the source doesn't match, return false
			return false;
		} catch (IllegalArgumentException e) {
			// If an IllegalArgumentException is caught during the creation of the new
			// photo,
			// handle the exception Print an error message indicating invalid arguments
			System.err.print("addPhoto: Invalid arguments");
			// Return false, indicating that the addition of the photo was
			// unsuccessful due to invalid arguments
			return false;
		}

	}

	/**
	 * Returns a string where each Photo is printed on a line by itself.
	 * 
	 * @return String with information about all photos.
	 */

	public String toString() {
		String result = "";
		// Goes through the 'allPhotos' list
		for (int i = 0; i < allPhotos.size(); i++) {
			// Append the string representation of each photo to 'result'
			result += allPhotos.get(i).toString();

			// If it's not the last photo, add a new line character for separation
			if (i < allPhotos.size() - 1) {
				result += ("\n");
			}
		}
		return result;
	}

	/**
	 * Returns the index in the ArrayList associated with the Photo that has a
	 * photoSource corresponding to the parameter. The method will return -1 if no
	 * photo is found or if the parameter is null.
	 * 
	 * @param photoSource Photo's photoSource.
	 * @return Index in the array or -1 (photo not found).
	 */

	public int findPhoto(String photoSource) {

		// Checks if the given photo source is null
		if (photoSource == null) {
			return -1;
		}

		// Goes through all the photos in the 'allPhotos' list
		for (int i = 0; i < allPhotos.size(); i++) {
			// Retrieves each photo from the list
			Photo existingPhoto = allPhotos.get(i);

			// Makes sure the current photo is not null and compares its source
			// to the given photo source
			if (existingPhoto != null && photoSource.equals(existingPhoto.getPhotoSource())) {
				// If the photo source matches, return the index 'i',
				// showing the position of the found photo
				return i;
			}
		}
		// If photo is not found, return -1 to show that the photo was not found.
		return -1;
	}

	/**
	 * Adds the specified comment to the photo with the specified photoSource (if
	 * such photo is present in allPhotos). It returns true if the comments are
	 * added and false if the photo could not be found, or if the parameters are
	 * invalid. A parameter is invalid if it is null or if the newComment string is
	 * blank (according to the String method isBlank()).
	 * 
	 * @param photoSource PhotoSource of photo we would like to add the comment.
	 * @param newComment  Comment to add.
	 * @return true if comment added; false otherwise.
	 */
	public boolean addComment(String photoSource, String newComment) {

		// Checks if the provided photo source or new comment is null or an empty/blank
		// string
		if (photoSource == null || newComment == null || newComment.isBlank()) {
			return false;
		}

		// Goes through the 'allPhotos' list
		for (int i = 0; i < allPhotos.size(); i++) {
			// Retrieves the photo at the current index 'i'
			Photo indexPhoto = allPhotos.get(i);
			// Checks if the current photo is not null and matches the provided
			// 'photoSource'
			if (indexPhoto != null && photoSource.equals(indexPhoto.getPhotoSource())) {
				// If the photo exists and matches the 'photoSource':
				// it adds the new comment to the matched photo
				allPhotos.get(i).addComments(newComment);
				// Returns true to indicate that the comment was successfully added
				return true;
			}
		}
		// If the photo is not found, return false.
		return false;
	}

	/**
	 * Returns the comments of the photo associated with the specified photoSource.
	 * The method will return null if no photo exists with the specified
	 * photoSource, or if the parameter is null.
	 * 
	 * @param photoSource photo to find comments for.
	 * @return Comments or null.
	 */
	public String getComments(String photoSource) {

		// Check if the provided photo source or new comment is null or an 
		// empty/blank string
		if (photoSource == null) {
			return null; 
		}
		
		// Goes through the 'allPhotos' list
		for (int i = 0; i < allPhotos.size(); i++) {
			// Retrieves the photo at the current index 'i'
			Photo indexPhoto = allPhotos.get(i);
			
			 // Checks if the current photo is not null and matches the provided 'photoSource'
			if (indexPhoto != null && photoSource.equals(indexPhoto.getPhotoSource())) {
				// If the photo exists and matches the 'photoSource':
		        // Add the new comment to the matched photo
				return allPhotos.get(i).getComments();
			}

		}
		// If no photo exists, return null.
		return null;
	}

	/**
	 * Removes all the photos from allPhotos. This method only takes a single line
	 * of code.
	 */
	public void removeAllPhotos() {
		allPhotos.clear();
	}

	/**
	 * Removes the Photo with the specified photoSource (if it exists). Returns true
	 * if the photo was removed and false if the photo was not found or the
	 * parameter is null. Remember that you can remove elements from an ArrayList
	 * using the ArrayList remove method.
	 * 
	 * @param photoSource Photo we would like to remove.
	 * @return true if photo was removed; false otherwise.
	 */
	public boolean removePhoto(String photoSource) {
	    // Checks if the provided photo source is null
	    if (photoSource == null) {
	        return false;
	    }

	    // Goes through the list of photos
	    for (int i = 0; i < allPhotos.size(); i++) {
	        // Gets the photo at the current index
	        Photo indexPhoto = allPhotos.get(i);
	        
	        // Checks if the current photo is not null and matches the given photo source
	        if (indexPhoto != null && photoSource.equals(indexPhoto.getPhotoSource())) {
	            // Removes the photo at the current index if it matches the provided source
	            allPhotos.remove(i);
	            // Return true indicating successful removal of the photo
	            return true;
	        }
	    }
	    // If no match is found or the list is empty, return false as the photo 
	    // couldn't be removed
	    return false;
	}

	/**
	 * Loads the photos specified in filename to the allPhotos ArrayList. It adds to
	 * the ArrayList (it does not clear the ArrayList before adding photos). Each
	 * line of the file will have information about a photo. The information will be
	 * the photoSource, followed by the width, height and date. You can assume the
	 * file will have correct information. The following is an examples of a file
	 * entry: <br>
	 * umcp/college1.jpg 300 400 10/18/2020-17:10 <br>
	 * If an error takes place while opening the file (e.g., file does not exist),
	 * an error message (any message is fine) will be printed using
	 * System.err.println. Notice that your code will not crash when a file could
	 * not be opened; in this case the method will print the error message and
	 * return false. The method will return false and perform no processing when the
	 * parameter is null.
	 * 
	 * @param filename Name of file with information about photos.
	 * @return true if the data was loaded and false otherwise.
	 * 
	 */
	public boolean loadPhotos(String filename) {
	    // Checks if the filename is null, returns false to indicate failure
	    if (filename == null) {
	        return false;
	    }

	    try {
	        // Attempts to read from the file using Scanner
	        Scanner myScanner = new Scanner(new File(filename));

	        // Goes through the file content
	        while (myScanner.hasNext()) {
	            // Extracts photo details from the file
	            String photoSource = myScanner.next();
	            int width = myScanner.nextInt();
	            int height = myScanner.nextInt();
	            String date = myScanner.next();

	            // Creates a new Photo object using the extracted information
	            Photo newPhoto = new Photo(photoSource, width, height, date);

	            // Adds the new Photo object to the 'allPhotos' collection
	            allPhotos.add(newPhoto);
	        }

	        // Returns true to indicate successful loading of photos from the file
	        return true;
	    } catch (IOException e) {
	        // If an IOException occurs during file reading, catch and handle it
	        // Print the error message to the standard error stream
	        System.err.println(e.getMessage());

	        // Return false to indicate failure in loading photos from the file
	        return false;
	    }
	}

	/**
	 * Sorts the allPhotos by date. This method requires a single line of code.
	 */

	public void sortPhotosByDate() {
		Collections.sort(allPhotos);
	}

	/**
	 * This method creates an HTML file with all the photos. This method has been
	 * implemented for you and it is not used by any other method.
	 * 
	 * @param htmlFilename Web page with photos.
	 */

	public void createHTMLPage(String htmlFilename) {
		String body = "";

		for (Photo photo : allPhotos) {
			body += "<img src=\"" + photo.getPhotoSource() + "\" ";
			body += "width=\"" + photo.getWidth() + "\" ";
			body += "height=\"" + photo.getHeight() + "\" ";
			body += "alt=\"photo image\"><br>\n";
		}

		Utilities.generateHTMLPageWithBody(htmlFilename, body);
	}
}