package Organisms.Animals;
import GUI.MainContainer.MainContents.Commentator;
import Organisms.Organism;

public class Human extends Organisms.Animal{

    private boolean ability;
    private int cooldownReps, strengthDecreaseReps, arrowPressed;
    public Human(Organism[] boardBeginning, int N, int M, Commentator mainCommentator){
        super(5,4,1,(char)1, N, M, boardBeginning, mainCommentator);
        this.ability = false;
        this.cooldownReps = 0;
        this.strengthDecreaseReps = 0;
    }
    @Override
    public Organism getBaby(){
        return new Human(boardBeginning,N, M, mainCommentator);
    }

    @Override
    public boolean abilityActive(){
        return this.ability;
    }

    @Override
    public void activateAbility(){
        if (!ability && cooldownReps == 0) {
            this.ability = true;
            this.strength = 10;
            this.cooldownReps = 5;
            this.strengthDecreaseReps = 5;
        }
    }

    @Override
    public void setAbilityCooldown(int x){
        this.cooldownReps = x;
    }

    @Override
    public void setAbility(boolean x){
        this.ability = x;
    }

    public void shortenAbility(){
        if (this.strengthDecreaseReps != 0) {
            this.strength -= 1;
            this.strengthDecreaseReps -= 1;
        } else if (this.cooldownReps != 0) {
            this.cooldownReps -= 1;
        }
        if(cooldownReps == 0 && strengthDecreaseReps == 0){
            ability = false;
        }
    }

    @Override
    public int getStrengthDecreaseReps(){
        return this.strengthDecreaseReps;
    }

    @Override
    public void setArrowPressed(int x){
        this.arrowPressed = x;
    }

    @Override
    public int generateNextPosition(){
        switch (this.arrowPressed) {
            case 38 -> {
                return this.position - N;
            }
            case 39 -> {
                return this.position + 1;
            }
            case 40 -> {
                return this.position + N;
            }
            case 37 -> {
                return this.position - 1;
            }
            default -> {
                return this.position;
            }
        }
    }

    @Override
    public void makeMove(){
        if(arrowPressed > 0){
            this.nextPosition = this.generateNextPosition();
            if (this.getBoardBeginning()[nextPosition] == null) {
                this.cordChangeOnMove(this.determineMoveDirection(this.nextPosition - this.position));
                this.shortenAbility();
                this.boardBeginning[this.nextPosition] = this;
                this.boardBeginning[this.position] = null;
                this.position = this.nextPosition;
            } else {
                this.collision();
                this.shortenAbility();
            }
        }
    }

    @Override
    public boolean considerAction(){
        if (this.getYCord() == 1) {
            if (this.arrowPressed == 72) {
                return false;
            } else if (this.getXCord() == 1 && this.arrowPressed == 75) {
                return false;
            } else return this.getXCord() != N || this.arrowPressed != 77;
        } else if (this.getYCord() == M) {
            if (this.arrowPressed == 80) {
                return false;
            } else if (this.getXCord() == 1 && this.arrowPressed == 75) {
                return false;
            } else return this.getXCord() != N || this.arrowPressed != 77;
        } else {
            if (this.getXCord() == 1 && this.arrowPressed == 75) {
                return false;
            } else return this.getXCord() != N || this.arrowPressed != 77;
        }
    }
}
