package study.handlebars.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.handlebars.dto.member.MemberDTO;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.model.Member;
import study.handlebars.model.Post;
import study.handlebars.service.MemberSerive;
import study.handlebars.service.PostService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {
    private PostService postService;
    private MemberSerive memberSerive;

    //@Autowired
    public WebController(PostService postService, MemberSerive memberSerive) {
        this.postService = postService;
        this.memberSerive = memberSerive;
    }

    @GetMapping("/")
    public String main(Model model, HttpServletResponse request,  @AuthenticationPrincipal User userAccount){
        model.addAttribute("title","<div class=\"py-5 my-2 text-center\">환영합니다.</div>");
        Member member = new Member("djiw", "dko", "dwkoiaa", "dii");
        model.addAttribute("tempMember", member);
        if(userAccount != null){
            List<MemberDTO> allByMemberDTO = memberSerive.findAllByMemberDTO();
            model.addAttribute("members",allByMemberDTO);
        }
        return "index";
    }

    @GetMapping("/posts")
    public String post(Model model){
        List<PostRequestDto> allByMemberToPOST_request_dto_list = memberSerive.findAllByMemberToPOST_REQUEST_DTO_LIST();
        model.addAttribute("members", allByMemberToPOST_request_dto_list);

        return "page/post/index";
    }


    @GetMapping("/posts/{id}")
    public String postSave(@PathVariable Long id, Model model){
        model.addAttribute("members", postService.getPostByMemberId(id));
        return "page/post/index";
    }
}
