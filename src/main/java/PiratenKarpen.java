import pk.Game;
import pk.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;


public class PiratenKarpen {
    private static final Logger logger = LogManager.getLogger(PiratenKarpen.class);

    public static void main(String[] args) {
        //Command line arguments

        boolean random = true; boolean combo = true;

       if (Objects.equals(args[0], "random") && Objects.equals(args[1], "combo")){
           random = true;
           combo = true;
       }
       else if (Objects.equals(args[0], "random") && Objects.equals(args[1], "random")){
           random = true;
           combo = false;
       }
       else if (Objects.equals(args[0], "combo") && Objects.equals(args[1], "combo")){
           random = false;
           combo = true;
       }

        Player player1 = new Player(); Player player2 = new Player(); //Initializing two Player objects

        for (int i = 0; i<250;i++){
            player1.skulls = 0; player1.dice = 8; player1.score = 0; player1.result = false;
            player2.skulls = 0; player2.dice = 8; player2.score = 0; player2.result = false;
            Game.game(player1,player2,random,combo); //Game between 2 players, with strategies

            logger.info("Player 1 score: " + player1.score); logger.info("Player 2 score: " + player2.score); //Logs the score of the 2 players after the game ends
        }
        float perc_p1 = (player1.num_wins/250)*100; float perc_p2 = (player2.num_wins/250)*100; //% of wins
        System.out.println("Player 1 percentage of wins: " + perc_p1 + "%"); System.out.println("Player 2 precentage of wins: " + perc_p2 + "%");


    }
    
}
