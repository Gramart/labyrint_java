#!/usr/bin/env bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
wget --user=kimagencyc --password=Lgwx3ERx kimagency.cz:www/personalfolder/resources.zip $DIR
unzip resources.zip
rm resources.zip
