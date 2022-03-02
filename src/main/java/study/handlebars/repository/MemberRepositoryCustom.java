package study.handlebars.repository;

import study.handlebars.dto.member.MemberDTO;
import study.handlebars.dto.post.PostRequestDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<PostRequestDto> findAllByMemberToPOST_REQUEST_DTO_LIST();
    List<MemberDTO> findAllMemberDTO();
}
