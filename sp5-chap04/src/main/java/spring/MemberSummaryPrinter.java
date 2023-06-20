package spring;

public class MemberSummaryPrinter extends MemberPrinter {

    @Override
    public void print(Member member) {
        if (member == null) {
            System.out.println("데이터 없음\n");
            return;
        }
        System.out.printf("회원 정보: 이메일=%s, 이름=%s\n",
                member.getEmail(), member.getName());
    }
}
