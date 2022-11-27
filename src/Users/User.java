package Users;

public class User {
    private Long nCC;
    private String name;
    private String email;
    private String sex;
    private String birthDate;
    private Long phoneNumber;

    public User(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) {
        this.nCC = nCC;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
}
