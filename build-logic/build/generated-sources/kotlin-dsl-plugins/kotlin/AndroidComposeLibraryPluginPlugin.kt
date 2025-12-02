/**
 * Precompiled [android-compose-library-plugin.gradle.kts][Android_compose_library_plugin_gradle] script plugin.
 *
 * @see Android_compose_library_plugin_gradle
 */
public
class AndroidComposeLibraryPluginPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Android_compose_library_plugin_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
