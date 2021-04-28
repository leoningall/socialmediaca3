package socialmedia;

import java.util.ArrayList;

/**
 * This is the Comment class
 * @author Leon Ingall, Charles Pearman-Wright
 * @version 1.0
 */


public class Comment extends Post {

    private int postID;
    private int id;
    private ArrayList<Post> children = new ArrayList<Post>();

    /**
     * This is the constructor method of Comment
     * @param handle The handle of the user that made this comment
     * @param postID The id of the posts that this comments
     * @param content The message of the comment
     */
    public Comment(String handle, int postID, String content) {
        super(content,handle);
        this.postID = postID;  
    }
    /**
     * This gets the id of the original post
     * @return postID
     */
    public int getPostID() {return postID;}
    /**
     * This sets the id of the original post
     * @param postID the id to be set
     */
    public void setPostID(int postID) {this.postID = postID;}

    /**
     * This gets the id of the comment 
     * @return id
     */
    public int getID() {return id;}
    /**
     * This sets the id of the comment
     * @param id The id to be set
     */
    public void setID(int id) {this.id = id;}

    /**
     * This gets the ArrayList of children of this comment
     * @return childen
     */
    public ArrayList<Post> getChildren() {return children;}
    /**
     * This adds a child to the children ArrayList
    * @param post The post to be added to the ArrayList
     */
    public void addChild(Post post) {children.add(post);}
    
    
}