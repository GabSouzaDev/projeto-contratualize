/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.projeto.contratualize.contratualize.view.ClientInternalFrame;

import com.projeto.contratualize.contratualize.dao.ContractDAO;
import com.projeto.contratualize.contratualize.model.Contract;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabri
 */
public class AllClientScreen extends javax.swing.JInternalFrame {
    ContractDAO contractDAO = new ContractDAO();

    /**
     * Creates new form AllClientScreen
     */
    public AllClientScreen() {
        initComponents();
        loadClients();
        //adiciona o MouseListener
        jTable1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row = jTable1.rowAtPoint(e.getPoint());
                int column = jTable1.columnAtPoint(e.getPoint());
                if(column == 2) {
                    String clientName = (String) jTable1.getValueAt(row, 0);
                    openContractsByClientScreen(clientName);
                }
            }
            
        });
        
    }
    
    
    
    
    //Carrega os clientes do banco de dados
    private void loadClients() {
        List<Object[]> clients = contractDAO.findClientNamesWithContractCount();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpa a tabela
        for (Object[] client : clients) {
            String clientName = (String) client[0];
            Long contractCount = (Long) client[1];
            model.addRow(new Object[]{
                clientName,
                contractCount,
                "Ver contratos"
            });
        }
    }
    
   private void openContractsByClientScreen(String clientName) {
       List<Contract> contracts = contractDAO.findContractsByClientName(clientName);
       if(contracts.isEmpty()){
        JOptionPane.showMessageDialog(this, "Este cliente não possui contratos.");
        return;
       } 
        
       VisualizeContractByClientName contractsScreen = new VisualizeContractByClientName(clientName);
       contractsScreen.setVisible(true);
       
        // Adiciona ao JDesktopPane
        JDesktopPane desktop = (JDesktopPane) SwingUtilities.getAncestorOfClass(JDesktopPane.class, this);
        if (desktop != null) {
            desktop.add(contractsScreen);
            contractsScreen.moveToFront();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao abrir a tela de contratos: JDesktopPane não encontrado.");
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cliente", "Contratos", "Ver contratos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
