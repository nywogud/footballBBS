package hello.footballBBS.service;

import hello.footballBBS.domain.Post;
import hello.footballBBS.repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //게시글 등록
    public Long regPost(Post post) {
        postRepository.save(post);
        return post.getContentNumber();
    }

    public Long modifyPost(Post post){
        postRepository.modifyPost(post);
        return post.getContentNumber();
    }


    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Post> findOne(Long contentNumber) {
        return postRepository.findByContentNumber(contentNumber);
    }

    public Long findContentNumber(String writer, String title, String content){

        return postRepository.findContentNumber(writer, title, content);
    }

}
