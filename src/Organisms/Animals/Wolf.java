package Organisms.Animals;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Animal;
import Organisms.Organism;

public class Wolf extends Animal {
    public Wolf(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(9,5,1, 'W', N, M, boardBeginning, mainCommentator);
    }
    @Override
    public Organism getBaby(){
        return new Wolf(boardBeginning, N, M, mainCommentator);
    }
}
