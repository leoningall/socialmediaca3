package socialmedia;

import java.util.ArrayList;

public class Post {

    private String content;
    private String handle;
    private ArrayList<Post> children = new ArrayList<Post>();
    private int id;
    // added a list of endorsements, we might not need it, but its there just in case.

    /**
     * This is the contstructor method of Post
     * @param content The content of the Post
     * @param handle The handle of the Post
     */
    public Post(String content, String handle) {
        this.content = content;
        this.handle = handle;
    }
    /**
     * This constructor is for generic posts.
     * @param content The content of the Post
     */
    public Post(String content) {
        this.content = content;
    }

    /**
     * This gets the content of the Post
     * @return content
     */
    public String getContent() {
        return content;
    }
    /**
     * This sets the content of the Post
     * @param content The content to be set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This gets the handle of the Post
     * @return handle 
     */
    public String getHandle() {
        return handle;
    }
    /**
     * This sets the handle of the Post
     * @param handle The handle to be set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * This gets the id of the Post
    * @return id
     */
    public int getID() {
        return id;
    }
    /**
     * This sets the id of the Post
     * @param id The id to be set
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * This gets the ArrayList of children of the post
     * @return children
     */
    public ArrayList<Post> getChildren() {
        return children;
    }
    /**
     * This adds a child to the ArrayList
     * @param post The post to be added
     */
    public void addChild(Post post) {
        children.add(post);
    }
    /**
     * This removes an endorsement from the list of children
     * @param endorsement The endorsement to be removed
     */
    public void removeEndorsement(Endorsement endorsement) {
        for(Post post: children) {
            if(post.getClass() == Endorsement.class && post == endorsement) {
                children.remove(post);
            }
        }
    }
    /**
     * I dont think this is needed anymore
     */
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
                comments.remove(comments.get(i));
            }
        }
    }
     */
    
    /**
     * This gets an ArrayList of all the endorsements of this Post
     * @return endorsements
     */
    public ArrayList<Endorsement> getEndorsements() {
        ArrayList<Endorsement> endorsements = new ArrayList<>();
        for(Post post: children) {
            if(post.getClass() == Endorsement.class) {
                Endorsement e = (Endorsement)post;
                endorsements.add(e);
            }
        }
        return endorsements;
    }
    /**
     * public void addEndorsement(Endorsement endorsement) {
        children.add(endorsement);
        }
     */
    
    public ArrayList<Comment> getComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        for(Post post: children) {
            if(post.getClass() == Comment.class) {
                Comment e = (Comment)post;
                comments.add(e);
            }
        }
        return comments;
    }

}