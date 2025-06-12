import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        userRepo repo = new userRepo();
        int option;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Create user");
            System.out.println("2 - List users");
            System.out.println("3 - Update users");
            System.out.println("4 - Delete users");
            System.out.println("0 - Sair");
            System.out.println("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    repo.addUser(name, email);
                }
                case 2 -> {
                    System.out.println("Users: ");
                    for (User user : repo.getAllUsers()) {
                        System.out.println(user);
                    }
                }
                case 3 -> {
                    System.out.println("User ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("New name: ");
                    String name = scanner.nextLine();
                    System.out.println("New email: ");
                    String email = scanner.nextLine();
                    boolean updated = repo.updateUser(id, name, email);
                    System.out.println(updated ? "Updated succesfully" : "User not founded");
                }
                case 4 -> {
                    System.out.println("User id: ");
                    int id = scanner.nextInt();
                    boolean deleted = repo.deleteUser(id);
                    System.out.println(deleted ? "User deleted." : "User not founded");
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);

        scanner.close();
    }
}