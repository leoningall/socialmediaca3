package socialmedia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * SocialMedia is a compiling, functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Leon Ingall, Charles Pearman-Wright
 * @version 1.0
 */

public class SocialMedia implements SocialMediaPlatform {
	
	public ArrayList<Account> allAccounts = new ArrayList<Account>();
	public ArrayList<Post> allPosts = new ArrayList<Post>();
	public static int counter;
	public static StringBuilder childrenDetails;
	public static int postIDTally = 0;

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
		
		
		for (Account acc : allAccounts) {
			if (acc.getID() == id) {
				// remove all posts of that user
				for(Post post: allPosts) {
					if(post.getHandle() == acc.getHandle()) {
						try {
                            deletePost(post.getID());
                        } catch (PostIDNotRecognisedException e) {
                            // if post id not recognized
                            e.printStackTrace();
                        }
					}
				}
				// remove the account
				allAccounts.remove(acc);
				return;
			}
		}

		throw new AccountIDNotRecognisedException("Given ID not found in list of Accounts.");

	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {

		
		for (Account acc : allAccounts) {
			if (acc.getHandle() == handle) {
				//remove all posts of that user
				for(Post post: acc.getPosts()) {

					try {
						deletePost(post.getID());
					} catch (PostIDNotRecognisedException e) {
						e.printStackTrace();
					}

				}
				// remove the account
				allAccounts.remove(acc);
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
				if(acc.getHandle() != null) {
					System.out.println("Handle: " + acc.getHandle());
				}
				if (acc.getDescription() != null) {
					System.out.println("Description: " + acc.getDescription());
				}
				System.out.println("Post Count: " + acc.getPosts().size());
			}
			throw new HandleNotRecognisedException("Handle not found in list of accounts.");
		}
		return null;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

		if (message.length() == 0 || message.length() > 100) {
			throw new InvalidPostException("Message must be between 1 and 100 characters.");
		}

		for (Account acc : allAccounts) {
			if (acc.getHandle() == handle) {
				int id = ++postIDTally;
				Post post = new Post(message, handle);
				post.setID(id);
				acc.getPosts().add(post);
				allPosts.add(post);
				return post.getID();
			}
		}

		//if here is reached, handle must be invalid
		throw new HandleNotRecognisedException("Handle not found in list of accounts");
		
	}

	@Override
	public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		
		// checking the handle
		Account accountEndorsing = null;
		Boolean isValidAccount = false;
		for(Account acc: allAccounts) {
			if(acc.getHandle() == handle) {
				isValidAccount = true;
				accountEndorsing = acc;
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
				if (post.getClass() == Endorsement.class) {
					throw new NotActionablePostException("You cannot endorse an endorsement!");
				}
				endorsed_post = post;
				isValidPost = true;
			}
		}
		if(!isValidPost) {
			throw new PostIDNotRecognisedException("ID not found in the list of posts");
		}

		// checking if the post is an endorsement

		Endorsement endorsement = new Endorsement(handle, id);

		// create the endorsement content and set it.
		String endorsee = "";
		for (Post i : allPosts) {
			if (i.getID() == id) {
				endorsee = i.getHandle();
			}
		}

		String endorsement_content = "EP@" + endorsee + ": " + endorsed_post.getContent();
		endorsement.setContent(endorsement_content);
		// set the id.
		int idToAdd = ++postIDTally;
		endorsement.setID(idToAdd);
		// add the endorsement as a child of the post it is endorsing.
		Post child = endorsement;
		// endorsed_post.addChild(endorsement);
		if (endorsed_post.getClass() == Comment.class) {
			endorsed_post = (Comment)endorsed_post;
		}
		endorsed_post.addChild(child);
		// add the endorsement to the list of posts.
		allPosts.add(endorsement);

		accountEndorsing.getPosts().add(endorsement);
		

		return id;
	}
	
	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

		// theres an error in here that fucks up everything, and makes all the throwing Exceptions go white.
	
		
		// check if the handle is correct
		Boolean isValidAccount = false;
		for(Account acc: allAccounts) {
			if(acc.getHandle() == handle){
				isValidAccount = true;
				break;
			}
		}
		if(isValidAccount == false) {
			throw new HandleNotRecognisedException("Handle not found in list of accounts");
		}

		//check if post id is correct
		Boolean isValidPostID = false;
		Post post = null;
		for(Post p: allPosts) {
			// checks 
			if(p.getID() == id) {
				if (p.getClass() == Endorsement.class) {
					throw new NotActionablePostException("You cannt comment this post!");
				}
				isValidPostID = true;
				post = p;
				break;
			}
		}
		if(isValidPostID == false) {
			throw new PostIDNotRecognisedException("ID not found in the list of posts");
		}

		Comment comment = new Comment(handle, id, message);
		comment.setID(++postIDTally);

		post.addChild(comment);
		allPosts.add(comment);
			
		return comment.getID();
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		
		for (Post post : allPosts) {
			if(post.getID() == id) {
				if(post.getChildren().size() > 0) {
					Post newPost = new Post(null ,"The original content was removed from the system and is no longer available.");
					newPost.setID(-1);
					for(Post p: post.getChildren()) {
						if(p.getClass() == Endorsement.class) {
							Endorsement endToRemove = (Endorsement)p;
							
							//post.removeEndorsement(endToRemove);
							allPosts.remove(endToRemove);
							
						}
						else {
							if(p.getClass() == Comment.class) {
								p.setHandle("Unavailable");
								p.setContent("The original content was removed from the system and is no longer available.");
								Comment c = (Comment)p;
								c.setPostID(newPost.getID());
								//deletePost(p.getID());
							}
						}
					}
					
				}
				allPosts.remove(post);
				return;
			// isValid = true;
			// break;
			}
		}


		throw new PostIDNotRecognisedException("ID not found in the list of posts");

		

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		String outputString = "";
		//loop through all posts and find matching ID
		for (Post i : allPosts) {
			if (i.getID() == id) {
				//if id matches, construct string to return and then return it
				outputString += "\nID: " + id;
				outputString += "\nAccount: " + i.getHandle();
				outputString += "\nNo. endorsements: " + i.getEndorsements().size();
				//check if each child is a comment
				outputString += " | No. comments: " + i.getComments().size();
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
		
		
		boolean isValid = false;
        StringBuilder childrenDetails = new StringBuilder();
        for (Post post : allPosts) {
            if (post.getID() == id) {
                isValid = true;
                childrenDetails.append(showIndividualPost(id) + "|" + "\n" + "| > ");
            }
        }
        if (isValid) {
            counter = 0;
            while (counter < allPosts.size()) {
				Post post = allPosts.get(counter);
                if (post.getID() == id) {
                    for (Post child : post.getChildren()) {
						if(child.getClass() == Comment.class) {
							childrenDetails.append(showPostChildrenDetails(child.getID()) + "\n" + "|" + "\n" + "| > ");
						}
                    }
                }
                counter++;
            }
            return childrenDetails;
        } else {
            throw new PostIDNotRecognisedException("post id does not exist");
		}
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
			if ((i.getClass() != Endorsement.class) && (i.getClass() != Comment.class)) {
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
		//Loops through all posts and keeps a track of the one with the longest endorsements ArrayList.
		Post mostEndorsedPost = allPosts.get(0);
		for (Post i : allPosts) {
			if (i.getEndorsements().size() > mostEndorsedPost.getEndorsements().size()) {
				mostEndorsedPost = i;
			}
		}
		return mostEndorsedPost.getID();
	}

	@Override
	public int getMostEndorsedAccount() {
		// Find account with the longest arraylist of endorsements.

		//pick the first account in the list and assume it's the most endorsed
		Account mostEndorsedAccount = allAccounts.get(0);
		int endorseTotal;

		for (Account i : allAccounts) {
			//need to count up the endorsements on each post
			endorseTotal = 0;
			int mostEndorsements = 0;
			//for each of this user's posts, count and sum the endorsements they have
			for (Post j : i.getPosts()) {
				endorseTotal += j.getEndorsements().size();
			}
			//if this total is bigger than the current most, update most endorsed account
			if (endorseTotal > mostEndorsements) {
				mostEndorsedAccount = i;
				mostEndorsements = endorseTotal;
			}
		}

		return mostEndorsedAccount.getID();
	}

	@Override
	public void erasePlatform() {
		allAccounts.clear();
		allPosts.clear();
		counter = 0;
	}

	@Override
	public void savePlatform(String filename) throws IOException {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			ArrayList<Object> output = new ArrayList<>();
			output.add(allAccounts);
			output.add(allPosts);
			objectOut.writeObject(output);
			objectOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		ArrayList<Object> read = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			read = (ArrayList<Object>) objectIn.readObject();
			objectIn.close();
			allAccounts = (ArrayList<Account>) read.get(0);
			allPosts = (ArrayList<Post>) read.get(1);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void testWorks() {

		System.out.println("\ntotal number of posts: " + allPosts.size());
		System.out.println("\n\nAll Posts:\n\n");

		for (Post i : allPosts) {
			try {
                System.out.println(showIndividualPost(i.getID()));
            } catch (PostIDNotRecognisedException e) {
                e.printStackTrace();
            }
		}


		System.out.println("\nAll Accounts:\n");

		for (Account i : allAccounts) {
			System.out.println(i.getHandle());
		}

	}

}
