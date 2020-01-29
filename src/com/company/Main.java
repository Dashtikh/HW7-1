package com.company;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //insert
        try {
            CarServ.getInstance().save(new CarEnti().setmodel(1).setName("pride").setprice(1000));
            System.out.println("saved successfully");
        } catch (Exception e) {
            System.out.println("Fail to save!" + e.getMessage());
        }


    }
}
