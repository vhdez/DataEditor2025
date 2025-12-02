package com.example.dataeditor2025;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Video extends Movie {
    private int allSales;
    private int vhsSales;
    private int dvdSales;
    private int bluraySales;
    private static ArrayList<Video> allVideos = new ArrayList<Video>();

    public Video(int rank, String title, long revenue, LocalDate releaseDate, int allSales, int vhsSales, int dvdSales, int bluraySales) {
        super(rank, title, revenue, releaseDate);
        this.allSales = allSales;
        this.vhsSales = vhsSales;
        this.dvdSales = dvdSales;
        this.bluraySales = bluraySales;
        allVideos.add(this);
    }

    public static ArrayList<Video> getAllVideos() {
        return allVideos;
    }

    public static void setAllVideos(ArrayList<Video> allVideos) {
        Video.allVideos = allVideos;
    }

    public int getAllSales() {
        return allSales;
    }

    public void setAllSales(int allSales) {
        this.allSales = allSales;
    }

    public int getVhsSales() {
        return vhsSales;
    }

    public void setVhsSales(int vhsSales) {
        this.vhsSales = vhsSales;
    }

    public int getDvdSales() {
        return dvdSales;
    }

    public void setDvdSales(int dvdSales) {
        this.dvdSales = dvdSales;
    }

    public int getBluraySales() {
        return bluraySales;
    }

    public void setBluraySales(int bluraySales) {
        this.bluraySales = bluraySales;
    }

    public String toString() {
        String superString = super.toString();
        String newString = "Video " + superString;
        newString += ", selling " + getAllSales() + " total copies (";
        newString += getVhsSales() + " on vhs + ";
        newString += getDvdSales() + " on dvd + ";
        newString += getBluraySales() + " on bluray)";
        return newString;
    }

    static void readVideosData() throws Exception {
        File myData = new File("src/VideosData");
        Scanner myReader = new Scanner(myData);
        int currentRank = 1;
        while (myReader.hasNextLine()) {
            int rank = currentRank;
            currentRank = currentRank + 1;

            // The Lion King
            String titleLine = myReader.nextLine();
            System.out.println(titleLine);
            String title = titleLine;

            // March 3, 1995	48,500,000	32,000,000[1]	11,900,000 [2]	3,680,000[3]	$852,000,000[4][2][3]
            String dataLine = myReader.nextLine();
            System.out.println(dataLine);
            Scanner lineScanner = new Scanner(dataLine);
            lineScanner.useDelimiter("\t");

            String dateString = lineScanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, uuuu", Locale.US);
            LocalDate video1LocalDate = LocalDate.parse(dateString, formatter);

            String allSalesChunk = lineScanner.next();
            allSalesChunk = allSalesChunk.replace(",", "");
            int allSales = Integer.parseInt(allSalesChunk);

            String vhsSalesChunk = lineScanner.next();
            int vhsSales = 0;
            if (!vhsSalesChunk.contains("—")) {
                vhsSalesChunk = vhsSalesChunk.replace(",", "");
                int bracketCharacterLocation1 = vhsSalesChunk.indexOf("[");
                if (bracketCharacterLocation1 > 0) {
                    vhsSalesChunk = vhsSalesChunk.substring(0, bracketCharacterLocation1 - 1);
                }
                vhsSales = Integer.parseInt(vhsSalesChunk);
            }

            String dvdSalesChunk = lineScanner.next();
            dvdSalesChunk = dvdSalesChunk.replace(",", "");
            int bracketCharacterLocation2 = dvdSalesChunk.indexOf("[");
            dvdSalesChunk = dvdSalesChunk.substring(0, bracketCharacterLocation2-1);
            int dvdSales = Integer.parseInt(dvdSalesChunk);

            String bluraySalesChunk = lineScanner.next();
            int bluraySales = 0;
            if (!bluraySalesChunk.contains("—")) {
                bluraySalesChunk = bluraySalesChunk.replace(",", "");
                int bracketCharacterLocation3 = bluraySalesChunk.indexOf("[");
                bluraySalesChunk = bluraySalesChunk.substring(0, bracketCharacterLocation3-1);
                bluraySales = Integer.parseInt(bluraySalesChunk);
            }

            String revenueChunk = lineScanner.next();
            revenueChunk = revenueChunk.replace("$", "");
            revenueChunk = revenueChunk.replace(",", "");
            int bracketCharacterLocation4 = revenueChunk.indexOf("[");
            revenueChunk = revenueChunk.substring(0, bracketCharacterLocation4-1);
            long revenue = Long.parseLong(revenueChunk);

            // create a Video object
            new Video(rank, title, revenue, video1LocalDate, allSales, vhsSales, dvdSales, bluraySales);
        }
    }
}
