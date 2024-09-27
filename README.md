# Photo-Manager
The PhotoManager is a Java-based class that manages a collection of photo objects. It uses an ArrayList to store, manipulate, and retrieve photo details such as their source (URL or filename), dimensions, and date taken. The program includes various methods to add, remove, and modify photo information, as well as handling comments for each photo. Additionally, it provides features for sorting photos by date, loading photo data from files, and creating an HTML page with all the photos.

Features
Manage Photos:

Add photos with attributes such as source, width, height, and date.
Remove photos by their source URL or filename.
Retrieve a list of all photos or find a specific one by its source.
Add and retrieve comments associated with each photo.
Error Handling:

Handles invalid photo creation by printing error messages when invalid arguments are provided.
Gracefully handles file-related errors such as missing files during photo loading.
Load Photos from File:

Load photo data from a text file where each line contains information about a photo (source, width, height, date).
HTML Page Generation:

Automatically generates an HTML page displaying all photos with their respective dimensions.
Sorting by Date:

Sort photos by their date taken in ascending order.
