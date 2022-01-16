package prca_cc2_jan2022;

public class Signal {
    boolean present = false;

    public synchronized void joue(){
        this.present=true;
        notify();
    }

    public synchronized void arret(){
        this.present=false;
        notify();
    }

    public synchronized void waitSig(){
        while(!present){
            try{
                wait();
            }catch (InterruptedException e){
                e.getStackTrace();
            }
        }
        this.present=false;
    }
}
