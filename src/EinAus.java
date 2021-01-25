// EinAus zum Projekt Sudoku

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EinAus extends JFrame implements ActionListener
{

  private JTextField feld[][] = new JTextField[9][9];
  Sudoku hSudoku = new Sudoku();
  private JLabel myLabel = new JLabel();

   public EinAus()
   {
      super("Sudoku");
      this.getContentPane().setBackground(Color.lightGray);
      this.getContentPane().setLayout(null);
      //Dialog-Button
      JButton button1 = new JButton("Lösen");
      button1.setBounds (60, 470, 150, 30);
      button1.setBackground(Color.red);
      button1.addActionListener(this);
      this.getContentPane().add(button1);
      //Mischen-Button
      JButton button2 = new JButton("Reset");
      button2.setBounds (240, 470, 150, 30);
      button2.setBackground(Color.red);
      button2.addActionListener(this);
      this.getContentPane().add(button2);
      //Ende-Button
      JButton button3 = new JButton("Ende");
      button3.setBounds (140, 520, 150, 30);
      button3.setBackground(Color.red);
      button3.addActionListener(this);
      this.getContentPane().add(button3);

      //9x9 Felder
      for (int j = 0; j < 9; j++)
      {
       for (int i = 0; i < 9;i++)
       {
         feld[i][j]= new JTextField (40);
         feld[i][j].setText ("");
         feld[i][j].setBounds (5 + 50*i, 5+ 50*j, 40, 40);
         feld[i][j].setBackground(Color.white);
         this.getContentPane().add (feld[i][j]);
       }
      }
    }


    public void actionPerformed(ActionEvent event)
    {
      String cmd = event.getActionCommand();
      if (cmd.equals("Lösen"))
      {
         //Einlesen der Eingetragenen Felder
         for (int j = 0; j < 9; j++)
          {
           for (int i = 0; i < 9;i++)
           {
             String hilfe = feld[i][j].getText ();
             if (!hilfe.equals(""))
             {
               int hilfe2 = Integer.parseInt(hilfe);
               hSudoku.spielfeld[i][j] = hilfe2;
             }
           }
          }
          // Aufruf der Lösungsmethode -man kann auf das Abfangen des Ergenisses verzichten.
         hSudoku.backtracking(0,0);
         // Ausgabe
         for (int j = 0; j < 9; j++)
         {
           for (int i = 0; i < 9;i++)
           {
             feld[i][j].setText (""+hSudoku.getZahl(i,j));
           }
         }
       }
        if (cmd.equals("Reset"))
      {
      //Löschen der Ausgabe und Erzeugen eines neuen Sudoku
        for (int j = 0; j < 9; j++)
        {
         for (int i = 0; i < 9;i++)
         {
           feld[i][j].setText ("");
         }
        }
        hSudoku = new Sudoku();
      }
        else if (cmd.equals("Ende"))
      {
          setVisible(false);
          dispose();
          System.exit(0);
      }
    }
}
