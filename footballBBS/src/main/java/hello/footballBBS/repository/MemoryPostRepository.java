//package hello.footballBBS.repository;
//
//import hello.footballBBS.domain.Post;
//import java.util.*;
//
//public class MemoryPostRepository implements PostRepository {
//
//    private static Map<Long, Post> store = new HashMap<>();
//    private static Long sequence = 0L;
//
//    @Override
//    public void save(Post post) {
//
//        post.setContentNumber(++sequence);
//        store.put(post.getContentNumber(), post);
//
//    }
//
//    public Long findContentNumber(Post post){
//        return null;
//    }
//
//    @Override
//    public void modifyPost(Post post) {
//
//    }
//
//    @Override
//    public List<Post> findByContentNumber(Long contentNumber) {
//        return null;
//    }
//
//    @Override
//    public List<Post> findAll() {
//        return new ArrayList<>(store.values());
//    }
//}
