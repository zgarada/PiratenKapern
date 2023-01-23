package pk;

public class Roll {
    public static boolean turn(Player p) {
        Dice myDice = new Dice();
            while (p.skulls<3){
                for (int i = 0; i<p.dice;i++){
                    //System.out.println(myDice.roll());
                    if (myDice.roll() == Faces.SKULL){
                        p.dice--;
                        p.skulls++;
                    }
                    if (myDice.roll() == Faces.GOLD || myDice.roll() == Faces.DIAMOND){
                        p.score += 100;
                    }
                    if (p.score >= 6000){
                        p.result = true;
                        return p.result;
                    }
                }
            }
        p.skulls = 0;
        p.dice = 8; 
        return p.result;
    }   
}
