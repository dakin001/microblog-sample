package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.domain.account.model.Account;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.infrastructure.repository.mybatis.PostRepositoryImpl;
import com.example.moikiitos.infrastructure.repository.mybatis.mapper.AccountMapper;
import com.example.moikiitos.infrastructure.repository.mybatis.mapper.FollowerMapper;
import com.example.moikiitos.infrastructure.repository.mybatis.mapper.PostMapper;
import com.example.moikiitos.infrastructure.repository.mybatis.mapper.UserMapper;
import com.example.moikiitos.domain.shared.PageQuery;
import com.example.moikiitos.domain.user.model.Follower;
import com.example.moikiitos.domain.user.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryImplTest {
    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    FollowerMapper followerMapper;

    PostRepositoryImpl repository;
    User user1;
    User user2;

    @BeforeAll
    void init() {
        repository = new PostRepositoryImpl(postMapper, userMapper);

        user1 = createAccount("user1");
        user2 = createAccount("user2");
    }

    @Test
    void findFeedByUserId_has1SelfPost_return1() {
        // CASE
        Post post = new Post();
        post.setUser(user1);
        post.setContent("test");
        postMapper.insert(post);

        // WHEN
        var dbPosts = repository.findFeedByUserId(user1.getId(), new PageQuery());

        // THEN
        assertEquals(1, dbPosts.size());
        assertEquals(post.getContent(), dbPosts.get(0).getContent());
        assertEquals(user1.getName(), dbPosts.get(0).getUser().getName());
    }

    @Test
    void findFeedByUserId_hasNoPost_returnEmpty() {
        // CASE
        Post post = new Post();
        post.setUser(user1);
        post.setContent("test");
        postMapper.insert(post);

        // WHEN
        var dbPosts = repository.findFeedByUserId(user2.getId(), new PageQuery());

        // THEN
        assertEquals(0, dbPosts.size());
    }

    @Test
    void findFeedByUserId_FollowingHasPost_return1() {
        // CASE
        Post post = new Post();
        post.setUser(user1);
        post.setContent("test");
        postMapper.insert(post);

        Follower follower = new Follower();
        follower.setFollower(user2);
        follower.setFollowing(user1);
        followerMapper.insert(follower);

        // WHEN
        var dbPosts = repository.findFeedByUserId(user2.getId(), new PageQuery());

        // THEN
        assertEquals(1, dbPosts.size());
    }

    @Test
    void findFeedByUserId_FollowingHasPosts_returnSecondPage() {
        // CASE
        for (int i = 1; i <= 7; i++) {
            Post post = new Post();
            post.setUser(user1);
            post.setContent("test" + i);
            postMapper.insert(post);
        }

        Follower follower = new Follower();
        follower.setFollower(user2);
        follower.setFollowing(user1);
        followerMapper.insert(follower);
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSkip(3);
        pageQuery.setLimit(2);

        // WHEN
        var dbPosts = repository.findFeedByUserId(user2.getId(), pageQuery);

        // THEN
        assertEquals(2, dbPosts.size());
        // order by desc
        assertEquals("test4", dbPosts.get(0).getContent());
    }

    User createAccount(String name) {
        Account account = new Account();
        account.setName(name);
        account.setEmail(name + "@example.com");
        accountMapper.insert(account);

        User user = new User();
        user.setId(account.getId());
        user.setName(account.getName());
        user.setEmail(account.getEmail());

        return user;
    }
}