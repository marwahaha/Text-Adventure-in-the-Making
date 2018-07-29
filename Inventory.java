import java.util.LinkedList;
import java.util.Scanner;

public class Inventory{
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
     System.out.println("Inventory full!"); 
    }
  }
  
  public boolean useItem(int x){ //use for duration of combat
    if (x >= curSize) {
      System.out.println("There is no item to use!");
      return false;
    }
    else {
      potionsUsed[potionIndex++] = x; //store which potion was used 
      if (potionIndex > 4){ potionIndex = 4;}
      Item i = inv.get(x);
      p.p1.setStr(p.p1.getStr() + i.getStr()); 
      p.p1.setDef(p.p1.getDef() + i.getDef()); 
      p.p1.setSpd(p.p1.getSpd() + i.getSpd()); 
      p.p1.setMag(p.p1.getMag() + i.getMag()); 
      p.p1.setLck(p.p1.getLck() + i.getLck()); 
      p.p1.setLck(p.p1.getLck() + i.getLck()); 
      p.setHp(p.getHp() + i.getHp());
      if (p.getHp() > p.getMaxHp()){ p.setHp(p.getMaxHp());}
      inv.remove(i);
      usedInv.add(i);
      curSize--;
      return true;
    }
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
    p.p1.setStr(p.p1.getStr() - i.getStr()); 
    p.p1.setDef(p.p1.getDef() - i.getDef()); 
    p.p1.setSpd(p.p1.getSpd() - i.getSpd()); 
    p.p1.setMag(p.p1.getMag() - i.getMag()); 
    p.p1.setLck(p.p1.getLck() - i.getLck()); 
    usedInv.remove(i);
  }
  
  public void showInventory(){
    if (curSize == 0) {
      System.out.println("\nPotions:\n1. Empty"); 
      System.out.println("2. Empty"); 
      System.out.println("3. Empty"); 
      System.out.println("4. Empty"); 
      System.out.println("5. Empty"); 
    }
    else {
      System.out.println("\nPotions:"); 
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
          System.out.println("Invalid choice.");
      }
    }
    return ret;
  }
  
  public Item getItem(int index){
    return inv.get(index);
  }
  
  public void removeItem(Item i){
    inv.remove(i);
  }
}