import java.lang.Character;
import java.util.Scanner;
import java.util.Random;

public class Combat {

    public Combat(Player player){
        Scanner sc = new Scanner(System.in);
        System.out.println("You've been attacked!");
        Monster monster = new Monster("Skeleton");
        System.out.println("Initializing combat....");

        int result;
        boolean inCombat = true;
        while(inCombat!=false){
            System.out.println("1. Attack");
            System.out.println("2. Inventory");
            System.out.println("3. Flee");
            result = sc.nextInt();
            switch(result) {
                case 1:
                    if (attack(player, monster)) {
                        inCombat = false;
                    }
                    break;
                case 2:
                    System.out.println("Opening Inventory");
                    break;
                case 3:
                    System.out.println("You run away!");
                    inCombat = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public boolean attack(Player player, Monster monster){
        Scanner sc2 = new Scanner(System.in);
        int result;
        System.out.println("Choose your attack:");
        System.out.println("1. Special Attack");
        System.out.println("2. Normal Attack");
        result = sc2.nextInt();
        if(result == 1) {
            int damage = player.p1.specialAtk() - monster.getDef();
            monster.takeDmg(damage);
            System.out.println("You did " + damage + " damage to enemy!");
        }
        else if(result == 2) {
            int damage = player.p1.regAtk() - monster.getDef();
            monster.takeDmg(damage);
            System.out.println("You did " + damage + " damage to enemy!");
        }

        if(monster.getHp()<=0) {
            System.out.println("Enemy Defeated! You gained 10 Exp!");
            player.setExp(player.getExp() + 10);
            player.checkExp();
            Random r = new Random();
            int randInt = r.nextInt(10); //random # 0 - 9
            if (randInt <= 2) { //30% chance to drop item (for now)
              Item i = new Item("Rusty Sword"); //making random item first
              i = i.itemDrop(); //item becomes randomized
              System.out.println("The " + monster.getName() + " dropped an item!");
              System.out.println("Do you wish to equip " + i.getDesc() + "?");
              Scanner sc = new Scanner(System.in);
              System.out.println("1: Yes \n2: No");
              int c = sc.nextInt();
              if (c == 1){ //if player already has item in corresponding slot, unequipt it then equip new item
                if (player.getItem(i.getDesc()) != null) {
                  player.unequip(i.getDesc());
                }
                player.equip(i);
              }
              if (c == 2){ System.out.println("You leave the " + i.getDesc() + " behind.");};
            }
            return true;
        }
        else {
            System.out.println("Enemy has " + monster.getHp() + " health left");
            System.out.println("Enemy Attacking!");
            int damage = monster.attack() - player.p1.getDef();
            if(damage < 0){ damage = 0;}
            player.takeDmg(damage);
            System.out.println("You took " + damage + " damage");
            System.out.println("Health remaining: "+ player.getHp());
            return false;
        }
    }
}
