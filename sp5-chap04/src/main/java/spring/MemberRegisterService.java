package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {}

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long register(RegisterRequest req) {
        // 패스워드가 일치하지 않으면 예외 발생
        checkIfPasswordEqualsToConfirmPassword(req);

        // 같은 이메일이 존재하면 예외 발생
        checkIfDuplicateEmailExists(req.getEmail());

        // Member 객체 생성 후 insert
        Member member = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDao.insert(member);
        return member.getId();
    }

    private void checkIfPasswordEqualsToConfirmPassword(RegisterRequest req) {
        if (!req.isPasswordEqualsToConfirmPassword())
            throw new UnmatchedPasswordException();
    }

    private void checkIfDuplicateEmailExists(String email) {
        if (memberDao.selectByEmail(email) != null)
            throw new DuplicateEmailException();
    }
}
