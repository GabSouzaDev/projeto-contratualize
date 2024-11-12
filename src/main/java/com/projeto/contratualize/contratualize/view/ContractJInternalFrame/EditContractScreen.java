/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change th    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
is license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.projeto.contratualize.contratualize.view.ContractJInternalFrame;


import com.projeto.contratualize.contratualize.dao.ContractDAO;
import com.projeto.contratualize.contratualize.model.Contract;


import com.projeto.contratualize.contratualize.util.LinkValidator;
import com.projeto.contratualize.contratualize.util.ConfigureDateField;
import com.projeto.contratualize.contratualize.util.api.BankInfoFetcher;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



/**
 * 
 *
 * @author gabri
 */
public class EditContractScreen extends javax.swing.JInternalFrame {
    private BankInfoFetcher fetcher;
    ContractDAO contractDAO = new ContractDAO();

    /**
     * Creates new form RegisterContractScreen
     */
    
    public EditContractScreen(Contract contract) {
        initComponents();
        loadContracts(); //Carrega todos os contratos no choice
        fillFields(contract); //preenche os campos se o contrato existir no banco de dados
        fetcher = new BankInfoFetcher(); //Instancia a API uma vez
        initListeners(); //Inicia o listener para preenchimento do banco usando a API
        ConfigureDateField.configureDateField(jFormattedTextField1); // Chama o método para configurar o campo de data
        
    }
    
    public EditContractScreen() {
        initComponents();
        loadContracts();
        fetcher = new BankInfoFetcher(); //Instancia a API uma vez
        initListeners();
        ConfigureDateField.configureDateField(jFormattedTextField1);

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
    
    private void fillFields(Contract contract) {
        choice1.select(contract.getContractNumber() + " - " + contract.getClientName());
        jTextField2.setText(contract.getClientName().toUpperCase());
        jTextField3.setText(contract.getBankCode());
        jSpinner1.setValue(contract.getNumberOfInstallments());
        jFormattedTextField1.setText(ConfigureDateField.formatDate(contract.getStartDate()));
        jTextField4.setText(contract.getContractLink());
    }
    
    
    //Configurando a comunicação com a API
    private void initListeners() {
        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateBankName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateBankName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateBankName();
            }
        });
    }

   private void updateBankName() {
        String bankCodeStr = jTextField3.getText();
        if (!bankCodeStr.isEmpty()) {
            try {
                int bankCode = Integer.parseInt(bankCodeStr); // Converte para int
                String bankName = fetcher.getBankNameByCode(bankCode);
                if (bankName != null) {
                    jTextField6.setText(bankName); // Preencher o campo do nome do banco
                } else {
                    jTextField6.setText(""); // Limpar se não encontrado
                }
            } catch (NumberFormatException e) {
                jTextField6.setText(""); // Limpar se houver erro na conversão
            }
        } else {
            jTextField6.setText(""); // Limpar se o campo estiver vazio
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
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        choice1 = new java.awt.Choice();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();

        setClosable(true);
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar contrato"));

        jLabel1.setText("Numero do contrato");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome do cliente");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Código do banco");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 1));

        jLabel4.setText("Parcelas");

        jButton1.setText("EDITAR CONTRATO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        //Criando listener para que os campos sejam preenchidos ao trocar o contrato a ser editado.
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                String selectedContract = choice1.getSelectedItem();
                Long contractId = contractMap.get(selectedContract);
                Contract contract = contractDAO.findById(contractId);
                fillFields(contract);
            }
        });

        jLabel6.setText("Link de visualização do contrato");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Data da assinatura");

        jTextField6.setEditable(false);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2)
                        .addComponent(jLabel1)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addComponent(choice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String contractNumber = choice1.getSelectedItem();
        Long contractId = contractMap.get(contractNumber); //Obtem o ID do contrato
        String clientName = jTextField2.getText().toUpperCase();
        String bankCode = jTextField3.getText();
        int numberOfInstallments = (Integer) jSpinner1.getValue();
        String dateString = jFormattedTextField1.getText();
        String contractLink = jTextField4.getText();
        
        
        Contract contract = contractDAO.findById(contractId);
        
        if(contractNumber == null || contractNumber.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Escolha um contrato para editar.");
            return;
        } 
        
        System.out.println("Buscando contrato com o numero: " + contractId);
        
        
        if(contract == null) {
            JOptionPane.showMessageDialog(this, "Contrato não encontrado.");
            return;
        }
        
        
        if(!contractLink.isEmpty() && !LinkValidator.isValid(contractLink)){
            JOptionPane.showMessageDialog(this, "Formato de link inválido!" +
                "certifique que você esteja inserindo um documento PDF ou imagem referente" +
                "ao contrato a ser cadastrado");
            return;
        }
        
        if(numberOfInstallments != 0){
            contract.setNumberOfInstallments(numberOfInstallments);

        }
      
        // Captura e faz a conversão da data
        LocalDate startDate;
        dateString = dateString.trim();
        System.out.println(dateString);
        if (!dateString.isEmpty()) {
            if (!dateString.equals("/  /" )) {
                try {
                    startDate = ConfigureDateField.parseDate(dateString);
                    contract.setStartDate(startDate);
                    
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(this, "Data inválida. Use o formato DD/MM/AAAA.");
                    return;
                }
            }
        }
        
        if(!clientName.isEmpty()){
            contract.setClientName(clientName);
        }
        if(!bankCode.isEmpty()){
            contract.setBankCode(bankCode);
        }

        if(!contractLink.isEmpty()) {
            contract.setContractLink(contractLink);
        }
        try {
            contractDAO.update(contract);
            JOptionPane.showMessageDialog(this, "Contrato alterado com sucesso!");
            clearFields();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar contrato:" + e.getMessage());
        }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearFields() {
        jTextField2.setText("");
        jTextField3.setText("");
        jSpinner1.setValue(0);
        jTextField4.setText("");
    }
    
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}