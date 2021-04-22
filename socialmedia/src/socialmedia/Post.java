package socialmedia;

import java.util.ArrayList;

public class Post {

    private String content;
    private String handle;
    private ArrayList<Post> children = new ArrayList<Post>();
    private int id;
    // added a list of endorsements, we might not need it, but its there just in case.

    public Post(String content, String handle) {
        this.content = content;
        this.handle = handle;
    }
    /**
     * This constructor is for generic posts.
     */
    public Post(String content) {
        this.content = content;
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

    public ArrayList<Post> getChildren() {
        return children;
    }
    public void addChild(Post post) {
        children.add(post);
    }
    public void removeEndorsement(Endorsement endorsement) {
        for(Endorsement e: children) {
            if(endorsement == e) {
                children.remove(e);
            }
        }
    }
    /**
     * I dont think this is needed anymore
     */
    /**
     * public void removeChild(Post post) {
        // iterate through the list of comments
        for(int i=0; i<children.size(); i++) {
            // if the comment matches
            if(children.get(i) == post) {
                // if that comment has comments
                if(post.getChildren().size() > 0) {
                    // iterate through that list
                    for(Post p: post.getChildren()) {
                        // recursively remove those comments 
                        removeChild(p);
                    }
                }
                comments.remove(comments.get(i));
            }
        }
    }
     */
    
    
    public ArrayList<Endorsement> getEndorsements() {
        ArrayList<Endorsement> endorsements = new ArrayList<>();
        for(Post post: children) {
            if(post.getClass() == Endorsement.class) {
                endorsements.add(post);
            }
        }
        return endorsements;
    }
    /**
     * public void addEndorsement(Endorsement endorsement) {
        children.add(endorsement);
        }
     */
    
}