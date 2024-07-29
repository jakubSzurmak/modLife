package Organisms.Plants;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Organism;
import Organisms.Plant;

public class SowThistle extends Plant {
    public SowThistle(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(0,0,1, 'O', N, M, boardBeginning, mainCommentator);
    }

    @Override
    public void action(){
        if(!this.getLifeStatus()){
            for (short i = 0; i < 3; i++){
                this.recreate(this);
            }
        }
    }

    @Override
    public Organism getBaby() {
        return new SowThistle(boardBeginning, N, M, mainCommentator);
    }
}
