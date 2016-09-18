package com.starwix;

import com.starwix.controller.api.CommissionController;
import com.starwix.controller.api.TransactionController;
import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {
  {
    assets("/", "index.html");
    assets("/assets/**");

    use(TransactionController.class);
    use(CommissionController.class);
  }

  public static void main(final String[] args) throws Throwable {
    run(App::new, args);
  }
}
