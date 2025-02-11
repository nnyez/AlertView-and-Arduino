/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author geova
 */
//Conexion Arduino-Base de datos
public class DriverController {

    public static void sentMsg(String option) {
        switch (option) {
            case "pass1":
                msgPassOne();
                break;

            case "pass2":
                msgPassTwo();

                break;
            case "card1":
                msgCardOne();
                break;

            case "card2":
                msgCardTwo();
                break;

            default:
                throw new AssertionError();
        }
    }

    private static void msgPassOne() {

    }

    private static void msgPassTwo() {

    }

    private static void msgCardOne() {

    }

    private static void msgCardTwo() {

    }
}
