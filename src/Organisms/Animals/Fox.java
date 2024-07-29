package Organisms.Animals;
import GUI.MainContainer.MainContents.Commentator;
import Organisms.Animal;
import Organisms.Organism;

public class Fox extends Animal {
    public Fox(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(3,7,1, 'F', N, M, boardBeginning, mainCommentator);
    }
    @Override
    public int generateNextPosition(){
        if (this.getYCord() == 1) {
            if (this.getXCord() == 1) {
                if (boardBeginning[position + N] == null || boardBeginning[position + N].getStrength() <= strength) {
                    return position + N;
                } else if (boardBeginning[position + 1] == null || boardBeginning[position + 1].getStrength() <= strength) {
                    return position + 1;
                } else {
                    return position;
                }
            } else if (this.getXCord() == N) {
                if (boardBeginning[position + N] == null || boardBeginning[position + N].getStrength() <= strength) {
                    return position + N;
                } else if (boardBeginning[position - 1] == null || boardBeginning[position - 1].getStrength() <= strength) {
                    return position - 1;
                } else {
                    return position;
                }
            } else {
                if (boardBeginning[position + N] == null || boardBeginning[position + N].getStrength() <= strength) {
                    return position + N;
                } else if (boardBeginning[position + 1] == null || boardBeginning[position + 1].getStrength() <= strength) {
                    return position + 1;
                } else if (boardBeginning[position - 1] == null || boardBeginning[position - 1].getStrength() <= strength) {
                    return position - 1;
                } else {
                    return position;
                }
            }
        } else if (this.getYCord() == M) {
            if (this.getXCord() == 1) {
                if (boardBeginning[position - N] == null || boardBeginning[position - N].getStrength() <= strength) {
                    return position - N;
                } else if (boardBeginning[position + 1] == null || boardBeginning[position + 1].getStrength() <= strength) {
                    return position + 1;
                } else {
                    return position;
                }
            } else if (this.getXCord() == N) {
                if (boardBeginning[position - N] == null || boardBeginning[position - N].getStrength() <= strength) {
                    return position - N;
                } else if (boardBeginning[position - 1] == null || boardBeginning[position - 1].getStrength() <= strength) {
                    return position - 1;
                } else {
                    return position;
                }
            } else {
                if (boardBeginning[position - N] == null || boardBeginning[position - N].getStrength() <= strength) {
                    return position - N;
                } else if (boardBeginning[position + 1] == null || boardBeginning[position + 1].getStrength() <= strength) {
                    return position + 1;
                }else if (boardBeginning[position - 1] == null || boardBeginning[position - 1].getStrength() <= strength) {
                    return position - 1;
                } else {
                    return position;
                }
            }
        } else {
            if (this.getXCord() == 1) {
                if (boardBeginning[position - N] == null || boardBeginning[position - N].getStrength() <= strength) {
                    return position - N;
                } else if (boardBeginning[position + N] == null || boardBeginning[position + N].getStrength() <= strength) {
                    return position + N;
                } else if (boardBeginning[position + 1] == null || boardBeginning[position + 1].getStrength() <= strength) {
                    return position + 1;
                } else {
                    return position;
                }
            } else if (this.getXCord() == N) {
                if (boardBeginning[position - N] == null || boardBeginning[position - N].getStrength() <= strength) {
                    return position - N;
                } else if (boardBeginning[position + N] == null || boardBeginning[position + N].getStrength() <= strength) {
                    return position + N;
                } else if (boardBeginning[position - 1] == null || boardBeginning[position - 1].getStrength() <= strength) {
                    return position - 1;
                } else {
                    return position;
                }
            } else {
                if (boardBeginning[position - N] == null || boardBeginning[position - N].getStrength() <= strength) {
                    return position - N;
                } else if (boardBeginning[position + N] == null || boardBeginning[position + N].getStrength() <= strength) {
                    return position + N;
                } else if (boardBeginning[position + 1] == null || boardBeginning[position + 1].getStrength() <= strength) {
                    return position + 1;
                } else if (boardBeginning[position - 1] == null || boardBeginning[position - 1].getStrength() <= strength) {
                    return position - 1;
                } else {
                    return position;
                }
            }
        }
    }
    @Override
    public Organism getBaby(){
        return new Fox(boardBeginning, N, M, mainCommentator);
    }
}
