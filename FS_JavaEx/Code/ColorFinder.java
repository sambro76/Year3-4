import java.applet.*;
import java.awt.*;
import IDD_CF_Dialog;

public class ColorFinder extends Applet implements Runnable
{
	private Thread m_ColorFinder = null;

	private int m_Red=0, m_Green=0,m_Blue=0;
	private String m_Red_Hex, m_Green_Hex, m_Blue_Hex;

	private Color m_Color=new Color(m_Red,m_Green,m_Blue);

	private Panel p1=new Panel();
	private Panel p2=new Panel();
	private IDD_CF_Dialog m_CFDialog = new IDD_CF_Dialog(p2);

	public ColorFinder()
	{
	}

	public String getAppletInfo()
	{
		return "Name: ColorFinder\r\n" +
		       "Author: Alessandro Fronte\r\n" +
		       "Created with Microsoft Visual J++ Version 1.1";
	}


	public void init()
	{
		setLayout(new GridLayout(2,1,10,10));
		add(p1);
		p1.setBackground(m_Color);
		add(p2);
		m_CFDialog.CreateControls();
	}

	public void destroy()
	{
	}

	public void paint(Graphics g){
	}

	public void start()
	{
		if (m_ColorFinder == null)
		{
			m_ColorFinder = new Thread(this);
			m_ColorFinder.start();
		}
	}
	
	public void stop()
	{
		if (m_ColorFinder != null)
		{
			m_ColorFinder.stop();
			m_ColorFinder = null;
		}
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Color m_Color=new Color(m_Red,m_Green,m_Blue);
				p1.setBackground(m_Color);
				p1.repaint();
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				stop();
			}
		}
	}

    public boolean handleEvent (Event evt){

		if (   evt.target==m_CFDialog.IDC_SCROLLBAR_Red
			&&((evt.id==Event.SCROLL_ABSOLUTE) ||
			   (evt.id==Event.SCROLL_LINE_DOWN) ||
			   (evt.id==Event.SCROLL_LINE_UP)   ||
			   (evt.id==Event.SCROLL_PAGE_DOWN) ||
			   (evt.id==Event.SCROLL_PAGE_UP))){
					m_Red=m_CFDialog.IDC_SCROLLBAR_Red.getValue();
					m_Red_Hex=Integer.toHexString(m_Red);

					if(m_Red_Hex.length()!=2)
						m_Red_Hex="0"+m_Red_Hex;

					m_CFDialog.Red_Value.setText(m_Red_Hex);
		}

		if (   evt.target==m_CFDialog.IDC_SCROLLBAR_Green
			&&((evt.id==Event.SCROLL_ABSOLUTE) ||
			   (evt.id==Event.SCROLL_LINE_DOWN) ||
			   (evt.id==Event.SCROLL_LINE_UP)   ||
			   (evt.id==Event.SCROLL_PAGE_DOWN) ||
			   (evt.id==Event.SCROLL_PAGE_UP))){
					m_Green=m_CFDialog.IDC_SCROLLBAR_Green.getValue();
					m_Green_Hex=Integer.toHexString(m_Green);

					if(m_Green_Hex.length()!=2)
						m_Green_Hex="0"+m_Green_Hex;

					m_CFDialog.Green_Value.setText(m_Green_Hex);
		}

		if (   evt.target==m_CFDialog.IDC_SCROLLBAR_Blue
			&&((evt.id==Event.SCROLL_ABSOLUTE) ||
			   (evt.id==Event.SCROLL_LINE_DOWN) ||
			   (evt.id==Event.SCROLL_LINE_UP)   ||
			   (evt.id==Event.SCROLL_PAGE_DOWN) ||
			   (evt.id==Event.SCROLL_PAGE_UP))){
					m_Blue=m_CFDialog.IDC_SCROLLBAR_Blue.getValue();
					m_Blue_Hex=Integer.toHexString(m_Blue);

					if(m_Blue_Hex.length()!=2)
						m_Blue_Hex="0"+m_Blue_Hex;

					m_CFDialog.Blue_Value.setText(m_Blue_Hex);
		}

		if (   evt.target==m_CFDialog.ID_Rnd_BUTTON1
			&& evt.id==Event.MOUSE_UP) {
			m_Red=(int)(Math.random()*255);
			m_Red_Hex=Integer.toHexString(m_Red);

			if(m_Red_Hex.length()!=2)
				m_Red_Hex="0"+m_Red_Hex;

			m_CFDialog.Red_Value.setText(m_Red_Hex);
			m_CFDialog.IDC_SCROLLBAR_Red.setValue(m_Red);

			m_Green=(int)(Math.random()*255);
			m_Green_Hex=Integer.toHexString(m_Green);

			if(m_Green_Hex.length()!=2)
				m_Green_Hex="0"+m_Green_Hex;

			m_CFDialog.Green_Value.setText(m_Green_Hex);
			m_CFDialog.IDC_SCROLLBAR_Green.setValue(m_Green);

			m_Blue=(int)(Math.random()*255);
			m_Blue_Hex=Integer.toHexString(m_Blue);

			if(m_Blue_Hex.length()!=2)
				m_Blue_Hex="0"+m_Blue_Hex;

			m_CFDialog.Blue_Value.setText(m_Blue_Hex);
			m_CFDialog.IDC_SCROLLBAR_Blue.setValue(m_Blue);
		}

		return true;
	}
}
