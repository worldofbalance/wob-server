package db;

// Java Imports
import javax.sql.DataSource;

/*
 * Here are the dbcp-specific classes.
 * Note that they are only used in the setupDataSource method. In normal use,
 * your classes interact only with the standard JDBC API.
 */
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;

/**
 * The ConnectionPool class prepares a data source for use. Consists of a
 * connection pool to establish multiple connections.
 */
public class ConnectionPool {

    public static DataSource setupDataSource(String connectURI) {
        /*
         * First, we'll need a ObjectPool that serves as the
         * actual pool of connections.
         *
         * We'll use a GenericObjectPool instance, although
         * any ObjectPool implementation will suffice.
         *
         * Parameters:
         * - PoolableObjectFactory - looks like it can be set to default if NULL
         * is given for parameter
         * - int maxActive - maximum number of objects that can be borrowed from
         * the pool at one time; default is 8; non-positive number for limitless pool;
         * - byte whenExhaustedAction - specifies the behavior of the
         * borrowObject() method when the pool is exhausted; default is BLOCK;
         * - maxWait - how long (milliseconds) will the pool block the request
         * for new connection if pool is exhausted; if non-positive value given, blocks
         * indefinitely; default value -1;
         * - testOnBorrow - will or will not the pool attempt to validate each
         * object before it is returned from the borrowObject() method
         * - testOnReturn - will or will not the pool attempt to validate each
         * object before it is returned to the pool in the returnObject(java.lang.Object) method
         *
         * Number of parameters available is even longer, but these will do the
         * job for now.
         */
        ObjectPool connectionPool = new GenericObjectPool(null,
                -1, // Default is 8 changed to 16. Can change to -1 for no limit.
                // GenericObjectPool.DEFAULT_MAX_ACTIVE,
                GenericObjectPool.DEFAULT_WHEN_EXHAUSTED_ACTION,
                GenericObjectPool.DEFAULT_MAX_WAIT,
                GenericObjectPool.DEFAULT_MAX_IDLE, true, false, 3600000,
                GenericObjectPool.DEFAULT_MAX_ACTIVE,
                GenericObjectPool.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS, true);

        /*
         * Next, we'll create a ConnectionFactory that the
         * pool will use to create Connections.
         * We'll use the DriverManagerConnectionFactory,
         * using the connect string passed by GameDB.
         */
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                connectURI, null);

        /*
         * Now we'll create the PoolableConnectionFactory, which wraps
         * the "real" Connections created by the ConnectionFactory with
         * the classes that implement the pooling functionality.
         */
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory, connectionPool, null, "SELECT * FROM `species` LIMIT 1", false, true);

        /*
         * Finally, we create the PoolingDriver itself,
         * passing in the object pool we created.
         */
        PoolingDataSource dataSource = new PoolingDataSource(connectionPool);

        return dataSource;
    }
}