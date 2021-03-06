package ru.job4j.store;

import ru.job4j.model.Candidate;
import ru.job4j.model.Post;
import ru.job4j.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {

    private static final MemStore INST = new MemStore();
    private final static AtomicInteger POST_ID = new AtomicInteger(4);
    private final static AtomicInteger CANDIDATE_ID = new AtomicInteger(1);
    private final static AtomicInteger USER_ID = new AtomicInteger(0);
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private Map<Integer, User> users = new ConcurrentHashMap<>();

    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
    }

    public static MemStore instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public Candidate findByIdCandidate(int id) {
        return null;
    }

    @Override
    public void savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    @Override
    public Post findByIdPost(int id) {
        return posts.get(id);
    }

    @Override
    public Collection<User> findAllUsers() {
        return null;
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    @Override
    public User findByUser(int id) {
        return users.get(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        if (users.isEmpty()) {
            return null;
        }
        User user = null;
        for (User u : users.values()) {
            if (u.getEmail().equals(email)
                    && u.getPassword().equals(password)) {
               user = u;
            }
        }
        return user;
    }
}
