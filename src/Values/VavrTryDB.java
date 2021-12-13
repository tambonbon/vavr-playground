package Values;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;

import io.vavr.control.Try;

public class VavrTryDB {
    
    static interface Database {
        void start();
    }

    public void fetchMeasurement(int geoCoord, int airlyService, int measurment)  {
        try {
            throw new SQLException();
        } catch (SQLException e) { }
    }

    private Database database; private Logger LOG; 

    public void TryDB()  {
        // Java OOP way
        try {
            database.start();
            fetchMeasurement(1, 1, 1);
            throw new SQLException();
        } catch (SQLException e) {
            LOG.log(Level.ERROR, "Cannot start database");
        }

        // vavr FP way
        Try
            .run(database::start)
            .onSuccess(ignore -> LOG.log(Level.INFO, "Database started successfuly"))
            .onFailure(exception -> LOG.log(Level.ERROR, "Cannot start database"))
            .andThen(
                () -> fetchMeasurement(1, 1, 1)
            );
    }
}
