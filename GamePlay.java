package code;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements  ActionListener , KeyListener
{
    private boolean play = false;
    private int score = 0;
    private int totalBubble = 6;
    private Timer timer;
    private int delay= 8;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -2;
    private int ballYdir = -4;
    private int playerX = 350;
    private MapGenerator map;
    private int lvl = 1;

    public GamePlay()
    {

        //lvl 1
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);     //false
        timer=new Timer(delay,this);
        timer.start();

        map = new MapGenerator(2, 3);
    }

    public void paint(Graphics g)   //in built method
    {

        //Extra colors
        Color c = new Color(255, 230, 199);     //lvl 1 canvas
        Color v = new Color(122, 62 ,101);    //lvl 2 canvas

        Color darkgrn = new Color(0,102,0);
        Color darkred = new Color(153,0,0);

        //for lvl 1 design
        if (lvl ==1) {
            g.setColor(c);
            g.fillRect(1, 1, 692, 592);

            // borders
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(683, 3, 3, 592);

            //Paddle
            Color paddle = new Color(231,177,10);
            g.setColor(paddle);
            g.fillRect(playerX, 550, 100, 8);
            //Bubble
            map.draw((Graphics2D) g);

            //Ball

            g.setColor(Color.red);
            g.fillOval(ballposX, ballposY, 20, 20);
        }

//lvl 2 design
         else if(lvl == 2)
    {
        g.setColor(v);
        g.fillRect(1, 1, 692, 592);

        // borders
        g.setColor(Color.red);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(683, 3, 3, 592);

        //Paddle
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);
        //Bubble
        map.draw((Graphics2D) g);

        //Ball
        g.setColor(Color.blue);
        g.fillOval(ballposX, ballposY, 20, 20);
    }
        //score
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score  " + score, 550, 30);
        g.drawString("Level  " + lvl, 10, 30);

        //Game Over

        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over!!, Score: " + score, 200, 300);

            g.setColor(Color.pink);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press Enter to Restart!!", 230, 350);
        }

        g.dispose();


        // Congratulations
        if (totalBubble == 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            if(lvl == 1) {
                if (!play)
                {
                    //lvl 1
                    play = true;
                    score = 0;
                    totalBubble = 20;
                    ballposX = 120;
                    ballposY = 350;
                    ballXdir = -2;
                    ballYdir = -4;
                    playerX = 310;
                    lvl = 2;
                    map = new MapGenerator(4, 5);
                }
            }
            else
            {
                //lvl2
                play = false;
                ballXdir = 0;
                ballYdir = 0;
                g.setColor(Color.green);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("you won!!, Score: " + score, 200, 300);

                g.setColor(Color.pink);
                g.setFont(new Font("serif", Font.BOLD, 25));
                g.drawString("Press Enter to Restart!!", 230, 350);
            }
        }
    }


    public void moveLeft()
    {
        play = true;
        playerX-=20;
    }
    public void moveRight()
    {
        play = true;
        playerX+=20;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX < 0)
            {
                playerX = 0;
            }
            else
            {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX >= 600)
            {
                playerX = 600;
            }
            else
            {
                moveRight();
            }
        }


        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                if(lvl ==1) {

                    play = true;
                    score = 0;
                    totalBubble = 6;
                    ballposX = 120;
                    ballposY = 350;
                    ballXdir = -1;
                    ballYdir = -2;
                    playerX = 310;
                    map = new MapGenerator(2, 3);
                }
                else if(lvl ==2)
                {
                    play = true;
                    score = 0;
                    totalBubble = 20;
                    ballposX = 120;
                    ballposY = 350;
                    ballXdir = -1;
                    ballYdir = -2;
                    playerX = 310;
                    map = new MapGenerator(4, 5);
                }
            }

        }
        repaint();
    }

   
    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(play)
        {
            if(ballposX <= 0)
            {
                ballXdir = -ballXdir;
            }
            if(ballposX >= 670)
            {
                ballXdir = -ballXdir;
            }
            if(ballposY <= 0)
            {
                ballYdir = -ballYdir;
            }


            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
            {
                ballYdir = -ballYdir;
                ballXdir = -2;
            }
            else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
            {
                ballYdir = -ballYdir;
                ballXdir = ballXdir + 1;
            }
            else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
            {
                ballYdir = -ballYdir;
            }


            A: for(int i = 0; i<map.map.length; i++)
            {
                for(int j =0; j<map.map[0].length; j++)
                {
                    if(map.map[i][j] > 0)
                    {
                        int width = map.bubbleWidth;
                        int height = map.bubbleHeight;
                        int bubbleXpos = 80 + j * width;
                        int bubbleYpos = 50 + i * height;


                        Rectangle bubbleRect = new Rectangle(bubbleXpos, bubbleYpos, width, height);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);


                        if(ballRect.intersects(bubbleRect))
                        {
                            map.setBubble(0, i, j);
                            totalBubble-=1;
                            score+=5;

                            if(ballposX + 19 <= bubbleRect.x || ballposX + 1 >= bubbleRect.x + bubbleRect.width)
                            {
                                ballXdir = -ballXdir;
                            }
                            else
                            {
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;

            
            repaint();
        }
    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }

}
