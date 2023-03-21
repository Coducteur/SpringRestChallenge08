package cours.springRest.controller;

import cours.springRest.entity.Book;
import cours.springRest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getListOfAllBooks(){
        return bookRepository.findAll();
    }
    @GetMapping("/books/{id}")
    public Book showBook(@PathVariable int id){
        return bookRepository.findById(id).get();
    }
    @PostMapping("/books")
    public Book createNewBook(@RequestBody Book newbook){
        return bookRepository.save(newbook);
    }
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id,@RequestBody Book book){
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }
    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }

}
