package br.com.project.storage.front.Dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.stream.IntStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.controller.ProductEntreyController;
import br.com.project.storage.back.model.ProductAssistant;
import br.com.project.storage.back.model.ProductEntry;

public class ProductConferenceDialog extends JDialog {

    private final ProductEntreyController entryController;
    private final ProductController productController;
    private final ProductEntry productEntry;

    private DefaultListModel<ProductAssistant> itemsModel;
    private JList<ProductAssistant> listItems;

    public ProductConferenceDialog(Window window, String string, ProductEntreyController productEntreyController,
            ProductController productController, ProductEntry loaded) {
        super(window, string, ModalityType.APPLICATION_MODAL);
        this.entryController = productEntreyController;
        this.productController = productController;
        this.productEntry = loaded;
        initComponents();
        pack();
        setLocationRelativeTo(window);
    }

    public void initComponents() {
        setLayout(new BorderLayout(8, 8));

        // Painel com GridBagLayout onde os componentes serão adicionados
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        // inicializa a linha
        gbc.gridy = 0;

        // label
        gbc.gridx = 0;
        form.add(new JLabel("Lotes / Itens:"), gbc);

        // lista
        itemsModel = new DefaultListModel<>();
        listItems = new JList<>(itemsModel);
        listItems.setVisibleRowCount(4);
        gbc.gridx = 1;
        form.add(new JScrollPane(listItems), gbc);

        // botão adicionar
        JButton btnAddItem = new JButton("Adicionar Item");
        btnAddItem.addActionListener(e -> onAddItem());
        gbc.gridx = 2;
        form.add(btnAddItem, gbc);

        // botão remover
        JButton btnRemoveItem = new JButton("Remover Item");
        btnRemoveItem.addActionListener(e -> onRemoveItem());
        gbc.gridx = 3;
        form.add(btnRemoveItem, gbc);

        // agora adiciona o painel "form" ao JDialog usando BorderLayout
        add(form, BorderLayout.CENTER);
    }

    private void onAddItem() {
        ItemDialog dlg = new ItemDialog(SwingUtilities.getWindowAncestor(this));
        dlg.setVisible(true);
        ProductAssistant assistant = dlg.getResult();
        if (assistant != null) {
            itemsModel.addElement(assistant);
        }
    }

    private void onRemoveItem() {
       int idx = listItems.getSelectedIndex();
        if (idx >= 0) {
            itemsModel.remove(idx);
        } else {
            JOptionPane.showMessageDialog(this,
                "Selecione um item para remover.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
        }
    }

}