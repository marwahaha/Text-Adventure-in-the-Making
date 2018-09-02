import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class Magic implements Serializable{
  //each class has 3 skills that they will learn at lvl 3, 5, 7
  
  private Player p;
  private String[] pSkills;
  
  //warrior skills
  private String[] warSkills = {
    "Regenerate", //heal 
    "Anger", //raise str
    "Dominating Blow"}; //stronk hit
  
  //rogue skills
  private String[] rogSkills = {
    "Bandage", //heal
    "Quicken", //inc spd
    "Backstab"}; //stronk hit
  
  //wizard skills
  private String[] wizSkills = {
    "Energy Shield", //damage mitigation?
    "Fireball", //med hit
    "Lightning Strike"}; //stronk hit
  
  public Magic(Player p){
    this.p = p;
    this.pSkills = new String[3];
  }
  
  public void learn(int skillNum){
    if (p.getGameClass().equals("Warrior")){
      pSkills[skillNum] = warSkills[skillNum];
      System.out.println("You learned the skill " + warSkills[skillNum] + "!");
    }
    else if (p.getGameClass().equals("Rogue")){
      pSkills[skillNum] = rogSkills[skillNum];
      System.out.println("You learned the skill " + rogSkills[skillNum] + "!");
    }
    else if (p.getGameClass().equals("Mage")){
      pSkills[skillNum] = wizSkills[skillNum];
      System.out.println("You learned the skill " + wizSkills[skillNum] + "!");
    }
  }
  
  public int useMagic(int skillNum){
    //returns a number according to skill and class (ex: 11 = warrior/skill 1, 21 = rogue/skill 1)
    String skill = pSkills[skillNum];
    if (skill.equals("Regenerate")) {
      if (p.getMana() - 15 < 0) {
        System.out.println("Not enough mana!");
        return -11;
      }
      else {
        p.setHp(p.getHp() + 10); //heal 10 hp
        if (p.getHp() > p.getMaxHp()) {
          p.setHp(p.getMaxHp());
        }
        p.setMana(p.getMana() -15); 
        System.out.println("\nYou regenerate 10 health.");
        return 11;
      }
    } 
    else if (skill.equals("Anger")) {
      if (p.getMana() - 20 < 0) {
        System.out.println("Not enough mana!");
        return -12;
      }
      else {
        p.getChar().setStr(p.getChar().getStr() + 3); //gain 3 str for combat
        p.setMana(p.getMana() - 20); 
        //remove buff after combat somehow
        System.out.println("\nYour anger makes you stronger.");
        return 12;
      }
    }    
    else if (skill.equals("Dominating Blow")) {
      //deal 30? damage
      if (p.getMana() - 25 < 0) {
        System.out.println("Not enough mana!");
        return -13;
      }
      else {
        p.setMana(p.getMana() - 25); 
        
        return 13;
      }
    }
    if (skill.equals("Bandage")) {
      if (p.getMana() - 10 < 0) {
        System.out.println("Not enough mana!");
        return -21;
      }
      else {
        p.setHp(p.getHp() + 10); //heal 10 hp
        if (p.getHp() > p.getMaxHp()) {
          p.setHp(p.getMaxHp());
        }
        p.setMana(p.getMana() - 10); 
        System.out.println("\nYou heal 10 health.");
        return 21;
      }
    } 
    else if (skill.equals("Quicken")) {
      if (p.getMana() - 20 < 0) {
        System.out.println("Not enough mana!");
        return -22;
      }
      else {
        p.getChar().setSpd(p.getChar().getSpd() + 20); //gain 20 spd? for combat
        p.setMana(p.getMana() - 20); 
        //remove buff after combat somehow
        System.out.println("\nYou become more agile.");
        return 22;
      }
    }
    else if (skill.equals("Backstab")) {
      //deal 35? damage
      if (p.getMana() - 25 < 0) {
        System.out.println("Not enough mana!");
        return -23;
      }
      else {
        p.setMana(p.getMana() - 25); 
        return 23;
      }
    }
    if (skill.equals("Energy Shield")) {
      if (p.getMana() - 15 < 0) {
        System.out.println("Not enough mana!");
        return -31;
      }
      else {    
        p.getChar().setDef(p.getChar().getDef() + 3);
        System.out.println("\nYou are protected by a ward.");
        return 31;
      }
    } 
    else if (skill.equals("Fireball")) {
      //deal 20? damage
      if (p.getMana() - 20 < 0) {
        System.out.println("Not enough mana!");
        return -32;
      }
      else {
        p.setMana(p.getMana() - 25); 
        return 32;
      }
    }
    else if (skill.equals("Lightning Strike")) {
      //deal 40? damage
      if (p.getMana() - 30 < 0) {
        System.out.println("Not enough mana!");
        return -33;
      }
      else {
        p.setMana(p.getMana() - 30); 
        return 33;
      }
    }
    return -1;
  }
  
  public void removeBuffs(){
    if (p.getGameClass().equals("Warrior")){
      //remove anger buff since its the only buff a warrior has
      p.getChar().setStr(p.getChar().getStr() - 3);
    }
    else if (p.getGameClass().equals("Rogue")){ //remove quicken buff
      p.getChar().setSpd(p.getChar().getSpd() - 20);
    }
    else if (p.getGameClass().equals("Mage")){ //remove energy shield
      p.getChar().setDef(p.getChar().getDef() - 3);
    }
  }
  
  public int magicMenu(){
    boolean loop = true;
    Scanner s = new Scanner(System.in);
    while (loop){
      System.out.println("\nMagic:");
      if (pSkills[0] != null) {System.out.println("1. " + pSkills[0]);}
      else { System.out.println("1. Not Learned");}
      if (pSkills[1] != null) {System.out.println("2. " + pSkills[1]);}
      else { System.out.println("2. Not Learned");}
      if (pSkills[2] != null) {System.out.println("3. " + pSkills[2]);}
      else { System.out.println("3. Not Learned");}
      System.out.println("4. Back");
      int c = s.nextInt();
      switch(c){
        case 1:
          if (pSkills[0] != null) { //use skill 1
          loop = false;
          return useMagic(0);
        }
          else { //print 
            System.out.println("You have not learned this skill!\n");
          }
          break;
        case 2:
          if (pSkills[1] != null) { //use skill 2
          loop = false;
          return useMagic(1);
        }
          else { //print 
            System.out.println("You have not learned this skill!\n");
          }
          break;
        case 3:
          if (pSkills[2] != null) { //use skill 3
          loop = false;
          return useMagic(2);
        }
          else { //print 
            System.out.println("You have not learned this skill!\n");
          }
          break;
        case 4:
          loop = false;
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }
    return -1;
  }
  
  public boolean magicDamage(Player p, Monster m, int damage){
    boolean evadeP = p.evadeP(p); //booleans that will decide if p/m will evade
    boolean critP = p.critP(p); //booleans for p/m crit
    boolean critM = m.critM(m);
    int dmg = damage;
    if (p.getChar().getSpd() > m.getSpd()) { //p first
      if (critP){ 
        System.out.println("\nCritical hit!"); //crit is 2 times damage multiplier for now
        System.out.println("You deal " + dmg + " damage to " + m.getName() + "!");
        dmg += dmg;
        m.takeDmg(dmg);
      }
      else {
        m.takeDmg(dmg);
        System.out.println("\nYou deal " + dmg + " damage to " + m.getName() + "!");
      }
      if (m.getHp() == 0) { return m.monsterDead(p, m);}
      else {
        System.out.println(m.getName() + " Health: " + m.getHp());
      }
      System.out.println("\n" + m.getName() + " attacking!");
      if (evadeP) { System.out.println("You evade the attack!");}
      else {
        int damageM = m.attack() - p.getChar().getDef();
        if (critM){ damageM += damageM; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
        if(damageM < 0){ damageM = 0;}
        p.takeDmg(damageM);
        System.out.println("You take " + damageM + " damage.");
        if (p.playerDead(p)){ return true;}
        System.out.println("Health: "+ p.getHp());
        System.out.println("Mana: "+ p.getMana());
      }
    }
    if (p.getChar().getSpd() <= m.getSpd()) { //m first
      System.out.println("\n" + m.getName() + " attacking!");
      if (evadeP) { System.out.println("You evade the attack!");}
      else {
        int damageM = m.attack() - p.getChar().getDef();
        if (critM){ damageM += damageM; System.out.println("Critical hit!");} //crit is 2 times damage multiplier for now
        if(damageM < 0){ damageM = 0;}
        p.takeDmg(damageM);
        System.out.println("You take " + damageM + " damage.");
        if (p.playerDead(p)){ return true;}
        System.out.println("Health: "+ p.getHp());
        System.out.println("Mana: "+ p.getMana());
      }
      if (critP){ System.out.println("\nCritical hit!");
        dmg += dmg;
        m.takeDmg(dmg);
        System.out.println("You deal " + dmg + " damage to " + m.getName() + "!");
      } 
      else {
        m.takeDmg(dmg);
        System.out.println("\nYou deal " + dmg + " damage to " + m.getName() + "!");
      }
      if (m.getHp() == 0) { return m.monsterDead(p, m);}
      else {
        System.out.println(m.getName() + " Health: " + m.getHp());}
    }
    return false;
  }
}