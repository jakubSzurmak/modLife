package Organisms.Animals;
import GUI.MainContainer.MainContents.Commentator;
import Organisms.Animal;
import Organisms.Organism;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(4,4,2, 'A', N, M, boardBeginning, mainCommentator);
    }

    @Override
    public char defendAgainst(Organism attacker){
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            int nextInd = findFreeEscapeSpot();
            if(nextInd == -1){
                if (attacker.getStrength() < strength) {
                    return 'D';
                } else {
                    return 'V';
                }
            }
            cordChangeOnMove(nextInd);
            boardBeginning[position] = null;
            this.setPosition(xCord, yCord);
            return 'E';
        } else {
            if (attacker.getStrength() < strength) {
                return 'D';
            } else {
                return 'V';
            }
        }
    }

    @Override
    public Organism getBaby(){
        return new Antelope(boardBeginning, N, M, mainCommentator);
    }
}
