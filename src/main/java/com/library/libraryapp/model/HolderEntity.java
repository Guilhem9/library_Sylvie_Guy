package com.library.libraryapp.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@Data
@MappedSuperclass
public abstract class HolderEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holderEntity")
    private List<Document> documentsHeld = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "possessorEntity")
    private List<Document> documentsPossessed = new ArrayList<>();
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
