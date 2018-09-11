import java.util.*;
import java.awt.*;
import java.applet.*;

/*
** a simple digital clock in Java (beta applet
** muquit@semcor.com
** December 05, 1995
*/

public class Dgclock extends Applet implements Runnable
{
    Thread
        timer=null;


    Image[]
        simage=new Image[10],
        image=new Image[11];

    Image
        buf_image;

    Graphics
        gc;

    int
        width=15,
        height=20;

    public void init()
    {
        int
            x,
            y;

        image[0]=getImage(getCodeBase(),"images/zero.gif");
        image[1]=getImage(getCodeBase(),"images/one.gif");
        image[2]=getImage(getCodeBase(),"images/two.gif");
        image[3]=getImage(getCodeBase(),"images/three.gif");
        image[4]=getImage(getCodeBase(),"images/four.gif");
        image[5]=getImage(getCodeBase(),"images/five.gif");
        image[6]=getImage(getCodeBase(),"images/six.gif");
        image[7]=getImage(getCodeBase(),"images/seven.gif");
        image[8]=getImage(getCodeBase(),"images/eight.gif");
        image[9]=getImage(getCodeBase(),"images/nine.gif");
        image[10]=getImage(getCodeBase(),"images/colon.gif");

        simage[0]=getImage(getCodeBase(),"images/szero.gif");
        simage[1]=getImage(getCodeBase(),"images/sone.gif");
        simage[2]=getImage(getCodeBase(),"images/stwo.gif");
        simage[3]=getImage(getCodeBase(),"images/sthree.gif");
        simage[4]=getImage(getCodeBase(),"images/sfour.gif");
        simage[5]=getImage(getCodeBase(),"images/sfive.gif");
        simage[6]=getImage(getCodeBase(),"images/ssix.gif");
        simage[7]=getImage(getCodeBase(),"images/sseven.gif");
        simage[8]=getImage(getCodeBase(),"images/seight.gif");
        simage[9]=getImage(getCodeBase(),"images/snine.gif");

        try
        {
            buf_image=createImage(89,20);
            gc=buf_image.getGraphics();
        } catch (Exception e) gc=null;
    }
    
    public void start()
    {
        if (timer == null)
        {
            timer=new Thread(this, "Dgclock");
            timer.start();
        }
    }

    public void run()
    {
        while (timer != null)
        {
            repaint();
            try
            {
                timer.sleep(1000);
            }catch (InterruptedException e)
             {
             }
        }
    }
    
    public void paintApplet(Graphics g)
    {
        int
            i,
            j,
            k;

        Date
            now=new Date();

        int
            hour,
            minute,
            second;

        String
            hr,
            min,
            sec,
            today;

        hr=formatDate("%I",now);
        min=formatDate("%M",now);
        sec=formatDate("%S",now);

        for (i=0; i <hr.length(); i++)
        {
            g.drawImage(image[hr.charAt(i)-'0'],i*width,0,this);
        }
        g.drawImage(image[10],i*width,0,this);
        i++;
        for (j=0; j < min.length(); j++)
        {
            g.drawImage(image[min.charAt(j)-'0'],i*width,0,this);
            i++;
        }
        for (j=0; j < sec.length(); j++)
        {
            g.drawImage(simage[sec.charAt(j)-'0'],(i*width)+(j*7),0,this);
        }
    }

    public void update(Graphics g)
    {
        if (buf_image != null)
        {
            paintApplet(gc);
            g.drawImage(buf_image,0,0,this);
        }
        else
        {
            g.clearRect(0,0,89,20);
            paintApplet(g);
        }
    }
// Left Pad a number
// taken from Clock2 by Per Reedtz Thomsen/Rachel Gollub
private String padElement(int expr, char padChar)
{
  String result = "";
  // I'm just padding 2 digit numbers
  if (expr < 10) result = result.concat(String.valueOf(padChar));
  result = result.concat(String.valueOf(expr));
  return(result);  
}

// Format a date according to the formatting string.
// taken from Clock2 by Per Reedtz Thomsen/Rachel Gollub
private String formatDate(String fmt, Date d)
{
  String formattedDate = "";
      
  // Retrieve the specific date information
  int hour = d.getHours();
  int minute = d.getMinutes();
  int second = d.getSeconds();
        
  int US_Hour = hour < 13 ? hour : hour - 12;
      
  // Loop through the format string
  for(int i = 0; i < fmt.length(); i++)
  {
    if (fmt.charAt(i) == '%') // We've hit a formatting command...
    {
      i++; // Move past the '%' sign
      // Figure out the format.
      switch (fmt.charAt(i))
      {
      case 'I': // Hour -- 01 to 12
        formattedDate = formattedDate.concat(padElement(US_Hour, '0'));
        //formattedDate=formattedDate.concat(String.valueOf(US_Hour));
        break;
      case 'M': // Minutes -- 00 to 59
        formattedDate = formattedDate.concat(padElement(minute, '0'));
        break;
      case 'S': // Second -- 00 to 61 (leap seconds)
        formattedDate = formattedDate.concat(padElement(second, '0'));
        break;
      default:
        formattedDate = formattedDate.concat("??");
        break;
      }
    }
    else // A regular character
    {
      formattedDate = formattedDate.concat(String.valueOf(fmt.charAt(i)));
    }
  } // end for

  return(formattedDate);
}
    public void stop()
    {
        timer.stop();
        timer=null;
    }
}
