package com.ash1.restaip.bootrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ash1.restaip.bootrest.entity.Books;
@Component
public class BookService {
    private static List<Books> list= new ArrayList<>();
    
    static{
        list.add(new Books(2, "book2", "ABC"));
        list.add(new Books(3, "book3", "ABD"));
    }

    // get All books
    public List<Books> getAlBooks(){
        return list;
    }

    // get single book by id 

    public Books getBookById(int id){
       Books book=null;
       book= list.stream().filter(e->e.getId()==id).findFirst().get();
       return book;
    }

    //  adding the book
    public void addBook(Books b){
        list.add(b);
    }

    // delete book by id
    public void deleteBook(int bookId){
       list = list.stream().filter(book->{
            if(book.getId()!=bookId){
                return true;
            }else {
                return false;
            }
        }).collect(Collectors.toList());
    }

    //update the book

    public void updateBook(Books book,int bookId){
        list=list.stream().map(b->{
            if(b.getId()==bookId){
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
    } 
}
 