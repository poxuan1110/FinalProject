import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonLabelExample {
    public static void main(String[] args) {
        // 創建主框架
        JFrame frame = new JFrame("JPanel Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 創建一個JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // 創建JLabel
        JLabel label = new JLabel("按鈕尚未被點擊");
        
        // 創建按鈕
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        // 添加按鈕到JPanel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(label);

        // 創建事件監聽器
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                if (source == button1) {
                    label.setText("Button 1 被點擊");
                } else if (source == button2) {
                    label.setText("Button 2 被點擊");
                } else if (source == button3) {
                    label.setText("Button 3 被點擊");
                }
            }
        };

        // 將事件監聽器附加到按鈕
        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
        button3.addActionListener(buttonListener);

        // 將JPanel添加到框架
        frame.add(panel);

        // 顯示框架
        frame.setVisible(true);
    }
}
