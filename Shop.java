import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Shop{

    private int size;
    private String[] items; //shop items to sell
    private int itemsIndex;
    private int[] prices; //shop item prices
    private int[] yourPrices; //your item prices
    private int priceIndex;
    private int yourIndex;

    public Shop(Player p){
        this.items = new String[5]; //5 items to sell max
        this.itemsIndex = 0;
        this.size = 5;
        this.prices = new int[5];
        this.priceIndex = 0;
        this.yourPrices = new int[5];
        this.yourIndex = 0;
        stockItems();
        buyMenu(p);
    }

    public void stockItems(){ //stocks store with items and prices
        ArrayList<String> shopItems = new ArrayList<String>(); //stores names to stop duplicate items in shop
        int index = 0;
        int price = 0;
        Random r = new Random();
        Item it = new Item("Rusty Sword"); //make initial item
        for (int i = 0; i < 5; i++){
            it = it.itemDrop(); //randomize item
            boolean newItem = false;
            while (newItem == false){
                if (shopItems.contains(it.getDesc())){ it = it.itemDrop();} //new item if same one is in shop already
                else newItem = true;
            }
            shopItems.add(it.getDesc());
            items[itemsIndex++] = it.getDesc(); //add to items
            if (it.getType().equals("potion")){
                price = r.nextInt(15) + 5;
            } //cheap potions
            else if (it.getType().equals("ring")){ price = r.nextInt(15) + 25;} //expensive rings
            else {price = r.nextInt(15) + 15;} //normal items
            prices[priceIndex++] = price; //set price of item
        }
    }

    public void buy(String item, int index, Player p) {
        Item i = new Item(item);
        if (p.getInv().getSize() >= 5) {
            System.out.println("Your bag is too full!");
        } else if ((p.getGold() - prices[index]) < 0) {
            System.out.println("You do not have enough gold to buy " + i.getDesc() + ".\n");
        } else {
            System.out.println("You want to buy " + i.getDesc() + " for " + prices[index] + " gold?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            Scanner s = new Scanner(System.in);
            int choice = s.nextInt();
            if (choice == 1) {
                p.setGold(p.getGold() - prices[index]); //subtract gold
                if (p.getGold() >= prices[index]) {
                    if (!i.getType().equals("potion")) { //if not a potion
                        if (p.getItem(i.getType()) != null) {
                            p.unequip(i.getType());
                        }
                        System.out.println("You equipped " + i.getDesc() + ".");
                        p.equip(i);
                    } else { //if a potion
                        p.getInv().addItem(i); //add item
                    }
                    //remove item from shop
                    for (int x = 0; x < size; x++) {
                        if (i.getDesc().equals(items[x])) {
                            items[x] = null;
                        }
                    }
                    prices[index] = 0;
                    size--;
                }
            } else {
                System.out.println("You chose to not buy the " + i.getDesc() + ".\n");
            }
        }
    }

    public void sell(Item i, Player p, int price){
        System.out.println("You want to sell your " + i.getDesc() + " for " + price + " gold?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        if (choice == 1) {
            p.getInv().removeItem(i);
            p.setGold(p.getGold() + price);
        }
    }

    public void buyMenu(Player p){ //prints buy menu
        boolean loop = true;
        int choice;
        Scanner s = new Scanner(System.in);
        while (loop){
            buyPrint(p); //show buy menu
            choice = s.nextInt();
            switch(choice){
                case 1:
                    buy(items[0], 0, p);
                    break;
                case 2:
                    buy(items[1], 1, p);
                    break;
                case 3:
                    buy(items[2], 2, p);
                    break;
                case 4:
                    buy(items[3], 3, p);
                    break;
                case 5:
                    buy(items[4], 4, p);
                    break;
                case 6:
                    System.out.println("You leave the shop.");
                    loop = false;
                    break;
                case 7:
                    sellMenu(p);
                    loop = false;
                    break;
                case 8:
                    invMenu(p);
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void buyPrint(Player p){
        int i;
        System.out.println("Gold: " + p.getGold());
        System.out.println("Buy:");
        for (i = 0; i < 5; i++){
            if (items[i] == null) { System.out.println((i+1) + ". Sold!");}
            else { System.out.println((i+1) + ". Price: " + prices[i] + " | " + items[i]);}
        }
        System.out.println("6. Leave Shop");
        System.out.println("7. Sell");
        System.out.println("8. Inventory");
    }

    public void sellMenu(Player p){ //prints sell menu. sale prices are randomized
        boolean loop = true;
        int choice;
        Scanner s = new Scanner(System.in);
        while (loop){
            sellPrint(p);
            choice = s.nextInt();
            switch(choice){
                case 1:
                    if (p.getInv().getSize() >= 1) {
                        sell(p.getInv().getItem(0), p, yourPrices[0]);
                    }
                    break;
                case 2:
                    if (p.getInv().getSize() >= 2) {
                        sell(p.getInv().getItem(1), p, yourPrices[1]);
                    }
                    break;
                case 3:
                    if (p.getInv().getSize() >= 3) {
                        sell(p.getInv().getItem(2), p, yourPrices[2]);
                    }
                    break;
                case 4:
                    if (p.getInv().getSize() >= 4) {
                        sell(p.getInv().getItem(3), p, yourPrices[3]);
                    }
                    break;
                case 5:
                    if (p.getInv().getSize() >= 5) {
                        sell(p.getInv().getItem(4), p, yourPrices[4]);
                    }
                    break;
                case 6:
                    System.out.println("You leave the shop.");
                    loop = false;
                    break;
                case 7:
                    buyMenu(p);
                    loop = false;
                    break;
                case 8:
                    invMenu(p);
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void sellPrint(Player p){
        int i;
        int salePrice;
        Random r = new Random();
        System.out.println("Gold: " + p.getGold());
        System.out.println("Sell:");
        for (i = 0; i < p.getInv().getSize(); i++){
            salePrice = r.nextInt(10) + 15; //random-ish sale price for each item the player has
            yourPrices[yourIndex++] = salePrice;
            System.out.println((i+1) + ". Price: " + salePrice + " | " + p.getInv().getItem(i).getDesc());
        }
        while (i < 5){
            System.out.println((i+1) + ". Empty.");
            i++;
        }
        System.out.println("6. Leave Shop");
        System.out.println("7. Buy");
        System.out.println("8. Inventory");
    }

    public static void invMenu(Player player){
        System.out.println("\n1. Equipment\n2. Potions\n3. Back");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        boolean loop = true;
        while (loop)
            switch(choice){
                case 1:
                    System.out.println("Equipment:");
                    player.showEquipped();
                    loop = false;
                    break;
                case 2:
                    player.getInv().showInventory();
                    loop = false;
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
    }
}