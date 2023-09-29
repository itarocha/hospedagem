package br.com.itarocha.hospedagem.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

@Controller("/example")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ExampleController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/admin")
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public String withroles() {
        return "You have ROLE_ADMIN or ROLE_X roles";
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/anonymous")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public String anonymous() {
        return "You are anonymous";
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/authenticated")
    public String authenticated(Authentication authentication) {
        return authentication.getName() + " is authenticated";
    }
}