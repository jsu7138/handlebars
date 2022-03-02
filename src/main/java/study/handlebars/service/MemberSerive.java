package study.handlebars.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import study.handlebars.dto.member.MemberDTO;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.model.Member;
import study.handlebars.repository.MemberRepository;

import javax.persistence.Access;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MemberSerive implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    public MemberSerive(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member save(Member member){
        memberRepository.save(member);
        return member;
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findByName(String name){
        return memberRepository.findByName(name);
    }

    public List<Member> findFetchPostAllMember(){
        return memberRepository.findFetchByMember();
    }

    public List<PostRequestDto> findAllByMemberToPOST_REQUEST_DTO_LIST(){
        return memberRepository.findAllByMemberToPOST_REQUEST_DTO_LIST();
    }

    public List<MemberDTO> findAllByMemberDTO(){
         return memberRepository.findAllMemberDTO();
    }

    public boolean signUp(MemberDTO memberDTO){
        try {
            if(!this.findByName(memberDTO.getName()).isEmpty()){
                return false;
            }

            String encodePassword = passwordEncoder.encode(memberDTO.getPassword());
            Member member = memberDTO.toMember();
            member.setPassword(encodePassword);

            this.save(member);

            return true;

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byName = memberRepository.findByName(username);
        Optional<Member> findMember = Optional.ofNullable(memberRepository.findByName(username))
                .orElseThrow(()-> new UsernameNotFoundException(username));
        User user = new User(findMember.get().getName(), findMember.get().getPassword(), authorities());
        System.out.println("user = " + user);
        return user;
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
