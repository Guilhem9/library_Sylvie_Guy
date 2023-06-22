package com.library.libraryapp.model;
public abstract class HolderEntity {

}

/*
import javax.persistence.*;
import java.util.HashMap;


@Entity
@Table(name = "holder_entities")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "holder_entity_id")
    private HashMap<String, Document> documentsHeld;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "holder_entity_id")
    private HashMap<String, Document> documentsPossessed;

    // Constructors, getters, setters, and methods...
}*/
