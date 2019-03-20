
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class ScrollFrame {
    public static void main(String args[])throws Exception
    {
        JFrame frame=new JFrame("MemoriamMedia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(500,500));
        JPanel panel=new JPanel();
        panel.setMaximumSize(new Dimension(500,500));
        JButton button;
        panel.setLayout(new GridLayout(3,1));
        for(int i=1;i<=3;i++)
        {
            button=new JButton("Play");
            button.setPreferredSize(new Dimension(500,30));
            button.setHorizontalAlignment(SwingConstants.LEFT);
            panel.add(button);
        }
        JScrollPane jsp=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(jsp);
        frame.pack();
        frame.setVisible(true);
    }        
}
