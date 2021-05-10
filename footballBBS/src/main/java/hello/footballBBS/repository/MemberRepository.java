package hello.footballBBS.repository;

import hello.footballBBS.domain.Member;

import java.util.List;

public interface MemberRepository {

    void memberSave(Member member);

    List<Member> findById(String id);

    List<Member> findByName(String name);

    List<Member> findAll();

}
