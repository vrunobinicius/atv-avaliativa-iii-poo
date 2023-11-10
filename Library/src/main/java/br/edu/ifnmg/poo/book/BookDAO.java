package br.edu.ifnmg.poo.book;

import br.edu.ifnmg.poo.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bvan &lt;Bruno Vinícius at ifnmg&gt;
 */
public class BookDAO extends Dao<Book> {

    public static final String TABLE = "book";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(title, authors, acquisition, pages, "
                + "year, edition, price)  values (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE + " set title = ?, authors = ?, acquisition = ?, "
                + "pages = ?, year = ?, edition = ?, price = ? where ID = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book e) {
        try {
            pstmt.setString(1, e.getTitle());
            pstmt.setString(2, e.getAuthors());
            pstmt.setObject(3, e.getAcquisition(), java.sql.Types.DATE);
            pstmt.setObject(4, e.getPages(), java.sql.Types.SMALLINT);
            pstmt.setObject(5, e.getYear(), java.sql.Types.SMALLINT);
            pstmt.setObject(6, e.getEdition(), java.sql.Types.TINYINT);
            pstmt.setObject(7, e.getPrice(), java.sql.Types.DECIMAL);

            if (e.getId() != null) {
                pstmt.setLong(8, e.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getFindByIdStatment() {
        return "select ID, title, authors, acquisition, pages, "
                + "year, edition, price"
                + " from " + TABLE + " where ID = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select ID, title, authors, acquisition, pages, "
                + "year, edition, price"
                + " from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where ID = ?";
    }

    @Override
    public Book extractObject(ResultSet resultSet) {
        Book book = null;

        try {
            book = new Book();
            book.setId(resultSet.getLong("ID"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthors(resultSet.getString("authors"));
            book.setAcquisition(resultSet.getObject("acquisition", LocalDate.class));
            book.setPages(resultSet.getShort("pages"));
            book.setYear(resultSet.getShort("year"));
            book.setEdition(resultSet.getByte("edition"));
            book.setPrice(resultSet.getBigDecimal("price"));
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return book;
    }

    @Override
    public List<Book> extractObjects(ResultSet resultSet) {
        List<Book> listBooks = null;

        try {
            listBooks = new ArrayList<Book>();
            while (resultSet.next()) {
                listBooks.add(extractObject(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listBooks;

    }
}
