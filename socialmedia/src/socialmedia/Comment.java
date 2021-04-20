package socialmedia;

public class Comment extends Post {

    private int postID;


    // havent done this bit yet ...
    public Comment(String handle, int postID, String content) {
        super(content,handle);
        this.postID = postID;  
    }

    public int getPostID() {return postID;}
    public void setPostID(int postID) {this.postID = postID;}
}