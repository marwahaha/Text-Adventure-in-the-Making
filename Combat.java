;
import java.lang.Character;
import java.util.Scanner;

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
            int damage = player.player.specialAtk() - monster.getDef();
            monster.takeDmg(damage);
            System.out.println("You did "+damage+" damage to enemy!");
        }
        else if(result == 2) {
            int damage = player.player.regAtk() - monster.getDef();
            monster.takeDmg(damage);
            System.out.println("You did "+damage+" damage to enemy!");
        }

        if(monster.getHp()<=0) {
            System.out.println("Enemy Defeated!");
            return true;
        }
        else {
            System.out.println("Enemy has " + monster.getHp() + " health left");
            System.out.println("Enemy Attacking!");
            int damage = monster.attack() - player.player.getDef();
            if(damage<0){
                damage = 0;
            }
            player.player.takeDmg(damage);
            System.out.println("You took "+damage+" damage");
            System.out.println("Health remaining: "+player.getHp());
            return false;
        }

    }

}
