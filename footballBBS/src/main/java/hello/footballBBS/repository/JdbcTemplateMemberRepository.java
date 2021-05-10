package hello.footballBBS.repository;

import hello.footballBBS.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void memberSave(Member member) {
        jdbcTemplate.update
                ("INSERT INTO footballBB.member (member.name, member.id, member.password, member.email, member.regDate) VALUES (?, ?, ?, ?, NOW())"
                , member.getName(), member.getId(), member.getPassword(), member.getEmail());
    }

    @Override
    public List<Member> findById(String id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id=?", memberRowMapper(), id);
        return result;
    }

    @Override
    public List<Member> findByName(String name) {
        List<Member> result =
                jdbcTemplate.query("SELECT * FROM member WHERE name=?", memberRowMapper(), name);
                return result;
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query
                ("SELECT * FROM member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setName(rs.getString("name"));
                member.setId(rs.getString("id"));
                member.setPassword(rs.getString("password"));
                member.setMemberNumber(rs.getLong("memberNumber"));
                member.setEmail(rs.getString("email"));
                member.setRegDate(rs.getDate("regDate"));
                return member;
            }
        };
    }
}
