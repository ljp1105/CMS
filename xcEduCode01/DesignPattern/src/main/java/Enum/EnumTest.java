package Enum;

public enum  EnumTest{
    ONE("TOM1",1),
    TWO("TOM2",2),
    THREE("TOM3",3),
    FOUR("TOM4",2);
    private String tome;
    private int i;


    private EnumTest(String tome,int i){
        this.tome=tome;
        this.i=i;
    }
    public String getTome() {
        return tome;
    }

    public void setTome(String tome) {
        this.tome = tome;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}
