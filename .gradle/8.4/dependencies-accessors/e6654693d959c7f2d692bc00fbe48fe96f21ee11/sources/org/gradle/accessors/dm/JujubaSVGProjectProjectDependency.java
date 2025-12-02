package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class JujubaSVGProjectProjectDependency extends DelegatingProjectDependency {

    @Inject
    public JujubaSVGProjectProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":jujubasvg"
     */
    public JujubasvgProjectDependency getJujubasvg() { return new JujubasvgProjectDependency(getFactory(), create(":jujubasvg")); }

    /**
     * Creates a project dependency on the project at path ":sampleApp"
     */
    public SampleAppProjectDependency getSampleApp() { return new SampleAppProjectDependency(getFactory(), create(":sampleApp")); }

}
