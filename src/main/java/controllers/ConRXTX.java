/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

/**
 *
 * @author geova
 */
public class ConRXTX extends Thread{
    //Todo el codigo referente a la escucha y escritura de datos con Arduino
    private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    
    private final SerialPortEventListener listener= new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };
    

    public ConRXTX() throws ArduinoException {
        arduino.arduinoRXTX("COM3", 9600,listener );
    }
    
    
    
}
