package com.javainuse.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
public class DeletionController 
{
    @RequestMapping(path = "api/v1/delete")
    public String deleteUser(@RequestParam(required=true) String email) throws SQLException
    {
        String dbURL = "jdbc:mysql://localhost:3306/cs307group27?useSSL=false";
        String dbUsername = "Group27";
        String dbPassword = "Pcs307g#27";
        int rowsDeleted = 0;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM user WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword))
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rowsDeleted = pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pstmt.close();
        }
        assert(rowsDeleted == 1);
        return "User Deleted";
    }
}
