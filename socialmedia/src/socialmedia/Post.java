package socialmedia;

import java.util.ArrayList;

public class Post {

    private String content;
    private String handle;
    private ArrayList<Post> comments = new ArrayList<Post>();
    private int id;
    private int endorsements = 0;



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
    
}