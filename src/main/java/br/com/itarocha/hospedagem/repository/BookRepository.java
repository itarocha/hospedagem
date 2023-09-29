package br.com.itarocha.hospedagem.repository;

import br.com.itarocha.hospedagem.model.Book;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
public class BookRepository extends AbstractRepository{

    public BookRepository(@CurrentSession EntityManager em, ApplicationConfiguration cfg){
        super(em, cfg);
    }

    @Transactional(readOnly = true)
    public Optional<Book> findById(@NotNull Long id) {
        return Optional.ofNullable(getEm().find(Book.class, id));
    }

    @Transactional
    public Book save(@NotBlank String name) {
        Book book = new Book(name);
        getEm().persist(book);
        return book;
    }

    @Transactional
    //@Override
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(book -> getEm().remove(book.getId()));
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        String qlString = "SELECT g FROM Book as g";
        /*
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY g." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
         */
        TypedQuery<Book> query = getEm().createQuery(qlString, Book.class);
        //query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        //args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }

    @Transactional
    public int update(@NotNull Long id, @NotBlank String name) {
        return getEm().createQuery("UPDATE Book g SET name = :name where id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .executeUpdate();
    }
}
