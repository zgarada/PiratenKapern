package pk;

public class Roll {
    public static boolean turn(Player p) {
        Dice myDice = new Dice();
        int skulls_turn = 0;
        while (skulls_turn <3){
            for (int i = 0; i<p.dice;i++){
                System.out.println(myDice.roll());
                if (myDice.roll() == Faces.SKULL){ 
                    p.skulls++;
                    p.dice--;
                    skulls_turn++;
                }
                if (myDice.roll() == Faces.GOLD || myDice.roll() == Faces.DIAMOND){
                    p.score += 100;
                }
            }
    
            if (p.score >= 6000){
                p.result = true;
                return p.result;
            }
        }
        return p.result;
        
        
    }
    
}
