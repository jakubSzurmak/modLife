package Organisms.Plants;
import GUI.MainContainer.MainContents.Commentator;
import Organisms.Organism;
import Organisms.Plant;

public class Guarana extends Plant {
    public Guarana(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(0,0,1, 'U', N, M, boardBeginning, mainCommentator);
    }
    @Override
    public char defendAgainst(Organism attacker){
        attacker.setStrength(attacker.getStrength()+3);
        return 'V';
    }

    @Override
    public Organism getBaby(){
        return new Guarana(boardBeginning, N, M, mainCommentator);
    }
}
