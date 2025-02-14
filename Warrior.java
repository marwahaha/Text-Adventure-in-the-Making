import java.io.Serializable;
import java.util.ArrayList;

public class Warrior implements Character, Serializable{
  
  private int spd, str, def, lck, mag;
  private String gameClass;
  private ArrayList<Magic> magic;
  
  public void createChar() {
    this.gameClass = "Warrior";
    this.str = 5;
    this.def = 4;
    this.spd = 3;
    this.lck = 1;
    this.mag = 1;
    this.magic = new ArrayList<Magic>();
  }
  
  public int regAtk(){
    return str;
  }
  
  public int getStr() { return this.str; }
  public int getDef() { return this.def; }
  public int getMag(){ return this.mag; }
  public int getSpd() { return this.spd; }
  public int getLck(){ return this.lck; }
  public String getGameClass() { return this.gameClass; }
  public void setStr(int x) {this.str = x; }
  public void setDef(int x) {this.def = x; }
  public void setMag(int x) {this.mag = x; }
  public void setSpd(int x) {this.spd = x; }
  public void setLck(int x) {this.lck = x; }
}
