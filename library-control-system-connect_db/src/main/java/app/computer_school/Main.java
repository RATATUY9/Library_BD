package app.computer_school;

import app.computer_school.controllers.ConsoleController;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String args[]) throws SQLException {
        ConsoleController controller = new ConsoleController();



        controller.run();
    }
}
