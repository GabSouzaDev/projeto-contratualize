/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Calcula a data de prescrição e verifica se o contrato está prescrito
 * @author gabri
 */
public class PrescriptionCalculator {
    
    public static LocalDate calculatePrescriptionDate(LocalDate startDate, int numberOfInstallments) {
        LocalDate endDate = startDate.plusMonths(numberOfInstallments);
        return endDate.plusYears(5);
    }
    
    public static boolean isPrescribed(LocalDate prescriptionDate, LocalDate currentDate) {
        return prescriptionDate.isBefore(currentDate);
    }
    
    //Calcular a diferença de tempo até a data de prescrição
    public static long getDaysUntilPrescription(LocalDate prescriptionDate, LocalDate currentDate) {
        return ChronoUnit.DAYS.between(currentDate, prescriptionDate);
    }
    
}
