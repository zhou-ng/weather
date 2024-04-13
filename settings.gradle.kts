pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "lesson"
include(":app")
include(":lesson3")
include(":lesson4")
include(":lesson2")
include(":lesson5_1")
include(":lesson5_2")
include(":lesson4_2")
include(":lesson11")
include(":weather")
