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

    public int getDef(){ return this.def; }
    public void takeDmg(int x){
        this.hp-=x;
    }

    public int getHp(){ return this.hp; }
    public String getName(){ return this.name; }


}