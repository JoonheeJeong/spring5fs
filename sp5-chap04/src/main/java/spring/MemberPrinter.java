package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

    public void print(Member member) {
        String registerDateTime = (dateTimeFormatter != null)
                ? dateTimeFormatter.format(member.getRegisterDateTime())
                : String.format("%tF", member.getRegisterDateTime());

        System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                member.getId(), member.getEmail(), member.getName(), registerDateTime);
    }

    @Autowired(required = false)
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
