package pk;

public class Game {
    
    public static void game(Player p1, Player p2) {
        while (p1.result == false && p2.result == false){
            Roll.turn(p1);
            Roll.turn(p2);
        }
        if (p1.result == true){
            System.out.println("Player 1 wins!");
        }
        if (p2.result == true){
            System.out.println("Player 2 wins!");
        }
        if (p1.skulls >= 8){
            p2.result = true;
            System.out.println("Player 2 wins!");
        }
        if (p2.skulls >= 8){
            System.out.println("Player 1 wins!");
            p1.result = true;
        }
    }
    
}
