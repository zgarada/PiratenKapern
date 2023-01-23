import pk.Game;
import pk.Player;


public class PiratenKarpen {

    public static void main(String[] args) {
        Player player1 = new Player(); Player player2 = new Player();
        
        for (int i = 0; i<42; i++){
            player1.skulls = 0; player1.dice = 8; player1.score = 0; player1.result = false;
            player2.skulls = 0; player2.dice = 8; player2.score = 0; player2.result = false;
            Game.game(player1,player2);
        }
        float perc_p1 = (player1.num_wins/42)*100; float perc_p2 = (player2.num_wins/42)*100;
        System.out.println("Player 1 percentage of wins: " + perc_p1 + "%"); System.out.println("Player 2 precentage of wins: " + perc_p2 + "%");
        
    }
    
}
