package com.example.quran;

public class Surah {
    private int number;
    private String name;
    private String arabicName;
    private int numberOfAyahs;
    private String text;

    public Surah(int number, String name, String arabicName, int numberOfAyahs, String text) {
        this.number = number;
        this.name = name;
        this.arabicName = arabicName;
        this.numberOfAyahs = numberOfAyahs;
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getArabicName() {
        return arabicName;
    }

    public int getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public String getText() {
        return text;
    }
}