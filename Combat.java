import java.lang.Character;
import java.util.Scanner;
import java.util.Random;

public class Combat {
  
  public Combat(Player player, int dungeonLvl, int encounters){
    if (encounters == 10) { //boss
      bossFight(player, dungeonLvl);
    }
    else {
      Random r = new Random();
      int shopChance = r.nextInt(9);
      if (shopChance < 1 && encounters > 4) { //10% chance for shop
        System.out.println("You found a shop!\n");
        Shop s = new Shop(player);
      }
      else {
        Scanner sc = new Scanner(System.in);
        System.out.println("You've been attacked!");
        Monster monster = new Monster("Skeleton", dungeonLvl);
        System.out.println("Initializing combat..");
        boolean potion = false; //if true, at end of combat remove potion buffs
        boolean magicBuff = false; //if true, remove magic buffs at end of combat
        int choice;
        boolean inCombat = true;
        while (inCombat) {
          System.out.println("\nCombat Options:\n1. Attack    2. Magic");
          System.out.println("3. Potions   4. Flee\n5: Stats");
          choice = sc.nextInt();
          switch (choice) {
            case 1:
              if (attack(player, monster)) {
              inCombat = false;
            }
              else {
                //regen mana (subject to change)
                player.setMana(player.getMana() + 2);
                if (player.getMana() > player.getMaxMana()){
                  player.setMana(player.getMaxMana());
                }
                // 
              }
              break;
            case 2:
              int magic = player.getMagic().magicMenu();
              if (magic == 12) {  //warrior's anger skill used, take away buff after combat
                magicBuff = true; 
              }
              if (magic == 13) { //warrior's damage skill, deal damage
                if (player.getMagic().magicDamage(player, monster, 20)){
                  inCombat = false;
                }
              }
              break;
            case 3:
              potion = player.getInv().potionMenu(player);
              break;
            case 4:
              if (player.getChar().getSpd() > monster.getSpd()) { //if faster than monster, always escape
              System.out.println("You retreat!");
              inCombat = false;
            } 
              else {
                int rand = r.nextInt(2); // 50% chance of escaping
                if (rand == 1) {
                  System.out.println("You retreat!");
                  inCombat = false;
                } else {
                  System.out.println("Failed to retreat!");
                }
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
        if (magicBuff) {
          player.getMagic().removeBuffs();
          magicBuff = false;
        }
      }
    }
  }
  
  public void bossFight(Player p, int dungeonLvl){
    Monster boss = new Monster("Skeleton King", dungeonLvl, true);
    Scanner sc = new Scanner(System.in);
    System.out.println("You encounter a strong evil presence..");
    System.out.println("Initializing combat..");
    Random r = new Random();
    boolean potion = false; //if true, at end of combat remove potion buffs
    boolean magicBuff = false; //if true, remove magic buffs at end of combat
    int choice;
    boolean inCombat = true;
    while (inCombat) {
      System.out.println("\nCombat Options:\n1. Attack\n2. Magic\n3. Potions\n4. Flee\n5: Stats\n");
      choice = sc.nextInt();
      switch (choice) {
        case 1:
          if (attack(p, boss)) {
          inCombat = false;
        }
          else {
            //regen mana (subject to change)
            p.setMana(p.getMana() + 2);
            if (p.getMana() > p.getMaxMana()){
              p.setMana(p.getMaxMana());
            }
            // 
          }
          break;
        case 2:
          int magic = p.getMagic().magicMenu();
          if (magic == 12) {  //warrior's anger skill used, take away buff after combat
            magicBuff = true; 
          }
          if (magic == 13) { //warrior's damage skill, deal damage
            if (p.getMagic().magicDamage(p, boss, 20)){
              inCombat = false;
            }
          }
          break;
        case 3:
          potion = p.getInv().potionMenu(p);
          break;
        case 4:
          if (p.getChar().getSpd() > boss.getSpd()) { //if faster than monster, always escape
          System.out.println("You retreat!");
          inCombat = false;
        } 
          else {
            int rand = r.nextInt(2); // 50% chance of escaping
            if (rand == 1) {
              System.out.println("You retreat!");
              inCombat = false;
            }
            else {
              System.out.println("Failed to retreat!");
            }
          }
          break;
        case 5:
          p.showStats();
          System.out.println();
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }
    if (potion) {
      p.getInv().removeBuffs();
      potion = false;
    }
    if (magicBuff) {
      p.getMagic().removeBuffs();
      magicBuff = false;
    }
  }
  
  public boolean attack(Player player, Monster monster){
    if (monster.getHp() > 0){ //monster not dead
      //the one with more speed attacks first
      boolean evadeP = player.evadeP(player); //booleans that will decide if player/monster will evade
      boolean evadeM = monster.evadeM(monster);
      boolean critP = player.critP(player); //booleans for player/monster crit
      boolean critM = monster.critM(monster);
      if (player.getChar().getSpd() > monster.getSpd()) { //player first
        if (evadeM) { System.out.println("The " + monster.getName() + " evades your attack!");}
        else {
          int damage = player.getChar().regAtk() - monster.getDef();
          if (critP){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          monster.takeDmg(damage);
          System.out.println("You dealt " + damage + " damage to " + monster.getName() + " !");
          if (monster.getHp() == 0) { return monster.monsterDead(player, monster);}
          System.out.println(monster.getName() + " Health: " + monster.getHp());
        }
        System.out.println("\nEnemy Attacking!");
        if (evadeP) { System.out.println("You evade the attack!");}
        else {
          int damage = monster.attack() - player.getChar().getDef();
          if (critM){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          if(damage < 0){ damage = 0;}
          player.takeDmg(damage);
          System.out.println("You took " + damage + " damage.");
          if (player.playerDead(player)){ return true;}
          System.out.println("Health: "+ player.getHp());
          System.out.println("Mana: "+ player.getMana());
        }
      }
      if (player.getChar().getSpd() <= monster.getSpd()) { //monster first
        System.out.println("\nEnemy Attacking!");
        if (evadeP) { System.out.println("You evade the attack!");}
        else {
          int damage = monster.attack() - player.getChar().getDef();
          if (critM){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          if(damage < 0){ damage = 0;}
          player.takeDmg(damage);
          System.out.println("You took " + damage + " damage.");
          if (player.playerDead(player)){ return true;}
          System.out.println("Health: "+ player.getHp());
          System.out.println("Mana: "+ player.getMana());
        }
        
        if (evadeM) { System.out.println("The " + monster.getName() + " evades your attack!");}
        else {
          int damage = player.getChar().regAtk() - monster.getDef();
          if (critP){ damage += damage; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
          monster.takeDmg(damage);
          System.out.println("You dealt " + damage + " damage to " + monster.getName() + " !");
          if (monster.getHp() == 0) { return monster.monsterDead(player, monster);}
          System.out.println(monster.getName() + " Health: " + monster.getHp());
        }
      }
    }
    return false;
  }
}