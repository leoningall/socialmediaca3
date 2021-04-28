package socialmedia;

/**
 * This is the Endorsement class
 * @author Leon Ingall, Charles Pearman-Wright
 * @version 1.0
 */

public class Endorsement extends Post {

    private int postID;
    private String content;
    private int id;

    /**
     * This is the constructor method for Endoresement
     * @param handle the handle of the user endoring a post
     * @param postID the id of the post it is endorsing
     */
    public Endorsement(String handle, int postID) {
        //make new post with empty content - said content gets filled in
        //as soon as it's made in the endorsePost method
        super("", handle);
        this.postID = postID;
    }

    /**
     * This gets the id of the Endorsement
     * @return id
     */
    public int getID() {return id;}
    /**
     * This sets the id of the Endorsement
     * @param id The id to be set
     */
    public void setID(int id) {this.id = id;}
    
    /**
     * This gets the id of the original post
     * @return postID
     */
    public int getPostID() {return postID;}
    /**
     * This sets the id of the original post
     * @param postID The id to be set
     */
    public void setPostID(int postID) {this.postID = postID;}

    /**
     * This gets the content of the Endorsement
     * @return content
     */
    public String getContent() {return content;}
    /**
     * This sets the content of the Endorsement
     * @param content The content to be set
     */
    public void setContent(String content) {this.content = content;}

    
}
