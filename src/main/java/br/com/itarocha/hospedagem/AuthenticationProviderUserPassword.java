package br.com.itarocha.hospedagem;

import br.com.itarocha.hospedagem.teste.UsersStore;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Inject
    UsersStore store;

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest req) {
        String username = req.getIdentity().toString();
        String password = req.getSecret().toString();
        // Buscar no banco de dados através de um serviço
        if (password.equals(store.getUserPassword(username))) {
            //return Flowable.just(new UserDetails((String) req.getIdentity(), new ArrayList<>()));
            //UserDetails details = new UserDetails(username, Collections.singletonList(store.getUserRole(username)));

            EmailUserDetails detalhes = new EmailUserDetails(username, "Raimundo Nonato", Collections.singletonList(store.getUserRole(username)), "sherlock@micronaut.example");

            return Flowable.just(detalhes);

            //return Flowable.just(new EmailUserDetails("sherlock", Collections.emptyList(), "sherlock@micronaut.example"));
        } else {
            return Flowable.just(new AuthenticationFailed());
        }
       /*
        if (req.getIdentity().equals("user") && req.getSecret().equals("password")) {
            return Flowable.just(new UserDetails("user", new ArrayList<>()));
        }
        return Flowable.just(new AuthenticationFailed());
        */
    }
}