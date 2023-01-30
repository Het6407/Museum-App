package com.museum.model;

@SuppressWarnings("serial")
public class MuseumNotFoundException extends RuntimeException {
    public MuseumNotFoundException(String message) {
        super(message);
    }
}