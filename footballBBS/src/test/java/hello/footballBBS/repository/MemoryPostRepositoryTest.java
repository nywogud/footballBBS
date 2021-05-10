//package hello.footballBBS.repository;
//
//import hello.footballBBS.domain.Post;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Optional;
//
//
//class MemoryPostRepositoryTest {
//
//    PostRepository postRepository = new MemoryPostRepository();
//
//    @Test
//    void save() {
//        Post post = new Post();
//        post.setWriter("writer");
//        post.setTitle("title");
//        post.setContent("content");
//
//        postRepository.save(post);
//
//        Post result =
//                postRepository.findByContentNumber(post.getContentNumber()).get();
//
//        Assertions.assertThat(post).isEqualTo(result);
//    }
//
//    @Test
//    void findAll() {
//
//        Post post1 = new Post();
//        post1.setWriter("writer1");
//        post1.setTitle("title1");
//        post1.setContent("content1");
//        postRepository.save(post1);
//
//        Post post2 = new Post();
//        post2.setWriter("writer2");
//        post2.setTitle("title2");
//        post2.setContent("content2");
//        postRepository.save(post2);
//
//        List<Post> result = postRepository.findAll();
//
//        Assertions.assertThat(result.size()).isEqualTo(2);
//    }
//}