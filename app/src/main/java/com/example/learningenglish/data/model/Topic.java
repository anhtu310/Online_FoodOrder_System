package com.example.learningenglish.data.model;

import java.util.List;

public class Topic {

    private int id ;
    private String name ;
    private String imageUrl;
    private List<Vocab> vocabList;
    public String getName(){
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setVocabList(List<Vocab> vocabList) {
        this.vocabList = vocabList;
    }

    public List<Vocab> getVocabList() {
        return vocabList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Topic(int id, String name, String imageUrl){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
