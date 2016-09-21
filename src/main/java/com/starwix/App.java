package com.starwix;

import com.starwix.controller.api.CommissionController;
import com.starwix.controller.api.TransactionController;
import com.starwix.entities.CardInformation;
import com.starwix.entities.CommissionList;
import com.starwix.entities.requests.TransactionRequest;
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
import org.jooby.hbv.Hbv;
import org.jooby.jdbc.Jdbc;
import org.jooby.json.Gzon;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    {
        use(new Jdbc("db.main"));
        use(new Hbv(CardInformation.class, TransactionRequest.class));
        use(new Gzon());

        assets("/", "index.html");
        assets("/assets/**");

        use(TransactionController.class);
        use(CommissionController.class);

        onStart(() -> {
            startLiquibase();
            saveCommissionInDB();
        });

        err((req, rsp, err) -> {
            Throwable cause = err.getCause();
            if (cause instanceof ConstraintViolationException) {
                Set<ConstraintViolation<?>> constraints = ((ConstraintViolationException) cause).getConstraintViolations();

                Map<Path, String> errors = constraints.stream()
                        .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
                rsp.send(errors);
            }
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
