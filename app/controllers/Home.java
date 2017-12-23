package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.users.login;
import views.html.users.register;

/**
 * User: vitali 23/12/17 16:19
 */

public class Home extends Controller {

    public Result index() {
        System.out.println("index");
        return ok(index.render());
    }

}


