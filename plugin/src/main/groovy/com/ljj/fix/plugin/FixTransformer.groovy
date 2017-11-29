package com.ljj.fix.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project
import org.apache.commons.io.FileUtils;
/**
 * Created by ljj on 2017/11/27.
 */

public class FixTransformer extends Transform{

  Project project;

  public FixTransformer(Project project) {
    this.project = project;
  }

  @Override
  public String getName() {
    return "fixplugin";
  }

  @Override
  public Set<QualifiedContent.ContentType> getInputTypes() {
    return TransformManager.CONTENT_CLASS;
  }

  @Override
  public Set<QualifiedContent.Scope> getScopes() {
    return TransformManager.SCOPE_FULL_PROJECT;
  }

  @Override
  public boolean isIncremental() {
    return false;
  }

  @Override
  public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs,
      TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException,
      InterruptedException {
    project.getLogger().error("context->path：" + context.getPath());
    project.getLogger().error("context->temporaryDir：" + context.getTemporaryDir().getAbsolutePath());
    for (TransformInput input : inputs) {
      Collection<DirectoryInput> dirInputs = input.getDirectoryInputs();
      Collection<JarInput> jarInputs = input.getJarInputs();
      for (DirectoryInput directoryInput : dirInputs) {
        project.getLogger().error("context->directoryInput：" + directoryInput.getFile().getAbsolutePath());

        File dest = outputProvider
            .getContentLocation(directoryInput.getName(), directoryInput.getContentTypes(), directoryInput.getScopes(),
                Format.DIRECTORY);
        project.getLogger().error("context->directoryOutput：" + dest);
        FixInject.setProject(project);
        //ByteCodeUtil.inject(directoryInput.getFile().getAbsolutePath(),packageName);
        FixInject.injectDir(directoryInput.getFile().getAbsolutePath(),"com/ljj/fixtest");
        FileUtils.copyDirectory(directoryInput.getFile(), dest);
      }
      for (JarInput jarInput : jarInputs) {
        project.getLogger().error("context->JarInput：" + jarInput.getFile().getAbsolutePath());
        File dest = outputProvider
            .getContentLocation(jarInput.getName(), jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
        project.getLogger().error("context->JarOutput：" + dest);
        FileUtils.copyFile(jarInput.getFile(), dest);
      }
    }
  }

}
