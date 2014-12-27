package com.springapp.rabbis.toolkit;

/**
 * Created by home-lt on 01/12/14.
 */
public class Toolkit {

    public static void main(String[] args){
        Toolkit hebrewToGeorgian = new Toolkit();
        String yearToGeorgian = hebrewToGeorgian.convertHebrewYearToGeorgian("התשמב");
        System.out.println(yearToGeorgian);
        String year = hebrewToGeorgian.formatHebrewYear("התשמב");
        System.out.println(year);
        System.out.println(hebrewToGeorgian.formatHebrewYearSimple(year));

        yearToGeorgian = hebrewToGeorgian.convertHebrewYearToGeorgian("דתשמב");
        System.out.println(yearToGeorgian);
        year = hebrewToGeorgian.formatHebrewYear("דתשמב");
        System.out.println(year);
        System.out.println(hebrewToGeorgian.formatHebrewYearSimple(year));
        year = "ד' תשמ\"ב";
        System.out.println(hebrewToGeorgian.formatHebrewYearSimple(year));

        System.out.println(removeBracketsContent("efeewfewf(efef)"));
    }

    public static String removeBracketsContent(String str){
        str = str.replaceAll("\\(.*\\)", "");
        return str;

    }

    public String formatHebrewYear(String hebrewYear){
        if (hebrewYear.contains("'")) return hebrewYear;
        int length = hebrewYear.length();
        String year;
        if (length==2){
            year = hebrewYear.substring(0,1).concat("' ").concat(hebrewYear.substring(1, length)).concat("'");
        } else {
            year = hebrewYear.substring(0,1).concat("' ").concat(hebrewYear.substring(1, length - 1)).concat("\"").concat(hebrewYear.substring(length - 1, length));
        }

        return year;
    }

    public String formatHebrewYearSimple(String hebrewYear){
        String year = hebrewYear.replace("\"", "").replace("'","").replace(" ","");
        return year;
    }
    public String convertHebrewYearToGeorgian(String hebrewYear){
        int gematria = getGematria(formatHebrewYearSimple(hebrewYear));
        return String.valueOf(gematria - 3760);
    }

    private int getGematria(String hebrewdate){
        int georgian;
        char[] date = hebrewdate.toCharArray();
        if (date[0] == 'ה'){
            georgian = 5000;
        } else {
            georgian = 4000;
        }
        for (int i = 1 ;i < date.length; i++){
            georgian += getCharVal(date[i]);
        }
        return georgian;
    }

    private int getCharVal(char letter) {
        switch(letter) {
            case 'א': return 1;
            case 'ב': return 2;
            case 'ג': return 3;
            case 'ד': return 4;
            case 'ה': return 5;
            case 'ו': return 6;
            case 'ז': return 7;
            case 'ח': return 8;
            case 'ט': return 9;
            case 'י': return 10;
            case 'כ':
            case 'ך': return 20;
            case 'ל': return 30;
            case 'מ':
            case 'ם': return 40;
            case 'נ':
            case 'ן': return 50;
            case 'ס': return 60;
            case 'ע': return 70;
            case 'פ':
            case 'ף': return 80;
            case 'צ':
            case 'ץ': return 90;
            case 'ק': return 100;
            case 'ר': return 200;
            case 'ש': return 300;
            case 'ת': return 400;
            default:  return 0;
        }
    }
}
