package Thread;

public enum Facteur {
    UNDEFINED,
    TRIPLE,
    DOUBLE,
    SIMPLE,
    ;

    private int facteurTours;
    static {
        TRIPLE.facteurTours=3;
        DOUBLE.facteurTours=2;
        SIMPLE.facteurTours=1;
    }

    public int getTemp(int J){
        double x =Math.random()*J+1;
        UNDEFINED.facteurTours= (int) x;
        return facteurTours;
    }
}
