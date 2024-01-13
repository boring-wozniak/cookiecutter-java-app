package {{cookiecutter.package}};

import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

final class App {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    String greeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        final var app = new App();
        LOG.info("The app says {}", app.greeting());
    }

}
