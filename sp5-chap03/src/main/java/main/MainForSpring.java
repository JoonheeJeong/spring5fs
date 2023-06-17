package main;

import config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {

    private static ApplicationContext ctx = null;

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요:");
            String cmd = reader.readLine();
            if (cmd.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                return;
            }

            if (cmd.startsWith("new ")) {
                processNewCommand(cmd.split(" "));
                continue;
            } else if (cmd.startsWith("change ")) {
                processChangePasswordCommand(cmd.split(" "));
                continue;
            } else if (cmd.equals("list")) {
                processListCommand();
                continue;
            }

            printHelp();
        }
    }

    private static void printHelp() {
        String output = "잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.\n" +
                "명령어 사용법:\n" +
                "new 이메일 이름 암호 암호_확인\n" +
                "change 이메일 현재_암호 새로운_암호\n" +
                "list\n\n";
        System.out.println(output);
    }

    private static void processNewCommand(String[] args) {
        if (args.length != 5) {
            printHelp();
            return;
        }

        MemberRegisterService memberRegisterService = ctx.getBean("memberRegisterService", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);

        try {
            memberRegisterService.register(req);
            System.out.println("등록했습니다.\n");
        } catch (UnmatchedPasswordException e) {
            System.out.println("암호와 암호_확인이 일치하지 않습니다.\n");
        } catch (DuplicateEmailException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }

    private static void processChangePasswordCommand(String[] args) {
        if (args.length != 4) {
            printHelp();
            return;
        }

        ChangePasswordService changePasswordService = ctx.getBean(ChangePasswordService.class);
        try {
            changePasswordService.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch (WrongPasswordException e) {
            System.out.println("이메일의 암호가 틀렸습니다.\n");
        }
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }
}
