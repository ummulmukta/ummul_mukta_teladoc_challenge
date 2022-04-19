package com.challenge.test;

import static io.restassured.RestAssured.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetMovieTitle {

	public static void main(String[] args) {
		ArrayList<String> movieTitlesComplete = new ArrayList<String>();
		ArrayList<String> movieTitlesPartial = new ArrayList<String>();

		Response response;

		// Define Resource

		RestAssured.baseURI = "https://jsonmock.hackerrank.com/api/movies/search/?Title=";

		// Opens Scanner utility to get user input
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter movie name you want to look for...");
		String movieName = sc.next();
		// Close Scanner
		sc.close();

		// Extracts JSON response

		response = when().get(RestAssured.baseURI + movieName);
		System.out.println(response.getBody().prettyPrint());
		System.out.println(response.getStatusCode());

		// converts the response to a String
		String res = response.asString();

		// Retrieve total number of pages which must be queried to get all the results.
		JsonPath js = new JsonPath(res);

		int totalPages = Integer.parseInt(js.getString("total_pages"));
		
		// Iterate the number of pages and add the movie titles in an ArrayList

		for (int a = 1; a <= totalPages; a++) {

			res = when().get(RestAssured.baseURI + movieName + "&page=" + a).then().extract().asString();

			js = new JsonPath(res);

			movieTitlesPartial = js.get("data.Title");

			movieTitlesComplete.addAll(movieTitlesPartial);

		}

		Collections.sort(movieTitlesComplete);
		
		System.out.println(" Total number of titles found  " + movieTitlesComplete.size());
		
		for (String str : movieTitlesComplete)
			System.out.println("Title is:   " + str);

	}

}
