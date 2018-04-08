package Helper;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/SEProject";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public boolean UserLoginValidation(String user_name, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE email=\"" + user_name + "\" AND password=\"" + password + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                returnValue = false;
            } else {
                returnValue = true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (
                SQLException se)

        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e)

        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally

        {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return returnValue;
    }

    public String UserRegisterValidation(String nickname, String email, String password, String confirm_password) {
        String Validation_result = "";

        if (!this.EmailFormValidation(email)) {
            Validation_result += "Email input has wrong form<br/>";
        }

        if (!this.nicknameValidation(nickname)) {
            Validation_result += "Nickname must have less than 255 characters<br/>";
        }

        if (!this.passwordValidation(password)) {
            Validation_result += "Password must contain at " +
                    "least eight characters, at least one number and both lower and uppercase letters and special characters<br/>";
        }

        if (!this.passwordConfirm(password,confirm_password)){
            Validation_result+="Confirm password does not match";
        }
        if (!this.UniqueEmailValidation(email)){
            Validation_result+="Registed email";
        }
        return Validation_result;
    }

    public boolean UniqueEmailValidation(String email) {
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = false;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE email=\"" + email + "\"";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                returnValue = true;
            } else {
                returnValue = false;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (
                SQLException se)

        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e)

        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally

        {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return returnValue;
    }

    public boolean nicknameValidation(String nickname) {
        return nickname.length() < 255 && nickname.length() != 0;
    }

    public boolean EmailFormValidation(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile( "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean passwordValidation(String password) {
        Pattern VALID_PASSWORD = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = VALID_PASSWORD.matcher(password);
        return matcher.find();
        //Regex for password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters
    }

    public boolean passwordConfirm(String password, String confirm_password) {
        return password.equals(confirm_password);
    }
}
