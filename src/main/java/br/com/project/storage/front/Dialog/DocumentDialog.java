package br.com.project.storage.front.Dialog;

import br.com.project.storage.back.model.AuxiliaryDocument;

import javax.swing.*;
import java.awt.*;

public class DocumentDialog extends JDialog {
    private AuxiliaryDocument result;

    public DocumentDialog(Window owner) {
        super(owner, "Novo Documento", ModalityType.APPLICATION_MODAL);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtType = new JTextField(15);
        JTextField txtInfo = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Tipo de Documento:"), gbc);
        gbc.gridx = 1;
        add(txtType, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Informações:"), gbc);
        gbc.gridx = 1;
        add(txtInfo, gbc);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> {
            String type = txtType.getText().trim();
            String info = txtInfo.getText().trim();
            if(type.isEmpty() || info.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            result = new AuxiliaryDocument(type, info);
            dispose();
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnOk, gbc);

        pack();
        setLocationRelativeTo(owner);
    }

    public AuxiliaryDocument getResult() {
        return result;
    }
}