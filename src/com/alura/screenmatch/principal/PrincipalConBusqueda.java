package com.alura.screenmatch.principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Buscador SWAPI - Star Wars API
 * Compatible con modo consola y modo interfaz gráfica moderna.
 */
public class PrincipalConBusqueda {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final HttpClient client = HttpClient.newHttpClient();

    // === MÉTODO PRINCIPAL DE CONSULTA ===
    public static String buscarEnSwapi(String categoria, String idOrQuery) throws Exception {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }

        String direccion;
        if (idOrQuery.matches("\\d+")) {
            direccion = "https://swapi.py4e.com/api/" + categoria + "/" + idOrQuery + "/";
        } else {
            direccion = "https://swapi.py4e.com/api/" + categoria + "/?search=" + idOrQuery.replace(" ", "+");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error HTTP " + response.statusCode() + ": no se pudo obtener datos.");
        }

        return response.body();
    }

    // === RESUMEN LEGIBLE ===
    private static String generarResumen(Map<String, Object> data, String categoria) {
        StringBuilder sb = new StringBuilder("\n===  RESUMEN ===\n");

        switch (categoria) {
            case "films" -> {
                sb.append(" Título: ").append(data.get("title")).append("\n");
                sb.append(" Estreno: ").append(data.get("release_date")).append("\n");
                sb.append(" Director: ").append(data.get("director")).append("\n");
                sb.append(" Episodio: ").append(data.get("episode_id")).append("\n");
            }
            case "people" -> {
                sb.append(" Nombre: ").append(data.get("name")).append("\n");
                sb.append(" Peso: ").append(data.get("mass")).append("\n");
                sb.append(" Altura: ").append(data.get("height")).append("\n");
                sb.append(" Color de ojos: ").append(data.get("eye_color")).append("\n");
                sb.append(" Género: ").append(data.get("gender")).append("\n");
            }
            case "planets" -> {
                sb.append(" Nombre: ").append(data.get("name")).append("\n");
                sb.append(" Clima: ").append(data.get("climate")).append("\n");
                sb.append(" Terreno: ").append(data.get("terrain")).append("\n");
                sb.append(" Población: ").append(data.get("population")).append("\n");
            }
            case "starships" -> {
                sb.append(" Nombre: ").append(data.get("name")).append("\n");
                sb.append(" Modelo: ").append(data.get("model")).append("\n");
                sb.append(" Fabricante: ").append(data.get("manufacturer")).append("\n");
                sb.append(" Costo en créditos: ").append(data.get("cost_in_credits")).append("\n");
            }
            case "vehicles" -> {
                sb.append(" Nombre: ").append(data.get("name")).append("\n");
                sb.append(" Modelo: ").append(data.get("model")).append("\n");
                sb.append(" Fabricante: ").append(data.get("manufacturer")).append("\n");
                sb.append(" Capacidad: ").append(data.get("crew")).append("\n");
            }
            case "species" -> {
                sb.append(" Nombre: ").append(data.get("name")).append("\n");
                sb.append(" Clasificación: ").append(data.get("classification")).append("\n");
                sb.append(" Designación: ").append(data.get("designation")).append("\n");
                sb.append(" Lenguaje: ").append(data.get("language")).append("\n");
            }
        }
        return sb.toString();
    }

    // === INTERFAZ GRÁFICA MODERNA ===
    public static class VentanaStarWars extends JFrame {

        private final JComboBox<String> comboCategoria;
        private final JTextField campoBusqueda;
        private final JTextArea areaResultado;
        private final JButton botonBuscar;

        public VentanaStarWars() {
            // Configuración general de la ventana
            setTitle(" Buscador SWAPI - Star Wars");
            setSize(750, 550);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(15, 15));
            setResizable(false);

            // === Paleta de colores ===
            Color fondoPrincipal = new Color(30, 30, 30);
            Color fondoPanel = new Color(45, 45, 45);
            Color textoPrincipal = new Color(230, 230, 230);
            Color azulNeon = new Color(80, 180, 255);

            getContentPane().setBackground(fondoPrincipal);

            // === Panel superior (búsqueda) ===
            JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
            panelSuperior.setBackground(fondoPanel);
            panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel titulo = new JLabel(" SWAPI Explorer");
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
            titulo.setForeground(azulNeon);

            JLabel labelCategoria = new JLabel("Categoría:");
            labelCategoria.setForeground(textoPrincipal);
            labelCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            comboCategoria = new JComboBox<>(new String[]{
                    "films", "people", "planets", "starships", "vehicles", "species"
            });
            comboCategoria.setBackground(new Color(60, 60, 60));
            comboCategoria.setForeground(textoPrincipal);
            comboCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            comboCategoria.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JLabel labelBuscar = new JLabel("Buscar:");
            labelBuscar.setForeground(textoPrincipal);
            labelBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            campoBusqueda = new JTextField(18);
            campoBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campoBusqueda.setBackground(new Color(50, 50, 50));
            campoBusqueda.setForeground(Color.WHITE);
            campoBusqueda.setCaretColor(azulNeon);
            campoBusqueda.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(azulNeon, 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            botonBuscar = new JButton(" Buscar");
            botonBuscar.setFocusPainted(false);
            botonBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
            botonBuscar.setBackground(azulNeon);
            botonBuscar.setForeground(Color.BLACK);
            botonBuscar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            botonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            // Efecto hover
            botonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    botonBuscar.setBackground(new Color(100, 200, 255));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    botonBuscar.setBackground(azulNeon);
                }
            });

            // Agregar componentes
            panelSuperior.add(titulo);
            panelSuperior.add(labelCategoria);
            panelSuperior.add(comboCategoria);
            panelSuperior.add(labelBuscar);
            panelSuperior.add(campoBusqueda);
            panelSuperior.add(botonBuscar);
            add(panelSuperior, BorderLayout.NORTH);

            // === Área de resultados ===
            areaResultado = new JTextArea();
            areaResultado.setEditable(false);
            areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
            areaResultado.setBackground(new Color(25, 25, 25));
            areaResultado.setForeground(new Color(180, 255, 180));
            areaResultado.setCaretColor(azulNeon);
            areaResultado.setMargin(new Insets(15, 15, 15, 15));

            JScrollPane scroll = new JScrollPane(areaResultado);
            scroll.setBorder(BorderFactory.createLineBorder(azulNeon));
            scroll.getViewport().setBackground(new Color(25, 25, 25));

            add(scroll, BorderLayout.CENTER);

            // Acción del botón
            botonBuscar.addActionListener(this::buscarAccion);
        }

        private void buscarAccion(ActionEvent e) {
            String categoria = (String) comboCategoria.getSelectedItem();
            String query = campoBusqueda.getText().trim();

            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID o nombre.", "⚠️ Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {
                        areaResultado.setText("🔎 Buscando en SWAPI...\n");
                        String json = buscarEnSwapi(categoria, query);
                        Map<String, Object> data = gson.fromJson(json, Map.class);
                        String resumen = generarResumen(data, categoria);
                        areaResultado.setText(resumen);

                        try (FileWriter writer = new FileWriter("swapi_resultados_gui.json", true)) {
                            writer.write(gson.toJson(data));
                            writer.write("\n");
                        }

                    } catch (Exception ex) {
                        areaResultado.setText(" Error: " + ex.getMessage());
                    }
                    return null;
                }
            };
            worker.execute();
        }
    }

    // === MAIN: PERMITE ELEGIR MODO DE EJECUCIÓN ===
    public static void main(String[] args) throws IOException, InterruptedException {
        String modo = JOptionPane.showInputDialog("""
                Selecciona el modo de ejecución:
                1 - Consola
                2 - Interfaz gráfica
                """);

        if ("2".equals(modo)) {
            SwingUtilities.invokeLater(() -> new VentanaStarWars().setVisible(true));
        } else {
            ejecutarConsola();
        }
    }

    // === MODO CONSOLA ORIGINAL ===
    public static void ejecutarConsola() throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        List<Map<String, Object>> resultados = new ArrayList<>();

        while (true) {
            System.out.println("\n===  CONSULTA API STAR WARS (SWAPI) ===");
            System.out.println("1. Films");
            System.out.println("2. Characters");
            System.out.println("3. Planets");
            System.out.println("4. Starships");
            System.out.println("5. Vehicles");
            System.out.println("6. Species");
            System.out.println("7. Salir");
            System.out.print(" Opción: ");
            String opcion = lectura.nextLine();

            if (opcion.equals("7") || opcion.equalsIgnoreCase("salir")) break;

            String categoria = switch (opcion) {
                case "1" -> "films";
                case "2" -> "people";
                case "3" -> "planets";
                case "4" -> "starships";
                case "5" -> "vehicles";
                case "6" -> "species";
                default -> null;
            };

            if (categoria == null) {
                System.out.println("️ Opción no válida, intente nuevamente.");
                continue;
            }

            System.out.print("Ingrese ID o nombre a buscar: ");
            String entrada = lectura.nextLine();

            try {
                String json = buscarEnSwapi(categoria, entrada);
                Map<String, Object> data = gson.fromJson(json, Map.class);
                resultados.add(data);

                System.out.println(generarResumen(data, categoria));

            } catch (Exception e) {
                System.out.println(" Error al consultar la API: " + e.getMessage());
            }
        }

        try (FileWriter writer = new FileWriter("swapi_resultados.json")) {
            writer.write(gson.toJson(resultados));
            System.out.println("\n Archivo 'swapi_resultados.json' guardado exitosamente.");
        }

        System.out.println("\n Programa finalizado.");
    }
}
