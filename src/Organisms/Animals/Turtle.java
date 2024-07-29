package Organisms.Animals;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Animal;
import Organisms.Organism;

public class Turtle extends Animal {

    public Turtle(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(2,1,1, 'T', N, M, boardBeginning, mainCommentator);
    }
    @Override
    public boolean considerAction(){
        return (int) (Math.random() * 100) + 1 > 75;
    }

    @Override
    public char defendAgainst(Organism attacker){
        if (attacker.getStrength() < 5) {
            return 'R';
        } else {
            return 'V';
        }
    }

    @Override
    public Organism getBaby(){
        return new Turtle(boardBeginning, N, M, mainCommentator);
    }
}
