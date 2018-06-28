package test.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import test.wicket.hello.user.UserPage;

public class MyApp extends WebApplication{
    @Override
    public Class<? extends Page> getHomePage() {
        return UserPage.class;
    }
}
