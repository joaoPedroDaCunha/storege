package br.com.project.storage.front.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.project.storage.back.controller.ProductController;
import br.com.project.storage.back.controller.ProductEntreyController;
import br.com.project.storage.back.model.ProductEntry;

public class ProductConferencePanelView extends JPanel{

    private final ProductEntreyController productEntreyController;
    private final ProductController productController;

    private JTable table;
    private DefaultTableModel tableModel;
    private List<ProductEntry> entry = new ArrayList<>();

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");


    public ProductConferencePanelView (ProductEntreyController productEntreyController, ProductController productController){
        this.productEntreyController = productEntreyController;
        this.productController = productController;
        initUi();
        loadData();
    }

    private void initUi(){
        setLayout(new BorderLayout(16, 16));

        String[] columns ={
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        JButton CheckButton   = new JButton("Conferir");

        CheckButton.addActionListener(e -> CheckSelectedProduct());

        buttonPanel.add(CheckButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Object CheckSelectedProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CheckSelectedProduct'");
    }

    private void loadData(){
        entry.clear();
        tableModel.setRowCount(0);

        try {
            entry.addAll(this.productEntreyController.getAllWaiting());
            for(ProductEntry e : entry){
                Object[] row={
                    e.getProduct(),
                    e.getDateEntry().format(fmt),
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
