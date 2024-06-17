import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Clock extends JPanel implements ActionListener {
    private BufferedImage clock, second, minute, hour;
    private Timer secondtimer;
    private double secondAngle;
    private double minuteAngle;
    private double hourAngle;
    private String currentTime; // To store the current time as a string
    private String country;

    public Clock(String currentTime, String country) {
        this.currentTime = currentTime; // Initialize the current time string
        this.country = country;

        String[] dateTimeParts = currentTime.split(" ");
        String timePart = dateTimeParts[1];
        String[] timeComponents = timePart.split(":");

        this.secondAngle = Double.parseDouble(timeComponents[2]) * 6;
        this.minuteAngle = (Double.parseDouble(timeComponents[1]) * 6 + Double.parseDouble(timeComponents[2]) * 0.1) % 360;
        this.hourAngle = (Double.parseDouble(timeComponents[0]) * 30 + Double.parseDouble(timeComponents[1]) * 0.5 + Double.parseDouble(timeComponents[2]) * (0.5 / 60)) % 360;

        setFocusable(true);
        
        try {

            clock = ImageIO.read(new File("clock.png"));
            second = ImageIO.read(new File("second.png"));
            minute = ImageIO.read(new File("minute.png"));
            hour = ImageIO.read(new File("hour.png"));
            //image = ImageIO.read(new File("heheheha.png"));

        } catch (IOException ex) {

            ex.printStackTrace();

        }

        secondtimer = new Timer(1000, this);
        secondtimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the clock
        if (clock != null) {

            int centerX = getWidth() / 2 - clock.getWidth() / 3 / 2;
            int centerY = getHeight() / 2 - clock.getHeight() / 3 / 2;
            g.drawImage(clock, centerX, centerY, clock.getWidth() / 3, clock.getHeight() / 3, null);

        }
        
        // Draw the hour hand
        if (hour != null) {

            Graphics2D g2d = (Graphics2D) g.create();
            int imageWidth = hour.getWidth();
            int imageHeight = hour.getHeight();
            int scaledWidth = imageWidth / 3;
            int scaledHeight = imageHeight / 3;
            AffineTransform transform = new AffineTransform();

            transform.translate(getWidth() / 2, getHeight() / 2);
            transform.rotate(Math.toRadians(hourAngle));
            transform.scale(1 / 3.0, 1 / 3.0);
            transform.translate(-scaledWidth - 24, -scaledHeight / 2 - 570);

            g2d.drawImage(hour, transform, this);
            g2d.dispose();
        }
        
        // Draw the minute hand
        if (minute != null) {

            Graphics2D g2d = (Graphics2D) g.create();
            int imageWidth = minute.getWidth();
            int imageHeight = minute.getHeight();
            int scaledWidth = imageWidth / 3;
            int scaledHeight = imageHeight / 3;

            AffineTransform transform = new AffineTransform();
            transform.translate(getWidth() / 2, getHeight() / 2);
            transform.rotate(Math.toRadians(minuteAngle));
            transform.scale(1 / 3.0, 1 / 3.0);
            transform.translate(-scaledWidth - 24, -scaledHeight / 2 - 650);

            g2d.drawImage(minute, transform, this);
            g2d.dispose();
        }
        
        // Draw the second hand
        if (second != null) {

            Graphics2D g2d = (Graphics2D) g.create();
            int imageWidth = second.getWidth();
            int imageHeight = second.getHeight();
            int scaledWidth = imageWidth / 3;
            int scaledHeight = imageHeight / 3;

            AffineTransform transform = new AffineTransform();
            transform.translate(getWidth() / 2, getHeight() / 2);
            transform.rotate(Math.toRadians(secondAngle));
            transform.scale(1 / 3.0, 1 / 3.0);
            transform.translate(-scaledWidth - 5, -scaledHeight / 2 - 640);

            g2d.drawImage(second, transform, this);
            g2d.dispose();
        }
        
        // Draw the additional image
        /*if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            AffineTransform transform = new AffineTransform();
            transform.translate(getWidth() / 2, getHeight() / 2);
            transform.rotate(Math.toRadians(secondAngle));
            transform.scale(1 / 3.0, 1 / 3.0);
            transform.translate(-image.getWidth() / 3 - 30, -image.getHeight() / 3);
            g2d.drawImage(image, transform, this);
            g2d.dispose();
        }*/

        // Draw the current time string
        g.setColor(Color.BLACK); // Set the text color
        g.setFont(new Font("Arial", Font.BOLD, 50)); // Set the font

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int currentTimeWidth = metrics.stringWidth(currentTime); // Get the width of the current time string
        int currentTimeX = (getWidth() - currentTimeWidth) / 2; // Calculate the x coordinate for the text to be centered
        
        int countryWidth = metrics.stringWidth(country); // Get the width of the current time string
        int countryX = (getWidth() - countryWidth) / 2;
        
        g.drawString(currentTime, currentTimeX, 40);
        g.drawString(country, countryX, 753); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondAngle += 6;
        if (secondAngle >= 360) {
            secondAngle = 0;
        }

        minuteAngle += 0.1;
        if (minuteAngle >= 360) {
            minuteAngle = 0;
        }

        hourAngle += 360 / 43200.0;
        if (hourAngle >= 360) {
            hourAngle = 0;
        }
        repaint();
    }
}
