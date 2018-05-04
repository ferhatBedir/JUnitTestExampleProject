package assertions;

public interface ServiceInterface {

    void accountId(Integer id);

    void addAccount(String username);

    void removeAccount(Integer id);

    String getUserName(Integer id);

    Integer getUserId(Integer id);

}
