package phamacy;

import java.util.HashMap;
import java.util.Scanner;

public class phamacy {

	 // Dummy data for patients
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

    // Dummy data for available drugs and their prices
    private static final HashMap<String, Double> availableDrugs = new HashMap<>();
    static {
        availableDrugs.put("Drug1", 10.0);
        availableDrugs.put("Drug2", 20.0);
        availableDrugs.put("Drug3", 30.0);
        availableDrugs.put("Drug4", 40.0);
        availableDrugs.put("Cast", 50.0);
        availableDrugs.put("Pain medication", 15.0);
        availableDrugs.put("Antibiotics", 25.0);
        availableDrugs.put("Insulin", 75.0);
        availableDrugs.put("Blood sugar monitor", 40.0);
        availableDrugs.put("Dietary supplements", 30.0);
        availableDrugs.put("Inhaler", 60.0);
        availableDrugs.put("Antihistamines", 10.0);
        availableDrugs.put("Topical cream", 20.0);
        availableDrugs.put("Stress management techniques", 0.0); // Free resources
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        Patient patient = findPatient(patientName);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Prescription for " + patient.getName() + ":");
        System.out.println("Disease: " + patient.getDisease());

        double totalCost = 0.0;
        for (String drug : patient.getPrescription()) {
            if (availableDrugs.containsKey(drug)) {
                double price = availableDrugs.get(drug);
                System.out.println("* " + drug + ": $" + price);
                totalCost += price;
            } else {
                System.out.println("* " + drug + ": Out of stock");
            }
        }

        System.out.println("Total cost: $" + totalCost);
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
}
