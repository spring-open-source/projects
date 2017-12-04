package jdbc.repository;

/**
 *
 * @author hardiku
 */
public class User extends BaseModel
{

    private String username;
    private String password;

    private String fullname;

    private String role;

    protected void setUsername(String username)
    {
        this.username = username;
    }

    protected void setPassword(String password)
    {
        this.password = password;
    }

    protected void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    protected void setRole(String role)
    {
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFullname()
    {
        return fullname;
    }

    public String getRole()
    {
        return role;
    }

    public User(
            String id,
            String username,
            String password,
            String fullname,
            String role)
    {
        super(id);
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

}
