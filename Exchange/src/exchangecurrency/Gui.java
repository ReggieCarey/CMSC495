/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;
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

    ExchangeRateDB erdb = new ExchangeRateDB();
    ExchangeRateWebService erws = new ExchangeRateWebService();

    class Model {

        private String sourceCurrencyCode;

        public static final String PROP_SOURCECURRENCYCODE = "sourceCurrencyCode";

        public String getSourceCurrencyCode() {
            return sourceCurrencyCode;
        }

        public void setSourceCurrencyCode(String sourceCurrencyCode) {
            String oldSourceCurrencyCode = this.sourceCurrencyCode;
            this.sourceCurrencyCode = sourceCurrencyCode;
            propertyChangeSupport.firePropertyChange(PROP_SOURCECURRENCYCODE, oldSourceCurrencyCode, sourceCurrencyCode);
        }

        private String targetCurrencyCode;

        public static final String PROP_TARGETCURRENCYCODE = "targetCurrencyCode";

        public String getTargetCurrencyCode() {
            return targetCurrencyCode;
        }

        public void setTargetCurrencyCode(String targetCurrencyCode) {
            String oldTargetCurrencyCode = this.targetCurrencyCode;
            this.targetCurrencyCode = targetCurrencyCode;
            propertyChangeSupport.firePropertyChange(PROP_TARGETCURRENCYCODE, oldTargetCurrencyCode, targetCurrencyCode);
        }

        private Double sourceAmount;

        public static final String PROP_SOURCEAMOUNT = "sourceAmount";

        public Double getSourceAmount() {
            return sourceAmount;
        }

        public void setSourceAmount(Double sourceAmount) {
            Double oldSourceAmount = this.sourceAmount;
            this.sourceAmount = sourceAmount;
            propertyChangeSupport.firePropertyChange(PROP_SOURCEAMOUNT, oldSourceAmount, sourceAmount);
        }
        private Double targetAmount;

        public static final String PROP_TARGETAMOUNT = "targetAmount";

        public Double getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(Double targetAmount) {
            Double oldTargetAmount = this.targetAmount;
            this.targetAmount = targetAmount;
            propertyChangeSupport.firePropertyChange(PROP_TARGETAMOUNT, oldTargetAmount, targetAmount);
        }

        private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }
    }

    Model model = new Model();

    private Gui() {
        model.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            System.out.println(evt);
        });
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

        List<String> currencyCodeList = erdb.getCurrencyCodes();
        Collections.sort(currencyCodeList);
        String[] currencyCodes = currencyCodeList.toArray(new String[currencyCodeList.size()]);

        DefaultComboBoxModel sourceComboBoxModel = new DefaultComboBoxModel(currencyCodes);
        sourceCurrencyComboBox.setModel(sourceComboBoxModel);
        sourceCurrencyComboBox.addItemListener((ItemEvent evt) -> {
            sourceCurrencyComboBoxItemStateChanged(evt);
        });
        model.setSourceCurrencyCode((String) sourceCurrencyComboBox.getSelectedItem());

        targetCurrencyLabel.setText("Target Currency");

        DefaultComboBoxModel targetComboBoxModel = new DefaultComboBoxModel(currencyCodes);
        targetCurrencyComboBox.setModel(targetComboBoxModel);
        targetCurrencyComboBox.addItemListener((ItemEvent evt) -> {
            targetCurrencyComboBoxItemStateChanged(evt);
        });
        model.setTargetCurrencyCode((String) targetCurrencyComboBox.getSelectedItem());

        sourceAmountLabel.setText("Source Amount");

//        sourceAmountInputTextField.getDocument().addDocumentListener(new DocumentListener() {
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                try {
//                    String text = e.getDocument().getText(0, e.getLength());
//                    System.out.println(text);
//                } catch (BadLocationException ex) {
//                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                try {
//                    String text = e.getDocument().getText(0, e.getLength());
//                    System.out.println(text);
//                } catch (BadLocationException ex) {
//                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                try {
//                    String text = e.getDocument().getText(0, e.getLength());
//                    System.out.println(text);
//                } catch (BadLocationException ex) {
//                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        sourceAmountInputTextField.addActionListener((ActionEvent evt) -> {
            sourceAmountInputTextFieldActionPerformed(evt);
        });

        targetAmountLabel.setText("Target Amount");
        targetAmountOutputTextField.setEditable(false);

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
        System.out.println(evt);
    }

    private void sourceCurrencyComboBoxItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            model.setSourceCurrencyCode((String) evt.getItem());
        }
    }

    private void targetCurrencyComboBoxItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            model.setTargetCurrencyCode((String) evt.getItem());
        }
    }

    public static void go() {
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
            new Gui().setVisible(true);
        });
    }
}
