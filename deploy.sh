#!/bin/bash
#usage : VERSION=A.B.C ./deploy.sh

if [ -z ${VERSION+x} ]; then echo "VERSION is unset"; exit 1 ; fi

git pull && git add .

mvn versions:set -DnewVersion=$VERSION && mvn deploy -T2C

git add .\
 && git commit -m "v$VERSION"\
 && git tag "$VERSION"\
 && git push\
 && git push origin "$VERSION" 