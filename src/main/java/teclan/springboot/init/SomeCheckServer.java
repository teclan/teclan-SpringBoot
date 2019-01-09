package teclan.springboot.init;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @ClassName: SomeCheckServer
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/9 15:22
 **/

@Component
@Order(value = 0)
public class SomeCheckServer  implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(SomeCheckServer.class);


    @Autowired
    private  DatabaseCheck databaseCheck;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("\n程序自检....\n");

        databaseCheck.run();
    }
}
