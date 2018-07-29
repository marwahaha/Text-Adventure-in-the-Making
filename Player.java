import java.util.Scanner;
import java.util.ArrayList;

public class Player{

    //private String gameClass;
    private int lvl;
    private int maxExp, exp;  //total & current exp, hp, mana
    private int maxHp, hp; //each level up adds 10 hp (for now)
    private int maxMana, mana; //each point in magic increases mana by 5
    private int gold;
    //each class begins with 13 stat points distributed,each level up lets you add 3 where you want
    public Character p1; //pONE

    //items that are equipped
    private Item head, body, feet, arms, ring1, wep;

    //items that are stored as consumables
    private Inventory inv;


    public Player(){
        this.inv = new Inventory(this);
        this.head = body = feet = arms = ring1 = wep = null;
        this.lvl = 1;
        this.maxExp = 50;
        this.exp = gold = 0;
        Scanner sc2 = new Scanner(System.in);
        int choice;
        boolean loop = true;
        while (loop) {
            System.out.println("\nChoose your class:");
            System.out.println("1: Warrior");
            System.out.println("2: Rogue");
            System.out.println("3: Wizard");
            choice = sc2.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Welcome, Warrior.");
                    p1 = new Warrior();
                    p1.createChar();
                    Item i = new Item("Rusty Sword");
                    equip(i);
                    this.maxHp = hp = 50;
                    this.maxMana = mana = 15 + (p1.getMag()*5);
                    loop = false;
                    break;
                case 2:
                    System.out.println("Welcome, Rogue.");
                    this.maxHp = hp = 40;
                    p1 = new Rogue();
                    p1.createChar();
                    i = new Item("Rusty Dagger");
                    equip(i);
                    this.maxMana = mana = 15 + (p1.getMag()*5);
                    loop = false;
                    break;
                case 3:
                    System.out.println("Welcome, Wizard.");
                    this.maxHp = hp = 35;
                    p1 = new Wizard();
                    p1.createChar();
                    i = new Item("Old Staff");
                    equip(i);
                    this.maxMana = mana = 15 + (p1.getMag()*5);
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void showStats(){
        System.out.println("HP: " + hp + "/" + maxHp +
                " | MP: " + mana + "/" + maxMana +
                " | EXP: " + exp + "/" + maxExp +
                " | Gold: " + gold);
        System.out.println("STR: " + p1.getStr());
        System.out.println("DEF: " + p1.getDef());
        System.out.println("SPD: " + p1.getSpd());
        System.out.println("MAG: " + p1.getMag());
        System.out.println("LCK: " + p1.getLck());
    }

    public boolean checkExp(){ //to check if character is able to level up
        if (exp >= maxExp) {
            this.exp = exp - maxExp;
            this.levelUp();
            return true;
        }
        else
            return false;
    }

    public void levelUp(){
        System.out.println("LEVEL UP!");
        this.maxHp += 10;
        this.hp = maxHp;
        this.mana = maxMana;
        this.maxExp += 10;
        this.exp = 0;

        Scanner sc3 = new Scanner(System.in);
        int points = 3;
        int choice;
        int allocate;
        while (points > 0) {
            System.out.println("You have " + points + " stat points to allocate!");
            System.out.println("Choose stat to allocate: ");
            System.out.println("1: Strength");
            System.out.println("2: Defense");
            System.out.println("3: Speed");
            System.out.println("4: Magic");
            System.out.println("5: Luck");
            choice = sc3.nextInt();
            switch(choice){
                case 1:
                    System.out.println("How many points do you want to allocate to Strength?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    p1.setStr(p1.getStr()+allocate);
                    break;
                case 2:
                    System.out.println("How many points do you want to allocate to Defense?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    p1.setDef(p1.getDef()+allocate);
                    break;
                case 3:
                    System.out.println("How many points do you want to allocate to Speed?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    p1.setSpd(p1.getSpd()+allocate);
                    break;
                case 4:
                    System.out.println("How many points do you want to allocate to Magic?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    p1.setMag(p1.getMag()+allocate);
                    this.maxMana += p1.getMag()*5;
                    this.mana = maxMana;
                    break;
                case 5:
                    System.out.println("How many points do you want to allocate to Luck?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    p1.setLck(p1.getLck()+allocate);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
        this.lvl++;
        System.out.println("You are now Level " + lvl + "!");
    }

    public void equip(Item i){
        if (i.getType().equals("head")) this.head = i;
        if (i.getType().equals("body")) this.body = i;
        if (i.getType().equals("feet")) this.feet = i;
        if (i.getType().equals("arms")) this.arms = i;
        if (i.getType().equals("ring")) this.ring1 = i;
        if (i.getType().equals("wep")) this.wep = i;
        p1.setStr(p1.getStr() + i.getStr());
        p1.setDef(p1.getDef() + i.getDef());
        p1.setSpd(p1.getSpd() + i.getSpd());
        p1.setMag(p1.getMag() + i.getMag());
        p1.setLck(p1.getLck() + i.getLck());
    }

    public void unequip(String slot){
        Item i = getItem(slot);
        p1.setStr(p1.getStr() - i.getStr());
        p1.setDef(p1.getDef() - i.getDef());
        p1.setSpd(p1.getSpd() - i.getSpd());
        p1.setMag(p1.getMag() - i.getMag());
        p1.setLck(p1.getLck() - i.getLck());
        if (slot.equals("head")) this.head = null;
        if (slot.equals("body")) this.body = null;
        if (slot.equals("feet")) this.feet = null;
        if (slot.equals("arms")) this.arms = null;
        if (slot.equals("ring1")) this.ring1 = null;
        if (slot.equals("wep")) this.wep = null;
    }

    public Item getItem(String slot){
        if (slot.equals("head")) return this.head;
        if (slot.equals("body")) return this.body;
        if (slot.equals("feet")) return this.feet;
        if (slot.equals("arms")) return this.arms;
        if (slot.equals("ring1")) return this.ring1;
        if (slot.equals("wep")) return this.wep;
        else return null;
    }

    public void compareItem(Item item) {
        String itemType = item.getType();
        if (itemType.equals("ring")) {
            itemType = "ring1";
        }
        Item equippedItem = getItem(itemType);
        System.out.println("Equipped:");
        if(equippedItem!=null){ equippedItem.getStats();}
        else{ System.out.println("------------\nEmpty\n------------"); }
        System.out.println("Dropped:");
        item.getStats();
    }


    public Inventory getInv(){ return this.inv;}
    public int getHp(){
        if (hp <= 0){ return 0;}
        else return hp;
    }
    public void setHp(int x){ this.hp = x;}
    public int getMaxHp(){ return this.maxHp;}
    public void setMaxHp(int x){ this.maxHp = x;}
    public int getMana(){ return this.mana;}
    public void setMana(int x){ this.mana = x;}
    public int getMaxMana(){ return this.maxMana;}
    public void setMaxMana(int x){ this.maxMana = x;}
    public int getExp(){ return this.exp;}
    public void setExp(int x){ this.exp = x;}
    public int getGold(){ return this.gold;}
    public void setGold(int x){ this.gold = x;}

    public void takeDmg(int x){hp -= x;}

    public void showEquipped(){ //shows equipped items
        if (getItem("head") != null){ System.out.println("Head: " + head.getDesc());}
        else System.out.println("Head: Empty");
        if (getItem("body") != null){ System.out.println("Body: " + body.getDesc());}
        else System.out.println("Body: Empty");
        if (getItem("arms") != null){ System.out.println("Arms: " + arms.getDesc());}
        else System.out.println("Arms: Empty");
        if (getItem("feet") != null){ System.out.println("Feet: " + feet.getDesc());}
        else System.out.println("Feet: Empty");
        if (getItem("ring1") != null){ System.out.println("Ring: " + ring1.getDesc());}
        else System.out.println("Ring: Empty");
        if (getItem("wep") != null){ System.out.println("Weapon: " + wep.getDesc());}
        else System.out.println("Weapon: Empty");
    }
}