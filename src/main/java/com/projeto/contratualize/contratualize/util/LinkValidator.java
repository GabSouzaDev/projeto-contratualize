/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.util;

import java.util.regex.Pattern;

/**
 *
 * @author gabri
 */
public class LinkValidator {
    private static final String LINK_REGEX = "https?://[^\\s]+(\\.pdf|\\.jpg|\\.jpeg|\\.png)$";
    private static final Pattern LINK_PATTERN = Pattern.compile(LINK_REGEX);
    
    public static boolean isValid(String link) {
        if(link == null || link.isEmpty()) {
            return false;
        }
        return LINK_PATTERN.matcher(link).matches();
    }
    
    
}
