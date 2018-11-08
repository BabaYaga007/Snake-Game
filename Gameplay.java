import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

public class Gameplay extends JPanel implements KeyListener,ActionListener
{
    private ImageIcon titleImage;
    
    private int snakelen =3;
    int moves=0;
    
    private int[] snakexlen = new int[750];
    private int[] snakeylen = new int[750];
    private boolean left =false;
    private boolean right =false;
    private boolean up =false;
    private boolean down =false;
    
    private ImageIcon rightm;
    private ImageIcon leftm;
    private ImageIcon downm;
    private ImageIcon upm; 
    
    private Timer timer;
    private int delay=100;
    private ImageIcon snakeimage;
    
    private int [] enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,
        375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,
        375,400,425,450,475,500,525,550,575,600,625};
    
    private ImageIcon enemyimage;
    private Random random= new Random();
    
    private int score =0;
    
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);   
    
    public Gameplay()
    {
        addKeyListener(this);  
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g)
    {
        if(moves==0)
        {
            snakexlen[0]=100;
            snakexlen[1]=75;
            snakexlen[2]=50;
            
            snakeylen[0]=100;
            snakeylen[1]=100;
            snakeylen[2]=100; 
        }        
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25,11);
        
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);
        
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);
        
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores : "+score,780,30);
        
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Lenght : "+snakelen,780,50);        
        
        rightm = new ImageIcon("rightmouth.png");
        rightm.paintIcon(this, g, snakexlen[0], snakeylen[0]);
        
        rightm = new ImageIcon("rightmouth.png");
        
        for (int a=0;a<snakelen;a++)
        {
            if(a==0 && right)
            {
                rightm = new ImageIcon("rightmouth.png");
                rightm.paintIcon(this, g, snakexlen[a], snakeylen[a]);
            }
            if(a==0 && left)
            {
                leftm = new ImageIcon("leftmouth.png");
                leftm.paintIcon(this, g, snakexlen[a], snakeylen[a]);
            }
            if(a==0 && up)
            {
                upm = new ImageIcon("upmouth.png");
                upm.paintIcon(this, g, snakexlen[a], snakeylen[a]);
            }
            if(a==0 && down)
            {
                downm = new ImageIcon("downmouth.png");
                downm.paintIcon(this, g, snakexlen[a], snakeylen[a]);
            }
            if(a!=0)
            {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlen[a], snakeylen[a]);
            }
        }
        
        enemyimage =new ImageIcon("enemy.png");
        if((enemyxpos[xpos] == snakexlen[0] && enemyypos[ypos] == snakeylen[0]))
        {
            score++;
            snakelen++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g ,enemyxpos[xpos], enemyypos[ypos]);
        
        for(int b=1;b<snakelen;b++)
        {
            if(snakexlen[b] == snakexlen[0] &&  snakeylen[b] == snakeylen[0])
            {
                right=false;
                left=false;
                up=false;
                down=false;
            
                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);
            
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Space to RESTART", 280, 340);            
            }            
        }
        
        g.dispose();    
    }
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(right)
        {
            for(int r=snakelen-1;r>=0;r--)
            {
                snakeylen[r+1]=snakeylen[r];
            }
            for(int r=snakelen;r>=0;r--)
            {
                if(r==0)
                {
                    snakexlen[r]=snakexlen[r]+25;
                }
                else
                {
                    snakexlen[r]=snakexlen[r-1];
                }
                if(snakexlen[r]>850)
                {
                    snakexlen[r]=25;
                }
            }
            repaint();
        }
        if(left)
        {
            for(int r=snakelen-1;r>=0;r--)
            {
                snakeylen[r+1]=snakeylen[r];
            }
            for(int r=snakelen;r>=0;r--)
            {
                if(r==0)
                {
                    snakexlen[r]=snakexlen[r]-25;
                }
                else
                {
                    snakexlen[r]=snakexlen[r-1];
                }
                if(snakexlen[r]<25)
                {
                    snakexlen[r]=850;
                }
            }
            repaint();
        }
        if(up)
        {
            for(int r=snakelen-1;r>=0;r--)
            {
                snakexlen[r+1]=snakexlen[r];
            }
            for(int r=snakelen;r>=0;r--)
            {
                if(r==0)
                {
                    snakeylen[r]=snakeylen[r]-25;
                }
                else
                {
                    snakeylen[r]=snakeylen[r-1];
                }
                if(snakeylen[r]<75)
                {
                    snakeylen[r]=625;
                 }
            }
            repaint();
        }
        if(down)
        {
            for(int r=snakelen-1;r>=0;r--)
            {
                snakexlen[r+1]=snakexlen[r];
            }
            for(int r=snakelen;r>=0;r--)
            {
                if(r==0)
                {
                    snakeylen[r]=snakeylen[r]+25;
                }
                else
                  {
                    snakeylen[r]=snakeylen[r-1];
                }
                if(snakeylen[r]>625)
                {
                    snakeylen[r]=75;
                }
            }
            repaint(); 
        }
    }
    public void keyTyped(KeyEvent e) 
    {
    }
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            moves=0;score=0;
            snakelen=3;
            repaint();
        }        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        { 
            moves++;
            right = true;
            if(!left)
            {
                right =true;
                left =false;
            }
            else
            {
                right =false;
                left=true;
            }
            up =false;
            down=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            left = true;
            if(!right)
            {
                left =true;
            }
            else
            {
                left =false;
                right=true;
            }
            up =false;
            down=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            up = true;
            if(!down)
            {
                up =true;
            }
            else
            {
                 up=false;
                down=true;
            }
            right =false;
            left=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            down = true;
            if(!up)
            {
                down =true;
            }
            else
            {
                down =false;
                up=true;
            }
            right =false;
            left=false;
        }
    }
    public void keyReleased(KeyEvent e)
    {
    }
    
    
}