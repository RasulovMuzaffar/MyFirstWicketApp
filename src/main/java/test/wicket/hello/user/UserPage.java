package test.wicket.hello.user;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import test.wicket.model.dao.UserModelDao;
import test.wicket.model.dao.UserModelDaoImpl;
import test.wicket.model.pojo.UserModel;

import java.io.Serializable;
import java.util.List;

public class UserPage extends WebPage implements Serializable {

    UserModelDao umd = new UserModelDaoImpl();
    List<UserModel> users = umd.listUsers();

    private ModalWindow modalWindow;

    public UserPage() {
        if (users.isEmpty()) {
            UserModelDaoImpl umdi = new UserModelDaoImpl();
            umdi.initializeList();
        }
        UserModel userModel = new UserModel();
        Form<?> form = new Form("form");
        TextField<String> id = new TextField<String>("id", new PropertyModel<String>(userModel, "id"));
        id.setOutputMarkupId(true);
        TextField<String> fName = new TextField<String>("fName", new PropertyModel<String>(userModel, "firstName"));
        fName.setOutputMarkupId(true);
        TextField<String> lName = new TextField<String>("lName", new PropertyModel<String>(userModel, "lastName"));
        lName.setOutputMarkupId(true);
        TextField<String> age = new TextField<String>("age", new PropertyModel<String>(userModel, "age"));
        age.setOutputMarkupId(true);

        Button addBtn = new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();

                umd.addUser(new UserModel(userModel.getId(),
                        userModel.getFirstName(),
                        userModel.getLastName(),
                        userModel.getAge()));
            }
        };
        Button editBtn = new Button("editBtn") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                umd.updateUser(new UserModel(userModel.getId(),
                        userModel.getFirstName(),
                        userModel.getLastName(),
                        userModel.getAge()));

                addBtn.setVisible(true);
                super.setVisible(false);

                id.setModelObject("");
                fName.setModelObject("");
                lName.setModelObject("");
                age.setModelObject("");
            }
        };

        editBtn.setVisible(false);
        add(form);
        form.add(id);
        form.add(fName);
        form.add(lName);
        form.add(age);
        form.add(addBtn);
        form.add(editBtn);


        add(new ListView<UserModel>("listView", users) {
            public void populateItem(final ListItem<UserModel> item) {
                final UserModel data = item.getModelObject();
                item.add(new Label("id", data.getId()));
                item.add(new Label("fName", data.getFirstName()));
                item.add(new Label("lName", data.getLastName()));
                item.add(new Label("age", data.getAge()));

                item.add(new Link<String>("edit") {
                    @Override
                    public void onClick() {
                        System.out.println("edit " + data.toString());
                        id.setModelObject("" + data.getId());
                        fName.setModelObject(data.getFirstName());
                        lName.setModelObject(data.getLastName());
                        age.setModelObject("" + data.getAge());
                        editBtn.setVisible(true);
                        addBtn.setVisible(false);
                    }
                });

                item.add(new Link<String>("delete") {
                    @Override
                    public void onClick() {
                        boolean b = umd.deleteUser(data);
                        if (b) {
                            System.out.println("deleted : " + data.toString());
                        }
                    }
                });
            }
        });
    }
}
