// Projekt Sudoku


import java.awt.event.*;
class Starter
{

  public static void main(String[] args)
  {
     EinAus wnd = new EinAus();
     WindowListener l = new WindowAdapter()
     {
       public void windowClosing(WindowEvent e)
       {
         System.exit(0);
       }
     };
     wnd.addWindowListener(l);
     wnd.setSize(470,650);
     wnd.setVisible(true);
  }
}
