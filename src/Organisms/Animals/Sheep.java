package Organisms.Animals;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Animal;
import Organisms.Organism;

public class Sheep extends Animal {
    public Sheep(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(4,4,1, 'S', N, M, boardBeginning, mainCommentator);
    }

    @Override
    public Organism getBaby(){
        return new Sheep(boardBeginning, N, M, mainCommentator);
    }

}
