import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {


        int sek = 0; //sekundy od kiedy program sprawdza kolor
        int x = 0, y = 0;

        boolean topLeftCornerSelected = false;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int screen_x = (int)screenSize.getWidth();
        int screen_y = (int)screenSize.getHeight();

        wait(5000);

        MyFrame myFrame = new MyFrame();
        myFrame.setLocation((int)(0.4 * screen_x), (int)(0.5 * screen_y));
        myFrame.setSize((int)(0.2 * screen_x), (int)(0.2 * screen_y));
        myFrame.getContentPane().setBackground(Color.black);

        JLabel confirm_label = new JLabel("111");
        myFrame.add(confirm_label);
        confirm_label.setVerticalAlignment(SwingConstants.NORTH);
        confirm_label.setHorizontalAlignment(SwingConstants.CENTER);


        //JLabel label_time = new JLabel("0");
        //myFrame.add(label_time);
        //label_time.setHorizontalAlignment(SwingConstants.CENTER);


        while(true) {


            //tylko pierwsza iteracja
            if(!topLeftCornerSelected) {

                //wait(3000);

                Point p1 = MouseInfo.getPointerInfo().getLocation();

                x = p1.x;
                y = p1.y;

                System.out.println("x: " + x + ", y: " + y);

                topLeftCornerSelected = true;



                confirm_label.setText("<html>Position saved. You can move your cursor now." +
                                      " Do not move this window.</html>");

            }


            wait(1000);

            sek+=1;
            System.out.println(sek + " seconds have passed.");

            //label_time.setText(sek + " seconds have passed.");

            if(!checkPixel(x, y, screen_x, screen_y)) {

                System.out.println("Click");
                //System.exit(0);
                break;

            }




        }

    }

    static boolean checkPixel(int x, int y, int screen_x, int screen_y) {

        try {

            int x_client = 0, y_client = 0;

            x_client = 1280;
            y_client = 720;

            int x_click = x + x_client / 2;
            int y_click = y + (int)(y_client*0.8);

            Robot robot = new Robot();

            Color color = robot.getPixelColor((int)(0.5*screen_x), (int)(0.6*screen_y) /*540+108 */);



            boolean isBlack = checkIfBlack(color);

            if(!isBlack)
            {

                //click na pixel
                System.out.println("NIE JEST CZARNE");
                click(x_click, y_click); //437, 203 - play button

                //319, 159 - l. g. róg - 320, 160
                //~930, 760 - accept
                //x: +770, y: +600
                //x: +640? połowa x clienta
                //x: 1050/2 = 525
                //y: 1/5 launchera, 1/5 * 720, y + 4/5 * 720 = y+

                return false;

            }

            return true;

        } catch (AWTException exception) {
            exception.printStackTrace();
        }

        return true;
    }

    static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    static boolean checkIfBlack(Color color)
    {

        if(color.getRed() == 0)
        {

            if(color.getGreen() == 0)
            {

                if(color.getBlue() == 0)
                {

                    return true;

                }

            }

        }

    return false;

    }

    static void click(int x, int y) throws AWTException
    {

        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }

}

class MyFrame extends JFrame
{

    public MyFrame()
    {

        super("elo elo 3 2 0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

}