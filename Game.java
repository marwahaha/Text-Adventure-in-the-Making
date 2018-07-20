import java.util.Scanner; 

public class Game{

  public static void main(String[] arg){
    Scanner sc = new Scanner(System.in);
    boolean quit = false;
    int menuChoice;
    while (quit != true) {
      System.out.println("Choose a menu option..");
      System.out.println("1. Start");
      System.out.println("2. Reset");
      System.out.println("3. Quit");
      menuChoice = sc.nextInt();
      switch(menuChoice){
        case 1:
          System.out.println("Starting game..");
          Player p = new Player();
          //start game
          p.levelUp();
          p.showStats();
          break;
        case 2:
          System.out.println("Resetting progress..");
          //erase any saved game... (how do you even save progress?)
          break;
        case 3:
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