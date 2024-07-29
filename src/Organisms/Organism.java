package Organisms;

import GUI.MainContainer.MainContents.Commentator;

public abstract class Organism {
    protected int strength;
    protected int initiative;
    protected int position;
    protected int age;
    protected int xCord;
    protected int yCord;
    protected int movementSpeed;
    protected int nextPosition;
    protected final int N;
    protected final int M;
    protected final char species;
    boolean killed;

    public Commentator mainCommentator;

    protected final Organism[] boardBeginning;

    public Organism(int strength, int initiative, int movementSpeed,
                    char species, int N, int M, Organism[] boardOfOrganisms, Commentator mainCommentator) {
        this.strength = strength;
        this.initiative = initiative;
        this.species = species;
        this.movementSpeed = movementSpeed;
        this.killed = false;
        this.age = 0;
        this.position = -1;
        this.nextPosition = -1;
        this.N = N;
        this.M = M;
        this.xCord = -1;
        this.yCord = -1;
        this.boardBeginning = boardOfOrganisms;
        this.mainCommentator = mainCommentator;
    }

    public abstract void action();

    protected abstract void recreate(Organism partner);

    protected abstract void fightWith(Organism enemy);

    protected abstract char defendAgainst(Organism attacker);

    protected abstract Organism getBaby();

    public Organism[] getBoardBeginning() {
        return this.boardBeginning;
    }

    protected String displayOrganismName(char currentOrganism) {
        switch (currentOrganism) {
            case ('W') -> {
                return "Wolf ";
            }
            case ('T') -> {
                return "Turtle ";
            }
            case ('F') -> {
                return "Fox ";
            }
            case ('A') -> {
                return "Antelope ";
            }
            case ('S') -> {
                return "Sheep ";
            }
            case (1) -> {
                return "Human ";
            }
            case ('G') -> {
                return "Grass ";
            }
            case ('O') -> {
                return "SowThistle ";
            }
            case ('U') -> {
                return "Guarana ";
            }
            case ('B') -> {
                return "Belladonna ";
            }
            case ('H') -> {
                return "Sosnowsky's Hedgehog ";
            }
            default -> {
            }
        }
        return null;
    }

    protected void displayComment(char attacker, char defender, char statusFlag) {
        String currentComment = "";
        switch (statusFlag) {
            case 'K' -> {
                currentComment = currentComment.concat(displayOrganismName(attacker));
                currentComment = currentComment.concat("has killed ");
            }
            case 'D' -> {
                currentComment = currentComment.concat(displayOrganismName(attacker));
                currentComment = currentComment.concat("failed to kill ");
            }
            case 'R' -> {
                currentComment = currentComment.concat(displayOrganismName(attacker));
                currentComment = currentComment.concat("attack was reflected by ");
            }
            case 'P' -> {
                currentComment = currentComment.concat(displayOrganismName(attacker));
                if (defender == 'B' || defender == 'H') {
                    currentComment = currentComment.concat("was poisoned because it ate ");
                } else {
                    currentComment = currentComment.concat("ate ");
                }
            }
            case 'E' -> {
                currentComment = currentComment.concat(displayOrganismName(defender));
                currentComment = currentComment.concat("has escaped attack of ");
            }
            case 'X' -> {
                currentComment = currentComment.concat(displayOrganismName(attacker));
                if (isCharAnimal(attacker)) {
                    currentComment = currentComment.concat("has given birth to a new ");
                } else {
                    currentComment = currentComment.concat("has sowed a new ");
                }
            }
            default -> {
                return;
            }
        }
        currentComment = currentComment.concat(displayOrganismName(defender));
        currentComment = currentComment.concat("\n");
        mainCommentator.addComment(currentComment);
    }

    public void setXYCords(int x, int y) {
        this.xCord = x;
        this.yCord = y;
    }

    public void setStrength(int newStrength) {
        this.strength = newStrength;
    }

    public void incrementAge() {
        this.age += 1;
    }

    public void setPosition(int x, int y) {
        this.xCord = x;
        this.yCord = y;
        this.position = (this.N * (y - 1)) + (x - 1);
        this.boardBeginning[position] = this;
    }

    public void setInitiative(int x) {
        this.initiative = x;
    }

    public void setAge(int x) {
        this.age = x;
    }

    public void setAbility(boolean x) {
    }

    public void setArrowPressed(int x) {
    }

    public void setStrengthDecrease(int x) {
    }

    public void setAbilityCooldown(int x) {
    }

    public void activateAbility() {
    }

    public void setMovementSpeed(int x) {
        this.movementSpeed = x;
    }

    public int getXCord() {
        return this.xCord;
    }

    public int getYCord() {
        return this.yCord;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getInitiative() {
        return this.initiative;
    }

    public int getPosition() {
        return this.position;
    }

    public int getAge() {
        return this.age;
    }

    public char getSpecies() {
        return this.species;
    }

    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    public boolean considerAction() {
        return true;
    }

    public boolean abilityActive() {
        return false;
    }

    public int getCooldownReps() {
        return 0;
    }

    public int getStrengthDecreaseReps() {
        return 0;
    }

    public void setKilledOrganism() {
        this.killed = true;
    }

    public boolean getLifeStatus() {
        return this.killed;
    }

    public boolean isAnimal() {
        return switch (this.species) {
            case 'W', 'S', 'F', 'T', 'A', 1 -> true;
            default -> false;
        };
    }

    public boolean isCharAnimal(char speciesChar) {
        return switch (speciesChar) {
            case 'W', 'S', 'F', 'T', 'A', 'H' -> true;
            default -> false;
        };
    }

    public int findFreeRecreationSpot(Organism partner) {
        int xCord = partner.getXCord();
        int yCord = partner.getYCord();

        if (yCord == 1) {
            if (xCord == 1) {
                if (partner.boardBeginning[partner.position + N] == null) {
                    return partner.position + N;
                } else if (partner.boardBeginning[partner.position + 1] == null) {
                    return partner.position + 1;
                } else {
                    return -1;
                }
            } else if (xCord == N) {
                if (partner.boardBeginning[partner.position + N] == null) {
                    return partner.position + N;
                } else if (partner.boardBeginning[partner.position - 1] == null) {
                    return partner.position - 1;
                } else {
                    return -1;
                }
            } else {
                if (partner.boardBeginning[partner.position + N] == null) {
                    return partner.position + N;
                } else if (partner.boardBeginning[partner.position + 1] == null) {
                    return partner.position + 1;
                } else if (partner.boardBeginning[partner.position - 1] == null) {
                    return partner.position - 1;
                } else {
                    return -1;
                }
            }
        } else if (yCord == M) {
            if (xCord == 1) {
                if (partner.boardBeginning[partner.position - N] == null) {
                    return partner.position - N;
                } else if (partner.boardBeginning[partner.position + 1] == null) {
                    return partner.position + 1;
                } else {
                    return -1;
                }
            } else if (xCord == N) {
                if (partner.boardBeginning[partner.position - N] == null) {
                    return partner.position - N;
                } else if (partner.boardBeginning[partner.position - 1] == null) {
                    return partner.position - 1;
                } else {
                    return -1;
                }
            } else {
                if (partner.boardBeginning[partner.position - N] == null) {
                    return partner.position - N;
                } else if (partner.boardBeginning[partner.position + 1] == null) {
                    return partner.position + 1;
                } else if (partner.boardBeginning[partner.position - 1] == null) {
                    return partner.position - 1;
                } else {
                    return -1;
                }
            }
        } else {
            if (xCord == 1) {
                if (partner.boardBeginning[partner.position - N] == null) {
                    return partner.position - N;
                } else if (partner.boardBeginning[partner.position + N] == null) {
                    return partner.position + N;
                } else if (partner.boardBeginning[partner.position + 1] == null) {
                    return partner.position + 1;
                } else {
                    return -1;
                }
            } else if (xCord == N) {
                if (partner.boardBeginning[partner.position - N] == null) {
                    return partner.position - N;
                } else if (partner.boardBeginning[partner.position + N] == null) {
                    return partner.position + N;
                } else if (partner.boardBeginning[partner.position - 1] == null) {
                    return partner.position - 1;
                } else {
                    return -1;
                }
            } else {
                if (partner.boardBeginning[partner.position - N] == null) {
                    return partner.position - N;
                } else if (partner.boardBeginning[partner.position + N] == null) {
                    return partner.position + N;
                } else if (partner.boardBeginning[partner.position + 1] == null) {
                    return partner.position + 1;
                } else if (partner.boardBeginning[partner.position - 1] == null) {
                    return partner.position - 1;
                } else {
                    return -1;
                }
            }
        }
    }

    public int findFreeEscapeSpot() {
        if (this.getYCord() <= movementSpeed) {
            if (this.getXCord() <= movementSpeed) {
                if (boardBeginning[position + movementSpeed] == null) {
                    return 2;
                } else if (boardBeginning[position + (movementSpeed * N)] == null) {
                    return 3;
                }
            } else if (this.getXCord() > N - movementSpeed) {// > for <=
                if (boardBeginning[position - movementSpeed] == null) {
                    return 4;
                } else if (boardBeginning[position + (movementSpeed * N)] == null) {
                    return 3;
                }
            } else {
                if (boardBeginning[position + movementSpeed] == null) {
                    return 2;
                } else if (boardBeginning[position + (movementSpeed * N)] == null) {
                    return 3;
                } else if (boardBeginning[position - movementSpeed] == null) {
                    return 4;
                }
            }
        } else if (this.getYCord() >= M - movementSpeed) {
            if (this.getXCord() <= movementSpeed) {
                if (boardBeginning[position - (movementSpeed * N)] == null) {
                    return 1;
                } else if (boardBeginning[position + movementSpeed] == null) {
                    return 2;
                }
            } else if (this.getXCord() > N - movementSpeed) {// > for >=
                if (boardBeginning[position - (movementSpeed * N)] == null) {
                    return 1;
                } else if (boardBeginning[position - movementSpeed] == null) {
                    return 4;
                }
            } else {
                if (boardBeginning[position - (movementSpeed * N)] == null) {
                    return 1;
                } else if (boardBeginning[position + movementSpeed] == null) {
                    return 2;
                } else if (boardBeginning[position - movementSpeed] == null) {
                    return 4;
                }
            }
        } else {
            if (this.getXCord() <= movementSpeed) {
                if (boardBeginning[position - (movementSpeed * N)] == null) {
                    return 1;
                } else if (boardBeginning[position + movementSpeed] == null) {
                    return 2;
                } else if (boardBeginning[position + (movementSpeed * N)] == null) {
                    return 3;
                }
            } else if (this.getXCord() > N - movementSpeed) {// > for >=
                if (boardBeginning[position - (movementSpeed * N)] == null) {
                    return 1;
                } else if (boardBeginning[position - movementSpeed] == null) {
                    return 4;
                } else if (boardBeginning[position + (movementSpeed * N)] == null) {
                    return 3;
                }
            } else {
                if (boardBeginning[position - (movementSpeed * N)] == null) {
                    return 1;
                } else if (boardBeginning[position + movementSpeed] == null) {
                    return 2;
                } else if (boardBeginning[position + (movementSpeed * N)] == null) {
                    return 3;
                } else if (boardBeginning[position - movementSpeed] == null) {
                    return 4;
                }
            }
        }
        return -1;
    }

    public void collision() {
        if (this.species == (this.boardBeginning[this.nextPosition].getSpecies())) {
            this.recreate(this.boardBeginning[this.nextPosition]);
        } else {
            this.fightWith(this.boardBeginning[this.nextPosition]);
        }

    }
}

