package br.com.itarocha.hospedagem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {

    public Book() {}

    public Book(@NotNull String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("id=");
        sb.append(id);
        sb.append(", name='");
        sb.append(name);
        sb.append("'}");
        return sb.toString();
    }
}