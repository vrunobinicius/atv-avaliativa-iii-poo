package br.edu.ifnmg.poo.library;

import br.edu.ifnmg.poo.book.Book;
import br.edu.ifnmg.poo.book.BookDAO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class Library {

    public static void main(String[] args) {

        /*
                    2. testes contemplando:
                    2.1. tentativa de criação de um livro com data de aquisição com 3 dias posteriores ao dia corrente;
                    2.2. a inclusão de três livros com dados à escolha;
                    2.3. a atualização de um livro à escolha;
                    2.4. a localização por ID de dois livros à escolha;
                    2.5. a carga de todos os livros;
                    2.6. e a remoção de um livro à escolha.
         */
        BookDAO bookDao = new BookDAO();

        // 2. testes contemplando:
        // 2.1. tentativa de criação de um livro com data de aquisição com 3 dias posteriores ao dia corrente;
        String title1 = "Harry Potter and the Philosopher's Stone";
        String author1 = "J. K. Rowling";
        LocalDate acquisition1 = LocalDate.of(2023, 11, 13);
        Short nPage1 = 223;
        Short year1 = 1997;
        Byte edition1 = 1;
        BigDecimal price1 = new BigDecimal(145.00);
        Book book1 = new Book(title1, author1, acquisition1, nPage1, year1, edition1, price1);
        System.out.println(">> " + book1);

        bookDao.saveOrUpdate(book1);
        
        //2.2. a inclusão de três livros com dados à escolha;
        String title2 = "Harry Potter and the Chamber of Secrets";
        String author2 = "J. K. Rowling";
        LocalDate acquisition2 = LocalDate.of(2010, 1, 20);
        Short nPage2 = 251;
        Short year2 = 1998;
        Byte edition2 = 2;
        BigDecimal price2 = new BigDecimal(105.00);
        Book book2 = new Book(title2, author2, acquisition2, nPage2, year2, edition2, price2);
        System.out.println(">> " + book2);

        //Harry Potter and the Prisoner of Azkaban 
        // J. K. Rowling
        String title3 = "A Game of Thrones";
        String author3 = "George R. R. Martin";
        LocalDate acquisition3 = LocalDate.of(2012, 5, 10);
        Short nPage3 = 317;
        Short year3 = 1999;
        Byte edition3 = 3;
        BigDecimal price3 = new BigDecimal(125.00);
        Book book3 = new Book(title3, author3, acquisition3, nPage3, year3, edition3, price3);
        System.out.println(">> " + book3);

        String title4 = "Harry Potter and the Goblet of Fire";
        String author4 = "J. K. Rowling";
        LocalDate acquisition4 = LocalDate.of(2013, 10, 20);
        Short nPage4 = 636;
        Short year4 = 2000;
        Byte edition4 = 4;
        BigDecimal price4 = new BigDecimal(210.00);
        Book book4 = new Book(title4, author4, acquisition4, nPage4, year4, edition4, price4);
        System.out.println(">> " + book4);

        bookDao.saveOrUpdate(book2);
        bookDao.saveOrUpdate(book3);
        bookDao.saveOrUpdate(book4);

        //2.3. a atualização de um livro à escolha;
        title3 = "Harry Potter and the Prisoner of Azkaban";
        author3 = "J. K. Rowling";
        book3.setTitle(title3);
        book3.setAuthors(author3);
        bookDao.saveOrUpdate(book3);

        //2.4. a localização por ID de dois livros à escolha;
        Book idBook3 = bookDao.findById(3L);
        Book idBook1 = bookDao.findById(1L);
        System.out.println(">>> " + idBook3);
        System.out.println(">>> " + idBook1);

        //2.5. a carga de todos os livros;
        List<Book> list = bookDao.findAll();
        for (Book b : list) {
            System.out.println(">>> " + b);
        }

        //2.6. e a remoção de um livro à escolha.
        bookDao.delete(3L);
    }
}
