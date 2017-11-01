package com.phuoctran.fitme.Models;

import com.phuoctran.fitme.Models.ChartDetail;

import java.util.ArrayList;

/**
 * Created by VN-PC on 2017/05/04.
 */

public class ChartConstants
{
    public static ArrayList<ChartDetail> lstTopsMale;
    public static ArrayList<ChartDetail> lstTopsFemale;
    public static ArrayList<ChartDetail> lstBottomsMale;
    public static ArrayList<ChartDetail> lstBottomsFemale;
    public static ArrayList<ChartDetail> lstHat;
    public static ArrayList<ChartDetail> lstRing;
    public static ArrayList<ChartDetail> lstShoesMale;
    public static ArrayList<ChartDetail> lstShoesFemale;
    
    public static void setData(){
        lstTopsMale = new ArrayList();
        lstTopsMale.add(new ChartDetail("XS", "34", "34", "42", "85", "36"));
        lstTopsMale.add(new ChartDetail("S", "36", "36", "46", "90", "38"));
        lstTopsMale.add(new ChartDetail("M", "38", "38", "48", "95", "40"));
        lstTopsMale.add(new ChartDetail("L", "40", "40", "50", "100", "42"));
        lstTopsMale.add(new ChartDetail("XL", "42", "42", "52", "105", "44"));
        lstTopsMale.add(new ChartDetail("XXL", "44", "44", "54", "110", "46"));

        lstTopsFemale = new ArrayList();
        lstTopsFemale.add(new ChartDetail("XXS", "0", "4", "34", "44 (80)", "5"));
        lstTopsFemale.add(new ChartDetail("XS", "2", "6", "36", "55 (85)", "7"));
        lstTopsFemale.add(new ChartDetail("S", "4", "8", "38", "55 (90)", "9"));
        lstTopsFemale.add(new ChartDetail("M", "6", "10", "40", "66 (95)", "11"));
        lstTopsFemale.add(new ChartDetail("L", "8", "12", "42", "66 (100)", "13"));
        lstTopsFemale.add(new ChartDetail("XL", "10", "14", "44", "77 (105)", "15"));

        lstBottomsMale = new ArrayList();
        lstBottomsMale.add(new ChartDetail("XS", "34", "34", "44", "28", "76"));
        lstBottomsMale.add(new ChartDetail("S", "36", "36", "46", "30", "81"));
        lstBottomsMale.add(new ChartDetail("M", "38", "38", "48", "32", "86"));
        lstBottomsMale.add(new ChartDetail("L", "40", "40", "50", "34", "91"));
        lstBottomsMale.add(new ChartDetail("XL", "42", "42", "52", "36", "96"));
        lstBottomsMale.add(new ChartDetail("XXL", "44", "44", "54", "38", "101"));

        lstBottomsFemale = new ArrayList();
        lstBottomsFemale.add(new ChartDetail("XS", "2", "6", "34", "25", "5"));
        lstBottomsFemale.add(new ChartDetail("S", "4", "8", "36", "27", "7"));
        lstBottomsFemale.add(new ChartDetail("M", "6", "10", "38", "29", "9"));
        lstBottomsFemale.add(new ChartDetail("L", "10", "12", "42", "32", "13"));
        lstBottomsFemale.add(new ChartDetail("XL", "14", "14", "46", "34", "17"));

        lstHat = new ArrayList();
        lstHat.add(new ChartDetail("S", "6 3/4", "6 5/8"));
        lstHat.add(new ChartDetail("S/M", "6 7/8", "6 3/4"));
        lstHat.add(new ChartDetail("M", "7", "6 7/8"));
        lstHat.add(new ChartDetail("M/L", "7 1/8", "7"));
        lstHat.add(new ChartDetail("L", "7 1/4", "7 1/8"));
        lstHat.add(new ChartDetail("L/XL", "7 3/8", "7 1/4"));
        lstHat.add(new ChartDetail("XL", "7 1/2", "7 3/8"));
        lstHat.add(new ChartDetail("XL/XXL", "7 5/8", "7 1/2"));
        lstHat.add(new ChartDetail("XXL", "7 3/4", "7 5/8"));

        lstRing = new ArrayList();
        lstRing.add(new ChartDetail("4.75", "3", "F 1/2", "5", "5"));
        lstRing.add(new ChartDetail("6", "3.5", "G 1/2", "6", "6"));
        lstRing.add(new ChartDetail("7.5", "4", "H 1/2", "7", "7"));
        lstRing.add(new ChartDetail("8", "4.5", "I", "8", "8"));
        lstRing.add(new ChartDetail("9.25", "5", "J 1/2", "9", "9"));
        lstRing.add(new ChartDetail("10.25", "5.5", "K 1/2", "10", "10"));
        lstRing.add(new ChartDetail("11.25", "6", "L", "11", "11"));
        lstRing.add(new ChartDetail("12.5", "6.5", "M", "12", "12"));
        lstRing.add(new ChartDetail("13.25", "6.5", "M 1/2", "13", "13"));
        lstRing.add(new ChartDetail("14.5", "7", "N 1/2", "14", "14"));
        lstRing.add(new ChartDetail("15.75", "7", "O 1/2", "15", "15"));
        lstRing.add(new ChartDetail("16.25", "7.5", "P", "16", "16"));
        lstRing.add(new ChartDetail("17.5", "8", "Q", "17", "17"));
        lstRing.add(new ChartDetail("19", "8.5", "R", "18", "18"));
        lstRing.add(new ChartDetail("19.5", "9", "R 1/2", "19", "19"));
        lstRing.add(new ChartDetail("20.75", "9.5", "S 1/2", "20", "20"));
        lstRing.add(new ChartDetail("22", "10", "T 1/2", "21", "21"));
        lstRing.add(new ChartDetail("22.75", "10.5", "U", "22", "22"));
        lstRing.add(new ChartDetail("24", "10.5", "V", "23", "23"));
        lstRing.add(new ChartDetail("24.75", "11", "V 1/2", "24", "24"));
        lstRing.add(new ChartDetail("26", "11.5", "W 1/2", "25", "25"));
        lstRing.add(new ChartDetail("27.25", "12", "X 1/2", "26", "26"));

        lstShoesMale = new ArrayList();
        lstShoesMale.add(new ChartDetail("39.5", "6.5", "5.5", "245", "24.5"));
        lstShoesMale.add(new ChartDetail("40", "7", "6", "250", "25"));
        lstShoesMale.add(new ChartDetail("40.5", "7.5", "6.5", "255", "25.5"));
        lstShoesMale.add(new ChartDetail("41", "8", "7", "260", "26"));
        lstShoesMale.add(new ChartDetail("41.5", "8.5", "7.5", "265", "26.5"));
        lstShoesMale.add(new ChartDetail("42", "9", "8", "270", "27"));
        lstShoesMale.add(new ChartDetail("42.5", "9.5", "8.5", "275", "27.5"));
        lstShoesMale.add(new ChartDetail("43", "10", "9", "280", "28"));
        lstShoesMale.add(new ChartDetail("43.5", "10.5", "9.5", "285", "28.5"));
        lstShoesMale.add(new ChartDetail("44", "11", "10", "290", "29"));
        lstShoesMale.add(new ChartDetail("44.5", "11.5", "10.5", "295", "29.5"));
        lstShoesMale.add(new ChartDetail("45", "12", "11", "300", "30"));

        lstShoesFemale = new ArrayList();
        lstShoesFemale.add(new ChartDetail("36", "5", "2", "220", "21"));
        lstShoesFemale.add(new ChartDetail("36.5", "5.5", "2.5", "225", "21.5"));
        lstShoesFemale.add(new ChartDetail("37", "6", "3", "230", "22"));
        lstShoesFemale.add(new ChartDetail("37.5", "6.5", "3.5", "235", "22.5"));
        lstShoesFemale.add(new ChartDetail("38", "7", "4", "240", "23"));
        lstShoesFemale.add(new ChartDetail("38.5", "7.5", "4.5", "245", "23.5"));
        lstShoesFemale.add(new ChartDetail("39", "8", "5", "250", "24"));
        lstShoesFemale.add(new ChartDetail("39.5", "8.5", "5.5", "255", "24.5"));
        lstShoesFemale.add(new ChartDetail("40", "9", "6", "260", "25"));
        lstShoesFemale.add(new ChartDetail("40.5", "9.5", "6.5", "265", "25.5"));
        lstShoesFemale.add(new ChartDetail("41", "10", "7", "270", "26"));
    }
}
