/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.projeto.contratualize.contratualize.view.ContractJInternalFrame;

import com.projeto.contratualize.contratualize.view.UserInternalFrames.*;
import com.projeto.contratualize.contratualize.dao.ContractDAO;
import com.projeto.contratualize.contratualize.model.Contract;
import com.projeto.contratualize.contratualize.util.DeletionConfirmation;
import com.projeto.contratualize.contratualize.util.PrescriptionCalculator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author gabri
 */
public class AllContractScreen extends javax.swing.JInternalFrame {
    ContractDAO contractDAO = new ContractDAO();

    /**
     * Creates new form AllUserScreen
     */
    public AllContractScreen() {
        initComponents();
        loadContracts();

        // Adiciona o MouseListener
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTable1.rowAtPoint(e.getPoint());
                int column = jTable1.columnAtPoint(e.getPoint());
                if (column == 4) { // Editar
                    Long contractId =  (Long) jTable1.getValueAt(row, 0);
                    Contract contract = new ContractDAO().findById(contractId);
                    if(contract != null) {
                        openEditContractScreen(contract);
                    } else {
                        JOptionPane.showMessageDialog(AllContractScreen.this, "Contrato não encontrado.");
                    }
                } else if(column == 5){ //Excluir
                    Long contractId = (Long) jTable1.getValueAt(row, 0);
                    Contract contract = new ContractDAO().findById(contractId);
                    String contractNumber = (String) contract.getContractNumber();
                    if(DeletionConfirmation.contractConfirmDeletion(contractNumber)){
                        if(contract != null){
                            contractDAO.delete(contract);
                            JOptionPane.showMessageDialog(AllContractScreen.this,"Contrato excluído com sucesso!");
                            loadContracts();
                        } else {
                            JOptionPane.showMessageDialog(AllContractScreen.this, "Contrato não encontrado.");
                        }
                    }
                }
            }
        });
    }
    
    
    //Abrir a tela de edição 
    private void openEditContractScreen(Contract contract) {
        EditContractScreen editScreen = new EditContractScreen(contract);
        editScreen.setVisible(true);

        // Adiciona ao JDesktopPane
        JDesktopPane desktop = (JDesktopPane) SwingUtilities.getAncestorOfClass(JDesktopPane.class, this);
        if (desktop != null) {
            desktop.add(editScreen);
            editScreen.moveToFront();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao abrir a tela de edição: JDesktopPane não encontrado.");
        }
    }
    
    private void loadContracts() {
        List<Contract> contracts = contractDAO.findAllWithClients();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); //Limpa a tabela

        for (Contract contract : contracts) {
            if(contract.getStartDate() == null) {
                System.err.println("Contracto com startDate nulo: " + contract.getContractNumber());
                model.addRow(new Object[]{
                   contract.getId(),
                   contract.getContractNumber(),
                   contract.getClientName(),
                   "não definida",
                   "Editar",
                   "Excluir"   
                });
                continue;
                
            }
            //calcular prescrição
            LocalDate prescriptionDate = PrescriptionCalculator.calculatePrescriptionDate(
                    contract.getStartDate(), contract.getNumberOfInstallments());
            String prescriptionDateString = prescriptionDate != null ? prescriptionDate.toString() : "";
            //Adiciona os dados na tabela
            model.addRow(new Object[]{
                contract.getId(),
                contract.getContractNumber(),
                contract.getClientName(),
                prescriptionDateString,
                "Editar",
                "Excluir"
            });
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setResizable(true);
        setEnabled(false);
        setInheritsPopupMenu(true);
        setPreferredSize(new java.awt.Dimension(502, 450));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Contrato", "Cliente", "Prazo Presc.", "Editar", "Remover"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setAutoscrolls(false);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
    
   
    }
