package ca.mcgill.ecse321.petshelter.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.petshelter.model.AdoptionApplication;
import ca.mcgill.ecse321.petshelter.model.Advertisement;
import ca.mcgill.ecse321.petshelter.model.Comment;
import ca.mcgill.ecse321.petshelter.model.Donation;
import ca.mcgill.ecse321.petshelter.model.Forum;
import ca.mcgill.ecse321.petshelter.model.Gender;
import ca.mcgill.ecse321.petshelter.model.Pet;
import ca.mcgill.ecse321.petshelter.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class PetShelterPersistence {
	@Autowired
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private DonationRepository donationRepository;
	@Autowired
	private ForumRepository forumRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		advertisementRepository.deleteAll();
		applicationRepository.deleteAll();
		commentRepository.deleteAll();
		donationRepository.deleteAll();
		forumRepository.deleteAll();
		userRepository.deleteAll();
		petRepository.deleteAll();

	}

	@BeforeEach
	public User createUser() {
		String name = "TestUserName";
		String password = "myPassword";
		boolean emailValid = true;
		String email = "TestUserName@gmail.com";
		String apiToken = "token112";

		User user = new User();
		user.setUserName(name);
		user.setPassword(password);
		user.setIsEmailValidated(emailValid);
		user.setEmail(email);
		user.setApiToken(apiToken);

		userRepository.save(user);
		return user;
	}

	@BeforeEach
	public Advertisement createAdvertisement() {
		String description = "myDescription";
		String title = "myTitle";
		// long id = 12345;
		boolean isFulfiled = true;

		Advertisement advertisement = new Advertisement();

		advertisement.setDescription(description);
		// advertisement.setId(id);
		advertisement.setIsFulfilled(isFulfiled);
		advertisement.setTitle(title);

		advertisementRepository.save(advertisement);
		return advertisement;
	}

	@BeforeEach
	public Comment createComment() {
		Date postedDate = Date.valueOf("2015-03-21");
		String commentText = "this is a comment";
		Time time = Time.valueOf("10:22:03");

		User user = createUser();
		Comment comment = new Comment();

		comment.setDatePosted(postedDate);
		comment.setText(commentText);
		comment.setTime(time);
		comment.setUser(user);

		commentRepository.save(comment);
		return comment;
	}

	@Test
	public void testWriteUser() {
		String name = "TestUserName";
		String password = "myPassword";
		boolean emailValid = true;
		String email = "TestUserName@gmail.com";
		String apiToken = "token112";

		User user = new User();

		// sets everything
		user.setUserName(name);
		user.setPassword(password);
		user.setIsEmailValidated(emailValid);
		user.setEmail(email);
		user.setApiToken(apiToken);

		userRepository.save(user);

		user = null;

		user = userRepository.findUserByUserName(name);
		assertNotNull(user);
		assertEquals(name, user.getUserName());
		assertEquals(password, user.getPassword());
		assertEquals(emailValid, user.isIsEmailValidated());
		assertEquals(apiToken, user.getApiToken());
	}

	@Test
	public void testReadUser() {
		String name = "TestUserName";
		String password = "myPassword";
		boolean emailValid = true;
		String email = "TestUserName@gmail.com";
		String apiToken = "token112";

		User user = new User();

		// sets everything
		user.setUserName(name);
		user.setPassword(password);
		user.setIsEmailValidated(emailValid);
		user.setEmail(email);
		user.setApiToken(apiToken);

		userRepository.save(user);

		user = null;

		user = userRepository.findUserByUserName(name);
		assertNotNull(user);
		assertEquals(name, user.getUserName());
		assertEquals(password, user.getPassword());
		assertEquals(emailValid, user.isIsEmailValidated());
		assertEquals(apiToken, user.getApiToken());
	}

	@Test
	public void testWriteComment() {
		Date postedDate = Date.valueOf("2015-03-21");
		String commentText = "this is a comment";
		Time time = Time.valueOf("10:22:03");

		User user = createUser();
		Comment comment = new Comment();

		comment.setDatePosted(postedDate);
		comment.setText(commentText);
		comment.setTime(time);
		comment.setUser(user);

		commentRepository.save(comment);

		comment = null;
		comment = commentRepository.findCommentByUserAndText(user, commentText);

		assertNotNull(comment);
		assertEquals(commentText, comment.getText());

	}

	@Test
	public void testReadComment() {
		Date postedDate = Date.valueOf("2015-03-21");
		String commentText = "this is a comment";
		Time time = Time.valueOf("10:22:03");

		User user = createUser();
		Comment comment = new Comment();

		comment.setDatePosted(postedDate);
		comment.setText(commentText);
		comment.setTime(time);
		comment.setUser(user);

		commentRepository.save(comment);

		comment = null;
		comment = commentRepository.findCommentByUserAndText(user, commentText);

		assertNotNull(comment);
		assertEquals(commentText, comment.getText());

	}

	@Test
	public void testWriteAdvertisement() {
		String description = "myDescription";
		String title = "myTitle";
		boolean isFulfiled = true;

		Advertisement advertisement = new Advertisement();

		advertisement.setDescription(description);
		advertisement.setIsFulfilled(isFulfiled);
		advertisement.setTitle(title);

		advertisementRepository.save(advertisement);
		advertisement = advertisementRepository.findAdvertisementByTitle(title);

		assertNotNull(advertisement);
		assertEquals(title, advertisement.getTitle());
		assertEquals(description, advertisement.getDescription());
		assertEquals(isFulfiled, advertisement.isIsFulfilled());
	}

	@Test
	public void testReadAdvertisement() {
		String description = "myDescription";
		String title = "myTitle";
		boolean isFulfiled = true;

		Advertisement advertisement = new Advertisement();

		advertisement.setDescription(description);
		advertisement.setIsFulfilled(isFulfiled);
		advertisement.setTitle(title);

		advertisementRepository.save(advertisement);
		advertisement = advertisementRepository.findAdvertisementByTitle(title);

		assertNotNull(advertisement);
		assertEquals(title, advertisement.getTitle());
		assertEquals(description, advertisement.getDescription());
		assertEquals(isFulfiled, advertisement.isIsFulfilled());
	}

	@Test
	public void testWriteApplication() {
		User applicant = createUser();
		String description = "myDescription";

		Advertisement ad = new Advertisement();
		advertisementRepository.save(ad);
		boolean isAccepted = false;

		AdoptionApplication application = new AdoptionApplication();
		application.setIsAccepted(isAccepted);
		application.setAdvertisement(ad);
		application.setUser(applicant);
		application.setDescription(description);

		applicationRepository.save(application);
		application = applicationRepository.findApplicationByUserAndAdvertisement(applicant, ad);

		assertNotNull(application);

		assertEquals(applicant.getUserName(), application.getUser().getUserName());
		assertEquals(ad.getTitle(), application.getAdvertisement().getTitle());
		assertEquals(description, application.getDescription());
		assertEquals(isAccepted, application.isIsAccepted());
	}

	@Test
	public void testReadApplication() {
		User applicant = createUser();
		String description = "myDescription";

		Advertisement ad = new Advertisement();
		advertisementRepository.save(ad);
		boolean isAccepted = false;

		AdoptionApplication application = new AdoptionApplication();
		application.setIsAccepted(isAccepted);
		application.setAdvertisement(ad);
		application.setUser(applicant);
		application.setDescription(description);

		applicationRepository.save(application);
		application = applicationRepository.findApplicationByUserAndAdvertisement(applicant, ad);

		assertNotNull(application);

		assertEquals(applicant.getUserName(), application.getUser().getUserName());
		assertEquals(ad.getTitle(), application.getAdvertisement().getTitle());
		assertEquals(description, application.getDescription());
		assertEquals(isAccepted, application.isIsAccepted());
	}

	@Test
	public void testWritePet() {
		// user info
		User user = createUser();

		// pet info
		String petName = "TestPetName";
		Date birthDate = Date.valueOf("2015-03-31");
		String species = "Dog";
		String breed = "Labrador";
		Pet pet = new Pet();
		pet.setDateOfBirth(birthDate);
		pet.setName(petName);
		pet.setSpecies(species);
		pet.setBreed(breed);
		pet.setGender(Gender.FEMALE);
		HashSet<Pet> pets = new HashSet<Pet>();
		user.setPets(pets);
		userRepository.save(user);
		petRepository.save(pet);

		pet = null;

		pet = petRepository.findPetByName(petName);
		assertNotNull(pet);
		assertEquals(petName, pet.getName());

	}

	@Test
	public void testReadPet() {
		// user info
		User user = createUser();

		// pet info
		String petName = "TestPetName";
		Date birthDate = Date.valueOf("2015-03-31");
		String species = "Dog";
		String breed = "Labrador";
		Pet pet = new Pet();
		pet.setDateOfBirth(birthDate);
		pet.setName(petName);
		pet.setSpecies(species);
		pet.setBreed(breed);
		pet.setGender(Gender.FEMALE);
		HashSet<Pet> pets = new HashSet<Pet>();
		user.setPets(pets);
		userRepository.save(user);
		petRepository.save(pet);

		pet = null;

		pet = petRepository.findPetByName(petName);
		assertNotNull(pet);
		assertEquals(petName, pet.getName());

	}

	@Test
	public void testWriteDonation() {

		User user = createUser();
		Date donationDate = Date.valueOf("2015-03-31");
		Time donationTime = Time.valueOf("10:22:03");
		double amount = 122.2;

		Donation donation = new Donation();

		donation.setAmount(amount);
		donation.setDate(donationDate);
		donation.setTime(donationTime);
		donation.setUser(user);

		// userRepository.save(user);
		donationRepository.save(donation);

		donation = null;

		donation = donationRepository.findDonationByUserAndAmount(user, amount);
		assertNotNull(donation);
		assertEquals(donationDate, donation.getDate());
		// TODO: check why it doesnt work.
		// we need something to check that the username are unique in the db.
		assertEquals(user.getUserName(), donation.getUser().getUserName());

		assertEquals(user.getUserName(), donation.getUser().getUserName());
		assertEquals(amount, donation.getAmount(), 0.01);

	}

	@Test
	public void testReadDonation() {

		User user = createUser();
		Date donationDate = Date.valueOf("2015-03-31");
		Time donationTime = Time.valueOf("10:22:03");
		double amount = 122.2;

		Donation donation = new Donation();

		donation.setAmount(amount);
		donation.setDate(donationDate);
		donation.setTime(donationTime);
		donation.setUser(user);

		// userRepository.save(user);
		donationRepository.save(donation);

		donation = null;

		donation = donationRepository.findDonationByUserAndAmount(user, amount);
		assertNotNull(donation);
		assertEquals(donationDate, donation.getDate());
		// TODO: check why it doesnt work.
		// we need something to check that the username are unique in the db.
		assertEquals(user.getUserName(), donation.getUser().getUserName());

		assertEquals(user.getUserName(), donation.getUser().getUserName());
		assertEquals(amount, donation.getAmount(), 0.01);

	}

	@Test
	public void testReadForum() {
		String title = "myTitle";
		// lazy way of getting out but it will work because we are creating new objects
		// everytime
		Comment comment1 = createComment();
		Comment comment2 = createComment();
		Comment comment3 = createComment();
		Set<Comment> commentSet = new HashSet<>();
		commentSet.add(comment1);
		commentSet.add(comment2);
		commentSet.add(comment3);

		User user1 = createUser();
		User user2 = createUser();
		HashSet<User> userSet = new HashSet<>();
		userSet.add(user1);
		userSet.add(user2);

		Forum forum = new Forum();
		forum.setComments(commentSet);
		forum.setSubscribers(userSet);
		forum.setTitle(title);

		forumRepository.save(forum);

		forum = null;

		forum = forumRepository.findForumByTitle(title);
		assertNotNull(forum);
		for (User user : userSet) {
			assertTrue(forum.getSubscribers().contains(user));
		}
		for (Comment comment : commentSet) {
			assertTrue(forum.getComments().contains(comment));
		}
		assertEquals(title, forum.getTitle());
	}
	
	@Test
	public void testWriteForum() {
		String title = "myTitle";
		// lazy way of getting out but it will work because we are creating new objects
		// everytime
		Comment comment1 = createComment();
		Comment comment2 = createComment();
		Comment comment3 = createComment();
		Set<Comment> commentSet = new HashSet<>();
		commentSet.add(comment1);
		commentSet.add(comment2);
		commentSet.add(comment3);

		User user1 = createUser();
		User user2 = createUser();
		HashSet<User> userSet = new HashSet<>();
		userSet.add(user1);
		userSet.add(user2);

		Forum forum = new Forum();
		forum.setComments(commentSet);
		forum.setSubscribers(userSet);
		forum.setTitle(title);

		forumRepository.save(forum);

		forum = null;

		forum = forumRepository.findForumByTitle(title);
		assertNotNull(forum);
		for (User user : userSet) {
			assertTrue(forum.getSubscribers().contains(user));
		}
		for (Comment comment : commentSet) {
			assertTrue(forum.getComments().contains(comment));
		}
		assertEquals(title, forum.getTitle());
	}

}