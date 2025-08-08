package models.excpetions;

public class RefreshTokenExpired extends RuntimeException{
    public RefreshTokenExpired(String message) {
        super(message);
    }
}
