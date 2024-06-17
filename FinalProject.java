import javax.swing.JFrame;


public class FinalProject {
    public static void main(String[] args) {
        //FinalProject finalProject = new FinalProject();
        //String[] argsFP = {};
        //finalProject.main(argsFP);
        JFrame frame = new JFrame("Final Project"); 
        //text
        TextPanel a = new TextPanel();
        //ChangePanel changePanel = new ChangePanel();
        frame.add(a);
        //frame.add(changePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setSize(800, 600);
        frame.setVisible(true);
        
        
        
        /*Clock clock = new Clock();
        frame.add(clock);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 設置窗口狀態為全屏
        frame.setSize(800, 600);
        frame.setVisible(true);*/
    }
}
