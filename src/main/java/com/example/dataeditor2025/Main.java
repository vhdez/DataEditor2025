package com.example.dataeditor2025;

public class Main {
    public static void main(String[] args) throws Exception {
        Film.readFilmData();
        Video.readVideosData();

        // print all Objects in that ArrayList
        System.out.println("");
        System.out.println("ALL MOVIES: ");
        for (Movie eachMovie : Movie.getAllMovies()) {
            System.out.println(eachMovie);
        }

        System.out.println("");
        System.out.println("ONLY FILMS: ");
        for (Film eachFilm : Film.getAllFilms()) {
            System.out.println(eachFilm);
        }

        System.out.println("");
        System.out.println("ONLY VIDEOS: ");
        for (Video eachVideo : Video.getAllVideos()) {
            System.out.println(eachVideo);
        }
    }
}