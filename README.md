# Jenkins Shared Library - Multiple GitHub Checkout"

[Click here to view the published version of this documentation with more details](https://daversomethingsomething.github.io/jenkins-multiple-github-checkout/)

## Introduction

## Installation with Jenkins

Installation is simple.  This repo provides the expected/standard
[Jenkins Shared Library](https://www.jenkins.io/doc/book/pipeline/shared-libraries/)
directory/code structure.

For the simplest approach, you can enable this Pipeline step globally in
your Jenkins instance.

Under **`Manage Jenkins` -> `System` -> `Global Trusted Pipeline Libraries`**,
`Add +` a new library here, give it a name like `JenkinsMultipleGitHubCheckout`,
use the default `main` version, and use the **HTTPS** Git URL for this repo.
No special credentials should be required as this repo is public.

!!! quote annotate ""

    ![Manage Jenkins -> Global Trusted Pipeline Libraries](img/JenkinsGlobalTrustedPipelineLibraries.png)

If the Git repos you are working with require authentication to access, then
you can configure an appropriate Jenkins Credential under
**`Manage Jenkins` -> `Credentials` -> `System` -> `Global credentials (unrestricted)`**
or under your project's own credentials domain (preferred).

## Sample Pipeline Usage

!!! quote annotate "Sample Jenkins Pipeline using Jenkins Multiple GitHub Checkout"

    ```groovy
    @Library('JenkinsMultipleGitHubCheckout') _

    pipeline {
        agent any

        stages {
            stage('MultiClone') {
                steps {
                     multiClone(
                        [credentialsId: "JenkinsTest",
                         repoUrls: ['https://github.com/DaverSomethingSomethingOrg/conan-system-packaging.git',
                                   'https://github.com/DaverSomethingSomethingOrg/conan-github-workflows.git'
                         ]])
                     multiClone(
                        [credentialsId: "JenkinsTest",
                         repoUrls: ['https://github.com/DaverSomethingSomethingOrg/conan-build-container.git'
                         ]])
                }
            }
        }
    }
    ```


[Click here to view the published version of this documentation with more details](https://daversomethingsomething.github.io/jenkins-multiple-github-checkout/)

## License and Copyright

Copyright © 2026 David L. Armstrong

[Apache-2.0](LICENSE.txt)
