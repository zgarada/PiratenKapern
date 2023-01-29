package pk;
import java.util.Random;

public class Cards {
    public static String cardPick(){
        String[] cards = new String[35];
        cards[0] = "seaBattle2";  cards[1] = "seaBattle2";  cards[2] = "seaBattle3";  cards[3] = "seaBattle3";  cards[4] = "seaBattle4"; cards[5] = "seaBattle4";
        Random ranIndex = new Random();int cardIndex = ranIndex.nextInt(35);
        return cards[cardIndex];


    }


}
