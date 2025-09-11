package br.com.project.storage.front.panel;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.controller.ProductEntreyController;
import br.com.project.storage.back.enums.EntryStatus;
import br.com.project.storage.back.model.AuxiliaryDocument;
import br.com.project.storage.back.model.Product;
import br.com.project.storage.back.model.ProductAssistant;
import br.com.project.storage.back.model.ProductEntry;
import br.com.project.storage.front.Dialog.DocumentDialog;
import br.com.project.storage.front.Dialog.ItemDialog;

import com.toedter.calendar.JDateChooser;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

@Component
public class ProductEntryPanel extends JPanel {

    private final ProductEntreyController entryController;
    private final ProductController productController;

    private JComboBox<Product> cmbProduct;
    private JDateChooser dateChooser;
    private JTextField txtTotalWeight;
    private JTextField txtTotalAmount;

    private DefaultListModel<ProductAssistant> itemsModel;
    private JList<ProductAssistant> listItems;

    private JTextField txtDelivererName;
    private JTextField txtVehiclePlate;
    private JTextField txtPhone;

    private DefaultListModel<AuxiliaryDocument> docsModel;
    private JList<AuxiliaryDocument> listDocs;

    private JButton btnSave;

    public ProductEntryPanel(ProductEntreyController entryController,
                             ProductController productController) {
        this.entryController = entryController;
        this.productController = productController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        // Produto
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Produto:"), gbc);
        List<Product> prodList = new ArrayList<>(this.productController.getAll());
        cmbProduct = new JComboBox<>(prodList.toArray(new Product[0]));
        gbc.gridx = 1;
        add(cmbProduct, gbc);

        // Data com calendário
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Data de Entrada:"), gbc);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        gbc.gridx = 1;
        add(dateChooser, gbc);

        // Peso total
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Peso Total:"), gbc);
        txtTotalWeight = new JTextField(10);
        gbc.gridx = 1;
        add(txtTotalWeight, gbc);

        // Quantidade total
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Quantidade Total:"), gbc);
        txtTotalAmount = new JTextField(10);
        gbc.gridx = 1;
        add(txtTotalAmount, gbc);

        // Lista de itens + botões Adicionar / Remover
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Lotes / Itens:"), gbc);
        itemsModel = new DefaultListModel<>();
        listItems = new JList<>(itemsModel);
        listItems.setVisibleRowCount(4);
        gbc.gridx = 1;
        add(new JScrollPane(listItems), gbc);

        JButton btnAddItem = new JButton("Adicionar Item");
        btnAddItem.addActionListener(e -> onAddItem());
        gbc.gridx = 2;
        add(btnAddItem, gbc);

        JButton btnRemoveItem = new JButton("Remover Item");
        btnRemoveItem.addActionListener(e -> onRemoveItem());
        gbc.gridx = 3;
        add(btnRemoveItem, gbc);

        // Entregador
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nome do Entregador:"), gbc);
        txtDelivererName = new JTextField(15);
        gbc.gridx = 1;
        add(txtDelivererName, gbc);

        // Placa
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Placa do Veículo:"), gbc);
        txtVehiclePlate = new JTextField(10);
        gbc.gridx = 1;
        add(txtVehiclePlate, gbc);

        // Telefone
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Telefone:"), gbc);
        txtPhone = new JTextField(10);
        gbc.gridx = 1;
        add(txtPhone, gbc);

        // Documentos + botões Adicionar / Remover
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Documentos Auxiliares:"), gbc);
        docsModel = new DefaultListModel<>();
        listDocs = new JList<>(docsModel);
        listDocs.setVisibleRowCount(4);
        gbc.gridx = 1;
        add(new JScrollPane(listDocs), gbc);

        JButton btnAddDoc = new JButton("Adicionar Documento");
        btnAddDoc.addActionListener(e -> onAddDocument());
        gbc.gridx = 2;
        add(btnAddDoc, gbc);

        JButton btnRemoveDoc = new JButton("Remover Documento");
        btnRemoveDoc.addActionListener(e -> onRemoveDocument());
        gbc.gridx = 3;
        add(btnRemoveDoc, gbc);

        // Botão salvar
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        btnSave = new JButton("Salvar Entrada");
        btnSave.addActionListener(e -> onSave());
        add(btnSave, gbc);
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
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

    private void onAddDocument() {
        DocumentDialog dlg = new DocumentDialog(SwingUtilities.getWindowAncestor(this));
        dlg.setVisible(true);
        AuxiliaryDocument doc = dlg.getResult();
        if (doc != null) {
            docsModel.addElement(doc);
        }
    }

    private void onRemoveDocument() {
        int idx = listDocs.getSelectedIndex();
        if (idx >= 0) {
            docsModel.remove(idx);
        } else {
            JOptionPane.showMessageDialog(this,
                "Selecione um documento para remover.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void onSave() {
        try {
            Product product     = (Product) cmbProduct.getSelectedItem();
            Date dateEntry      = dateChooser.getDate();
            EntryStatus status  = EntryStatus.Waiting;
            double totalWeight  = Double.parseDouble(txtTotalWeight.getText());
            int totalAmount     = Integer.parseInt(txtTotalAmount.getText());
            List<ProductAssistant> items = Collections.list(itemsModel.elements());
            String deliverer    = txtDelivererName.getText();
            String plate        = txtVehiclePlate.getText();
            int phone           = Integer.parseInt(txtPhone.getText());
            List<AuxiliaryDocument> docs = Collections.list(docsModel.elements());

            ProductEntry entry = new ProductEntry(
                null, product, dateEntry, status,
                totalWeight, totalAmount,
                items, deliverer, plate, phone, docs
            );
            entryController.Post(entry);

            JOptionPane.showMessageDialog(this,
                "Entrada salva com sucesso.\nCódigo: " + entry.getCodeEntry(),
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        cmbProduct.setSelectedIndex(0);
        dateChooser.setDate(null);
        txtTotalWeight.setText("");
        txtTotalAmount.setText("");
        itemsModel.clear();
        txtDelivererName.setText("");
        txtVehiclePlate.setText("");
        txtPhone.setText("");
        docsModel.clear();
    }
}