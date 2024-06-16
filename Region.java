import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Region extends JPanel{

    public Region(String country) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font font = new Font("Serif", Font.PLAIN, 24);

        // 創建和配置標籤
        JLabel menuLabel = new JLabel("This country has these time zones. Click the region you want to convert to.");
        menuLabel.setFont(font);
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(menuLabel);
        add(Box.createVerticalStrut(10)); // 添加一些空間
        Time time = new Time();

        AllCountry change = new AllCountry();
        HashMap<String, Double> allTimeZoneOfTheCountry = change.getSpecialCountry().get(country);

        JButton[] allTimeZone = new JButton[allTimeZoneOfTheCountry.size()];
        Font buttonFont = new Font("Serif", Font.PLAIN, 18); // 按鈕字體大小
        int i = 0;
        for(String region : allTimeZoneOfTheCountry.keySet()){

            allTimeZone[i] = new JButton(region);
            allTimeZone[i].setFont(buttonFont); // 設置按鈕字體
            allTimeZone[i].setPreferredSize(new Dimension(150, 50));
            allTimeZone[i].setAlignmentX(Component.LEFT_ALIGNMENT);

            add(allTimeZone[i]);
            add(Box.createVerticalStrut(10)); // 添加一些空間
            int count = i;
            allTimeZone[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e){
                    removeAll();  // 移除所有组件
                    revalidate(); // 重新验证布局
                    repaint();    // 重新绘制面板
                    double duration = allTimeZoneOfTheCountry.get(allTimeZone[count].getText());
                    String changedTime = time.plusTime(duration);
                            
                    Clock clock = new Clock(changedTime, country);
                    add(clock);
                }
            });

            i++;
        }
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // 當按下 Esc 鍵時的處理代碼
                    removeAll();  // 移除所有组件
                    revalidate(); // 重新验证布局
                    repaint();    // 重新绘制面板
                    // 添加初始元件
                    TextPanel textpanel = new TextPanel();
                    add(textpanel);
                    JOptionPane.showMessageDialog(Region.this, "You pressed Esc, returning to main menu.");
                }
            }
        });

        // 設置面板可獲取鍵盤焦點
        setFocusable(true);
        requestFocusInWindow();
    }
}
