package study.handlebars.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import study.handlebars.dto.member.MemberDTO;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.dto.post.QPostRequestDto;
import study.handlebars.model.QMember;

import java.util.List;

import static study.handlebars.model.QMember.member;
import static study.handlebars.model.QPost.post;

public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public MemberRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<PostRequestDto> findAllByMemberToPOST_REQUEST_DTO_LIST(){
        // fetch join을 사용하는 이유는 엔티티 상태에서 엔티티 그래프를 참조하기 위해서 사용하는 것
        // 따라서 당연히 엔티티가 아닌 DTO 상태로 조회하는 것은 불가능

        return jpaQueryFactory.select(new QPostRequestDto(
                        member.id.as("member_id"),
                        member.name.as("member_name"),
                        post.id.as("post_id"),
                        post.title,
                        post.value.as("content")
                ))
                .from(member)
                .join(member.posts, post)
                .fetch();
    }

    public List<MemberDTO> findAllMemberDTO(){
        //QMember qMember = new QMember("member1");
        QMember qMember = QMember.member;
        return jpaQueryFactory.select(
                Projections.bean(MemberDTO.class,
                        qMember.name,
                        qMember.password,
                        qMember.email,
                        qMember.number.as("phone_number"),
                        qMember.createDateTime.as("createTime"))
                ).from(qMember)
                .fetch();
    }

}
