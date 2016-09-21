package com.starwix;

import com.starwix.controller.api.CommissionController;
import com.starwix.controller.api.TransactionController;
import com.starwix.entities.CommissionList;
import com.starwix.services.CommissionService;
import com.typesafe.config.Config;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.jooby.Jooby;
import org.jooby.jdbc.Jdbc;
import org.jooby.json.Gzon;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    {
        assets("/", "index.html");
        assets("/assets/**");

        use(new Jdbc("db.main"));
        use(new Gzon());

        use(TransactionController.class);
        use(CommissionController.class);

        onStart(() -> {
            startLiquibase();
            saveCommissionInDB();
        });
    }

    private void startLiquibase() throws LiquibaseException, SQLException {
        final DataSource dataSource = require(DataSource.class);
        final JdbcConnection jdbcConnection = new JdbcConnection(dataSource.getConnection());
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);
        final Liquibase liquibase = new liquibase.Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts(), new LabelExpression());
    }

    private void saveCommissionInDB() {
        final Config config = require(Config.class);
        final File file = new File(config.getString("file.commissions"));
        final CommissionService commissionService = require(CommissionService.class);
        final CommissionList commissionList = commissionService.parseFile(file);
        commissionService.deleteAll();
        commissionService.save(commissionList.getCommissions());
    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }
}
