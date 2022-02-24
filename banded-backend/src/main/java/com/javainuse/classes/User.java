package com.javainuse.classes;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.sql.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userID")
    private Integer userID;
    private String name;
    @Column(name="username")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email = "user@example.com";
    @Column(name="bio")
    private String bio;
    @Column(name="favBand")
    private String favBand;
    @Column(name="favSong")
    private String favSong;
    @Enumerated(EnumType.STRING)
    private UserAuthorities userAuthorities = UserAuthorities.USER;
    private Boolean locked = false;
    private Boolean enabled = true;
    //steven

    //A constructor that takes in all fields besides ID, which is autogenerated. We can add more constructors
    //based on what we need from the registration method
    public User(String name, String userName, String password, String email, String bio, String favBand,
                String favSong, UserAuthorities userAuthorities, Boolean locked, Boolean enabled) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.favBand = favBand;
        this.favSong = favSong;
        this.userAuthorities = userAuthorities;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userAuthorities.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    public void userFollowTopic(String username, String topic) throws SQLException
    {
        int userID = getUserIDByUsername(username);
        int topicID = getTopicIDByTopic(topic);

        String dbURL = "jdbc:mysql://localhost:3306/cs307group27?useSSL=false";
        String dbUsername = "Group27";
        String dbPassword = "Pcs307g#27";

        PreparedStatement pstmt = null;
        String query = "INSERT INTO topicfollower(topicID, userID) VALUES(?, ?);";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword))
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, topicID);
            pstmt.setInt(2, userID);
            pstmt.executeQuery();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pstmt.close();
        }
    }

    private int getUserIDByUsername(String username)
    {
        String dbURL = "jdbc:mysql://localhost:3306/cs307group27?useSSL=false";
        String dbUsername = "Group27";
        String dbPassword = "Pcs307g#27";

        PreparedStatement pstmt = null;
        int userID = -1;
        String query = "SELECT userID FROM user WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();
            while(res.next())
            {
                userID = res.getInt("userID");
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
        return userID;
    }

    //TODO: this method should be moved to the topic class
    private int getTopicIDByTopic(String topic)
    {
        String dbURL = "jdbc:mysql://localhost:3306/cs307group27?useSSL=false";
        String dbUsername = "Group27";
        String dbPassword = "Pcs307g#27";

        PreparedStatement pstmt = null;
        int topicID = -1;
        String query = "SELECT topicID FROM topic WHERE topicName = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)
        {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, topic);
            ResultSet res = pstmt.executeQuery();
            while(res.next())
            {
                topicID = res.getInt("topicID");
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
        return topicID;
    }
}