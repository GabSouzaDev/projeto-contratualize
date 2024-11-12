/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import javax.swing.JOptionPane;

/**
 * Envia e-mails e exibe pop-ups para alertas.
 * @author gabri
 */
public class AlertService {
    
    public void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Alerta de Prescrição", JOptionPane.INFORMATION_MESSAGE);
    }
   
    
}
