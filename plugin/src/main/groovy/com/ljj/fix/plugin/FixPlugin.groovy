package com.ljj.fix.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project;

/**
 * Created by ljj on 2017/11/27.
 */

public class FixPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.getLogger().error("FixPlugin-------------------start");
        def libPath = project.project(':hack').buildDir.absolutePath.concat("/intermediates/classes/debug")
        project.getLogger().error("libPath:--->"+libPath)
        FixInject.injectlibPath(libPath)
        AppExtension android=project.getExtensions().getByType(AppExtension.class);
        android.registerTransform(new FixTransformer(project));
    }
}
