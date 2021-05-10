package hello.footballBBS.service;

import hello.footballBBS.domain.Member;
import hello.footballBBS.repository.MemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        memberRepository.memberSave(member);
        return member.getMemberNumber();
    }

    public List<Member> findMemberName(String name){
        return memberRepository.findByName(name);
    }

    public List<Member> findMemberId(String id){
        return memberRepository.findById(id);
    }

}
