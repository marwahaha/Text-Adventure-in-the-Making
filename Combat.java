import java.lang.Character;
import java.util.Scanner;
import java.util.Random;

public class Combat {
  
  
  public Combat(Player player){
    //10% shop chance
    Random r = new Random();
    int shopChance = r.nextInt(9);
    if (shopChance < 1) {
      System.out.println("You found a shop!\n");
      Shop s = new Shop(player);
    }
    else {
      Scanner sc = new Scanner(System.in);
      System.out.println("You've been attacked!");
      Monster monster = new Monster("Skeleton");
      System.out.println("Initializing combat..");
      boolean potion = false; //if true, at end of combat remove potion buffs
      int choice;
      boolean inCombat = true;
      while (inCombat){
        System.out.println("\nCombat Options:\n1. Attack    2. Magic");
        System.out.println("3. Potions   4. Flee\n5: Stats");
        choice = sc.nextInt();
        switch(choice) {
          case 1:
            if (attack(player, monster)){ inCombat = false;}
            break;
          case 2:
            System.out.println("Magic not coded yet.");
            break;
          case 3:
            potion = player.getInv().potionMenu(player);
            break;
          case 4:
            if (player.p1.getSpd() > monster.getSpd()){ //if faster than monster, always escape
            System.out.println("You retreat!");
            inCombat = false;
          }
            else { 
              int rand = r.nextInt(2); // 50% chance of escaping
              if (rand == 1){
                System.out.println("You retreat!");
                inCombat = false;
              }
              else{ System.out.println("Failed to retreat!");}
            }
            break;
          case 5:
            player.showStats();
            System.out.println();
            break;
          default:
            System.out.println("Invalid choice.");
        }
      }
      if (potion) {
        player.getInv().removeBuffs();
        potion = false;
      }
    }
  }
  
  public boolean attack(Player player, Monster monster){
    if (monster.getHp() > 0){ //monster not dead
      //the one with more speed attacks first
      boolean evadeP = evadeP(player); //booleans that will decide if player/monster will evade
      boolean evadeM = evadeM(monster);
      boolean critP = critP(player); //booleans for player/monster crit
      boolean critM = critM(monster);
      if (player.p1.getSpd() > monster.getSpd()) { //player first
        if (evadeM) { System.out.println("The " + monster.getName() + " evades your attack!");}
        else {
          int damage = player.p1.regAtk() - monster.getDef();
          if (critP){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          monster.takeDmg(damage);
          System.out.println("You dealt " + damage + " damage to enemy!");
          if (monster.getHp() == 0) { return monsterDead(player, monster);}
          System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left.");
        }
        System.out.println("\nEnemy Attacking!");
        if (evadeP) { System.out.println("You evade the attack!");}
        else {
          int damage = monster.attack() - player.p1.getDef();
          if (critM){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          if(damage < 0){ damage = 0;}
          player.takeDmg(damage);
          System.out.println("You took " + damage + " damage.");
          if (playerDead(player)){ return true;}
          System.out.println("Health remaining: "+ player.getHp());
        }
      }
      if (player.p1.getSpd() <= monster.getSpd()) { //monster first
        System.out.println("\nEnemy Attacking!");
        if (evadeP) { System.out.println("You evade the attack!");}
        else {
          int damage = monster.attack() - player.p1.getDef();
          if (critM){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          if(damage < 0){ damage = 0;}
          player.takeDmg(damage);
          System.out.println("You took " + damage + " damage.");
          if (playerDead(player)){ return true;}
          System.out.println("Health remaining: "+ player.getHp());
        }
        
        if (evadeM) { System.out.println("The " + monster.getName() + " evades your attack!");}
        else {
          int damage = player.p1.regAtk() - monster.getDef();
          if (critP){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          monster.takeDmg(damage);
          System.out.println("You dealt " + damage + " damage to enemy!");
          if (monster.getHp() == 0) { return monsterDead(player, monster);}
          System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left.");
        }
      }
    }
    return false;
  }
  
  public boolean monsterDead(Player player, Monster monster){
    Random r = new Random();
    int randInt = r.nextInt(10); 
    randInt += 6; //between 6 and 15 exp per encounter (for now)
    System.out.println("\nEnemy Defeated! You gained " + randInt + " Exp!");
    player.setExp(player.getExp() + randInt);
    randInt = r.nextInt(10);
    player.setGold(player.getGold() + randInt);
    player.checkExp();
    randInt = r.nextInt(100); //random # 0 - 99
    randInt += player.p1.getLck();
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
      System.out.println("1: Yes \n2: No");
      int c = sc.nextInt();
      if (c == 1){ //if player already has item in corresponding slot, unequip it then equip new item
        if (i.getType().equals("potion")){ //if the dropped item is a potion, add to inventory
          player.getInv().addItem(i);
        }
        if (player.getItem(i.getDesc()) != null){
          player.unequip(i.getDesc());
        }
        player.equip(i);
      }
      if (c == 2){ System.out.println("You leave the " + i.getDesc() + " behind.");}
    }
    return true;
  }
  
  public boolean evadeP(Player player){
    Random r = new Random();
    int rand = r.nextInt(100); //0-99
    rand += player.p1.getSpd();
    if (rand >= 90) return true; //initial 10% chance to evade, increases with speed
    else return false;
  }
  
  public boolean evadeM(Monster monster){
    Random r = new Random();
    int rand = r.nextInt(100); //0-99
    rand += monster.getSpd();
    if (rand >= 90) return true; //initial 10% chance to evade, increases with speed
    else return false;
  }
  
  public boolean critP(Player p){
    Random r = new Random();
    int rand = r.nextInt(100); //initial 10% chance to crit, increases with luck
    rand += p.p1.getLck();
    if (rand >= 90) return true;
    else return false;
  }
  
  public boolean critM(Monster m){
    Random r = new Random();
    int rand = r.nextInt(100); //initial 10% chance to crit, increases with luck
    rand += m.getLck();
    if (rand >= 90) return true;
    else return false;
  }
  
  public boolean playerDead(Player p){ //return true if player health is <= 0
    if (p.getHp() <= 0){ 
      System.out.println("\nYou died!\n");
      return true;
    }
    else return false;
  }
}