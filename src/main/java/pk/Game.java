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
                    Roll.turn(p1,combo);
                    Roll.turn(p2,!combo);
                }
                else{
                    Roll.turn(p2,!combo);
                    Roll.turn(p1,combo);
                }
            }
        }
        //Random vs Random
        else if (random && !combo){
            while (!p1.result && !p2.result){
                if (first == 0){
                    Roll.turn(p1,!combo);
                    Roll.turn(p2,!combo);
                }
                else{
                    Roll.turn(p2,!combo);
                    Roll.turn(p1,!combo);
                }
            }
        }
        //Combo vs Combo
        else if (!random && combo){
            while (!p1.result && !p2.result){
                if (first == 0){
                    Roll.turn(p1,combo);
                    Roll.turn(p2,combo);
                }
                else{
                    Roll.turn(p2,combo);
                    Roll.turn(p1,combo);
                }
            }
        }



        while(p1.score == p2.score){
            Roll.turn(p1,combo);Roll.turn(p2,combo);
        }

        if (p1.score>p2.score){
            p1.num_wins++;
           // System.out.println("Player 1 wins!");
        }
        else if (p1.score<p2.score){
            p2.num_wins++;
            //System.out.println("Player 2 wins!");
        }
    }
}
