package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

/**
 * BadSocialMedia is a minimally compiling, but non-functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */

public class SocialMedia implements SocialMediaPlatform {
	
	public ArrayList<Account> allAccounts = new ArrayList<Account>();
	public ArrayList<Post> allPosts = new ArrayList<Post>();
	public static int counter;

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

		Boolean valid = true;

		for (Account acc : allAccounts) {
			if (acc.getHandle().equals(handle) ) {
				valid = false;
			}
		}
		if (valid == false) {
			throw new IllegalHandleException("Error - Handle already exists.");
		}

		if ((handle.length()) == 0 || (handle.length() > 30)) {
			throw new InvalidHandleException("Handle must be between 0 and 30 characters");
		}

		if (handle.contains(" ")) {
			throw new InvalidHandleException("Handle can't contain whitespace.");
		}


		Account acc = new Account(handle); //create new account
		allAccounts.add(acc);				//add it to arraylist
		acc.setID(allAccounts.size());		//set id to size of list of all accounts

		return acc.getID();
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

		Boolean valid = true;

		for (Account acc : allAccounts) {
			if (acc.getHandle().equals(handle) ) {
				valid = false;
			}
		}
		if (valid == false) {
			throw new IllegalHandleException("Error - Handle already exists.");
		}

		if ((handle.length()) == 0 || (handle.length() > 30)) {
			throw new InvalidHandleException("Handle must be between 0 and 30 characters");
		}

		if (handle.contains(" ")) {
			throw new InvalidHandleException("Handle can't contain whitespace.");
		}

		Account acc = new Account(handle,description); //create new account
		allAccounts.add(acc);				//add it to arraylist
		acc.setID(allAccounts.size());		//set id to size of list of all accounts

		return acc.getID();
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		
		for (int i = 0; i < allAccounts.size(); i++) {
			Account acc = allAccounts.get(i);
			if (acc.getID() == id) {
				// remove all posts of that user
				for(Post post: allPosts) {
					if(post.getHandle() == acc.getHandle()) {
						deletePost(post.getID());
					}
				}
				// remove the account
				allAccounts.remove(i);
				return;
			}
		}

		throw new AccountIDNotRecognisedException("Given ID not found in list of Accounts.");

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {

		for (int i = 0; i < allAccounts.size(); i++) {
			Account acc = allAccounts.get(i);
			if (acc.getHandle() == handle) {
				// remove all posts of that user
				for(Post post: allPosts) {
					if(post.getHandle() == handle) {
						deletePost(post.getID());
					}
				}
				// remove the account
				allAccounts.remove(i);
				return;
			}
		}

		throw new HandleNotRecognisedException("Given ID not found in list of Accounts.");

	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		
				if ((newHandle.length()) == 0 || (newHandle.length() > 30)) {
					throw new InvalidHandleException("New handle must be between 0 and 30 characters");
				}
		
				if (newHandle.contains(" ")) {
					throw new InvalidHandleException("New handle can't contain whitespace.");
				}

				for (Account acc : allAccounts) {
					if (acc.getHandle() == oldHandle) {
						acc.setHandle(newHandle);
						return;
					}
				}

				throw new HandleNotRecognisedException("Old handle not found in list of accounts.");

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		for (Account acc : allAccounts) {
			if (acc.getHandle() == handle) {
				acc.setDescription(description);
				return;
			}
		}

		throw new HandleNotRecognisedException("Handle not found in list of accounts.");

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		for (Account acc : allAccounts) {
			if (acc.getHandle() == handle) {
				
				System.out.println("\n\nID: " + Integer.toString(acc.getID()));
				System.out.println("Handle: " + acc.getHandle());
				if (acc.getDescription() != null) {
					System.out.println("Description: " + acc.getDescription());
				}
				System.out.println("Post Count: " + acc.getPosts().size());

				//TODO: loop through accounts and see which are endorsements, sum them and print

			}
		}
		return null;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

		if (message.length() == 0 || message.length() > 100) {
			throw new InvalidPostException("Message must be between 1 and 100 characters.");
		}

		Boolean valid = false;

		for (Account acc : allAccounts) {
			if (acc.getHandle() == handle) {
				valid = true;
			}
		}
		
		if (valid == false) {
			throw new HandleNotRecognisedException("Handle not found in list of accounts");
		}

		
		Post post = new Post(handle, message);

		for (Account acc : allAccounts) {
			if (acc.getHandle() == handle) {
				acc.getPosts().add(post);
			}
		}

		int id = allPosts.size()+1;
		post.setID(id);
		allPosts.add(post);		

		return id;
	}

	@Override
	public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		
		// checking the handle
		Boolean isValidAccount = false;
		Account account = null;
		for(Account acc: allAccounts) {
			if(acc.getHandle() == handle) {
				account = acc;
				isValid = true;
			}
		}
		if(!isValidAccount) {
			throw new HandleNotRecognisedException("Handle not found in list of accounts");
		}

		// checking the post id
		Boolean isValidPost = false;
		Post endorsed_post = null;
		for(Post post: allPosts) {
			if(post.getID() == id) {
				endorsed_post = post; 
				isValidPost = true;
			}
		}
		if(!isValidPost) {
			throw new PostIDNotRecognisedException("ID not found in the list of posts");
		}

		// checking if the post is an endorsement
		if(allPosts.get(id -1).getClass() == Endorsement.class) {
			throw new NotActionablePostException("You cannot endorse an endorsement!");
		}

		Endorsement endorsement = new Endorsement(handle, id);
		String endorsement_content = "EP@" + account.getHandle() + ": " + endorsed_post.getContent();
		endorsement.setContent(endorsement_content);

		endorsed_post.addEndorsement(endorsement);

		// setting the id of the endorsement, because it's a post (not sure if this will work)
		int id = allPosts.size()+1;
		endorsement.setID(id);
		allPosts.add(endorsement);

		return id;
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

		// check if the handle is correct
		Boolean isValidAccount = false;
		for(Account acc: allAccounts) {
			if(acc.getHandle() == handle){
				isValidAccount = true;
				break;
			}
		}
		if(!isValidAccount) {
			throw new HandleNotRecognisedException("Handle not found in list of accounts");
		}

		//check if post id is correct
		Boolean isValidPostID = false;
		for(Post post: allPosts) {
			if(post.getID() == id) {
				if ((post.getClass == Endorsement.class) {
					throw new NotActionablePostException("You cannt comment this post!");
				}
				isValidPostID = true;
				break;
			}
		}
		if(!isValidPostID) {
			throw new PostIDNotRecognisedException("ID not found in the list of posts");
		}

		Comment comment = new Comment(handle, id, message);
		comment.setID(allPosts.size());
		allPosts.add(comment);
			
		return comment.getID();
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// must delete all the comments and endorsements to that post as well

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		outputString = "";
		//loop through all posts and find matching ID
		for (Post i : allPosts) {
			if (i.getID() == id) {
				//if id matches, construct string to return and then return it
				outputString += "\n\nID: " + id;
				outputString += "\nAccount: " + i.getHandle();
				outputString += "\nNo. endorsements: " + i.getEndorsements().length();
				outputString += " | No. comments: " + i.getComments().length();
				outputString += "\n" + i.getContent();
				return outputString;
			}
		}
		//if here is reached, then the id must be wrong
		throw new PostIDNotRecognisedException("Post ID not recognised.");

	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		// return the length of the arraylist of accounts
		return allAccounts.size();
	}

	@Override
	public int getTotalOriginalPosts() {
		counter = 0;
		for (Post i : allPosts) {
			//if post isn't an endorsement or a comment, must be an original one
			//so add one to the counter
			if ((i.getClass != Endorsement.class) && (i.getClass != Comment.class)) {
				counter++;
			}
		}
		return counter;
	}


	@Override
	public int getTotalEndorsmentPosts() {
		counter = 0;
		for (Post i : allPosts) {
			//check if an endorsement, or just a normal post
			if (i.getClass() == Endorsement.class) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public int getTotalCommentPosts() {
		counter = 0;
		for (Post i : allPosts) {
			//check if a comment, or just a normal post
			if (i.getClass() == Comment.class) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void erasePlatform() {
		// TODO Auto-generated method stub

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
