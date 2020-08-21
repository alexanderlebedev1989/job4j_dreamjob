package ru.job4j.store;

import ru.job4j.model.Candidate;
import ru.job4j.model.Post;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void savePost(Post post);

    Post findByIdPost(int id);

    void saveCandidate(Candidate candidate);

    Candidate findByIdCandidate(int id);
}
