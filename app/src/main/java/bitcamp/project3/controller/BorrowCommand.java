package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BorrowCommand {

    String menuTitle = "대출";
    String[] menus = {"도서대출", "도서검색"};

    String currentUser = "user";

    List<Borrow> borrowList = new LinkedList<>();
    //원래 북리스트
    //LinkedList bookList;
    public List getBorrowList() {
        return this.borrowList;
    }
    
    //더미 북 리스트
    List<Book> bookList = Book.generateDummyData(5);

    public List getBookList() {
        return this.bookList;
    }

    // 생성자
    public BorrowCommand() {
    }

    public BorrowCommand(String title, List<Book> bookList) {
        this.menuTitle = title;
        this.bookList = bookList;
    }

//    public BorrowCommand(String title, LinkedList bookList) {
//        this.menuTitle = title;
//        this.bookList = bookList;
//    }

    // 메인실행
    public void execute() {

        System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        System.out.println("++대출도서 목록예정++\n");
        printBookList();
        cmd();

        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                cmd();
                continue;
            } else if (command.equals("0")) { // 이전 메뉴 선택
                return;
            } try {
                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo, menus);
                if (menuName == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                    continue;
                }
                switch (menuName){
                    case "도서대출":
                        bookBorrow();
                        break;
                    case "도서검색":
                        bookSearch();
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    private void bookBorrow() {
        System.out.println("도서대출 입니다.");
        int bookNo = Prompt.inputInt("도서번호를 입력하세요: ");

        // bookList에서 해당 도서 찾기
        Book selectedBook = null;
        for (Book book : bookList) {
            if (book.getNo() == bookNo) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println("해당 번호의 도서를 찾을 수 없습니다.");
            return;
        }

        if (selectedBook.isCheck()) {
            System.out.println("이미 대출 중인 도서입니다.");
            return;
        }

        // 새로운 Borrow 객체 생성
        Borrow borrow = new Borrow(LocalDate.now());
        borrow.setTitle(selectedBook.getTitle());
        borrow.setUser(currentUser);

        // borrowList에 대출 정보 추가
        borrowList.add(borrow);

        // 도서를 대출 중으로 표시
        selectedBook.setCheck(true);

        System.out.println("도서가 성공적으로 대출되었습니다.");
        System.out.printf("대출 도서: %s\n", selectedBook.getTitle());
        System.out.printf("대출 기간: %s ~ %s\n", borrow.getStartDate(), borrow.getEndDate());
    }

    private void printBookList() {
        System.out.println("도서목록 입니다.");
        System.out.println("번호 | 카테고리 | 도서명 | 저자 | 대출 상태");
        for (Book book : bookList) {
            System.out.printf("%d  |   %s  |  %s  |  %s  |  %s\n",
                book.getNo(), book.getBookCategory(), book.getTitle(),
                book.getAuthor(), book.isCheck() ? "대출 중" : "대출 가능");
        }
    }

    private void bookSearch() {
        System.out.println("도서 검색");
        System.out.println("1. 카테고리로 검색");
        System.out.println("2. 도서명으로 검색");
        System.out.println("3. 저자로 검색");
        int searchOption = Prompt.inputInt("검색 옵션을 선택하세요: ");

        String searchKeyword;
        List<Book> searchResults = new ArrayList<>();

        switch (searchOption) {
            case 1:
                searchKeyword = Prompt.input("검색할 카테고리를 입력하세요: ");
                for (Book book : bookList) {
                    if (book.getBookCategory().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 2:
                searchKeyword = Prompt.input("검색할 도서명을 입력하세요: ");
                for (Book book : bookList) {
                    if (book.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 3:
                searchKeyword = Prompt.input("검색할 저자를 입력하세요: ");
                for (Book book : bookList) {
                    if (book.getAuthor().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            default:
                System.out.println("잘못된 옵션을 선택하셨습니다.");
                return;
        }

        if (searchResults.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.println("검색 결과:");
            System.out.println("번호 | 카테고리 | 도서명 | 저자 | 대출 상태");
            for (Book book : searchResults) {
                System.out.printf("%d  |   %s  |  %s  |  %s  |  %s\n",
                    book.getNo(), book.getBookCategory(), book.getTitle(),
                    book.getAuthor(), book.isCheck() ? "대출 중" : "대출 가능");
            }
        }
    }

    public void cmd() {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("[%d] %s\n", (i + 1), menus[i]);
        }
        System.out.println("[0] 이전메뉴");
    }


    // 메뉴 검증
    static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    // 메뉴 타이틀 출력
    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }
}

