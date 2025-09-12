package br.com.project.storage.front.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.controller.ProductEntreyController;
import br.com.project.storage.back.model.ProductEntry;
import br.com.project.storage.front.Dialog.EntryFormDialog;

public class EntryPanelView extends JPanel{

    private final ProductEntreyController productEntreyController;
    private final ProductController productController;
    
    private JTable table;
    private DefaultTableModel tableModel;
    private List<ProductEntry> entry = new ArrayList<>();

    public EntryPanelView(ProductEntreyController productEntreyController, ProductController productController){
        this.productEntreyController = productEntreyController;
        this.productController = productController;
        initUi();
        loadData();
    }

    private void initUi(){
        setLayout(new BorderLayout(16, 16));

        String[] columns ={
            "codeEntry",
            "product",
            "dateEntry",
            "status",
            "totalWeight",
            "totalAmount",
            "delivererName",
            "vehiclePlate",
            "Phone"
        };

        tableModel = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

                table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);

        // Scroll pane centralizado
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões embaixo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        JButton editButton   = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");

        editButton.addActionListener(e -> editSelectedProduct());
        deleteButton.addActionListener(e -> deleteSelectedProduct());

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void editSelectedProduct() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Selecione um produto para excluir.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = table.convertRowIndexToModel(viewRow);
        ProductEntry selected = entry.get(modelRow);
        ProductEntry loaded = productEntreyController.findByIdWithCollections(selected.getCodeEntry());
            EntryFormDialog dialog = new EntryFormDialog(
            SwingUtilities.getWindowAncestor(this),
            "Editar Entrada",
            productEntreyController,
            productController,
            loaded
        );
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);
        dialog.setVisible(true);

        loadData();
    }

    private void deleteSelectedProduct() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Selecione um produto para excluir.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja excluir o produto selecionado?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int modelRow = table.convertRowIndexToModel(viewRow);
        ProductEntry selected = entry.get(modelRow);

        try {
            productEntreyController.Delete(selected.getCodeEntry());       
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao excluir produto:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData(){
        entry.clear();
        tableModel.setRowCount(0);

        try {
            entry.addAll(this.productEntreyController.getAll());
            for(ProductEntry e : entry){
                Object[] row={
                    e.getCodeEntry(),
                    e.getProduct(),
                    e.getDateEntry(),
                    e.getStatus(),
                    e.getTotalWeight(),
                    e.getTotalAmount(),
                    e.getDelivererName(),
                    e.getVehiclePlate(),
                    e.getPhone()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produtos:\n" + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
