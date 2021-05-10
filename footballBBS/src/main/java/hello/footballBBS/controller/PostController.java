package hello.footballBBS.controller;

import hello.footballBBS.domain.Post;
import hello.footballBBS.form.PostForm;
import hello.footballBBS.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/write")
    public String writePost(@RequestParam(value = "logInId", required = false) String logInId, Model model, HttpSession session) {
        if (session.getAttribute(logInId) != null) {
            model.addAttribute("logInId", session.getAttribute(logInId));
            return "post/write";
        } else {
            return "doLogin/login";
        }

    }

    @PostMapping("/post/new")
    public String write(PostForm form, Model model, HttpSession session) {

        String writer = (String) session.getAttribute(form.getWriter());

        Post post = new Post();

        post.setWriter(writer.trim());
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        postService.regPost(post);

        Long contentNumber = postService.findContentNumber(post.getWriter(), post.getTitle(), post.getContent());


        List<Post> content = postService.findOne(contentNumber);
        model.addAttribute("content", content);
        model.addAttribute("logInId", writer);

        return "post/viewPost";
    }

    @GetMapping("/post/list")
    public String list(Model model, @RequestParam(value = "logInId", required = false) String logInId) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);

        if (logInId == null) {
            return "post/list";
        } else {
            model.addAttribute("logInId", logInId);
            return "post/list";
        }
    }

    @GetMapping("/post/viewPost")
    public String viewPost(@RequestParam(value = "logInId", required = false) String logInId
            , @RequestParam("contentNumber") String contentNumber, Model model) {
        Long contentNum = Long.parseLong(contentNumber);
        List<Post> content = postService.findOne(contentNum);
        model.addAttribute("content", content);
        if (logInId != null) {
            model.addAttribute("logInId", logInId);
            return "post/viewPost";
        } else {
            return "post/viewPost";
        }
    }

    @GetMapping("/post/modifyPost")
    public String modifyPost(@RequestParam(value = "logInId", required = false) String logInId, @RequestParam("contentNumber") String contentNumber, Model model
            , HttpSession session) {

        Long contentNum = Long.parseLong(contentNumber);

        List<Post> searchResult = postService.findOne(contentNum);


        String sessionLogInId = (String) session.getAttribute(logInId);

        if (sessionLogInId == null) {

            model.addAttribute("content", searchResult);

            return "post/unModifyViewPost";

        } else if (!(sessionLogInId.trim().equals(searchResult.get(0).getWriter().trim()))) {
            //반복적인 실수 '==' 아니고 'equals'로 문자열 비교
            model.addAttribute("content", searchResult);
            model.addAttribute("logInId", logInId);

            return "post/unModifyViewPost";
        } else {

            model.addAttribute("content", searchResult);
            model.addAttribute("logInId", logInId);

            return "post/modifyPost";
        }
    }

    @PostMapping("/post/modifiedPost")
    public String modifiedPost(PostForm form, Model model) {

        Post post = new Post();

        post.setContentNumber(form.getContentNumber());
        post.setWriter(form.getWriter());
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        Long contentNumber = postService.modifyPost(post);

        List<Post> content = postService.findOne(contentNumber);
        model.addAttribute("content", content);
        model.addAttribute("logInId", form.getWriter());

        return "post/viewPost";
    }
}
