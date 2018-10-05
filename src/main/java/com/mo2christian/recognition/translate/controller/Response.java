package com.mo2christian.recognition.translate.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * Objet encapsulant la réponse de la traduction.
 */
public class Response {

    private String language;

    private List<String> words;

    public Response(){
        words = new LinkedList<>();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

}
