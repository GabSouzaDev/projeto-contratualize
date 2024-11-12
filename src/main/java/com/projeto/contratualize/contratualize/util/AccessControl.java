/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.util;

import com.projeto.contratualize.contratualize.model.User;

/**
 * Controle de acesso do administrador
 * @author gabri
 */

public class AccessControl {
    //remover contratos
    public static boolean canRemoveContract(User user) {
        return user.getRole() == User.Role.ADMIN;
        
    }
    //Editar contratos
    public static boolean canEditContract(User user) {
        return user.getRole() == User.Role.ADMIN;
 
    }
    
    //Criar novos usuárioss
    public static boolean canCreateUser(User user) {
        return user.getRole() == User.Role.ADMIN;
        
    }
    
    
    
}
