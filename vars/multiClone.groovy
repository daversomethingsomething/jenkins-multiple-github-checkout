#!/usr/bin/env groovy

/*
 * Copyright (c) 2026, David L. Armstrong
 *
 * This package is distributed under the terms of the Apache License 2.0.
 * For more details, see the full text of the license in the file LICENSE.txt.
 *
 */

//package org.DaverSomethingSomething

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Clones one or more Git repos public or private.
 * Accepts a Map parameter containing name/value parameters as follows.
 * 
 * @param credentialsId ID for the credentials entry in Jenkins to use for authorization
 * @param branch Branch to checkout (default: main)
 * @param shallow Create shallow clones (no history)
 * @param lfs Enable Git Large File Storage (LFS)
 * @param repoUrls List of repos to clone
 * @return `true` on success, throws exception otherwise.
 *
 */
def call(Map params){

    branchCheckout = null
    if(params.branch){
        branchCheckout = params.branch
    }
    extensionsList = []
    if(params.lfs) {
        extensionsList.add(lfs())
    }
    if(params.shallow) {
        extensionsList.add(cloneOption(shallow: true))
    }

    for( repoUrl in params.repoUrls ) {

        subdirectory = "." // default for a single repo

        // If more than one repo, grab the last part of the URL path
        // for the subdirectory name.
        if(params.repoUrls.size() > 1) {
            urlObj = repoUrl.toURL()
            Path filePath = Paths.get(urlObj.getPath())
            subdirectory = filePath.getFileName()
        }

        dir(subdirectory) {
            println "URL: ${repoUrl} Subdir: ${subdirectory}"
            checkout scmGit(
                branches: [[name: branchCheckout]],
                extensions: extensionList,
                userRemoteConfigs: [[credentialsId: params.credentialsId,
                    url: repoUrl]]
            )
        }
    }
}

return this