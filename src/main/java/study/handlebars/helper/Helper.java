package study.handlebars.helper;

import com.github.jknack.handlebars.Options;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.model.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class Helper {

    @HandlebarsHelper
    public class CustomHelper{
        public Boolean isNotNull(String data){
            return data != null;
        }

        public Boolean isEquals(String data1, String data2){
            System.out.println("data1 = " + data1);
            System.out.println("data2 = " + data2);
            return data1 != null && data2 != null && data1.equals(data2);
        }
        public Boolean isEqualsNot(String data1, String data2){
            return data1 != null && data2 != null && !data1.equals(data2);
        }

        public String getListSize(List<Object> list)  {
            if(list == null ) return "0";
            return String.valueOf(list.size());
        }

        public CharSequence compare(final Object obj1, final Options options) throws IOException {
            Object obj2 = options.param(0);
            int data1 = (int)obj1;
            int data2 = (int)obj2;
            return  data1 > data2 ? options.fn() : options.inverse();
        }
        
        public Boolean json(String name, boolean escapeHTML){
            System.out.println("escapeHTML = " + escapeHTML);
            return name == null;
        }
    }

    @HandlebarsHelper
    public class AuthHelper{
        public String getLoginName(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getName();
        }
        public Boolean getIsLogin(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication != null && authentication.isAuthenticated()
                    && !(authentication instanceof AnonymousAuthenticationToken);
        }
    }
}
