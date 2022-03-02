package study.handlebars.initalize;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.handlebars.dto.member.MemberDTO;
import study.handlebars.model.Member;
import study.handlebars.model.Team;
import study.handlebars.service.MemberSerive;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

//개발자가 직접 제공하는 bean을 생성시 사용.
@Component
@RequiredArgsConstructor
public class InitDatabase {
    private final InitMemberSerive initMemberSerive;

    //@Transactional 과 같이 쓸 수 없음.
    // initMemberSerive 가 생성된 후에 PostConstruct 가 실행됨.
    @PostConstruct
    public void init() {
        initMemberSerive.init();
    }
/*
0. 작업할 프로젝트 열기
1. git merge-review develop feature/security-task    //머지된 작업이 있는지 확인하고 필요시 합친다.
2. 게릿 리뷰를 받고 직접 Submit 클릭.
3. 젠키스 접속후 빌드 -> 빌드 브랜치명 : feature/security-task
4. github.coupang.net/coupang/coupang_deployment -> Fork 진행 후 git clone 진행
4. InteliJ 터미널 접속
    - git remote add main git@github.coupang.net: //한번만 설정하면된다. (원격 브런치의 주소를 설정).
    - git pool main master    //원격 마스터 브런치를 동기화시킴
    - 깃 새로 고침(fetch all remote 버튼 클릭)하여 확인.

* git checkout -- .      -> 기본 마스터 브랜치로 돌린다
* git checkout -b deploy-test   //브런치 작성과 체크아웃을 동시에 실행
* git status
* git add ~~
* git commit -m "change iap dev deploy version"
* git push origin deploy-test
* URL 확인후 -> 리뷰 받고 -> 브런치 삭제 클릭
*
*
* 스택스테이터스 - 데브 스텍 // 앞에 watch 붙여서 2초마다 확인
* 팟 스테이터스

* 터미널 배포
* awscn aws_boltx_jumpbox
* 2
* boltcfg login
* ~@Counpang.com
* ~password
* boltcfg query -r ~경로
* */
    @Component
    static class InitMemberSerive{
        @Autowired
        EntityManager em;
        @Autowired
        MemberSerive memberSerive;

        @Transactional
        public void init(){
            Team TeamA = new Team("TeamA");
            Team TeamB = new Team("TeamB");

            em.persist(TeamA);
            em.persist(TeamB);


            for (int i =0; i< 10; i++){
                MemberDTO memberDTO = new MemberDTO("MemberTempId" + i, "Member" + i + "@Google.com", UUID.randomUUID().toString() + "1f@!", "010-1234-5678", null);
                memberSerive.signUp(memberDTO);

                /*Member member = Member.builder()
                        .name("MemberTemp"+i)
                        .password(UUID.randomUUID().toString()+"1f@!")
                        .email("Member"+i+"@Google.com")
                        .number("010-1234-5678")
                        .build();
                em.persist(member);*/
            }
        }
    }
}
