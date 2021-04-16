package socialmedia;

public class Comment extends Post {

    private int id;

    public Comment(String handle, int id, String content) {
        super(content,handle);
        this.id = id;  
        
    }
    
}