package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;


public class CricketerTest {

@Test
    public void testFieldExistence() {
        Class<Cricketer> personClass = Cricketer.class;
        
        assertFieldExists(personClass, "name");
        assertFieldExists(personClass, "age");
        assertFieldExists(personClass, "country");
    }
    
    private void assertFieldExists(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            assertNotNull(field);
        } catch (NoSuchFieldException e) {
            fail("Field '" + fieldName + "' does not exist in the " + clazz.getSimpleName() + " class.");
        }
    }


@Test
void testCricketerNameGetterAndSetter() {

        Cricketer cricketer = new Cricketer();

        cricketer.setName("Virat Kohli");

        assertEquals("Virat Kohli", cricketer.getName());

    }

 @Test

    void testCricketerAgeGetterAndSetter() {

        Cricketer cricketer = new Cricketer();

        cricketer.setAge(25);

        assertEquals(25, cricketer.getAge());

    }

   
@Test
void testCricketerCountryGetterAndSetter() {

        Cricketer cricketer = new Cricketer();

        cricketer.setCountry("India");

        assertEquals("India", cricketer.getCountry());

    }

    @Test
        public void testModelFile() {

        String filePath = "src/main/java/com/examly/springapp/Cricketer.java";

        File file = new File(filePath);

        assertTrue(file.exists() && file.isFile());

    }




 @Test
    public void testAddCricketers() {
        ArrayList<Cricketer> cricketersList = new ArrayList<>();

        cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
        cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));

        assertEquals(2, cricketersList.size(), "Number of cricketers added should be 2");
    }

    @Test
    public void testSortCricketersByName() {
        ArrayList<Cricketer> cricketersList = new ArrayList<>();
        cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
        cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));
        cricketersList.add(new Cricketer("Kane Williamson", 30, "New Zealand"));

        Collections.sort(cricketersList);

        assertEquals("Kane Williamson", cricketersList.get(0).getName(), "First cricketer should be Kane Williamson");
        assertEquals("Steve Smith", cricketersList.get(1).getName(), "Second cricketer should be Steve Smith");
        assertEquals("Virat Kohli", cricketersList.get(2).getName(), "Third cricketer should be Virat Kohli");
    }

    @Test
    public void testSortCricketersByAge() {
        ArrayList<Cricketer> cricketersList = new ArrayList<>();
        cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
        cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));
        cricketersList.add(new Cricketer("Kane Williamson", 30, "New Zealand"));

        Collections.sort(cricketersList, new CricketerAgeComparator());

        assertEquals("Kane Williamson", cricketersList.get(0).getName(), "Youngest cricketer should be Kane Williamson");
        assertEquals("Steve Smith", cricketersList.get(1).getName(), "Second youngest cricketer should be Steve Smith");
        assertEquals("Virat Kohli", cricketersList.get(2).getName(), "Oldest cricketer should be Virat Kohli");
    }
        
    private static final String JDBC_URL = "jdbc:mysql://localhost/bookstore";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "examly";

    private Connection connection;

    @After
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testConnection() throws Exception{
        connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        assertNotNull(connection);
        connection.close();
    }


    
    @Test
    public void testRetrieveData() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        String query = "SELECT * FROM cricketer";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Assuming that the ResultSet contains multiple rows, loop through them
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String country = resultSet.getString("country");

                assertNotNull(name);
                assertNotNull(age); // Update the age threshold as needed
                assertNotNull(country);
                //System.out.println("Display the cricketername"+name);
            }
        }
    }

    

    




    
}
    


