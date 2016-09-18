package com.starwix;

import com.starwix.controller.api.CommissionController;
import com.starwix.controller.api.TransactionController;
import com.starwix.entities.CommissionList;
import com.typesafe.config.Config;
import org.jooby.Jooby;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
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

            final File file = new File(config.getString("commissions"));

            final JAXBContext jc = JAXBContext.newInstance(CommissionList.class);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();

            final CommissionList commissionList = (CommissionList) unmarshaller.unmarshal(file);

            System.out.println(commissionList);
        });
    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }
}
