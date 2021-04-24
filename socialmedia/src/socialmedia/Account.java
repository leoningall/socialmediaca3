package socialmedia;

import java.util.ArrayList;

public class Account {

    private String handle;
    private String description;
    //String name;
    private ArrayList<Post> posts = new ArrayList<Post>();
    private int id;

    /**
     * This is the constructor method for Account.
     * @param handle the handle of the user
     * @param description a short personal description
     */
    public Account(String handle, String description) {
        this.handle = handle;
        this.description = description;
        this.posts = posts;

    }
    /**
     * This is the constructor method for Account.
     * @param description a short personal description
     */
    public Account(String handle) {
        this.handle = handle;
        this.posts = posts;
    }
    
    /**
     * This returns the id of the Account
     * @return id
     */
    public int getID() {
        return id;
    }
    /**
     * This sets the the id of the Account
     * @param id The id that will be set
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * This returns the handle of the Account
     * @return handle 
     */
    public String getHandle() {
        return handle;
    }
    /**
     * This sets the handle of the Account
     * @param handle The handle that will be set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }
    /**
     * This gets the description of the user
     * @return description 
     */
    public String getDescription() {
        return description;
    }
    /**
     * This sets the description of the Account
     * @param desc The description that will be set
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * This returns an ArrayList of all the posts the user has made
     * @return posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }
    /**
     * Sets the posts ArrayList
     * @param posts
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}