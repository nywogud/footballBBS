package hello.footballBBS.repository;

import hello.footballBBS.domain.Post;

import java.util.Date;
import java.util.List;

public interface PostRepository {

    void save(Post post);

    public Long findContentNumber(String writer, String title, String content);

    void modifyPost(Post post);

    List<Post> findByContentNumber(Long contentNumber);

    List<Post> findAll();

}
