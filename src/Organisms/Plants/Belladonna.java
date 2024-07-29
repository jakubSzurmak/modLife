package Organisms.Plants;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Organism;
import Organisms.Plant;

public class Belladonna extends Plant {
    public Belladonna(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(99,0,1, 'B', N, M, boardBeginning, mainCommentator);
    }

    @Override
    public Organism getBaby(){
        return new Belladonna(boardBeginning, N, M, mainCommentator);
    }
}
