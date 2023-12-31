package edu.fx.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import edu.fx.thrillio.dao.BookmarkDao;
import edu.fx.thrillio.entities.Book;
import edu.fx.thrillio.entities.Bookmark;
import edu.fx.thrillio.entities.Movie;
import edu.fx.thrillio.entities.User;
import edu.fx.thrillio.entities.UserBookmark;
import edu.fx.thrillio.entities.WebLink;
import edu.fx.thrillio.util.HttpConnect;
import edu.fx.thrillio.util.IOUtil;

public class BookmarkManager {
    private static BookmarkManager instance = new BookmarkManager();
    private static BookmarkDao dao = new BookmarkDao();

    private BookmarkManager() {
    }

    public static BookmarkManager getInstance() {
	return instance;
    }

    public WebLink createWebLink(long id, String title, String url,
	    String host) {
	WebLink weblink = new WebLink();
	weblink.setId(id);
	weblink.setTitle(title);
	weblink.setUrl(url);
	weblink.setHost(host);

	return weblink;
    }

    public Book createBook(long id, String title, int publicationYear,
	    String publisher, String[] authors, String genre,
	    double amazonRating) {
	Book book = new Book();
	book.setId(id);
	book.setTitle(title);
	book.setPublicationYear(publicationYear);
	book.setPublisher(publisher);
	book.setAuthors(authors);
	book.setGenre(genre);
	book.setAmazonRating(amazonRating);

	return book;
    }

    public Movie createMovie(long id, String title, String profileUrl,
	    int releaseYear, String[] cast, String[] directors, String genre,
	    double imdbRating) {
	Movie movie = new Movie();
	movie.setId(id);
	movie.setTitle(title);
	movie.setProfileUrl(profileUrl);
	movie.setReleaseYear(releaseYear);
	movie.setCast(cast);
	movie.setDirectors(directors);
	movie.setGenre(genre);
	movie.setImdbRating(imdbRating);

	return movie;

    }

    public Bookmark[][] getBookmarks() {
	return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
	UserBookmark userBookmark = new UserBookmark();
	userBookmark.setUser(user);
	userBookmark.setBookmark(bookmark);

	if (bookmark instanceof WebLink) {
	    try {
		String url = ((WebLink) bookmark).getUrl();
		if (!url.endsWith(".pdf")) {
		    String webpage = HttpConnect
			    .download(((WebLink) bookmark).getUrl());
		    if (webpage != null) {
			IOUtil.write(webpage, bookmark.getId());
		    }
		}
	    } catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, String kidFriendlyStatus,
	    Bookmark bookmark) {
	bookmark.setKidFriendlyStatus(kidFriendlyStatus);
	bookmark.setKidFriendlyMarkedBy(user);

	System.out.println("Kid-friendly status: " + kidFriendlyStatus
		+ ", Marked by: " + user.getEmail() + ", " + bookmark);
    }

    public void share(User user, Bookmark bookmark) {
	bookmark.setSharedBy(user);

	System.out.println("Data to be shared: ");
	if (bookmark instanceof Book) {
	    System.out.println(((Book) bookmark).getItemData());
	} else if (bookmark instanceof WebLink) {
	    System.out.println(((WebLink) bookmark).getItemData());
	}

    }

}
