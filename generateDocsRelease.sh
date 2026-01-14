#!/bin/bash

# Build out any groovydoc docstrings available
groovydoc -destdir ./docs/groovydoc \
          -classpath vars/multiClone.groovy \
          -verbose \
          -nomainforscripts \
          org.DaverSomethingSomething

# Build the mkdocs site onto the `gh-pages` branch and version it
mike deploy v1.0 latest
