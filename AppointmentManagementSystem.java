import java.util.*;

class Visitor {
    private String name;
    private int age;
    private String phoneNumber;
    private String appointmentDate;
    private String appointmentTimeSlot;

    // Constructor
    public Visitor(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getter and setter methods for encapsulation
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTimeSlot() {
        return appointmentTimeSlot;
    }

    public void setAppointmentDetails(String appointmentDate, String appointmentTimeSlot) {
        this.appointmentDate = appointmentDate;
        this.appointmentTimeSlot = appointmentTimeSlot;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public void setAge(int age2) {
        this.age = age2;

    }

    public void setPhoneNumber(String phoneNumber2) {
        this.phoneNumber = phoneNumber2;
    }
}

class Clinic {
    private List<Visitor> visitors;
    private Map<String, List<String>> appointmentSchedule;

    // Constructor
    public Clinic() {
        visitors = new ArrayList<>();
        appointmentSchedule = new HashMap<>();
    }

    // Method to add a new visitor
    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    public void viewVisitorsList() {
        System.out.println("Visitors List:");
        for (int i = 0; i < visitors.size(); i++) {
            Visitor visitor = visitors.get(i);
            System.out.println((i + 1) + ". Name: " + visitor.getName() + ", Age: " + visitor.getAge() + ", Phone: "
                    + visitor.getPhoneNumber());
        }
    }

    // Method to view appointment schedule for a day
    public void viewAppointmentSchedule(String date) {
        List<String> timeSlots = appointmentSchedule.getOrDefault(date, new ArrayList<>());
        System.out.println("Appointment Schedule for " + date + ":");
        for (int i = 0; i < timeSlots.size(); i++) {
            System.out.println((i + 1) + ". " + timeSlots.get(i));
        }
    }

    // Method to book an appointment
    public void bookAppointment(String date, String timeSlot, Visitor visitor) {
        if (!appointmentSchedule.containsKey(date)) {
            appointmentSchedule.put(date, new ArrayList<>());
        }
        appointmentSchedule.get(date).add(timeSlot);
        visitor.setAppointmentDetails(date, timeSlot);
        System.out.println(
                "Visitor " + visitor.getName() + "'s appointment on " + date + " at " + timeSlot + " has been booked.");
    }

    // Method to edit visitor details
    public void editVisitorDetails(int index, String name, int age, String phoneNumber) {
        Visitor visitor = visitors.get(index);
        visitor.setName(name);
        visitor.setAge(age);
        visitor.setPhoneNumber(phoneNumber);
        System.out.println("Visitor details updated successfully.");
    }

    // Method to edit/cancel appointment
    public void editOrCancelAppointment(int index, String appointmentDate, String appointmentTimeSlot) {
        Visitor visitor = visitors.get(index);
        if (visitor.getAppointmentDate() != null && visitor.getAppointmentTimeSlot() != null) {
            String oldAppointmentDate = visitor.getAppointmentDate();
            String oldAppointmentTimeSlot = visitor.getAppointmentTimeSlot();
            appointmentSchedule.get(oldAppointmentDate).remove(oldAppointmentTimeSlot);
        }
        if (!appointmentSchedule.containsKey(appointmentDate)) {
            appointmentSchedule.put(appointmentDate, new ArrayList<>());
        }
        appointmentSchedule.get(appointmentDate).add(appointmentTimeSlot);
        visitor.setAppointmentDetails(appointmentDate, appointmentTimeSlot);
        System.out.println("Visitor's appointment updated successfully.");
    }
}

public class AppointmentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic clinic = new Clinic();

        System.out.println("Welcome to the Small Clinic Appointment Management System\n");

        while (true) {
            System.out.println("1. View Visitors List");
            System.out.println("2. Add New Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("\nPlease enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    clinic.viewVisitorsList();
                case 2:
                    System.out.print("\nEnter Visitor's Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Visitor's Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Visitor's Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Select Appointment Date (DD-MM-YYYY): ");
                    String appointmentDate = scanner.nextLine();

                    System.out.println("Available Time Slots:");
                    System.out.println("1. 09:00 AM - 10:00 AM");
                    System.out.println("2. 11:00 AM - 12:00 PM");
                    System.out.print("Select Time Slot: ");
                    int timeSlotChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    String timeSlot;
                    if (timeSlotChoice == 1) {
                        timeSlot = "09:00 AM - 10:00 AM";
                    } else {
                        timeSlot = "11:00 AM - 12:00 PM";
                    }

                    Visitor newVisitor = new Visitor(name, age, phoneNumber);
                    clinic.addVisitor(newVisitor);
                    clinic.bookAppointment(appointmentDate, timeSlot, newVisitor);
                    break;
                case 4:
                    System.out.print("\nEnter Appointment Date (DD-MM-YYYY): ");
                    String date = scanner.nextLine();
                    clinic.viewAppointmentSchedule(date);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}