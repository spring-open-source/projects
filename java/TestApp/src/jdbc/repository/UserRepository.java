package jdbc.repository;

/**
 *
 * @author hardiku
 */
public interface UserRepository
{

    public User findByUsername(String username);

    public <S extends User> Iterable<S> save(Iterable<S> itrbl);

}
