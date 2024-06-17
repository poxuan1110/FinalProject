import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class ListPanel extends JPanel {
    public ListPanel() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3));

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);  // 設置每次滾動的像素數
        verticalScrollBar.setBlockIncrement(50); // 設置每次點及滾動條按鈕時滾動的像素數

        add(scrollPane, BorderLayout.CENTER);

        Data allCountry = new Data();
        String[] print = new String[199];
        HashMap<String, Double> country = allCountry.getOtherCountry();
        Set<String> keys = country.keySet();
        int i = 0;

        for (String key : keys) {

            print[i] = key;
            i++;
        }

        HashMap<String, HashMap<String, Double>> special = allCountry.getSpecialCountry();
        keys = special.keySet();

        for (String key : keys) {
            
            print[i] = key;
            i++;
        }
        Arrays.sort(print);//將國家從a-z排列

        JButton[] button = new JButton[199];
        for (int j = 0; j < print.length; j++) {//列出所有國家按鈕

            Font buttonFont = new Font("Serif", Font.PLAIN, 18); // 按鈕字體大小
            button[j] = new JButton(print[j]);//按鈕名稱
            button[j].setFont(buttonFont); // 設置按鈕字體
            button[j].setPreferredSize(new Dimension(150, 50));
            button[j].setAlignmentX(Component.LEFT_ALIGNMENT);
            // System.out.println(print[j]);
            buttonPanel.add(button[j]);

            final int count = j;
            button[j].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    Time time = new Time();
                    String country = button[count].getText();
                    Data change = new Data();
                    double duration;

                    while (true) {

                        if (change.getOtherCountry().containsKey(country)) {// 單時區國家的時區轉換

                            removeAll(); // 移除所有组件
                            revalidate(); // 重新驗證布局
                            repaint(); // 重新繪製面板

                            duration = change.getOtherCountry().get(country);
                            String changedTime = time.plusTime(duration);

                            Clock changedClock = new Clock(changedTime, country);
                            setLayout(new BoxLayout(ListPanel.this, BoxLayout.Y_AXIS));
                            add(changedClock);
                            break;

                        } else if (change.getSpecialCountry().containsKey(country)) {// 多時區國家的時區轉換

                            removeAll(); // 移除所有组件
                            revalidate(); // 重新驗證布局
                            repaint(); // 重新繪製面板

                            SpecialCountry region = new SpecialCountry(country);
                            setLayout(new BoxLayout(ListPanel.this, BoxLayout.Y_AXIS));
                            add(region);
                            break;
                        }
                    }
                }
            });
        }

        addKeyListener(new KeyAdapter() {//Esc回主選單

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                    removeAll(); // 移除所有组件
                    revalidate(); // 重新驗證布局
                    repaint(); // 重新繪製面板

                    Menu textPanel = new Menu();
                    add(textPanel);
                    JOptionPane.showMessageDialog(ListPanel.this, "You pressed Esc, returning to main menu.");
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }
}
