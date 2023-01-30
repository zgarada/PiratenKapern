package pk;
import java.util.Random;

public class Game {
    
    public static void game(Player p1, Player p2,boolean random, boolean combo) {
        Random start = new Random();
        int first = start.nextInt(2);
        //Random VS combo
        if (random && combo){
            while (!p1.result && !p2.result){
                if (first == 0){
                    p1.skulls = 0; p1.dice = 8;
                    Roll.turn(p1,false);

                    p2.skulls = 0; p2.dice = 8;
                    Roll.turn(p2,true);
                }
                else{
                    p2.skulls = 0; p2.dice = 8;
                    Roll.turn(p2,true);

                    p1.skulls = 0; p1.dice = 8;
                    Roll.turn(p1,false);
                }
            }
        }
        //Random vs Random
        else if (random && !combo){
            while (!p1.result && !p2.result){
                if (first == 0){

                    Roll.turn(p1,false);

                    Roll.turn(p2,false);
                }
                else{

                    Roll.turn(p2,false);

                    Roll.turn(p1,false);
                }
            }
        }
        //Combo vs Combo
        else if (!random && combo){
            while (!p1.result && !p2.result){
                if (first == 0){
                    Roll.turn(p1,true);
                    Roll.turn(p2,true);
                }
                else{
                    Roll.turn(p2,true);
                    Roll.turn(p1,true);
                }
            }
        }
        //In case of a tie
        while(p1.score == p2.score){
            Roll.turn(p1,combo);Roll.turn(p2,combo);
        }
        if (p1.score>p2.score){
            p1.num_wins++;
           // System.out.println("Player 1 wins!");
        }
        else{
            p2.num_wins++;
            //System.out.println("Player 2 wins!");
        }
    }
}
