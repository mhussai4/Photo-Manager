package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import photomanager.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {

	@Test
	public void testPhotoConstructor() {

		Photo photo1 = new Photo("iPhone", 20, 30, "January");
		String expectedResult = "iPhone,20,30,January";
		String result = photo1.toString();

		assertEquals(expectedResult, result);
	}

	@Test
	public void testPhotoAddComments() {
		Photo photo1 = new Photo("iPhone", 20, 30, "January");
		photo1.addComments("I like pie");
		photo1.addComments("My mom baked me a pie");
		String expectedResult = "I like pie,My mom baked me a pie";
		String result = photo1.getComments();
		assertEquals(expectedResult, result);
	}

	@Test
	public void testPhotoCompareTo1() {
		Photo photo1 = new Photo("iPhone", 20, 30, "11/08/2023-8:25");
		Photo photo2 = new Photo("iPhone", 20, 30, "11/08/2023-8:25");
		int result = photo1.compareTo(photo2);
		assertEquals(0, result);
	}

	@Test
	public void testPhotoCompareTo2() {
		Photo photo1 = new Photo("iPhone", 20, 30, "11/08/2023-8:25");
		Photo photo2 = new Photo("iPhone", 20, 30, "11/28/2023-8:25");
		int result = photo1.compareTo(photo2);
		assertEquals(-1, result);
	}

	@Test
	public void testPhotoManagerToString() {
		PhotoManager photoManager = new PhotoManager();
		photoManager.addPhoto("iPhone", 20, 30, "11/08/2023-8:25");
		photoManager.addPhoto("iPhone", 20, 30, "11/08/2023-8:25");
		String result = photoManager.toString();
		String expectedResult = "iPhone,20,30,11/08/2023-8:25\niPhone,20,30,11/08/2023-8:25";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testPhotoManagerAddPhoto1() {
		PhotoManager photoManager = new PhotoManager();
		boolean added = photoManager.addPhoto("Hello", 300, 400, "09/17/2020-17:10");
		assertTrue(added);
	}

	@Test
	public void testPhotoManagerAddPhoto2() {
		PhotoManager photoManager = new PhotoManager();
		boolean added = photoManager.addPhoto(null, 300, 400, "09/17/2020-17:10");
		assertFalse(added);
	}

	@Test
	public void testPhotoManagerFindPhoto() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		photoManager.addPhoto("Android", 100, 300, "11/12/1998-12:53");
		int found = photoManager.findPhoto("iPhone");
		assertEquals(1, found);
	}

	@Test
	public void testPhotoManagerAddComment() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		photoManager.addPhoto("Android", 100, 300, "11/12/1998-12:53");
		boolean addComment = photoManager.addComment("iPhone", "Cool picture");
		assertTrue(addComment);
	}

	@Test
	public void testPhotoManagerGetComment() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		photoManager.addPhoto("Android", 100, 300, "11/12/1998-12:53");
		photoManager.addComment("iPhone", "Cool picture");
		String comment = photoManager.getComments("iPhone");
		String expectedResult = "Cool picture";
		assertEquals(expectedResult, comment);
	}

	@Test
	public void testPhotoManagerRemovePhoto1() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		photoManager.addPhoto("Android", 100, 300, "11/12/1998-12:53");
		boolean success = photoManager.removePhoto("Android");
		assertTrue(success);
	}

	@Test
	public void testPhotoManagerRemovePhoto2() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		boolean failure = photoManager.removePhoto("Android");
		assertFalse(failure);
	}

	@Test
	public void testPhotoMangerLoadPhotos() {
		String filename = null;
		PhotoManager photoManager = new PhotoManager();
		photoManager.loadPhotos(filename);

	}

	@Test
	public void testPhotoManagerSortPhotosByDate() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		photoManager.addPhoto("Android", 100, 300, "11/12/1998-12:53");
		photoManager.sortPhotosByDate();

		String answer = photoManager.toString();
		String expectedResult = "iPhone,150,200,02/12/1998-02:53\n" 
		+ "Android,100,300,11/12/1998-12:53\n"
		+ "UMCP,100,300,12/12/1998-12:53";
		assertEquals (expectedResult, answer);
	}
	
	@Test
	public void testPhotoManagerRemoveAllPhotos() {
		PhotoManager photoManager = new PhotoManager();

		photoManager.addPhoto("UMCP", 100, 300, "12/12/1998-12:53");
		photoManager.addPhoto("iPhone", 150, 200, "02/12/1998-02:53");
		photoManager.addPhoto("Android", 100, 300, "11/12/1998-12:53");
		photoManager.removeAllPhotos();
		String answer = photoManager.toString();
		String expectedResult = "";
		assertEquals (expectedResult, answer);
	}
	
}