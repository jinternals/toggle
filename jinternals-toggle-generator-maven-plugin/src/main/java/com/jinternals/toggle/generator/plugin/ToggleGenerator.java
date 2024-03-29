package com.jinternals.toggle.generator.plugin;

import com.jinternals.toggle.core.definition.ToggleDefinitions;
import com.jinternals.toggle.generator.plugin.exception.ToggleGeneratorException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

import static com.jinternals.toggle.core.constants.ToggleConstants.TOGGLE_FILE_NAME;
import static com.jinternals.toggle.generator.plugin.factory.Factory.*;
import static java.io.File.separatorChar;

@Mojo(name = "generate")
public class ToggleGenerator extends AbstractMojo {

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Parameter(property = "fileName", defaultValue = TOGGLE_FILE_NAME)
    private String fileName;

    @Parameter(property = "packageName", defaultValue = "")
    private String packageName;

    @Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}")
    private final File outputDirectory = new File("");

    @Parameter(property = "sourceDirectory", defaultValue = "${project.build.sourceDirectory}")
    private final File sourceDirectory = new File("");

    @Parameter(property = "resourcesDirectory", defaultValue = "${project.build.resources[0].directory}")
    private final File resourcesDirectory = new File("");


    public void execute()
            throws MojoExecutionException {

        File file = new File(resourcesDirectory.getPath() + separatorChar + fileName);

        if (!checkDirectoryExists(file)) {
            throw new ToggleGeneratorException("File or Directory don't exist");
        }

        ToggleDefinitions toggleDefinitions = toggleDefinitionParser().parse(file);

        getLog().info("ToggleDefinitions : " + toggleDefinitions);

        generateCode(toggleDefinitions);
        generateMetadata(toggleDefinitions);

    }

    private void generateMetadata(ToggleDefinitions toggleDefinitions) {
        File generateCodeOutputDirectory = new File(this.outputDirectory.toString() + separatorChar + "/classes/META-INF");
        springMetadataGenerator()
                .generate(toggleDefinitions, packageName, generateCodeOutputDirectory);

        getLog().info("outputDirectory " + generateCodeOutputDirectory);
    }

    private void generateCode(ToggleDefinitions toggleDefinitions) {
        File generateCodeOutputDirectory = new File(this.outputDirectory.toString() + separatorChar + "/generated-sources/java");
        toggleCodeGenerator()
                .generate(toggleDefinitions, packageName, generateCodeOutputDirectory);


        getLog().info("outputDirectory " + this.outputDirectory);
        getLog().info("sourceDirectory " + sourceDirectory);

        project.addCompileSourceRoot(generateCodeOutputDirectory.getPath());
    }


    private boolean checkDirectoryExists(File file) {
        getLog().info("Checking file : " + file);
        if (file.exists()) {
            getLog().info("File exist : " + file);
            return true;
        }
        getLog().info("File not exist : " + file);
        return false;
    }

    protected String getPackageName() {
        return packageName;
    }

    protected File getOutputDirectory() {
        return outputDirectory;
    }

    protected String getFileName() {
        return fileName;
    }
}
