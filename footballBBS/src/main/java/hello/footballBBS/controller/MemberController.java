package hello.footballBBS.controller;

import hello.footballBBS.domain.Member;
import hello.footballBBS.form.memberForm;
import hello.footballBBS.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/member/newMember")
    public String newMember() {
        return "member/newMember";
    }

    @PostMapping("/member/createId")
    public String newMember(memberForm form, Model model) {

        List<Member> checkDuple = memberService.findMemberId(form.getId().trim());

        if(checkDuple.size()==0){
            Member member = new Member();

            member.setName(form.getName().trim());
            member.setId(form.getId().trim());
            member.setPassword(form.getPassword().trim());
            member.setEmail(form.getEmail().trim());

            memberService.join(member);

            List<Member> memberDetails = memberService.findMemberId(member.getId().trim());
            model.addAttribute("memberDetails", memberDetails);


            return "redirect:/";
        }
        else {

            return "member/dupleChkNewMember";
        }

    }

}