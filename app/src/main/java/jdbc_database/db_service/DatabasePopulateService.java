package jdbc_database.db_service;

import jdbc_database.settings.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabasePopulateService {

    private PreparedStatement insertIntoWorkerTable;
    private PreparedStatement insertIntoClientTable;
    private PreparedStatement insertIntoProjectTable;
    private PreparedStatement insertIntoProjectWorkerTable;

    public DatabasePopulateService() throws SQLException {
        Connection connection = Database.getInstance().getConnection();

//        INSERT INTO WORKER TABLE
        String sqlInsertIntoWorkerTable = "INSERT INTO worker (name, birthday, level, salary) VALUES(?, ?, ?, ?)";
        this.insertIntoWorkerTable = connection.prepareStatement(sqlInsertIntoWorkerTable);

//        INSERT INTO CLIENT TABLE
        String sqlInsertIntoClientTable = "INSERT INTO client (name) VALUES(?)";
        this.insertIntoClientTable = connection.prepareStatement(sqlInsertIntoClientTable);

//        INSERT INTO PROJECT TABLE
        String sqlInsertIntoProjectTable = "INSERT INTO project (client_id, start_date, finish_date) VALUES(?, ?, ?)";
        this.insertIntoProjectTable = connection.prepareStatement(sqlInsertIntoProjectTable);

//        INSERT INTO PROJECT_WORKER TABLE
        String sqlInsertIntoProjectWorkerTable = "INSERT INTO project_worker (project_id, worker_id) VALUES(?, ?)";
        this.insertIntoProjectWorkerTable = connection.prepareStatement(sqlInsertIntoProjectWorkerTable);
    }

    public boolean createWorkerTable(String name, LocalDate birthday, String level, int salary) {
        try {
            insertIntoWorkerTable.setString(1, name);
            insertIntoWorkerTable.setString(2, birthday.toString());
            insertIntoWorkerTable.setString(3, level);
            insertIntoWorkerTable.setInt(4, salary);
            return insertIntoWorkerTable.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createClientTable(String name) {
        try {
            insertIntoClientTable.setString(1, name);
            return insertIntoClientTable.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createProjectTable(long client_id, LocalDate start_date, LocalDate finish_date) {
        try {
            insertIntoProjectTable.setLong(1, client_id);
            insertIntoProjectTable.setString(2, start_date.toString());
            insertIntoProjectTable.setString(3, finish_date.toString());
            return insertIntoProjectTable.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createProjectWorkerTable(int project_id, int worker_id) {
        try {
            insertIntoProjectWorkerTable.setInt(1, project_id);
            insertIntoProjectWorkerTable.setInt(2, worker_id);
            return insertIntoProjectWorkerTable.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();

        databasePopulateService.
                createWorkerTable("Serhii", LocalDate.parse("1994-12-31"), "Senior", 5500);
        databasePopulateService.
                createWorkerTable("Kate", LocalDate.parse("1994-01-01"), "Middle", 5900);
        databasePopulateService.
                createWorkerTable("Dmitro", LocalDate.parse("1996-04-13"), "Middle", 2500);
        databasePopulateService.
                createWorkerTable("Aziz", LocalDate.parse("1996-08-25"), "Junior", 800);
        databasePopulateService.
                createWorkerTable("Oleg", LocalDate.parse("1996-02-15"), "Trainee", 400);
        databasePopulateService.
                createWorkerTable("Roma", LocalDate.parse("1997-05-18"), "Junior", 900);
        databasePopulateService.
                createWorkerTable("Andrew", LocalDate.parse("1997-05-18"), "Junior", 900);
        databasePopulateService.
                createWorkerTable("Valeriy", LocalDate.parse("1975-12-14"), "Senior", 7000);
        databasePopulateService.
                createWorkerTable("Diana", LocalDate.parse("1999-04-25"), "Junior", 700);
        databasePopulateService.
                createWorkerTable("Den", LocalDate.parse("1996-07-13"), "Middle", 1500);
        databasePopulateService.
                createWorkerTable("Alex", LocalDate.parse("1991-12-03"), "Senior", 7000);
        databasePopulateService.
                createWorkerTable("Oleksandr", LocalDate.parse("1999-04-25"), "Junior", 800);

        databasePopulateService.createClientTable("John");
        databasePopulateService.createClientTable("Skot");
        databasePopulateService.createClientTable("Luke");
        databasePopulateService.createClientTable("Kevin");
        databasePopulateService.createClientTable("Rayan");

        databasePopulateService.
                createProjectTable(1, LocalDate.parse("2022-01-01"), LocalDate.parse("2022-06-06"));
        databasePopulateService.
                createProjectTable(1, LocalDate.parse("2022-03-13"), LocalDate.parse("2022-12-30"));
        databasePopulateService.
                createProjectTable(2, LocalDate.parse("2022-02-14"), LocalDate.parse("2022-07-15"));
        databasePopulateService.
                createProjectTable(4, LocalDate.parse("2022-11-15"), LocalDate.parse("2023-06-03"));
        databasePopulateService.
                createProjectTable(5, LocalDate.parse("2022-05-01"), LocalDate.parse("2022-08-30"));
        databasePopulateService.
                createProjectTable(3, LocalDate.parse("2022-02-14"), LocalDate.parse("2022-07-15"));
        databasePopulateService.
                createProjectTable(5, LocalDate.parse("2022-08-05"), LocalDate.parse("2023-01-01"));
        databasePopulateService.
                createProjectTable(4, LocalDate.parse("2022-12-14"), LocalDate.parse("2023-07-05"));
        databasePopulateService.
                createProjectTable(3, LocalDate.parse("2022-02-14"), LocalDate.parse("2022-07-15"));
        databasePopulateService.
                createProjectTable(2, LocalDate.parse("2022-04-30"), LocalDate.parse("2022-10-25"));
        databasePopulateService.
                createProjectTable(1, LocalDate.parse("2022-11-15"), LocalDate.parse("2023-06-12"));

        databasePopulateService.createProjectWorkerTable(1, 1);
        databasePopulateService.createProjectWorkerTable(1, 3);
        databasePopulateService.createProjectWorkerTable(2, 5);
        databasePopulateService.createProjectWorkerTable(2, 2);
        databasePopulateService.createProjectWorkerTable(3, 1);
        databasePopulateService.createProjectWorkerTable(3, 10);
        databasePopulateService.createProjectWorkerTable(3, 4);
        databasePopulateService.createProjectWorkerTable(4, 6);
        databasePopulateService.createProjectWorkerTable(4, 3);
        databasePopulateService.createProjectWorkerTable(5, 7);
        databasePopulateService.createProjectWorkerTable(6, 8);
        databasePopulateService.createProjectWorkerTable(6, 10);
        databasePopulateService.createProjectWorkerTable(6, 4);
        databasePopulateService.createProjectWorkerTable(7, 9);
        databasePopulateService.createProjectWorkerTable(7, 7);
        databasePopulateService.createProjectWorkerTable(8, 1);
        databasePopulateService.createProjectWorkerTable(8, 2);
        databasePopulateService.createProjectWorkerTable(9, 10);
        databasePopulateService.createProjectWorkerTable(10, 5);
        databasePopulateService.createProjectWorkerTable(10, 6);
        databasePopulateService.createProjectWorkerTable(10, 7);
        databasePopulateService.createProjectWorkerTable(10, 9);


    }
}
