package com.mycompany.app;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        BikeDAO bikeDAO = new BikeDAO();
        System.out.println(bikeDAO.getTableValues("cromdale"));
    }
}




