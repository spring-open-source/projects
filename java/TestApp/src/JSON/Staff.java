package JSON;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author hardiku
 */
@JsonRootName("staff")
public class Staff {

    private String name;
    private int age;
    private String position;
    private BigDecimal salary;
    private List<String> skills;

    public Staff() {
    }
    
    @JsonView(ObjViews.Normal.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(ObjViews.Normal.class)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonView(ObjViews.Normal.class)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @JsonView(ObjViews.Manager.class)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @JsonView(ObjViews.Manager.class)
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Staff{" + "name=" + name + ", age=" + age + ", position=" + position + ", salary=" + salary + ", skills=" + skills + '}';
    }
        
}
