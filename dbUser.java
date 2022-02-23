import java.sql.*;

public class dbUser
{
    public Integer userID;
    public String username;
    public String password;
    public String email;
    public String name;
    public String bio;
    public String favBand;
    public String favSong;

    public dbUser()
    {

    }

    public static Integer create(Connection conn, dbUser createUser) throws SQLException
    {
        Integer createUserID = null;
        PreparedStatement pstmt = null;
        String query = "INSERT INTO user(username, password, email, name, bio, favBand, favSong)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, createUser.username);
            pstmt.setString(2, createUser.password);
            pstmt.setString(3, createUser.email);
            pstmt.setString(4, createUser.name);
            pstmt.setString(5, createUser.bio);
            pstmt.setString(6, createUser.favBand);
            pstmt.setString(7, createUser.favSong);
            pstmt.addBatch();
            pstmt.executeBatch();
            ResultSet res = pstmt.getGeneratedKeys();
            while(res.next())
            {
                createUserID =  res.getInt(1);
                break;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pstmt.close();
        }
        return createUserID;
    }

    public static dbUser read(Connection conn, Integer readUserID) throws SQLException
    {
        dbUser readUser = new dbUser();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM user WHERE userID = ?";
        try
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, readUserID);
            pstmt.setInt(1, 21);
            ResultSet res = pstmt.executeQuery();
            while(res.next())
            {
                readUser.userID = readUserID;
                readUser.username = res.getString("username");
                readUser.password = res.getString("password");
                readUser.email = res.getString("email");
                readUser.name = res.getString("name");
                readUser.bio = res.getString("bio");
                readUser.favBand = res.getString("favBand");
                readUser.favSong = res.getString("favSong");
                break;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pstmt.close();
        }
        return readUser;
    }

    public static int update(Connection conn, dbUser updatedUser) throws SQLException
    {
        int rowsUpdated = 0;
        PreparedStatement pstmt = null;
        String query = "UPDATE user SET username = ?, password = ?, email = ?, name = ?, " + 
                        "bio = ?, favBand = ?, favSong = ? WHERE userID = ?";
        try
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, updatedUser.username);
            pstmt.setString(2, updatedUser.password);
            pstmt.setString(3, updatedUser.email);
            pstmt.setString(4, updatedUser.name);
            pstmt.setString(5, updatedUser.bio);
            pstmt.setString(6, updatedUser.favBand);
            pstmt.setString(7, updatedUser.favSong);
            pstmt.setInt(8, updatedUser.userID);
            rowsUpdated = pstmt.executeUpdate();
        }
        
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pstmt.close();
        }
        return rowsUpdated;
    }

    public static int delete(Connection conn, Integer userID) throws SQLException
    {
        int rowsDeleted = 0;
        PreparedStatement pstmt = null;
        String query = "DELETE FROM user WHERE userID = ?";
        try
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userID);
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
        return rowsDeleted;
    }
}


