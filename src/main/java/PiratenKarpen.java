import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling the dice");
        Dice myDice = new Dice();
        int skulls = 0;
        int dice = 9;
        while (skulls<3){
            System.out.println(myDice.roll());
            if (myDice.roll() == Faces.SKULL){
                skulls++;
                dice--;
            }
            if (myDice.roll() == Faces.GOLD || myDice.roll() == Faces.DIAMOND)
        }
            System.out.println(myDice.roll());
        System.out.println("That's all folks!");
    }
    
}
