package com.adatech.filmes_API.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FilmeApiResponseDTO {
    private String title;
    private String overview;
    private String release_date;
    private int runtime;
    private List<Genre> genres;
    private double popularity;
    private String original_language;

    public FilmeApiResponseDTO() {
    }

    public FilmeApiResponseDTO(String title, String release_date, String overview, int runtime, List<Genre> genres,
                               double popularity, String original_language) {
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.runtime = runtime;
        this.genres = genres;
        this.popularity = popularity;
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public static class Genre {
        private int id;
        private String name;

        public Genre() {
        }

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}