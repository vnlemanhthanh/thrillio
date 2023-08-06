package edu.fx.thrillio.managers;

import edu.fx.thrillio.dao.BookmarkDao;
import edu.fx.thrillio.entities.Book;
import edu.fx.thrillio.entities.Bookmark;
import edu.fx.thrillio.entities.Movie;
import edu.fx.thrillio.entities.User;
import edu.fx.thrillio.entities.UserBookmark;
import edu.fx.thrillio.entities.WebLink;

public class BookmarkManager {
    private static  BookmarkManager instance = new BookmarkManager();
    private static BookmarkDao dao = new BookmarkDao();
    private BookmarkManager() {
    }

    public static  BookmarkManager getInstance() {
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
	    String publisher, String[] authors, String genre, double amazoneRating) {
	Book book = new Book();
	book.setId(id);
	book.setTitle(title);
	book.setPublicationYear(publicationYear);
	book.setPublisher(publisher);
	book.setAuthors(authors);
	book.setGenre(genre);
	book.setAmazoneRating(amazoneRating);

	return book;
    }

    public Movie createMovie(long id, String title, String profileUrl,
	    int releaseYear, String[] cast, String[] drectors, String genre,
	    double imdbRating) {
	Movie movie = new Movie();
	movie.setId(id);
	movie.setTitle(title);
	movie.setProfileUrl(profileUrl);
	movie.setReleaseYear(releaseYear);
	movie.setCast(cast);
	movie.setDirectors(drectors);
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
	
	dao.saveUserBookmark(userBookmark);
	
    }
}

















