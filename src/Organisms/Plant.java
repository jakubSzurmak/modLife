package Organisms;

import GUI.MainContainer.MainContents.Commentator;

public abstract class Plant extends Organism {
    public Plant(int strength, int initiative, int movementSpeed, char species, int N, int M, Organism[] boardOfOrganisms, Commentator mainCommentator) {
        super(strength, initiative, movementSpeed, species, N, M, boardOfOrganisms, mainCommentator);
    }

    @Override
    public void action() {
        if (!this.killed) {
            if (considerAction()) {
                recreate(this);
            }
        }
    }
    @Override
    public void recreate(Organism partner){
        int newBornField = findFreeRecreationSpot(this);
        if (newBornField >= 0) {
            displayComment(getSpecies(), partner.getSpecies(), 'X');
            boardBeginning[newBornField] = getBaby();
            if (newBornField == position + N) {
                boardBeginning[newBornField].setXYCords(getXCord(), getYCord() + 1);
            } else if (newBornField == position - N) {
                boardBeginning[newBornField].setXYCords(getXCord(), getYCord() - 1);
            } else if (newBornField == position - 1) {
                boardBeginning[newBornField].setXYCords(getXCord() - 1, getYCord());
            } else if (newBornField == position + 1) {
                boardBeginning[newBornField].setXYCords(getXCord() + 1, getYCord());
            }

            boardBeginning[newBornField].setPosition(boardBeginning[newBornField].getXCord(),
                    boardBeginning[newBornField].getYCord());
        }
    }

    @Override
    public char defendAgainst(Organism attacker){
        if(this.getSpecies() == 'B' || this.getSpecies() == 'H'){
            return 'P';
        }else {
            return 'V';
        }
    }

    @Override
    public void fightWith(Organism defender){}
}
