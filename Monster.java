public class Monster{
    //monsters won't use mana/magic stats?
    private String name;
    private boolean boss; //?? idk if this boolean is needed
    private int hp, str, def, spd, lck;
    
    private String[] monsters = { //add more later
      "Skeleton", "Slime"
    };
    
    private String[] bosses = { //add more later
      "Skeleton King"
    };
    
    public Monster(String name, int dungeonLvl){
        //skeleton & slime basic monsters
        if (name.equals("Skeleton")){
            this.name = name;
            this.hp = 20 + (5*dungeonLvl);
            this.str = 4 + (3*dungeonLvl);
            this.def = 2 + (3*dungeonLvl);
            this.spd = 1 + (3*dungeonLvl);
            this.lck = 1 + (3*dungeonLvl);
        }
        if (name.equals("Slime")){
            this.name = name;
            this.hp = 20 + (3*dungeonLvl);
            this.str = 2 + (3*dungeonLvl);
            this.def = 4 + (3*dungeonLvl);
            this.spd = 1 + (3*dungeonLvl);
            this.lck = 1 + (3*dungeonLvl);
        }
    }
    
    public Monster(String boss, int dungeonLvl, boolean isBoss){
      if (boss.equals("Skeleton King")) {
        this.boss = isBoss; //?? idk if this boolean is needed
        this.name = name;
        this.hp = 60 + (5*dungeonLvl);
        this.str = 6 + (3*dungeonLvl);
        this.def = 6 + (3*dungeonLvl);
        this.spd = 5 + (3*dungeonLvl);
        this.lck = 3 + (3*dungeonLvl);
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
      else return this.hp; 
    }
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