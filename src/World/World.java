package World;

import Organisms.Animals.*;
import Organisms.Plants.*;
import Organisms.Organism;
import GUI.MainContainer.MainContents.Commentator;
import java.util.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.DataOutputStream;


public class World {

    private int N, M;

    public Commentator mainCommentator;
    private int globalRoundCounter = 0;
    private int humanX, humanY;
    private Organism[] boardOfOrganisms;

    public World(int N, int M, Commentator mainCommentator) {
        this.N = N;
        this.M = M;
        this.boardOfOrganisms = new Organism[N * M];
        this.mainCommentator = mainCommentator;
    }

    public int getRoundCounter() {
        return globalRoundCounter;
    }

    public boolean isHumanAlive() {
        return boardOfOrganisms[getHumanIndex()] != null && boardOfOrganisms[getHumanIndex()].getSpecies() == 1;
    }

    public void updateCommentator(){
        for(int i = 0 ; i < N*M; i++){
            if(boardOfOrganisms[i] != null){
                boardOfOrganisms[i].mainCommentator = this.mainCommentator;
            }
        }
    }

    public void setHumanX(int humanX) {
        this.humanX = humanX;
    }

    public void setHumanY(int humanY) {
        this.humanY = humanY;
    }

    public int getHumanIndex() {
        return (N * (humanY - 1)) + (humanX - 1);
    }

    public Coordinate generateRandomCoordinate(int N, int M) {
        int xG, yG, index;
        Random random = new Random();
        Coordinate coord;

        while (true) {
            xG = random.nextInt(N);
            yG = random.nextInt(M);
            coord = new Coordinate(xG, yG);
            index = N * yG + xG;
            if (this.boardOfOrganisms[index] == null) {
                return coord;
            }
        }
    }

    private int calculateOrgsOnSpawn() {
        int fields = N * M;
        int x = (N * M) - 11;
        int forEachN = 1;
        while (x > fields / 3) {
            x -= 10;
            forEachN += 1;
        }
        return forEachN;
    }

    public Organism spawnAnimal(int interval) {
        switch (interval) {
            case 0 -> {
                return new Wolf(boardOfOrganisms, N, M, mainCommentator);
            }
            case 1 -> {
                return new Sheep(boardOfOrganisms, N, M, mainCommentator);
            }
            case 2 -> {
                return new Fox(boardOfOrganisms, N, M, mainCommentator);
            }
            case 3 -> {
                return new Turtle(boardOfOrganisms, N, M, mainCommentator);
            }
            case 4 -> {
                return new Antelope(boardOfOrganisms, N, M, mainCommentator);
            }
            default -> {
            }
        }
        return null;
    }

    public Organism spawnPlant(int interval) {
        switch (interval) {
            case 5 -> {
                return new Grass(boardOfOrganisms, N, M, mainCommentator);
            }
            case 6 -> {
                return new SowThistle(boardOfOrganisms, N, M, mainCommentator);
            }
            case 7 -> {
                return new Guarana(boardOfOrganisms, N, M, mainCommentator);
            }
            case 8 -> {
                return new Belladonna(boardOfOrganisms, N, M, mainCommentator);
            }
            case 9 -> {
                return new Sosnowsky(boardOfOrganisms, N, M, mainCommentator);
            }
            default -> {
            }
        }
        return null;
    }

    private Organism createOrgDepOnInterval(int interval) {
        if (interval < 5) {
            return spawnAnimal(interval);
        } else if (interval <= 9) {
            return spawnPlant(interval);
        }
        return null;
    }

    public void randomOrgSpawn() {
        int pos, interval = calculateOrgsOnSpawn();
        Coordinate coord = this.generateRandomCoordinate(N, M);
        pos = this.N * coord.y() + coord.x();
        boardOfOrganisms[pos] = new Human(boardOfOrganisms, N, M, mainCommentator);
        boardOfOrganisms[pos].setPosition(coord.x() + 1, coord.y() + 1);
        humanX = coord.x() + 1;
        humanY = coord.y() + 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < interval; j++) {
                coord = this.generateRandomCoordinate(N, M);
                pos = this.N * coord.y() + coord.x();
                boardOfOrganisms[pos] = createOrgDepOnInterval(i);
                boardOfOrganisms[pos].setPosition(coord.x() + 1, coord.y() + 1);
            }
        }
    }

    public Organism[] getBoardBeginning() {
        return this.boardOfOrganisms;
    }

    public ArrayList<Organism> getOrganismsOnBoard() {
        ArrayList<Organism> orgsOnBoard = new ArrayList<>();
        for (int i = 0; i < N * M; i++) {
            if (this.getBoardBeginning()[i] != null) {
                orgsOnBoard.add(this.getBoardBeginning()[i]);
            }
        }
        return orgsOnBoard;
    }

    public void makeTurn() {
        ArrayList<Organism> orgsOnBoard = this.getOrganismsOnBoard();
        orgsOnBoard.sort((o1, o2) -> {
            if (o1.getInitiative() != o2.getInitiative()) {
                return Integer.compare(o2.getInitiative(), o1.getInitiative());
            } else {
                return Integer.compare(o2.getAge(), o1.getAge());
            }
        });
        mainCommentator.addComment("  <==========>  \n");
        mainCommentator.addComment("  Round: " + globalRoundCounter + "\n");
        for (Organism org : orgsOnBoard) {
            if (org != null && !org.getLifeStatus()) {
                org.action();
                if (org.getSpecies() == 1 && !org.getLifeStatus()) {
                    humanX = org.getXCord();
                    humanY = org.getYCord();
                }
                org.incrementAge();
            }
        }
        globalRoundCounter += 1;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    void initiateLoadedInstance(char species, int strength, int initiative, int age, int xCord, int yCord,
                                int movSpeed, World mainWorld, boolean ability, int strengthDecrease,
                                int cooldown) {

        if (species == 1) {
            Organism mainCharacter = new Human(mainWorld.getBoardBeginning(), N, M, mainCommentator);
            mainCharacter.setStrength(strength);
            mainCharacter.setInitiative(initiative);
            mainCharacter.setPosition(xCord, yCord);
            mainCharacter.setAge(age);
            mainCharacter.setXYCords(xCord, yCord);
            mainCharacter.setMovementSpeed(movSpeed);
            mainCharacter.setAbility(ability);
            mainCharacter.setStrengthDecrease(strengthDecrease);
            mainCharacter.setAbilityCooldown(cooldown);
            return;
        }

        Wolf x;
        Sheep y;
        Fox z;
        Turtle a;
        Antelope b;
        Grass c;
        SowThistle d;
        Belladonna e;
        Guarana f;
        Sosnowsky g;

        switch (species) {
            case 'W' -> {
                x = new Wolf(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                x.setStrength(strength);
                x.setInitiative(initiative);
                x.setPosition(xCord, yCord);
                x.setAge(age);
                x.setXYCords(xCord, yCord);
                x.setMovementSpeed(movSpeed);
            }
            case 'S' -> {
                y = new Sheep(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                y.setStrength(strength);
                y.setInitiative(initiative);
                y.setPosition(xCord, yCord);
                y.setAge(age);
                y.setXYCords(xCord, yCord);
                y.setMovementSpeed(movSpeed);
            }
            case 'F' -> {
                z = new Fox(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                z.setStrength(strength);
                z.setInitiative(initiative);
                z.setPosition(xCord, yCord);
                z.setAge(age);
                z.setXYCords(xCord, yCord);
                z.setMovementSpeed(movSpeed);
            }
            case 'T' -> {
                a = new Turtle(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                a.setStrength(strength);
                a.setInitiative(initiative);
                a.setPosition(xCord, yCord);
                a.setAge(age);
                a.setXYCords(xCord, yCord);
                a.setMovementSpeed(movSpeed);
            }
            case 'A' -> {
                b = new Antelope(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                b.setStrength(strength);
                b.setInitiative(initiative);
                b.setPosition(xCord, yCord);
                b.setAge(age);
                b.setXYCords(xCord, yCord);
                b.setMovementSpeed(movSpeed);
            }
            case 'G' -> {
                c = new Grass(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                c.setStrength(strength);
                c.setInitiative(initiative);
                c.setPosition(xCord, yCord);
                c.setAge(age);
                c.setXYCords(xCord, yCord);
                c.setMovementSpeed(movSpeed);
            }
            case 'O' -> {
                d = new SowThistle(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                d.setStrength(strength);
                d.setInitiative(initiative);
                d.setPosition(xCord, yCord);
                d.setAge(age);
                d.setXYCords(xCord, yCord);
                d.setMovementSpeed(movSpeed);
            }
            case 'B' -> {
                e = new Belladonna(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                e.setStrength(strength);
                e.setInitiative(initiative);
                e.setPosition(xCord, yCord);
                e.setAge(age);
                e.setXYCords(xCord, yCord);
                e.setMovementSpeed(movSpeed);
            }
            case 'U' -> {
                f = new Guarana(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                f.setStrength(strength);
                f.setInitiative(initiative);
                f.setPosition(xCord, yCord);
                f.setAge(age);
                f.setXYCords(xCord, yCord);
                f.setMovementSpeed(movSpeed);
            }
            case 'H' -> {
                g = new Sosnowsky(mainWorld.getBoardBeginning(), N, M, mainCommentator);
                g.setStrength(strength);
                g.setInitiative(initiative);
                g.setPosition(xCord, yCord);
                g.setAge(age);
                g.setXYCords(xCord, yCord);
                g.setMovementSpeed(movSpeed);
            }
            default -> {
            }
        }
    }

    public void loadGameState(String fileName) {
        File file = new File(fileName);
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int X, Y, max, roundCounter;
            X = dataInputStream.readInt();
            Y = dataInputStream.readInt();
            max = dataInputStream.readInt();
            roundCounter = dataInputStream.readInt();
            this.globalRoundCounter = roundCounter;
            this.boardOfOrganisms = new Organism[X * Y];
            N = X;
            M = Y;
            char species;
            int strength, initiative, age, xCord, yCord, movSpeed, decreaseReps = 0, cooldown = 0;
            boolean ability = false;
            for (int i = 0; i < max; i++) {
                species = dataInputStream.readChar();
                if (species == 1) {
                    ability = dataInputStream.readBoolean();
                    decreaseReps = dataInputStream.readInt();
                    cooldown = dataInputStream.readInt();
                    strength = dataInputStream.readInt();
                    initiative = dataInputStream.readInt();
                    age = dataInputStream.readInt();
                    xCord = dataInputStream.readInt();
                    yCord = dataInputStream.readInt();
                    movSpeed = dataInputStream.readInt();
                    initiateLoadedInstance(species, strength, initiative, age, xCord, yCord, movSpeed,
                            this, ability, decreaseReps, cooldown);
                    humanY = yCord;
                    humanX = xCord;
                    continue;
                }
                strength = dataInputStream.readInt();
                initiative = dataInputStream.readInt();
                age = dataInputStream.readInt();
                xCord = dataInputStream.readInt();
                yCord = dataInputStream.readInt();
                movSpeed = dataInputStream.readInt();
                initiateLoadedInstance(species, strength, initiative, age, xCord, yCord, movSpeed,
                        this, ability, decreaseReps, cooldown);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGameState(String fileName) {
        try (DataOutputStream fSave = new DataOutputStream(new FileOutputStream(fileName))) {
            int X = N;
            int Y = M;
            fSave.writeInt(X);
            fSave.writeInt(Y);
            ArrayList<Organism> orgsOnBoard =this.getOrganismsOnBoard();
            int max = orgsOnBoard.size();
            fSave.writeInt(max);
            fSave.writeInt(globalRoundCounter);

            char species;
            int strength, initiative, age, xCord, yCord, movSpeed, cooldown, decreaseReps;
            boolean ability;
            for (int i = 0; i < max; i++) {
                species = orgsOnBoard.get(i).getSpecies();
                if (species == 1) {
                    ability = orgsOnBoard.get(i).abilityActive();
                    decreaseReps = orgsOnBoard.get(i).getStrengthDecreaseReps();
                    cooldown = orgsOnBoard.get(i).getCooldownReps();
                    strength = orgsOnBoard.get(i).getStrength();
                    initiative = orgsOnBoard.get(i).getInitiative();
                    age = orgsOnBoard.get(i).getAge();
                    xCord = orgsOnBoard.get(i).getXCord();
                    yCord = orgsOnBoard.get(i).getYCord();
                    movSpeed = orgsOnBoard.get(i).getMovementSpeed();

                    fSave.writeChar(species);
                    fSave.writeBoolean(ability);
                    fSave.writeInt(decreaseReps);
                    fSave.writeInt(cooldown);
                    fSave.writeInt(strength);
                    fSave.writeInt(initiative);
                    fSave.writeInt(age);
                    fSave.writeInt(xCord);
                    fSave.writeInt(yCord);
                    fSave.writeInt(movSpeed);
                    continue;
                }
                strength = orgsOnBoard.get(i).getStrength();
                initiative = orgsOnBoard.get(i).getInitiative();
                age = orgsOnBoard.get(i).getAge();
                xCord = orgsOnBoard.get(i).getXCord();
                yCord = orgsOnBoard.get(i).getYCord();
                movSpeed = orgsOnBoard.get(i).getMovementSpeed();

                fSave.writeChar(species);
                fSave.writeInt(strength);
                fSave.writeInt(initiative);
                fSave.writeInt(age);
                fSave.writeInt(xCord);
                fSave.writeInt(yCord);
                fSave.writeInt(movSpeed);
            }
        } catch (IOException e) {
            System.exit(-100);
        }
    }

}
