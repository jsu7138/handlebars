package study.handlebars.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.handlebars.dto.member.MemberDTO;
import study.handlebars.service.MemberSerive;
import study.handlebars.util.BindingResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
public class MemberController {
    private MemberSerive memberSerive;

    @Autowired
    public MemberController(MemberSerive memberSerive) {
        this.memberSerive = memberSerive;
    }

    @GetMapping("/signin")
    public String signIn(){
        return "page/auth/signin";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        return "page/auth/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute @Valid MemberDTO memberDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            var fieldError = BindingResultUtil.toConcurrentHashMap(bindingResult);
            model.addAttribute("fields",fieldError);
            return "page/auth/signup";
        }

        if(!memberSerive.signUp(memberDTO)){
            bindingResult.addError(new FieldError("memberDTO","name","이미 존재하는 회원 아이디입니다"));
            model.addAttribute("fields", BindingResultUtil.toConcurrentHashMap(bindingResult));
            return "page/auth/signup";
        }

        redirectAttributes.addAttribute("signup", true);
        return "redirect:signin";
    }
}