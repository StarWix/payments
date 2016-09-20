package com.starwix;

import com.starwix.controller.api.CommissionController;
import com.starwix.controller.api.TransactionController;
import com.starwix.entities.CommissionList;
import com.starwix.services.CommissionService;
import com.typesafe.config.Config;
import org.jooby.Jooby;

import java.io.File;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    {
        assets("/", "index.html");
        assets("/assets/**");

        use(TransactionController.class);
        use(CommissionController.class);

        onStart(() -> {
            final Config config = require(Config.class);
            final File file = new File(config.getString("file.commissions"));

            final CommissionService commissionService = require(CommissionService.class);
            final CommissionList commissionList = commissionService.load(file);
            commissionService.save(commissionList.getCommissions());
        });
    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }
}
