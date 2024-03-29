import socialmedia.AccountIDNotRecognisedException;
import socialmedia.HandleNotRecognisedException;
import socialmedia.SocialMedia;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.InvalidPostException;
import socialmedia.NotActionablePostException;
import socialmedia.PostIDNotRecognisedException;
import socialmedia.SocialMediaPlatform;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface
 *
 * 
 * @author Diogo Pacheco, Leon Ingall, Charles Pearman-Wright
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id;
		try {
			id = platform.createAccount("my_handle");
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		}

		
		try {
			
			platform.createAccount("coolman123", "a very cool man");
			platform.createPost("coolman123", "what a message");
			platform.createAccount("greatdude456");
			platform.commentPost("greatdude456", 1, "good post my friend");
			platform.endorsePost("coolman123", 2);
			platform.commentPost("greatdude456",1,"cracking");
			platform.endorsePost("greatdude456", 1);
			platform.createPost("greatdude456","pogchamp");
			platform.endorsePost("coolman123", 6);
			platform.endorsePost("coolman123", 6);


			System.out.println("Comment Posts: " +platform.getTotalCommentPosts());
			System.out.println("Original Posts: " + platform.getTotalOriginalPosts());
			System.out.println("Endorsement Posts: " + platform.getTotalEndorsmentPosts());
			System.out.println("Most endorsed Post: " + platform.getMostEndorsedPost());
			System.out.println("Most endorsed Account: " + platform.getMostEndorsedAccount());

			System.out.println(platform.showAccount("coolman123"));

		} catch (IllegalHandleException e) {
			e.printStackTrace();
		} catch (InvalidHandleException e) {
			e.printStackTrace();
		} catch (InvalidPostException e) {
			e.printStackTrace();
		} catch (HandleNotRecognisedException e) {
			e.printStackTrace();
		} catch (PostIDNotRecognisedException e) {
			e.printStackTrace();
		} catch (NotActionablePostException e) {
			e.printStackTrace();
		// } catch (IOException e) {
		// 	e.printStackTrace();
		} 
	}
}
