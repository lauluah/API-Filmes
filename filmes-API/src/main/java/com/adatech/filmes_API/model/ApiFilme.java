package com.adatech.filmes_API.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "FILME_API_RESPONSE")
public class ApiFilme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "OVERVIEW", nullable = false)
    private String overview;

    @Column(name = "RELEASE_DATE", nullable = false)
    private String releaseDate;

    @Column(name = "RUNTIME", nullable = false)
    private int runtime;

//    @ElementCollection
//    @CollectionTable(name = "GENRES", joinColumns = @JoinColumn(name = "FILME_API_RESPONSE_ID"))
//    @Column(name = "GENRE")
//    private List<String> genres;

    @Column(name = "POPULARITY", nullable = false)
    private double popularity;

    @Column(name = "ORIGINAL_LANGUAGE", nullable = false)
    private String originalLanguage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

//    public List<String> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<String> genres) {
//        this.genres = genres;
//    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
}