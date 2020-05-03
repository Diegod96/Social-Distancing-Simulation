
import java.awt.*;



public class Person {

    //Location
    int x, y;

    //Velocity
    int vx, vy;

    //Status
    //0 means normal
    //1 means infected
    int status = 0;

    //Recovery time
    int recoveryTime;

    //Counter for number of people infected
    static int numInfected = 0;



    public Person() {

        //Randomize people's starting position
        x = (int)(Math.random() * 790 + 0);
        y = (int)(Math.random() * 590 + 0);

        //Start some people off as infected
        if (Math.random() < 0.08) {
            status = 1;
            numInfected++;
        }

        //Implement social distancing
        //In this instance, 90% of people are practicing social distancing
        if (Math.random() < 0.1) {
            vx = (int)(Math.random() * (10 + 1) +- 5);
            vy = (int)(Math.random() * (10 + 1) +- 5);
        }

//        //Randomize velocities
//        //Basically no social distancing
//        vx = (int)(Math.random() * (10 + 1) +- 5);
//        vy = (int)(Math.random() * (10 + 1) +- 5);

        //Randomize person's recovery time
        recoveryTime = (int)(Math.random() * (7000 - 5000 + 1) + 5000);

    }

    public void collision(Person p2) {
        Rectangle person1 = new Rectangle(p2.x, p2.y, 10, 10);
        Rectangle person2 = new Rectangle(this.x, this.y, 10, 10);

        if (person1.intersects(person2)) {
            //Spread disease from person1 to person2
            //Increment # people infected
            if (this.status == 1 && p2.status == 0) {
                p2.status = 1;
                numInfected++;
            }
            //Spread disease from person2 to person1
            //Increment # people infected
            else if (this.status == 0 && p2.status == 1) {
                this.status = 1;
                numInfected++;
            }
        }
    }

    public void paint(Graphics g) {

        //Assign color to person's status
        if (status == 0) {
            g.setColor(Color.LIGHT_GRAY);
        }
        else if (status == 1) {
            g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.BLUE);
        }

        //Update recovery time
        if (status == 1) {
            recoveryTime -= 16;
            //Infected -> recovered
            if (recoveryTime <= 0) {
                status = 2;
                numInfected--;
            }
        }

        //Change position based on velocity
        x += vx;
        y += vy;

        //Handle people containment and bouncing off walls
        if (x < 0 || x >= 790) {
            vx *= -1;
        }
        if (y < 0 || y >= 590) {
            vy *= -1;
        }

        g.fillOval(x, y, 10, 10);
    }


}
