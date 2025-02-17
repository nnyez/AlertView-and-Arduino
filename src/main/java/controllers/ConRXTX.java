/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import models.AlertCard;
import models.AlertPassword;
import models.IAlert;
import models.User;
import repositories.AlertRepository;
import repositories.MailAddressRepository;

/**
 *
 * @author geova
 */
public class ConRXTX extends Thread {

    private final AlertRepository repository;
    private final MailAddressRepository mailRepository;

    // Arduino
    private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    private String msg = "";
    // GetMsg
    PanamaHitek_MultiMessage multi = new PanamaHitek_MultiMessage(1, arduino);
    private final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (multi.dataReceptionCompleted()) {
                    msg = multi.getMessage(0);
                    sendMsg();

                }
                multi.flushBuffer();
            } catch (ArduinoException ex) {
                Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SerialPortException ex) {
                Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    private void sendMsg() {
        System.out.println(msg);

        String[] msgs = msg.split("\s+");

        switch (msgs[0]) {
            case "Card":
                verifyCard(msgs);
                break;
            case "Pass":
                verifyPass(msgs);
                break;
            case "!Pass":
                verifyAPass(msgs);
                break;

            default:
                msgs = new String[] { msgs[0], msgs[1] + " " + msgs[2] + " " + msgs[3] + " " + msgs[4], msgs[5] };
                if (msgs[0].startsWith("T")) {
                    cardSendAlert(msgs);
                } else if (msgs[0].startsWith("A")) {
                    passSendAlert(msgs);
                }
        }
    }

    public void verifyCard(String[] msgs) {
        List<User> users = repository.getUsers();
        try {

            for (User user : users) {
                boolean t = user.verifyCardCode(msgs);
                System.out.println(t);

                if (t) {
                    arduino.sendData(t + "");
                    return;
                }

            }
            arduino.sendData(false + "");
        } catch (ArduinoException ex) {
            Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verifyPass(String[] msgs) {
        List<User> users = repository.getUsers();
        try {

            for (User user : users) {
                boolean t = user.verifyPassCode(msgs);
                System.out.println(t);

                if (t) {
                    arduino.sendData(t + "");
                    return;
                }

            }
            arduino.sendData(false + "");
        } catch (ArduinoException ex) {
            Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verifyAPass(String[] msgs) {
        List<User> users = repository.getUser(1);
        try {

            for (User user : users) {
                boolean t = user.verifyPassCode(msgs);
                if (t) {
                    System.out.println(t);
                    arduino.sendData("ok");
                    return;

                }

            }
            arduino.sendData(false + "");
        } catch (ArduinoException ex) {
            Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(ConRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cardSendAlert(String[] msgs) {
        AlertCard a;
        switch (msgs[0]) {
            case "T1":
                a = new AlertCard("Tarjeta No Permitida", "Baja", LocalTime.now(), LocalDate.now(), msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertCard(a);
                break;
            case "T2":
                a = new AlertCard("Tarjeta No Permitida", "Media", LocalTime.now(), LocalDate.now(), msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertCard(a);

                break;
            case "T3":
                a = new AlertCard("Modo alerta por superar el número de validaciones permitidas de tarjeta", "Alta",
                        LocalTime.now(), LocalDate.now(), msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertCard(a);

                break;
            default:
                a = new AlertCard("Alerta, violacion de Seguridad", "Extrema", LocalTime.now(), LocalDate.now(),
                        msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertCard(a);

        }
        sendEmail(a);

    }

    private void passSendAlert(String[] msgs) {
        AlertPassword a;
        switch (msgs[0]) {
            case "A1":
                a = new AlertPassword("Intento fallido", "Baja", LocalTime.now(), LocalDate.now(), msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertPass(a);
                break;
            case "A2":
                a = new AlertPassword("Intento fallido", "Media", LocalTime.now(), LocalDate.now(), msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertPass(a);

                break;
            case "A3":
                a = new AlertPassword("Modo alerta por superar el limite de intentos fallidos", "Alta", LocalTime.now(),
                        LocalDate.now(), msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertPass(a);

                break;
            default:
                a = new AlertPassword("Alerta, violacion de Seguridad", "Extrema", LocalTime.now(), LocalDate.now(),
                        msgs[1],
                        Byte.parseByte(msgs[2]));
                repository.insertAlertPass(a);

        }
        sendEmail(a);
    }

    private void sendEmail(IAlert alert) {
        List<User> users = repository.getUsers();
        for (User user : users) {
            if (user.getMail() != null) {
                mailRepository.sendEmail(user.getMail(), alert);
            }
        }
    }

    public ConRXTX(AlertRepository repository, MailAddressRepository mailRepository) throws ArduinoException {
        this.repository = repository;
        this.mailRepository = mailRepository;
        arduino.arduinoRXTX("COM3", 9600, listener);


    }
}
