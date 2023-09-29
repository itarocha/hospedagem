package br.com.itarocha.hospedagem.controller;

import br.com.itarocha.hospedagem.model.Book;
import br.com.itarocha.hospedagem.repository.BookRepository;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Validated
@Controller("/books")
public class BookController {

    protected final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @Get("/{id}")
    public Book show(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Put("/")
    public HttpResponse update(@Body @Valid Book model) {
        int numberOfEntitiesUpdated = repo.update(model.getId(), model.getName());

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION , location(model.getId()).getPath());
    }

    @Get(value = "/list")
    public List<Book> list() {
        return repo.findAll();
    }

    @Post("/")
    public HttpResponse<Book> save(@Body @Valid Book cmd) {
        Book book = repo.save(cmd.getName());

        return HttpResponse
                .created(book)
                .headers(headers -> headers.location(location(book.getId())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        repo.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/books/" + id);
    }

    protected URI location(Book book) {
        return location(book.getId());
    }

}
