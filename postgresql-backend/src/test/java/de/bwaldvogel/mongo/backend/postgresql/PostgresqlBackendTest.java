package de.bwaldvogel.mongo.backend.postgresql;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import de.bwaldvogel.mongo.MongoBackend;
import de.bwaldvogel.mongo.MongoDatabase;
import de.bwaldvogel.mongo.backend.AbstractBackendTest;

public class PostgresqlBackendTest extends AbstractBackendTest {

    private static Jdbc3PoolingDataSource dataSource;

    @BeforeClass
    public static void initializeDataSource() {
        dataSource = new Jdbc3PoolingDataSource();
        dataSource.setDataSourceName(PostgresqlBackendTest.class.getSimpleName());
        dataSource.setApplicationName(PostgresqlBackendTest.class.getSimpleName());
        dataSource.setDatabaseName("mongo-java-server-test");
        dataSource.setUser("mongo-java-server-test");
        dataSource.setPassword("mongo-java-server-test");
        dataSource.setMaxConnections(5);
    }

    @AfterClass
    public static void closeDataSource() {
        dataSource.close();
    }

    @Override
    protected MongoBackend createBackend() throws Exception {
        PostgresqlBackend backend = new PostgresqlBackend(dataSource);
        for (String db : Arrays.asList(TEST_DATABASE_NAME, OTHER_TEST_DATABASE_NAME)) {
            MongoDatabase mongoDatabase = backend.openOrCreateDatabase(db);
            mongoDatabase.drop();
        }
        return backend;
    }

    @Override
    public void testCompoundSparseUniqueIndex() throws Exception {
        assumeStrictTests();
        super.testCompoundSparseUniqueIndex();
    }

    @Override
    public void testCompoundSparseUniqueIndexOnEmbeddedDocuments() throws Exception {
        assumeStrictTests();
        super.testCompoundSparseUniqueIndexOnEmbeddedDocuments();
    }

    @Override
    public void testSparseUniqueIndexOnEmbeddedDocument() throws Exception {
        assumeStrictTests();
        super.testSparseUniqueIndexOnEmbeddedDocument();
    }

    @Override
    public void testSecondarySparseUniqueIndex() throws Exception {
        assumeStrictTests();
        super.testSecondarySparseUniqueIndex();
    }

    @Override
    public void testUniqueIndexWithDeepDocuments() throws Exception {
        assumeStrictTests();
        super.testUniqueIndexWithDeepDocuments();
    }

    private void assumeStrictTests() {
        Assume.assumeTrue(Boolean.getBoolean(PostgresqlBackend.class.getSimpleName() + ".strictTest"));
    }

}