package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//@Controller: 스프링이 실행될 때 스프링 컨테이너에 이 클래스의 객체를 생성해서 넣어둔다. (=스프링 컨테이너에서 스프링 빈이 관리된다.)
// 스프링이 관리하게 되면 스프링 컨테이너에 등록하고 스프링 컨테이너로부터 받아서 사용하도록 바꿔야함. 관리해야함.

    private final MemberService memberService;

    @Autowired
    public  MemberController(MemberService memberService) {
        //생성자를 호출하는데 멤버서비스를 스프링이 스프링 컨테이너에 있는 멤버서비스와 연결시켜줌.
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public  String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
