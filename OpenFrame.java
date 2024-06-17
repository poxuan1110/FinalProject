import javax.swing.JFrame;


public class OpenFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Final Project"); //開視窗
        //text
        Menu a = new Menu();
        frame.add(a);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
