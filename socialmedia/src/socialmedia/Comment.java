package socialmedia;

import java.util.ArrayList;

public class Comment extends Post {

    private int postID;
    private int id;
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    // havent done this bit yet ...
    public Comment(String handle, int postID, String content) {
        super(content,handle);
        this.postID = postID;  
    }

    public int getPostID() {return postID;}
    public void setPostID(int postID) {this.postID = postID;}

    public int getID() {return id;}
    public void setID(int id) {this.id = id;}

    public ArrayList<Comment> getComments() {return comments;}
    public void addComment(Comment comment) {comments.add(comment);}
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
}