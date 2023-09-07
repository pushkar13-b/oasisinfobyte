import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class ReservationSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1000;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Online Reservation System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton loginButton = new JButton("Login");
        JButton reservationButton = new JButton("Make a Reservation");
        JButton cancellationButton = new JButton("Cancellation");

        panel.add(loginButton);
        panel.add(reservationButton);
        panel.add(cancellationButton);

        frame.add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your login logic here
                JOptionPane.showMessageDialog(frame, "Login Successful!");
            }
        });

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });

        cancellationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });

        frame.setVisible(true);
    }

    private static void makeReservation() {
        JFrame reservationFrame = new JFrame("Make a Reservation");
        reservationFrame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel trainNumberLabel = new JLabel("Train Number:");
        JTextField trainNumberField = new JTextField();
        JLabel classTypeLabel = new JLabel("Class Type:");
        JTextField classTypeField = new JTextField();
        JLabel dateLabel = new JLabel("Date of Journey:");
        JTextField dateField = new JTextField();
        JLabel fromLabel = new JLabel("From:");
        JTextField fromField = new JTextField();
        JLabel toLabel = new JLabel("To:");
        JTextField toField = new JTextField();

        JButton insertButton = new JButton("Insert Reservation");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(trainNumberLabel);
        panel.add(trainNumberField);
        panel.add(classTypeLabel);
        panel.add(classTypeField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(fromLabel);
        panel.add(fromField);
        panel.add(toLabel);
        panel.add(toField);
        panel.add(insertButton);

        reservationFrame.add(panel);
        reservationFrame.setVisible(true);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get data from fields and create a reservation
                String name = nameField.getText();
                String trainNumber = trainNumberField.getText();
                String classType = classTypeField.getText();
                String date = dateField.getText();
                String from = fromField.getText();
                String to = toField.getText();

                Reservation reservation = new Reservation(generatePNR(), name, trainNumber, classType, date, from, to);
                reservations.add(reservation);

                JOptionPane.showMessageDialog(reservationFrame, "Reservation Successful!\nPNR: " + reservation.getPnr());
                reservationFrame.dispose();
            }
        });
    }

    private static void cancelReservation() {
        JFrame cancellationFrame = new JFrame("Cancellation");
        cancellationFrame.setSize(300, 100);

        JTextField pnrField = new JTextField();
        JButton okButton = new JButton("OK");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(pnrField);
        panel.add(okButton);

        cancellationFrame.add(panel);
        cancellationFrame.setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText();
                Reservation reservationToRemove = null;

                for (Reservation reservation : reservations) {
                    if (reservation.getPnr().equals(pnr)) {
                        reservationToRemove = reservation;
                        break;
                    }
                }

                if (reservationToRemove != null) {
                    reservations.remove(reservationToRemove);
                    JOptionPane.showMessageDialog(cancellationFrame, "Cancellation Successful!");
                } else {
                    JOptionPane.showMessageDialog(cancellationFrame, "Reservation not found!");
                }

                cancellationFrame.dispose();
            }
        });
    }

    private static String generatePNR() {
        return "PNR" + pnrCounter++;
    }
}

class Reservation {
    private String pnr;
    private String name;
    private String trainNumber;
    private String classType;
    private String date;
    private String from;
    private String to;

    public Reservation(String pnr, String name, String trainNumber, String classType, String date, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getPnr() {
        return pnr;
    }
}
