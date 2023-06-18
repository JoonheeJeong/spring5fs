package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPw, String newPw) {
        Member member = getMemberIfExistsWithGivenEmail(email);

        member.changePassword(oldPw, newPw);

        memberDao.update(member);
    }

    private Member getMemberIfExistsWithGivenEmail(String email) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();
        return member;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
