/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 *
 * @author ReginaldCarey
 */
public class Gui extends JFrame {

    public Gui() {
        initComponents();
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        JLabel sourceCurrencyLabel = new JLabel();
        JComboBox sourceCurrencyComboBox = new JComboBox();
        JLabel targetCurrencyLabel = new JLabel();
        JComboBox targetCurrencyComboBox = new JComboBox();
        JLabel sourceAmountLabel = new JLabel();
        JTextField sourceAmountInputTextField = new JTextField();
        JSeparator separator = new JSeparator();
        JLabel targetAmountLabel = new JLabel();
        JTextField targetAmountOutputTextField = new JTextField();
        JPanel historicalDataOutputPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exchange! - a currency exchange application");

        sourceCurrencyLabel.setText("Source Currency");

        sourceCurrencyComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sourceCurrencyComboBox.addActionListener((ActionEvent evt) -> {
            sourceCurrencyComboBoxActionPerformed(evt);
        });

        targetCurrencyLabel.setText("Target Currency");

        targetCurrencyComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        targetCurrencyComboBox.addActionListener((ActionEvent evt) -> {
            targetCurrencyComboBoxActionPerformed(evt);
        });

        sourceAmountLabel.setText("Source Amount");

        sourceAmountInputTextField.addActionListener((ActionEvent evt) -> {
            sourceAmountInputTextFieldActionPerformed(evt);
        });

        targetAmountLabel.setText("Target Amount");

        historicalDataOutputPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        GroupLayout historicalDataOutputPanelLayout = new GroupLayout(historicalDataOutputPanel);
        historicalDataOutputPanel.setLayout(historicalDataOutputPanelLayout);
        historicalDataOutputPanelLayout.setHorizontalGroup(historicalDataOutputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 946, Short.MAX_VALUE)
        );
        historicalDataOutputPanelLayout.setVerticalGroup(historicalDataOutputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(separator)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(sourceCurrencyLabel)
                            .addComponent(targetCurrencyLabel)
                            .addComponent(sourceAmountLabel))
                        .addGap(35, 35, 35)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(sourceCurrencyComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(targetCurrencyComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sourceAmountInputTextField)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(targetAmountLabel)
                        .addGap(45, 45, 45)
                        .addComponent(targetAmountOutputTextField)))
                .addContainerGap())
            .addComponent(historicalDataOutputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceCurrencyLabel)
                    .addComponent(sourceCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(targetCurrencyLabel)
                    .addComponent(targetCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceAmountLabel)
                    .addComponent(sourceAmountInputTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(targetAmountLabel)
                    .addComponent(targetAmountOutputTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historicalDataOutputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new Dimension(960, 540));
        setLocationRelativeTo(null);
    }

    private void sourceAmountInputTextFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void targetCurrencyComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void sourceCurrencyComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void startGui() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            setVisible(true);
        });
    }
}
