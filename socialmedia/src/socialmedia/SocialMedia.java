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
			if (allAccounts.get(i).getID() == id) {
				allAccounts.remove(i);
				return;
			}
		}

		throw new AccountIDNotRecognisedException("Given ID not found in list of Accounts.");

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		for (int i = 0; i < allAccounts.size(); i++) {
			if (allAccounts.get(i).getHandle() == handle) {
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
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
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
