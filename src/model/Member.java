package model;
import java.time.LocalDateTime;
public class Member {
    private int id;
    private String name;
    private String email;
    private LocalDateTime joinedDate;
    public Member(int id, String name, String email, LocalDateTime joinedDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.joinedDate = joinedDate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
