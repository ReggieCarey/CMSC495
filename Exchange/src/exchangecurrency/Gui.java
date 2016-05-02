/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangecurrency;

import static exchangecurrency.Model.PROP_CURRENCYCODES;
import static exchangecurrency.Model.PROP_SOURCEAMOUNT;
import static exchangecurrency.Model.PROP_SOURCECURRENCYCODE;
import static exchangecurrency.Model.PROP_TARGETAMOUNT;
import static exchangecurrency.Model.PROP_TARGETCURRENCYCODE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
    University of Maryland: University College
    CMSC495 Group 2
    Class      : GUI
    Created on : May 1, 2016
    Author     : Reginald Carey
 */
public class Gui extends JFrame {

    private final Model model = new Model();

    private Gui() {
        initComponents();
        init();
    }

    public final void init() {
        CurrencyConversionLogic logic = new CurrencyConversionLogic();
        model.setCurrencyConversionLogic(logic);
        model.initModel();
    }

    private void initComponents() {
        //<editor-fold defaultstate="collapsed" desc=" Setup the GUI ">

        JPanel mainPanel = new JPanel();
        JPanel dataEntryPanel = new JPanel();
        JTextField sourceAmountTextField = new JTextField();
        JComboBox sourceCurrencyComboBox = new JComboBox();
        JTextField targetAmountTextField = new JTextField();
        DocumentFilter numberfilter = new NonNumericFilter();
        ((AbstractDocument)sourceAmountTextField.getDocument()).setDocumentFilter(numberfilter);
        JComboBox targetCurrencyComboBox = new JComboBox();
        JPanel currencyRatePanel = new JPanel();
        JLabel sourceCurrencyLabel = new JLabel();
        JLabel targetCurrencyLabel = new JLabel();
        JPanel historicalGraphPanel = new JPanel();

        model.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            switch (evt.getPropertyName()) {
                case PROP_CURRENCYCODES: {
                    List<String> currencyCodeList = (List<String>) evt.getNewValue();
                    Collections.sort(currencyCodeList);
                    String[] currencyCodes = currencyCodeList.toArray(new String[currencyCodeList.size()]);
                    DefaultComboBoxModel sourceComboBoxModel = new DefaultComboBoxModel(currencyCodes);
                    DefaultComboBoxModel targetComboBoxModel = new DefaultComboBoxModel(currencyCodes);
                    sourceCurrencyComboBox.setModel(sourceComboBoxModel);
                    targetCurrencyComboBox.setModel(targetComboBoxModel);
                    model.setSourceCurrencyCode((String) sourceCurrencyComboBox.getSelectedItem());
                    model.setTargetCurrencyCode((String) sourceCurrencyComboBox.getSelectedItem());
                    break;
                }
                case PROP_SOURCECURRENCYCODE: {
                    sourceCurrencyLabel.setText(String.format("%.2f %s (as of %s) equals", 1.0, model.getNameForCode((String) evt.getNewValue()), model.getLastUpdatedDate()));
                    sourceCurrencyComboBox.setSelectedItem(evt.getNewValue());
                    break;
                }
                case PROP_TARGETCURRENCYCODE: {
                    targetCurrencyLabel.setText(String.format("%.4f %s", model.getRate(), model.getNameForCode((String) evt.getNewValue())));
                    targetCurrencyComboBox.setSelectedItem(evt.getNewValue());
                    break;
                }
                case PROP_SOURCEAMOUNT: {
                    Double amount = (Double) evt.getNewValue();
                    String code = model.getSourceCurrencyCode();
                    sourceCurrencyLabel.setText(String.format("%.2f %s (as of %s) equals", 1.0, model.getNameForCode(code), model.getLastUpdatedDate()));
                    try {
                        sourceAmountTextField.setText(String.format("%.2f", amount));
                    } catch (IllegalStateException ex) {
                    }
                    break;
                }
                case PROP_TARGETAMOUNT: {
                    Double amount = (Double) evt.getNewValue();
                    String code = model.getTargetCurrencyCode();
                    targetCurrencyLabel.setText(String.format("%.4f %s", model.getRate(), model.getNameForCode(code)));
                    try {
                        targetAmountTextField.setText(String.format("%.2f", amount));
                    } catch (IllegalStateException ex) {
                    }
                    break;
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exchange! - a currency exchange application");

        dataEntryPanel.setFont(new Font("Lucida Grande", 0, 14)); // NOI18N

        sourceAmountTextField.setFont(dataEntryPanel.getFont());
        sourceAmountTextField.setHorizontalAlignment(JTextField.TRAILING);
        sourceAmountTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void handler(DocumentEvent e) {
                try {
                    String amtString = sourceAmountTextField.getText();
                    model.setSourceAmount(Double.parseDouble(amtString));
                    sourceAmountTextField.setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    sourceAmountTextField.setBackground(Color.red);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                handler(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handler(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handler(e);
            }
        });

        sourceCurrencyComboBox.setFont(dataEntryPanel.getFont());
        sourceCurrencyComboBox.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        sourceCurrencyComboBox.addItemListener((ItemEvent evt) -> {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                model.setSourceCurrencyCode((String) evt.getItem());
            }
        });

        targetAmountTextField.setFont(dataEntryPanel.getFont());
        targetAmountTextField.setHorizontalAlignment(JTextField.TRAILING);
        targetAmountTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void handler(DocumentEvent e) {
                try {
                    String amtString = targetAmountTextField.getText();
                    model.setTargetAmount(Double.parseDouble(amtString));
                    targetAmountTextField.setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    targetAmountTextField.setBackground(Color.red);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                handler(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handler(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handler(e);
            }
        });

        targetCurrencyComboBox.setFont(dataEntryPanel.getFont());
        targetCurrencyComboBox.setModel(new DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        targetCurrencyComboBox.addItemListener((ItemEvent evt) -> {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                model.setTargetCurrencyCode((String) evt.getItem());
            }
        });

        GroupLayout dataEntryPanelLayout = new GroupLayout(dataEntryPanel);
        dataEntryPanel.setLayout(dataEntryPanelLayout);
        dataEntryPanelLayout.setHorizontalGroup(dataEntryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(dataEntryPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dataEntryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(sourceAmountTextField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addComponent(targetAmountTextField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dataEntryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sourceCurrencyComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(targetCurrencyComboBox, 0, 158, Short.MAX_VALUE))
                        .addContainerGap())
        );
        dataEntryPanelLayout.setVerticalGroup(dataEntryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(dataEntryPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dataEntryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(sourceAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(sourceCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dataEntryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(targetAmountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(targetCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sourceCurrencyLabel.setFont(new Font("Lucida Sans", 0, 18)); // NOI18N
        sourceCurrencyLabel.setForeground(new Color(153, 153, 153));
        sourceCurrencyLabel.setText("sourceCurrencyLabel");

        targetCurrencyLabel.setFont(new Font("Lucida Grande", 0, 36)); // NOI18N
        targetCurrencyLabel.setText("targetCurrencyLabel");

        GroupLayout currencyRatePanelLayout = new GroupLayout(currencyRatePanel);
        currencyRatePanel.setLayout(currencyRatePanelLayout);
        currencyRatePanelLayout.setHorizontalGroup(currencyRatePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(currencyRatePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(currencyRatePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(targetCurrencyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sourceCurrencyLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        currencyRatePanelLayout.setVerticalGroup(currencyRatePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(currencyRatePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(sourceCurrencyLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(targetCurrencyLabel)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout historicalGraphPanelLayout = new GroupLayout(historicalGraphPanel);
        historicalGraphPanel.setLayout(historicalGraphPanelLayout);
        historicalGraphPanelLayout.setHorizontalGroup(historicalGraphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 275, Short.MAX_VALUE)
        );
        historicalGraphPanelLayout.setVerticalGroup(historicalGraphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(currencyRatePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(dataEntryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(historicalGraphPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(currencyRatePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(dataEntryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(historicalGraphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        setSize(new Dimension(652, 223));
        setLocationRelativeTo(null);
        //</editor-fold>
    }

    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Gui().setVisible(true);
        });
    }
}

class Model {

    public static final String PROP_SOURCECURRENCYCODE = "sourceCurrencyCode";
    public static final String PROP_TARGETCURRENCYCODE = "targetCurrencyCode";
    public static final String PROP_SOURCEAMOUNT = "sourceAmount";
    public static final String PROP_TARGETAMOUNT = "targetAmount";
    public static final String PROP_CURRENCYCODES = "currencyCodes";

    private String sourceCurrencyCode;
    private String targetCurrencyCode;
    private Double sourceAmount;
    private Double targetAmount;
    private List<String> currencyCodes;
    private boolean fromSetSource = false;
    private boolean fromSetTarget = false;

    private CurrencyConversionLogic logic;

    public void setCurrencyConversionLogic(CurrencyConversionLogic logic) {
        this.logic = logic;
    }

    public void initModel() {
        setSourceAmount(1.0);
        setTargetAmount(1.0);
        setCurrencyCodes(logic.getCurrencyCodes());
        setSourceCurrencyCode("USD");
        setTargetCurrencyCode("EUR");
    }

    public String getNameForCode(String code) {
        return code;
    }

    public Boolean getDecimalUsage(String code) {
        return logic.getDecimalUsage(code);
    }

    public String getLastUpdatedDate() {
        return logic.getLastUpdatedDate(sourceCurrencyCode);
    }

    public Double getRate() {
        if (sourceCurrencyCode != null && targetCurrencyCode != null) {
            return logic.getRate(sourceCurrencyCode, targetCurrencyCode);
        } else {
            return 1.0;
        }
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    private void computeTargetAmount() {
        if (sourceCurrencyCode != null && targetCurrencyCode != null) {
            setTargetAmount(logic.convert(sourceCurrencyCode, targetCurrencyCode, sourceAmount));
        }
    }

    private void computeSourceAmount() {
        if (sourceCurrencyCode != null && targetCurrencyCode != null) {
            setSourceAmount(logic.convert(targetCurrencyCode, sourceCurrencyCode, targetAmount));
        }
    }

    public void setSourceCurrencyCode(String sourceCurrencyCode) {
        String oldSourceCurrencyCode = this.sourceCurrencyCode;
        this.sourceCurrencyCode = sourceCurrencyCode;
        propertyChangeSupport.firePropertyChange(PROP_SOURCECURRENCYCODE, oldSourceCurrencyCode, sourceCurrencyCode);
        if (!fromSetTarget) {
            fromSetSource = true;
            computeTargetAmount();
            fromSetSource = false;
        }
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        String oldTargetCurrencyCode = this.targetCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        propertyChangeSupport.firePropertyChange(PROP_TARGETCURRENCYCODE, oldTargetCurrencyCode, targetCurrencyCode);
        if (!fromSetTarget) {
            fromSetSource = true;
            computeTargetAmount();
            fromSetSource = false;
        }
    }

    public Double getSourceAmount() {
        return sourceAmount;
    }

    public final void setSourceAmount(Double sourceAmount) {
        Double oldSourceAmount = this.sourceAmount;
        this.sourceAmount = sourceAmount;
        propertyChangeSupport.firePropertyChange(PROP_SOURCEAMOUNT, oldSourceAmount, sourceAmount);
        if (!fromSetTarget) {
            fromSetSource = true;
            computeTargetAmount();
            fromSetSource = false;
        }
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public final void setTargetAmount(Double targetAmount) {
        Double oldTargetAmount = this.targetAmount;
        this.targetAmount = targetAmount;
        propertyChangeSupport.firePropertyChange(PROP_TARGETAMOUNT, oldTargetAmount, targetAmount);
        if (!fromSetSource) {
            fromSetTarget = true;
            computeSourceAmount();
            fromSetTarget = false;
        }
    }

    public List<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public final void setCurrencyCodes(List<String> currencyCodes) {
        List<String> oldCurrencyCodes = this.currencyCodes;
        this.currencyCodes = currencyCodes;
        propertyChangeSupport.firePropertyChange(PROP_CURRENCYCODES, oldCurrencyCodes, currencyCodes);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}

class NonNumericFilter extends DocumentFilter {

@Override
public void insertString(DocumentFilter.FilterBypass fb, int offs,
String str, AttributeSet a) throws BadLocationException {
                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if (text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                }
}

// no need to override remove(): inherited version allows all removals

@Override
public void replace(DocumentFilter.FilterBypass fb, int offs, int length,
String str, AttributeSet a) throws BadLocationException {
                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if (text.matches("^[0-9]+[.]?[0-9]{0,2}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                }
}
}