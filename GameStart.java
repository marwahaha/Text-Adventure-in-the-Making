import java.util.Scanner; 

public class GameStart{
  private static class Character{
    private String name;
    private String gameClass;
    private int lvl, hp, mana, gold, str, def, spd, mag, lck;
    
    
  }
  
  public static void main(String[] arg){
    Scanner sc = new Scanner(System.in);
    boolean quit = false;
    int menuChoice;
    
    for (int i = 0; i <= 2; i++) {
      if (i == 0)
        System.out.println(i + ". Start");
      else if (i == 1)
        System.out.println(i + ". Reset");
      else 
        System.out.println(i + ". Quit");
    }
    
    while (quit != true) {
      System.out.print("Choose a menu option..");
      menuChoice = sc.nextInt();
      switch(menuChoice){
        case 0:
          System.out.println("Starting game..");
          //start game
          break;
        case 1:
          System.out.println("Resetting progress..");
          //reset
          break;
        case 2:
          System.out.println("Exiting game..");
          //quit
          quit = true;
          break;
        default:
          System.out.println("Invalid Choice");
      }
    }
  }
}