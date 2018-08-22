import java.util.LinkedList;
import java.util.Scanner;
import java.io.Serializable;

public class Inventory implements java.io.Serializable{
  private final int maxSize = 5;
  private int curSize;
  private LinkedList<Item> inv; //usable potions in combat (not permanent buffs)
  private LinkedList<Item> usedInv; //to store potions after they have been used until they can be deleted
  private Player p;
  private int[] potionsUsed = new int[5]; //keeps track of how many potions were used in combat
  private int potionIndex = 0;
  
  public Inventory(Player p){
    this.curSize = 0;
    this.inv = new LinkedList<Item>();
    this.usedInv = new LinkedList<Item>();
    this.p = p;
  }
  
  public int getSize(){ return this.curSize;}
  
  public void addItem(Item i){
    if (curSize != maxSize) {
      inv.add(curSize++, i);
    }
    else {
      System.out.println("\nInventory full!");
    }
  }
  
  public boolean isFull(){
    if (curSize == 5) return true;
    else return false;
  }
  
  public boolean useItem(int x){ 
    if (x >= curSize) {
      System.out.println("\nThere is no item to use!");
      return false;
    }
    else if (getItem(x).getType().equals("potion")) { //item is potion
      potionsUsed[potionIndex++] = x; //store which potion was used
      if (potionIndex > 4){ potionIndex = 4;}
      Item i = inv.get(x);
      p.getChar().setStr(p.getChar().getStr() + i.getStr());
      p.getChar().setDef(p.getChar().getDef() + i.getDef());
      p.getChar().setSpd(p.getChar().getSpd() + i.getSpd());
      p.getChar().setMag(p.getChar().getMag() + i.getMag());
      p.getChar().setLck(p.getChar().getLck() + i.getLck());
      p.getChar().setLck(p.getChar().getLck() + i.getLck());
      p.setHp(p.getHp() + i.getHp());
      if (p.getHp() > p.getMaxHp()){ p.setHp(p.getMaxHp());}
      inv.remove(i);
      usedInv.add(i);
      curSize--;
    }
    else { //item is equipment
      Scanner s = new Scanner(System.in);
      System.out.println("\nDo you want to equip " + getItem(x).getDesc() + "?");
      System.out.println("1: Yes\n2: No");
      boolean decided = false;
      while (decided == false){
        int c = s.nextInt();
        switch(c) {
          case 1:
            if (p.getItem(getItem(x).getType()) == null) {
            p.equip(getItem(x));
            System.out.println("\nYou equipped " + getItem(x).getDesc() + ".");
          }
            else {
              Item replaced = p.getItem(getItem(x).getType());
              p.unequip(getItem(x).getType());
              System.out.println("\nYou equipped " + getItem(x).getDesc() + " and put " + replaced.getDesc() + " in your inventory."); 
              p.equip(getItem(x));
              removeItem(getItem(x));
              addItem(replaced);
              //System.out.println("\nYou equipped " + getItem(x).getDesc() + " and put " + replaced.getDesc() + " in your inventory."); 
            }
            decided = true;
            break;
          case 2:
            System.out.println("\nYou leave the " + getItem(x).getDesc() + "in your inventory.");  
            decided = true;
            break;
          default:
            System.out.println("\nInvalid choice.");
            break;
        }
      }
    }
    return true;
  }
  
  public void removeBuffs(){ //used to take away buffs when combat ends
    for (int i = 0; i < potionIndex; i++){
      itemUsed(i);
    }
    //reset potions counters
    potionIndex = 0;
    potionsUsed = new int[5];
  }
  
  public void itemUsed(int x){ //helper for removeBuffs
    Item i = usedInv.get(x);
    p.getChar().setStr(p.getChar().getStr() - i.getStr());
    p.getChar().setDef(p.getChar().getDef() - i.getDef());
    p.getChar().setSpd(p.getChar().getSpd() - i.getSpd());
    p.getChar().setMag(p.getChar().getMag() - i.getMag());
    p.getChar().setLck(p.getChar().getLck() - i.getLck());
    usedInv.remove(i);
  }
  
  public void showInventory(){
    if (curSize == 0) {
      System.out.println("\nInventory:\n1. Empty");
      System.out.println("2. Empty");
      System.out.println("3. Empty");
      System.out.println("4. Empty");
      System.out.println("5. Empty");
    }
    else {
      System.out.println("\nInventory:");
      int i;
      for (i = 0; i < curSize; i++){
        System.out.println(i+1 + ". " + inv.get(i).getDesc());
      }
      while (i < 5) {
        System.out.println(++i + ". Empty");
      }
    }
  }
  
  public boolean potionMenu(Player player){
    boolean loop = true;
    boolean ret = false;
    while (loop) {
      player.getInv().showInventory();
      System.out.print("6. Back\n");
      Scanner s = new Scanner(System.in);
      int choice = s.nextInt();
      switch(choice){
        case 1:
          ret = player.getInv().useItem(0);
          if (ret) {loop = false;}
          break;
        case 2:
          ret = player.getInv().useItem(1);
          if (ret) {loop = false;}
          break;
        case 3:
          ret = player.getInv().useItem(2);
          if (ret) {loop = false;}
          break;
        case 4:
          ret = player.getInv().useItem(3);
          if (ret) {loop = false;}
          break;
        case 5:
          ret = player.getInv().useItem(4);
          if (ret) {loop = false;}
          break;
        case 6:
          loop = false;
          break;
        default:
          System.out.println("\nInvalid choice.");
      }
    }
    return ret;
  }
  
  public Item getItem(int index){
    return inv.get(index);
  }
  
  public void removeItem(Item i){
    inv.remove(i);
    curSize--;
  }
}