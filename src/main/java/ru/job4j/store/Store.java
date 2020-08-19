package ru.job4j.store;

import ru.job4j.model.Candidate;
import ru.job4j.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final Store INST = new Store();
    private final static AtomicInteger POST_ID = new AtomicInteger(4);
    private final static AtomicInteger CANDIDATE_ID = new AtomicInteger(1);
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void savePost(Post post) {
        post.setId(POST_ID.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void saveCandidate(Candidate candidate) {
        candidate.setId(CANDIDATE_ID.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }
}
