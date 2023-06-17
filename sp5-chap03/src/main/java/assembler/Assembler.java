package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

import java.util.HashMap;
import java.util.Map;

public class Assembler {

    private final MemberDao memberDao;
    private final MemberRegisterService memberRegisterService;
    private ChangePasswordService changePasswordService;

    public Assembler() {
        memberDao = new MemberDao();
        memberRegisterService = new MemberRegisterService(memberDao);
        changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return memberRegisterService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }
}
