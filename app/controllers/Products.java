package controllers;

import models.Product;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.errors.*;
import views.html.products.*;



import javax.inject.Inject;

/**
 * User: vitali 24/10/17 11:03
 */

public class Products extends Controller {

    @Inject
    private FormFactory formFactory;


    public Result list() {
        System.out.println("getAll");
        return ok(list.render(Product.find.all()));
    }

    public Result create() {
        Form<Product> form = formFactory.form(Product.class);
        return ok(create.render(form));
    }

    public Result show(Long ean) {
        Result result;
        Product product = Product.find.byId(ean);
        if (product != null) {
            result = ok(show.render(product));
        } else
            result = notFound(_404.render("element with ean " + ean + " not found"));
        return result;
    }

    public Result save() {
        Form<Product> boundForm = formFactory.form(Product.class).bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "Please, correct the form below");
            return badRequest(create.render(boundForm));
        } else {
            Product product = boundForm.get();
//            product.ean=System.currentTimeMillis();
//            if (Product.find.byId(product.ean) != null) {
//                flash("error", "product with ean " + product.ean + " is exist");
//                return badRequest(create.render(boundForm));
//            }
            System.out.println(product.ean);
            product.save();
            flash("success",
                    String.format("Successfully added product %s", product));
            System.out.println("save");
            return GO_HOME();
        }
    }

    public Result edit(Long id) {
        final Product product = Product.find.byId(id);
        if (product == null) {
            return notFound(_404.render("product with id " + id + " not exist"));
        }
        Form<Product> formProduct = formFactory.form(Product.class).fill(product);
        return ok(edit.render(id,formProduct));

    }

    public Result update(Long ean) {
        System.out.println("update");
        Form<Product> productForm = formFactory.form(Product.class).bindFromRequest();
        if (productForm.hasErrors()) {
            return badRequest("error in form");
        } else {
            Product newP = productForm.get();
            Product oldP = Product.find.byId(ean);
            oldP.name = newP.name;
            oldP.description = newP.description;
            oldP.update();
            flash("warning", "Product with name:" +oldP.name+" was changed");
            return GO_HOME();
        }
    }

    private Result GO_HOME() {
        return redirect(controllers.routes.Products.list());
    }

    public Result index() {
        return ok(list.render(Product.find.all()));
    }


    public Result delete(Long id) {
        System.out.println("delete " + id);
        final Product product = Product.find.byId(id);
        if (product != null) {
            product.delete();
            flash("warning",

                    String.format("Successfully delete product ", product));
            return ok();
        }
        final String MSG= String.format("product with id  %s is not exist" + id );
        flash("error",MSG);
        return notFound(_404.render(MSG));
    }
}
