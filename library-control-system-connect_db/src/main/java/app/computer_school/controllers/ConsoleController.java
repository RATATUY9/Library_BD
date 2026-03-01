package app.computer_school.controllers;

import app.computer_school.models.User;
import app.computer_school.system.database.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.computer_school.system.database.QueryBuilder;

public class ConsoleController
{
    private final Scanner scanner;
    private final DatabaseConnection connection;

    public ConsoleController() throws SQLException {
        this.scanner = new Scanner(System.in);

        this.connection = DatabaseConnection.getInstance();
    }

    public void run() throws SQLException {

        QueryBuilder<User> builder = User.query()
                .where("lastname", "ilike", "%Параво%");

        List<User> result = builder.find();

        for (User user : result) {
            System.out.println(user);
        }


        this.printWelcome();

        boolean shouldRun = true;

        while (shouldRun) {

            System.out.println("Menu:");

            String[] userItem = {"user", "User administration section"};
            String[] bookItem = {"book", "Book administration section"};

            ArrayList<String[]> menuItemsCollection = new ArrayList<String[]>();
            menuItemsCollection.add(userItem);
            menuItemsCollection.add(bookItem);

            this.printMenuItems(menuItemsCollection);

            this.printSeparator();

            System.out.print("Enter the command: ");

            String input = this.scanner.nextLine();

            this.printSeparator();

            if (this.ensureExit(input)) {
                System.out.println("Bye!");

                shouldRun = false;

                break;
            }

            if (!this.checkMenuItems(menuItemsCollection, input)) {
                System.out.println("Entered command is invalid: " + input);

                this.printSeparator();

                return;
            }

            System.out.println("Entered command: " + input);

            this.printSeparator();
        }


    }

    private void printWelcome()
    {
        String welcome = """
                ██╗░░██╗███████╗██╗░░░░░██╗░░░░░░█████╗░░░░░░░██████╗░██████╗░░█████╗░████████╗██╗░░██╗███████╗██████╗░██╗
                ██║░░██║██╔════╝██║░░░░░██║░░░░░██╔══██╗░░░░░░██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██║░░██║██╔════╝██╔══██╗██║
                ███████║█████╗░░██║░░░░░██║░░░░░██║░░██║░░░░░░██████╦╝██████╔╝██║░░██║░░░██║░░░███████║█████╗░░██████╔╝██║
                ██╔══██║██╔══╝░░██║░░░░░██║░░░░░██║░░██║██╗░░░██╔══██╗██╔══██╗██║░░██║░░░██║░░░██╔══██║██╔══╝░░██╔══██╗╚═╝
                ██║░░██║███████╗███████╗███████╗╚█████╔╝╚█║░░░██████╦╝██║░░██║╚█████╔╝░░░██║░░░██║░░██║███████╗██║░░██║██╗
                ╚═╝░░╚═╝╚══════╝╚══════╝╚══════╝░╚════╝░░╚╝░░░╚═════╝░╚═╝░░╚═╝░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝╚═╝""";

        System.out.println(welcome);
    }

    private void printSeparator()
    {
        System.out.println("|---------------------------------------------------------------|");
    }

    private boolean checkMenuItems(ArrayList<String[]> menuItems, String searchItem)
    {
        for (String[] item : menuItems) {
            if (item[0].equals(searchItem)) {
                return true;
            }
        }

        return false;
    }

    private void printMenuItems(ArrayList<String[]> menuItems)
    {
        for (String[] item : menuItems) {
            System.out.println(
                    String.format(
                            "%s:  %s",
                            item[0],
                            item[1]
                    )
            );
        }
    }

    private boolean ensureExit(String input)
    {
        return input.equals("exit");
    }
}
