import java.util.Scanner;

public class Game{

    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        int menuChoice;
        while (quit != true) {
            System.out.println("Choose a menu option..");
            System.out.println("1. Start/Resume");
            System.out.println("2. Reset");
            System.out.println("3. Quit");
            menuChoice = sc.nextInt();
            switch(menuChoice){
                case 1:
                    System.out.println("Starting game..");
                    quit = inGame();
                    break;
                case 2:
                    System.out.println("Resetting progress..");
                    //erase any saved game... (how do you even save progress?)
                    break;
                case 3:
                    System.out.println("Exiting game..");
                    //quit
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        }
    }
    public static boolean inGame(){
        Player p = new Player();
        Scanner input = new Scanner(System.in);
        int result;
        System.out.println("Dungeon Started!");
        boolean inProgress = true;
        while (inProgress) {
            menu();
            result = input.nextInt();
            switch(result){
                case 1:
                    Combat combat = new Combat(p);
                    System.out.println("You gained 10xp");
                    p.checkExp();
                    break;
                case 2:
                    System.out.println("Opening Inventory");
                    break;
                case 3:
                    System.out.println("Quitting");
                    inProgress = false;
                    return true;
                default:
                    System.out.println("Invalid");
            }
        }
        return false;
    }

    public static void menu(){
        System.out.println("\n Adventure menu: \n");
        System.out.println("1. Proceed Forward");
        System.out.println("2. Inventory");
        System.out.println("3. Quit");
    }

}