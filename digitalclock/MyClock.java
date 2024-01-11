package digitalclock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MyClock extends JFrame {
    private final JLabel clockLabel;
    private final JLabel heading;
    private final JComboBox<String> countrySelector;

    MyClock() {
        setTitle("MY CLOCK");
        setSize(1000, 500);
        setLocation(300, 200);

        heading = new JLabel("THE CLOCK PROJECT");
        clockLabel = new JLabel("TIME");
        Font font = new Font("", Font.ITALIC, 36);
        clockLabel.setFont(font);
        heading.setFont(font);
        String[] countries = TimeZone.getAvailableIDs();
        countrySelector = new JComboBox<>(countries);
        countrySelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });

        this.setLayout(new GridLayout(3, 1));
        this.add(heading);
        this.add(countrySelector);
        countrySelector.setFont(font);
        startClock();
        this.add(clockLabel);
        setVisible(true);
    }

    public void startClock() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateClock();
            }
        });

        timer.start();
    }

    private void updateClock() {
        try {
            String selectedCountry = (String) countrySelector.getSelectedItem();
            TimeZone timeZone = TimeZone.getTimeZone(selectedCountry);
            Date currentTime = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dateFormat.setTimeZone(timeZone);
            String formattedTime = dateFormat.format(currentTime);
            clockLabel.setText(formattedTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
