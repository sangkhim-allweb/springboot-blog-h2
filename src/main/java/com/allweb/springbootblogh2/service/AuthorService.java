package com.allweb.springbootblogh2.service;

import com.allweb.springbootblogh2.exception.BadRequestException;
import com.allweb.springbootblogh2.exception.DataNotFoundException;
import com.allweb.springbootblogh2.model.entity.Author;
import com.allweb.springbootblogh2.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        return authorList;
    }

    public Author getById(Long id) {
        Author author =
                authorRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new DataNotFoundException(
                                                MessageFormat.format("Author id {0} not found", String.valueOf(id))));
        return author;
    }

    public Author createOrUpdate(Author authorRequest) {
        Optional<Author> existingAuthor = authorRepository.findById(authorRequest.getId());

        if (existingAuthor.isPresent()) {
            Author authorUpdate = existingAuthor.get();

            authorUpdate.setName(authorRequest.getName());

            return authorRepository.save(authorUpdate);
        } else {
            return authorRepository.save(authorRequest);
        }
    }

    public void deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            throw new BadRequestException("Delete error, please check ID and try again");
        }
    }
}
