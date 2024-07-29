package Organisms.Plants;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Organism;
import Organisms.Plant;

public class Grass extends Plant {
    public Grass(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(0,0,1, 'G', N, M, boardBeginning, mainCommentator);
    }

    @Override
    public Organism getBaby(){
        return new Grass(boardBeginning, N, M, mainCommentator);
    }
}
