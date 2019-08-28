package com.example.dell;

public class input_variables {
    private static String component="";
    private static int cost=0;
    private static int cpr=0;
    private static int spr=0;
    private static String priority1;
    private static String priority2;
    private static boolean pr=false;

    public static String getComponents() {
        return component;
    }
    public static void setComponents(String components) {
        input_variables.component = components;
    }
    public static int getCost() {
        return cost;
    }
    public static void setCost(int cost) {
        input_variables.cost = cost;
    }
    public static int getCpr() {
        return cpr;
    }
    public static void setCpr(int cpr) {
        input_variables.cpr = cpr;
    }
    public static int getSpr() {
        return spr;
    }
    public static void setSpr(int spr) {
        input_variables.spr = spr;
    }
    public static String getPriority1() {
        return priority1;
    }
    public static void setPriority1(String priority1) {
        input_variables.priority1 = priority1;
    }
    public static String getPriority2() {
        return priority2;
    }
    public static void setPriority2(String priority2) {
        input_variables.priority2 = priority2;
    }
    public static boolean isPr() {
        return pr;
    }
    public static void setPr(boolean pr) {
        input_variables.pr = pr;
    }

}
