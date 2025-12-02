package com.example.dataeditor2025;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {
    private int rank;
    private String title;
    private long revenue;
    private LocalDate releaseDate;
    private static ArrayList<Movie> allMovies = new ArrayList<Movie>();

    public Movie(int rank, String title, long revenue, LocalDate releaseDate) {
        this.rank = rank;
        this.title = title;
        this.revenue = revenue;
        this.releaseDate = releaseDate;
        allMovies.add(this);
    }

    static public ArrayList<Movie> getAllMovies() {
        return allMovies;
    }

    static public void setAllMovies(ArrayList<Movie> allMovies) {
        allMovies = allMovies;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String toString() {
        String description = "Rank #" + getRank();
        description += " is \"" + getTitle();
        description += "\" earning $" + getRevenue();
        description += " starting on release date: " + getReleaseDate();
        return description;
    }
}
