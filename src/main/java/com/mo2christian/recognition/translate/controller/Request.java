package com.mo2christian.recognition.translate.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * Objet encapsulant la requete de traduction.
 */
public class Request {

    private String from;

    private String target;

    private List<String> words;

    public Request(){
        words = new LinkedList<>();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
