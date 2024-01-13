package {{cookiecutter.package}};

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppUnitTests {

    private final App app = new App();

    @Test
    void has_a_greeting() {
        assertThat(app.greeting())
            .as("should have a greeting")
            .isNotNull();
    }

}
