package com.jinternals.toggle.generator.plugin;

import com.jinternals.toggle.generator.plugin.exception.ToggleGeneratorException;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static java.lang.String.format;
import static org.junit.Assert.*;

@Ignore
public class ToggleGeneratorTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public TestResources resources = new TestResources();

    @Test
    public void testToggleGeneratorMojo() throws Exception {

        ToggleGenerator mojo = (ToggleGenerator) this.rule.lookupConfiguredMojo(loadPom("toggle-test"), "generate");
        assertNotNull(mojo);

        assertEquals("toggles.yml",mojo.getFileName());
        assertEquals("com.cloud.example.toggle",mojo.getPackageName());
        mojo.execute();

        String filePath = format("%s%s", mojo.getOutputDirectory().toString(), "/generated-sources/java/com/cloud/example/toggle/Toggles.java");
        File file = new File(filePath);
        assertTrue(file.exists());

    }

    @Test(expected = ToggleGeneratorException.class)
    public void testInvalidToggleGeneratorMojo() throws Exception {

        ToggleGenerator mojo = (ToggleGenerator) this.rule.lookupConfiguredMojo(loadPom("invalid-toggle-test"), "generate");
        assertNotNull(mojo);

        assertEquals("toggles.yml",mojo.getFileName());
        assertEquals("com.cloud.example.toggle",mojo.getPackageName());

        mojo.execute();


    }

    private File loadPom(String folderName) {
        return new File("src/test/resources/", folderName);
    }
}