/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.projeto.contratualize.contratualize.view.ContractJInternalFrame;

import com.projeto.contratualize.contratualize.view.UserInternalFrames.*;
import com.projeto.contratualize.contratualize.dao.ContractDAO;
import com.projeto.contratualize.contratualize.model.Contract;
import com.projeto.contratualize.contratualize.util.DeletionConfirmation;
import java.awt.event.ActionListener;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author gabri
 */
public class DeleteContractScreen extends javax.swing.JInternalFrame {
    ContractDAO contractDAO = new ContractDAO();
    /**
     * Creates new form EditUserScreen
     */
    public DeleteContractScreen() {
        initComponents();
        loadContracts();
        
    }
    
   private Map<String, Long> contractMap = new HashMap<>();
    private void loadContracts() {
        List<Contract> contracts = contractDAO.findAllWithClients();
        for(Contract contract : contracts) {
            String displayText = contract.getContractNumber() + " - " + contract.getClientName(); 
            choice1.add(displayText);
            contractMap.put(displayText, contract.getId()); //ARMAZENA O ID DO CONTRATO.
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Deletar contrato"));

        jButton1.setText("DELETAR CONTRATO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Escolha o contrato");

        choice1.setPreferredSize(new java.awt.Dimension(50, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(choice1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String contractNumber = choice1.getSelectedItem();
        Long contractId = contractMap.get(contractNumber); //Obtem o ID do contrato

        
    if(contractNumber == null || contractNumber.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escolha um contrato para excluir!");
        return;
    }
    
    System.out.println("Buscando contrato com o numero: " + contractId);

    if (DeletionConfirmation.contractConfirmDeletion(contractNumber)) {
       Contract contract = contractDAO.findById(contractId);

       if (contract != null) {
            contractDAO.delete(contract);
            JOptionPane.showMessageDialog(this, "Contrato excluído com sucesso!");
            choice1.remove(contractNumber);
       } else {
           JOptionPane.showMessageDialog(this, "Contrato não encontrado.");
           
       }

    

    }


    }//GEN-LAST:event_jButton1ActionPerformed
    
    /*
    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }  
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
