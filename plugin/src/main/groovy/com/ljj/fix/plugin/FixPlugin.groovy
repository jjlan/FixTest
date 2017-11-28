package com.ljj.fix.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

/**
 * Created by ljj on 2017/11/27.
 */

public class FixPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.getLogger().error("FixPlugin-------------------start");

    }
}
