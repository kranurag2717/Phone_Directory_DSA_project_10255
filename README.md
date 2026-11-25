# Student Contact Directory

**Problem Statement No.:** 24  
**Language:** Java  

A simple, menu-driven Java console application to manage student contacts. It supports adding, displaying, searching, updating, and deleting contacts, with automatic file persistence using `contacts.txt`.

---

## Features

- **Add Contact:** Stores student name, 10-digit phone number, and email address.
- **Phone Validation:** Ensures every phone number has exactly 10 numeric digits.
- **Duplicate Prevention:** Blocks duplicate phone numbers using a `HashMap`.
- **Display All Contacts:** Lists all saved contacts in the directory.
- **Search Contact:** Searches contacts by partial name (case-insensitive) or exact phone number.
- **Update Phone Number:** Updates an existing contact's phone number with validation and duplicate checks.
- **Delete Contact:** Removes a contact by name and keeps storage synchronized.
- **File Persistence:** Automatically loads contacts from `contacts.txt` at startup and saves all changes on exit.

---

## Concepts Used

- `ArrayList` (storing contact objects)
- `HashMap` (fast duplicate phone number detection)
- Linear Searching
- Modular Methods
- File Handling (`BufferedReader` and `BufferedWriter`)

---

## Project Structure

```text
├── Main.java                             # Complete Java source code
├── contacts.txt                          # Auto-generated file storing contact data
├── Student_Contact_Directory_Report.docx # Project documentation/report
└── README.md                             # Project overview and usage guide
```

---

## How to Run

### Prerequisites
- Java Development Kit (JDK 8 or higher)

### Steps
1. Clone or download this repository.
2. Open a terminal in the project folder.
3. Compile the Java file:
   ```bash
   javac Main.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

---

## Menu Options

1. **Add** – Add a new student contact
2. **Display** – Show all saved contacts
3. **Search** – Search by name or phone number
4. **Delete** – Delete a contact by name
5. **Update** – Update a contact's phone number
6. **Exit** – Save all contacts to `contacts.txt` and exit

---

## Test Cases Covered

| Test Case | Scenario | Expected Output |
| :--- | :--- | :--- |
| **1. Normal Input** | Add `Riya Sharma`, `9876543210`, `riya@gmail.com` | `Contact added. Riya Sharma \| 9876543210 \| riya@gmail.com` |
| **2. Boundary Input** | Delete the only contact (`Riya Sharma`) | `Contact deleted.`<br>`Directory is now empty.` |
| **3. Invalid Input** | Add `Aman Verma` with phone `12345` | `Invalid phone number. Enter 10 digits.` |
| **4. Valid Workflow** | Add `Aman Verma`, search `Aman`, update phone to `9000000000` | Adds contact, finds match, and prints `Contact updated. Aman Verma \| 9000000000` |