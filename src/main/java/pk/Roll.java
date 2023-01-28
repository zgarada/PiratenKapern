package pk;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class Roll {
    public static boolean turn(Player p) {
        Dice myDice = new Dice();
        //List < Faces > dice_stored = new ArrayList<>();
        int iterator = 0;
        Random reroll = new Random(); Random random_numDice = new Random();
        int count = 0;
       // int NumDiceReroll_init = random_numDice.nextInt(2,p.dice+1);
      //  int numDiceKeep_init = p.dice - NumDiceReroll_init;
        //Initial roll of all 8 dice
        for (int i = 0; i< 8 ; i++){
            Faces roll = myDice.roll();
            if (roll == Faces.SKULL){
                p.dice--;
                p.skulls++;
            }
            else if (roll == Faces.GOLD || roll == Faces.DIAMOND){
                p.score += 100;
                p.player_array.add(iterator, roll);
                iterator++;
            }
            else{
                p.player_array.add(iterator, roll);
                iterator++;
            }

            if (p.score >= 6000){
                p.result = true;
                return p.result;
            }
        }




            while (p.skulls<3){ //Each roll
                int iter = 0;
                //Implement end turn at random
                int reroll_int = reroll.nextInt(2);
                //If reroll, roll
                if (reroll_int == 1){
                    int NumDiceReroll = random_numDice.nextInt(2,p.dice+1);
                    int numDiceKeep = p.dice - NumDiceReroll;
                    for (int i = 0; i<NumDiceReroll;i++){ //Rolling the X dice
                        Faces roll = myDice.roll();
                        if (roll == Faces.SKULL){
                            p.dice--;
                            p.skulls++;
                        }
                        else if (roll == Faces.GOLD || roll == Faces.DIAMOND){
                            count += 100;
                            p.player_array.remove(iter + numDiceKeep);
                            p.player_array.add(iter + numDiceKeep, roll);
                            iter++;
                        }
                        else{
                            p.player_array.remove(iter + numDiceKeep);
                            p.player_array.add(iter + numDiceKeep, roll);
                            iter++;
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

       // System.out.println("List " + dice_stored);
        p.skulls = 0;
        p.dice = 8;
        p.player_array.clear();
        return p.result;
    }   
}
