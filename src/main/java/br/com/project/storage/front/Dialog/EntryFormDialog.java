package br.com.project.storage.front.Dialog;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.controller.ProductEntreyController;
import br.com.project.storage.back.model.Product;
import br.com.project.storage.back.model.ProductEntry;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.Date;

public class EntryFormDialog extends JDialog {

    private final ProductEntreyController entryController;
    private final ProductController productController;
    private final ProductEntry productEntry;  // nulo = nova entrada; não nulo = edição

    // componentes de formulário
    private JComboBox<Product> cmbProduct;
    private JDateChooser dateChooser;
    private JTextField txtTotalAmount, txtTotalWeight;
    private DefaultListModel<String> itemsModel;
    private JList<String> listItems;
    private JTextField txtDelivererName, txtVehiclePlate, txtPhone;
    private DefaultListModel<String> docsModel;
    private JList<String> listDocs;

    public EntryFormDialog(Window owner,
                           String title,
                           ProductEntreyController entryController,
                           ProductController productController,
                           ProductEntry selected) {

        super(owner, title, ModalityType.APPLICATION_MODAL);
        this.entryController   = entryController;
        this.productController = productController;
        this.productEntry      = selected;
        initComponents();
        populateFields();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 1: Produto
        gbc.gridx=0; gbc.gridy=0;
        form.add(new JLabel("Produto:"), gbc);
        cmbProduct = new JComboBox<>(
            productController.getAll().toArray(new Product[0])
        );
        gbc.gridx=1;
        form.add(cmbProduct, gbc);

        // Linha 2: Data de Entrada
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Data de Entrada:"), gbc);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy HH:mm");
        gbc.gridx=1;
        form.add(dateChooser, gbc);

        // Linha 3: Quantidade Total
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Quantidade Total:"), gbc);
        txtTotalAmount = new JTextField(8);
        gbc.gridx=1;
        form.add(txtTotalAmount, gbc);

        // Linha 4: Peso Total
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Peso Total:"), gbc);
        txtTotalWeight = new JTextField(8);
        gbc.gridx=1;
        form.add(txtTotalWeight, gbc);

        // Linha 5: Itens/Lotes
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Lotes / Itens:"), gbc);
        itemsModel = new DefaultListModel<>();
        listItems  = new JList<>(itemsModel);
        listItems.setVisibleRowCount(4);
        gbc.gridx=1;
        form.add(new JScrollPane(listItems), gbc);
        JButton btnAddItem = new JButton("Adicionar Item");
        btnAddItem.addActionListener(e -> onAddItem());
        gbc.gridx=2; form.add(btnAddItem, gbc);
        JButton btnRemoveItem = new JButton("Remover Item");
        btnRemoveItem.addActionListener(e -> onRemoveItem());
        gbc.gridx=3; form.add(btnRemoveItem, gbc);

        // Linha 6: Entregador
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Nome do Entregador:"), gbc);
        txtDelivererName = new JTextField(12);
        gbc.gridx=1;
        form.add(txtDelivererName, gbc);

        // Linha 7: Placa
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Placa do Veículo:"), gbc);
        txtVehiclePlate = new JTextField(10);
        gbc.gridx=1;
        form.add(txtVehiclePlate, gbc);

        // Linha 8: Telefone
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Telefone:"), gbc);
        txtPhone = new JTextField(10);
        gbc.gridx=1;
        form.add(txtPhone, gbc);

        // Linha 9: Documentos Auxiliares
        gbc.gridy++; gbc.gridx=0;
        form.add(new JLabel("Documentos Auxiliares:"), gbc);
        docsModel = new DefaultListModel<>();
        listDocs  = new JList<>(docsModel);
        listDocs.setVisibleRowCount(4);
        gbc.gridx=1;
        form.add(new JScrollPane(listDocs), gbc);
        JButton btnAddDoc = new JButton("Adicionar Documento");
        btnAddDoc.addActionListener(e -> onAddDocument());
        gbc.gridx=2; form.add(btnAddDoc, gbc);
        JButton btnRemoveDoc = new JButton("Remover Documento");
        btnRemoveDoc.addActionListener(e -> onRemoveDocument());
        gbc.gridx=3; form.add(btnRemoveDoc, gbc);

        add(form, BorderLayout.CENTER);

        // Painel de botões Salvar / Cancelar
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8,8));
        JButton btnSave   = new JButton("Salvar");
        JButton btnCancel = new JButton("Cancelar");
        btnSave.addActionListener(e -> onSave());
        btnCancel.addActionListener(e -> dispose());
        buttons.add(btnSave);
        buttons.add(btnCancel);
        add(buttons, BorderLayout.SOUTH);
    }

    private void populateFields() {
        if (productEntry == null) return;

        cmbProduct.setSelectedItem(productEntry.getProduct());
        Date dt = Date.from(
            productEntry.getDateEntry()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
        dateChooser.setDate(dt);
        txtTotalAmount.setText("" + productEntry.getTotalAmount());
        txtTotalWeight.setText("" + productEntry.getTotalWeight());

        itemsModel.clear();
        productEntry.getItems()
                    .forEach(item -> itemsModel.addElement(item.toString()));

        txtDelivererName.setText(productEntry.getDelivererName());
        txtVehiclePlate.setText(productEntry.getVehiclePlate());
        txtPhone.setText("" + productEntry.getPhone());

        docsModel.clear();
        productEntry.getDocument()
                    .forEach(doc -> docsModel.addElement(doc.getFileName()));
    }

    // abre um dialog customizado ou JOptionPane para inserir um novo item
    private void onAddItem() {
        String batch = JOptionPane.showInputDialog(this, "Código do Lote:");
        if (batch != null && !batch.isBlank()) {
            String qty = JOptionPane.showInputDialog(this, "Quantidade:");
            String w   = JOptionPane.showInputDialog(this, "Peso:");
            itemsModel.addElement(batch + " | qtd=" + qty + " | p=" + w);
        }
    }

    private void onRemoveItem() {
        int idx = listItems.getSelectedIndex();
        if (idx >= 0) itemsModel.remove(idx);
    }

    private void onAddDocument() {
        String name = JOptionPane.showInputDialog(this, "Nome do Documento:");
        if (name != null && !name.isBlank()) {
            docsModel.addElement(name);
        }
    }

    private void onRemoveDocument() {
        int idx = listDocs.getSelectedIndex();
        if (idx >= 0) docsModel.remove(idx);
    }

    private void onSave() {
        try {
            ProductEntry e = (productEntry == null
                ? new ProductEntry()
                : productEntry
            );

            e.setProduct((Product) cmbProduct.getSelectedItem());
            e.setDateEntry(
                dateChooser.getDate()
                           .toInstant()
                           .atZone(ZoneId.systemDefault())
                           .toLocalDateTime()
            );
            e.setTotalAmount(Integer.parseInt(txtTotalAmount.getText().trim()));
            e.setTotalWeight(Double.parseDouble(txtTotalWeight.getText().trim()));

            // aqui você precisará converter cada linha de itemsModel
            // de volta para ProductAssistant e AuxiliaryDocument...
            // Exemplo simplificado:
            // e.setItems(convertToAssistants(itemsModel));
            // e.setDocuments(convertToDocs(docsModel));

            e.setDelivererName(txtDelivererName.getText().trim());
            e.setVehiclePlate(txtVehiclePlate.getText().trim());
            e.setPhone(Integer.parseInt(txtPhone.getText().trim()));

            if (productEntry == null) {
                entryController.Post(e);
            } else {
                entryController.Put(e);
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar entrada:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}