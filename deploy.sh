#!/bin/bash
#version=A.B.C

if [ -z ${VERSION+x} ]; then echo "VERSION is unset"; exit 1 ; fi
mvn versions:set -DnewVersion=$VERSION && mvn deploy -T2C

git add .
git commit -m "v$VERSION" && git tag "$VERSION" && git push && git push origin "$VERSION" 