import java.util.Scanner; 

public class GameStart{
  
  private static class Character{
    private String name;
    private String gameClass;
    private int lvl, hp, mana, gold, str, def, spd, mag, lck;
    
    public Character(){
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter your name: ");
      this.name = sc.next();
      System.out.println("Choose your class:");
          System.out.println("1: Warrior");
          System.out.println("2: Rogue");
          System.out.println("3: Wizard");
      int choice;
      boolean quit = false;
      choice = sc.nextInt();
      switch(choice){
        case 1:
          System.out.println("Welcome, Warrior "+ name +".");
          this.gameClass = "Warrior";
          break;
        case 2:
          System.out.println("Welcome, Rogue "+ name +".");
          this.gameClass = "Rogue";
          break;
        case 3:
          System.out.println("Welcome, Wizard "+ name +".");
          this.gameClass = "Wizard";
          break;
        default:
          System.out.println("Invalid Choice");
      }
    }
  }
  
  private Character char;
    
  public static void main(String[] arg){
    Scanner sc = new Scanner(System.in);
    boolean quit = false;
    int menuChoice;
        System.out.println("1. Start");
        System.out.println("2. Reset");
        System.out.println("3. Quit");
    
    while (quit != true) {
      System.out.print("Choose a menu option..");
      menuChoice = sc.nextInt();
      switch(menuChoice){
        case 1:
          System.out.println("Starting game..");
          //start game
          this.char = new Character();
          break;
        case 2:
          System.out.println("Resetting progress..");
          //reset
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