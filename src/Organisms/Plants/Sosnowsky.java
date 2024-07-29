package Organisms.Plants;

import GUI.MainContainer.MainContents.Commentator;
import Organisms.Organism;
import Organisms.Plant;

public class Sosnowsky extends Plant {

    public Sosnowsky(Organism[] boardBeginning, int N, int M, Commentator mainCommentator) {
        super(10, 0, 1, 'H', N, M, boardBeginning, mainCommentator);
    }

    public void seekSurroundingAnimals() {
        int xCord = this.getXCord();
        int yCord = this.getYCord();

        if (yCord == 1) {
            if (xCord == 1) {
                if (boardBeginning[position + 1] != null && boardBeginning[position + 1].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position + 1].getSpecies(), 'K');
                    boardBeginning[position + 1].setKilledOrganism();
                    boardBeginning[position + 1] = null;
                }
                if (boardBeginning[position + N] != null && boardBeginning[position + N].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position + N].getSpecies(), 'K');
                    boardBeginning[position + N].setKilledOrganism();
                    boardBeginning[position + N] = null;
                }
            } else if (xCord == N) {
                if (boardBeginning[position - 1] != null && boardBeginning[position - 1].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position - 1].getSpecies(), 'K');
                    boardBeginning[position - 1].setKilledOrganism();
                    boardBeginning[position - 1] = null;
                }
                if (boardBeginning[position + N] != null && boardBeginning[position + N].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position + N].getSpecies(), 'K');
                    boardBeginning[position + N].setKilledOrganism();
                    boardBeginning[position + N] = null;
                }
            } else {
                if (boardBeginning[position + 1] != null && boardBeginning[position + 1].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position + 1].getSpecies(), 'K');
                    boardBeginning[position + 1].setKilledOrganism();
                    boardBeginning[position + 1] = null;
                }
                if (boardBeginning[position - 1] != null && boardBeginning[position - 1].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position - 1].getSpecies(), 'K');
                    boardBeginning[position - 1].setKilledOrganism();
                    boardBeginning[position - 1] = null;
                }
                if (boardBeginning[position + N] != null && boardBeginning[position + N].isAnimal()) {
                    displayComment(getSpecies(), boardBeginning[position + N].getSpecies(), 'K');
                    boardBeginning[position + N].setKilledOrganism();
                    boardBeginning[position + N] = null;
                }
            }
        } else if (this.getYCord() == M) {
            if (this.getXCord() == 1) {
                if (this.boardBeginning[position - N] != null && this.boardBeginning[position - N].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position - N].getSpecies(), 'K');
                    this.boardBeginning[position - N].setKilledOrganism();
                    this.boardBeginning[position - N] = null;
                }
                if (this.boardBeginning[position + 1] != null && this.boardBeginning[position + 1].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position + 1].getSpecies(), 'K');
                    this.boardBeginning[position + 1].setKilledOrganism();
                    this.boardBeginning[position + 1] = null;
                }
            } else if (this.getXCord() == N) {
                if (this.boardBeginning[position - N] != null && this.boardBeginning[position - N].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position - N].getSpecies(), 'K');
                    this.boardBeginning[position - N].setKilledOrganism();
                    this.boardBeginning[position - N] = null;
                }
                if (this.boardBeginning[position - 1] != null && this.boardBeginning[position - 1].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position - 1].getSpecies(), 'K');
                    this.boardBeginning[position - 1].setKilledOrganism();
                    this.boardBeginning[position - 1] = null;
                }
            } else {
                if (this.boardBeginning[position + 1] != null && this.boardBeginning[position + 1].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position + 1].getSpecies(), 'K');
                    this.boardBeginning[position + 1].setKilledOrganism();
                    this.boardBeginning[position + 1] = null;
                }
                if (this.boardBeginning[position - 1] != null && this.boardBeginning[position - 1].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position - 1].getSpecies(), 'K');
                    this.boardBeginning[position - 1].setKilledOrganism();
                    this.boardBeginning[position - 1] = null;
                }
                if (this.boardBeginning[position - N] != null && this.boardBeginning[position - N].isAnimal()) {
                    displayComment(getSpecies(), this.boardBeginning[position - N].getSpecies(), 'K');
                    this.boardBeginning[position - N].setKilledOrganism();
                    this.boardBeginning[position - N] = null;
                }
            }
        } else {
            if (this.getXCord() == 1){
                if (this.boardBeginning[position - N] != null && this.boardBeginning[position - N].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position - N].getSpecies(), 'K');
                    this.boardBeginning[position - N].setKilledOrganism();
                    this.boardBeginning[position - N] = null;
                }
                if (this.boardBeginning[position + 1] != null && this.boardBeginning[position + 1].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position + 1].getSpecies(), 'K');
                    this.boardBeginning[position + 1].setKilledOrganism();
                    this.boardBeginning[position + 1] = null;
                }
                if (this.boardBeginning[position + N] != null && this.boardBeginning[position + N].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position + N].getSpecies(), 'K');
                    this.boardBeginning[position + N].setKilledOrganism();
                    this.boardBeginning[position + N] = null;
                }
            } else if (this.getXCord() == N){
                if (this.boardBeginning[position - N] != null && this.boardBeginning[position - N].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position - N].getSpecies(), 'K');
                    this.boardBeginning[position - N].setKilledOrganism();
                    this.boardBeginning[position - N] = null;
                }
                if (this.boardBeginning[position - 1] != null && this.boardBeginning[position - 1].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position - 1].getSpecies(), 'K');
                    this.boardBeginning[position - 1].setKilledOrganism();
                    this.boardBeginning[position - 1] = null;
                }
                if (this.boardBeginning[position + N] != null && this.boardBeginning[position + N].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position + N].getSpecies(), 'K');
                    this.boardBeginning[position + N].setKilledOrganism();
                    this.boardBeginning[position + N] = null;
                }
            } else{
                if (this.boardBeginning[position - N] != null && this.boardBeginning[position - N].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position - N].getSpecies(), 'K');
                    this.boardBeginning[position - N].setKilledOrganism();
                    this.boardBeginning[position - N] = null;
                }
                if (this.boardBeginning[position - 1] != null && this.boardBeginning[position - 1].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position - 1].getSpecies(), 'K');
                    this.boardBeginning[position - 1].setKilledOrganism();
                    this.boardBeginning[position - 1] = null;
                }
                if (this.boardBeginning[position + 1] != null && this.boardBeginning[position + 1].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position + 1].getSpecies(), 'K');
                    this.boardBeginning[position + 1].setKilledOrganism();
                    this.boardBeginning[position + 1] = null;
                }
                if (this.boardBeginning[position + N] != null && this.boardBeginning[position + N].isAnimal()){
                    this.displayComment(this.getSpecies(), this.boardBeginning[position + N].getSpecies(), 'K');
                    this.boardBeginning[position + N].setKilledOrganism();
                    this.boardBeginning[position + N] = null;
                }
            }
        }
    }

    @Override
    public Organism getBaby(){
        return new Sosnowsky(boardBeginning, N, M, mainCommentator);
    }
    @Override
    public void fightWith(Organism enemy){
        enemy.setKilledOrganism();
    }

    @Override
    public void action(){
        if(this.considerAction()){
            this.seekSurroundingAnimals();
            this.recreate(this);
        }
    }
}
