public class Player{
  //general
  private String name;
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
  
  Player(String name, String gameClass){
    this.name = name;
    this.gameClass = gameClass;
    this.lvl = 1;
    this.gold = 0;
    if (gameClass.equals("Warrior")){ 
      this.hp = 50;
      this.mana = 15;
      this.str = 5; 
      this.def = 4;
      this.spd = 3;
      this.mag = 1; 
      this.lck = 1;
    }
    if (gameClass.equals("Rogue")){ 
      this.hp = 40;
      this.mana = 20;
      this.str = 3;
      this.def = 2;
      this.spd = 5;
      this.mag = 1; 
      this.lck = 2;
    }
     if (gameClass.equals("Wizard")){ 
      this.hp = 35;
      this.mana = 35;
      this.str = 2;
      this.def = 2;
      this.spd = 3;
      this.mag = 5; 
      this.lck = 1;
    }
  }
}