package dao;
import db.DBConnection;
import model.Member;
import java.sql.*;


public class MemberDAO {
    public void addMember(Member member) throws SQLException{
        String sql = "INSERT INTO member(name,email.joinedDate) VALUES(?,?,?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,member.getName());
            ps.setString(2, member.getEmail());
            ps.setTimestamp(3, Timestamp.valueOf(member.getJoinedDate()));
            int rows = ps.executeUpdate();
            if(rows>0){
                System.out.println("Member added succesfully");
            }
        }catch (SQLException e){
            System.out.println("Error adding member: " + e.getMessage());
        }
    }
    //fetching member details by ID
    public void getMemberById(int memberId) throws SQLException {
        String sql = "SELECT * FROM member WHERE id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Member details: ");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Joined Date: " + rs.getTimestamp("joined_date"));
            }else{
                System.out.println("No member found with ID: " + memberId);
            }
        }
    }
    //member deletion query
    public void deleteMember(int memberId) throws  SQLException{
        String sql = "DELETE FROM member where id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, memberId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member with ID " + memberId + " deleted successfully.");
            } else {
                System.out.println("No member found with ID " + memberId);
            }
        }
    }
}
