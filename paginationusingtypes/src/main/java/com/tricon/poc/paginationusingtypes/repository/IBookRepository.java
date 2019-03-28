package com.tricon.poc.paginationusingtypes.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tricon.poc.paginationusingtypes.modal.Book;

public interface IBookRepository extends MongoRepository<Book, String>,PagingAndSortingRepository<Book, String>{
	public List<Book> findAllById(String id,Pageable pageable);
	public List<Book> findBookByTitle(String title,Pageable pageable);
	public Page<Book> findBookByPublisher(String publisher,Pageable pageable);
	public List<Book> findBookByPublisherAndAuthors(String publisher, String authors);
	public Page<Book> findBookByPublisherAndAuthors(String publisher, String author,Pageable pageable);
}
