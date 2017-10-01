package br.com.tsi.listacasamentosaas.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import static java.util.Collections.singletonList;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Value("${spring.data.mongodb.username}")
    private String mongoUsername;

    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;


    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    public Mongo mongo() throws Exception {
        //return new MongoClient(mongoHost, mongoPort);
        return new MongoClient(singletonList(new ServerAddress(mongoHost, mongoPort)),
                singletonList(MongoCredential.createCredential(mongoUsername, mongoDB, mongoPassword.toCharArray())));
    }

    @Override
    protected String getMappingBasePackage() {
        return "br.com.tsi.listacasamentosaas";
    }
}