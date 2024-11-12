/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.PlainDocument;

public class ConfigureDateField {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void configureDateField(JFormattedTextField field) {
        field.setDocument(new PlainDocument());

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                String text = field.getText();
                if (!text.isEmpty()) {
                    try {
                        LocalDate.parse(text, formatter); // Validar a data
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Data inválida. Use o formato DD/MM/AAAA.");
                        field.requestFocus();
                    }
                }
            }
        });
    }

    public static String formatDate(LocalDate date) {
        return date != null ? date.format(formatter) : "";
    }

    public static LocalDate parseDate(String text) {
        try {
            return LocalDate.parse(text, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}