import java.util.Scanner; 

public class Player{
  //general
  //private String name;
  private String gameClass;
  private int lvl;
  private int hp;
  private int mana;
  private int gold;
  //stats
  private int str; //attack damage
  private int def; //mitigation
  private int spd; //evasion
  private int mag; //mana & magic damage
  private int lck; //crit & magic find(?)
  
  public Player(){
    this.lvl = 1;
    this.gold = 0;
    Scanner sc2 = new Scanner(System.in);
    int choice;
    boolean loop = true;
    while (loop == true) {
      System.out.println("Choose your class:");
      System.out.println("1: Warrior");
      System.out.println("2: Rogue");
      System.out.println("3: Wizard");
      choice = sc2.nextInt();
      switch(choice){
        case 1:
          System.out.println("Welcome, Warrior.");
          this.gameClass = "Warrior";
          this.hp = 50;
          this.mana = 15;
          this.str = 5; 
          this.def = 4;
          this.spd = 3;
          this.mag = 1; 
          this.lck = 1;
          loop = false;
          break;
        case 2:
          System.out.println("Welcome, Rogue.");
          this.gameClass = "Rogue";
          this.hp = 40;
          this.mana = 20;
          this.str = 3;
          this.def = 2;
          this.spd = 5;
          this.mag = 1; 
          this.lck = 2;
          loop = false;
          break;
        case 3:
          System.out.println("Welcome, Wizard.");
          this.gameClass = "Wizard";
          this.hp = 35;
          this.mana = 35;
          this.str = 2;
          this.def = 2;
          this.spd = 3;
          this.mag = 5; 
          this.lck = 1;
          loop = false;
          break;
        default:
          System.out.println("Invalid Choice");
      }
    }
  }
  
  public void showStats(){
    System.out.println("HP: " + hp); 
    System.out.println("MP: " + mana); 
    System.out.println("Gold: " + gold); 
    System.out.println("STR: " + str); 
    System.out.println("DEF: " + def); 
    System.out.println("SPD: " + spd); 
    System.out.println("MAG: " + mag); 

  }
}