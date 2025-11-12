/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BISMILLAH
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class KalkulatorCanggih1 extends JFrame implements ActionListener, KeyListener {

    private JTextField textField;
    private double angka1 = 0, angka2 = 0, hasil = 0;
    private String operator = "";

    public KalkulatorCanggih1() {
        setTitle("Kalkulator Canggih1");
        setSize(420, 550);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ====== Tampilan Layar ======
        textField = new JTextField();
        textField.setBounds(30, 40, 340, 50);
        textField.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        add(textField);

        // Tambahkan listener keyboard
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        // ====== Daftar Tombol ======
        String[] tombol = {
            "7", "8", "9", "÷", "√",
            "4", "5", "6", "×", "x²",
            "1", "2", "3", "-", "x³",
            "0", ".", "=", "+", "%",
            "mod", "sin", "cos", "tan", "C"
        };

        int x = 30, y = 120;
        for (int i = 0; i < tombol.length; i++) {
            JButton btn = new JButton(tombol[i]);
            btn.setBounds(x, y, 60, 50);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.addActionListener(this);
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);

            // ===== Pewarnaan berdasarkan jenis tombol =====
            if (tombol[i].matches("[0-9]")) {
                btn.setBackground(new Color(100, 149, 237)); // biru lembut untuk angka
            } else if (tombol[i].matches("[+\\-×÷=]")) {
                btn.setBackground(new Color(123, 104, 238)); // ungu muda untuk operator
            } else if (tombol[i].equals("C")) {
                btn.setBackground(new Color(7, 7, 7)); // merah untuk tombol clear
            } else if (tombol[i].matches("sin|cos|tan|mod|√|x²|x³|%")) {
                btn.setBackground(new Color(72, 209, 204)); // hijau toska untuk fungsi
            } else if (tombol[i].equals(".")) {
                btn.setBackground(new Color(176, 224, 230)); // biru muda untuk titik
                btn.setForeground(Color.BLACK);
            } else {
                btn.setBackground(new Color(230, 230, 250)); // default
                btn.setForeground(Color.BLACK);
            }

            add(btn);
            x += 70;
            if ((i + 1) % 5 == 0) {
                x = 30;
                y += 60;
            }
        }

        // ===== Warna background utama =====
        getContentPane().setBackground(new Color(245, 245, 255));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        prosesInput(e.getActionCommand());
    }

    // ====== Tambahan: Proses Input dari Keyboard ======
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        switch (key) {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
            case '.':
                textField.setText(textField.getText() + key);
                break;

            case '+': prosesInput("+"); break;
            case '-': prosesInput("-"); break;
            case '*': prosesInput("×"); break;
            case '/': prosesInput("÷"); break;
            case '%': prosesInput("%"); break;
            case '\n': prosesInput("="); break; // Enter untuk sama dengan

            case KeyEvent.VK_BACK_SPACE:
                if (textField.getText().length() > 0) {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // ====== Fungsi Utama untuk Proses ======
    private void prosesInput(String command) {
        DecimalFormat df = new DecimalFormat("0.######");

        try {
            switch (command) {
                case "C":
                    textField.setText("");
                    angka1 = angka2 = hasil = 0;
                    operator = "";
                    break;

                // ===== Operator dasar =====
                case "+":
                case "-":
                case "×":
                case "÷":
                case "mod":
                    angka1 = Double.parseDouble(textField.getText());
                    operator = command;
                    textField.setText(textField.getText() + " " + operator + " ");
                    break;

                // ===== Hasil =====
                case "=":
                    String[] bagian = textField.getText().split(" ");
                    if (bagian.length < 3) return;

                    angka1 = Double.parseDouble(bagian[0]);
                    operator = bagian[1];
                    angka2 = Double.parseDouble(bagian[2]);

                    switch (operator) {
                        case "+": hasil = angka1 + angka2; break;
                        case "-": hasil = angka1 - angka2; break;
                        case "×": hasil = angka1 * angka2; break;
                        case "÷": hasil = angka1 / angka2; break;
                        case "mod": hasil = angka1 % angka2; break;
                    }

                    textField.setText(df.format(hasil));
                    break;

                // ===== Fungsi matematika =====
                case "√":
                    hasil = Math.sqrt(Double.parseDouble(textField.getText()));
                    textField.setText(df.format(hasil));
                    break;

                case "x²":
                    hasil = Math.pow(Double.parseDouble(textField.getText()), 2);
                    textField.setText(df.format(hasil));
                    break;

                case "x³":
                    hasil = Math.pow(Double.parseDouble(textField.getText()), 3);
                    textField.setText(df.format(hasil));
                    break;

                case "%":
                    hasil = Double.parseDouble(textField.getText()) / 100;
                    textField.setText(df.format(hasil));
                    break;

                case "sin":
                    hasil = Math.sin(Math.toRadians(Double.parseDouble(textField.getText())));
                    textField.setText(df.format(hasil));
                    break;

                case "cos":
                    hasil = Math.cos(Math.toRadians(Double.parseDouble(textField.getText())));
                    textField.setText(df.format(hasil));
                    break;

                case "tan":
                    hasil = Math.tan(Math.toRadians(Double.parseDouble(textField.getText())));
                    textField.setText(df.format(hasil));
                    break;

                // ===== Angka dan titik =====
                default:
                    textField.setText(textField.getText() + command);
                    break;
            }
        } catch (Exception ex) {
            textField.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KalkulatorCanggih1::new);
    }
}