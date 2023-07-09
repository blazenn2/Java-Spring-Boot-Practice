package com.example.demo.post;

import com.example.demo.user.UserJdbcDaoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostJdbcDaoService {
    private final JdbcTemplate jdbcTemplate;

    public PostJdbcDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static Post mapRow(ResultSet rs, int rowNumber) throws SQLException {
        return new Post(rs.getInt("id"), rs.getInt("user_id"), rs.getString("post_title"), rs.getString("post_message"));
    }

    public List<Post> getAllPosts() {
        String sql = "SELECT * FROM posts";
        List<Post> posts = jdbcTemplate.query(sql, PostJdbcDaoService::mapRow);
        return posts;
    }

    public List<Post> getPostsOfUser(int userId) {
        String sql = "SELECT * FROM posts WHERE user_id=?";
        List<Post> userPosts = jdbcTemplate.query(sql, new Object[]{userId}, PostJdbcDaoService::mapRow);
        return userPosts;
    }

    public List addPostOfUser(Post post) {
        String sql = "INSERT INTO posts(user_id, post_title, post_message) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, post.getUserId(), post.getPostTitle(), post.getPostMessage());
        List<Post> postsOfUser = this.getPostsOfUser(post.getUserId());
        return postsOfUser;
    }

    public Post getASinglePostOfUser(int postId, int userId) {
        String sql;
        List<Post> post;
        if (userId == -1) {
            sql = "SELECT * FROM posts WHERE id=?";
             post = jdbcTemplate.query(sql, new Object[]{postId}, PostJdbcDaoService::mapRow);
        }
        else {
            sql = "SELECT * FROM posts WHERE id=? AND user_id=?";
            post = jdbcTemplate.query(sql, new Object[]{postId, userId}, PostJdbcDaoService::mapRow);
        }
        if (post.isEmpty()) {
            return null;
        }
        return post.get(0);
    }

    public Post findASinglePost(int postId) {
        return this.getASinglePostOfUser(postId, -1);
    }


}
