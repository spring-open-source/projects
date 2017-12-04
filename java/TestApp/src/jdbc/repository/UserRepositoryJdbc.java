package jdbc.repository;

/**
 *
 * @author hardiku
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.infinispan.stream.impl.local.ValueCacheCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository ("userRepository")
public class UserRepositoryJdbc extends AbstractJdbcRepository<User, String> implements UserRepository
{

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate template)
    {
        super(
                userRowMapper,
                userUpdaterMapper,
                "USER",
                "id",
                template);
    }

    @Override
    public User findByUsername(String username)
    {
        return this.findOneByColumn("username", username);
    }

    static RowMapper<User> userRowMapper = new RowMapper<User>()
    {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            return new User(
                    rs.getString("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullName"),
                    rs.getString("role"));
        }
    };

    static Updater<User> userUpdaterMapper = new Updater<User>()
    {
        @Override
        public void mapColumns(User t, Map<String, Object> mapping)
        {
            mapping.put("id", t.getId());
            mapping.put("username", t.getUsername());
            mapping.put("password", t.getPassword());
            mapping.put("fullName", t.getFullname());
            mapping.put("role", t.getRole());
        }
    };

    @Override
    public <S extends User> S save(S s)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<User> findAll(Iterable<String> itrbl)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends T> ValueCacheCollection<K, V> save(Iterable<S> arg0)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
