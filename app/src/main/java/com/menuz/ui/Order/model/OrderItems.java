package com.menuz.ui.Order.model;

import java.util.ArrayList;

public final class OrderItems {
    public String remark="";
    public ArrayList<AddOnPrep> addOnPrep;
    public ArrayList<String> itemPrep=new ArrayList<>();

    public static class AddOnPrep{
        public String addOnName="";
        public String addOnPrep="";
        public String prefix="";
    }
}
