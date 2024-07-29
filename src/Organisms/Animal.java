package Organisms;

import GUI.MainContainer.MainContents.Commentator;

public abstract class Animal extends Organism{
    public Animal(int strength, int initiative, int movementSpeed, char species, int N, int M, Organism[] boardOfOrganisms, Commentator mainCommentator) {
        super(strength, initiative, movementSpeed, species, N, M, boardOfOrganisms, mainCommentator);
    }

    protected int determineMoveDirection(int positionChange) {

        if(positionChange == this.movementSpeed){
            return 2;
        }else if(positionChange == -(this.movementSpeed)){
            return 4;
        }else if(positionChange == N*this.movementSpeed){
            return 3;
        }else if(positionChange == -N*this.movementSpeed){
            return 1;
        }
        return -1;
    }

    protected int generateMoveDirection() {
        int moveDirection;
        if (this.getYCord() <= movementSpeed) {
            if (this.getXCord() <= movementSpeed) {
                moveDirection = (int) (Math.random() * 2);
                if (moveDirection % 2 == 0) {
                    return 2;
                } else {
                    return 3;
                }
            } else if (this.getXCord() > N - movementSpeed) {
                moveDirection = (int) (Math.random() * 2);
                if (moveDirection % 2 == 0) {
                    return 4;
                } else {
                    return 3;
                }
            } else {
                moveDirection = (int) (Math.random() * 3);
                if (moveDirection == 0) {
                    return 2;
                } else if (moveDirection == 1) {
                    return 3;
                } else {
                    return 4;
                }
            }
        } else if (this.getYCord() > M - movementSpeed) {
            if (this.getXCord() <= movementSpeed) {
                moveDirection = (int) (Math.random() * 2);
                if (moveDirection % 2 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            } else if (this.getXCord() > N - movementSpeed) {
                moveDirection = (int) (Math.random() * 2);
                if (moveDirection % 2 == 0) {
                    return 4;
                } else {
                    return 1;
                }
            } else {
                moveDirection = (int) (Math.random() * 3);
                if (moveDirection == 0) {
                    return 1;
                } else if (moveDirection == 1) {
                    return 2;
                } else {
                    return 4;
                }
            }
        } else {
            if (this.getXCord() < movementSpeed) {
                moveDirection = (int) (Math.random() * 3);
                if (moveDirection == 0) {
                    return 1;
                } else if (moveDirection == 1) {
                    return 2;
                } else {
                    return 3;
                }
            } else if (this.getXCord() > N - movementSpeed) {
                moveDirection = (int) (Math.random() * 3);
                if (moveDirection == 0) {
                    return 1;
                } else if (moveDirection == 1) {
                    return 4;
                } else {
                    return 3;
                }
            } else {
                moveDirection = (int) (Math.random() * 4);
                if (moveDirection == 0) {
                    return 1;
                } else if (moveDirection == 1) {
                    return 2;
                } else if (moveDirection == 2) {
                    return 3;
                } else {
                    return 4;
                }
            }
        }
    }
    protected int generateNextPosition() {
        switch (generateMoveDirection()) {
            case 1 -> {
                return position - (movementSpeed * N);
            }
            case 2 -> {
                return position + movementSpeed;
            }
            case 3 -> {
                return position + (movementSpeed * N);
            }
            case 4 -> {
                return position - movementSpeed;
            }
            default -> {
                return position;
            }
        }
    }

    protected void cordChangeOnMove(int nextPos) {
        switch (nextPos) {
            case 1 -> yCord -= movementSpeed;
            case 2 -> xCord += movementSpeed;
            case 3 -> yCord += movementSpeed;
            case 4 -> xCord -= movementSpeed;
            default -> {
            }
        }
    }
    protected void makeMove() {
        this.nextPosition = this.generateNextPosition();
        if (this.getBoardBeginning()[nextPosition] == null) {
            this.cordChangeOnMove(this.determineMoveDirection(this.nextPosition - this.position));
            this.boardBeginning[this.nextPosition] = this;
            this.boardBeginning[this.position] = null;
            this.position = this.nextPosition;
        } else if(this.position != nextPosition) {
            this.collision();
        }
    }
    @Override
    protected char defendAgainst(Organism attacker) {
        if (attacker.getStrength() < strength) {
            return 'D';
        } else {
            return 'V';
        }
    }

    @Override
    protected void fightWith(Organism enemy) {
        char status = ' ';
        if (enemy != null) {
            status = enemy.defendAgainst(this);
        } else {
            status = 'E';
        }

        if (status == 'V') {
            displayComment(getSpecies(), enemy.getSpecies(), 'K');
            enemy.setKilledOrganism();
            boardBeginning[position] = null;
            setPosition(enemy.getXCord(), enemy.getYCord());

        } else if (status == 'D') {
            displayComment(getSpecies(), enemy.getSpecies(), 'D');
            setKilledOrganism();
            getBoardBeginning()[position] = null;

        } else if (status == 'P') {
            displayComment(getSpecies(), enemy.getSpecies(), 'P');
            enemy.setKilledOrganism();
            enemy.getBoardBeginning()[enemy.getPosition()] = null;
            setKilledOrganism();
            boardBeginning[position] = null;

        } else if (status == 'R') {
            displayComment(getSpecies(), enemy.getSpecies(), 'R');
            nextPosition = position;

        } else if (status == 'E') {
            assert enemy != null;
            displayComment(getSpecies(), enemy.getSpecies(), 'E');
            boardBeginning[nextPosition] = this;
            boardBeginning[position] = null;
            position = nextPosition;
        }
    }
    @Override
    protected void recreate(Organism partner) {
        int newBornField = findFreeRecreationSpot(partner);
        if (newBornField != -1) {
            boardBeginning[newBornField] = getBaby();

            if (newBornField == partner.position + N) {
                boardBeginning[newBornField].setXYCords(partner.getXCord(), partner.getYCord() + 1);
                boardBeginning[newBornField].setPosition(partner.getXCord(), partner.getYCord() + 1);
            } else if (newBornField == partner.position - N) {
                boardBeginning[newBornField].setXYCords(partner.getXCord(), partner.getYCord() - 1);
                boardBeginning[newBornField].setPosition(partner.getXCord(), partner.getYCord() - 1);
            } else if (newBornField == partner.position - 1) {
                boardBeginning[newBornField].setXYCords(partner.getXCord() - 1, partner.getYCord());
                boardBeginning[newBornField].setPosition(partner.getXCord() - 1, partner.getYCord());
            } else if (newBornField == partner.position + 1) {
                boardBeginning[newBornField].setXYCords(partner.getXCord() + 1, partner.getYCord());
                boardBeginning[newBornField].setPosition(partner.getXCord() + 1, partner.getYCord());
            }
            displayComment(getSpecies(), partner.getSpecies(), 'X');
        }
    }
    @Override
    public void action(){
        if(this.considerAction()){
            this.makeMove();
        }
    }

}
