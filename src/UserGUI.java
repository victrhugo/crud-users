import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class UserGUI extends JFrame {
    private userRepo repository = new userRepo();
    private JTextField nameField, emailField;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserGUI() {
        setTitle("CRUD de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Campos de entrada
        nameField = new JTextField(15);
        emailField = new JTextField(15);

        // Tabela
        String[] columns = { "ID", "Nome", "Email" };
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(userTable);

        // Botões
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton clearButton = new JButton("Limpar");

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);

        // Eventos
        addButton.addActionListener(e -> {
            repository.addUser(nameField.getText(), emailField.getText());
            refreshTable();
            clearFields();
        });

        updateButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                repository.updateUser(id, nameField.getText(), emailField.getText());
                refreshTable();
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                repository.deleteUser(id);
                refreshTable();
                clearFields();
            }
        });

        clearButton.addActionListener(e -> clearFields());

        userTable.getSelectionModel().addListSelectionListener(e -> {
            int row = userTable.getSelectedRow();
            if (row >= 0) {
                nameField.setText((String) tableModel.getValueAt(row, 1));
                emailField.setText((String) tableModel.getValueAt(row, 2));
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (User u : repository.getAllUsers()) {
            tableModel.addRow(new Object[] { u.getId(), u.getName(), u.getEmail() });
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        userTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserGUI().setVisible(true);
        });
    }
}

