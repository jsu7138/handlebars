package study.handlebars.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.handlebars.model.Member;
import study.handlebars.validation.ValidPhoneNumber;

import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Getter @Setter
@NoArgsConstructor
@ToString(of = {"name","email","password","phone_number"})
public class MemberDTO {
    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 8, max = 100, message = "아이디는 최소 8글자 이상입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 오직 영문과 숫자를 포함해서 입력할 수 있습니다.")
    private String name;

    @Email
    @Size(min = 2,max = 100, message = "이메일의 길이가 잘못되었습니다.")
    @NotBlank(message = "이메일은 공백이 될 수 없습니다.")
    private String email;

    @NotNull
    @Size(min = 8, max = 100, message = "비밀번호는 최소 8자 이상 입력하십시오.")
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*\\d)(?=.*[-~!@#$%^&*()+|=])[A-Za-z\\d-~!@#$%^&*()+|=]{8,}",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @NotNull
    @ValidPhoneNumber
    private String phone_number;

    private LocalDateTime createTime;


    public MemberDTO(String name, String email, String password, String phone_number, LocalDateTime createTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.createTime = createTime;
    }

    public Member toMember(){
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .number(phone_number)
                .build();
    }
}
