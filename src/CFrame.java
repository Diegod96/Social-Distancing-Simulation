import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CFrame extends JPanel implements ActionListener {
    ArrayList<Person> people = new ArrayList<Person>();
    ArrayList<Point> points = new ArrayList<Point>();
    int time = 0;


    public static void main(String[] args) {
        //Instantiate frame object
        CFrame c = new CFrame();
    }

    public void paint(Graphics g) {
        //Increment time
        time += 16;
        //Add time and value to points list
        points.add(new Point(time / 16, Person.numInfected));
        super.paintComponent(g);
        for (Person p : people) {
            p.paint(g);
        }

        //Generate unique people pairs in the people list
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                people.get(i).collision(people.get(j));
            }
        }

        //Set color for infected rate line graph
        g.setColor(Color.RED);
        for (Point p: points) {
            g.fillOval(p.time, 200 - p.value, 10, 10);
        }
    }

    public CFrame() {

        //Set up frame
        JFrame frame = new JFrame("Covid-19 Social Distancing Simulation");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add people to the simulation
        for (int i = 0; i < 100; i++) {
            people.add(new Person());
        }

        //Set up a timer
        Timer t = new Timer(16,this);
        t.start();

        frame.add(this);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}
