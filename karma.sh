#!/bin/sh

export BROWSER=$1
if [ -z "$BROWSER" ]; then
  export BROWSER="Chrome";
fi

export WATCH=true
export REPORTER=mocha

node_modules/karma/bin/karma start test/karma.conf.js