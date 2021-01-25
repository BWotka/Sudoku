class Sudoku
{
  int [][] spielfeld;
  
  //Konstruktor
  public Sudoku()
  {
    spielfeld = new int [9][9]; //Spielfeld 9x9 Felder

    // Felder werden leer erzeugt
    for (int i = 0; i < 9; i++)
    {
      for (int j = 0; j < 9; j++)
      {
        spielfeld [i][j]= 0;
      }
    }
  }

  // Hilfsmethoden um zu überprüfen, ob eine Zahl in einer Zeile, Spalte
  // oder in einem kleinen Quadrat steht

  private boolean leereSpalte (int zahl, int x)
  {
    boolean leer = true;
    for (int y = 0; y < 9; y++){
      if(getZahl(x,y) == zahl){
        leer = false;
        break;
      }
    }
    return leer;
  }
  
   private boolean leereZeile (int zahl, int y)
  {
    boolean leer = true;
    for (int x = 0; x < 9; x++){
      if(getZahl(x,y)  == zahl){
        leer = false;
        break;
      }
    }
    return leer;
  }



  //überprüft, ob die Zahl im kleinen Quadrat steht
  private boolean boxfrei (int zahl, int x, int y)
  {
    boolean boxleer = true;
    // obere linke Ecke der Box herausfinden
    int boxx;
    int boxy;
    boxx = x- (x%3);
    boxy = y- (y%3);


    for(int ix=0; ix < 3; ix++){
      for(int iy=0; iy < 3; iy++){
        if(getZahl(ix+boxx, iy+boxy) == zahl){
          boxleer = false;
          break;
        }
      }
      if (!boxleer){
        break;
      }
    }

    return boxleer;
  }



  // überprüft, ob (x,y) ein Feld ist, auf das man die Zahl setzen könnte.
  public boolean moeglichesFeld(int zahl, int x, int y)
  {
   return    leereZeile(zahl,y)
          && leereSpalte(zahl,x)
          && boxfrei(zahl,x,y)
          && getZahl(x,y) == 0;
  }

  // setzt die zahl auf das Feld (x,y)
  public void setZahl(int zahl,int x, int y)
  {
    spielfeld[x][y]= zahl;
  }
  
  //ließt eine Zahl aus
  public int getZahl(int x, int y)
  {
    return spielfeld[x][y];
  }

  //Lösungsalgorithmus
   public boolean backtracking(int zeile, int spalte)
  {
    if (getZahl(spalte,zeile) != 0){
      int neuspalte = spalte+1;
      int neuzeile = zeile;
      if(neuspalte > 8){
        neuspalte = 0;
        neuzeile++;
      }
      if(neuzeile > 8){
        System.out.println("Finale Lösung");
        System.out.println("Spalte: "+spalte+" Zeile: "+zeile);
        System.out.println("Zahl: "+getZahl(spalte, zeile));
        return true;
      }
      return backtracking(neuzeile, neuspalte);
    }
      List<Integer> moeglich;
    moeglich = new List<Integer>();
      // alle moeglichen Zahlen an Kord
      for(int pos = 1;pos < 10; pos++){
          if(moeglichesFeld(pos, spalte, zeile)){
            moeglich.append(pos);
          }
      }
      // keine Zahl möglich
      if(moeglich.isEmpty()){
        return false;
      }

      moeglich.toFirst();

      while (moeglich.hasAccess()){
        setZahl(moeglich.getContent(), spalte, zeile);
        // welche zeile jetzt
        int neuspalte = spalte+1;
        int neuzeile = zeile;
        if(neuspalte > 8){
          neuspalte = 0;
          neuzeile++;
        }
        if(neuzeile > 8){
          System.out.println("Finale Lösung");
          System.out.println("Spalte: "+spalte+" Zeile: "+zeile);
          System.out.println("Zahl: "+moeglich.getContent());
          print();
          return true;
        }

        if(backtracking(neuzeile, neuspalte)){
          return true;
        }
        else{
          setZahl(0, spalte, zeile);
          moeglich.next();
        }

      }
      return false;
   }


   public void print(){
     for(int ix=0; ix < 9; ix++){
       for(int iy=0; iy < 9; iy++){
         System.out.print(spielfeld[iy][ix]+"\t");
       }
       System.out.println("");
     }
   }
 }
