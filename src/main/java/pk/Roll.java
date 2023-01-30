package pk;
import java.util.*;

import static pk.Faces.MONKEY;

public class Roll {
    public static void turn(Player p, boolean combo) {

        Dice myDice = new Dice();
        String card = Cards.cardPick(); //Pick up a card from card class

        int saberTarget = 0;
        if (Objects.equals(card, "seaBattle2")) {
            saberTarget = 2;
        } else if (Objects.equals(card, "seaBattle3")) {
            saberTarget = 3;
        } else if (Objects.equals(card, "seaBattle4")) {
            saberTarget = 4;
        }

        int numSabers = 0;
        boolean monkeyBusiness = Objects.equals(card, "monkeyBusiness");

        //If seaBattle card is drawn
        if (saberTarget == 2 || saberTarget == 3 || saberTarget == 4) {
            //If random
            int iterator = 0;
            Random reroll = new Random();
            Random random_numDice = new Random();
            int count = 0;
            if (!combo) {
                for (int i = 0; i < 8; i++) {
                    Faces roll = myDice.roll();
                    if (roll == Faces.SKULL) {
                        p.dice--;
                        p.skulls++;
                    } else if (roll == Faces.SABER) {
                        numSabers++;
                        p.player_array.add(iterator, roll);
                        iterator++;

                    } else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                        p.score += 100;
                        p.player_array.add(iterator, roll);
                        iterator++;
                    } else {
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }

                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }

                while (p.skulls<3) { //Each roll
                    //Implement end turn at random
                    int iter = 0;
                    int reroll_int = reroll.nextInt(2);
                    numSabers = 0;
                    //If reroll is true (1)
                    if (reroll_int == 1) {
                        int NumDiceReroll = random_numDice.nextInt(2, p.dice + 1);
                        int numDiceKeep = p.dice - NumDiceReroll;
                        for (int i = 0; i < NumDiceReroll; i++) { //Rolling the X dice
                            Faces roll = myDice.roll();

                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            } else if (roll == Faces.SABER) {
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                numSabers++;
                                iter++;
                            } else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                iter++;
                            } else {
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                iter++;
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }
                    }
                    //If reroll false (0), end turn, add points to score
                    else {
                        break;
                    }
                }
                if (numSabers < saberTarget) {
                    if (saberTarget == 2) {
                        p.score -= 300;
                        return;
                    } else if (saberTarget == 3) {
                        p.score -= 500;
                        return;
                    } else if (saberTarget == 4) {
                        p.score -= 1000;
                        return;
                    }

                } else {
                    p.score += count;
                    if (saberTarget == 2) {
                        p.score += 300;
                    } else if (saberTarget == 3) {
                        p.score += 500;
                    } else if (saberTarget == 4) {
                        p.score += 1000;
                    }


                }
                Collections.sort(p.player_array);
                //Combo scoring
                int[] numCombo = new int[]{1, 1, 1, 1, 1};
                int index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }
                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] == 3) {
                        p.score += 100;
                    } else if (numCombo[i] == 4) {
                        p.score += 200;
                    } else if (numCombo[i] == 5) {
                        p.score += 500;
                    } else if (numCombo[i] == 6) {
                        p.score += 1000;
                    } else if (numCombo[i] == 7) {
                        p.score += 2000;
                    } else if (numCombo[i] == 8) {
                        p.score += 4000;
                    }
                }

                if (p.score >= 6000) {
                    p.result = true;
                    return;
                }
                else{
                    p.skulls = 0;
                    p.dice = 8;
                    p.player_array.clear();
                    return;
                }
            }


            //If combo
            if (combo) {
                int[] numCombo = {1, 1, 1, 1, 1};
                Faces whichFacekeep;
                for (int i = 0; i < 8; i++) {
                    Faces roll = myDice.roll();
                    if (roll == Faces.SKULL) {
                        p.dice--;
                        p.skulls++;
                    } else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                        p.score += 100;
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }
                    else if (roll == Faces.SABER){
                        p.player_array.add(iterator, roll);
                        iterator++;
                        numSabers++;
                    }
                    else {
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }

                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }


                //ONLY KEEP ROLLING IF SKULLS LESS THAN 2, IF 2 THEN END TURN
                while (numSabers < saberTarget && p.skulls<3) { //Each roll
                    //KEEP HIGHEST COMBO SCORE, OTHERS RE-ROLL
                    for (int i = 0; i < p.dice; i++) { //Rolling the X dice
                        Faces roll = myDice.roll();
                        if (p.player_array.get(i) != Faces.SABER) {
                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            }
                            else if (roll == Faces.SABER) {
                                numSabers++;
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            }
                            else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            } else {
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }

                    }
                }
                Collections.sort(p.player_array);
                int highestCombo = 0;
                int index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }
                int highestIndex = 0;
                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] >= 3 && numCombo[i] > highestCombo) {
                        highestCombo = numCombo[i];
                        highestIndex = i;
                    }
                }
                whichFacekeep = p.player_array.get(highestIndex);
                while (p.skulls<2) { //Each roll
                    //KEEP HIGHEST COMBO SCORE, OTHERS RE-ROLL
                    for (int i = 0; i < p.dice; i++) { //Rolling the X dice
                        Faces roll = myDice.roll();
                        if (roll != whichFacekeep) {
                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            }
                            else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            } else {
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }

                    }
                }
                Collections.sort(p.player_array);
                index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }

                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] == 3) {
                        p.score += 100;
                    } else if (numCombo[i] == 4) {
                        p.score += 200;
                    } else if (numCombo[i] == 5) {
                        p.score += 500;
                    } else if (numCombo[i] == 6) {
                        p.score += 1000;
                    } else if (numCombo[i] == 7) {
                        p.score += 2000;
                    } else if (numCombo[i] == 8) {
                        p.score += 4000;
                    }
                }
                if (p.skulls < 3) {
                    p.score += count;
                    if (saberTarget == 2) {
                        p.score += 300;
                    } else if (saberTarget == 3) {
                        p.score += 500;
                    } else if (saberTarget == 4) {
                        p.score += 1000;
                    }
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                else {
                    if (saberTarget == 2) {
                        p.score -= 300;
                        return;
                    } else if (saberTarget == 3) {
                        p.score -= 500;
                        return;
                    } else if (saberTarget == 4) {
                        p.score -= 1000;
                        return;
                    }
                }
                p.skulls = 0;
                p.dice = 8;
            }
        }
        //IF CARD WAS NOT SEA BATTLE CARD and NOT MONKET BUSINESS CARD
        else if (!monkeyBusiness){
            //If random
            int iterator = 0;
            Random reroll = new Random();
            Random random_numDice = new Random();
            int count = 0;
            if (!combo) {
                for (int i = 0; i < 8; i++) {
                    Faces roll = myDice.roll();
                    if (roll == Faces.SKULL) {
                        p.dice--;
                        p.skulls++;
                    }
                    else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                        p.score += 100;
                        p.player_array.add(iterator, roll);
                        iterator++;
                    } else {
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }

                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }

                while (p.skulls < 3) { //Each roll
                    //Implement end turn at random
                    int iter = 0;
                    int reroll_int = reroll.nextInt(2);
                    //If reroll is true (1)
                    if (reroll_int == 1) {
                        int NumDiceReroll = random_numDice.nextInt(2, p.dice + 1);
                        int numDiceKeep = p.dice - NumDiceReroll;
                        for (int i = 0; i < NumDiceReroll; i++) { //Rolling the X dice
                            Faces roll = myDice.roll();
                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            }
                            else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                iter++;
                            } else {
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                iter++;
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }
                    }

                    //If reroll false (0), end turn, add points to score
                    else {
                        break;
                    }

                }

                Collections.sort(p.player_array);
                //Combo scoring
                int[] numCombo = new int[]{1, 1, 1, 1, 1};
                int index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }

                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] == 3) {
                        p.score += 100;
                    } else if (numCombo[i] == 4) {
                        p.score += 200;
                    } else if (numCombo[i] == 5) {
                        p.score += 500;
                    } else if (numCombo[i] == 6) {
                        p.score += 1000;
                    } else if (numCombo[i] == 7) {
                        p.score += 2000;
                    } else if (numCombo[i] == 8) {
                        p.score += 4000;
                    }
                }

                if (p.skulls<3){
                    p.score += count;
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                p.skulls = 0;
                p.dice = 8;
                p.player_array.clear();
            }
            //If combo
            if (combo) {
                int[] numCombo = {1, 1, 1, 1, 1};
                for (int i = 0; i < 8; i++) {
                    Faces roll = myDice.roll();
                    if (roll == Faces.SKULL) {
                        p.dice--;
                        p.skulls++;
                    } else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                        p.score += 100;
                        p.player_array.add(iterator, roll);
                        iterator++;
                    } else {
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }

                Collections.sort(p.player_array);
                int highestCombo = 0;
                int index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }
                int highestIndex = 0;
                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] >= 3 && numCombo[i] > highestCombo) {
                        highestCombo = numCombo[i];
                        highestIndex = i;
                    }
                }
                Faces whichFacekeep;
                whichFacekeep = p.player_array.get(highestIndex);
                //ONLY KEEP ROLLING IF SKULLS LESS THAN 2, IF 2 THEN END TURN
                while (p.skulls<2) { //Each roll
                    //KEEP HIGHEST COMBO SCORE, OTHERS RE-ROLL
                    for (int i = 0; i < p.dice; i++) { //Rolling the X dice
                        Faces roll = myDice.roll();
                        if (roll != whichFacekeep) {
                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            }
                            else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            } else {
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }

                    }
                }
                Collections.sort(p.player_array);
                index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }

                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] == 3) {
                        p.score += 100;
                    } else if (numCombo[i] == 4) {
                        p.score += 200;
                    } else if (numCombo[i] == 5) {
                        p.score += 500;
                    } else if (numCombo[i] == 6) {
                        p.score += 1000;
                    } else if (numCombo[i] == 7) {
                        p.score += 2000;
                    } else if (numCombo[i] == 8) {
                        p.score += 4000;
                    }
                }

                if (p.skulls < 3) {
                    p.score += count;
                    p.player_array.clear();
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                p.skulls = 0;
                p.dice = 8;
                p.player_array.clear();
            }
        }

        //If Monkey Business card
        if (monkeyBusiness){
            //If random
            int iterator = 0;
            Random reroll = new Random();
            Random random_numDice = new Random();
            int count = 0;
            if (!combo) {
                for (int i = 0; i < 8; i++) {
                    Faces roll = myDice.roll();
                    if (Objects.equals(roll, Faces.PARROT)){
                        roll = MONKEY;
                    }
                    if (roll == Faces.SKULL) {
                        p.dice--;
                        p.skulls++;
                    }
                    else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                        p.score += 100;
                        p.player_array.add(iterator, roll);
                        iterator++;
                    } else {
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }

                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                while (p.skulls < 3) { //Each roll
                    //Implement end turn at random
                    int iter = 0;
                    int reroll_int = reroll.nextInt(2);
                    //If reroll is true (1)
                    if (reroll_int == 1) {
                        int NumDiceReroll = random_numDice.nextInt(2, p.dice + 1);
                        int numDiceKeep = p.dice - NumDiceReroll;
                        for (int i = 0; i < NumDiceReroll; i++) { //Rolling the X dice
                            Faces roll = myDice.roll();
                            if (Objects.equals(roll, Faces.PARROT)){
                                roll = MONKEY;
                            }
                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            }
                            else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                iter++;
                            } else {
                                p.player_array.remove(iter + numDiceKeep);
                                p.player_array.add(iter + numDiceKeep, roll);
                                iter++;
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }
                    }
                    //If reroll false (0), end turn, add points to score
                    else {
                        break;
                    }

                }
                Collections.sort(p.player_array);
                //Combo scoring
                int[] numCombo = new int[]{1, 1, 1, 1, 1};
                int index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }
                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] == 3) {
                        p.score += 100;
                    } else if (numCombo[i] == 4) {
                        p.score += 200;
                    } else if (numCombo[i] == 5) {
                        p.score += 500;
                    } else if (numCombo[i] == 6) {
                        p.score += 1000;
                    } else if (numCombo[i] == 7) {
                        p.score += 2000;
                    } else if (numCombo[i] == 8) {
                        p.score += 4000;
                    }
                }

                if (p.skulls<3){
                    p.score += count;
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                p.skulls = 0;
                p.dice = 8;
                p.player_array.clear();
            }
            //If combo
            if (combo) {
                int[] numCombo = {1, 1, 1, 1, 1};
                for (int i = 0; i < 8; i++) {
                    Faces roll = myDice.roll();
                    if (Objects.equals(roll, Faces.PARROT)){
                        roll = MONKEY;
                    }
                    if (roll == Faces.SKULL) {
                        p.dice--;
                        p.skulls++;
                    } else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                        p.score += 100;
                        p.player_array.add(iterator, roll);
                        iterator++;
                    } else {
                        p.player_array.add(iterator, roll);
                        iterator++;
                    }
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                Collections.sort(p.player_array);
                int highestCombo = 0;
                int index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }
                int highestIndex = 0;
                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] >= 3 && numCombo[i] > highestCombo) {
                        highestCombo = numCombo[i];
                        highestIndex = i;
                    }
                }
                Faces whichFacekeep;
                whichFacekeep = p.player_array.get(highestIndex);
                //ONLY KEEP ROLLING IF SKULLS LESS THAN 2, IF 2 THEN END TURN
                while (p.skulls<2) { //Each roll
                    //KEEP HIGHEST COMBO SCORE, OTHERS RE-ROLL
                    for (int i = 0; i < p.dice; i++) { //Rolling the X dice
                        Faces roll = myDice.roll();
                        if (Objects.equals(roll, Faces.PARROT)){
                            roll = MONKEY;
                        }
                        if (roll != whichFacekeep) {
                            if (roll == Faces.SKULL) {
                                p.dice--;
                                p.skulls++;
                            }
                            else if (roll == Faces.GOLD || roll == Faces.DIAMOND) {
                                count += 100;
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            } else {
                                p.player_array.remove(i);
                                p.player_array.add(i, roll);
                            }
                            if (p.score >= 6000) {
                                p.result = true;
                                return;
                            }
                        }

                    }
                }

                Collections.sort(p.player_array);
                index = 0;
                for (int i = 0; i < p.player_array.size() - 1; i++) {
                    if (p.player_array.get(i) == p.player_array.get(i + 1)) {
                        numCombo[index]++;
                    } else {
                        index++;
                    }
                }
                for (int i = 0; i < numCombo.length; i++) {
                    if (numCombo[i] == 3) {
                        p.score += 100;
                    } else if (numCombo[i] == 4) {
                        p.score += 200;
                    } else if (numCombo[i] == 5) {
                        p.score += 500;
                    } else if (numCombo[i] == 6) {
                        p.score += 1000;
                    } else if (numCombo[i] == 7) {
                        p.score += 2000;
                    } else if (numCombo[i] == 8) {
                        p.score += 4000;
                    }
                }
                if (p.skulls < 3) {
                    p.score += count;
                    p.player_array.clear();
                    if (p.score >= 6000) {
                        p.result = true;
                        return;
                    }
                }
                p.skulls = 0;
                p.dice = 8;
                p.player_array.clear();
            }
        }





    }
}
