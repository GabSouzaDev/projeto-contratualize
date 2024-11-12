/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.service;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author gabri
 */
public class GmailService {
    private static final String APPLICATION_NAME = "API GMAIL";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/gmail.send");
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    
    private Gmail service;
    
    public GmailService() throws IOException, GeneralSecurityException {
        Credential credential = getCredentials();        
        service = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
    
    private Credential getCredentials() throws IOException, GeneralSecurityException {
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))).setAccessType("offline").build();
        
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8890).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    
    //Enviar e-mail
   public void sendEmail(String to, String subject, String bodyText) throws Exception {
       MimeMessage email = createEmail(to, "contratualizesystem@gmail.com", subject, bodyText);
       Message message = createMessageWithEmail(email);
       service.users().messages().send("me", message).execute();
   }
   
   private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
       MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
       email.setFrom(new InternetAddress(from));
       email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
       email.setSubject(subject);
       email.setText(bodyText);
       return email;
   }
   
   private Message createMessageWithEmail(MimeMessage email) throws IOException, MessagingException {
       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
       email.writeTo(buffer);
       byte[] bytes = buffer.toByteArray();
       String encodedEmail = Base64.getUrlEncoder().encodeToString(bytes);
       Message message = new Message();
       message.setRaw(encodedEmail);
       return message;
   }
   
   
}
