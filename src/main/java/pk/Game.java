package pk;
import java.util.Random;

public class Game {
    
    public static void game(Player p1, Player p2) {
        Random start = new Random();
        int first = start.nextInt(2);
        while (p1.result == false && p2.result == false){
            if (first == 0){
                Roll.turn(p1);
                Roll.turn(p2);
            }
            else{
                Roll.turn(p2);
                Roll.turn(p1);
            }
        }


        while(p1.score == p2.score){
            Roll.turn(p1);Roll.turn(p2);
        }

        if (p1.score>p2.score){
            p1.num_wins++;
            //System.out.println("Player 1 score: " + p1.score);System.out.println("Player 2 score: " + p2.score);
            //System.out.println("Player 1 wins!");
        }
        else if (p1.score<p2.score){
            p2.num_wins++;
           // System.out.println("Player 1 score: " + p1.score);System.out.println("Player 2 score: " + p2.score);
           // System.out.println("Player 2 wins!");
        }



    }
}
