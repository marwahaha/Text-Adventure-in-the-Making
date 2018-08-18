import java.util.Random;
import java.util.Scanner;

public class Monster{
    //monsters won't use mana/magic stats?
    private String name;
    private boolean boss; //?? idk if this boolean is needed
    private int hp, str, def, spd, lck;
    
    private String[] monsters = { //add more later
      "Skeleton", "Slime"
    };
    
    private String[] bosses = { //add more later
      "Skeleton King"
    };
    
    public Monster(String name, int dungeonLvl){
        //skeleton & slime basic monsters
        if (name.equals("Skeleton")){
            this.name = name;
            this.hp = 20 + (5*dungeonLvl);
            this.str = 4 + (3*dungeonLvl);
            this.def = 2 + (3*dungeonLvl);
            this.spd = 1 + (3*dungeonLvl);
            this.lck = 1 + (3*dungeonLvl);
        }
        if (name.equals("Slime")){
            this.name = name;
            this.hp = 20 + (3*dungeonLvl);
            this.str = 2 + (3*dungeonLvl);
            this.def = 4 + (3*dungeonLvl);
            this.spd = 1 + (3*dungeonLvl);
            this.lck = 1 + (3*dungeonLvl);
        }
    }
    
    public Monster(String boss, int dungeonLvl, boolean isBoss){
      if (boss.equals("Skeleton King")) {
        this.boss = isBoss; //?? idk if this boolean is needed
        this.name = name;
        this.hp = 60 + (5*dungeonLvl);
        this.str = 6 + (3*dungeonLvl);
        this.def = 6 + (3*dungeonLvl);
        this.spd = 5 + (3*dungeonLvl);
        this.lck = 3 + (3*dungeonLvl);
      }
    }

    public int attack(){
        return str+spd;
    }

    public void takeDmg(int x){
        this.hp-=x;
    }

    public boolean critM(Monster m){
      Random r = new Random();
      int rand = r.nextInt(100); //initial 10% chance to crit, increases with luck
      rand += m.getLck();
      if (rand >= 90) return true;
      else return false;
    }
    
    public boolean evadeM(Monster monster){
      Random r = new Random();
      int rand = r.nextInt(100); //0-99
      rand += monster.getSpd();
      if (rand >= 95) return true; //initial 5% chance to evade, increases with speed
      else return false;
    }
    
      public boolean monsterDead(Player player, Monster monster){
    Random r = new Random();
    int randInt1 = r.nextInt(10) + 5; //exp
    int randInt2 = r.nextInt(10) + 5; //gold
    player.setGold(player.getGold() + randInt2);
    System.out.println("\nEnemy Defeated! You gained " + randInt2 + " gold and " + randInt1 + " Exp!");
    player.setExp(player.getExp() + randInt1);
    player.checkExp();
    int randInt = r.nextInt(100); //random # 0 - 99
    randInt += player.getChar().getLck();
    if (randInt >= 70){ //30% chance to drop item (for now) + added chance with player's luck
      Item i = new Item("Rusty Sword"); //making item first
      i = i.itemDrop(); //item becomes randomized
      System.out.println("\nThe " + monster.getName() + " dropped an item!");
      if (i.getType().equals("potion")){
        System.out.println("Do you wish to pick up " + i.getDesc() + "?");
      }
      else {
        System.out.println("Do you wish to equip " + i.getDesc() + "?");
      }
      Scanner sc = new Scanner(System.in);
      boolean decided = false;
      while (!decided) {
        System.out.println("1: Yes \n2: No");
        if(!i.getType().equals("potion")){ System.out.println("3: Compare Item");}
        int c = sc.nextInt();
        switch(c) {
          case 1: //if player already has item in corresponding slot, add it to inventory
            if (i.getType().equals("potion")) { //if the dropped item is a potion, add to inventory
            player.getInv().addItem(i);
          }
            if (player.getItem(i.getType()) != null) {
              System.out.println("You already have " + player.getItem(i.getType()).getDesc() 
                                   + " equipped, do you want to put " + i.getDesc() + " in your inventory?\n1. Yes\n2. No");
              int c2 = sc.nextInt();
              boolean decided2 = false;
              while (decided2 == false) {
                switch(c2) {
                  case 1:
                    player.getInv().addItem(i);
                    System.out.println(i.getDesc() + " was put in your inventory.");
                    decided2 = true;
                    break;
                  case 2:
                    System.out.println("You leave the " + i.getDesc() + " behind.");
                    decided2 = true;
                    break;
                  default:
                    System.out.println("Invalid input.");
                    break;
                }
              }
            }
            else {
              player.equip(i);
              System.out.println("You equipped the " + i.getDesc() + ".");
            }
            decided=true;
            break;
          case 2:
            System.out.println("Do you want to put the " + i.getDesc() + " into your inventory?");
            System.out.println("1: Yes \n2: No");
            int c3 = sc.nextInt();
            boolean decided2 = false;
            while (decided2 == false) {
              switch(c3) {
                case 1:
                  player.getInv().addItem(i);
                  System.out.println(i.getDesc() + " was put in your inventory.");
                  decided2 = true;
                  break;
                case 2:
                  System.out.println("You leave the " + i.getDesc() + " behind.");
                  decided2 = true;
                  break;
                default:
                  System.out.println("Invalid input.");
                  break;
              }
            }
            decided=true;
            break;
          case 3:
            player.compareItem(i);
            break;
          default:
            System.out.println("Invalid input.");
            break;
        }
      }
    }
    return true;
  }
    
    public int getHp(){ 
      if (hp < 0) return 0;
      else return this.hp; 
    }
    public void setHp(int x){ this.hp = x;}
    public String getName(){ return this.name; }
    public int getStr() { return this.str; }
    public int getDef() { return this.def; }
    public int getSpd() { return this.spd; }
    public int getLck(){ return this.lck; }
    public void setStr(int x) {this.str = x; }
    public void setDef(int x) {this.def = x; }
    public void setSpd(int x) {this.spd = x; }
    public void setLck(int x) {this.lck = x; }
}