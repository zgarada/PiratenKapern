package pk;
import java.util.Random;

public class Game {
    
    public static void game(Player p1, Player p2) {
        Random start = new Random();
        int first = start.nextInt(2);
        while (p1.result == false && p2.result == false){
            if (first == 0){
            Roll.turn(p1);
            if (Roll.turn(p1) == false){
                Roll.turn(p2);
            }
            }
            else{
                Roll.turn(p2);
                if (Roll.turn(p2) == false){
                    Roll.turn(p1);
                }
            }
        }
        if (p1.result == true && first == 0){
            Roll.turn(p2);
            if (p1.score > p2.score) {
                //System.out.println("Player 1 wins!");
                p1.num_wins++;
            }
            else if (p1.score == p2.score){
                Roll.turn(p1); Roll.turn(p2);
                if (p1.score > p2.score) {
                    //System.out.println("Player 1 wins!");
                    p1.num_wins++;
                }
                else {
                    //System.out.println("Player 2 wins!");
                    p2.num_wins++;
                }
            }
            else {
                //System.out.println("Player 2 wins!");
                p2.num_wins++;
            }
            //System.out.println(p1.score);
        }
        //if p2 starts
        if (p2.result == true && first == 1){
            Roll.turn(p1);
            if (p2.score>p1.score){
               // System.out.println("Player 2 wins!");
                p2.num_wins++;
            }
            else if (p1.score == p2.score){
                Roll.turn(p2);Roll.turn(p1);
                if (p1.score > p2.score) {
                  //  System.out.println("Player 1 wins!");
                    p1.num_wins++;
                }
                else {
                   // System.out.println("Player 2 wins!");
                    p2.num_wins++;
                }
            }
            else{
               // System.out.println("Player 1 wins!");
                p1.num_wins++;
            }


            //System.out.println(p2.score);
        }
    }
}
