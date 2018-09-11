/*************************** Begin of source ***************************/
// (C) 1996 Preston Rohner
// colors.java: 

import java.applet.*;
import java.awt.*;

//-----------------------------------------------------------------------
public class colors extends Applet implements Runnable {
   TextField   tfRed;
   TextField   tfGreen;
   TextField   tfBlue;
   Label       lblElement;
   Label       lblRed;
   Label       lblGreen;
   Label       lblBlue;
   Choice      cElement;
   int[][]     aColors;
   static      int RED = 0;
   static      int GREEN = 1;
   static      int BLUE = 2;
   static      final int TEXT_ROW     = 0;
   static      final int BGCOLOR_ROW  = 1;
   static      final int LINK_ROW     = 2;
   static      final int VLINK_ROW    = 3;
   static      final int MISC_ROW     = 4;

   //--------------------------------------------------------------------
   public void init() {
      add(lblElement = new Label("Element: "));
      cElement = new Choice();
      cElement.addItem("Text");           
      cElement.addItem("Background");
      cElement.addItem("Link");
      cElement.addItem("VLink");
      cElement.addItem("Misc. Text");

      // This array hold the color attributes.
      aColors = new int[cElement.countItems()][3];
      aColors[BGCOLOR_ROW][RED]     = 0xff;
      aColors[BGCOLOR_ROW][GREEN]   = 0xff;
      aColors[BGCOLOR_ROW][BLUE]    = 0xd8;
      aColors[TEXT_ROW][RED]        = 0x00;
      aColors[TEXT_ROW][GREEN]      = 0x00;
      aColors[TEXT_ROW][BLUE]       = 0x70;
      aColors[LINK_ROW][RED]        = 0xcc;
      aColors[LINK_ROW][GREEN]      = 0x44;
      aColors[LINK_ROW][BLUE]       = 0x00;
      aColors[VLINK_ROW][RED]       = 0x00;
      aColors[VLINK_ROW][GREEN]     = 0x60;
      aColors[VLINK_ROW][BLUE]      = 0x30;
      aColors[MISC_ROW][RED]        = 0x00;
      aColors[MISC_ROW][GREEN]      = 0x00;
      aColors[MISC_ROW][BLUE]       = 0x70;

      add(cElement);
   
      add(lblRed     = new Label("Red: "));
      add(tfRed      = new TextField(5));
   
      add(lblGreen   = new Label("Green: "));
      add(tfGreen    = new TextField(5));
   
      add(lblBlue    = new Label("Blue: "));
      add(tfBlue     = new TextField(5));
   
//      add(new Button("Store"));
      add(new Button("Redisplay"));
      ShowCurrent();
      ShowLabels();
   }

   //--------------------------------------------------------------------
   public void paint(Graphics g) {
      int iY = 70;
      Color clr;
      String s;

      g.setFont(new Font("New Font", Font.PLAIN, 14));


      // Fill in background color -----------------------------------
      s = RGBStr(       aColors[BGCOLOR_ROW][RED],
                        aColors[BGCOLOR_ROW][GREEN],
                        aColors[BGCOLOR_ROW][BLUE]);
      clr = new Color(  aColors[BGCOLOR_ROW][RED],
                        aColors[BGCOLOR_ROW][GREEN],
                        aColors[BGCOLOR_ROW][BLUE]);
      g.setColor(clr);
      g.fillRect(0, 0, size().width, size().height);

      clr = new Color(0, 0, 0);
      g.setColor(clr);
      g.drawString("bgcolor=\"#" + s + "\"", 20, iY);
      iY += g.getFontMetrics(g.getFont()).getHeight();

      // Draw the normal text color
      s = RGBStr(       aColors[TEXT_ROW][RED],
                        aColors[TEXT_ROW][GREEN],
                        aColors[TEXT_ROW][BLUE]);
      clr = new Color(  aColors[TEXT_ROW][RED],
                        aColors[TEXT_ROW][GREEN],
                        aColors[TEXT_ROW][BLUE]);
      g.setColor(clr);
      g.drawString("text=\"#" + s + "\"", 20, iY);
      iY += g.getFontMetrics(g.getFont()).getHeight();

      // Draw the link text color -----------------------------------
      s = RGBStr(       aColors[LINK_ROW][RED],
                        aColors[LINK_ROW][GREEN],
                        aColors[LINK_ROW][BLUE]);
      clr = new Color(  aColors[LINK_ROW][RED],
                        aColors[LINK_ROW][GREEN],
                        aColors[LINK_ROW][BLUE]);
      g.setColor(clr);
      g.drawString("link=\"#" + s + "\"", 20, iY);
      iY += g.getFontMetrics(g.getFont()).getHeight();

      // Draw the Vlink text color ----------------------------------
      s = RGBStr(       aColors[VLINK_ROW][RED],
                        aColors[VLINK_ROW][GREEN],
                        aColors[VLINK_ROW][BLUE]);
      clr = new Color(  aColors[VLINK_ROW][RED],
                        aColors[VLINK_ROW][GREEN],
                        aColors[VLINK_ROW][BLUE]);
      g.setColor(clr);
      g.drawString("vlink=\"#" + s + "\"", 20, iY);
      iY += g.getFontMetrics(g.getFont()).getHeight();

      // Draw the misc text color -----------------------------------
      s = RGBStr(       aColors[MISC_ROW][RED],
                        aColors[MISC_ROW][GREEN],
                        aColors[MISC_ROW][BLUE]);
      clr = new Color(  aColors[MISC_ROW][RED],
                        aColors[MISC_ROW][GREEN],
                        aColors[MISC_ROW][BLUE]);
      g.setColor(clr);
      g.drawString("Test text " + s, 20, iY);
      iY += g.getFontMetrics(g.getFont()).getHeight();

      //ShowLabels();
   }

   //--------------------------------------------------------------------
   void ShowLabels() {
      // The plan for this method was to set each label's background color
      // to the same color as the current background color.  Then I wanted
      // to set the foreground color to black.  Then cause each label to
      // be repainted.  When I executed the repaint() method, the labels
      // each seemed to use its own background color as the foreground
      // color too (making the text invisible).
////  Color clr;
////
////  clr = new Color(  aColors[BGCOLOR_ROW][RED],
////                    aColors[BGCOLOR_ROW][GREEN],
////                    aColors[BGCOLOR_ROW][BLUE]);
////
////  lblElement.setBackground(clr);
////  lblRed.setBackground(clr);
////  lblGreen.setBackground(clr);
////  lblBlue.setBackground(clr);
////
////  // Prepare to show text
////  clr = new Color(0, 0, 0); // s/b black
////
////  lblElement.setForeground(clr);
////  lblRed.setForeground(clr);
////  lblGreen.setForeground(clr);
////  lblBlue.setForeground(clr);
////  repaint();
////
////  lblElement.repaint();
////  lblRed.repaint();
////  lblGreen.repaint();
////  lblBlue.repaint();
   }

   //--------------------------------------------------------------------
   String RGBStr(int r, int g, int b) {
      int Radix = 16;

      String s1;
      if (r <= 16) {
         s1 = new String("0" + Integer.toString(r, Radix));
      } else {
         s1 = new String(Integer.toString(r, Radix));
      }

      String s2;
      if (g <= 16) {
         s2 = new String("0" + Integer.toString(g, Radix));
      } else {
         s2 = new String(Integer.toString(g, Radix));
      }

      String s3;
      if (b <= 16) {
         s3 = new String("0" + Integer.toString(b, Radix));
      } else {
         s3 = new String(Integer.toString(b, Radix));
      }

      String s = new String(s1 + s2 + s3);
      return s;
   }

   //--------------------------------------------------------------------
   public void start() {
      repaint();
   }

   //--------------------------------------------------------------------
   public void run() {
   }

   //--------------------------------------------------------------------
   public void stop() {
   }

   //--------------------------------------------------------------------
   public boolean action(Event ev, Object arg) {
      boolean bRet = false;

      if (ev.target instanceof Button) {
         String label = (String)arg;
         if (label.equalsIgnoreCase("redisplay")) {
            repaint();
         } else if (label.equalsIgnoreCase("store")) {
            StoreCurrent();
         }
         bRet = true;
      } else if (ev.target instanceof TextField) {
         ///// Doesn't seem to work as I expected.
         //if (Event.KEY_PRESS == ev.id) {
         //   if (13 == ev.key || 9 == ev.key) {
         //      System.out.println("A text field got a tab or enter.");
         //   }
         //}

         System.out.println("A text field got an event.");
         if (ev.target == tfRed) {
            tfGreen.requestFocus();
         } else if (ev.target == tfGreen) {
            tfBlue.requestFocus();
         } if (ev.target == tfBlue) {
            tfRed.requestFocus();
         }

         StoreCurrent();
         repaint();
      } else if (ev.target instanceof Choice) {
         ShowCurrent();
         tfRed.requestFocus();
      }

      return bRet;
   }

   //--------------------------------------------------------------------
   void ShowCurrent() {
      int iRow = -1;

      switch (cElement.getSelectedIndex()) {
         case TEXT_ROW     :  iRow = TEXT_ROW;
                              break;
         case BGCOLOR_ROW  :  iRow = BGCOLOR_ROW;
                              break;
         case LINK_ROW     :  iRow = LINK_ROW;
                              break;
         case VLINK_ROW    :  iRow = VLINK_ROW;
                              break;
         case MISC_ROW     :  iRow = MISC_ROW;
                              break;
      }

      if (0 <= iRow) {
         tfRed.setText(Integer.toString(aColors[iRow][RED], 16));
         tfGreen.setText(Integer.toString(aColors[iRow][GREEN], 16));
         tfBlue.setText(Integer.toString(aColors[iRow][BLUE], 16));
      }

   }

   //--------------------------------------------------------------------
   void StoreCurrent() {
      int iRed = 0, iGreen = 0, iBlue = 0;

      try
         iRed   = Integer.parseInt(tfRed.getText(), 16);
      catch (NumberFormatException e) {
         // value already set
      }

      try
         iGreen = Integer.parseInt(tfGreen.getText(), 16);
      catch (NumberFormatException e) {
         // value already set
      }

      try
         iBlue  = Integer.parseInt(tfBlue.getText(), 16);
      catch (NumberFormatException e) {
         // value already set
      }

      int iRow = -1;

      switch (cElement.getSelectedIndex()) {
         case TEXT_ROW     :  iRow = TEXT_ROW;
                              break;
         case BGCOLOR_ROW  :  iRow = BGCOLOR_ROW;
                              break;
         case LINK_ROW     :  iRow = LINK_ROW;
                              break;
         case VLINK_ROW    :  iRow = VLINK_ROW;
                              break;
         case MISC_ROW     :  iRow = MISC_ROW;
                              break;
      }

      if (0 <= iRow) {
         aColors[iRow][RED]   = iRed;
         aColors[iRow][GREEN] = iGreen;
         aColors[iRow][BLUE]  = iBlue;
      } else {
         System.out.println("Invalid index cElements.");
      }
   }
}

/***************************  End of source  ***************************/


