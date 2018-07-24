public class Monster{
    //monsters won't use mana/magic stats?
    private String name;
    private int hp, str, def, spd, lck;

    public Monster(String name){
        //skeleton & slime basic monsters
        if (name.equals("Skeleton")){
            this.name = name;
            this.hp = 20;
            this.str = 4;
            this.def = 2;
            this.spd = 1;
            this.lck = 1;
        }
        if (name.equals("Slime")){
            this.name = name;
            this.hp = 15;
            this.str = 3;
            this.def = 3;
            this.spd = 1;
            this.lck = 1;
        }
    }

    public int attack(){
        return str+spd;
    }

    public void takeDmg(int x){
        this.hp-=x;
    }

    public int getHp(){ 
      if (hp < 0) return 0;
      else return this.hp; }
    public void setHp(int x){ this.hp = x;}
    public String getName(){ return this.name; }
    public int getStr() { return this.str; }
    public int getDef() { return this.def; }
    public int getSpd() { return this.spd; }
    public int getLck(){ return this.lck; }
    public void setStr(int x) {this.str = x; }
    public void setDef(int x) {this.def = x; }
    public void setSpd(int x) {this.spd = x; }
    public void setLck(int x) {this.lck = x; }

}