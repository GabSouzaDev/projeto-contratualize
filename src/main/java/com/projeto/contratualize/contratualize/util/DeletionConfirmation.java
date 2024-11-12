/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.util;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author gabri
 */
public class DeletionConfirmation {
    
    
    public static boolean userConfirmDeletion(String username) {
        String randomString = generateRandomString(8);
        
        String confirmationInput = JOptionPane.showInputDialog(null,
                "Você tem certeza que deseja excluir o usuário " + username + 
                "? Este processo é irreversível!\n" +
                "Digite os seguintes caracteres se deseja confirmar: " + randomString,
                "Confirmar Exclusão", JOptionPane.QUESTION_MESSAGE);
        
        if(confirmationInput == null || confirmationInput.isEmpty()) {
            return false; // cancela a operação
            
        }
        
        return confirmationInput.equals(randomString);
    }
    
    public static boolean contractConfirmDeletion(String contractNumber) {
        String randomString = generateRandomString(8);
        
        String confirmationInput = JOptionPane.showInputDialog(null,
                "Você tem certeza que deseja excluir o contrato " + contractNumber + 
                "? Este processo é irreversível!\n" +
                "Digite os seguintes caracteres se deseja confirmar: " + randomString,
                "Confirmar Exclusão", JOptionPane.QUESTION_MESSAGE);
        
        if(confirmationInput == null || confirmationInput.isEmpty()) {
            return false; // cancela a operação
            
        }
        
        return confirmationInput.equals(randomString);
    }
    
    
    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
