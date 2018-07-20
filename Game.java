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
          //player choice
          Scanner sc2 = new Scanner(System.in);
          System.out.println("Enter your name: ");
          String name = sc.next();
          System.out.println("Choose your class:");
          System.out.println("1: Warrior");
          System.out.println("2: Rogue");
          System.out.println("3: Wizard");
          int choice;
          choice = sc2.nextInt();
          Player p;
          switch(choice){
            case 1:
              System.out.println("Welcome, Warrior "+ name +".");
              p = new Player(name, "Warrior");
              break;
            case 2:
              System.out.println("Welcome, Rogue "+ name +".");
              p = new Player(name, "Rogue");
              break;
            case 3:
              System.out.println("Welcome, Wizard "+ name +".");
              p = new Player(name, "Wizard");
              break;
            default:
              System.out.println("Invalid Choice");
          }
          //start game
          
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