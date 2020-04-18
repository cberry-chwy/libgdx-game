#!/usr/bin/env bash
find . -type f -name 'kisspng-rpg-maker-mv-role-playing-video-game-sprite-2d-com-5b444a54b5fb60*' | while read FILE ; do
    newfile="$(echo ${FILE} |sed -e 's/\kisspng-rpg-maker-mv-role-playing-video-game-sprite-2d-com-5b444a54b5fb60/hero/')" ;
    mv "${FILE}" "${newfile}" ;
done