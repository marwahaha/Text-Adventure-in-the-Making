import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

public class Item implements Serializable{
  
  private int str, def, spd, mag, lck; //stat bonuses
  private int hp; //used for potion healing amount
  private String desc; //item description
  private String type; //if item if weapon/helment/ring etc
  
  //list of items
  private String[] items = {
    "Rusty Sword", "Rusty Armor", "Rusty Helmet", "Rusty Boots", "Rusty Gauntlets",
    "Rusty Dagger", "Leather Cowl", "Leather Armor", "Leather Boots", "Leather Gauntlets",
    "Old Staff", "Dusty Hood", "Old Robe", "Old Slippers", "Old Armwraps",
    "Lucky Ring", "Ring of Might", "Ring of Speed", "Ring of Wisdom", "Stalwart Ring",
    "Potion of Strength", "Potion of Stoneskin", "Healing Potion (S)", "Healing Potion (M)", "Healing Potion (L)"
  };
  
//  private String[] items = { //test
//    "Rusty Sword",
//    "Potion of Strength", "Potion of Stoneskin", "Healing Potion (S)", "Healing Potion (M)", "Healing Potion (L)"
//  };
  
  public Item(String name){
    if (name.equals("Rusty Sword"))          {str = 2; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "wep";}
    if (name.equals("Rusty Armor"))          {str = 0; def = 2; spd = 0; mag = 0; lck = 0; desc = name; type = "body";}
    if (name.equals("Rusty Helmet"))         {str = 0; def = 2; spd = 0; mag = 0; lck = 0; desc = name; type = "head";}
    if (name.equals("Rusty Boots"))          {str = 0; def = 2; spd = 0; mag = 0; lck = 0; desc = name; type = "feet";}
    if (name.equals("Rusty Gauntlets"))      {str = 0; def = 2; spd = 0; mag = 0; lck = 0; desc = name; type = "arms";}
    if (name.equals("Rusty Dagger"))         {str = 1; def = 0; spd = 1; mag = 0; lck = 1; desc = name; type = "wep";}
    if (name.equals("Leather Cowl"))         {str = 0; def = 1; spd = 1; mag = 0; lck = 1; desc = name; type = "head";}
    if (name.equals("Leather Armor"))        {str = 0; def = 1; spd = 1; mag = 0; lck = 1; desc = name; type = "body";}
    if (name.equals("Leather Boots"))        {str = 0; def = 1; spd = 1; mag = 0; lck = 1; desc = name; type = "feet";}
    if (name.equals("Leather Gauntlets"))    {str = 0; def = 1; spd = 1; mag = 0; lck = 1; desc = name; type = "arms";}
    if (name.equals("Old Staff"))            {str = 0; def = 1; spd = 0; mag = 2; lck = 0; desc = name; type = "wep";}
    if (name.equals("Dusty Hood"))           {str = 0; def = 1; spd = 0; mag = 2; lck = 0; desc = name; type = "head";}
    if (name.equals("Old Robe"))             {str = 0; def = 1; spd = 0; mag = 2; lck = 0; desc = name; type = "body";}
    if (name.equals("Old Slippers"))         {str = 0; def = 1; spd = 1; mag = 1; lck = 0; desc = name; type = "feet";}
    if (name.equals("Old Armwraps"))         {str = 0; def = 1; spd = 0; mag = 1; lck = 0; desc = name; type = "arms";}
    if (name.equals("Lucky Ring"))           {str = 0; def = 0; spd = 0; mag = 0; lck = 2; desc = name; type = "ring";}
    if (name.equals("Ring of Might"))        {str = 2; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "ring";}
    if (name.equals("Ring of Speed"))        {str = 0; def = 0; spd = 2; mag = 0; lck = 0; desc = name; type = "ring";}
    if (name.equals("Ring of Wisdom"))       {str = 0; def = 0; spd = 0; mag = 2; lck = 0; desc = name; type = "ring";}
    if (name.equals("Stalwart Ring"))        {str = 0; def = 2; spd = 0; mag = 0; lck = 0; desc = name; type = "ring";}
    if (name.equals("Potion of Strength"))   {str = 3; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "potion";}
    if (name.equals("Potion of Stoneskin"))  {str = 0; def = 3; spd = 0; mag = 0; lck = 0; desc = name; type = "potion";}
    if (name.equals("Healing Potion (S)"))   {hp = 20; desc = name; type = "potion";}
    if (name.equals("Healing Potion (M)"))   {hp = 30; desc = name; type = "potion";}
    if (name.equals("Healing Potion (L)"))   {hp = 40; desc = name; type = "potion";}
  }
  
  public Item itemDrop(){ //drops an item after combat
    Random r = new Random(); //random # from 0 to 24 for now (total of 25 items)
    int rInt = r.nextInt(24);
    Item i = new Item(items[rInt]);
    return i;
  }
  
  public void getStats(){
    System.out.println("STR: "+getStr() + " | DEF: " + getDef() + " | WIS: " + getMag() + " | SPD: "+getSpd() + " | LCK: "+getLck());
  }
  
  public int getHp(){ return this.hp;}
  public int getStr(){ return this.str;}
  public int getDef(){ return this.def;}
  public int getMag(){ return this.mag;}
  public int getSpd(){ return this.spd;}
  public int getLck(){ return this.lck;}
  public String getType(){ return this.type;}
  public String getDesc(){ return this.desc;}
}