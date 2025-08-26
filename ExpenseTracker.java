import java.util.*;

class Expense {
    String category;
    double amount;
    Date date;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.date = new Date(); // current date
    }

    @Override
    public String toString() {
        return date + " | " + category + " | ₹" + amount;
    }
}

public class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();

    // Add new expense
    public void addExpense(String category, double amount) {
        expenses.add(new Expense(category, amount));
        System.out.println("✅ Expense added successfully!");
    }

    // View all expenses
    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("\n--- All Expenses ---");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // View total spent
    public void viewTotal() {
        double total = expenses.stream().mapToDouble(e -> e.amount).sum();
        System.out.println("\n💰 Total Spent: ₹" + total);
    }

    // View expenses by category
    public void viewByCategory(String category) {
        System.out.println("\n--- Expenses in " + category + " ---");
        expenses.stream()
                .filter(e -> e.category.equalsIgnoreCase(category))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("\n=== Personal Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Spent");
            System.out.println("4. View by Category");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter amount: ₹");
                    double amt = sc.nextDouble();
                    tracker.addExpense(cat, amt);
                    break;

                case 2:
                    tracker.viewExpenses();
                    break;

                case 3:
                    tracker.viewTotal();
                    break;

                case 4:
                    System.out.print("Enter category to filter: ");
                    String c = sc.nextLine();
                    tracker.viewByCategory(c);
                    break;

                case 5:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
