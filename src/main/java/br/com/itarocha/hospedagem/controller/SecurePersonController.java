package br.com.itarocha.hospedagem.controller;

import br.com.itarocha.hospedagem.model.Book;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller("/livros")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SecurePersonController {

    List<Book> persons = new ArrayList<>();

    public SecurePersonController(){
        persons.add(new Book("Livro Alfa"));
        persons.add(new Book("Livro Beta"));
        persons.add(new Book("Livro Gama"));
        persons.add(new Book("Livro Epslon"));
    }

    @Post
    @Secured("ADMIN")
    public Book add(@Body @Valid Book person) {
        //person.setId(persons.size() + 1);
        persons.add(person);
        return person;
    }

    @Get("/{id:4}")
    public Optional<Book> findById(@NotNull Integer id) {
        return persons.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Get("/")
    public List<Book> findAll() {
        return persons.stream()
                //.skip(offset == null ? 0 : offset)
                //.limit(max == null ? 10000 : max)
                .collect(Collectors.toList());
    }

}