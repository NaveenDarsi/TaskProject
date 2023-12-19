package com.marolix.service;

import com.marolix.model.Ticket;
import com.marolix.repository.TicketRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketService {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bus_ticket_db";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    private final TicketRepository ticketRepository = new TicketRepository();

    public List<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }

    public void bookTicket(Ticket ticket) {
        if (isSeatAvailable(ticket.getSource(), ticket.getDestination())) {
            ticketRepository.addTicket(ticket);
            System.out.println("Ticket booked successfully for " + ticket.getPassengerName());
        } else {
            System.out.println("Sorry, the seat is not available for the selected route.");
        }
    }

    public void bookTickets(List<Ticket> tickets) {
        double totalCost = 0;

        for (Ticket ticket : tickets) {
            if (isSeatAvailable(ticket.getSource(), ticket.getDestination())) {
                ticketRepository.addTicket(ticket);
                totalCost += ticket.getPrice();
                System.out.println("Ticket booked successfully for " + ticket.getPassengerName());
            } else {
                System.out.println("Sorry, the seat is not available for the selected route.");
            }
        }

        System.out.println("Total Cost: $" + totalCost);
    }

    public boolean isSeatAvailable(String source, String destination) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM tickets WHERE source = ? AND destination = ?")) {

            preparedStatement.setString(1, source);
            preparedStatement.setString(2, destination);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Ticket getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }

    public void updateTicketDetails(Ticket ticket) {
        if (ticketRepository.getTicketById(ticket.getId()) != null) {
            ticketRepository.updateTicket(ticket);
        } else {
            System.out.println("Ticket not found. Unable to update details.");
        }
    }

    public void cancelTicket(int ticketId) {
        Ticket ticket = ticketRepository.getTicketById(ticketId);
        if (ticket != null) {
            ticketRepository.deleteTicket(ticketId);
        } else {
            System.out.println("Ticket not found. Unable to cancel.");
        }
    }
}
