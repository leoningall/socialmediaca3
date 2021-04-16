package socialmedia;

public class Endorsement extends Post {

    public Endorsement(String content, String handle, boolean endorsement) {
        super(content, handle);
        this.endorsement = endorsement;   
    }

    public boolean getEndorsement() {return endorsement;}
    
}
