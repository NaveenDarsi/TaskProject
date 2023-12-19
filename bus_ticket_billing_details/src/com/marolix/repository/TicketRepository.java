package com.marolix.repository;

import com.marolix.model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bus_ticket_db";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets");

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setPassengerName(resultSet.getString("passenger_name"));
                ticket.setSource(resultSet.getString("source"));
                ticket.setPickupPoint(resultSet.getString("pickup_point"));
                ticket.setDestination(resultSet.getString("destination"));
                ticket.setPrice(resultSet.getDouble("price"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public void addTicket(Ticket ticket) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO tickets (passenger_name, source, destination, pickup_point, price) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, ticket.getPassengerName());
            preparedStatement.setString(2, ticket.getSource());
            preparedStatement.setString(3, ticket.getDestination());
            preparedStatement.setString(4, ticket.getPickupPoint());
            preparedStatement.setDouble(5, ticket.getPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTicket(Ticket ticket) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE tickets SET passenger_name=?, source=?, destination=?, price=? WHERE id=?")) {

            preparedStatement.setString(1, ticket.getPassengerName());
            preparedStatement.setString(2, ticket.getSource());
            preparedStatement.setString(3, ticket.getDestination());
            preparedStatement.setDouble(4, ticket.getPrice());
            preparedStatement.setInt(5, ticket.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket(int ticketId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tickets WHERE id=?")) {

            preparedStatement.setInt(1, ticketId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket getTicketById(int ticketId) {
        Ticket ticket = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tickets WHERE id=?")) {

            preparedStatement.setInt(1, ticketId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setPassengerName(resultSet.getString("passenger_name"));
                ticket.setSource(resultSet.getString("source"));
                ticket.setDestination(resultSet.getString("destination"));
                ticket.setPrice(resultSet.getDouble("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
    }
}
