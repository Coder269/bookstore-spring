package com.abbas.bookstore.model;


import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "items_id"))
    private List<Book> items;

}
