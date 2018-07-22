import java.util.Scanner;

public class Player{
    //general

    //private String gameClass;
    private int lvl;
    private int maxExp, exp;  //total & current exp, hp, mana
    private int maxHp, hp; //each level up adds 10 hp (for now)
    private int maxMana, mana; //each point in magic increases mana by 5
    private int gold;
    //stats
    //each class begins with 13 stat points distributed,
    //each level up lets you add 3 where you want
    public Character player;
    /*private int str; //attack damage
    private int def; //mitigation
    private int spd; //evasion & turn order
    private int mag; //mana & magic damage(?)
    private int lck; //crit & magic find(?)
*/
    public Player(){
        this.lvl = 1;
        this.maxExp = 50;
        this.exp = gold = 0;
        Scanner sc2 = new Scanner(System.in);
        int choice;
        boolean loop = true;
        while (loop) {
            System.out.println("Choose your class:");
            System.out.println("1: Warrior");
            System.out.println("2: Rogue");
            System.out.println("3: Wizard");
            choice = sc2.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Welcome, Warrior.");
                    player = new Warrior();
                    player.createChar();
                    this.maxHp = hp = 50;
                    this.maxMana = mana = 15 + (player.getMag()*5);
                    loop = false;
                    break;
                case 2:
                    System.out.println("Welcome, Rogue.");
                    this.maxHp = hp = 40;
                    player = new Rogue();
                    player.createChar();
                    this.maxMana = mana = 15 + (player.getMag()*5);
                    loop = false;
                    break;
                case 3:
                    System.out.println("Welcome, Wizard.");
                    this.maxHp = hp = 35;
                    player = new Wizard();
                    player.createChar();
                    this.maxMana = mana = 15 + (player.getMag()*5);
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
        System.out.println("STR: " + player.getStr());
        System.out.println("DEF: " + player.getDef());
        System.out.println("SPD: " + player.getSpd());
        System.out.println("MAG: " + player.getMag());
        System.out.println("LCK: " + player.getLck());
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
                    player.setLck(player.getLck()+allocate);
                    break;
                case 2:
                    System.out.println("How many points do you want to allocate to Defense?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    player.setDef(player.getDef()+allocate);
                    break;
                case 3:
                    System.out.println("How many points do you want to allocate to Speed?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    player.setSpd(player.getSpd()+allocate);
                    break;
                case 4:
                    System.out.println("How many points do you want to allocate to Magic?");
                    allocate = sc3.nextInt();
                    if (points - allocate < 0) {
                        System.out.println("You can allocate only " + points + " point(s).");
                        break;
                    }
                    points -= allocate;
                    player.setMag(player.getMag()+allocate);
                    this.maxMana += player.getMag()*5;
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
                    player.setLck(player.getLck()+allocate);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
        this.lvl++;
        System.out.println("You are now Level " + lvl + "!");
    }

    public int getHp(){
        return this.hp;
    }
}