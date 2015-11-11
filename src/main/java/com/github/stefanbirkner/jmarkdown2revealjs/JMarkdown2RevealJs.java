package com.github.stefanbirkner.jmarkdown2revealjs;

import org.apache.commons.io.IOUtils;
import org.pegdown.LinkRenderer;
import org.pegdown.PegDownProcessor;
import org.pegdown.ToHtmlSerializer;
import org.pegdown.ast.HeaderNode;
import org.pegdown.ast.RootNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Converts Markdown files to reveal.js presentations.
 * <p>Use a standard Markdown file. JMarkdown2RevealJs create a new slide
 * whenever it encounters an `h1` or `h2`. Here is an example file with
 * three slides.
 * <pre>
 * Title Slide
 * ===========
 *
 * First Slide
 * -----------
 *
 * content of first slide
 *
 * Second Slide
 * ------------
 *
 * content of second slide
 * </pre>
 * Such a file is converted with the following two lines.
 * <pre>
 * String markdown = readMarkdown(); //your code that reads the markdown
 * String deckJsHtml = new JMarkdown2RevealJs().convert(markdown);
 * </pre>
 * <p>JMarkdown2RevealJs uses relative URLs for the CSS and JavaScript files by
 * default. You can create HTML files with different URLs by providing a
 * configuration with a prefix for the URLs.
 * <pre>
 * Configuration configuration = new Configuration("http://your.domain/");
 * String markdown = readMarkdown(); //your code that reads the markdown
 * String revealJsHtml = new JMarkdown2RevealJs(configuration).convert(markdown);
 * </pre>
 *
 * @since 0.1.0
 */
public class JMarkdown2RevealJs {
    private static final String NO_URL_PREFIX = "";
    private static final Configuration DEFAULT_CONFIGURATION = new Configuration(NO_URL_PREFIX);
    private static final String SLIDE_END_TAG = "</section>";
    private static final String SLIDE_START_TAG = "<section>";

    private static final String TEMPLATE = readTemplate();
    private final Configuration configuration;

    public JMarkdown2RevealJs() {
        this(DEFAULT_CONFIGURATION);
    }

    public JMarkdown2RevealJs(Configuration configuration) {
        this.configuration = configuration;
    }

    private static String readTemplate() {
        InputStream resource = JMarkdown2RevealJs.class.getResourceAsStream("boilerplate.html");
        try {
            return IOUtils.toString(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts Markdown files to reveal.js presentations.
     *
     * @param markdown the slides written in markdown
     * @return an HTML file that can be used with reveal.js JavaScript and CSS.
     */
    public String convert(String markdown) {
        String htmlContent = convertMarkdownToHtml(markdown);
        String templateWithFinalUrls = TEMPLATE.replace("{{urlprefix}}", configuration.getCssAndJavaScriptUrlPrefix());
        return templateWithFinalUrls.replace("{{slides}}", htmlContent);
    }

    private String convertMarkdownToHtml(String markdown) {
        RootNode ast = new PegDownProcessor().parseMarkdown(markdown.toCharArray());
        return new Serializer().toHtml(ast) + "\n" + SLIDE_END_TAG;
    }

    private static class Serializer extends ToHtmlSerializer {
        private static final List<Integer> HEADLINE_LEVELS_THAT_START_A_NEW_SLIDE = asList(1, 2);
        private boolean firstSlideStarted = false;

        public Serializer() {
            super(new LinkRenderer());
        }

        @Override
        public void visit(HeaderNode node) {
            if (HEADLINE_LEVELS_THAT_START_A_NEW_SLIDE.contains(node.getLevel()))
                startNewSlide();
            super.visit(node);
        }

        private void startNewSlide() {
            if (firstSlideStarted)
                printer.println().print(SLIDE_END_TAG).println();
            printer.print(SLIDE_START_TAG);
            firstSlideStarted = true;
        }
    }
}
