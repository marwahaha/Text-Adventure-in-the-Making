import java.util.ArrayList;

public class Inventory<Item>{
  private final int maxSize = 5;
  private int curSize;
  private ArrayList<Item> items; //equipped items
  private ArrayList<Consumable> cons; //usable in combat
  
  public Inventory(){
    this.curSize = 0;
    this.items = new ArrayList<Item>();
    this.cons = new ArrayList<Consumable>();
    
  }
  
}