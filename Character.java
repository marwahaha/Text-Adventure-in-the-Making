public interface Character {
  
  /*
   private int str; //attack damage
   private int def; //mitigation
   private int spd; //evasion & turn order
   private int mag; //mana & magic damage(?)
   private int lck; //crit & magic find(?)
   */
  
    void createChar();
    int getDef();
    int getStr();
    int getMag();
    int getSpd();
    int getLck();
    String getGameClass();
    void setStr(int x);
    void setDef(int x);
    void setMag(int x);
    void setSpd(int x);
    void setLck(int x);
    int regAtk();
}
