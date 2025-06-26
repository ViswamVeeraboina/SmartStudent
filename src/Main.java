public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();

        if (adminService.login()) {
            adminService.showMenu();
        } else {
            System.out.println("🔐 Access denied. Exiting...");
        }
    }
}
