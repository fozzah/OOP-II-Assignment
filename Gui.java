package learn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Gui {
    private static final Patient[] patients = {
            new Patient("Mason King", "Flu", new String[]{"Drug1", "Drug2"}),
            new Patient("Tyson Deluke", "Cold", new String[]{"Drug3", "Drug4", "UnavailableDrug"}),
            new Patient("Halsey Ikea", "Fracture", new String[]{"Cast", "Pain medication", "Antibiotics"}),
            new Patient("Brian Kinuthia", "Diabetes", new String[]{"Insulin", "Blood sugar monitor", "Dietary supplements"}),
            new Patient("Ibrahim Kanuhe", "Asthma", new String[]{"Antihistamines", "Salbutamol"}),
            new Patient("Linzzy Ambani", "Arthritis", new String[]{"Pain medication", "Joint supplements", "Physical therapy referral"}),
            new Patient("Duncan Areri", "Anxiety", new String[]{"Antidepressants", "Therapy referral"}),
            new Patient("Eve Omondi", "Depression", new String[]{"Antidepressants", "Therapy referral", "Support group information"}),
            new Patient("Carol Chepkemboi", "Headache", new String[]{"Pain medication", "Stress management techniques"}),
            new Patient("Abdallah Kombo", "Skin rash", new String[]{"Topical cream", "Antihistamines", "Allergy testing referral"}),

    };

    private static final HashMap<String, Double> availableDrugs = new HashMap<>();
    static {
        availableDrugs.put("Drug1", 10.0);
        availableDrugs.put("Drug2", 20.0);
        availableDrugs.put("Drug3", 30.0);
        availableDrugs.put("Drug4", 40.0);
        availableDrugs.put("Cast", 50.0);
        availableDrugs.put("Antidepressants", 50.0);
        availableDrugs.put("Pain medication", 15.0);
        availableDrugs.put("Antibiotics", 25.0);
        availableDrugs.put("Insulin", 75.0);
        availableDrugs.put("Blood sugar monitor", 40.0);
        availableDrugs.put("Dietary supplements", 30.0);
        availableDrugs.put("Inhaler", 60.0);
        availableDrugs.put("Antihistamines", 10.0);
        availableDrugs.put("Topical cream", 20.0);
        availableDrugs.put("Stress management techniques", 0.0);
    }

    private static Patient findPatient(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    private static class Patient {
        private String name;
        private String disease;
        private String[] prescription;

        public Patient(String name, String disease, String[] prescription) {
            this.name = name;
            this.disease = disease;
            this.prescription = prescription;
        }

        public String getName() {
            return name;
        }

        public String getDisease() {
            return disease;
        }

        public String[] getPrescription() {
            return prescription;
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Pharmacy Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JMenuBar ob = new JMenuBar();
        JMenu ob1 = new JMenu("FILE");
        JMenu ob2 = new JMenu("Help");
        ob.add(ob1);
        ob.add(ob2);

        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        ob1.add(m11);
        ob1.add(m22);

        JPanel labelInputPanel = new JPanel();
        labelInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Enter patient name");
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(300, 28));
        JButton send = new JButton("Send");

        JPanel patientInfoPanel = new JPanel();
        patientInfoPanel.setLayout(new BoxLayout(patientInfoPanel, BoxLayout.Y_AXIS));

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = tf.getText();
                Patient patient = findPatient(patientName);
                if (patient != null) {
                    displayPatientInfo(patientInfoPanel, patient);
                } else {
                    JOptionPane.showMessageDialog(frame, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        labelInputPanel.add(label);
        labelInputPanel.add(tf);
        labelInputPanel.add(send);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(labelInputPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(patientInfoPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.getContentPane().add(BorderLayout.NORTH, ob);

        frame.setVisible(true);
    }

    private static void displayPatientInfo(JPanel patientInfoPanel, Patient patient) {
        patientInfoPanel.removeAll(); // Clear previous patient information

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        JLabel nameLabel = new JLabel("Name: " + patient.getName());
        patientInfoPanel.add(nameLabel, gbc);

        gbc.gridy++;
        JLabel diseaseLabel = new JLabel("Disease: " + patient.getDisease());
        patientInfoPanel.add(diseaseLabel, gbc);

        gbc.gridy++;
        JLabel prescriptionLabel = new JLabel("Prescription:");
        patientInfoPanel.add(prescriptionLabel, gbc);

        for (String drug : patient.getPrescription()) {
            gbc.gridy++;
            if (availableDrugs.containsKey(drug)) {
                double price = availableDrugs.get(drug);
                JLabel drugLabel = new JLabel("* " + drug + ": $" + price);
                patientInfoPanel.add(drugLabel, gbc);
            } else {
                JLabel drugLabel = new JLabel("* " + drug + ": Out of stock");
                patientInfoPanel.add(drugLabel, gbc);
            }
        }

        patientInfoPanel.revalidate();
        patientInfoPanel.repaint();
    }
}