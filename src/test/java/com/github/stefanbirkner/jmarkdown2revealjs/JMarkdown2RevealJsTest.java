package com.github.stefanbirkner.jmarkdown2revealjs;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class JMarkdown2RevealJsTest {
    @Test
    public void convertsThreeSlides() throws Exception {
        String markdown = getResource("threeSlides.md");
        String html = new JMarkdown2RevealJs().convert(markdown);
        assertThat(html).isEqualTo(getResource("threeSlides.html"));
    }

    @Test
    public void convertsThreeSlidesWithUrlPrefix() throws Exception {
        Configuration configuration = new Configuration("http://dummy.domain/");
        String markdown = getResource("threeSlides.md");
        String html = new JMarkdown2RevealJs(configuration).convert(markdown);
        assertThat(html).isEqualTo(getResource("threeSlidesWithUrlPrefix.html"));
    }

    private String getResource(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        return IOUtils.toString(resource);
    }
}
