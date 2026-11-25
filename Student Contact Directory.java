import java.io.*;
import java.util.*;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

public class Main {
    static ArrayList<Contact> contacts = new ArrayList<>();
    static HashMap<String, Contact> phoneMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        loadContacts();

        while (true) {
            System.out.println("\n1. Add\n2. Display\n3. Search\n4. Delete\n5. Update\n6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    saveContacts();
                    System.out.println("Data saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    static void addContact() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. Enter 10 digits.");
            return;
        }

        if (phoneMap.containsKey(phone)) {
            System.out.println("Duplicate phone number. Contact not added.");
            return;
        }

        System.out.print("Email: ");
        String email = sc.nextLine();

        Contact c = new Contact(name, phone, email);
        contacts.add(c);
        phoneMap.put(phone, c);

        System.out.println("Contact added. " + name + " | " + phone + " | " + email);
    }

    static void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Directory is empty.");
            return;
        }

        for (Contact c : contacts) {
            System.out.println(c.name + " | " + c.phone + " | " + c.email);
        }
    }

    static void searchContact() {
        System.out.print("Enter name or phone to search: ");
        String query = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Contact c : contacts) {
            if (c.name.toLowerCase().contains(query) || c.phone.equals(query)) {
                System.out.println("Found: " + c.name + " | " + c.phone + " | " + c.email);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    static void deleteContact() {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.name.equalsIgnoreCase(name)) {
                contacts.remove(i);
                phoneMap.remove(c.phone);
                System.out.println("Contact deleted.");
                if (contacts.isEmpty()) {
                    System.out.println("Directory is now empty.");
                }
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    static void updateContact() {
        System.out.print("Enter name to update phone: ");
        String name = sc.nextLine();

        for (Contact c : contacts) {
            if (c.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new phone: ");
                String newPhone = sc.nextLine();

                if (!isValidPhone(newPhone)) {
                    System.out.println("Invalid phone number. Enter 10 digits.");
                    return;
                }

                if (phoneMap.containsKey(newPhone)) {
                    System.out.println("Duplicate phone number. Update failed.");
                    return;
                }

                phoneMap.remove(c.phone);
                c.phone = newPhone;
                phoneMap.put(newPhone, c);

                System.out.println("Contact updated. " + c.name + " | " + c.phone);
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    static void loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    Contact c = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(c);
                    phoneMap.put(c.phone, c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts.");
        }
    }

    static void saveContacts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contacts) {
                bw.write(c.name + "|" + c.phone + "|" + c.email);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }
}