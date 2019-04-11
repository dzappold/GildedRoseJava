/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val com_pholser: String = "0.8.2"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2"

    const val hamcrest: String = "2.1"

    const val org_junit_jupiter: String = "5.4.2"

    const val mockito_core: String = "2.27.0"

    const val org_pitest: String = "1.4.7"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.3.1"

        const val currentVersion: String = "5.3.1"

        const val nightlyVersion: String = "5.5-20190410000104+0000"

        const val releaseCandidate: String = "5.4-rc-1"
    }
}
