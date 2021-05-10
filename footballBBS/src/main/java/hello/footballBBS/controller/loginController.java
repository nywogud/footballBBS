package hello.footballBBS.controller;

import hello.footballBBS.domain.Member;
import hello.footballBBS.form.loginForm;
import hello.footballBBS.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class loginController {

    private final MemberService memberService;

    @Autowired
    public loginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/doLogin/login")
    public String login() {
        return "doLogin/login";
    }


    @PostMapping("/doLogin/login")
    public String doLogin(loginForm form, HttpSession session, Model model) {

        List<Member> checkId = memberService.findMemberId(form.getId());


        if (checkId.size() == 0) {
            return "doLogin/loginFailById";
        }
        if (!(form.getPassword().trim().equals(checkId.get(0).getPassword().trim()))) {
            return "doLogin/loginFailByPassword";
        } else {

            String loggedInId = form.getId();

            session.setAttribute(loggedInId, form.getId());

//            System.out.println("세션에서 뱉어내는 값 : " + session.getAttribute(loggedInId));

            model.addAttribute("logInId", session.getAttribute(loggedInId));


            return String.format("redirect:/logInHome?logInId=%s", session.getAttribute(loggedInId));

        }

    }

    @GetMapping("/logInHome")
    public String logInHome(@RequestParam("logInId") String logInId, Model model, HttpSession session) {
//        System.out.println(logInId);

        String sessionLogInId = (String) session.getAttribute(logInId);

        if (sessionLogInId == null) {
            return "doLogin/login";
        } else {
            model.addAttribute("logInId", logInId);
            return "logInHome";
        }
    }

    @GetMapping("member/logOut")
    public String logOutHome(@RequestParam("logOutId") String logOutId, HttpSession session) {
        String loggedOutId = (String) session.getAttribute(logOutId);
//        System.out.println(loggedOutId);
        session.removeAttribute(loggedOutId);
        return "redirect:/";
    }
}
