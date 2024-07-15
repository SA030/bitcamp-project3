package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import bitcamp.project3.vo.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;

public class ReturnCommand {
        String menuTitle = "반납";
        String[] menus = {"도서반납"};

        User currentUser;

        List<Book> bookList;
        List<Borrow> borrowList;

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public ReturnCommand(List<Book> bookList, List<Borrow> borrowList, User currentUser) {
        this.bookList = bookList;
        this.borrowList = borrowList;
        this.currentUser = currentUser;
    }


    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static ReturnCommand rc;

    // setup BookCommand Instance
    public static ReturnCommand getInstance() {

        if (rc == null) {
            rc = new ReturnCommand();
        }

        return rc;
    }// Method getInstance END

    // reset BookCommand Instance
    public static void freeInstance() {
        rc = null;
    }// Method freeInstance END


    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public ReturnCommand() {
        this.bookList = new ArrayList<>();
    }

    // 메인실행
    public void execute() {
/*System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        System.out.println("++대출도서 목록예정++\n");
        printBookList();
        cmd();
        String command = Prompt.input(String.format("메인/%s>", menuTitle));
        if (command.equals("menu")) {
            cmd();
            continue;
        } else if (command.equals("0")) { // 이전 메뉴 선택
            return;
        }
        int menuNo = Integer.parseInt(command);
        String menuName = getMenuTitle(menuNo, menus);
        if (menuName == null) {
            System.out.println("유효한 메뉴 번호가 아닙니다.");
            continue;
        }
        switch (menuName){
            case "도서반납":
                bookReturn();
                break;
        }**/
        while (processMenu()) {
            try {

                } catch (NumberFormatException ex) {
                    printNumberFormatException();
                }
        }
    }

    //Menu Process
    //[0] 종료 입력시에만 process 종료
    private boolean processMenu() {
        printMenuTUI();
        int menuNo = inputInt(String.format("메인/%s>", menuTitle));


        return switch (menuNo) {
            case 1 -> {
                bookReturn();
                yield true;
            }
            case 0 -> false;
            default -> {
                printNumberLimitException();
                yield true;
            }
        };
    }




    private void printMenuTUI(){
        setClearCmd();

        System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        printBorrowBookList();
        printBookList();
//        cmd();

        System.out.print(printCustomMenu(menus));
    }






    private void bookReturn() {
        // 현재 사용자가 대출한 책 목록 출력
        List<Borrow> userBorrows = new ArrayList<>();
        for (Borrow borrow : borrowList) {
            if (borrow.getUser().equals(currentUser)) {
                userBorrows.add(borrow);
            }
        }

        printBorrowBookList();

        int selectNo = Prompt.inputInt("반납할 도서의 번호를 입력하세요 (취소: 0): ");
        if (selectNo == 0) {
            System.out.println("반납이 취소되었습니다.");
            return;
        }

        if (selectNo < 1 || selectNo > userBorrows.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Borrow selectedBorrow = userBorrows.get(selectNo - 1);

        // 해당 도서를 bookList에서 찾아 대출 상태 변경
        for (Book book : bookList) {
            if (book.getTitle().equals(selectedBorrow.getTitle())) {
                book.setCheck(false);
                break;
            }
        }

        // borrowList에서 해당 대출 정보 제거
        borrowList.remove(selectedBorrow);

        System.out.printf("%s 도서가 성공적으로 반납되었습니다.\n", selectedBorrow.getTitle());
    }

        private void printBorrowBookList() {
            String[] calm={"No", "도서명", "대출일", "반납예정일"};
            int[] width={4, 20, 20, 20};
            //no, title, borrowDate, RetueDate
            int i=0;

            System.out.println("도서반납 입니다.");

            // 현재 사용자가 대출한 책 목록 출력
            List<Borrow> userBorrows = new ArrayList<>();
            for (Borrow borrow : borrowList) {
                if (borrow.getUser().equals(currentUser)) {
                    userBorrows.add(borrow);
                }
            }

            if (userBorrows.isEmpty()) {
                System.out.println("현재 대출 중인 도서가 없습니다.");
                return;
            }
            //////////////////////////////////////////////////////////////
            ////////////////////////result table//////////////////////////
            //////////////////////////////////////////////////////////////
            //table title
            System.out.println("현재 대출 중인 도서 목록:");
            System.out.print(printTableLine(width));
            for(String data: calm){
                System.out.print(printTableDataFormat(width[i++], data));
            }
            System.out.print(":\n");
            System.out.print(printTableLine(width));

            //table data
            int borrowIndex = 1;
            for (Borrow borrow : userBorrows) {
                System.out.print(printTableDataFormat( width[0], String.format("%s", borrowIndex)) );
                System.out.print(printTableDataFormat( width[1], String.format("%s", borrow.getTitle())) );
                System.out.print(printTableDataFormat( width[2], String.format("%s", borrow.getStartDate())) );
                System.out.print(printTableDataFormat( width[3], String.format("%s", borrow.getEndDate())) );
                System.out.print(":\n");
                borrowIndex++;
            }

            //END line
            System.out.print(printTableLine(width));
        }

        private void printBookList() {
            String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
            int[] width={4, 20, 20, 20, 15};
            //no, cate, title, writer, status
            int i=0;

/*            System.out.println("번호 | 카테고리 | 도서명 | 저자 | 대출 상태");

            for (Book book : bookList) {
                System.out.printf("%d  |   %s  |  %s  |  %s  |  %s\n",
                    book.getNo(), book.getBookCategory(), book.getTitle(),
                    book.getAuthor(), book.isCheck() ? "대출 중" : "대출 가능");
            }**/

            System.out.println("도서목록 입니다.");

            if (bookList == null){
                System.out.println("도서 목록이 없습니다.");
                return;
            }



            //////////////////////////////////////////////////////////////
            ////////////////////////result table//////////////////////////
            //////////////////////////////////////////////////////////////
            //table title
            System.out.print(printTableLine(width));
            for(String data: calm){
                System.out.print(printTableDataFormat(width[i++], data));
            }
            System.out.print(":\n");
            System.out.print(printTableLine(width));

            //table data
            for (Book book : bookList) {
                System.out.print(printTableDataFormat( width[0], String.format("%s", book.getNo())) );
                System.out.print(printTableDataFormat( width[1], String.format("%s", book.getBookCategory())) );
                System.out.print(printTableDataFormat( width[2], String.format("%s", book.getTitle())) );
                System.out.print(printTableDataFormat( width[3], String.format("%s", book.getAuthor())) );
                System.out.print(printTableDataFormat( width[4], String.format("%s", book.isCheck() ? "대출중" : "대출가능") ));
                System.out.print(":\n");
            }

            //END line
            System.out.print(printTableLine(width));
        }

    ///////////////////////////////////////////////////////////
    ////////////////////// Admin Process //////////////////////
    ///////////////////////////////////////////////////////////
    public void adminExecute(){
        rc.processAdminUser();
    }

    private void printAdminMenuTUI(){
        setClearCmd();

        System.out.print(printAdminMenu(2));
    }

    public void processAdminUser() {
        while (adminMenuCommand()) {
        }
    }//Method update END

    private boolean adminMenuCommand(){

        printAdminMenuTUI();

        int ans = inputInt("메인/대출관리> ");

        return switch (ans) {
            case 1 -> {
                rc.printAllBorrowStatus();
                yield true;
            }
            case 2 -> {
                rc.printBorrowStatusByUser();
                yield true;
            }
            case 0 -> false;
            default -> {
                printNumberLimitException();
                yield true;
            }
        };
    }//Method updateMenuCommand END

    private void printAllBorrowStatus() {
        System.out.println("\n[전체 대출 현황]");
        if (borrowList == null || borrowList.isEmpty()) {
            System.out.println("현재 대출 중인 도서가 없습니다.");
            return;
        }

        String[] columns = {"No", "사용자", "도서명", "대출일", "반납예정일"};
        int[] widths = {4, 15, 20, 20, 20};
        printTableHeader(columns, widths);

        int index = 1;
        for (Borrow borrow : this.borrowList) {
            System.out.print(printTableDataFormat(widths[0], String.valueOf(index)));
            System.out.print(printTableDataFormat(widths[1], borrow.getUser().getName()));
            System.out.print(printTableDataFormat(widths[2], borrow.getTitle()));
            System.out.print(printTableDataFormat(widths[2], String.format("%s",borrow.getStartDate())));
            System.out.print(printTableDataFormat(widths[3], String.format("%s",borrow.getEndDate())));
            System.out.print(":\n");
            index++;
        }
        System.out.print(printTableLine(widths));
    }

    private void printBorrowStatusByUser() {
        System.out.println("\n[사용자별 대출 현황]");
        if (borrowList == null || borrowList.isEmpty()) {
            System.out.println("현재 대출 중인 도서가 없습니다.");
            return;
        }

        Map<User, List<Borrow>> borrowsByUser = new HashMap<>();
        for (Borrow borrow : borrowList) {
            borrowsByUser.computeIfAbsent(borrow.getUser(), k -> new ArrayList<>()).add(borrow);
        }

        for (Map.Entry<User, List<Borrow>> entry : borrowsByUser.entrySet()) {
            User user = entry.getKey();
            List<Borrow> userBorrows = entry.getValue();

            System.out.printf("\n사용자: %s\n", user.getName());
            String[] columns = {"No", "도서명", "대출일", "반납예정일"};
            int[] widths = {4, 20, 20, 20};
            printTableHeader(columns, widths);

            int index = 1;
            for (Borrow borrow : userBorrows) {
                System.out.print(printTableDataFormat(widths[0], String.valueOf(index)));
                System.out.print(printTableDataFormat(widths[1], borrow.getTitle()));
                System.out.print(printTableDataFormat(widths[2], String.format("%s",borrow.getStartDate())));
                System.out.print(printTableDataFormat(widths[3], String.format("%s",borrow.getEndDate())));
                System.out.print(":\n");
                index++;
            }
            System.out.print(printTableLine(widths));
        }
    }

    private void printTableHeader(String[] columns, int[] widths) {
        System.out.print(printTableLine(widths));
        for (int i = 0; i < columns.length; i++) {
            System.out.print(printTableDataFormat(widths[i], columns[i]));
        }
        System.out.print(":\n");
        System.out.print(printTableLine(widths));
    }

}
