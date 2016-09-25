package com.starwix;

import com.starwix.controller.api.CommissionController;
import com.starwix.controller.api.TransactionController;
import com.starwix.entities.CardInformation;
import com.starwix.entities.CommissionList;
import com.starwix.entities.requests.TransactionRequest;
import com.starwix.exceptions.WebError;
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
import org.jooby.Status;
import org.jooby.hbv.Hbv;
import org.jooby.jdbc.Jdbc;
import org.jooby.json.Gzon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    {
        use(new Jdbc("db.main"));
        use(new Hbv(CardInformation.class, TransactionRequest.class));
        use(new Gzon());

        before((req, rsp) -> logger.info(
                req.method() + " " + req.path() + "\n" +
                req.headers() + "\n" +
                req.cookies() + "\n" +
                req.params() + "\n" //+
                //TODO: contribute to jooby or create issue. Throws FileAlreadyExistsException when body() call a few times.
                // req.body().toOptional() + "\n"
        ));

        assets("/", "index.html");
        assets("/assets/**");

        use(TransactionController.class);
        use(CommissionController.class);

        onStart(() -> {
            startLiquibase();
            saveCommissionInDB();
        });

        err((req, rsp, err) -> {
            final Throwable cause = err.getCause();
            if (cause instanceof ConstraintViolationException) {
                final Set<ConstraintViolation<?>> constraints = ((ConstraintViolationException) cause).getConstraintViolations();

                final Map<Path, Object[]> errors = constraints.stream()
                        .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getExecutableParameters));
                rsp.send(errors);
            } else if (cause instanceof WebError) {
                final Map<String, Object> errors = new HashMap<>();
                errors.put("error", ((WebError) cause).getError());
                errors.put("args", ((WebError) cause).getArgs());
                rsp.status(Status.BAD_REQUEST);
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
