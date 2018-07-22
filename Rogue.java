public class Rogue implements Character{

    private int lvl, hp, spd, str, def, lck, mag;
    private String gameClass;

    public void createChar() {
        this.gameClass = "Rogue";
        this.str = 3;
        this.def = 2;
        this.spd = 5;
        this.lck = 2;
        this.mag = 1;
    }

    public int specialAtk() {
        return str+spd;
    }

    public int regAtk(){
        return str;
    }

    public void takeDmg(int x){
        hp -= x;
    }


    public int getStr() { return this.str; }

    public int getDef() { return this.def; }

    public int getMag(){ return this.mag; }

    public int getSpd() { return this.spd; }

    public int getLck(){ return this.lck; }

    public String getgameClass() { return this.gameClass; }

    public void setHp(int x) {this.hp = x; }

    public void setStr(int x) {this.str = x; }

    public void setDef(int x) {this.def = x; }

    public void setMag(int x) {this.mag = x; }

    public void setSpd(int x) {this.spd = x; }

    public void setLck(int x) {this.lck = x; }
}
