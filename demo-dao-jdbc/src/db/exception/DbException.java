package db.exception;

public class DbException extends RuntimeException {
    public DbException(String mensagem) {
        super(mensagem);
    }
}
