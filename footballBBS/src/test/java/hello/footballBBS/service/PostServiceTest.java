//package hello.footballBBS.service;
//
//import hello.footballBBS.domain.Post;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PostServiceTest {
//
//    PostService postService = new PostService();
//
//    @Test
//    void 게시글_등록() {
//        Post post = new Post();
//        post.setWriter("writer");
//        post.setTitle("title");
//        post.setContent("content");
//
//        Long contentNumber = postService.regPost(post);
//
//        Post findPost = postService.findOne(contentNumber).get();
//        Assertions.assertThat(post.getContent()).isEqualTo(findPost.getContent());
//    }
//
//
//
//    @Test
//    void findPosts() {
//    }
//
//    @Test
//    void findOne() {
//    }
//}