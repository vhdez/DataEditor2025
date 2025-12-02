package com.example.dataeditor2025;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Film extends Movie {
    private int peak;
    private static ArrayList<Film> allFilms = new ArrayList<Film>();

    public Film(int rank, String title, long revenue, LocalDate releaseDate, int peak) {
        super(rank, title, revenue, releaseDate);
        this.peak = peak;
        allFilms.add(this);
    }

    public static ArrayList<Film> getAllFilms() {
        return allFilms;
    }

    public static void setAllFilms(ArrayList<Film> allFilms) {
        Film.allFilms = allFilms;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public String toString() {
        String superString = super.toString();
        String newString = "Film " + superString + ", peaking at #" + getPeak();
        return newString;
    }

    public static void readFilmData() throws Exception {
        File myData = new File("src/FilmsData");
        Scanner myReader = new Scanner(myData);
        String firstLine = myReader.nextLine();
        while (myReader.hasNextLine()) {
            String dataLine = myReader.nextLine();
            System.out.println(dataLine);

            Scanner lineScanner = new Scanner(dataLine);
            lineScanner.useDelimiter("\t");
            // 1	1	Avatar	$2,923,710,708	2009	[# 1][# 2]

            int rank = lineScanner.nextInt();

            // 33	24RK	Spider-Man: Far From Home	$1,132,679,685	2019	[# 58][# 4]
            String peakChunk = lineScanner.next();
            peakChunk = peakChunk.replace("RK", "");
            peakChunk = peakChunk.replace("TS", "");
            int peak = Integer.parseInt(peakChunk);

            String title = lineScanner.next();

            String revenueChunk = lineScanner.next();
            revenueChunk = revenueChunk.replace("$","");
            revenueChunk = revenueChunk.replace(",","");
            //4	1	Titanic	T$2,257,906,828	1997	[# 7][# 8]
            revenueChunk = revenueChunk.replace("T","");
            // 8	6	Spider-Man: No Way Home	SM$1,922,598,800	2021	[# 15][# 16]
            revenueChunk = revenueChunk.replace("SM","");
            //24	5	Frozen	F$1,290,000,000	2013	[# 44][# 45]
            revenueChunk = revenueChunk.replace("F","");
            //38	7	The Dark Knight Rises	DKR$1,081,169,825	2012	[# 66][# 67]
            revenueChunk = revenueChunk.replace("DKR","");
            long revenue = Long.parseLong(revenueChunk);

            int year = lineScanner.nextInt();
            LocalDate film1LocalDate = LocalDate.of(year, 1, 1);
            // create each Film object
            new Film(rank, title, revenue, film1LocalDate, peak);
        }
    }
}
