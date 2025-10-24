package com.alura.screenmatch.principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SwapiExplorerGUI extends JFrame {

    private JComboBox<String> comboCategoria;
    private JTextField campoBusqueda;
    private JTextArea areaResultado;
    private JButton botonBuscar;

    public SwapiExplorerGUI() {
        setTitle("Buscador SWAPI - Star Wars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 950);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(20, 20, 20)); // fondo oscuro

        // ===== PANEL SUPERIOR =====
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBackground(new Color(30, 30, 30));
        panelSuperior.setPreferredSize(new Dimension(950, 140)); // más alto

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel(" SWAPI Explorer");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(new Color(0, 153, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelSuperior.add(titulo, gbc);

        // Etiqueta Categoría
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel etiquetaCategoria = new JLabel("Categoría:");
        etiquetaCategoria.setForeground(Color.WHITE);
        etiquetaCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelSuperior.add(etiquetaCategoria, gbc);

        // ComboBox Categoría
        gbc.gridx = 1;
        comboCategoria = new JComboBox<>(new String[]{
                "films", "people", "planets", "species", "starships", "vehicles"
        });
        comboCategoria.setBackground(new Color(45, 45, 45));
        comboCategoria.setForeground(Color.WHITE);
        comboCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboCategoria.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255)));
        panelSuperior.add(comboCategoria, gbc);

        // Etiqueta Buscar
        gbc.gridx = 2;
        JLabel etiquetaBuscar = new JLabel("Buscar:");
        etiquetaBuscar.setForeground(Color.WHITE);
        etiquetaBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelSuperior.add(etiquetaBuscar, gbc);

        // Campo de texto
        gbc.gridx = 3;
        campoBusqueda = new JTextField(18);
        campoBusqueda.setBackground(new Color(45, 45, 45));
        campoBusqueda.setForeground(Color.WHITE);
        campoBusqueda.setCaretColor(Color.WHITE);
        campoBusqueda.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255)));
        campoBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelSuperior.add(campoBusqueda, gbc);

        // Botón Buscar
        botonBuscar = new JButton(" Buscar");
        botonBuscar.setBackground(new Color(0, 153, 255));
        botonBuscar.setForeground(Color.WHITE);
        botonBuscar.setFocusPainted(false);
        botonBuscar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botonBuscar.setPreferredSize(new Dimension(140, 40));
        botonBuscar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        botonBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // efecto hover
        botonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonBuscar.setBackground(new Color(0, 180, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonBuscar.setBackground(new Color(0, 153, 255));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSuperior.add(botonBuscar, gbc);

        add(panelSuperior, BorderLayout.NORTH);

        // ===== ÁREA DE RESULTADOS =====
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setBackground(new Color(25, 25, 25));
        areaResultado.setForeground(Color.WHITE);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaResultado.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255)));

        JScrollPane scrollPane = new JScrollPane(areaResultado);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Acción del botón
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEnSwapi();
            }
        });
    }

    // ===== MÉTODO PRINCIPAL DE BÚSQUEDA =====
    private void buscarEnSwapi() {
        String categoria = (String) comboCategoria.getSelectedItem();
        String termino = campoBusqueda.getText().trim();

        if (termino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un término de búsqueda.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String url = "https://swapi.dev/api/" + categoria + "/?search=" + termino;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            areaResultado.setText(" Resultados para \"" + termino + "\" en " + categoria + ":\n\n" + response.body());
        } catch (Exception ex) {
            areaResultado.setText(" Error al realizar la búsqueda: " + ex.getMessage());
        }
    }

    // ===== MÉTODO MAIN =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwapiExplorerGUI().setVisible(true);
        });
    }
}
