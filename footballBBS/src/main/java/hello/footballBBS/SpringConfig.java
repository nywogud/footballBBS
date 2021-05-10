package hello.footballBBS;

import hello.footballBBS.repository.*;
import hello.footballBBS.service.MemberService;
import hello.footballBBS.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
        return new JdbcTemplatePostRepository(dataSource);
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
