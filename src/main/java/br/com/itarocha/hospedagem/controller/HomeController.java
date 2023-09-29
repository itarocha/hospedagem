package br.com.itarocha.hospedagem.controller;

import br.com.itarocha.hospedagem.model.Book;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;

import javax.annotation.Nullable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Secured("isAuthenticated()")
@Controller("/")
public class HomeController {

    @Get("/")
    public Map<String, Object> index(@Nullable Principal principal){
        //return new Book("O Livro do Tesouro"); //principal.getName();
        Map<String, Object> data = new HashMap<>();
        data.put("loggedIn", principal!=null);
        if (principal!=null) {
            data.put("username", principal.getName());
        }
        return data;
    }
}
