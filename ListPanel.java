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
        verticalScrollBar.setUnitIncrement(16);  // 设置每次滚动的像素数
        verticalScrollBar.setBlockIncrement(50); // 设置每次点击滚动条按钮时滚动的像素数

        add(scrollPane, BorderLayout.CENTER);

        AllCountry allCountry = new AllCountry();
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
        Arrays.sort(print);
        JButton[] button = new JButton[199];
        for (int j = 0; j < print.length; j++) {
            Font buttonFont = new Font("Serif", Font.PLAIN, 18); // 按鈕字體大小
            button[j] = new JButton(print[j]);
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
                    AllCountry change = new AllCountry();
                    double duration;
                    while (true) {
                        if (change.getOtherCountry().containsKey(country)) {// 單時區國家的時區轉換
                            removeAll(); // 移除所有组件
                            revalidate(); // 重新验证布局
                            repaint(); // 重新绘制面板
                            duration = change.getOtherCountry().get(country);
                            String changedTime = time.plusTime(duration);

                            Clock changedClock = new Clock(changedTime, country);
                            setLayout(new BoxLayout(ListPanel.this, BoxLayout.Y_AXIS));
                            add(changedClock);
                            break;
                        } else if (change.getSpecialCountry().containsKey(country)) {// 多時區國家的時區轉換
                            removeAll(); // 移除所有组件
                            revalidate(); // 重新验证布局
                            repaint(); // 重新绘制面板

                            Region region = new Region(country);
                            setLayout(new BoxLayout(ListPanel.this, BoxLayout.Y_AXIS));
                            add(region);
                            break;
                        }
                    }
                }
            });
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    removeAll(); // 移除所有组件
                    revalidate(); // 重新验证布局
                    repaint(); // 重新绘制面板
                    TextPanel textPanel = new TextPanel();
                    add(textPanel);
                    JOptionPane.showMessageDialog(ListPanel.this, "You pressed Esc, returning to main menu.");
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }
}
