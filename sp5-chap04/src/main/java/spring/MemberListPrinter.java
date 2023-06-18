package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter printer;

    public MemberListPrinter() {}

    public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public void printAll() {
        memberDao.selectAll().forEach(printer::print);
        System.out.println();
    }
}
