
import com.projeto.contratualize.contratualize.service.AlertService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabri
 */
public class TestAlertService {
    public static void main(String[] args) {
        AlertService alertService = new AlertService();
        
        alertService.showPopup("Teste de alerta!");
    }
}
