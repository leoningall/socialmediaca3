package socialmedia;

import java.util.ArrayList;

public class Post {

    private String content;
    private String handle;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private int id;
    // added a list of endorsements, we might not need it, but its there just in case.
    private ArrayList<Endorsement> endorsements = new ArrayList<Endorsement>();

    public Post(String content, String handle) {
        this.content = content;
        this.handle = handle;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getHandle() {
        return handle;
    }
    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
    public ArrayList<Comment> addComment(Comment comment) {
        comments.add(comment);
    }
    
    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }
    public ArrayList<Endorsement> addEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
    }
}