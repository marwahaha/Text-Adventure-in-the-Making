import java.util.ArrayList;
import java.util.Random;

public class Item{
  
  private int dmg; //damage 
  private int arm; //armor
  private int str, def, spd, mag, lck; //stat bonuses
  private String desc; //item description
  private String type; //if item if weapon/helment/ring etc
  
  //list of items
  private String[] items = {
    "Rusty Sword", "Rusty Armor", "Rusty Helmet", "Rusty Boots", "Rusty Gauntlets", 
    "Rusty Dagger", "Leather Cowl", "Leather Armor", "Leather Boots", "Leather Gauntlets", 
    "Old Staff", "Dusty Hood", "Old Robe", "Old Slippers", "Old Armwraps",
    "Lucky Ring", "Ring of Might", "Ring of Speed", "Ring of Wisdom", "Stalwart Ring"
  };
  
  public Item(String name){
    if (name.equals("Rusty Sword"))          {dmg = 2; arm = 0; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "wep";}
    if (name.equals("Rusty Armor"))          {dmg = 0; arm = 2; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "body";}
    if (name.equals("Rusty Helmet"))         {dmg = 0; arm = 1; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "head";}
    if (name.equals("Rusty Boots"))          {dmg = 0; arm = 1; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "feet";}
    if (name.equals("Rusty Gauntlets"))      {dmg = 0; arm = 1; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "arm";}
    if (name.equals("Rusty Dagger"))         {dmg = 1; arm = 0; str = 0; def = 0; spd = 0; mag = 0; lck = 1; desc = name; type = "wep";}
    if (name.equals("Leather Cowl"))         {dmg = 0; arm = 1; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "head";}
    if (name.equals("Leather Armor"))        {dmg = 0; arm = 1; str = 0; def = 0; spd = 1; mag = 0; lck = 0; desc = name; type = "body";}
    if (name.equals("Leather Boots"))        {dmg = 0; arm = 1; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "feet";}
    if (name.equals("Leather Gauntlets"))    {dmg = 0; arm = 1; str = 0; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "arm";}
    if (name.equals("Old Staff"))            {dmg = 0; arm = 0; str = 0; def = 0; spd = 0; mag = 2; lck = 0; desc = name; type = "wep";}
    if (name.equals("Dusty Hood"))           {dmg = 0; arm = 0; str = 0; def = 0; spd = 0; mag = 1; lck = 0; desc = name; type = "head";}
    if (name.equals("Old Robe"))             {dmg = 0; arm = 0; str = 0; def = 1; spd = 0; mag = 1; lck = 0; desc = name; type = "body";}
    if (name.equals("Old Slippers"))         {dmg = 0; arm = 0; str = 0; def = 0; spd = 0; mag = 1; lck = 0; desc = name; type = "feet";}
    if (name.equals("Old Armwraps"))         {dmg = 0; arm = 0; str = 0; def = 1; spd = 0; mag = 1; lck = 0; desc = name; type = "arm";}
    if (name.equals("Lucky Ring"))           {dmg = 0; arm = 0; str = 0; def = 0; spd = 0; mag = 0; lck = 2; desc = name; type = "ring";}
    if (name.equals("Ring of Might"))        {dmg = 0; arm = 0; str = 2; def = 0; spd = 0; mag = 0; lck = 0; desc = name; type = "ring";}
    if (name.equals("Ring of Speed"))        {dmg = 0; arm = 0; str = 0; def = 0; spd = 2; mag = 0; lck = 0; desc = name; type = "ring";}
    if (name.equals("Ring od Wisdom"))       {dmg = 0; arm = 0; str = 0; def = 0; spd = 0; mag = 2; lck = 0; desc = name; type = "ring";}
    if (name.equals("Stalwart Ring"))        {dmg = 0; arm = 0; str = 0; def = 2; spd = 0; mag = 0; lck = 0; desc = name; type = "ring";}
  }
    
  public Item itemDrop(){ //drops an item after combat
    Random r = new Random(); //random # from 0 to 19 for now (total of 20 items)
    int rInt = r.nextInt(20); 
    Item i = new Item(items[rInt]);
    return i;
  }
  
  public int getStr(){ return this.str;}
  public int getDef(){ return this.def;}
  public int getMag(){ return this.mag;}
  public int getSpd(){ return this.spd;}
  public int getLck(){ return this.lck;}
  public int getDmg(){ return this.dmg;}
  public int getArm(){ return this.arm;}
  public String getType(){ return this.type;}
  public String getDesc(){ return this.desc;}
}