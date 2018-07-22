public interface Character {


    void createChar();
    int getDef();
    int getStr();
    int getMag();
    int getSpd();
    int getLck();
    String getgameClass();
    void setStr(int x);
    void setDef(int x);
    void setMag(int x);
    void setSpd(int x);
    void setLck(int x);
    int specialAtk();
    int regAtk();
    void takeDmg(int x);

}
