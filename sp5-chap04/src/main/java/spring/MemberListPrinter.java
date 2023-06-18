package spring;

public class MemberListPrinter {
    private final MemberDao memberDao;
    private final MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public void printAll() {
        memberDao.selectAll().forEach(printer::print);
        System.out.println();
    }
}
