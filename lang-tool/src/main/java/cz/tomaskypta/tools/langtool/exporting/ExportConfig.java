package cz.tomaskypta.tools.langtool.exporting;

import java.util.HashSet;
import java.util.Set;

import cz.tomaskypta.tools.langtool.CommandlineArguments;
import cz.tomaskypta.tools.langtool.CommonConfig;

/**
 * Created by tomas on 03.10.14.
 */
public class ExportConfig extends CommonConfig {

    public String inputExportProject;
    public String outputFile;
    public Set<String> additionalResources;

    public ExportConfig() {
        super();
        this.additionalResources = new HashSet<String>();
    }

    public ExportConfig(final ExportConfig other) {
        super(other);
        this.inputExportProject = other.inputExportProject;
        this.outputFile = other.outputFile;
        this.additionalResources = new HashSet<String>(other.additionalResources);
    }

    public ExportConfig(final CommandlineArguments args) {
        super(args);
        this.inputExportProject = args.getExportProject();
        this.outputFile = args.getOutputFile();
        this.additionalResources = new HashSet<String>();
        addAdditionalResources(args.getAdditionalResources());
    }

    private void addAdditionalResources(final String additionalResourcesRaw) {
        if (additionalResourcesRaw == null) {
            return;
        }
        for (String resName : additionalResourcesRaw.split(":")) {
            if (!resName.endsWith(".xml")) {
                resName = resName + ".xml";
            }
            additionalResources.add(resName);
        }
    }
}
