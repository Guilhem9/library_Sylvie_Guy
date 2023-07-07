package com.library.libraryapp.controller;

import com.library.libraryapp.model.Document;
import com.library.libraryapp.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

//    @Autowired
//    private DocumentService documentService;
//
//    @GetMapping
//    public List<Document> getAllDocuments() {
//        return documentService.getAllDocuments();
//    }
//
//    @GetMapping("/{id}")
//    public Document getDocumentById(@PathVariable Long id) {
//        return documentService.getDocumentById(id);
//    }
//
//    @PostMapping
//    public Document createDocument(@RequestBody Document document) {
//        return documentService.createDocument(document);
//    }
//
//    @PutMapping
//    public Document updateDocument(@RequestBody Document document) {
//        return documentService.updateDocument(document);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteDocument(@PathVariable Long id) {
//        documentService.deleteDocument(id);
//    }
}