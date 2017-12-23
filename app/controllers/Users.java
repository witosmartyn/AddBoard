package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.users.login;
import views.html.users.register;

/**
 * User: vitali 23/12/17 16:34
 */

public class Users extends Controller {

    public static class Log {

        public String email;
        public String password;

    }

    public Result register() {
        System.out.println("register");
        return ok(register.render());
    }
    public Result login() {
        System.out.println("login");
        return ok(login.render());
    }
}
