package com.ash1.restaip.bootrest.controllers;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller; /* This is also for @Controller */

import org.springframework.web.bind.annotation.*;

import com.ash1.restaip.bootrest.entity.Books;
import com.ash1.restaip.bootrest.services.BookService;;

@RestController
//@Controller
public class BookControler {

   // @RequestMapping(value = "/books", method = RequestMethod.GET) Isi place per direct @GetMapping use krenge
   // @ResponseBody /* jb @Controller use krte hai , Tb @ResponseBody lgana pdega  */
    
    private BookService bookService= new BookService();

    // get all books
   @GetMapping("/books")
    public List<Books> getBooks(){

        return this.bookService.getAlBooks();
    }

    //get single book by id
    @GetMapping("/books/{id}")
    public Books getBook(@PathVariable("id")int id){
        return bookService.getBookById(id);
    }
    // adding new book
    @PostMapping("/books")
    public Books addBook(@RequestBody Books book){
         this.bookService.addBook(book);
         return book;

    } 
    // delete book by id
    @DeleteMapping("/books/{bookId}")
     public void deleBook(@PathVariable("bookId") int bookId){
        this.bookService.deleteBook(bookId);

     }

     // update book
     @PutMapping("/books/{bookId}")
     public Books updateBook(@RequestBody Books book,@PathVariable("bookId") int bookId){
        this.bookService.updateBook(book,bookId);
        return book;
     }

}
