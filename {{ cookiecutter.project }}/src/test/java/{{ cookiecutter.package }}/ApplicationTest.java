package {{ cookiecutter.package }};

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    @Test
    void a_test() {
        assertThat("My test")
            .isEqualTo("My test");
    }

}
