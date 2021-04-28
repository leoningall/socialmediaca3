package socialmedia;

import java.util.ArrayList;

/**
 * This is the Post class
 * @author Leon Ingall, Charles Pearman-Wright
 * @version 1.0
 */

public class Post {

    private String content;
    private String handle;
    private ArrayList<Post> children = new ArrayList<Post>();
    private int id;

    /**
     * This is the contstructor method of Post
     * @param content The content of the Post
     * @param handle The handle of the Post
     */
    public Post(String content, String handle) {
        this.content = content;
        this.handle = handle;
    }

    /**
     * This gets the content of the Post
     * @return content
     */
    public String getContent() {
        return content;
    }
    /**
     * This sets the content of the Post
     * @param content The content to be set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This gets the handle of the Post
     * @return handle 
     */
    public String getHandle() {
        return handle;
    }
    /**
     * This sets the handle of the Post
     * @param handle The handle to be set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * This gets the id of the Post
    * @return id
     */
    public int getID() {
        return id;
    }
    /**
     * This sets the id of the Post
     * @param id The id to be set
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * This gets the ArrayList of children of the post
     * @return children
     */
    public ArrayList<Post> getChildren() {
        return children;
    }
    /**
     * This adds a child to the ArrayList
     * @param post The post to be added
     */
    public void addChild(Post post) {
        children.add(post);
    }
    /**
     * This removes an endorsement from the list of children
     * @param endorsement The endorsement to be removed
     */
    public void removeEndorsement(Endorsement endorsement) {
        // Iterates through the children ArrayList
        for(Post post: children) {
            // checks if it's an Endorsement
            if(post.getClass() == Endorsement.class && (Endorsement)post == endorsement) {
                // removes it
                children.remove(post);
                
            }
        }
    }
    
    /**
     * This gets an ArrayList of all the endorsements of this Post
     * @return endorsements
     */
    public ArrayList<Endorsement> getEndorsements() {
        ArrayList<Endorsement> endorsements = new ArrayList<>();
        for(Post post: children) {
            // checks if it's an Endorsement
            if(post.getClass() == Endorsement.class) {
                // adds it to the list
                Endorsement e = (Endorsement)post;
                endorsements.add(e);
            }
        }
        return endorsements;
    }
    
    public ArrayList<Comment> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        for(Post post: children) {
            // checks if it's a comment
            if(post.getClass() == Comment.class) {
                // adds it to the list
                Comment e = (Comment)post;
                comments.add(e);
            }
        }
        return comments;
    }

}