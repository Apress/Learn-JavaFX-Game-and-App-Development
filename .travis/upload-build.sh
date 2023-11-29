#!/bin/bash

mkdir upload
cd upload

git clone --quiet https://github.com/AlmasB/builds

cd builds
mkdir Drop

git config user.email "$UP_USER_EMAIL"
git config user.name "up-server"

cp ../../target/drop-game.zip Drop/drop-game-$TRAVIS_OS_NAME.zip

git add Drop/

commit_msg="upload-build.sh"

git commit -m "\"$commit_msg\""

git push --quiet "https://$UP_GH_TOKEN@github.com/AlmasB/builds.git" master > /dev/null 2>&1

echo "Uploaded build"