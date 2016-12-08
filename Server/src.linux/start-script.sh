#!/bin/sh
# 
# Starts the @app.name@ server daemon.
# 
# This file is sourced by Xsession(5), not executed.

/usr/bin/java -jar @install.linux.dir@/@jar.path.relative.linux@ &
