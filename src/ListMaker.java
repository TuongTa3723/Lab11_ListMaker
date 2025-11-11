import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    // Shared state
    private static final ArrayList<String> list = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Lab 11: ListMaker ===");
        boolean done = false;

        while (!done) {
            printMenuAndList();

            String choice = SafeInput.getRegExString(in,
                    "Choose [A]dd, [D]elete, [I]nsert, [P]rint, [Q]uit",
                    "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    doAdd();
                    break;
                case "D":
                    doDelete();
                    break;
                case "I":
                    doInsert();
                    break;
                case "P":
                    doPrint();
                    break;
                case "Q":
                    boolean reallyQuit = SafeInput.getYNConfirm(in, "Are you sure you want to quit");
                    if (reallyQuit) {
                        done = true;
                    }
                    break;
            }
        }

        System.out.println("Goodbye!");
    }


    private static void doAdd() {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to ADD");
        list.add(item);
        System.out.println("Added: \"" + item + "\"");
    }

    private static void doDelete() {
        if (list.isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }
        printNumberedList();

        int choice = SafeInput.getRangedInt(in,
                "Enter the ITEM NUMBER to DELETE",
                1, list.size());

        String removed = list.remove(choice - 1);
        System.out.println("Deleted: \"" + removed + "\"");
    }

    private static void doInsert() {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to INSERT");

        int where;
        if (list.isEmpty()) {
            where = 1;
            list.add(item);
            System.out.println("List was empty; inserted at position 1: \"" + item + "\"");
            return;
        } else {
            printNumberedList();
            where = SafeInput.getRangedInt(in,
                    "Insert BEFORE which item number (1.." + (list.size() + 1) + ")?",
                    1, list.size() + 1);
        }

        list.add(where - 1, item);
        System.out.println("Inserted at position " + where + ": \"" + item + "\"");
    }

    private static void doPrint() {
        if (list.isEmpty()) {
            System.out.println("[List is empty]");
        } else {
            printNumberedList();
        }
    }


    private static void printMenuAndList() {
        System.out.println();
        System.out.println("--------- CURRENT LIST ---------");
        if (list.isEmpty()) {
            System.out.println("[List is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%2d) %s%n", i + 1, list.get(i));
            }
        }
        System.out.println("--------------------------------");
        System.out.println("[A]dd  [D]elete  [I]nsert  [P]rint  [Q]uit");
    }

    private static void printNumberedList() {
        System.out.println("----- Numbered List -----");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%2d) %s%n", i + 1, list.get(i));
        }
        System.out.println("-------------------------");
    }
}