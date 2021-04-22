package socialmedia;

import java.util.ArrayList;

public class Comment extends Post {

    private int postID;
    private int id;
    private ArrayList<Post> children = new ArrayList<Post>();

    // havent done this bit yet ...
    public Comment(String handle, int postID, String content) {
        super(content,handle);
        this.postID = postID;  
    }

    public int getPostID() {return postID;}
    public void setPostID(int postID) {this.postID = postID;}

    public int getID() {return id;}
    public void setID(int id) {this.id = id;}

    public ArrayList<Post> getChildren() {return children;}
    public void addChild(Post post) {children.add(post);}
    
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
                children.remove(children.get(i));
            }
        }
    }
     */
    
}