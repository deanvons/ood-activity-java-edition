package no.loopacademy;
import no.loopacademy.Data.Book;
import no.loopacademy.Data.Member;

import java.util.*;

public class LibraryManager {

    public final List<Book> books = new ArrayList<>();
    public final List<Member> members = new ArrayList<>();
    public final Map<Member, List<Book>> borrowedBooks = new HashMap<>();


    public void addBook(Book book) { books.add(book); }

    public void removeBook(Book book) {
        books.removeIf(b -> Objects.equals(b.getTitle(), book.getTitle())
                && Objects.equals(b.getAuthor(), book.getAuthor()));
    }

    public void updateBook(String originalTitle, Book updatedBook) {
        Book b = findBook(originalTitle);
        if (b != null) {
            b.setTitle(updatedBook.getTitle());
            b.setAuthor(updatedBook.getAuthor());
        }
    }

    public Book findBook(String title) {
        for (Book b : books) {
            if (Objects.equals(b.getTitle(), title)) return b;
        }
        return null;
    }


    public void registerMember(Member member) { members.add(member); }

    public void removeMember(Member member) {
        members.removeIf(m -> Objects.equals(m.getMemberId(), member.getMemberId()));
        borrowedBooks.remove(member);
    }

    public void updateMember(String memberId, Member updatedMember) {
        Member m = findMember(memberId);
        if (m != null) {
            m.setName(updatedMember.getName());
            m.setMemberId(updatedMember.getMemberId());
        }
    }

    public Member findMember(String memberId) {
        for (Member m : members) {
            if (Objects.equals(m.getMemberId(), memberId)) return m;
        }
        return null;
    }

    public void borrowBook(Member member, Book book) {
        borrowedBooks.computeIfAbsent(member, k -> new ArrayList<>()).add(book);
    }

    public void returnBook(Member member, Book book) {
        List<Book> list = borrowedBooks.get(member);
        if (list != null) {
            list.removeIf(b -> Objects.equals(b.getTitle(), book.getTitle())
                    && Objects.equals(b.getAuthor(), book.getAuthor()));
        }
    }

    public List<Book> viewBorrowedBooks(Member member) {
        return borrowedBooks.getOrDefault(member, new ArrayList<>());
    }
}
