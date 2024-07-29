package GUI.MainContainer.MainContents;

import javax.swing.*;

public class Commentator {

    private final JTextArea commentingArea;

    public Commentator(JTextArea commentingPodcast){
        this.commentingArea = commentingPodcast;
    }

    public void addComment(String text){
        commentingArea.append(text);
    }

}
