/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hardiku
 */
public class JacksonJsonConverter
{

    ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) 
    {
        JacksonJsonConverter obj = new JacksonJsonConverter();
        obj.convertObjToJson();
        obj.convertJsonToObject();
    }

    private void convertObjToJson()
    {
        

        Staff staff = createDummyObject();

        try {
            // Convert object to JSON string and save into a file directly
            mapper.writeValue(new File("D:\\staff.json"), staff);

            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(staff);
            System.out.println(jsonInString);

            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonInString);
            //controlled views
            String normalView = mapper.writerWithView(ObjViews.Normal.class).writeValueAsString(staff);
            System.out.println("Normal View\n"+normalView);
            
            String managerView = mapper.writerWithView(ObjViews.Manager.class).writeValueAsString(staff);
            System.out.println("Manager View\n"+managerView);
            

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertJsonToObject() {
        ObjectMapper mapper = new ObjectMapper();

        try 
        {
            // Convert JSON string from file to Object
            Staff staff = mapper.readValue(new File("D:\\staff.json"), Staff.class);
            System.out.println(staff);

            // Convert JSON string to Object
            String jsonInString = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
            Staff staff1 = mapper.readValue(jsonInString, Staff.class);
            //Pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff1);
            System.out.println(prettyStaff1);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Staff createDummyObject()
    {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(33);
        staff.setPosition("Developer");
        staff.setSalary(new BigDecimal("7500"));

        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");

        staff.setSkills(skills);

        return staff;

    }
}
