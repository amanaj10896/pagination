package com.tricon.poc.paginationusingtypes.resolvers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tricon.poc.paginationusingtypes.exception.BookNotFoundException;
import com.tricon.poc.paginationusingtypes.modal.Book;
import com.tricon.poc.paginationusingtypes.modal.Data;
import com.tricon.poc.paginationusingtypes.repository.IBookRepository;


@Component
public class QueryResolver implements GraphQLQueryResolver{


	private static final Logger logger = LoggerFactory.getLogger(QueryResolver.class);
	@Autowired
	IBookRepository bookRepository;
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	public List<Book> getAllBooksPaginated(String id, int skip,int first){
		logger.info("List of all books with id :{} was asked", id);
		
		List<Book> books=bookRepository.findAllById(id,PageRequest.of(skip, first));
//		if (books == null) {
//			throw new BookNotFoundException("Book with id" + id +"not found");
//		}
		return books;
}
	
	public List<Book> getBookByTitle(String title, int skip,int first) {
		logger.info("List of books by using only title:{} was asked",title);
		return bookRepository.findBookByTitle(title,PageRequest.of(skip, first));
	}
	
	public Page<Book> getBookByPublisherSorted(String publisher,Number skip,Number first, String sort, String keytosort){
		if(publisher==null) {
		logger.info("List of sorted books were asked without particular publisher");
		return bookRepository.findAll(PageRequest.of(skip.intValue(), first.intValue(),Direction.valueOf(sort), keytosort ));
		}else
		{
			logger.info("List of sorted books were asked with particular publisher : {}",publisher);

			return bookRepository.findBookByPublisher(publisher, PageRequest.of(skip.intValue(), first.intValue(),Direction.valueOf(sort), keytosort ));
		}
	}
	
	public List<Book> getBookByPublisherAndAuthor(Data data){
		logger.info("List of books on basis of an input object:{}",data.getPublisher(),data.getAuthors());
		return bookRepository.findBookByPublisherAndAuthors(data.getPublisher(),data.getAuthors());
	}
	
	public Page<Book> getBookByPublisherAndAuthorSorted(Data data,Number skip,Number first, String sort, String keytosort){
		if(data==null) {
		logger.info("List of sorted books were asked without particular data object");
		return bookRepository.findAll(PageRequest.of(skip.intValue(), first.intValue(),Direction.valueOf(sort), keytosort ));

		}
		else {
		logger.info("List of books on basis of an input object:{}",data.getPublisher(),data.getAuthors());
		return bookRepository.findBookByPublisherAndAuthors(data.getPublisher(),data.getAuthors(),PageRequest.of(skip.intValue(), first.intValue(),Direction.valueOf(sort), keytosort));
		}
	}
	
	
}
