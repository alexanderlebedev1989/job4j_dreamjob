package ru.job4j.store;

import ru.job4j.model.Candidate;
import ru.job4j.model.Post;

import java.util.Collection;

public interface Store {

    Collection<Candidate> findAllCandidates();
    void saveCandidate(Candidate candidate);
    Candidate findByIdCandidate(int id);

    void savePost(Post post);
    Collection<Post> findAllPosts();
    Post findByIdPost(int id);

}
