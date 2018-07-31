import java.util.Scanner;

public class Game{
  
  public static void main(String[] arg){
    Scanner sc = new Scanner(System.in);
    boolean quit = false;
    int menuChoice;
    while (quit != true) {
      System.out.println("Choose a menu option..\n");
      System.out.println("1. New Game\n2. Continue");
      System.out.println("3. Quit");
      menuChoice = sc.nextInt();
      switch(menuChoice){
        case 1:
          System.out.println("Starting game..");
          quit = inGame();
          break;
        case 2:
          System.out.println("Continuing save..");
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
  
  public static boolean inGame(){
    Player p = new Player();
    int dungeonLvl = 0; //increase level after boss
    int encounters = 0; //boss after 10 encounters
    Scanner input = new Scanner(System.in);
    int result;
    System.out.println("You enter the dungeon!");
    boolean potion = false; //if a potion is used out of combat, remove it after combat
    boolean inProgress = true;
    while (inProgress) {
      menu();
      result = input.nextInt();
      switch(result){
        case 1:
          if (encounters < 10) { //normal fights
          Combat combat = new Combat(p, dungeonLvl, encounters);
          encounters++;
        }
          else { //if 10 encounters, boss fight, next dungeon level
            Combat boss = new Combat(p, dungeonLvl, encounters);
            dungeonLvl++;
            encounters = 0;
          }
          if (p.getHp() == 0){ inProgress = false;}
          if (potion){
            p.getInv().removeBuffs();
            potion = false;
          }
          break;
        case 2:
          System.out.println("Opening Inventory");
          potion = invMenu(p);
          break;
        case 3:
          p.showStats();
          break;
        case 4:
          System.out.println("Quitting");
          inProgress = false;
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }
    return inProgress;
  }
  
  public static void menu(){
    System.out.println("\nOptions: ");
    System.out.println("1. Proceed Forward");
    System.out.println("2. Inventory");
    System.out.println("3. Stats");
    System.out.println("4. Quit");
  }
  
  public static boolean invMenu(Player player){ //return true if potion was used out of combat else false
    System.out.println("\n1. Equipment\n2. Potions\n3. Back");
    Scanner s = new Scanner(System.in);
    int choice = s.nextInt();
    boolean potion = false;
    boolean loop = true;
    while (loop)
      switch(choice){
      case 1:
        System.out.println("Equipment:");
        player.showEquipped();
        loop = false;
        break;
      case 2:
        potion = player.getInv().potionMenu(player);
        loop = false;
        break;
      case 3:
        loop = false;
        break;
      default:
        System.out.println("Invalid choice.");
    }
    return potion;
  }
}