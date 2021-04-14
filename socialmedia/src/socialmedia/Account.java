package socialmedia;

import java.util.ArrayList;

public class Account {

    private String handle;
    private String description;
    //String name;
    private ArrayList<Post> posts = new ArrayList<Post>();
    private int id;

    public Account(String handle, String description) {
        this.handle = handle;
        this.description = description;
        this.posts = posts;

    }

    public Account(String handle) {
        this.handle = handle;
        this.posts = posts;
    }
    
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }
    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }



}