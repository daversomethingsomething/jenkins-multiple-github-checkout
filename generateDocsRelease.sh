#!/bin/bash

# Build out any groovydoc docstrings available
groovydoc --destdir ./docs/groovydoc --sourcepath src/ org.DaverSomethingSomething.MultipleGitHubCheckout

# Build the mkdocs site onto the `gh-pages` branch and version it
mike deploy v1.0 latest
