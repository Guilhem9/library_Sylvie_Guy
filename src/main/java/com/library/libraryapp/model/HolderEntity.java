package com.library.libraryapp.model;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
public abstract class HolderEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holderEntity")
    private List<Document> documentsHeld;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "possessorEntity")
    private List<Document> documentsPossessed;

    // Default constructor
    public HolderEntity() {
        this.documentsHeld = new ArrayList<>();
        this.documentsPossessed = new ArrayList<>();
    }
    public HolderEntity(List<Document> documentsHeld, List<Document> documentsPossessed) {
        this.documentsHeld = documentsHeld;
        this.documentsPossessed = documentsPossessed;
    }

    // Getter methods for private fields

    public Long getId() {
        return this.id;
    }

    public List<Document> getDocumentsHeld() {
        return this.documentsHeld;
    }

    public List<Document> getDocumentsPossessed() {
        return this.documentsPossessed;
    }

    // Add setter methods if necessary
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
