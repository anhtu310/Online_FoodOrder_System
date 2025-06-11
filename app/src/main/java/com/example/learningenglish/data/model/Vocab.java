package com.example.learningenglish.data.model;

public class Vocab {
    private int id;
    private String word ;
    private String meaning;
    private String pronounciation ;
    private String example ;
    private String note;

    private String imageUrl;
    public Vocab(int id, String word, String meaning, String pronounciation, String example, String note, String imageUrl) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.pronounciation = pronounciation;
        this.example = example;
        this.note = note;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getExample() {
        return example;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getNote() {
        return note;
    }

    public String getPronounciation() {
        return pronounciation;
    }

    public String getWord() {
        return word;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPronounciation(String pronounciation) {
        this.pronounciation = pronounciation;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
