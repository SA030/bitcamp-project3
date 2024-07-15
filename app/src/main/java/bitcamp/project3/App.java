package bitcamp.project3;

import bitcamp.project3.Monitor.AdminMonitor;
import bitcamp.project3.Monitor.Membership;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.Monitor.UserMonitor;

public class App {

    static AdminMonitor rm = AdminMonitor.getInstance();
    static UserMonitor um = UserMonitor.getInstance();

    public App(){
        rm.borrowCommand = new BorrowCommand("대출관리", rm.bookCommand.getBookList());

    }

    // Main
    public static void main(String[] args) {
        App app = new App();

        // 로그인 후 회원SeqNo get
        // 관리자SeqNo : 0
        // 유저SeqNo: 1~
        // 로그인 실패 시 -1, INPUT으로 따로 1/2 관리자 유저 접속
        int check = Membership.getInstance().cmd();

        if(check==0){
            check = 1;
        }
        else if(check>0){
            check = 2;
        }
        else {
            check = Prompt.inputInt("[임시]관리자 1/유저 2");
        }

        switch (check) {
            case 1:
                rm.adminExecute();
                break;
            case 2:
                um.userExecute();
                break;
        }
    }


}

