package com.tricon.poc.paginationusingtypes.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tricon.poc.paginationusingtypes.modal.Book;
import com.tricon.poc.paginationusingtypes.repository.IBookRepository;


@Component
public class MutationResolver implements GraphQLMutationResolver {

	
	@Autowired
	IBookRepository bookRepository;
	public Book createBook(String isn, String title, String publisher, String authors, String publishedDate) {
		return bookRepository.save(new Book(isn,title, publisher, authors, publishedDate));
	}
}
