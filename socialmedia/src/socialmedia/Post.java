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
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    /**
     * This probs doesnt work...
     */
    public void removeComment(Comment comment) {
        // iterate through the list of comments
        for(int i=0; i<comments.size(); i++) {
            // if the comment matches
            if(comments.get(i) == comment) {
                // if that comment has comments
                if(comment.getComments().size() > 0) {
                    // iterate through that list
                    for(Comment c: comment.getComments()) {
                        // recursively remove those comments 
                        removeComment(c);
                    }
                }
                comments.remove(comments.get(i));
            }
        }
    }
    
    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }
    public ArrayList<Endorsement> addEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
    }
}