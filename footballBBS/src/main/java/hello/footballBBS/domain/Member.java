package hello.footballBBS.domain;

import java.util.Date;

public class Member {
    Long memberNumber;
    String id;
    String password;
    String name;
    String email;
    Date regDate;

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Long getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Long memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
