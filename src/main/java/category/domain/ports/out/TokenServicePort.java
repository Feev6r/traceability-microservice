package category.domain.ports.out;

public interface TokenServicePort {

    String extractUsername(String token);
    boolean isTokenExpired(String token);
    String extractRoles(String token);

}
