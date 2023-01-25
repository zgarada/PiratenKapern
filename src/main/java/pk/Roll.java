package pk;
import java.util.Random;
public class Roll {
    public static boolean turn(Player p) {
        Dice myDice = new Dice();
        //Initial roll of all 8 dice
        for (int i = 0; i< 8 ; i++){
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

        Random reroll = new Random(); Random random_numDice = new Random();
        int count = 0;

            while (p.skulls<3){ //Each roll
                //Implement end turn at random
                int reroll_int = reroll.nextInt(2);
                //If reroll, roll
                if (reroll_int == 1){
                    int randNumDice = random_numDice.nextInt(2,p.dice+1);
                    for (int i = 0; i<randNumDice;i++){ //Rolling the X dice
                        if (myDice.roll() == Faces.SKULL){
                            p.dice--;
                            p.skulls++;
                        }
                        if (myDice.roll() == Faces.GOLD || myDice.roll() == Faces.DIAMOND){
                            count += 100;
                        }
                        if (p.score >= 6000){
                            p.result = true;
                            return p.result;
                        }
                    }
                }
                //If not reroll and end turn, add points to score
                else{
                    p.score += count;
                    if (p.score >= 6000){
                        p.result = true;
                        return p.result;
                    }
                    break;
                }

            }
        p.skulls = 0;
        p.dice = 8; 
        return p.result;
    }   
}
