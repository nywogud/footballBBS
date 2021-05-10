package hello.footballBBS.repository;

import hello.footballBBS.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplatePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplatePostRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Post post) {

        jdbcTemplate.update
                ("INSERT INTO footballBB.post (post.writer, post.title, post.content, post.regDate) VALUES (?,?,?, NOW())"
                        , post.getWriter(), post.getTitle(), post.getContent());
    }

    @Override
    public void modifyPost(Post post) {

        jdbcTemplate.update("UPDATE footballBB.post set post.title=?, post.content=?, post.regDate=NOW() WHERE post.contentNumber=?",
                post.getTitle(), post.getContent(), post.getContentNumber());

    }

    @Override
    public List<Post> findByContentNumber(Long contentNumber) {
        List<Post> result = jdbcTemplate.query("select * from post where contentNumber = ?", postRowMapper(), contentNumber);
        return result;
    }


    @Override
    public Long findContentNumber(String writer, String title, String content) {
        Long contentNumber = jdbcTemplate.queryForObject(
                "SELECT contentNumber FROM post AS p WHERE p.regDate = (select MAX(regDate) from post AS p WHERE p.writer=? AND p.title=? AND p.content=?)",
                Long.class, writer, title, content);
        return contentNumber;
    }

//    String name = jdbcTemplate.queryForObject(
//            "SELECT name FROM USER WHERE id=?",
//            String.class,
//            1000L);


    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM footballBB.post ORDER BY footballBB.post.contentNumber DESC", postRowMapper());
    }

    private RowMapper<Post> postRowMapper() {
        return new RowMapper<Post>() {
            @Override
            public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                Post post = new Post();
                post.setContentNumber(rs.getLong("contentNumber"));
                post.setWriter(rs.getString("writer"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setRegDate(rs.getDate("regDate"));
                return post;
            }
        };
    }
}
