package GUI;
import GUI.GetSizePrompt.SizePrompt;
import GUI.MainContainer.MainContents.Commentator;
import GUI.MainContainer.MainWindow;
import World.World;
import javax.swing.*;


public class Organizer extends JFrame {
    //program begins

    protected Commentator mainCommentator;
    public void createSizePrompt(){
        new SizePrompt();
    }
    public void createMainWindow(World mainWorld){
        new MainWindow(mainWorld);
    }
    public void createWorld(int x, int y){
        World mainWorld = new World(x, y, mainCommentator);
        mainWorld.randomOrgSpawn();
        createMainWindow(mainWorld);

    }
}
