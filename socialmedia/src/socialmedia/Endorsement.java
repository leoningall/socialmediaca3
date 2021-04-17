package socialmedia;

public class Endorsement extends Post {

    private int postID;
    private String content;

    // might need to inherit content, not re-initialising it... 
    public Endorsement(String handle, int postID) {
        super(handle, id);
        this.postID = postID;  
    }

    public int getPostID() {return postID;}
    public void setPostID(int postID) {this.postID = postID;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content};
}