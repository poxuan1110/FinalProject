import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChangePanel extends JPanel {
    public ChangePanel() {
        // 設定佈局管理器為 BoxLayout，垂直排列組件
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 設定字體
        Font font = new Font("Serif", Font.PLAIN, 24);

        // 創建和配置標籤
        JLabel menuLabel = new JLabel("Function:");
        menuLabel.setFont(font);
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel line1Label = new JLabel("________________________________________________________");
        line1Label.setFont(font);
        line1Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 創建和配置按鈕
        Font buttonFont = new Font("Serif", Font.PLAIN, 18); // 按鈕字體大小
        JButton button1 = new JButton("1. Current time");
        button1.setFont(buttonFont); // 設置按鈕字體
        button1.setPreferredSize(new Dimension(150, 50));
        button1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton button2 = new JButton("2. Time converted from Taiwan to other countries");
        button2.setFont(buttonFont); // 設置按鈕字體
        button2.setPreferredSize(new Dimension(150, 50));
        button2.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel line2Label = new JLabel("________________________________________________________");
        line2Label.setFont(font);
        line2Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel instructionLabel = new JLabel("Please click the function you want to operate.");
        instructionLabel.setFont(font);
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 將標籤和按鈕添加到面板
        add(Box.createVerticalStrut(10)); // 添加一些空間
        add(menuLabel);
        add(Box.createVerticalStrut(5)); // 添加一些空間
        add(line1Label);
        add(Box.createVerticalStrut(30)); // 添加一些空間
        add(button1);
        add(Box.createVerticalStrut(10)); // 添加一些空間
        add(button2);
        add(Box.createVerticalStrut(20)); // 添加一些空間
        add(line2Label);
        add(Box.createVerticalStrut(10)); // 添加一些空間
        add(instructionLabel);

        // 添加按鈕事件處理程序
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();  // 移除所有组件
                revalidate(); // 重新验证布局
                repaint();    // 重新绘制面板
                Time time = new Time();
                String currentTime = time.getCurrentTime();
                Clock clock = new Clock(currentTime, "Taiwan");
                add(clock);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();  // 移除所有组件
                revalidate(); // 重新验证布局
                repaint();    // 重新绘制面板
                ListPanel listPanel = new ListPanel();
                add(listPanel);
            }
        });

        // 添加鍵盤事件處理程序來檢測 Esc 鍵
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    removeAll();  // 移除所有组件
                    revalidate(); // 重新验证布局
                    repaint();    // 重新绘制面板
                    TextPanel textpanel = new TextPanel();
                    add(textpanel);
                    JOptionPane.showMessageDialog(ChangePanel.this, "You pressed Esc, returning to main menu.");
                }
            }
        });

        // 設置面板可獲取鍵盤焦點
        setFocusable(true);
        requestFocusInWindow();
    }
}
