/*
 * Copyright 2012 - present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.initializr.generator.spring.build.gradle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.spring.initializr.generator.buildsystem.gradle.GradleBuild;
import io.spring.initializr.generator.buildsystem.gradle.GradleSettingsWriter;
import io.spring.initializr.generator.io.IndentingWriter;
import io.spring.initializr.generator.io.IndentingWriterFactory;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectContributor} for the project's settings file.
 *
 * @author Andy Wilkinson
 * @author Jean-Baptiste Nizet
 */
class VersionCatalogProjectContributor implements ProjectContributor {

  /**
   * The Gradle build.
   */
  private final GradleBuild build;

  /**
   * Factory for creating indenting writers.
   */
  private final IndentingWriterFactory indentingWriterFactory;

  /**
   * Writer for Gradle settings.
   */
  private final GradleSettingsWriter settingsWriter;

  /**
   * Name of the settings file.
   */
  private final String settingsFileName;

  VersionCatalogProjectContributor(GradleBuild gradleBuild, IndentingWriterFactory writerFactory,
                                   GradleSettingsWriter gradleSettingsWriter, String gradleSettingsFileName) {
    this.build = gradleBuild;
    this.indentingWriterFactory = writerFactory;
    this.settingsWriter = gradleSettingsWriter;
    this.settingsFileName = gradleSettingsFileName;
  }

  @Override
  public void contribute(Path projectRoot) throws IOException {
    final Path catalogFile = projectRoot.resolve(this.settingsFileName);
//    Path settingsGradle = Files.createFile(projectRoot.resolve(this.settingsFileName));

//    // ✅ Ensure parent directories exist
//    Files.createDirectories(catalogFile.getParent());
//    try (IndentingWriter writer = this.indentingWriterFactory.createIndentingWriter("toml",
//        Files.newBufferedWriter(settingsGradle))) {
//      this.settingsWriter.writeTo(writer, this.build);
//    }

    // ✅ Ensure parent directories exist
    Files.createDirectories(catalogFile.getParent());

    // ✅ Create or truncate file safely
    try (IndentingWriter writer = this.indentingWriterFactory.createIndentingWriter(
        "toml",
        Files.newBufferedWriter(catalogFile))) {
      this.settingsWriter.writeTo(writer, this.build);
    }
  }

}
