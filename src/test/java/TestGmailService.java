/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.projeto.contratualize.contratualize.service.GmailService;
/**
 *
 * @author gabri
 */
public class TestGmailService {
    
    public static void main(String[] args) {
        try {
            GmailService gmailService = new GmailService();
            gmailService.sendEmail("gabrielfreelasouza@gmail.com", "Teste de envio", "Contratualize");
            System.out.println("Email enviado com sucesso!");
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
}
