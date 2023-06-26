package com.example.demo.post;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDaoService {
    private static List<Post> posts = new ArrayList<>();
    private static int idCounter = 6;

    static {
        posts.add(new Post(1, 1, "Hello mom", "This is a message to my mom :)"));
        posts.add(new Post(2, 2, "Java is awesome", "I am trying to build a server on java :S"));
        posts.add(new Post(3, 1, "Eid mubarak!", "Eid mubarak to everyone <3"));
        posts.add(new Post(4, 2, "Hello World", "This is hello world from spring boot!"));
        posts.add(new Post(5, 1, "JS vs Java?", "Who will win? Java or Javascript?"));
        posts.add(new Post(6, 1, "Du Bliest!", "I am German ... haha :D"));
    }

    public List getAllPosts() {
        return posts;
    }

    public List getPostsOfUser(int userId) {
        List<Post> userPosts = new ArrayList<>();
        posts.forEach(post -> {
            if (post.getUserId() == userId) userPosts.add(post);
        });
        return userPosts;
    }

    public List addPostOfUser(Post post) {
        if (post.getId() == null) {
            this.idCounter++;
            post.setId(idCounter);
        }
        posts.add(post);
        return this.getPostsOfUser(post.getUserId());
    }

    public Post getASinglePostOfUser(int postId, int userId) {
        return posts.stream().filter(post -> post.getId() == postId && post.getUserId() == userId).findFirst().orElse(null);
    }
}
