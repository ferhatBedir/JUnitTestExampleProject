package assertions;


public class Service implements ServiceInterface {


    public void accountId(Integer id) {
        System.out.println("(Account Id --> " + id + " )");
    }

    public void addAccount(String username) {
        System.out.println("(User Name --> " + username + " )");
    }

    public void removeAccount(Integer id) {
        System.out.println("(Remove Account Id --> " + id + " )");
    }

    public String getUserName(Integer id) {
        return ("(User Name --> " + id + " )");
    }

    public Integer getUserId(Integer id) {
        return (id);
    }


}
